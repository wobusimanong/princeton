import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int size;
    
    public RandomizedQueue() {
        size = 0;
        a = (Item[]) new Object[2];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        
        if (size == a.length) {
            resize(2 * a.length);
        }
        
        a[size++] = item;
    }
    
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = a[i];
        }
        
        a = temp;
    }
    
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        
        int index = StdRandom.uniform(size);
        Item temp = a[index];
        Item t = a[size - 1];
        a[size - 1] = a[index];
        a[index] = t;
        a[size - 1] = null;
        size--;
        
        if (size > 0 && size == a.length / 4) {
            resize(a.length / 2);
        }
        
        return temp;
    }
    
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        
        return a[StdRandom.uniform(size)];
    }
    
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }
    
    private class RandomArrayIterator implements Iterator<Item> {
        private int i;
        private Item[] copy;
        
        public RandomArrayIterator() {
            i = size;
            copy = (Item[]) new Object[size];
            for (int j = 0; j < size; j++) {
                copy[j] = a[j];
            }
        }
        
        public boolean hasNext() {
            return i > 0;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            int index = StdRandom.uniform(i);
            Item result = copy[index];
            Item temp = copy[index];
            copy[index] = copy[i - 1];
            copy[i - 1] = temp;
            i--;
            
            return result;
        }
    }
    
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("df");
        rq.enqueue("ad");
        rq.enqueue("du");
        
        StdOut.println("The size of queue is " + rq.size());
        for (int i = 0; i < 8; i++) {
            StdOut.println("Random access: " + rq.sample());
        }
    }
}