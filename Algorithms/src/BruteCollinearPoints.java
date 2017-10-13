import edu.princeton.cs.algs4.*;
public class BruteCollinearPoints {
	
	private final Point[] points;
	private LineSegment[] returned;
	private int segments = 0;
	
	public BruteCollinearPoints(Point[] points) {
		// TODO Auto-generated constructor stub
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
		this.points = points.clone();
		segments = 0;
	}
	public int numberOfSegments() { // the number of line segments
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
	public LineSegment[] segments() { // the line segments
		segments = 0;
		int pointsLength=getArrayLength();
		LineSegment[]temp = new LineSegment[pointsLength];
		Point point, point1, point2, point3;
		int j = 0;		
		Merge.sort(this.points);
		for (int i = 0; i < pointsLength-3; i++) {
			for (int k = i+1; k < pointsLength-2; k++) {
				for (int l = k+1; l < pointsLength-1; l++) {
					for (int m = l+1; m < pointsLength; m++) {
						point = getPoint(i);
						point1 = getPoint(k);
						point2 = getPoint(l);
						point3 = getPoint(m);
						if (point.slopeTo(point1) == point.slopeTo(point2) && point.slopeTo(point2) == point.slopeTo(point3)) {
							segments++;
							LineSegment segment = new LineSegment(point, point3);
							temp[j] = segment;
							j++;
						}
					}
				}
			}
		}
		j = 0;
		returned = new LineSegment[segments];
		for (LineSegment lineSegment : temp) {
			if (lineSegment != null) {
				returned[j] = lineSegment;
				j++;
			}
			
		}
		return returned.clone();
	}
	
}
