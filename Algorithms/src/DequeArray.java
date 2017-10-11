import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;



public class DequeArray<Item> implements Iterable<Item> {
	private Item[] q;       // dequeue elements
    private int n;          // number of elements on dequeue
    private int first;      // index of first element of dequeue
    private int last;       // index of next available slot

    
	public DequeArray() { // construct an empty deque
		q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
	}
	public boolean isEmpty() {                 // is the deque empty?
		return n==0;
	}
	public int size() {                        // return the number of items on the deque
		return n;
	}
	public void addFirst(Item item) {          // add the item to the front
		if (item == null) {
			throw new IllegalArgumentException();
		}
		// double size of array if necessary and recopy to front of array
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
        q[first++] = item;                        // add item
        if (first == q.length) first = 0;          // wrap-around
        
        
        n++;
	}
	public void addLast(Item item) {           // add the item to the end
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (n == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (first == q.length) first = 0;          // wrap-around
        
        n++;
	}
	public Item removeFirst() {                // remove and return the item from the front
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	
		n--;
		return null;
	}
	
	public Item removeLast() {                 // remove and return the item from the end
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	
		n--;
		return null;
		
		
	}
	// resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last  = n;
    }
	public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
		throw new IllegalArgumentException();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DequeArray<String> tekst = new DequeArray<>();
		StdOut.println("Deque empty: " + tekst.isEmpty());
		tekst.addFirst("Prvi");
		tekst.addFirst("Drugi");
		tekst.addFirst("Treci");
		tekst.addLast("Deveti");
		tekst.addLast("Deseti");
		tekst.addLast("Jedanaesti");
		String removed = tekst.removeFirst();
		StdOut.println(removed);
		StdOut.println(tekst.removeFirst());
		StdOut.println(tekst.removeLast());
		
		
		
		StdOut.println("Deque empty: " + tekst.isEmpty());
	}

}
