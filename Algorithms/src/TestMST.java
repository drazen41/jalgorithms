import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.KruskalMST;
import edu.princeton.cs.algs4.LazyPrimMST;
import edu.princeton.cs.algs4.PrimMST;
import edu.princeton.cs.algs4.StdOut;

public class TestMST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 Edge.main(null);
	     EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(10,7);
	     StdOut.println(edgeWeightedGraph.toString());
//	     KruskalMST kruskalMST = new KruskalMST(edgeWeightedGraph);
//	     for (Edge edge : kruskalMST.edges()) {
//	    	 StdOut.println(edge.toString());
//	     }
//	     LazyPrimMST lazyPrimMST = new LazyPrimMST(edgeWeightedGraph);
//	     for (Edge edge : lazyPrimMST.edges()) {
//	    	 StdOut.println(edge);
//	     }
	     PrimMST primMST = new PrimMST(edgeWeightedGraph);
	     for (Edge edge : primMST.edges()) {
	    	 StdOut.println(edge);
	     }
	}

}
