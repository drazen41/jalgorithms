
public class PercolationStats {

	private int n;
	private int trials;
	public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.n = n;
		this.trials = trials;
	}
	public double mean()     {                     // sample mean of percolation threshold
		 throw new java.lang.UnsupportedOperationException();  
	}
	public double stddev() {                       // sample standard deviation of percolation threshold
		throw new java.lang.UnsupportedOperationException(); 
	}
	public double confidenceLo() {                  // low  endpoint of 95% confidence interval
		throw new java.lang.UnsupportedOperationException(); 
	}
	public double confidenceHi() {                  // high endpoint of 95% confidence interval
		throw new java.lang.UnsupportedOperationException(); 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
