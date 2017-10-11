import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
//	static String[] strings ;
//	static int n=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = 3;
		int i = 0;
		RandomizedQueue<String > randomizedQueue = new RandomizedQueue<>();
		if (args.length == 1) {
			k = Integer.parseInt(args[0]);
		}
//		strings = new String[1];
		while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            StdOut.println(item);
//			strings[i] = StdIn.readString();
			randomizedQueue.enqueue(StdIn.readString());
//			n++;
//			if (strings.length == n) {
//				resize(strings.length * 2);
//			}
//			i++;
        }
		Iterator<String> iterator = randomizedQueue.iterator();
		
		while(iterator.hasNext() && i < k) {			
			StdOut.println(iterator.next());
			i++;
		}
        
	}
//	private static void resize(int capacity) {
//        assert capacity >= n;
//        String[] temp = new String[capacity];
//        for (int i = 0; i < strings.length; i++) {
//            temp[i]=strings[i];
//        	
//        }
//        strings = temp;
//       
//    }

}
