import java.lang.reflect.Array;

import edu.princeton.cs.algs4.*;
public class Client {
	public static void main(String[] args) {
//		Integer[] a = new Integer[20];
//		for (int i = 0; i < 20; i++) {
//			a[i] = StdRandom.uniform(20);
//		}
//		Merge.sort(a);
//		for (int i = 0; i < a.length; i++) {
//			StdOut.println(a[i]);
//		}
//		Point[] points = new Point[4];
//		Point point = new Point(1, 1);
//		points[0] = point;
//		Point point2 = new Point(2, 2);
//		points[1] = point2;
//		Point point3 = new Point(3, 3);
//		points[2] = point3;
//		Point point4 = new Point(4, 4);
//		points[3] = point4;
//		int j = 0;
//		for (int i = 0; i < points.length; i++) {
//			Point point = new Point(StdRandom.uniform(200), StdRandom.uniform(300));
//			points[j] = point;
//			j++;
//		}
		
		
				
		// read the n points from a file
		String arg0 = args[0];
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }
//	    for (Point point : points) {
//			StdOut.println(point.toString());
//		}
//		BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
//		LineSegment[] segments = bruteCollinearPoints.segments();
//		StdOut.println(bruteCollinearPoints.numberOfSegments());
//		if (segments.length> 0) {
//			for (LineSegment lineSegment : segments) {
//				StdOut.println(lineSegment.toString());
//			}
//		}
//		
//	    FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
//	    segments = fastCollinearPoints.segments();
//	    StdOut.println(fastCollinearPoints.numberOfSegments());
//	    if (segments.length > 0) {
//	    	for (LineSegment lineSegment : segments) {
//				StdOut.println(lineSegment.toString());
//			}
//		}
//		LineSegment[] lineSegments = bruteCollinearPoints.segments();
//		StdOut.println(bruteCollinearPoints.numberOfSegments());
//	    points[3] = new Point(23, 23);
//	    StdOut.println(bruteCollinearPoints.numberOfSegments());
//	    StdOut.println(bruteCollinearPoints.numberOfSegments());
//	    LineSegment[] lineSegments1 = bruteCollinearPoints.segments();
//	    StdOut.println(lineSegments.equals(lineSegments1));
//	    lineSegments1 = new LineSegment[20];
//	    
//	    Point point7 = new Point(11036, 14218 );
//	    Point[] pointsArg = {point7,null};
//	    
//	    bruteCollinearPoints = new BruteCollinearPoints(pointsArg);
	    
//	    Point[] points1 = {new Point(1,1),new Point(2,2), new Point(3,3), new Point(4,4)}; 
//	    Point[] points2 = {new Point(1,1),new Point(2,2), new Point(3,3), new Point(4,4)}; 		
//	    StdOut.println(points1.equals(points1));		
	    // draw the points
//	    StdDraw.enableDoubleBuffering();
//	    StdDraw.setXscale(0, 32768);
//	    StdDraw.setYscale(0, 32768);
//	    for (Point p : points) {
//	        p.draw();
//	    }
//	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
//	        segment.draw();
	    }
//	    StdDraw.show();
	    StdOut.println(collinear.segments().length);
	}
}
