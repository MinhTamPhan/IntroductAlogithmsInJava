import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

import java.util.TreeSet;

/**
 * Created by MinhTam on  5/5/2018.
 */
public class PointSET {
    private TreeSet<Point2D> setPoint;
    // construct an empty set of points
    public PointSET() {
        setPoint = new TreeSet<>();
    }
    // is the set empty?
    public boolean isEmpty(){
        return setPoint.isEmpty();
    }
    // number of points in the set
    public int size() {
        return setPoint.size();
    }
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p){
        if(p == null)
            throw new IllegalArgumentException();
        setPoint.add(p);
    }
    // does the set contain point p?
    public boolean contains(Point2D p){
        return setPoint.contains(p);
    }
    // draw all points to standard draw
    public void draw(){
        for (Point2D item: setPoint ) {
            StdDraw.point(item.x(), item.y() );
        }
    }
    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect){
        Stack<Point2D> result = new Stack<>();
        for (Point2D item: setPoint ) {
            if(rect.contains(item))
                result.push(item);
        }
        return result;
    }
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        if(setPoint.isEmpty())
            return null;
        Point2D result = null;
        double min = Double.MAX_VALUE;
        for (Point2D item: setPoint ) {
            if(item.distanceSquaredTo(p) < min) {
                min = item.distanceSquaredTo(p);
                result = item;
            }
        }
        return result;
    }
    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
