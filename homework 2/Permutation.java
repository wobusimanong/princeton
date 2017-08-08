import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String str = StdIn.readString();
        
        rq.enqueue(str);
        while(!StdIn.isEmpty()) {
            str = StdIn.readString();
            rq.enqueue(str);
        }
        
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}