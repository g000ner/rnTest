package study.rntest.service.compute;

import study.rntest.entity.compute.ComputeResultLog;
import study.rntest.entity.compute.ComputeResult;
import study.rntest.entity.point.Dimension;
import study.rntest.entity.point.Point;
import study.rntest.entity.point.PointsPair;
import study.rntest.entity.point.Point2D;
import study.rntest.entity.point.Point3D;
import study.rntest.exception.ComputeException;
import study.rntest.exception.handler.IncorrectPointPairException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComputeService {
    private ComputeResultsLogService computeResultsLogService;
    private UUID currentRunId;
    private PointsDistanceComputer distanceComputer;
    private Dimension currComputeType;

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

    public List<ComputeResult> computeDistanceBetweenPointsInPairs(List<PointsPair> pointsPairs) throws IncorrectPointPairException {
        List<ComputeResult> result = new ArrayList<>();
        if (pointsPairs.isEmpty()) {
            return result;
        }

        setDistanceComputerByFirstPointsPair(pointsPairs.get(0));

        currentRunId = UUID.randomUUID();
        computeResultsLogService.openSession();
        try {
            for (PointsPair pair : pointsPairs) {
                Time computeStartTime = new Time(System.currentTimeMillis());
                double distance = 0;
                distance = distanceComputer.computeDistanceBetweenPointsInPair(pair);

                Time computeEndTime = new Time(System.currentTimeMillis());

                ComputeResult computeResultRow = new ComputeResult(
                        pair.getFirstPoint(),
                        pair.getSecondPoint(),
                        distance
                );

                ComputeResultLog resultLog = new ComputeResultLog(
                        currentRunId,
                        computeStartTime,
                        computeEndTime,
                        pair.getFirstPoint().toString(),
                        pair.getSecondPoint().toString(),
                        currComputeType,
                        distance
                );
                computeResultsLogService.saveComputeResultLog(resultLog);

                result.add(computeResultRow);

            }
        } catch (ComputeException e) {
            throw new RuntimeException(e);
        } finally {
            computeResultsLogService.closeSession();
        }

        return result;
    }

    private void setDistanceComputerByFirstPointsPair(PointsPair pair) throws IncorrectPointPairException {
        Point first = pair.getFirstPoint();

        if (first == null) {
            throw new IncorrectPointPairException("В паре нет первой точки");
        }

        Class<? extends Point> pointsClass = first.getClass();
        if (pointsClass.equals(Point2D.class)) {
            distanceComputer = new Points2DDistanceComputer();
            currComputeType = Dimension.SECOND_DIM;
        } else if (pointsClass.equals(Point3D.class)) {
            distanceComputer = new Points3DDistanceComputer();
            currComputeType = Dimension.THREE_DIM;
        } else {
            throw new IncorrectPointPairException("В паре точки неизвестной размерности");
        }
    }
}
