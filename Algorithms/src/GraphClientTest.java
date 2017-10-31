import edu.princeton.cs.algs4.*;
public class GraphClientTest {

	public GraphClientTest() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[]) {
		In in = new In(args[0]);
//        Graph G = new Graph(in);
//        int s = Integer.parseInt(args[1]);
//        StdOut.println("************ DFS **********************");
//        DepthFirstPaths dfs = new DepthFirstPaths(G, s);
//
//        for (int v = 0; v < G.V(); v++) {
//            if (dfs.hasPathTo(v)) {
//                StdOut.printf("%d to %d:  ", s, v);
//                for (int x : dfs.pathTo(v)) {
//                    if (x == s) StdOut.print(x);
//                    else        StdOut.print("-" + x);
//                }
//                StdOut.println();
//            }
//
//            else {
//                StdOut.printf("%d to %d:  not connected\n", s, v);
//            }
//
//        }
//        StdOut.println("************ BFS **********************");
//        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
//
//        for (int v = 0; v < G.V(); v++) {
//            if (bfs.hasPathTo(v)) {
//                StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
//                for (int x : bfs.pathTo(v)) {
//                    if (x == s) StdOut.print(x);
//                    else        StdOut.print("-" + x);
//                }
//                StdOut.println();
//            }
//
//            else {
//                StdOut.printf("%d to %d (-):  not connected\n", s, v);
//            }
//
//        }
//        StdOut.println("************ Connected Components **********************");
//        CC cc = new CC(G);
//
//        // number of connected components
//        int m = cc.count();
//        StdOut.println(m + " components");
//
//        // compute list of vertices in each connected component
//        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
//        for (int i = 0; i < m; i++) {
//            components[i] = new Queue<Integer>();
//        }
//        for (int v = 0; v < G.V(); v++) {
//            components[cc.id(v)].enqueue(v);
//        }
//
//        // print results
//        for (int i = 0; i < m; i++) {
//            for (int v : components[i]) {
//                StdOut.print(v + " ");
//            }
//            StdOut.println();
//        }
//        StdOut.println("************ Digraph **********************");
//        Digraph G1 = new Digraph(in);
//        StdOut.println(G1);
//        
//     // read in sources from command-line arguments
//        Bag<Integer> sources = new Bag<Integer>();
//        for (int i = 1; i < args.length; i++) {
//            int s = Integer.parseInt(args[i]);
//            sources.add(s);
//        }
//        StdOut.println("************ Directed DFS **********************");
//        // multiple-source reachability
//        DirectedDFS dfs = new DirectedDFS(G1, sources);
//
//        // print out vertices reachable from sources
//        for (int v = 0; v < G1.V(); v++) {
//            if (dfs.marked(v)) StdOut.print(v + " ");
//        }
//        StdOut.println();
//        
//        DepthFirstOrder dfs = new DepthFirstOrder(G1);
//        StdOut.println("   v  pre post");
//        StdOut.println("--------------");
//        for (int v = 0; v < G1.V(); v++) {
//            StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
//        }
//
//        StdOut.print("Preorder:  ");
//        for (int v : dfs.pre()) {
//            StdOut.print(v + " ");
//        }
//        StdOut.println();
//
//        StdOut.print("Postorder: ");
//        for (int v : dfs.post()) {
//            StdOut.print(v + " ");
//        }
//        StdOut.println();
//
//        StdOut.print("Reverse postorder: ");
//        for (int v : dfs.reversePost()) {
//            StdOut.print(v + " ");
//        }
//        StdOut.println();
        
        
       
	}

}
