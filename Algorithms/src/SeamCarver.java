import java.awt.Color;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Stack;



public class SeamCarver {

	private Picture picture ;
	private double[][] energies;
	private int[] seam;
	private int[] edgeTo;
	private int[][] pathTo;
	private double[][] distTo;
	private double minEnergy = 0;
	
	
//	private double[][] energiesVertical;
//	private double[][] energiesHorizontal;
	public SeamCarver(Picture picture) {                // create a seam carver object based on the given picture
		if (picture == null) {
			throw new IllegalArgumentException();
		}
		this.picture = new Picture(picture);

		
	}
	public Picture picture() {                          // current picture
		return this.picture;
	}
	public int width() {                            // width of current picture
		return this.picture.width();
	}
	public int height() {                           // height of current picture
		return this.picture.height();
	}
	public double energy(int x, int y) { // energy of pixel at column x and row y
		double energy = 0;
		if (x < 0 || x > this.width()-1 || y < 0 || y > this.height()-1) {
			throw new IllegalArgumentException();
		}
		if (x == 0 || x == this.width()-1 || y == 0 || y == this.height()-1) {
			return 1000;
		}
		
		
		
		Color colorBeforeX = new Color(this.picture.getRGB(x-1, y));
		
		Color colorAfterX = new Color(this.picture.getRGB(x+1, y));
		
		Color colorBeforeY = new Color(this.picture.getRGB(x, y-1));
		
		Color colorAfterY = new Color(this.picture.getRGB(x, y+1));
		
		double rx = colorAfterX.getRed() - colorBeforeX.getRed();
		double gx = colorAfterX.getGreen() - colorBeforeX.getGreen();
		double bx = colorAfterX.getBlue() - colorBeforeX.getBlue();
		double ry = colorAfterY.getRed() - colorBeforeY.getRed();
		double gy = colorAfterY.getGreen() - colorBeforeY.getGreen();
		double by = colorAfterY.getBlue() - colorBeforeY.getBlue();
		energy = Math.sqrt(rx*rx + ry *ry + gx * gx + gy * gy + bx * bx + by * by);
		colorAfterX = null;
		colorAfterY = null;
		colorBeforeX = null;
		colorBeforeY = null;
		return energy;
//		return energies[x][y];
	}
	
