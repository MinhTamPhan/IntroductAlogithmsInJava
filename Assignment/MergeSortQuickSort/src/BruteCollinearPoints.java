/**
 * Created by MinhTam on  3/7/2018.
 */
import edu.princeton.cs.algs4.Merge;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points
    private LineSegment[] segments;
    public BruteCollinearPoints(Point[] points){
        if (points == null)
            throw new IllegalArgumentException();
        checkDuplicatedEntries(points);
        Merge.sort(points);
        ArrayList<LineSegment> foundSegments = new ArrayList<>();
        for (int p = 0; p < points.length - 3; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                if (points[q] == null)
                    throw new java.lang.NullPointerException();
                for (int r = q + 1; r < points.length - 1; r++) {
                    if (points[r] == null)
                        throw new java.lang.NullPointerException();
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[s] == null)
                            throw new java.lang.NullPointerException();
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) &&
                                points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                            foundSegments.add(new LineSegment(points[p], points[s]));
                        }
                    }
                }
            }
        }
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    private void checkDuplicatedEntries(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }
    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }
    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, numberOfSegments());
    }

}
