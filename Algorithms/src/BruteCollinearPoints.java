import edu.princeton.cs.algs4.*;
public class BruteCollinearPoints {
	
	private Point[]points;
	private int segments = 0;
	public BruteCollinearPoints(Point[] points) {
		// TODO Auto-generated constructor stub
		this.points = points;
	}
	public int numberOfSegments() { // the number of line segments
		return segments;
	}
	public LineSegment[] segments() { // the line segments
		LineSegment[]temp = new LineSegment[this.points.length/2];
		int j = 0;
		for (int i = 0; i < this.points.length; i++) {
			double p, q, r, s;
			Point point = points[i];
			Point point1 = points[i+1];
			Point point2 = points[i+2];
			Point point3 = points[i+3];
			if (point.slopeTo(point1) == point.slopeTo(point2) && point.slopeTo(point2) == point.slopeTo(point3)) {
				segments++;
				LineSegment segment = new LineSegment(point, point3);
				temp[j] = segment;
				j++;
			}
			
		}
		j = 0;
		LineSegment[] returned = new LineSegment[segments];
		for (LineSegment lineSegment : temp) {
			returned[j] = lineSegment;
		}
		return returned;
	}
	
}
