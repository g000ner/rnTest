package study.rnTest.service.compute;

import study.rnTest.entity.compute.ComputeResult;
import study.rnTest.entity.point.Point;
import study.rnTest.entity.point.Point3D;
import study.rnTest.entity.point.PointsPair;

public class Points3DDistanceComputer implements PointsDistanceComputer {
    @Override
    public double computeDistanceBetweenPointsInPair(PointsPair pair) {
        Point first = pair.getFirstPoint();
        Point second = pair.getSecondPoint();
        double distance = 0;

        Point3D firstThreeDim = (Point3D) first;
        Point3D secondThreeDim = (Point3D) second;

        distance = Math.sqrt(
                Math.pow(firstThreeDim.getX() - secondThreeDim.getX(), 2) +
                        Math.pow(firstThreeDim.getY() - secondThreeDim.getY(), 2) +
                        Math.pow(firstThreeDim.getZ() - secondThreeDim.getZ(), 2));

        return distance;
    }
}
