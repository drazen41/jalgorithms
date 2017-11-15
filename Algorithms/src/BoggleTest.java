import edu.princeton.cs.algs4.*;

public class BoggleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stopwatch stopwatch = new Stopwatch();
		String[] boards = {"board-points4410.txt","board-points4527.txt","board-points13464.txt","board-points26539.txt","board4x4.txt","board-q.txt", "board-16q.txt"};
		String[] dicts = {"dictionary-zingarelli2005.txt","dictionary-yawl.txt","dictionary-algs4.txt","dictionary-16q.txt"};
		//In in = new In(args[0]);
		In in = new In(dicts[1]);
	    String[] dictionary = in.readAllStrings();
	    BoggleSolver solver = new BoggleSolver(dictionary);
	    StdOut.println("Elapsed time: " + stopwatch.elapsedTime());
	    
	   
	    BoggleBoard board = new BoggleBoard(boards[6]);
	    int score = 0;
	    
	    board = null;
	    for (int i = 0; i < 4; i++) {
	    	board = new BoggleBoard(boards[i]);
	    	//board = new BoggleBoard();
	    	//stopwatch = new Stopwatch();  
			for (String word : solver.getAllValidWords(board)) {
		       // StdOut.println(word);
		        score += solver.scoreOf(word);
		      
			}
			
			StdOut.println("Elapsed time: " + stopwatch.elapsedTime());
			  StdOut.println("Score = " + score);
			  score = 0;
		}
	    
//	    
	    
//		for (String word : solver.getAllValidWords(board)) {
//	        StdOut.println(word);
//	        score += solver.scoreOf(word);
//		}
//	    StdOut.println("Elapsed time: " + stopwatch.elapsedTime());
//	    StdOut.println("Score = " + score);
	}

}
