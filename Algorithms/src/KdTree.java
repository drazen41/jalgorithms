import java.io.ObjectOutputStream.PutField;
import java.util.Comparator;

import org.w3c.dom.NodeList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {
	
	private static class Node implements Comparable<Point2D> {
		private Point2D point2d; // the point
		private RectHV rectHV;   // the axis-aligned rectangle corresponding to this node
		private Node leftBottom; // the left/bottom subtree
		private Node rightTop;   // the right/top subtree
		@Override
		public int compareTo(Point2D that) {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	private Node root = null;
	public KdTree() {
		// TODO Auto-generated constructor stub
		root = new Node();
		
	}
	public  boolean isEmpty() {                   
		return root.point2d == null;
	}
	public int size() {                         
		if (root.point2d == null) {
			return 0;
		}
		return (size(root));
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
		Node node = null;
//		if (size == 0) {			
//			root.point2d = p;
//			node = root;
//			RectHV rectHV = new RectHV(0, 0, p.x(), 1);
//			node.rectHV = rectHV;
//		} else {
//			node = put(p);
//		}
//		node = put(p);
//		if (root.point2d == null) {
//			root = node;
//		}
		
		
//		RectHV rectHV = new RectHV(xmin, ymin, xmax, ymax)
//		int size = size();
		if (size == 0) {
			
			root.point2d = p;
			RectHV rectHV = new RectHV(0, 0, p.x(), 1);
			root.rectHV = rectHV;
			
		} else {
			node = root;
			Node newNode = new Node();
			newNode.point2d = p;
			boolean stop = false;
			int i = 1;
			double xmin, xmax, ymin,ymax;
			RectHV rectHV = null;
			while(!stop) {
				if (node.leftBottom != null && node.rightTop != null) {
					i++;
					continue;
				}
				if (i % 2 == 1) { //compare by x, horizontal
					if (newNode.point2d.x() < node.point2d.x()) {
						if (node.leftBottom != null) {
							node = node.leftBottom;
							i++;
							continue;
						}
						node.leftBottom = newNode;
						xmin = node.rectHV.xmin();
						ymin = node.rectHV.ymin();
						xmax = node.point2d.x();
						ymax = newNode.point2d.y();
						rectHV = new RectHV(xmin, ymin, xmax, ymax);
						node.leftBottom.rectHV = rectHV;
						stop = true;
						continue;
					} else {
						if (node.rightTop != null) {
							node = node.rightTop;
							i++;
							continue;
						}
						node.rightTop = newNode;
						xmin = node.point2d.x();
						xmax = 1;
						ymin = node.rectHV.ymin();
						ymax = newNode.point2d.y();
						stop = true;
						continue;
					}
				} else { // compare by y, vertical
					if (newNode.point2d.y() < node.point2d.y()) {
						if (node.leftBottom != null) {
							node = node.leftBottom;
							i++;
							continue;
						}						
						xmin = node.rectHV.xmin();
						xmax = newNode.point2d.x();
						ymin = node.rectHV.ymin();
						ymax = node.rectHV.ymax();
						rectHV = new RectHV(xmin, ymin, xmax, ymax);
						newNode.rectHV = rectHV;
						node.leftBottom = newNode;
						stop = true;
						continue;
					} else {
						if (node.rightTop != null) {
							node = node.rightTop;
							i++;
							continue;
						}
						xmin = newNode.point2d.x();
						xmax = node.rectHV.xmax();
						ymin = node.rectHV.ymax();
						ymax = 1;
						rectHV = new RectHV(xmin, ymin, xmax, ymax);
						newNode.rectHV = rectHV;
						node.rightTop = newNode;
						stop = true;
						continue;
					}
				}
				
			}

		}
		
		
		
	}
	
	public boolean contains(Point2D p) {           
		if (p == null) {
			throw new IllegalArgumentException();
		}
		return get(root,p,0) != null;
	}
	private Point2D get(Node node, Point2D key, int i) {
        if (key == null) throw new IllegalArgumentException("called get() with a null key");
        if (node == null) return null;
        if (key.compareTo(node.point2d)==0) {
			return node.point2d;
		}
        int cmp = -99;
        Comparator<Point2D> comparator = null;
        if (i % 2 == 0) {
        	comparator = Point2D.X_ORDER;      	
        	
		} else {
			comparator = Point2D.Y_ORDER;
		}
        cmp = comparator.compare(key, node.point2d);      
        if      (cmp < 0) return get(node.leftBottom, key,++i);
        else if (cmp > 0) return get(node.rightTop, key,++i);
        else return null;
    }
	private Node put(Point2D point2d) {
		
		Node node = put(root, point2d, 0);
		return node;
	}
	private Node put(Node node, Point2D point2d, int i) {
        if (node == null || node.point2d == null ) {
        	Node newNode = new Node();
        	newNode.point2d = point2d;
        	return newNode;
        }
        int cmp = -99;
        double xmin=0, xmax=0, ymin=0,ymax=0;
        Comparator<Point2D> comparator = null;
       
        if (i % 2 == 0) {
			comparator = Point2D.X_ORDER;
			
		} else {
			comparator = Point2D.Y_ORDER;
			
		}
        cmp = comparator.compare(point2d,node.point2d);
        if      (cmp < 0) { 
        	Node nextNode = node.leftBottom;
        	nextNode = put(nextNode, point2d, ++i);
        	node.leftBottom = nextNode;
//        	xmin = node.rectHV.xmin();
//        	node.leftBottom  = put(node.leftBottom,point2d,  ++i);      	       	
//        	if (i%2==1) { // horizontal
//        		xmin = node.rectHV.xmin();
//            	xmax = node.rectHV.xmax();
//            	ymin = node.rectHV.ymin();
//            	ymax = point2d.y();
//			} else { // vertical
//				xmin = node.rectHV.xmin();
//				xmax = point2d.x();
//				ymin = node.rectHV.ymin();
//				ymax = node.rectHV.ymax();
//			}
//        	
//        	RectHV rectHV = new RectHV(xmin, ymin, xmax, ymax);
//        	node.leftBottom.rectHV = rectHV;
        	return node;
        }
        else if (cmp > 0) { 
        	node.rightTop = put(node.rightTop,point2d,++i);
        }
        else  node.point2d = point2d;
//        node.size = 1 + size(node.leftBottom) + size(node.rightTop);
        return node;
    }
	public void draw() {                        
		StdDraw.clear();
		StdDraw.setPenRadius(0.01);
		boolean stop = false;
		Node current = root.leftBottom;
		int i = 0;
		drawRect(root, i, true);
		boolean left = true;		
		while(!stop) {
			current = drawRect(current, ++i, left);
			
			stop = true;
		}
	}
	private Node drawRect(Node currentNode, int i, boolean left) {
		if (i % 2 == 0) {
			StdDraw.setPenColor(StdDraw.RED);
			
			
		} else {
			StdDraw.setPenColor(StdDraw.BLUE);
			
		}
		RectHV rectHV = currentNode.rectHV;
		rectHV.draw();
		StdDraw.show();
		if (left) {
			return currentNode.leftBottom;
		} else {
			return currentNode.rightTop;
		}
		
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
		
		StdOut.println(tree.contains(new Point2D(0.9, 0.6)));
		
//		StdDraw.clear();
//        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(0.01);
//        RectHV rectHV = new RectHV(0, 0, 0.7, 1);
//        rectHV.draw();
//        StdDraw.show();
		tree.draw();
	}

}
