import edu.princeton.cs.algs4.*;

public class BoggleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
	    String[] dictionary = in.readAllStrings();
	    BoggleSolver solver = new BoggleSolver(dictionary);
	    BoggleBoard board = new BoggleBoard(args[1]);
	    int score = 0;
	    for (String word : solver.getAllValidWords(board)) {
	        StdOut.println(word);
	        score += solver.scoreOf(word);
	    }
	   
	    StdOut.println("Score = " + score);
	}

}
