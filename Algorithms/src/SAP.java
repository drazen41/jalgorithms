import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
		
		
		private Digraph digraph;
		private int ancestor;
		private int length;
		
	// constructor takes a digraph (not necessarily a DAG)
	   public SAP(Digraph G) {
		   digraph = new Digraph(G);
		  this.ancestor = -1;
		  this.length = digraph.V();
		   
	   }

	   // length of shortest ancestral path between v and w; -1 if no such path
	   public int length(int v, int w) {		   
		   calculateLengthAncestorFor(v, w);
		   if (this.length == digraph.V()) {
			   return -1;
		   }
		   return this.length;
	   }

	   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	   public int ancestor(int v, int w) {
		   calculateLengthAncestorFor(v, w);		   
		   return this.ancestor;
	   }

	   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	   public int length(Iterable<Integer> v, Iterable<Integer> w) {
		   BreadthFirstDirectedPaths bfdp = new BreadthFirstDirectedPaths(digraph, w);
		   for (Integer v1 : v) {
			   for (Integer w1 : w) {
				   calculateLengthAncestorFor(v1, w1);
			   }
			   
		   }
		   if (this.length == digraph.V()) {
			   return -1;
		   }
		   return this.length;
	   }

	   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	   public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		   for (Integer v1 : v) {
			   for (Integer w1 : w) {
				   calculateLengthAncestorFor(v1, w1);
			   }
			   
		   }
		   return this.ancestor;
	   }
	   private void calculateLengthAncestorFor(int v, int w) {
		   
		  
		   BreadthFirstDirectedPaths bfdp = new BreadthFirstDirectedPaths(digraph, w);
		   boolean visited[] = new boolean[digraph.V()];
		   Queue<Integer> red = new Queue<Integer>();
		   int i = 1;
		   red.enqueue(v);
		   boolean stop = false;
		   while(!red.isEmpty() && !stop) {
			   if (i > this.length) {
				   stop = true;
				   continue;
			   }
			   int vert = red.dequeue();
			   Iterable<Integer> adj = digraph.adj(vert);
			   for (Integer visit : adj) {
				   red.enqueue(visit);
				   if (!visited[visit]) {
					   visited[visit] = true;
					   if (bfdp.hasPathTo(visit) && bfdp.distTo(visit)<length) {
						   this.length = bfdp.distTo(visit) + i;
						   this.ancestor = visit;
					   }
				   }
			   }
			   i++;
		   }
	   }
	// do unit testing of this class
	   public static void main(String[] args) {
		   In in = new In(args[0]);
		   Digraph G = new Digraph(in);
		   StdOut.println(G.toString());
		   SAP sap = new SAP(G);
		   int v = 3;
		   int w = 11;
		   BreadthFirstDirectedPaths breadthFirstDirectedPaths = new BreadthFirstDirectedPaths(G, 3);
		   BreadthFirstDirectedPaths bfdp1 = new BreadthFirstDirectedPaths(G, 11);
		   boolean visited[] = new boolean[G.V()];
		   int length = G.V();
		   Queue<Integer> red = new Queue<Integer>();
		   int i = 1;
		   red.enqueue(v);
		   while(!red.isEmpty()) {
			   int vert = red.dequeue();
			   Iterable<Integer> adj = G.adj(vert);
			   for (Integer visit : adj) {
				   red.enqueue(visit);
				   if (!visited[visit]) {
					   visited[visit] = true;
					   if (bfdp1.hasPathTo(visit) && bfdp1.distTo(visit)>0 && bfdp1.distTo(visit)<length) {
						   length = bfdp1.distTo(visit) + i;
						  
					   }
				   }
			   }
			   i++;
		   }
		  // StdOut.println(length);
		  StdOut.println("Length is: " + sap.length(v,w));
		  StdOut.println("Ancestor is: " + sap.ancestor(v,w));
		   
			
		}

		   
	   

}
