import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private boolean[][] grid;
	private int openSites;
	private int n;
	
	private WeightedQuickUnionUF weightedQuickUnionUF;
	public Percolation(int n) {
		// TODO Auto-generated constructor stub
		
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException();
		} 
		grid = new boolean[n+1][n+1];
		openSites = 0;
		this.n = n;
		weightedQuickUnionUF = new WeightedQuickUnionUF(n*n+2);
	}
	private boolean checkIfIllegalArguments(int row, int col) {
		if (row <= 0 || row > n || col > n || col <= 0) {
			return true;
		}
		return false;
	}
	public    void open(int row, int col) { // open site (row, col) if it is not open already
		if (checkIfIllegalArguments(row, col)) {
			throw new java.lang.IllegalArgumentException();
		}
		if (isOpen(row, col)) {
			return;
		}
		int pozicija = (row-1)*this.n + col;
		int up = pozicija - n;
		int down = pozicija + n;
		int left = pozicija - 1;
		int right = pozicija + 1;
		if (row == 1) {
			weightedQuickUnionUF.union(0, pozicija);
			
			if (col == 1) {
				if (isOpen(row, col+1)) {
					weightedQuickUnionUF.union(right, pozicija);
					
				}
				if (isOpen(row+1, col)) {
					weightedQuickUnionUF.union(down, pozicija);
					
				}
			} else if (col == n) {
				if (isOpen(row, col-1)) {
					weightedQuickUnionUF.union(left, pozicija);
					
				}
				if (isOpen(row+1, col)) {
					weightedQuickUnionUF.union(down, pozicija);
					
				}
			} else {
				if (isOpen(row, col-1)) {
					weightedQuickUnionUF.union(left, pozicija);
					
				}
				if (isOpen(row, col+1)) {
					weightedQuickUnionUF.union(right, pozicija);
					
				}
				if (isOpen(row+1, col)) {
					weightedQuickUnionUF.union(down, pozicija);
					
				}
			}
			
			
			
		} else if (row == n) {
			weightedQuickUnionUF.union(n*n+1, pozicija);
			
			if (col == 1) {
				if (isOpen(row, col+1)) {
					weightedQuickUnionUF.union(right, pozicija);
					
				}
				if (isOpen(row-1, col)) {
					weightedQuickUnionUF.union(up, pozicija);
					
				}
			} else if (col == n) {
				if (isOpen(row, col-1)) {
					weightedQuickUnionUF.union(left, pozicija);
					
				}
				if (isOpen(row-1, col)) {
					weightedQuickUnionUF.union(up, pozicija);
					
				}
			} else {
				if (isOpen(row, col-1)) {
					weightedQuickUnionUF.union(left, pozicija);
					
				}
				if (isOpen(row, col+1)) {
					weightedQuickUnionUF.union(right, pozicija);
					
				}
				if (isOpen(row-1, col)) {
					weightedQuickUnionUF.union(up, pozicija);
					
				}
			}
		} else {
			
			if (col == 1) {
				if (isOpen(row+1, col)) {
					weightedQuickUnionUF.union(down, pozicija);
					
				}
				if (isOpen(row-1, col)) {
					weightedQuickUnionUF.union(up, pozicija);
					
				}
				if (isOpen(row, col+1)) {
					weightedQuickUnionUF.union(right, pozicija);
					
				}
			} else if (col == n) {
				if (isOpen(row-1, col)) {
					weightedQuickUnionUF.union(up, pozicija);
					
				}
				if (isOpen(row+1, col)) {
					weightedQuickUnionUF.union(down, pozicija);
					
				}
				if (isOpen(row, col-1)) {
					weightedQuickUnionUF.union(left, pozicija);
					
				}
			} else {
				
				if (isOpen(row-1, col)) {
					weightedQuickUnionUF.union(up, pozicija);
					
				}
				if (isOpen(row+1, col)) {
					weightedQuickUnionUF.union(down, pozicija);
					
				}
				if (isOpen(row, col-1)) {
					weightedQuickUnionUF.union(left, pozicija);
					
				}
				if (isOpen(row, col+1)) {
					weightedQuickUnionUF.union(right, pozicija);
					
				}
				
				
			}
		}
		openSites++;
		grid[row][col] = true;
	}
	public boolean isOpen(int row, int col) {  // is site (row, col) open?
		if (checkIfIllegalArguments(row, col)) {
			throw new java.lang.IllegalArgumentException();
		}   
		return grid[row][col];
	}
	public boolean isFull(int row, int col) {  // is site (row, col) full?
		if (checkIfIllegalArguments(row, col)) {
			throw new java.lang.IllegalArgumentException();
		}
		int pozicija = (row-1)*this.n + col;
		return weightedQuickUnionUF.connected(0, pozicija);
		
	}
	public     int numberOfOpenSites() {      // number of open sites
		   return openSites;
	}
	public boolean percolates() {              // does the system percolate?
		
		if (weightedQuickUnionUF.connected(0, n*n+1)) {
			return true;
		} else {
			return false;
		}
		   
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 3;
		Percolation percolation = new Percolation(n);
		int row = StdRandom.uniform(1,n+1);
		int col = StdRandom.uniform(1,n+1);
		percolation.open(row, col);
		boolean percolates = false;
		while(!percolates) {
			if (percolation.percolates()) {
				percolates = true;
				System.out.println("Percolation with " + percolation.numberOfOpenSites() + " open sites.");
			} else {
				row = StdRandom.uniform(1,n+1);
				col = StdRandom.uniform(1,n+1);
				percolation.open(row, col);
				
			}	
		}
//		int row = 3;
//		int col = 2;
//		percolation.open(row, col);
//		boolean isOpen = percolation.isOpen(row, col);
//		boolean percolates = percolation.percolates();
//		int numberOfOpenSites = percolation.numberOfOpenSites();
//		boolean isFull = percolation.isFull(row, col);
//		
		
		
		

		
		
		
	}

}
