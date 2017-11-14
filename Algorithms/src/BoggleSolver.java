import java.util.HashSet;
import java.util.TreeSet;

import edu.princeton.cs.algs4.*;

public class BoggleSolver {
	private final TrieSET dictionary;
	private TreeSet<String> validWords;
	private BoggleBoard boggleBoard;
	private StringBuilder word;
	private boolean[][] visited;
	private int boardRows;
	private int boardCols;
	private HashSet<String> boardStrings;
	// Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
    	this.dictionary = new TrieSET();
    	for (int i = 0; i < dictionary.length; i++) {
			this.dictionary.add(dictionary[i]);
		}
    	word = new StringBuilder();
    	boardStrings = new HashSet<String>();
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
    	this.boggleBoard = board;
    	this.boardRows = board.rows();
    	this.boardCols = board.cols();
    	validWords = new TreeSet<String>();
    	this.visited = new boolean[board.rows()][board.cols()];
    	for (int i = 0; i < boggleBoard.rows(); i++) {   		
    		for (int j = 0; j < boggleBoard.cols(); j++) {
    			
    			this.word.setLength(0);
    			dfs(i, j);
    			//composeString(i, j);
    			//printStrings();
			}
		}
//    	for (String string : boardStrings) {
//			for (String dict : dictionary) {
//				if (dict.equals(string)) {
//					validWords.add(string);
//				}
//			}
//		}
    	
