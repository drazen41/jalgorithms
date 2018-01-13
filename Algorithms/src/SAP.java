import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;



public final class SAP {
		
		
		private final Digraph digraph;
		private int ancestor;
		private int length;
                private Bag<Integer> sources;
                private class Ancestor {
                    int source;
                    int length;
                    
                }
		private Ancestor value;
	// constructor takes a digraph (not necessarily a DAG)
	   public SAP(Digraph G) {
		  if (G == null) {
			throw new IllegalArgumentException();
		}
		  digraph = new Digraph(G);
		  this.ancestor = -1;
		  this.length = digraph.V();
                  sources = new Bag<>();
		  calculateSAP();
	   }
           private void calculateSAP(){
               /*
               BreadthFirstDirectedPaths bfdpV = null;
               BreadthFirstDirectedPaths bfdpW = null;
               BreadthFirstDirectedPaths temp = null;
               for (int i = 0; i < this.digraph.V(); i++) {                  
                   if (this.digraph.indegree(i)>0) {
                       bfdpV = new BreadthFirstDirectedPaths(digraph, i);
                   }
                   
               }
               */
               for (int i = 0; i < this.digraph.V(); i++) {                  
                   if (this.digraph.outdegree(i)>0) {
                       sources.add(i);
                   }                  
               }
               BreadthFirstDirectedPaths bfdp = new BreadthFirstDirectedPaths(digraph, sources);
               for (int i = 0; i < sources.size(); i++) {
                   for (int j = 1; j < sources.size(); j++) {
                       if (i <= j) {
                           continue;
                       }
                      
                       
                   }
               }
               
           }
           public int length(int v, int w) {
               return length;
           }
           public int length(Iterable<Integer> v, Iterable<Integer> w) {
               return length;
           }
           public int ancestor (int v, int w) {
               return ancestor;
           }
           public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
               return ancestor;
           }
	   private void validateInputs(int v, int w) {
		   if (v < 0 || w < 0 || v > digraph.V() - 1 || w > digraph.V()-1) {
			   throw new IllegalArgumentException();
		   }
	   }
	   // length of shortest ancestral path between v and w; -1 if no such path
	   public int length1(int v, int w) {		   
		   validateInputs(v, w);
		   this.length = digraph.V();
		   this.ancestor = -1;
		   calculateLengthAncestorFor(v, w);
		   if (this.length == digraph.V()) {
			   return -1;
		   }
		   return this.length;
	   }

	   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	   public int ancestor1(int v, int w) {
		   validateInputs(v, w);
		   if (this.ancestor > -1) {
			   return this.ancestor;
		   }
		   this.length = digraph.V();
		   this.ancestor = -1;
		   calculateLengthAncestorFor(v, w);		   
		   return this.ancestor;
	   }

	   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	   public int length1(Iterable<Integer> v, Iterable<Integer> w) {
		   if (v == null || w == null) {
			   throw new IllegalArgumentException();
		   }
		   int vSize = iterableCount(v);
		   int wSize = iterableCount(w);
		   this.length = digraph.V();
		   this.ancestor = -1;
		   boolean visited[][] = new boolean [vSize][wSize];
		   for (Integer v1 : v) {
			   for (Integer w1 : w) {
				   validateInputs(v1, w1);
//				   if (!visited[v1][w1]) {
//					   visited[v1][w1]=true;
//					   visited[w1][v1]=true;
//					   
//				   }
				   calculateLengthAncestorFor(v1, w1);
			   }
			   
		   }
		   if (this.length == digraph.V()) {
			   return -1;
		   }
		   return this.length;
	   }
	   private int iterableCount(Iterable<Integer> it) {
		   if (it instanceof Collection) {
			   return ((Collection<Integer>) it).size();
		   } else {
			   Iterator<Integer> iterator = it.iterator();
			   int count = 0;
			   while(iterator.hasNext()) {
		            iterator.next();
		            count++;
		       }
			   return count;
		   }
	   }
	   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	   public int ancestor1(Iterable<Integer> v, Iterable<Integer> w) {
		   if (v == null || w == null) {
			   throw new IllegalArgumentException();
		   }
		   int vSize = iterableCount(v);
		   int wSize = iterableCount(w);
		   this.length = digraph.V();
		   this.ancestor = -1;
		   boolean visited[][] = new boolean [vSize][wSize];
		   for (Integer v1 : v) {
			   for (Integer w1 : w) {
				   validateInputs(v1, w1);
//				   if (!visited[v1][w1]) {
//					   visited[v1][w1]=true;
//					   visited[w1][v1]=true;
//					   
//				   }
				   calculateLengthAncestorFor(v1, w1);
			   }
			   
		   }
		   return this.ancestor;
	   }
	   private void calculateLengthAncestorFor(int v, int w) {
		   if (v==w) {
			   this.length = 0;
			   this.ancestor = v;
			   return;
		   }
			 
		   BreadthFirstDirectedPaths bfdpV = new BreadthFirstDirectedPaths(digraph, v);
		   BreadthFirstDirectedPaths bfdp = new BreadthFirstDirectedPaths(digraph, w);
		   boolean visited[] = new boolean[digraph.V()];
		   Queue<Integer> red = new Queue<Integer>();
		   int i = 1;
		   
		   boolean stop = false;
		   int pathToW;
		   int pathToV;
		   if (bfdpV.hasPathTo(w) || bfdp.hasPathTo(v)) {
			   pathToW = bfdpV.distTo(w);
			   pathToV = bfdp.distTo(v);
			   if (pathToV < pathToW) {
				   int t = v;
				   v = w;
				   w = t;
				   bfdpV = new BreadthFirstDirectedPaths(digraph, v);
				   bfdp = new BreadthFirstDirectedPaths(digraph, w);
			   }
			  
		   }
		   
		   red.enqueue(v);
		   while(!red.isEmpty() && !stop) {
//			   if (i > this.length) {
//				   stop = true;
//				   continue;
//			   }
			   int vert = red.dequeue();
			   Iterable<Integer> adj = digraph.adj(vert);
			   
			   for (Integer visit : adj) {
				   if (visited[visit])
					   continue;
				   red.enqueue(visit);
				   if (!visited[visit]) {
					   visited[visit] = true;
					   
				   }
				   if (bfdp.hasPathTo(visit) && bfdpV.hasPathTo(visit)) {
					   int lengthW = bfdp.distTo(visit);
					   int lengthV = bfdpV.distTo(visit);
					   int totalLength = lengthW + lengthV;		   
					   if (totalLength<length) {
						   this.length = totalLength;
						   this.ancestor = visit;
					   }
				   }
				   
			   }
			   
			   i++;
		   }
		   
	   }
	// do unit testing of this class
	   public static void main(String[] args) {
		
               args = new String[2];
                //args[0] = "digraph1.txt";
                args[0] = "digraph-wordnet.txt";
                Stopwatch stopwatch = new Stopwatch();
               In in = new In(args[0]);
		   Digraph G = new Digraph(in);
		   //StdOut.println(G.toString());
                   
		   SAP sap = new SAP(G);
                   /*
                   StdOut.println("Created SAP in: " + stopwatch.elapsedTime());
		   int v = 3;
		   int w = 7;
//		   Queue<Integer> vis = new Queue<Integer>();
//		   vis.enqueue(1);
//		   vis.enqueue(2);
//		   vis.enqueue(3);
//		   Queue<Integer> wis = new Queue<Integer>();
//		   wis.enqueue(3);
//		   wis.enqueue(4);
//		   wis.enqueue(5);
//		  // StdOut.println(length);
                  
		  StdOut.println("Length is: " + sap.length(v,w));
		  StdOut.println("Ancestor is: " + sap.ancestor(v,w));
//		  StdOut.println("Length for iterable vis wis: " + sap.length(vis, wis));
//		  StdOut.println("Ancestor for iterable vis wis: " + sap.ancestor(vis, wis));
		   */
                   /*
                  Bag<Integer> sources = new Bag<Integer>();
                  for (int i = 0; i < G.V(); i++) {
                      if (G.outdegree(i)>0){                     
                      sources.add(i);
                      }
                  }
                  */
                  Iterable<Integer> path = new Bag<Integer>();
//                  BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, sources);
                  /*
                  for (int i = 0; i < sources.size(); i++) {
                      path = bfs.pathTo(i);
                      StdOut.print("Path to " + i + ":" );
                      for (int v : path) {
                          StdOut.print(v + "->");
                      }
                      StdOut.println();
                      
                  }
                  
                  for (int i = 0; i < G.V(); i++) {
                      StdOut.print("Dist from " + i);
                      for (int j = 1; j < G.V(); j++) {
                          StdOut.print(" to " + j + " = " + bfs.distTo(j));
                      }
                      StdOut.println();
                  }
                  */
                  ArrayList<HashMap<Integer,Integer>> list = new ArrayList<>();
                  HashMap<Integer,Integer> ancestor = new HashMap<>();
                  //ancestor.add(0, 1);
                  //list.add(0, ancestor);
                  HashMap<Integer,HashMap<Integer,Integer>> lista = new HashMap<>();
                  
                  BreadthFirstDirectedPaths bfs1 = null;
                  for (int i = 0; i < G.V(); i++) {
                      bfs1 = new BreadthFirstDirectedPaths(G, i);
                      ancestor = new HashMap<>();
                      for (int j = 0; j < G.V(); j++) {
                          if (bfs1.hasPathTo(j) && i!=j) {
                              ancestor.put(j, bfs1.distTo(j));
                          }
                          /*
                          Iterable<Integer> paths = bfs1.pathTo(j);
                          StdOut.print("Path " + i + " to " + j + " : " );
                          for (Integer path1 : paths) {
                              StdOut.print(path1 + "->");
                          }
                          StdOut.println("Dist from " + i + " to " + j + " = " + bfs1.distTo(i));
                          */
                      }
                      list.add(i, ancestor);                     
		    }
                  StdOut.println(stopwatch.elapsedTime());
                    int v = 135;
                    int w = 2000;
                    
                    HashMap<Integer,Integer> vList = list.get(v);
                    HashMap<Integer,Integer> wList = list.get(w);
                    HashMap<Integer,Integer> temp;
                    int k = vList.getOrDefault(w, -1);
                   int l = wList.getOrDefault(v, -1);
                   int length=Integer.MAX_VALUE;
                   int sAncestor = Integer.MAX_VALUE;
                    if (k>-1) {
                        //StdOut.println("Ancestor: " + w);
                        length = k;
                        sAncestor = w;
                    } else if (l>-1) {
                       //StdOut.println("Ancestor: " + v);
                       length = l;
                       sAncestor = v;
                    } else {
                        for (int vKey : vList.keySet()) {
                            for (int wKey : wList.keySet()) {
                                if (vKey == wKey) {
                                    l = vList.get(vKey) + wList.get(wKey);
                                    if (l < length) {
                                        length = l;
                                        sAncestor = vKey;
                                    }
                                }
                            }
                        }
                        
                    }
                    StdOut.println("Ancestor: " + sAncestor + "; length: " + length);

           }

           
	   

}
