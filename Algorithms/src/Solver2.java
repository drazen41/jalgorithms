import edu.princeton.cs.algs4.*;


public final class Solver2 {
	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private int moves;
		private SearchNode previous;
		private int manPriority=-1;
		private int hamPriority;
		public SearchNode(Board board, int moves, SearchNode previous) {
			// TODO Auto-generated constructor stub
			this.board = board;
//			if (previous != null) {
//				this.moves = moves + previous.moves;
//			} else {
//				
//			}
			this.moves = moves;
			this.previous = previous;
			this.manPriority = board.manhattan() + this.moves;			
			this.hamPriority = board.hamming();
		}
		private int manPriority() {
			if (manPriority==-1) {
				this.manPriority = board.manhattan() + this.moves;
			}
			return this.manPriority;
		}
		@Override
		public int compareTo(SearchNode that) {
			// TODO Auto-generated method stub
			if (this.manPriority() <= that.manPriority()) {
				return -1;
			} else if (this.manPriority() > that.manPriority()) {
				return 1;
			} else if (this.manPriority == that.manPriority) {
//				if (this.hamPriority > that.hamPriority) {
//					return 1;
//				} else if (this.hamPriority < that.hamPriority) {
//					return -1;
//				} 
			}
			
			return 0;
		}
		
	}
	private final Stack<Board> boards;
	private final MinPQ<SearchNode> pq;
	private boolean isSolvable;
	private int numberOfMoves;
	public Solver2(Board initial) {
		// TODO Auto-generated constructor stub
		if (initial == null) {
			throw new IllegalArgumentException();
		}
		isSolvable = false;
		int moves=0;
		SearchNode searchNode = new SearchNode(initial, moves, null);
		
		pq = new MinPQ<SearchNode>();
		MinPQ<SearchNode> pqTwin = new MinPQ<SearchNode>();
		pq.insert(searchNode);
		Board twin = initial.twin();
		SearchNode searchNodeTwin = new SearchNode(twin, moves, null);
		pqTwin.insert(searchNodeTwin);
		boards = new Stack<Board>();
		Iterable<Board> neighbours = null;
		Iterable<Board> twinNeighbours = null;
		Board board = null;
		
		boolean stop = false;
		
		while(!stop && moves <1000) {
			moves++;
			searchNode = pq.delMin();
			searchNodeTwin = pqTwin.delMin();
//			StdOut.println(searchNode.board + "Man : " + searchNode.manPriority + " Ham: " + searchNode.hamPriority);
			board = searchNode.board;
			twin = searchNodeTwin.board;
			if (board.isGoal()) {
				stop = true;
				isSolvable = true;
				
				this.boards.push(board);
				while(searchNode.previous != null) {
					searchNode = searchNode.previous;
					this.boards.push(searchNode.board);
					numberOfMoves++;
				}
				continue;
			}
//			if (twin.isGoal()) {
//				stop = true;
//				continue;
//			}
			neighbours = board.neighbors();
			twinNeighbours = twin.neighbors();
//			SearchNode previous = new SearchNode(board, searchNode.moves, searchNode);
//			SearchNode previousTwin = new SearchNode(twin, searchNodeTwin.moves, searchNodeTwin);
			for (Board board1 : neighbours) {
				
				if (searchNode.previous != null && board1.equals(searchNode.previous.board)) {
					continue;
				}
				SearchNode searchNode3 = new SearchNode(board1, moves, searchNode);
				pq.insert(searchNode3);
//				StdOut.print("Man: " + searchNode3.manPriority + " Ham: " + searchNode3.hamPriority + "\n" );
//				StdOut.println();
			}
			for (Board board2 : twinNeighbours) {				
				if (searchNodeTwin.previous != null && board2.equals(searchNodeTwin.previous.board)) {
					continue;
				}
				SearchNode searchNode3 = new SearchNode(board2, moves, searchNodeTwin);
				pqTwin.insert(searchNode3);
			}
			
		}
			
	}
	
	
	public boolean isSolvable() {            // is the initial board solvable?
		return isSolvable;
	}
    public int moves() {                     // min number of moves to solve initial board; -1 if unsolvable
    	if (isSolvable) {
			return numberOfMoves;
		}
    	return -1;
    }
    public Iterable<Board> solution() {      // sequence of boards in a shortest solution; null if unsolvable
    	return this.boards;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver2 solver = new Solver2(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	    Iterable<Board> solution = solver.solution();
	   
	    
//	    for (Board board : solver.boards) {
//			StdOut.println(board);
//		}
	}

}
