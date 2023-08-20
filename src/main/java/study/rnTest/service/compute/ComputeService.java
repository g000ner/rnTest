package study.rnTest.service.compute;

import study.rnTest.entity.compute.ComputeResultLog;
import study.rnTest.entity.compute.ComputeResult;
import study.rnTest.entity.point.Dimension;
import study.rnTest.entity.point.Point;
import study.rnTest.entity.point.PointsPair;
import study.rnTest.entity.point.Point2D;
import study.rnTest.entity.point.Point3D;
import study.rnTest.exception.handler.IncorrectPointPairException;

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
        for (PointsPair pair : pointsPairs) {
            Time computeStartTime = new Time(System.currentTimeMillis());
            double distance = distanceComputer.computeDistanceBetweenPointsInPair(pair);
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
        computeResultsLogService.closeSession();

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
        } else if (pointsClass.equals(Point3D.class)){
            distanceComputer = new Points3DDistanceComputer();
            currComputeType = Dimension.THREE_DIM;
        } else {
            throw new IncorrectPointPairException("В паре точки неизвестной размерности");
        }
    }
}
