package study.rnTest.service.compute;

import study.rnTest.entity.compute.ComputeResult;
import study.rnTest.entity.point.Point;
import study.rnTest.entity.point.Point2D;
import study.rnTest.entity.point.PointsPair;

public class Points2DDistanceComputer implements PointsDistanceComputer {
    @Override
    public double computeDistanceBetweenPointsInPair(PointsPair pair) {
        Point first = pair.getFirstPoint();
        Point second = pair.getSecondPoint();
        double distance = 0;

        Point2D firstSecDim = (Point2D) first;
        Point2D secondSecDim = (Point2D) second;

        distance = Math.sqrt(
                Math.pow(firstSecDim.getX() - secondSecDim.getX(), 2) +
                        Math.pow(firstSecDim.getY() - secondSecDim.getY(), 2));

        return distance;
    }
}
