package study.rntest.service.compute;

import study.rntest.entity.point.Point;
import study.rntest.entity.point.Point3D;
import study.rntest.entity.point.PointsPair;
import study.rntest.exception.ComputeException;

public class Points3DDistanceComputer implements PointsDistanceComputer {
    @Override
    public double computeDistanceBetweenPointsInPair(PointsPair pair) throws ComputeException {
        Point first = pair.getFirstPoint();
        Point second = pair.getSecondPoint();
        double distance = 0;

        Point3D firstThreeDim = (Point3D) first;
        Point3D secondThreeDim = (Point3D) second;

        distance = Math.sqrt(
                Math.pow(firstThreeDim.getX() - secondThreeDim.getX(), 2) +
                        Math.pow(firstThreeDim.getY() - secondThreeDim.getY(), 2) +
                        Math.pow(firstThreeDim.getZ() - secondThreeDim.getZ(), 2));

        if (Double.isInfinite(distance)) {
            throw new ComputeException("Дистанция - infinite");
        }

        if (Double.isNaN(distance)) {
            throw new ComputeException("Дистанция - NaN");
        }

        return distance;
    }
}
