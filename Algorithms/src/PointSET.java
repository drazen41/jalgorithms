import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class PointSET {

	private SET<Point2D> set;
	public PointSET() { // construct an empty set of points
		// TODO Auto-generated constructor stub
		set = new SET<Point2D>();
	}
	public  boolean isEmpty() {                      // is the set empty?
		return set.isEmpty();
	}
	public int size() {                         // number of points in the set
		return set.size();
	}
	public  void insert(Point2D p) {              // add the point to the set (if it is not already in the set)
		if (p == null) {
			throw new IllegalArgumentException();
		}
		if (!set.contains(p)) {
			set.add(p);
		}
		
	}
	public boolean contains(Point2D p) {            // does the set contain point p?
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return set.contains(p);
	}
	public void draw() {                         // draw all points to standard draw
	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 1);
	    StdDraw.setYscale(0, 1);
	    for (Point2D point2d : set) {
			point2d.draw();
		}
	    StdDraw.show();
	}
	public Iterable<Point2D> range(RectHV rect) {             // all points that are inside the rectangle (or on the boundary)
		if (rect == null) {
			throw new IllegalArgumentException();
		}
		Queue<Point2D> queue = new Queue<>();
		for (Point2D point2d : set) {
			if (rect.contains(point2d)) {
				queue.enqueue(point2d);
			}
		}
		return queue;
	}
	public Point2D nearest(Point2D p) {             // a nearest neighbor in the set to point p; null if the set is empty
		if (p == null) {
			throw new IllegalArgumentException();
		}
		if (set.isEmpty()) {
			return null;
		}
		double distance = 1;
		Point2D pointToReturn = null;
		for (Point2D point2d : set) {
			if (p.distanceSquaredTo(point2d) < distance ) {
				distance = p.distanceSquaredTo(point2d);
				pointToReturn = point2d;
			}
		}
		return pointToReturn;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PointSET pointSET = new PointSET();
		StdOut.println(pointSET.isEmpty());
		StdOut.println(pointSET.size());
		Point2D point2d = new Point2D(0.1, 0.1);
		pointSET.insert(point2d);
		point2d = new Point2D(0.6, 0.6);
		pointSET.insert(point2d);
		StdOut.println(pointSET.isEmpty());
		StdOut.println(pointSET.size());
		StdOut.println(pointSET.contains(point2d));
		StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
		pointSET.draw();
		
		// draw the rectangle
		double x0 = 0.3, y0 = 0.2;      // initial endpoint of rectangle
        double x1 = 0.7, y1 = 0.4;      // current location of mouse
		RectHV rect = new RectHV(Math.min(x0, x1), Math.min(y0, y1),
                                 Math.max(x0, x1), Math.max(y0, y1));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        rect.draw();
        StdDraw.show();
		
	}

}
