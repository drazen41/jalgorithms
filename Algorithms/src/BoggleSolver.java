import java.util.HashSet;
import java.util.TreeSet;
import edu.princeton.cs.algs4.*;

public final class BoggleSolver {
	private TST<Integer> dictionary;
	
	private HashSet<String> validWords;
	private char startChar;
	private int charCount;
	private int startRow;
	private int startCol;
	private boolean stop;
	private boolean checked;
	private BoggleBoard boggleBoard;
	private StringBuilder word;
	private boolean[][] visited;
	private int boardRows;
	private int boardCols;
	private Queue<String> prefixes;
	private String[] aDictionary;
	private HashSet<String> prefixNotInDictionary;
	//private HashSet<String> boardStrings;
	private HashSet<String> allWords;
	// Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
    	this.allWords = new HashSet<String>();
    	
    	aDictionary = dictionary;
    	createTries();
//    	for (int i = 0; i < dictionary.length; i++) {
//			this.dictionary.add(dictionary[i]);
//		}
//    	hDict = new HashSet<String>();
//    	for (int i = 0; i < dictionary.length; i++) {
//			this.hDict.add(dictionary[i]);
//		}
    	word = new StringBuilder();
    	//boardStrings = new HashSet<String>();
    	this.prefixes = new Queue<String>();
    }
    private void createTries() {
    	this.dictionary = new TST<Integer>();
    	//this.dictionary.add("Noqueue");
    	for (String word : aDictionary) {
			
			
		}
    	for (int i = 0; i < aDictionary.length; i++) {
    		this.dictionary.put(aDictionary[i], i);
		}
    	aDictionary = null;
    }
    private void cleanTries() {
    	int maxWord = (this.boardCols * this.boardRows)*2;
//    	for (String word : dictionary) {
//			if (word.length() > maxWord) {
//				dictionary.delete(word);
//			}
//		}
    }
    private void getAllWords(int row, int col) {
    	if (row > this.boardRows-1 || col > this.boardCols-1 || row < 0 || col < 0) {
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
    	visited[row][col] = true;
    	if (word.length()> 2) {
			allWords.add(word.toString());
		}
    	dfs(row, col+1);
    	dfs(row, col-1);
    	dfs(row+1, col+1);
    	dfs(row+1, col);
    	dfs(row+1, col-1);
    	dfs(row-1, col+1);
    	dfs(row-1, col);
    	dfs(row-1, col-1);
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
    private void checkBoard() {
    	boolean checked = true;
    	char firstChar = this.boggleBoard.getLetter(0, 0); 
    	char secondChar;
    	
    	for (int i = 0; i < this.boardRows; i++) {
			for (int j = 0; j < this.boardCols; j++) {
				secondChar = this.boggleBoard.getLetter(i, j);
				if (firstChar != secondChar) {
					this.checked = false;
					return;
				}
			}
		}
    	this.checked = true;
    }
    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
    	
    	this.boggleBoard = board;
    	this.boardRows = board.rows();
    	this.boardCols = board.cols();
    	if (this.boardCols > 3) {
    		checkBoard(); 
		}
    	
    	
    	
    	
    	
    	this.prefixNotInDictionary = new HashSet<String>();
    	validWords = new HashSet<String>();
    	//createTries();
    	this.visited = new boolean[board.rows()][board.cols()];
    	//StringBuilder builder = new StringBuilder();
    	for (int i = 0; i < boggleBoard.rows(); i++) {   		
    		for (int j = 0; j < boggleBoard.cols(); j++) {
//    			builder.append(boggleBoard.getLetter(i, j));
//    			prefixes = (Queue<String>)dictionary.keysWithPrefix(builder.toString());
//    			if (prefixes.isEmpty()) {
//					continue;
//				}
    			startRow = i;
    			startCol = j;
    			startChar = board.getLetter(i, j);
    			
    			this.word.setLength(0);
    			dfs(i, j);
    			
    			//builder.setLength(0);
    			//composeString(i, j);
    			//printStrings();
			}
		}
    	this.boggleBoard = null;
    	this.visited = null;
    	
    	
    	return validWords;
    }

    private void dfs(int row, int col) {
//    	StdOut.println("Word: " + word.toString() + ", Row:" + row + ", Col:" + col);
    	if (stop) {
			return;
		}
    	if (row > this.boardRows-1 || col > this.boardCols-1 || row < 0 || col < 0) {
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
    	
//    	if (this.prefixNotInDictionary.contains(word.toString())) {
//			return;
//		}
    	
//    	if (word.length() < 3) {
//			return;
//		}
//    	if (validWords.contains(word.toString())) {
//			return;
//		}
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
    		//this.prefixNotInDictionary.add(word.toString());
    		//visited[row][col] = false;
    		return;
		}
    	
    	visited[row][col] = true;
    	
    	boolean validWord = false;
    	
    	if (word.length()> 2) {
			validWord = dictionary.contains(word.toString());
			if (validWord && !validWords.contains(word.toString())) {
				validWords.add(word.toString());
			} else {
				//word.setLength(word.length()-1);
//				stop = true;
//				visited[row][col] = false;
//				visited = new boolean[this.boardRows][this.boardCols];
				//return;
			}
		}
    	dfs(row, col+1);
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
    	String longest = this.dictionary.longestPrefixOf(word.toString());
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
    	//StdOut.println("Word: " + word.toString() + ", Row:" + row + ", Col:" + col);
    	if (this.checked) {
			stop = true;
		}
    	
    }
    private boolean startsWith(String text) {
//    	for (String string : hDict) {
//			if (string.length()>=text.length()) {
//				String dictText = string.substring(0,text.length());
//	    		if (dictText.equals(text)) {
//					return true;
//				}
//			}
//    		
//		}
//    	if (text.length()<3) {
//			return false;
//		}
    	if (text.length()<3) {
			return true;
		}
    	Queue<String> queue =(Queue<String>) dictionary.keysWithPrefix(text);
    	if (!queue.isEmpty()) {
			return true;
		}
//    	int length = text.length();
//    	if (length == 1) {
//			if (!prefixes.isEmpty()) {
//				return true;
//			}
//		} else {
//			for (String pre : prefixes) {
//				
//				if (pre.substring(0, length).equals(text)) {
//					return true;
//				}
//			}
//		}
    	
    	
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
    	if (!dictionary.contains(word)) {
			return 0;
		}
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
