import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by MinhTam on  3/18/2018.
 */
public class Solver {

    private class PriorityBoard implements Comparable<PriorityBoard> {
        Board board;
        int priority;
        private PriorityBoard(Board board, int priority){
            this.board = board;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityBoard o) {
            if (priority < o.priority)
                return  -1;
            if (priority > o.priority)
                return  1;
            return 0;
        }
    }
    private Stack<Board> solution;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        solution = new Stack<>();
        PriorityBoard board = new PriorityBoard(initial, initial.manhattan());
        runAStar(board, board.board);
    }
    private Iterable<Board> runAStar(PriorityBoard priorityBoard, Board start){
        MinPQ<PriorityBoard> pq = new MinPQ<>();
        HashMap<Board, Board> previous = new HashMap<>();
        HashMap<Board, Integer> move = new HashMap<Board, Integer>();
        move.put(priorityBoard.board, 0);
        pq.insert(priorityBoard);
        Board goal = null;
        while (!pq.isEmpty()){
            PriorityBoard i = pq.delMin();
            //StdOut.println(i.board);
            if(i.board.isGoal()){
                goal = i.board;
                break;
            }
            //int t = i.manhattan() + move.get(i);
            Iterable<Board> listNeighbors = i.board.neighbors();
            listNeighbors.forEach(board -> {
                if(!move.containsKey(board))
                    pq.insert(new PriorityBoard(board,
                        board.manhattan() + move.get(i.board)));
                move.put(board, move.get(i.board) + 1);
                previous.put(board, i.board);
            });
        }
        solution.push(goal);
        while (previous.get(goal) != start){
            goal = previous.get(goal);
            solution.push(goal);
        }
        return solution;
    }
    // is the initial board solvable?
    public boolean isSolvable() {
        return solution.size() > 0;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solution.size();
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }
    // solve a slider puzzle (given below)
    public static void main(String[] args) {

    }
}
