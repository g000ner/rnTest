package study.rntest.service.compute;

import study.rntest.entity.point.Point;
import study.rntest.entity.point.Point2D;
import study.rntest.entity.point.PointsPair;
import study.rntest.exception.ComputeException;

public class Points2DDistanceComputer implements PointsDistanceComputer {
    @Override
    public double computeDistanceBetweenPointsInPair(PointsPair pair) throws ComputeException {
        Point first = pair.getFirstPoint();
        Point second = pair.getSecondPoint();
        double distance = 0;

        Point2D firstSecDim = (Point2D) first;
        Point2D secondSecDim = (Point2D) second;

        distance = Math.sqrt(
                Math.pow(firstSecDim.getX() - secondSecDim.getX(), 2) +
                        Math.pow(firstSecDim.getY() - secondSecDim.getY(), 2));

        if (Double.isInfinite(distance)) {
            throw new ComputeException("Дистанция - infinite");
        }

        if (Double.isNaN(distance)) {
            throw new ComputeException("Дистанция - NaN");
        }

        return distance;
    }
}
