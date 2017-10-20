import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public final class Board2 {
	
	private class SearchNode implements Comparable<SearchNode> {
		private int[][] blocks;
		int numberOfMoves = 0;
		SearchNode predecessor = null;
		public SearchNode(int[][]blocks, int numberOfMoves, SearchNode predecessor) {
			// TODO Auto-generated constructor stub
			this.blocks = blocks;
			this.numberOfMoves = numberOfMoves;
			this.predecessor = predecessor;
		}
		@Override
		public int compareTo(SearchNode that) {
			// TODO Auto-generated method stub
			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks.length; j++) {
					if (this.blocks[i][j] == that.blocks[i][j]) {
						return 0;
					} else if (this.blocks[i][j] < that.blocks[i][j]) {
						return -1;
					}
				}
			}
			return 1;
		}
	}
	private SearchNode searchNode = null;
	private MinPQ<SearchNode> pq = null;
	private SearchNode goalNode = null;
	public Board2(int [][] blocks) {
		// TODO Auto-generated constructor stub
		searchNode = new SearchNode(blocks, 0, null);
		pq = new MinPQ<SearchNode>();
		pq.insert(searchNode);
		int[][] goal = new int[blocks.length][blocks.length];
		for (int i = 0; i < goal.length; i++) {
			for (int j = 0; j < goal.length; j++) {
//				if (i == blocks.length-1 && j == blocks.length-1) {
//					goal[i][j] = 0;
//				} else {
//					
//				}
				goal[i][j] = i*blocks.length + j + 1;
			}
		}
		goalNode = new SearchNode(goal,0,null);
		
	}
	public int dimension() {                 // board dimension n
		return this.searchNode.blocks.length;
	}
    public int hamming() {                   // number of blocks out of place
    	return 0;
    }
    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
    	return 0;
    }
    public boolean isGoal() {                // is this board the goal board?
    	return false;
    }
    public Board2 twin() {                    // a board that is obtained by exchanging any pair of blocks
    	return null;
    }
    public boolean equals(Object y) {        // does this board equal y?
    	return false;
    }
    public Iterable<Board2> neighbors() {     // all neighboring boards
    	Iterable<Board2> iterable = new Iterable<Board2>() {
			
			@Override
			public Iterator<Board2> iterator() {
				// TODO Auto-generated method stub
				return null;
			}
		};
    	return iterable;
    }
    public String toString() {               // string representation of this board (in the output format specified below)
    	String buffer = "";
    	buffer += dimension();
    	for (int i = 0; i < this.searchNode.blocks.length; i++) {
			buffer += "\n";
    		for (int j = 0; j < this.searchNode.blocks.length; j++) {
				buffer += this.searchNode.blocks[i][j] + " ";
			}
		}
    	return buffer;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board2 initial = new Board2(blocks);
//	    StdOut.println(initial.dimension());
	    StdOut.println(initial.toString());
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}

}
