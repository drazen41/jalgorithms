import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private class MyLineSegment implements Comparable<Point> {
		private final Point p;   // one endpoint of this line segment
	    private final Point q;   // the other endpoint of this line segment
	    private double slope;
	    private MyLineSegment(Point p, Point q) {
	        if (p == null || q == null) {
	            throw new NullPointerException("argument is null");
	        }
	        this.p = p;
	        this.q = q;
	    }
		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	private Point[]points;
	private int segments = 0;
	public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
		if (points == null) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < points.length; i++) {
			if (points[i] == null) {
				throw new IllegalArgumentException();
			}
			for (int j = i+1; j < points.length; j++) {
				if (points[j]==null || points[i].compareTo(points[j])== 0) {
					throw new IllegalArgumentException();
				}
			}
		}
		this.points = points.clone();
		segments = 0;
	}
	public int numberOfSegments() {        // the number of line segments
		return segments;
	}
	private int getArrayLength() {
		return points.length;
	}
	private Point getPoint(int index) {
		return points[index];
	}
	private Point[] getArray() {
		return points.clone();
	}
	public LineSegment[] segments() {                // the line segments
		int pointsLength = getArrayLength();
		segments = 0;
		LineSegment[]temp = new LineSegment[pointsLength];
		Point[] copy = new Point[pointsLength];
		copy = this.points.clone();
		Point[] copy2 = copy.clone();
		Merge.sort(copy2);
		
		Point[] segmentPoints = new Point[4];
		Point[] firsts = new Point[pointsLength/4];
		
		for (int i = 0; i < pointsLength; i++) {
			Point origin = copy2[i];
			
			Arrays.sort(copy2, copy2[i].slopeOrder());
//			Merge.sort(copy2);
			
			for (int j = 1; j < copy2.length-2; j++) {				
				Point point1 = copy2[j];
				Point point2 = copy2[j+1];
				Point point3 = copy2[j+2];
				Point first = null,last = null;
				
				LineSegment lineSegment = null;
				boolean weHaveSegment = false;
				if (origin.slopeTo(point1)==origin.slopeTo(point2) && origin.slopeTo(point2)==origin.slopeTo(point3)) {
//					lineSegment = new LineSegment(origin, point3);
					weHaveSegment = true;
					segmentPoints[0] = origin;
					segmentPoints[1] = point1;
					segmentPoints[2] = point2;
					segmentPoints[3] = point3;
					Merge.sort(segmentPoints);
					first = segmentPoints[0];
					last = segmentPoints[3];
				}
				if (weHaveSegment) {
					Point point5=null;
					boolean ok = true;
					int k = j+3;
					while (ok && k < copy2.length) {
						point5 = copy2[k];
						if (origin.slopeTo(point3)==origin.slopeTo(point5)) {
//							lineSegment = new LineSegment(origin, point5);
							ok = true; 
							if (point5.compareTo(first) == -1) {
								first = point5;
							}
							if (point5.compareTo(last) == 1) {
								last = point5;
							}
							k++;
						} else {
							ok = false;
						}
						
					}
					int f = 0;
					boolean newSegment = true;
					for (Point point : firsts) {
						if (point != null) {
							
							if (point.compareTo(first)==0) {
								newSegment = false;
							}
							
						}
						
					}
					if (newSegment) {
						lineSegment = new LineSegment(first, last);
						temp[segments] = lineSegment;
						segments++;
						firsts[f] = segmentPoints[0];
						f++;
					}
					
					
//					StdOut.println("Segment " + segments + ": " +  origin.toString() + "->" + point1.toString() + "->" + point2.toString() +"->" + point3.toString());
//					if (numberOfSegments()>4 && point5 != null) {
//						StdOut.print("->" + point5.toString());
//						StdOut.println();
//					}
				}
				
				
			}
			
			
			
			copy2 = copy.clone();
		}

			
		int j = 0;
		LineSegment[] returned = new LineSegment[segments];
		for (LineSegment lineSegment : temp) {
			if (lineSegment != null) {
				returned[j] = lineSegment;
				j++;
			}
			
		}
		return returned;
	}
}
