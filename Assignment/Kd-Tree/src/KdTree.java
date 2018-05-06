import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

/**
 * Created by MinhTam on  5/6/2018.
 */
public class KdTree {
    private class Node {
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
    private int size;
    private Node root;

    // construct an empty set of points
    public KdTree() {
        size = 0;
        root = null;
    }

    // is the set empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p){
        if(p == null)
            throw new IllegalArgumentException();
        root = insert(root, p, 0.0, 0.0, 1.0, 1.0, true);
    }

    // does the set contain point p?
    public boolean contains(Point2D p){
        if (p == null)
            throw new IllegalArgumentException();
        return contains(root, p, true);
    }

    // draw all points to standard draw
    public void draw(){
        draw(root, true);
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect){
        Queue<Point2D> q = new Queue<Point2D>();
        range(root, rect, q);
        return q;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        if (root == null) return null;
        return nearest(root, p, root.getP(), true);
    }
    /* Helper functions */


    /* Approach:
     * Traverse the tree, alternating between vertical and horizontal nodes,
     * until you either find the node (return true) or reach a null pointer
     * (return false).
     *
     * Params:
     * node: Current node to be considered
     * p:    Input point (checked for inclusion)
     * xcmp: true if the current node is vertical (requires x-val comparison) */
    private boolean contains(Node root, Point2D p, boolean xcmp) {
        if(root == null) return false;
        else if(root.getP().x() == p.x() && root.getP().y() == p.y())
            return true;
        else {
            if(xcmp) {
                double cmp = p.x() - root.getP().x();
                if(cmp < 0) return contains(root.getLb(), p, !xcmp);
                else return contains(root.getRt(), p, !xcmp);
            }
            // The current node is horizontal: compare y-coordinates

            else {
                double cmp = p.y() - root.getP().y();
                if (cmp < 0) return contains(root.getLb(), p, !xcmp);
                else return contains(root.getRt(), p, !xcmp);
            }
        }
    }
    /* Approach:
     * Traverse the tree, alternating between vertical and horizontal nodes,
     * drawing each point and the appropriate line containing that point.
     *
     * Params:
     * node:     Current node to be considered
     * drawVert: true if current node is vertical (need to draw vertical line) */
    private void draw(Node root, boolean drawVert) {
        if(root == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        root.getP().draw();
        // Draw vertical line with x-coordinates of the point and y-coordinates
        // of the parent rectangle
        if (drawVert) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(root.getP().x(), root.getRect().ymin(),
                    root.getP().x(), root.getRect().ymax());
        }
        // Draw horizontal line with y-coordinates of the point and x-coordinates
        // of the parent rectangle
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(root.getRect().xmin(), root.getP().y(),
                    root.getRect().xmax(), root.getP().y());
        }
        // Draw subtrees
        draw(root.getLb(), !drawVert);
        draw(root.getRt(), !drawVert);
    }
    /* Approach:
     * Traverse the tree, alternating between vertical and horizontal nodes,
     * until you reach an empty node and insert there. Keep track of the parent
     * rectangle of each node (a new node will split its parent rectangle either
     * horizontally or vertically, depending on the node, into two subrectangles
     * for its children).
     *
     * Params:
     * node:   Current node to be considered
     * p:      Input point (to be inserted)
     * x0, y0: Rectangle lower bounds
     * x1, y1: Rectangle upper bounds
     * xcmp:   true if the current node is vertical (requires x-val comparison) */
    private Node insert(Node root, Point2D p, double x0, double y0,
                        double x1, double y1, boolean xcmp) {
        // Insert when you reach an empty location
        if (root == null) {
            size++;
            RectHV r = new RectHV(x0, y0, x1, y1);
            return new Node(p, r);
        }
        // If the point already exists, just return
        else if (root.getP().x() == p.x() && root.getP().y() == p.y())
            return root;
        // The current node is vertical: compare x-coordinates
        // The current node is vertical: compare x-coordinates
        if (xcmp) {
            double cmp = p.x() - root.getP().x();
            if (cmp < 0)
                root.setLb(insert(root.getLb(), p, x0, y0, root.getP().x(),
                        y1, !xcmp));
            else
                root.setRt(insert(root.getRt(), p, root.getP().x(), y0, x1,
                        y1, !xcmp));
        }
        // The current node is horizontal: compare y-coordinates
        else {
            double cmp = p.y() - root.getP().y();
            if (cmp < 0)
                root.setLb(insert(root.getLb(), p, x0, y0, x1,
                        root.getP().y(), !xcmp));
            else
                root.setRt(insert(root.getRt(), p, x0, root.getP().y(),
                        x1, y1, !xcmp));
        }
        return root;
    }
    /* Approach:
     * Traverse the tree, alternating between vertical and horizontal nodes,
     * keeping track of the closest point to the input point found so far.
     * Only consider subtrees whose parent rectangles are closer to the input
     * point than the closest point found so far.
     *
     * Params:
     * node: Current node to be considered
     * p:    Input point (finding closest point to it)
     * c:    Point currently closest to the input point
     * xcmp: true if the current node is vertical (requires x-val comparison) */

    private Point2D nearest(Node root, Point2D p, Point2D p1, boolean xcmp) {
        Point2D closest = p1;
        // If there are no more nodes, return the closest point found so far
        if (root == null) return closest;
        // If the current point is closer than the closest point found so far,
        // update the closest point
        if (root.getP().distanceSquaredTo(p) < closest.distanceSquaredTo(p))
            closest = root.getP();
        // If the current rectangle is closer to p than the closest point,
        // check its subtrees
        if (root.getRect().distanceSquaredTo(p) < closest.distanceSquaredTo(p)) {
            // Find which subtree the p is in
            Node near;
            Node far;
            if ((xcmp && (p.x() < root.getP().x())) ||
                    (!xcmp && (p.y() < root.getP().y()))) {
                near = root.getLb();
                far = root.getRt();
            }

            else {
                near = root.getRt();
                far = root.getLb();
            }
            // Check subtree on the same side as p
            closest = nearest(near, p, closest, !xcmp);
            // Check the subtree on the opposite side as p
            closest = nearest(far, p, closest, !xcmp);
        }
        return closest;
    }
    /* Approach:
     * Traverse the tree, enqueueing nodes if they are inside the input rectangle.
     * Only consider subtrees whose parent rectangles intersect the input rectangle.
     *
     * Params:
     * node: Current node to be considered
     * rect: Input rectangle (checked for which points are inside of it)
     * q:    Queue containing the points inside the rectangle */
    private void range(Node root, RectHV rect, Queue<Point2D> q) {
        if (root == null) return;
        // If the current point is in the input rectangle, enqueue that point
        if (rect.contains(root.getP())) {
            q.enqueue(root.getP());
        }
        // Check the left and right subtrees if the input rectangle intersects
        // the current rectangle
        if (rect.intersects(root.getRect())) {
            range(root.getLb(), rect, q);
            range(root.getRt(), rect, q);
        }
    }

}
