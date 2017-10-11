import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;



public class DequeOld<Item> implements Iterable<Item> {
	private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private Node<Item> oldlast;
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
	public DequeOld() { // construct an empty deque
		// TODO Auto-generated constructor stub
		first = null;
      last  = null;
        n = 0;
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
		Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        if (last == null) last = first;
        
        n++;
	}
	public void addLast(Item item) {           // add the item to the end
		if (item == null) {
			throw new IllegalArgumentException();
		}
		oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (first == null) first = last;
        else oldlast.next = last;
        n++;
	}
	public Item removeFirst() {                // remove and return the item from the front
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = first.item;
		first = first.next;
		n--;
		return item;
	}
	
	public Item removeLast() {                 // remove and return the item from the end
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = last.item;
		last = oldlast;
		last.next = null;
		n--;
		return item;
		
		
	}
	public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
		throw new IllegalArgumentException();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DequeOld<String> tekst = new DequeOld<>();
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
