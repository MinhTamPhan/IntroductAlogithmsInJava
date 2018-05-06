import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

/**
 * Created by MinhTam on  5/5/2018.
 */
public class Node {
    // the point
    private Point2D p;
    // the axis-aligned rectangle corresponding to this node
    private RectHV rect;
    // the left/bottom subtree
    private Node lb;
    // the right/top subtree
    private Node rt;

    public Node(Point2D p, RectHV rect, Node lb, Node rt) {
        this.p = p;
        this.rect = rect;
        this.lb = lb;
        this.rt = rt;
    }

    public Point2D getP() {
        return p;
    }

    public Node getLb() {
        return lb;
    }
    public void setLb(Node lb) {
        this.lb = lb;
    }

    public RectHV getRect() {
        return rect;
    }

    public void setRect(RectHV rect) {
        this.rect = rect;
    }

    public Node getRt() {
        return rt;
    }

    public void setRt(Node rt) {
        this.rt = rt;
    }

    public Node(Point2D p, RectHV r) {
        this.p = p;
        this.rect = r;
    }
}
