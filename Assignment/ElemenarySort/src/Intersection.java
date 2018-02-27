/**
 * Created by MinhTam on  2/27/2018.
 */

import com.sun.scenario.effect.Merge;
import edu.princeton.cs.algs4.*;

/**
 *Intersection of two sets.Given two arrays A and B, each containing distinct
 * 2D points in the plane, design a subquadratic algorithm to count the number
 * of points that are contained both in array A and array B.
 */
public class Intersection {
    public class Poind2d implements Comparable<Poind2d>{
        private float x;
        private float y;
        public Poind2d (float x, float y){
            this.x = x;
            this.y = y;
        }
        public Poind2d(){
            x = y =0;
        }
        public Poind2d(Poind2d another){
            x = another.x;
            y = another.y;
        }

        @Override
        public int compareTo(Poind2d that) {
            if (that.x > this.x) return -1;

            if (that.x < this.x) return 1;

            if (that.y > this.y) return -1;

            if (that.y < this.y) return 1;
            return 0;
        }
        public int countIntersection(Poind2d[] a, Poind2d[] b) {
            Quick.sort(a);
            Quick.sort(b);
            //Shell.sort(b) ;
            int i = 0;
            int j = 0;
            int count = 0;
            while (i < a.length && j < b.length) {
                if (a[i].compareTo(b[j]) == 0) {
                    count++;
                    i++;
                    j++;
                }
                else if (a[i].compareTo(b[j]) < 0) i++;
                else j++;
            }
            return count;
        }
    }

    /*
    Question 2
    Permutation.
    Given two integer arrays of size N, design a subquadratic algorithm
    to determine whether one is a permutation of the other.
    That is, do they contain exactly the same entries but, possibly,
    in a different order.
     */



    public boolean isPerm(Integer[] a, Integer[] b) {
        if (a.length != b.length) return false;
//        Shell.sort(a);
//        Shell.sort(b);
        edu.princeton.cs.algs4.Merge.sort(a);
        edu.princeton.cs.algs4.Merge.sort(b);
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }
    /*
    Question 3
    Dutch national flag. Given an array of N buckets, each containing a red,
    white, or blue pebble, sort them by color. The allowed operations are:
    swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
    color(i): color of pebble in bucket i.
    The performance requirements are as follows:
    At most N calls to color().
    At most N calls to swap().
    Constant extra space.
     */



    enum Pebble {
        Red,
        White,
        Blue
    }



    class Buckets {
        private Pebble[] pebbles;
        private Pebble color(int i) {
            return pebbles[i];
        }



        private int compare(Pebble p) {
            Pebble white = Pebble.White;
            return p.ordinal() - white.ordinal();
        }



        private void swap(int i, int j) {
            Pebble tmp = pebbles[i];
            pebbles[i] = pebbles[j];
            pebbles[j] = tmp;
        }



        public void sort() {
            assert pebbles.length > 0;
            int r = 0;
            int runner = 0;
            int b = pebbles.length - 1;
            while (runner <= b) {
                Pebble color = color(runner);
                int cmp = compare(color);
                if (cmp < 0) {
                    swap(runner++, r++);
                }
                else if (cmp > 0) {
                    swap(runner, b--);
                }

                else {
                    runner++;
                }
            }
        }
    }
}
