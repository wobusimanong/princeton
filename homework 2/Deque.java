import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;
    private class Node {
        private Item i;
        private Node next;
        private Node prev;
        Node(Item i) {
            this.i = i;
            next = null;
            prev = null;
        }
    }
    
    
    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        
        if (isEmpty()) {
            head = new Node(item);
            tail = head;
        } else {
            Node temp = head;
            head = new Node(item);
            head.next = temp;
            temp.prev = head;
        }
        
        size++;
    }
    
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        
        if (isEmpty()) {
            tail = new Node(item);
            head = tail;
        } else {
            Node temp = tail;
            tail.next = new Node(item);
            tail = tail.next;
            tail.prev = temp;
        }
        
        size++;
    }
    
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        
        Item result = head.i;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        
        size--;
        return result;
    }
    
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        
        Item result = tail.i;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        
        size--;
        return result;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node h;
        
        public DequeIterator() {
            h = head;
        }
        
        public boolean hasNext() {
            return h != null;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            Node temp = h;
            h = h.next;
            return temp.i;
        }
    }
    
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<Integer>();
        dq.addFirst(1);
        dq.addLast(2);
        dq.addFirst(3);
        StdOut.println("The size of this deque is " + dq.size());
        int a = dq.removeFirst();
        StdOut.println("The first removed element is " + a + "," + 
                       "The size is " + dq.size());
    }
    
}