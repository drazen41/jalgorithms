import edu.princeton.cs.algs4.*;
public class BruteCollinearPoints {
	
	private Point[]points;
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
		this.points = points;
	}
	public int numberOfSegments() { // the number of line segments
		return segments;
	}
	public LineSegment[] segments() { // the line segments
		LineSegment[]temp = new LineSegment[this.points.length];
		Point point, point1, point2, point3;
		int j = 0;
		for (int i = 0; i < points.length-3; i++) {
			for (int k = i+1; k < points.length-2; k++) {
				for (int l = k+1; l < points.length-1; l++) {
					for (int m = l+1; m < points.length; m++) {
						point = points[i];
						point1 = points[k];
						point2 = points[l];
						point3 = points[m];
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
