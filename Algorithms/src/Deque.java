import java.util.Iterator;
public class Deque<Item> implements Iterable<Item> {

	public Deque() { // construct an empty deque
		// TODO Auto-generated constructor stub
	}
	public boolean isEmpty() {                 // is the deque empty?
		throw new IllegalArgumentException();
	}
	public int size() {                        // return the number of items on the deque
		throw new IllegalArgumentException();
	}
	public void addFirst(Item item) {          // add the item to the front
		if (item == null) {
			throw new IllegalArgumentException();
		}
		
	}
	public void addLast(Item item) {           // add the item to the end
		if (item == null) {
			throw new IllegalArgumentException();
		}
	}
	public Item removeFirst() {                // remove and return the item from the front
		throw new IllegalArgumentException();
	}
	public Item removeLast() {                 // remove and return the item from the end
		throw new IllegalArgumentException();
	}
	public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
		throw new IllegalArgumentException();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
