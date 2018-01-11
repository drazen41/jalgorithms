import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class WordNet {
	 
	   private class Synset  {
		   private int id;
		   private String syns;
		   private String gloss;
		   private Synset Next;
		   private Synset(int id, String syns, String gloss) {
			   this.id = id;
			   this.syns = syns;
			   this.gloss = gloss;
		   }
		   private Synset() {
			   
		   }
		   
	   }
	   private HashMap<String,Queue<Synset>> nouns;
	   private Digraph digraph;
	   private int vertices;
	   private HashMap<Integer,String> synsetIds;
	   private Boolean rooted = true;
	   // constructor takes the name of the two input files
	   public WordNet(String synsets, String hypernyms) {
		   if (synsets == null || hypernyms == null) {
			   throw new IllegalArgumentException();
		   }
		   In hypernymsText = new In(hypernyms);
		   String[] lines = hypernymsText.readAllLines();
		  
		  int max = 0;
		  for (String line : lines) {
			   String[] elements = line.split(",");
			   int v = Integer.parseInt(elements[0]);
			   for (int i=1; i<elements.length;i++) {
				   int w = Integer.parseInt(elements[i]);
				  if (w > max) {
					max = w;
				}
			   }
			   
		   }
		  
		   digraph = new Digraph(max+1);
		   this.vertices = lines.length;
		   for (String line : lines) {
			   String[] elements = line.split(",");
			   int v = Integer.parseInt(elements[0]);
			   for (int i=1; i<elements.length;i++) {
				   int w = Integer.parseInt(elements[i]);
				   digraph.addEdge(v, w);
			   }
			   
		   }
		   DirectedCycle dcycle = new DirectedCycle(digraph);
		   if (dcycle.hasCycle())
			   throw new IllegalArgumentException();
		   // check rooted
		   checkRooted();
		   if (!this.rooted )
			   throw new IllegalArgumentException("Digraph not rooted");
		   In synsetsText = new In(synsets);
		   nouns = new HashMap<String,Queue<Synset>>();
		   synsetIds = new HashMap<Integer,String>();
		   int synsetMax = -1;
		   while (!synsetsText.isEmpty()) {
			   String line = synsetsText.readLine();
			   String[] fields = line.split(",");
			   synsetIds.put(Integer.parseInt(fields[0]), fields[1]);
			   String[] sNouns = fields[1].split(" ");
			   for (String sNoun : sNouns) {
				   int synsetId = Integer.parseInt(fields[0]);
				   if (synsetId > synsetMax)
					   synsetMax = synsetId;
				   Synset synset = new Synset();
				   Queue<Synset> red = nouns.get(sNoun);
				   synset.id = synsetId;
				   synset.syns = sNoun;
				   if (red == null) {
					   red = new Queue<Synset>();
					   red.enqueue(synset);
					   nouns.put(sNoun,red);
				   } else {
//					   prev.Next = synset;
					   red.enqueue(synset);
					   
					   
				   }
				   
			   }
			   
		   }
		   
	   }
	   private void checkRooted() {
		   
		   int count = 0;
		   for (int i = 0; i < this.digraph.V(); i++) {
			   if (this.digraph.indegree(i)==0 && this.digraph.outdegree(i)==0)
				   continue;
			   if (this.digraph.outdegree(i)==0 && this.digraph.indegree(i)>0)
				   count++;
			   
		   }
		   if (count>1) this.rooted = false;
	   }
	   // returns all WordNet nouns
	   public Iterable<String> nouns() {
		   return nouns.keySet();
	   }

	   // is the word a WordNet noun?
	   public boolean isNoun(String word) {
		   if (word == null)
			   throw new IllegalArgumentException();
		   return nouns.containsKey(word);
	   }

	   // distance between nounA and nounB (defined below)
	   public int distance(String nounA, String nounB) {
		   if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
			   throw new IllegalArgumentException();
		   Queue<Synset> vSynset = nouns.get(nounA);
		   Queue<Synset> wSynset = nouns.get(nounB);
		   Queue<Integer> vis = new Queue<Integer>();
		   Queue<Integer> wis = new Queue<Integer>();
		   for (Synset s : vSynset) {
			   vis.enqueue(s.id);
		   }
		   for (Synset s : wSynset) {
			   wis.enqueue(s.id);
		   }
		   SAP sap = new SAP(this.digraph);
		   int length = this.vertices;

		   length = sap.length(vis, wis);
		   
		  return length;
	   }

	   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	   // in a shortest ancestral path (defined below)
	   public String sap(String nounA, String nounB) {
		   if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
			   throw new IllegalArgumentException();
		   Queue<Synset> vSynset = nouns.get(nounA);
		   Queue<Synset> wSynset = nouns.get(nounB);
		   Queue<Integer> vis = new Queue<Integer>();
		   Queue<Integer> wis = new Queue<Integer>();
		   for (Synset s : vSynset) {
			   vis.enqueue(s.id);
		   }
		   for (Synset s : wSynset) {
			   wis.enqueue(s.id);
		   }
		   SAP sap = new SAP(this.digraph);
		   int a = sap.ancestor(vis, wis);
		   return synsetIds.get(a);
	   }

	   // do unit testing of this class
	   public static void main(String[] args) {
		   Stopwatch stopwatch = new Stopwatch();
		   WordNet wordNet = new WordNet(args[0],args[1]);
//		   for (String noun : wordNet.nouns()) {
//			   StdOut.println(noun);
//		   }
//		   StdOut.println( stopwatch.elapsedTime());
//		   StdOut.println(wordNet.isNoun("fibrinase"));
//		   StdOut.println(wordNet.isNoun("abrakadabra"));
//		   StdOut.println(wordNet.distance("worm", "bird"));
//		   StdOut.println(wordNet.sap("worm", "bird"));
		   
		   StdOut.println(wordNet.isNoun("f"));
		   StdOut.println(wordNet.isNoun("a"));
		   StdOut.println(wordNet.distance("f", "a"));
		   StdOut.println(wordNet.sap("f", "a"));
		   
	   }

}
