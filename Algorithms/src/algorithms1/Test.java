package algorithms1;

public class Test {
	public static void main(String[] args) {
		int first, second;
		first = 6;
		second = 1;
		//QuickUnionTest(first,second);
		WeightedUnionTest(first, second);
	}
	private static void Print(int first, int second, boolean result) {
		if (result) {
			System.out.println(first + " and " + second + " are connected");
		} else {
			System.out.println(first + " and " + second + " are not connected");
		}
	}
	private static void QuickUnionTest(int first, int second) {
		QuickUnionUF quuf = new QuickUnionUF(10);
		quuf.union(3, 6);
		quuf.union(7, 8);
		quuf.union(3, 8);
		if (quuf.connected(first, second)) {
			System.out.println(first + " and " + second + " are connected");
		} else {
			System.out.println(first + " and " + second + " are not connected");
		}
		quuf.connected(6, 7);
	}
	private static void WeightedUnionTest(int first,int second) {
		WeightedUnionUF wuuf = new WeightedUnionUF(10);
		wuuf.union(1, 0);
		wuuf.union(2, 0);
		wuuf.union(3, 0);
		wuuf.union(4, 0);
		wuuf.union(5, 0);
		wuuf.union(7, 8);
		wuuf.union(9, 8);
		wuuf.union(6, 7);
		wuuf.union(3, 6);
		boolean result = wuuf.connected(first,second);
		Print(first,second,result);
		
	}
}
