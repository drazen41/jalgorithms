import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
	private int n;
	private int trials;	
	private int openSites[];
	private double mean = 0;
	private double stDev = 0;
	public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.n = n;
		this.trials = trials;
		openSites = new int[trials];
		int i = 0;
		Percolation percolation;
		while(i<trials) {
			percolation = new Percolation(n);
			runPercolation(percolation);
			openSites[i] = percolation.numberOfOpenSites();
			i++;
		}
		this.mean = StdStats.mean(this.openSites) / Math.pow(n, 2);
		this.stDev = StdStats.stddev(this.openSites) / Math.pow(n, 2);
	}
	public double mean()     {                     // sample mean of percolation threshold
		return mean;
	}
	public double stddev() {                       // sample standard deviation of percolation threshold
		return stDev;
	}
	public double confidenceLo() {                  // low  endpoint of 95% confidence interval
		return mean - (1.96*this.stDev)/Math.sqrt(this.trials); 
	}
	public double confidenceHi() {                  // high endpoint of 95% confidence interval
		return mean + (1.96*this.stDev)/Math.sqrt(this.trials);  
	}
	private void runPercolation(Percolation percolation) {
		int row = StdRandom.uniform(1,n+1);
		int col = StdRandom.uniform(1,n+1);
		percolation.open(row, col);
		boolean percolates = false;
		while(!percolates) {
			if (percolation.percolates()) {
				percolates = true;	
			} else {
				row = StdRandom.uniform(1,n+1);
				col = StdRandom.uniform(1,n+1);
				percolation.open(row, col);		
			}	
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 200;
		int trials = 100;
		if (args.length == 2) {
			try {
		        n = Integer.parseInt(args[0]);
		        trials = Integer.parseInt(args[1]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[0] + " must be an integer.");
		    }
		}
		int i = 0;
		PercolationStats stats = new PercolationStats(n, trials);
		System.out.println("mean = " + stats.mean());
		System.out.println("stddev = " + stats.stddev());
		System.out.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
		
	}

}
