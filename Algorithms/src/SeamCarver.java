import java.awt.Color;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {

	private Picture picture ;
	private double[][] energies;
	private double[] seam;
//	private double[][] energiesVertical;
//	private double[][] energiesHorizontal;
	public SeamCarver(Picture picture) {                // create a seam carver object based on the given picture
		if (picture == null) {
			throw new NullPointerException();
		}
		this.picture = new Picture(picture);
//		energies = new double[this.picture.width()][this.picture.height()];
//		for (int x = 0; x < this.picture.width(); x++) {
//			for (int y = 0; y < this.picture.height(); y++) {
//				if (x == 0 || x == this.width()-1 || y == 0 || y == this.height()-1) {
//					energies[x][y] = 1000;
//				} else {
//					Color colorBeforeX = new Color(this.picture.getRGB(x-1, y));
//					
//					Color colorAfterX = new Color(this.picture.getRGB(x+1, y));
//					
//					Color colorBeforeY = new Color(this.picture.getRGB(x, y-1));
//					
//					Color colorAfterY = new Color(this.picture.getRGB(x, y+1));
//					
//					double rx = colorAfterX.getRed() - colorBeforeX.getRed();
//					double gx = colorAfterX.getGreen() - colorBeforeX.getGreen();
//					double bx = colorAfterX.getBlue() - colorBeforeX.getBlue();
//					double ry = colorAfterY.getRed() - colorBeforeY.getRed();
//					double gy = colorAfterY.getGreen() - colorBeforeY.getGreen();
//					double by = colorAfterY.getBlue() - colorBeforeY.getBlue();
//					energiesVertical[x][y] = Math.sqrt(rx*rx + ry *ry + gx * gx + gy * gy + bx * by);
//				}
//			}
//		}
		
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
		if (x < 0 && x > this.width()-1 && y < 0 && y > this.height()-1) {
			throw new IndexOutOfBoundsException();
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
		return energy;
//		return energies[x][y];
	}
	
	public int[] findHorizontalSeam() { // sequence of indices for horizontal seam
		
		return null;
	}
	public int[] findVerticalSeam() { // sequence of indices for vertical seam
		energies = new double[width()][height()];
		seam = new double[height()];
		double minBefore = 0, min = 0;
		for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++)
                energies[col][row] = energy(col, row);
            
        }
		for (int i = 0; i < height(); i++) {
			min = energies[i][0];
			
			for (int j = 0; j < width()-1; j++) {
				if (energies[j][i] < min) {
					min = energies[j][i];
					
				}
			}
			seam[i] = min;
		}
		return null;
	}
	public void removeHorizontalSeam(int[] seam) {   // remove horizontal seam from current picture
		if (seam == null) {
			throw new NullPointerException();
		}
		if (this.picture.height() <= 1) {
			throw new IllegalArgumentException();
		}
		if (findHorizontalSeam().length != seam.length || !validateSeam(seam) ) {
			throw new IllegalArgumentException();
		}
	}
	public void removeVerticalSeam(int[] seam) { // remove vertical seam from current picture
		if (seam == null) {
			throw new NullPointerException();
		}
		if (this.picture.width() <= 1) {
			throw new IllegalArgumentException();
		}
	}
	private boolean validateSeam(int[] seam) {
		boolean ok = true;
		for (int i = 0; i < seam.length; i++) {
			if (i>0 && i < seam.length -1) {
				if (seam[i]-seam[i-1]>1 || seam[i+1]-seam[i]>1) {
					return false;
				}
			}
		}
		
		return ok;
	}

}
