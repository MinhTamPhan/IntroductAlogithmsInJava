/**
 * Created by MinhTam on  3/13/2018.
 */


import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.List;

import java.util.ArrayList;
import java.util.Arrays;
public class FastCollinearPoints {
    private LineSegment[] seg;
    private int num;
    private int N;
    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();
        N = points.length;
        seg = new LineSegment[N];
        Point[] alux  = new Point[N];
        Point[] base = new Point[N];
        boolean hasInline;
        int cap = 0;
        int same = 0;
        Point current;
        Point max;
        for (int i = 0; i < N; i++) {
            if (points[i] == null)
                throw new NullPointerException();
            for (int j = i+1; j < N; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException();
            }
            alux[i] = points[i];
            base[i] = points[i];
        }
        Arrays.sort(base);
        for (int i = 0, j = 0; i < N; i++, j = 0) {
            current = base[i];
            Arrays.sort(alux, current.slopeOrder());
            while (j < N - 2) {
                if (current.slopeTo(alux[j]) != current.slopeTo(alux[j+2]))
                    j++;
                else {
                    for (cap = 0, hasInline = false, max = alux[j];
                         j+cap < N && current.slopeTo(alux[j]) == current.slopeTo(alux[j+cap]); cap++) {
                        if (alux[j+cap].compareTo(max) > 0)
                            max = alux[j+cap];
                        if (current.compareTo(alux[j+cap]) > 0) {
                            hasInline = true;
                        } else if (current.compareTo(alux[j+cap]) == 0)
                            same = 1;
                    }
                    j += cap;
                    if (!hasInline && cap - same >= 2) {
                        seg[num++] = new LineSegment(current, max);
                        if (seg[seg.length-1] != null) {
                            changeSize(seg.length, seg.length * 2);
                        }
                    }
                    same = 0;
                }
            }
        }
    }
    private void changeSize(int oldSize, int newSize) {
        LineSegment[] newseg = new LineSegment[newSize];
        for (int i = 0; i < oldSize; i++) {
            newseg[i] = seg[i];
        }
        seg = newseg;
    }
    public int numberOfSegments() {
        return num;
    }
    public LineSegment[] segments() {
        LineSegment[] result = new LineSegment[num];
        for (int i = 0; i < num; i++)
            result[i] = seg[i];
        return result;
    }
}
