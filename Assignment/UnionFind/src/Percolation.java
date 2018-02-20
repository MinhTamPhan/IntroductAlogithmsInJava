/**
 * Created by MinhTam on  2/20/2018.
 */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    // weighted quick find object reference
    private WeightedQuickUnionUF wuf;
    // a boolean array for holding whether a particular site is open or close.
    private boolean[] openSiteGrid;
    // size of the grid
    private int size;

    /**
     * create n-by-n grid, with all sites blocked
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.size = N; // size of the grid.

        // two extra site for virtual top and bottom site
        wuf = new WeightedQuickUnionUF((N * N) + 2);

        // create N-by-N grid, with all sites blocked
        openSiteGrid = new boolean[(N * N) + 2];
    }

    /**
     * @param i
     * @param j
     * @return integer
     * @description this method converts the 2d index to 1d for boolean array
     * @throws IndexOutOfBoundException
     */

    private int getIndexOfElementinGrid(int i, int j) {
        if (isIndicesInvalid(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        //the two virtual sites will have index  0 and N*N+1 respectively
        return (i - 1) * this.size + j;
    }

    /**
     * @param i
     * @param j
     * @return boolean
     * @description checks if the indices are in valid range or not
     */
    private boolean isIndicesInvalid(int i, int j) {
        return (i < 1 || i > this.size || j < 1 || j > this.size);
    }

    /**
     * @return void
     * @param i
     * @param j
     * @description this method connects two sites
     */
    private void connect(int i, int j) {
        wuf.union(i, j);
    }


    /**
     * @param i
     * @param j
     * @return void
     * @description opens a site and connects to adjacent open sites if any
     * @throws IndexOutOfBoundException
     */

    public void open(int i, int j) {
        if (isIndicesInvalid(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int index = getIndexOfElementinGrid(i, j);
        if (!openSiteGrid[index]) {
            // open site (row i, column j) if it is not open already
            openSiteGrid[index] = true;
        }
        if (i == 1) {
            connect(index, 0);
        }
        if (i == this.size) {
            wuf.union(index, (this.size * this.size) + 1);
        }
        // if the left site is open connect it to left site
        if (i > 1 && isOpen(i - 1, j)) {
            connect(index, getIndexOfElementinGrid(i - 1, j));
        }
        // if the bottom site is open connect it to bottom site
        if (i < this.size && isOpen(i + 1, j)) {
            connect(index, getIndexOfElementinGrid(i + 1, j));
        }
        // if the left site is open connect it to left site
        if (j > 1 && isOpen(i, j - 1)) {
            connect(index, getIndexOfElementinGrid(i, j - 1));
        }
        // if the right site is open connect it to right site
        if (j < this.size && isOpen(i, j + 1)) {
            connect(index, getIndexOfElementinGrid(i, j + 1));
        }
    }

    /**
     * @param i
     * @param j
     * @return boolean
     * @description checks whether a particular site is open or not
     * @throws IndexOutOfBoundException
     */
    public boolean isOpen(int i, int j) {
        if (isIndicesInvalid(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int index = getIndexOfElementinGrid(i, j);
        return openSiteGrid[index];
    }

    /**
     * @param i
     * @param j
     * @return boolean
     * @description checks whether a particular site is full or not
     * @throws IndexOutOfBoundException
     */

    public boolean isFull(int i, int j) {
        if (isIndicesInvalid(i, j)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int index = getIndexOfElementinGrid(i, j);
        return wuf.connected(index, 0);
    }

    /**
     *
     * @return number of open sites
     */
    public  int numberOfOpenSites() {
        int count = 0;
        for (boolean element: openSiteGrid ) {
            if(element == true)
                count ++;
        }
        return count;
    }

    /**
     * @return boolean
     * @description checks whether the system percolates or not
     */

    public boolean percolates() {
        return wuf.connected((this.size * this.size) + 1, 0);
    }

    public static void main(String[] args)   // test client (optional)
    {

    }

}
