import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by MinhTam on  2/25/2018.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();



        //StdRandom.shuffle(strs);

        for (int i = 0; i < k; i++) {
            String strs = StdIn.readString();
            rq.enqueue(strs);
        }
        for (int i = 0; i < k; i++) {

            StdOut.println(rq.dequeue());

        }
    }
}
