package study.rntest.service.compute;

import study.rntest.entity.point.PointsPair;
import study.rntest.exception.ComputeException;

public interface PointsDistanceComputer {

    double computeDistanceBetweenPointsInPair(PointsPair pair) throws ComputeException;
}
