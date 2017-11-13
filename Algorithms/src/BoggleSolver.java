import java.util.HashSet;

import edu.princeton.cs.algs4.*;

public class BoggleSolver {
	private final HashSet<String> dictionary;
	private HashSet<String> validWords;
	private BoggleBoard boggleBoard;
	private StringBuilder word;
	private boolean[][] visited;
	private int boardRows;
	private int boardCols;
	// Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
    	this.dictionary = new HashSet<String>();
    	for (int i = 0; i < dictionary.length; i++) {
			this.dictionary.add(dictionary[i]);
		}
    	word = new StringBuilder();
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
    	this.boggleBoard = board;
    	this.boardRows = board.rows();
    	this.boardCols = board.cols();
    	this.visited = new boolean[board.rows()][board.cols()];
    	dfs(0, 0);
    	return validWords;
    }
    private void dfs(int row, int col) {
    	if (row > boggleBoard.rows()-1 || col > boggleBoard.cols()-1) {
//    		word.setLength(word.length()-1);
    		return;
		}
    	if (visited[row][col]) {
    		word.setLength(word.length()-1);
    		return;
		}
    	char letter = boggleBoard.getLetter(row, col);
    	word.append(letter);
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
//    	dfs(row, col+1);
//    	
//    	dfs(row, col);
//    	dfs(row, col-1);
//    	dfs(row+1, col+1);
//    	dfs(row+1, col);
//    	dfs(row+1, col-1);
//    	dfs(row-1, col+1);
//    	dfs(row-1, col);
//    	dfs(row-1, col-1);
    	Iterable<Character> adj = adj(row, col);
    	for (Character character : adj) {
			
		}
    	
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
    	return 0;
    }
    
}
