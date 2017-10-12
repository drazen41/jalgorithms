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
		Point[] points = new Point[20];
//		int j = 0;
//		for (int i = 0; i < points.length; i++) {
//			Point point = new Point(j+2, j+3);
//			j++;
//		}
		BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
		bruteCollinearPoints.segments();
		StdOut.println(bruteCollinearPoints.numberOfSegments());
		
		
		
		
		// read the n points from a file
//		String arg0 = args[0];
//	    In in = new In(args[0]);
//	    int n = in.readInt();
//	    Point[] points = new Point[n];
//	    for (int i = 0; i < n; i++) {
//	        int x = in.readInt();
//	        int y = in.readInt();
//	        points[i] = new Point(x, y);
//	    }
//
//	    // draw the points
//	    StdDraw.enableDoubleBuffering();
//	    StdDraw.setXscale(0, 32768);
//	    StdDraw.setYscale(0, 32768);
//	    for (Point p : points) {
//	        p.draw();
//	    }
//	    StdDraw.show();

	    // print and draw the line segments
//	    FastCollinearPoints collinear = new FastCollinearPoints(points);
//	    for (LineSegment segment : collinear.segments()) {
//	        StdOut.println(segment);
//	        segment.draw();
//	    }
//	    StdDraw.show();
	    
	}
}
