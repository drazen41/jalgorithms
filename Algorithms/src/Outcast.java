import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public final class Outcast {
	
	private WordNet wordNet;
	public Outcast(WordNet wordnet) {         // constructor takes a WordNet object
		
		this.wordNet = wordnet;
		
	}
	public String outcast(String[] nouns) {   // given an array of WordNet nouns, return an outcast
		List<String> list = Arrays.asList(nouns);
		int length = 0;
		String sap = "";
		String nA = "", nB = "";
		for (int i = 0; i<list.size();i++) {
			int dist = 0;
			String nounA = list.get(i);
			for (int j = 1; j<list.size();j++) {
				
				String nounB = list.get(j);
				int t = wordNet.distance(nounA, nounB);
				dist += t;
				if (dist > length ) {
					length = dist;
					nA = nounA;
					nB = nounB;
				}	
					
			}
			
		}
		sap = wordNet.sap(nA, nB);
		return sap;
	}
	public static void main(String[] args) {  // see test client below
//		WordNet wordNet = new WordNet(args[0],args[1]);
//		Outcast outcast = new Outcast(wordNet);
//		String[] nouns = new String[5];
//		nouns[0] = "worm";
//		nouns[1] = "animal";
//		nouns[2] = "bird";
//		nouns[3] = "Aare";
//		nouns[4] = "Abel";
//		StdOut.println(outcast.outcast(nouns));
		WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}

}
