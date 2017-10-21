import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public final class Board {
	
	private final int[][]blocks;
	private final int n;
	private int zeroI;
	private int zeroJ;
	public Board(int [][] blocks) {
		// TODO Auto-generated constructor stub
		n = blocks.length;
		this.blocks = new int[n][n];
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				this.blocks[i][j]= blocks[i][j];
				if (this.blocks[i][j]==0) {
					zeroI = i;
					zeroJ = j;
				}
			}
		}
		
	}
	private int[][] createGoalBoard() {
		int[][] goal = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == n-1 && j == n-1) {
					goal[i][j] = 0;
				} else {
					goal[i][j] = i*n + j + 1;
				}
				
			}
		}
		return goal;
	}
	private int goalValueAt(int i, int j) {
		if (i == n-1 && j == n-1) {
			return 0;
		}
		return i*n + j + 1;
	}
	
	public int dimension() {                 // board dimension n
		return n;
	}
    public int hamming() {                   // number of blocks out of place
    	int ham = 0;
    	for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				if (blocks[i][j] != goalValueAt(i, j) && blocks[i][j] != 0) {
					ham++;
				}
			}
		}
    	
    	return ham;
    }
    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
    	int man = 0;
    	int[][] goal = createGoalBoard();
    	int blockValue;
    	int goalValue;
    	for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++) {
				blockValue = this.blocks[i][j];
				goalValue = goal[i][j];
				if (blockValue != goalValue && blockValue != 0) {
					int rowPlace = (blockValue-1)/n;
					int colPlace = blockValue - 1 - (rowPlace * n);
					int distance = Math.abs(i-rowPlace) + Math.abs(j-colPlace);
					man += distance;
				}
			}
		}
    	
    	return man;
    }
    public boolean isGoal() {                // is this board the goal board?
    	boolean itIs = true;
    	int[][]goal = createGoalBoard();
    	if (!boardEquals(this.blocks, goal)) {
			return false;
		}
    	return itIs;
    }
    private boolean boardEquals(int[][]first, int[][]second) {
    	boolean yes = true;
    	for (int i = 0; i < second.length; i++) {
			for (int j = 0; j < second.length; j++) {
				if (first[i][j] != second[i][j]) {
					return false;
				}
			}
		}
    	
    	return yes;
    }
    private boolean swapPairsOfBlocks(int firstI, int firstJ, int secondI, int secondJ) {
    	if (firstI<0 || firstJ > n-1 || secondI < 0 || secondI > n-1 || secondJ < 0 || secondJ > n-1) {
			return false;
		}
    	int temp = this.blocks[firstI][firstJ];
    	this.blocks[firstI][firstJ] = this.blocks[secondI][secondJ];
    	this.blocks[secondI][secondJ] = temp;
    	return true;
    }
    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
    	Board board = new Board(this.blocks);
    	for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i][j + 1] != 0) {
                    board.swapPairsOfBlocks(i, j, i, j + 1);
                    return board;
                }
            }
}
    	
    	
    	return board;
    }
    public boolean equals(Object y) {        // does this board equal y?
    	boolean yes = false;
    	if (y== null || y.getClass() != Board.class) {
			return false;
		}
    	Board board = (Board)y;
    	if (board.n != this.n) {
			return false;
		}
    	if (boardEquals(this.blocks, board.blocks)) {
			return true;
		}
    	return yes;
    }
    public Iterable<Board> neighbors() {     // all neighboring boards
    	Queue<Board> queue = new Queue<Board>();
    	Board board = new Board(this.blocks);
    	boolean swaped = board.swapPairsOfBlocks(board.zeroI, board.zeroJ, board.zeroI-1, board.zeroJ);
    	if (swaped) {
    		queue.enqueue(board);
		}
    	board = new Board(blocks);
    	swaped = board.swapPairsOfBlocks(board.zeroI, board.zeroJ, board.zeroI+1, board.zeroJ);
    	if (swaped) {
    		queue.enqueue(board);
		}
    	board = new Board(blocks);
    	swaped = board.swapPairsOfBlocks(board.zeroI, board.zeroJ, board.zeroI, board.zeroJ-1);
    	if (swaped) {
    		queue.enqueue(board);
		}
    	board = new Board(blocks);
    	swaped = board.swapPairsOfBlocks(board.zeroI, board.zeroJ, board.zeroI, board.zeroJ+1);
    	if (swaped) {
    		queue.enqueue(board);
		}
    	
    	
    	return queue;
    }
    
    
    public String toString() {               // string representation of this board (in the output format specified below)
    	StringBuilder sBuilder = new StringBuilder();
    	sBuilder.append(n );
        
    	
    	//String format = "%" + ; 
    	for (int i = 0; i < n; i++) {
			sBuilder.append("\n");
    		for (int j = 0; j < n; j++) {
				sBuilder.append(String.format("%8d", this.blocks[i][j]));
			}
		}
    	sBuilder.append("\n");
    	return sBuilder.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[][] blocks = new int[3][3];
//		for (int i = 0; i < blocks.length; i++) {
//			for (int j = 0; j < blocks.length; j++) {
//				if (i==blocks.length-1 && j==blocks.length-1) {
//					blocks[i][j]=0;
//				} else {
//					blocks[i][j]=1+i*blocks.length+j;
//				}
//			}
//		}
		
		In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    
	    Board initial = new Board(blocks);
	    
//	    StdOut.println(initial.dimension());
	    StdOut.println(initial);
	    int[][] goal = initial.createGoalBoard();
	    Board goalBoard = new Board(goal);
	    StdOut.println(goalBoard);
	    StdOut.println();
	    boolean isGoalBoard = initial.isGoal();
	    StdOut.println(isGoalBoard);
	    
	    Object test = new Object();
	    Board test2 = new Board(blocks);
	    boolean equals = initial.equals(test2);
	    StdOut.println(equals);
	    
	    Board twin = initial.twin();
	    StdOut.println(twin);
	    StdOut.println("---- Neighbours------");
	    Queue<Board> queue = (Queue<Board>) initial.neighbors();
	    for (Board board : queue) {
			StdOut.println(board);
		}
	    
	    
	    
	    
	    
	}

}
