import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.Merge;

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
		Merge.sort(copy);
		
		for (int i = 0; i < pointsLength; i++) {
			Arrays.sort(this.points, this.points[i].slopeOrder());
			for (int j = 0; j < points.length-4; j++) {
				Point origin = points[j];
				Point point1 = points[j+1];
				Point point2 = points[j+2];
				Point point3 = points[j+3];
				
				LineSegment lineSegment = null;
				boolean weHaveSegment = false;
				if (origin.slopeTo(point1)==origin.slopeTo(point2) && origin.slopeTo(point2)==origin.slopeTo(point3)) {
					lineSegment = new LineSegment(origin, point3);
					weHaveSegment = true;
				}
				if (weHaveSegment) {
					for (int k = 4; k < points.length; k++) {
						Point point5 = points[k];
						if (origin.slopeTo(point3)==origin.slopeTo(point5)) {
							lineSegment = new LineSegment(origin, point5);
						}
					}
					temp[segments] = lineSegment;
					segments++;
				}
				
				
			}
			
			
			
			this.points = copy;
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
