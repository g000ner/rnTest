package study.rnTest.service.compute;

import study.rnTest.entity.compute.ComputeResultLog;
import study.rnTest.entity.compute.ComputeResult;
import study.rnTest.entity.point.Dimension;
import study.rnTest.entity.point.Point;
import study.rnTest.entity.point.PointsPair;
import study.rnTest.entity.point.Point2D;
import study.rnTest.entity.point.Point3D;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComputeService {
    private ComputeResultsLogService computeResultsLogService;
    private UUID currentRunId;

    private static ComputeService instance = null;

    private ComputeService() {
        computeResultsLogService = ComputeResultsLogService.getInstance();
    }

    public static ComputeService getInstance() {
        if (instance == null) {
            instance = new ComputeService();
        }

        return instance;
    }

    public List<ComputeResult> computeDistanceBetweenPointsInPairs(List<PointsPair> pointsPairs) {
        List<ComputeResult> result = new ArrayList<>();
        if (pointsPairs.isEmpty()) {
            return result;
        }

        currentRunId = UUID.randomUUID();

        computeResultsLogService.openSession();
        for (PointsPair pair : pointsPairs) {
            double distance = computeDistanceBetweenPointsInPair(pair);

            ComputeResult computeResultRow = new ComputeResult(
                    pair.getFirstPoint(),
                    pair.getSecondPoint(),
                    distance
            );

            result.add(computeResultRow);
        }
        computeResultsLogService.closeSession();

        return result;
    }

    private double computeDistanceBetweenPointsInPair(PointsPair pair) {
        Time computeStartTime = new Time(System.currentTimeMillis());

        Point first = pair.getFirstPoint();
        Point second = pair.getSecondPoint();
        double distance = 0;

        Class<? extends Point> pointsClass = first.getClass();

        // todo
        Dimension computeType;
        if (pointsClass.equals(Point2D.class)) {
            Point2D firstSecDim = (Point2D) first;
            Point2D secondSecDim = (Point2D) second;

            distance = Math.sqrt(
                    Math.pow(firstSecDim.getX() - secondSecDim.getX(), 2) +
                            Math.pow(firstSecDim.getY() - secondSecDim.getY(), 2));

            computeType = Dimension.SECOND_DIM;
        } else {
            Point3D firstThreeDim = (Point3D) first;
            Point3D secondThreeDim = (Point3D) second;

            distance = Math.sqrt(
                    Math.pow(firstThreeDim.getX() - secondThreeDim.getX(), 2) +
                            Math.pow(firstThreeDim.getY() - secondThreeDim.getY(), 2) +
                            Math.pow(firstThreeDim.getZ() - secondThreeDim.getZ(), 2));

            computeType = Dimension.THREE_DIM;
        }
        Time computeEndTime = new Time(System.currentTimeMillis());


        ComputeResultLog resultLog = new ComputeResultLog(
                currentRunId,
                computeStartTime,
                computeEndTime,
                pair.getFirstPoint().toString(),
                pair.getSecondPoint().toString(),
                computeType,
                distance
        );
        computeResultsLogService.saveComputeResultLog(resultLog);

        return distance;
    }
}
