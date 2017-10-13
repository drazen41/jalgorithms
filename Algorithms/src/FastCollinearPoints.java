import java.util.Comparator;

import edu.princeton.cs.algs4.Merge;

public class FastCollinearPoints {
	private final Point[]points;
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
				if (points[i].compareTo(points[j])== 0) {
					throw new IllegalArgumentException();
				}
			}
		}
		this.points = (Point[])points.clone();
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
		Merge.sort(this.points);
		for (int i = 0; i < pointsLength-3; i++) {
			
			
			
		}
		int i = 0;
		while (i<pointsLength-3) {
			Point point = getPoint(i);
			Point point2 = getPoint(i+1);
			Point point3 = getPoint(i+2);
			Point point4 = getPoint(i+3);
			boolean weHaveSegment = false;
			LineSegment lineSegment = null;
			if (point.slopeTo(point2) == point.slopeTo(point3) && point.slopeTo(point3)== point.slopeTo(point4)) {
				lineSegment = new LineSegment(point, point4);
				weHaveSegment = true;
			}
			if (weHaveSegment) {
				for (int j = i+4; j < temp.length; j++) {
					Point point5 = getPoint(j);
					if (point.slopeTo(point4) == point.slopeTo(point5)) {
						lineSegment = new LineSegment(point, point5);
					}
				}
				temp[segments] = lineSegment;
				segments++;
				i = i+3;
			} else {
				i++;
			}
			
			
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
