package algorithms1;

public class Test {
	public static void main(String[] args) {
		QuickUnionUF quuf = new QuickUnionUF(10);
		quuf.union(3, 6);
		quuf.union(7, 8);
		quuf.union(3, 8);
		int first, second;
		first = 9;
		second = 7;
		if (quuf.connected(first, second)) {
			System.out.println(first + " and " + second + " are connected");
		} else {
			System.out.println(first + " and " + second + " are not connected");
		}
		quuf.connected(6, 7);
	}
}
