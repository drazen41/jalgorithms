import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;



public class Deque<Item> implements Iterable<Item> {
	private Node<Item> first;    // first node
	private Node<Item> last;    // last node
    private int n;               // number of elements on dequeue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> previous;
    }
	public Deque() { // construct an empty deque
		// TODO Auto-generated constructor stub
		first = null;
		last = null;
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
        if (oldFirst != null) {
        	oldFirst.previous = first ;
		}
        
        
        n++;
	}
	public void addLast(Item item) {           // add the item to the end
		if (item == null) {
			throw new IllegalArgumentException();
		}
		Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.previous = oldlast;
        last.next = null;
        if (first == null) first = last;
        if (oldlast != null) {
			oldlast.next = last;
		}
        n++;
	}
	public Item removeFirst() {                // remove and return the item from the front
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> oldFirst = first;
		Item item = first.item;
		first = oldFirst.next;
		oldFirst = null;
		if (first != null) {
			first.previous = null;
		}
		
//		newFirst.previous = null;
//		first.previous = null;
		n--;
		if (n == 0) {
			last = null;
			first = null;
		}
		return item;
	}
	
	public Item removeLast() {                 // remove and return the item from the end
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		Node<Item> oldPrevious = last.previous;
		Item item = last.item;
		last = last.previous;
		if (oldPrevious != null) {
			last.previous = oldPrevious.previous;
		}
//		last.next = null;
		n--;
		if (n == 0) {
			first = null;
			last = null;
		}
		return item;
		
		
	}
	public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
		return new DequeIterator<Item>(first);
	}
	private class DequeIterator<Item> implements Iterator<Item> {
		private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Integer> deque = new  Deque<>();
//		deque.addFirst(0);
//		deque.addFirst(1);
//		deque.addFirst(2);
//		StdOut.println(deque.removeFirst());
//		StdOut.println(deque.removeFirst());
//		StdOut.println(deque.removeFirst());
		Iterator<Integer > iterator = deque.iterator();
		deque.addFirst(1);
//		iterator.next();
		deque.addLast(2);		 
		StdOut.println(deque.removeLast());
		iterator.next();
	}

}
