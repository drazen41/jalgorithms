
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
				if (points[i].compareTo(points[j])== 0) {
					throw new IllegalArgumentException();
				}
			}
		}
		this.points = points;
	}
	public int numberOfSegments() {        // the number of line segments
		return segments;
	}
	public LineSegment[] segments() {                // the line segments
		LineSegment[]temp = new LineSegment[this.points.length];
		
		
		
		
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
