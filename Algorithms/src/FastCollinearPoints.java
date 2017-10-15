import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
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
		
		for (int i = 0; i < pointsLength; i++) {
			Point origin = copy2[i];
			int numberOfPoints = 0;
			Arrays.sort(copy2, copy2[i].slopeOrder());
//			Merge.sort(copy2);
			
			for (int j = 1; j < copy2.length-2; j++) {				
				Point point1 = copy2[j];
				Point point2 = copy2[j+1];
				Point point3 = copy2[j+2];
				
				LineSegment lineSegment = null;
				boolean weHaveSegment = false;
				if (origin.slopeTo(point1)==origin.slopeTo(point2) && origin.slopeTo(point2)==origin.slopeTo(point3)) {
					lineSegment = new LineSegment(origin, point3);
					weHaveSegment = true;
					numberOfPoints = 4;
				}
				if (weHaveSegment) {
					Point point5=null;
					for (int k = j+3; k < copy2.length; k++) {
						point5 = copy2[k];
						if (origin.slopeTo(point3)==origin.slopeTo(point5)) {
							lineSegment = new LineSegment(origin, point5);
							numberOfPoints++;
						}
					}
					temp[segments] = lineSegment;
					segments++;
//					StdOut.println("Segment " + segments + ": " +  origin.toString() + "->" + point1.toString() + "->" + point2.toString() +"->" + point3.toString());
//					if (point5 != null) {
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
