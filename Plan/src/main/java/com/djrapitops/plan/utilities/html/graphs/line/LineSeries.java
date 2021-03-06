/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.djrapitops.plan.utilities.html.graphs.line;

import main.java.com.djrapitops.plan.utilities.analysis.DouglasPeuckerAlgorithm;
import main.java.com.djrapitops.plan.utilities.analysis.Point;
import main.java.com.djrapitops.plan.utilities.analysis.ReduceGapTriangles;

import java.util.List;

/**
 * Abstract scatter graph creator used by other graph creators.
 *
 * @author Rsl1122
 * @since 3.5.2
 */
public class LineSeries {

    /**
     * Constructor used to hide the public constructor
     */
    private LineSeries() {
        throw new IllegalStateException("Utility class");
    }

    public static String createSeries(List<Point> points, boolean reduceGapTriangles) {
        return createSeries(points, reduceGapTriangles, true);
    }

    public static String createSeries(List<Point> points, boolean reduceGapTriangles, boolean reducePoints) {
        StringBuilder arrayBuilder = new StringBuilder("[");

//        if (reducePoints) {
//            points = DouglasPeuckerAlgorithm.reducePoints(points, 0);
//        }
//
//        if (reduceGapTriangles) {
//            points = ReduceGapTriangles.reduce(points);
//        }

        int size = points.size();
        for (int i = 0; i < size; i++) {
            Point point = points.get(i);
            double y = point.getY();
            long date = (long) point.getX();
            arrayBuilder.append("[").append(date).append(",").append(y).append("]");
            if (i < size - 1) {
                arrayBuilder.append(",");
            }
        }

        arrayBuilder.append("]");
        return arrayBuilder.toString();
    }
}
