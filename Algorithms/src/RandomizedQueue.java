import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
//import edu.princeton.cs.algs4.Stopwatch;


public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int last;       // index of next available slot

	
	
	public RandomizedQueue() { // construct an empty randomized queue
		// TODO Auto-generated constructor stub
		 q = (Item[]) new Object[2];
	     n = 0;
	     last = 0;
	}
	public boolean isEmpty() {                // is the randomized queue empty?
		return n == 0;
	}
	public int size() {                        // return the number of items on the randomized queue
		return n;
	}
	public void enqueue(Item item) {           // add the item
		if (item == null) {
			throw new IllegalArgumentException();
		}
		// double size of array if necessary and recopy to front of array
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        n++;
	}
	public Item dequeue() {                   // remove and return a random item
		if (isEmpty()) throw new NoSuchElementException();
        Item item = randomDeque();        
        return item;
	}
	private Item randomDeque() {
		int[] temp = new int[n];
		int te = 0;
		for (int i = 0; i < q.length; i++) {
			if (q[i] != null) {
				temp[te] = i;
				te++;
			}
		}
		int random = StdRandom.uniform(temp.length);
		Item item = q[temp[random]];
		
        q[temp[random]] = null;                            // to avoid loitering
        n--;
      
        if (random == q.length) random  = 0;           // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length/4) resize(q.length/2); 
        return item;
	}
	public Item sample() {                     // return a random item (but do not remove it)
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int[] temp = new int[n];
		int te = 0;
		for (int i = 0; i < q.length; i++) {
			if (q[i] != null) {
				temp[te] = i;
				te++;
			}
		}
		int random = StdRandom.uniform(temp.length);
		Item item = q[temp[random]];
		return item;
		
	}
	@Override
	public Iterator<Item> iterator() { // return an independent iterator over items in random order
		// TODO Auto-generated method stub
		return new RandomizedQueueIterator();
	}
	private class RandomizedQueueIterator implements Iterator<Item> {

		private int i = 0;
//		private Item temp[];
		private RandomizedQueueIterator() {
//			int random = 0;
			int te=0;
			Item item = null;
//			temp = (Item[]) new Object[n];
			
			for (int i = q.length - 1; i > 0; i--) {
				te = StdRandom.uniform(i+1);
				item = q[te];
				q[te] = q[i];
				q[i] = item;
			}
			
		}
        public boolean hasNext()  { return i < n;                               }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
        	if (!hasNext()) throw new NoSuchElementException();
            Item item = q[i];
            if (item == null) {
				while(item == null) {
					i++;
					item = q[i];
				}
			} else {
				i++;
			}
            
            return item;
            
        }
		
    }
	private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        int te = 0;
        for (int i = 0; i < q.length; i++) {
            if (q[i] != null) {
				temp[te] = q[i];
				te++;
			}
        	
        }
        q = temp;
        last  = n;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stopwatch stopwatch = new Stopwatch();
		
		RandomizedQueue<Integer> ints = new RandomizedQueue<>();
		for (int i = 0; i < 40000000; i++) {
			ints.enqueue(i);
			
		}
		for (int i = 0; i < 50; i++) {
//			StdOut.println(ints.dequeue());
			ints.dequeue();
		}
//		ints.enqueue(1);
//		StdOut.println(ints.dequeue());
		StdOut.println(stopwatch.elapsedTime());
	}

	

}