    	return validWords;
    }
    private void composeString(int row, int col) {
    	if (row < 0 || col < 0 || row > boardRows-1 || col > boardCols-1) {
			return;
		}
    	if (visited[row][col]) {
			//visited[row][col] = false;
    		return;
		}
    	char letter = boggleBoard.getLetter(row, col);
    	word.append(letter);
    	if (word.length()>2 && !boardStrings.contains(word)) {
			boardStrings.add(word.toString());
//			StdOut.println(boardStrings);
		}
    	visited[row][col] = true;
    	//composeString(row, col);
    	composeString(row, col+1);
    	composeString(row, col-1);
    	composeString(row+1, col+1);
    	composeString(row+1, col-1);
    	composeString(row+1, col);
    	composeString(row-1, col+1);
    	composeString(row-1, col-1);
    	composeString(row-1, col);
    	word.setLength(word.length()-1);
    	visited[row][col] = false;
    	
    }
    private void printStrings() {
    	for (String string : boardStrings) {
    		StdOut.println(string);
		}
    }
    private void dfs(int row, int col) {
    	if (row > boggleBoard.rows()-1 || col > boggleBoard.cols()-1 || row < 0 || col < 0) {
//    		if (this.word.length()>1) {
//    			word.setLength(word.length()-1);
//			}
    		
    		return;
		}
    	if (visited[row][col]) {
		//word.setLength(word.length()-1);
		return;
	}
    	char letter = boggleBoard.getLetter(row, col);
    	if (letter == 'Q' ) {
			word.append(letter + "U");
		} else {
			word.append(letter);
		}
    	
    	
    	
    	
    	if (!startsWith(word.toString())) {
    		if (word.length()>1) {
        		if (word.charAt(word.length()-2)=='Q') {
        			word.setLength(word.length()-2);
        		} else {
        			word.setLength(word.length()-1);
        		}
    		} else {
    			word.setLength(word.length()-1);
    		}
    		
    		
    		return;
		}
    	
    	visited[row][col] = true;
    	
    	boolean validWord = false;
    	boolean stop = false;
    	if (word.length()> 2) {
			validWord = dictionary.contains(word.toString());
			if (validWord) {
				validWords.add(word.toString());
			} else {
				//word.setLength(word.length()-1);
//				stop = true;
//				visited[row][col] = false;
//				visited = new boolean[this.boardRows][this.boardCols];
			}
		}
//    	if (stop) return;
    	dfs(row, col+1);
    	
//    	dfs(row, col);
    	dfs(row, col-1);
    	dfs(row+1, col+1);
    	dfs(row+1, col);
    	dfs(row+1, col-1);
    	dfs(row-1, col+1);
    	dfs(row-1, col);
    	dfs(row-1, col-1);
//    	if (this.word.length()==1) {
//    		visited = new boolean[boggleBoard.rows()][boggleBoard.cols()];
//    		visited[row][col] = true;
//		}
    	if (word.length()>1) {
    		if (word.charAt(word.length()-2)=='Q') {
    			word.setLength(word.length()-2);
    		} else {
    			word.setLength(word.length()-1);
    		}
		} else {
			word.setLength(word.length()-1);
		}
    	
    	visited[row][col] = false;
    	
    	
    }
    private boolean startsWith(String text) {
//    	for (String string : dictionary) {
//			if (string.length()>=text.length()) {
//				String dictText = string.substring(0,text.length());
//	    		if (dictText.equals(text)) {
//					return true;
//				}
//			}
//    		
//		}
    	Queue<String> queue =(Queue<String>) dictionary.keysWithPrefix(text);
    	if (!queue.isEmpty()) {
			return true;
		}
    	return false;
    }
    private Iterable<Character> adj(int row, int col) {
    	Queue<Character> queue = new Queue<Character>();
    	if (row == 0 && col == 0) {
			queue.enqueue(boggleBoard.getLetter(row, col+1));
			queue.enqueue(boggleBoard.getLetter(row+1, col+1));
			queue.enqueue(boggleBoard.getLetter(row+1, col));
		} else if (row == 0 && col < boggleBoard.cols()-1) {
			queue.enqueue(boggleBoard.getLetter(row, col+1));
			queue.enqueue(boggleBoard.getLetter(row+1, col+1));
			queue.enqueue(boggleBoard.getLetter(row+1, col));
			queue.enqueue(boggleBoard.getLetter(row+1, col-1));
			queue.enqueue(boggleBoard.getLetter(row, col-1));
		} else if (row == 0 && col == boggleBoard.cols()-1) {
			queue.enqueue(boggleBoard.getLetter(row, col-1));
			queue.enqueue(boggleBoard.getLetter(row+1, col));
			queue.enqueue(boggleBoard.getLetter(row+1, col-1));
		} else if (row == boggleBoard.rows()-1 && col == 0) {
			queue.enqueue(boggleBoard.getLetter(row, col+1));
			queue.enqueue(boggleBoard.getLetter(row-1, col+1));
			queue.enqueue(boggleBoard.getLetter(row-1, col));
		} else if (row == boggleBoard.rows()-1 && col < boggleBoard.cols()-1) {
			queue.enqueue(boggleBoard.getLetter(row, col+1));
			queue.enqueue(boggleBoard.getLetter(row-1, col+1));
			queue.enqueue(boggleBoard.getLetter(row-1, col));
			queue.enqueue(boggleBoard.getLetter(row-1, col-1));
			queue.enqueue(boggleBoard.getLetter(row, col-1));
		} else if (row == boggleBoard.rows()-1 && col == boggleBoard.cols()-1) {
			queue.enqueue(boggleBoard.getLetter(row, col-1));
			queue.enqueue(boggleBoard.getLetter(row-1, col-1));
			queue.enqueue(boggleBoard.getLetter(row-1, col));
		} else {
			queue.enqueue(boggleBoard.getLetter(row, col+1));
			queue.enqueue(boggleBoard.getLetter(row+1, col+1));
			queue.enqueue(boggleBoard.getLetter(row+1, col));
			queue.enqueue(boggleBoard.getLetter(row+1, col-1));
			queue.enqueue(boggleBoard.getLetter(row, col-1));
			queue.enqueue(boggleBoard.getLetter(row-1, col-1));
			queue.enqueue(boggleBoard.getLetter(row-1, col));
			queue.enqueue(boggleBoard.getLetter(row-1, col+1));
		}
    	return queue;
    }
    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
    	switch (word.length()) {
    	case 3:
    	case 4:
			return 1;
    	case 5:
    		return 2;
    	case 6:
    		return 3;
    	case 7:
    		return 5;
		default:
			break;
		}
    	if (word.length()>=8) {
			return 11;
		}
    	return 0;
    }
    
}
