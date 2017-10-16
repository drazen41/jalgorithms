import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private class MyLineSegment implements Comparable<MyLineSegment> {
		private Point firstPoint;   // one endpoint of this line segment
	    private Point lastPoint;   // the other endpoint of this line segment
	    private double slope;
	    private MyLineSegment(Point firstPoint, Point lastPoint,double slope) {
	        if (firstPoint == null || lastPoint == null) {
	            throw new NullPointerException("argument is null");
	        }
	        this.firstPoint = firstPoint;
	        this.lastPoint = lastPoint;
	        this.slope = slope;
	    }
		@Override
		public int compareTo(MyLineSegment that) {
			// TODO Auto-generated method stub
			if (this.slope == that.slope) {
				int firstResult = this.firstPoint.compareTo(that.firstPoint);
				int lastResult = this.lastPoint.compareTo(that.lastPoint);
				if (firstResult <= 0 && lastResult == 0 ) {
					return 0;
				} else if (firstResult == 0 && lastResult == -1) { // change Last
					return 2;
				} 
//				if (this.firstPoint.compareTo(that.firstPoint)==0 && this.lastPoint.compareTo(that.lastPoint)==0) {
//					return 0;
//				} else if (this.firstPoint.compareTo(that.firstPoint) <0 && this.lastPoint.compareTo(that.lastPoint)==0) { //change first point
//					return -1;
//				} else if (this.firstPoint.compareTo(that.firstPoint)==0 && this.lastPoint.compareTo(that.lastPoint)<0) { // change last point
//					return 1;
//				}
				
				
			} else {
				return -99;
			}
			return -99;
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
		MyLineSegment[] myLineSegments = new MyLineSegment[pointsLength];
		Point[] segmentPoints = new Point[4];
		MyLineSegment myLineSegment = null;
		ArrayList<MyLineSegment> myLineSegmentsList = new ArrayList<>();
		int f = 0;
		int mls = 0;
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
					myLineSegment = new MyLineSegment(first, last, origin.slopeTo(point3));
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
							myLineSegment = new MyLineSegment(first, last, origin.slopeTo(point5));
							k++;
						} else {
							ok = false;
						}
						
					}
					
					boolean newSegment = true;
					
					boolean stop = false;
					int mlsLoop = 0;
					
					while (!stop) {
						MyLineSegment mlsTemp = myLineSegments[mlsLoop];
						if (mlsTemp == null) {
							stop = true;
							continue;
						}
						if (mlsTemp.compareTo(myLineSegment) == 0) { //same segment
							stop = true;
							newSegment = false;
													
						} else if (mlsTemp.compareTo(myLineSegment) == 2) {
							myLineSegments[mlsLoop].lastPoint = myLineSegment.lastPoint;						
							stop = true;
							newSegment = false;
						} 
						
						mlsLoop++;
					}
					
					if (newSegment) {
//						lineSegment = new LineSegment(first, last);
//						temp[segments] = lineSegment;
						
						segments++;
						myLineSegments[mls]=myLineSegment;
						mls++;
					}
					
					

				}
				
				
			}
			
			
			
			copy2 = copy.clone();
		}

			
		int j = 0;
		LineSegment[] returned = new LineSegment[segments];
		for (MyLineSegment lineSegment : myLineSegments) {
			if (lineSegment != null) {
				LineSegment lineSegment2 = new LineSegment(lineSegment.firstPoint, lineSegment.lastPoint);
				returned[j] = lineSegment2;
				j++;
			}
		}
		
//		for (LineSegment lineSegment : temp) {
//			if (lineSegment != null) {
//				returned[j] = lineSegment;
//				j++;
//			}
//			
//		}
		return returned;
	}
}