	public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
		energies = new double[width()][height()];
		seam = new int[width()];
		Picture original = this.picture;
		Picture transpose = new Picture(height(),width());
		this.picture = transpose;
		for (int i = 0; i < width(); i++) {
			for (int j = 0; j < height(); j++) {
				transpose.set(i, j,original.get(j, i));
				
				
			}
		}
		findVerticalSeam();
		this.picture = original;
		transpose = null;
		return seam;
		
	}
	public int[] findVerticalSeam() { // sequence of indices for vertical seam
		energies = new double[width()][height()];
		seam = new int[height()];
		distTo = new double[height()][width()];
		pathTo = new int[height()][width()];
//		edgeTo = new int[height()];
//		double[][] energyMatrix = new double[width()][height()];
//		double localEnergy = height() * 1000;
		this.minEnergy = height() * 1000;
		
		if (width()==1) {
			for (int i = 0; i < height(); i++) {
				energies[0][i] = energy(0, i);
				seam[i] = 0;
			}
			
		} else {
			for (int row = 0; row < height(); row++) {
	            for (int col = 0; col < width(); col++) {
	               energies[col][row] = energy(col, row);
	               relax(row, col);
				}
	        }
			boolean stop = false;
			int i = 0;
			while (!stop) {
				if (distTo[height()-1][i] == this.minEnergy) {
					int col = i;
					seam[height()-1] = col;
					for (int j = height()-1; j > 0; j--) {
						col = pathTo[j][col];
						seam[j-1] = col;
					}
					stop = true;
				} else {
					i++;
				}
			}
		}
		
		
		
		
//		for (int i = 0; i < width(); i++) {
//			for (int j = 0; j < height(); j++) {
//				relax(i,j);
//			}
//			
//		}
//		for (int i = 0; i < width()-1; i++) {
//			//double me = 
//			this.minEnergy = 1000;
//			relaxDown(0, i);
//			if (this.minEnergy < localEnergy ) {
//				localEnergy = this.minEnergy ;
//				seam = edgeTo.clone();
//			}
//		}
//		for (int i = 0; i < width()-1; i++) {
//			//double me = 
//			this.minEnergy = 1000;
//			relaxUp(height()-1, i);
//			if (this.minEnergy < localEnergy ) {
//				localEnergy = this.minEnergy ;
//				seam = edgeTo.clone();
//			}
//		}
//		edgeTo = null;
		return seam;
	}
	private void relax(int row, int col) {
		double currentEnergy = energies[col][row];
		if (row == 0) {
			distTo[row][col] = 1000;
			pathTo[row][col] = col;
		} else {
			if (col == 0) {
				double a = distTo[row-1][col];
				double b = distTo[row-1][col+1] ;
				if (a < b ) {
					distTo[row][col] = a + currentEnergy;
					pathTo[row][col] = col;
				} else {
					distTo[row][col] = b + currentEnergy;
					pathTo[row][col] = col+1;
				}
			} else if (col == width()-1) {
				double a = distTo[row-1][col];
				double b = distTo[row-1][col-1] ;
				if (a < b ) {
					distTo[row][col] = a + currentEnergy;
					pathTo[row][col] = col;
				} else {
					distTo[row][col] = b + currentEnergy;
					pathTo[row][col] = col-1;
				}
			} else {
				double a = distTo[row-1][col];
				double b = distTo[row-1][col+1] ;
				double c = distTo[row-1][col-1] ;
				if (a <= b && a <= c ) {
					distTo[row][col] = a + currentEnergy;
					pathTo[row][col] = col;
				} else if (b < a && b < c) {
					distTo[row][col] = b + currentEnergy;
					pathTo[row][col] = col+1;
				} else {
					distTo[row][col] = c + currentEnergy;
					pathTo[row][col] = col-1;
				}
			}
			
		}
		if (row == height()-1) {
			if (distTo[row][col]<this.minEnergy) {
				this.minEnergy = distTo[row][col];
			}
		}
	}
	private void relaxStack (int row, int col) {
//		if (col < 0 || col > width()-1) {
//			return;
//		}
//		if (row == height()) {
//			double t = stack.peek();
//			if (t < this.minEnergy ) {
//				this.minEnergy = t;
//			}
//			return;
//		}
//		double thisEnergy = energy(col, row);
//		 
//		if (row == 0) {
//			stack.push(thisEnergy);
//		} else {
//			double sumBefore =stack.peek();
//			stack.push(sumBefore + thisEnergy);
//		}
		
		relax(row+1, col-1);
//		if (col-1 >= 0) {
//			stack.pop();
//		}		
		relax(row+1, col);
		
		relax(row+1, col+1);
//		stack.pop();
//		if (col +1 < height()) {
//			stack.pop();
//		}
	}
	private void relaxUp(int row, int col) {
		edgeTo[row] = col;
		
		if (col == 0 && row > 0) {
			double a1 = energies[col][row-1];
			double a2 = energies[col+1][row-1];
			if (a1 < a2) {
				minEnergy += a1;
				relaxUp(row-1, col);
				
			} else {
				minEnergy += a2;
				relaxUp(row-1, col+1);
				
			}
		} else if (col == width()-1 && row > 0) {
			double a1 = energies[col][row-1];
			double a2 = energies[col-1][row-1];
			if (a1 < a2) {
				minEnergy += a1;
				relaxUp(row-1, col);
				
			} else {
				minEnergy += a2;
				relaxUp(row-1, col-1);
				
			}
		} else if (col < width()-1 && row > 0) {
			double a1 = energies[col][row-1];
			double a2 = energies[col-1][row-1];
			double a3 = energies[col+1][row-1];
			if (a1 < a2 && a1 < a3) {
				minEnergy += a1;
				relaxUp(row-1, col);
				
			} else if (a2 <= a3 && a2 <= a1) {
				minEnergy += a2;
				relaxUp(row-1, col-1);
				
			} else {
				minEnergy += a3;
				relaxUp(row-1, col+1);
			}
		}
	}
	private void relaxDown(int row, int col) {
		
		edgeTo[row] = col;
		
		if (col == 0 && row < height()-1) {
			double a1 = energies[col][row+1];
			double a2 = energies[col+1][row+1];
			if (a1 < a2) {
				minEnergy += a1;
				relaxDown(row+1, col);
				
			} else {
				minEnergy += a2;
				relaxDown(row+1, col+1);
				
			}
		} else if (col == width()-1 && row < height()-1) {
			double a1 = energies[col][row+1];
			double a2 = energies[col-1][row+1];
			if (a1 < a2) {
				minEnergy += a1;
				relaxDown(row+1, col);
				
			} else {
				minEnergy += a2;
				relaxDown(row+1, col-1);
				
			}
		} else if (col < width()-1 && row < height()-1) {
			double a1 = energies[col][row+1];
			double a2 = energies[col-1][row+1];
			double a3 = energies[col+1][row+1];
			if (a1 < a2 && a1 < a3) {
				minEnergy += a1;
				relaxDown(row+1, col);
				
			} else if (a2 <= a3 && a2 <= a1) {
				minEnergy += a2;
				relaxDown(row+1, col-1);
				
			} else {
				minEnergy += a3;
				relaxDown(row+1, col+1);
			}
		}
		//return edgeTo;
		
	}
	
	public void removeHorizontalSeam(int[] seam) {   // remove horizontal seam from current picture
		if (seam == null) {
			throw new IllegalArgumentException();
		}
		if (this.picture.height() <= 1) {
			throw new IllegalArgumentException();
		}
		if (findHorizontalSeam().length != seam.length || !validateSeam(seam) ) {
			throw new IllegalArgumentException();
		}
		Picture picture = new Picture(width(), height()-1);
		for (int col = 0; col < seam.length; col++) {
			for (int row = 0; row < height(); row++) {
				if (seam[col] == row) {
					continue;
				} else {
					if (row > seam[col]) {
						picture.setRGB(col, row-1, this.picture.getRGB(col, row));
					} else {
						picture.setRGB(col, row, this.picture.getRGB(col, row));
					}
					
				}
				
			}
		}
		this.picture = picture;
		
	}
	public void removeVerticalSeam(int[] seam) { // remove vertical seam from current picture
		if (seam == null) {
			throw new IllegalArgumentException();
		}
		if (this.picture.width() <= 1) {
			throw new IllegalArgumentException();
		}
		if (findVerticalSeam().length != seam.length || !validateSeam(seam)) {
			throw new IllegalArgumentException();
		}
		Picture picture = new Picture(width()-1,height());
		for (int i = 0; i < seam.length; i++) {
			for (int j = 0; j < width(); j++) {
				if (seam[i] == j) {
					continue;
				} else {
					if (j > seam[i]) {
						picture.setRGB(j-1, i, this.picture.getRGB(j, i));
					} else {
						picture.setRGB(j, i, this.picture.getRGB(j, i));
					}
				}
			}
		}
		this.picture = picture;
	}
	private boolean validateSeam(int[] seam) {
		boolean ok = true;
		for (int i = 0; i < seam.length; i++) {
			if (i>0 && i < seam.length -1) {
				if (Math.abs(seam[i]-seam[i-1])>1 || Math.abs(seam[i+1]-seam[i])>1) {
					return false;
				}
			}
		}
		
		return ok;
	}

}
