import javax.swing.undo.AbstractUndoableEdit;

import edu.princeton.cs.algs4.AcyclicSP;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Topological;

public class TestShortestPathDirectedGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		DirectedEdge.main(null);
		EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(10,7);
		StdOut.println(edgeWeightedDigraph.toString());
		Topological topological = new Topological(edgeWeightedDigraph);
		StdOut.println(topological.hasOrder());
//		AcyclicSP acyclicSP = new AcyclicSP(edgeWeightedDigraph , 1);
//		for (DirectedEdge edge : acyclicSP.pathTo(9)) {
//			StdOut.println(edge);
//		}
		DijkstraSP dijkstraSP = new DijkstraSP(edgeWeightedDigraph, 0);
		StdOut.println(dijkstraSP.hasPathTo(4));

	}

}
