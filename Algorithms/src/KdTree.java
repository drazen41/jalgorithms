import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
	
	private static class Node {
		private Point2D point2d; // the point
		private RectHV rectHV;   // the axis-aligned rectangle corresponding to this node
		private Node leftBottom; // the left/bottom subtree
		private Node rightTop;   // the right/top subtree
	}
	private Node tree;
	public KdTree() {
		// TODO Auto-generated constructor stub
		tree = new Node();
		
	}
	public  boolean isEmpty() {                   
		return tree.point2d == null;
	}
	public int size() {                         
		if (tree.point2d == null) {
			return 0;
		}
		return (size(tree));
	}
	private int size(Node node) {
		if (node == null) 
			return 0;
		else {
			return (size(node.leftBottom ) + 1 + size(node.rightTop));
		}
	}
	public  void insert(Point2D p) {              
		if (p == null) {
			throw new IllegalArgumentException();
		}
		int size = size();
		if (size == 0) {
			tree.point2d = p;
			
		} else {
			Node node = tree;
			Node newNode = new Node();
			newNode.point2d = p;
			boolean stop = false;
			int i = 1;
			
			while(!stop) {
				if (node.leftBottom != null && node.rightTop != null) {
					i++;
					continue;
				}
				if (i % 2 == 1) { //compare by x
					if (newNode.point2d.x() < node.point2d.x()) {
						if (node.leftBottom != null) {
							node = node.leftBottom;
							i++;
							continue;
						}
						node.leftBottom = newNode;
						stop = true;
						continue;
					} else {
						if (node.rightTop != null) {
							node = node.rightTop;
							i++;
							continue;
						}
						node.rightTop = newNode;
						stop = true;
						continue;
					}
				} else { // compare by y
					if (newNode.point2d.y() < node.point2d.y()) {
						if (node.leftBottom != null) {
							node = node.leftBottom;
							i++;
							continue;
						}
						node.leftBottom = newNode;
						stop = true;
						continue;
					} else {
						if (node.rightTop != null) {
							node = node.rightTop;
							i++;
							continue;
						}
						node.rightTop = newNode;
						stop = true;
						continue;
					}
				}
				
			}

		}
		
		
		
	}
	private void insertNode(Node newNode, Node root) {
		
	}
	public boolean contains(Point2D p) {           
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return false;
	}
	public void draw() {                        
		
	}
	public Iterable<Point2D> range(RectHV rect) {             
		if (rect == null) {
			throw new IllegalArgumentException();
		}
		return null;
	}
	public Point2D nearest(Point2D p) {            
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KdTree tree = new KdTree();
		StdOut.println(tree.isEmpty());
		StdOut.println(tree.size());
		tree.insert(new Point2D(0.7, 0.2));
		StdOut.println(tree.size());
		tree.insert(new Point2D(0.5, 0.4));
		StdOut.println(tree.size());
		tree.insert(new Point2D(0.2, 0.3));
		StdOut.println(tree.size());
		tree.insert(new Point2D(0.4, 0.7));
		StdOut.println(tree.size());
		tree.insert(new Point2D(0.9, 0.6));
		StdOut.println(tree.size());
		
//		StdDraw.clear();
//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.setPenRadius(0.02);
        
		
	}

}
