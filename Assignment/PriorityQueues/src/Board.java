/**
 * Created by MinhTam on  3/18/2018.
 */
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;
public class Board {
    private Point2D board[];
    private int N;
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null)
            throw new IllegalArgumentException();
        N = blocks.length;
        board = new Point2D[N * N];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                int val = blocks[i][j];
                board[i] = new Point2D(val % N, val / N) ;
            }
        }
    }
    // board dimension n
    public int dimension() {
        return  N;
    }
    // number of blocks out of place
    public int hamming()  {
        int count = 0;
        for (int i = 0; i < N * N; i++) {
            if(board[i].x() != i % N || board[i].y() != i / N )
                count++;
        }
        return  count;
    }
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N * N; i++) {
            int row = (i) / N;
            int col = (i) % N;
            sum += Math.abs(row - board[i].y()) + Math.abs(col - board[i].x());
        }
        return sum;
    }
    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int n = 0,m = 0;
        while (n == m){
            n = StdRandom.uniform(0, 9);
            m = StdRandom.uniform(0, 9);
        }
        while (board[n/3][n%3] == 0){
            n = StdRandom.uniform(0, 9);
        }
        while (board[m/3][m%3] == 0){
            m = StdRandom.uniform(0, 9);
        }
        int[][] result = board.clone();
        int t = result[m/3][m%3];
        result[m/3][m%3] = result[n/3][n%3];
        result[n/3][n%3] = t;
        return  new Board(result);
    }
    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            throw new IllegalArgumentException();
        }
        Board that = (Board)y;
        for (int i = 0; i < N * N; i++) {
            if (!board[i].equals(that.board[i]))
                return false;
        }
        return true;
    }
    // all neighboring boards
    public Iterable<Board> neighbors() {
        int row = 0,col = 0;
        for (int i = 0; i < N * N; i++) {
            if(board[i].x() == 0 && board[i].y() == 0){
                row = i / N;
                col = i % N;
            }
        }
        Stack<Board> result = new Stack<>();
        int l = col - 1, r = col + 1;
        int top = row - 1, down = row + 1;
        if (l >= 0){
            Board neighbor = new Board(board);
            neighbor.board[row][col] = neighbor.board[row][l];
            neighbor.board[row][l] = 0;
            result.push(neighbor);
        }
        if (top >= 0){
            Board neighbor = this.clone();
            neighbor.board[row][col] = neighbor.board[top][col];
            neighbor.board[top][col] = 0;
            result.push(neighbor);
        }
        if (r < N){
            Board neighbor = new Board(board.clone());
            neighbor.board[row][col] = neighbor.board[row][r];
            neighbor.board[row][r] = 0;
            result.push(neighbor);
        }
        if (down < N){
            Board neighbor = new Board(board.clone());
            neighbor.board[row][col] = neighbor.board[down][col];
            neighbor.board[down][col] = 0;
            result.push(neighbor);
        }
        return  result;
    }
    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
