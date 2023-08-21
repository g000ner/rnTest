package study.rntest.service.compute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import study.rntest.entity.point.Point2D;
import study.rntest.entity.point.PointsPair;
import study.rntest.exception.ComputeException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Points2DDistanceComputerTest {
    @Mock
    private PointsPair pointsPair;

    private Points2DDistanceComputer points2DDistanceComputer;

    @BeforeEach
    public void init() {
        points2DDistanceComputer = new Points2DDistanceComputer();
    }

    @Test
    public void computeDistanceBetweenPointsInPairSamePointsTest() throws ComputeException {
        pointsPair = mock(PointsPair.class);

        Point2D firstPoint = mock(Point2D.class);
        when(firstPoint.getX()).thenReturn(0.0);
        when(firstPoint.getY()).thenReturn(0.0);

        Point2D secondPoint = mock(Point2D.class);
        when(secondPoint.getX()).thenReturn(0.0);
        when(secondPoint.getY()).thenReturn(0.0);

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        double computeResult = points2DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);

        assertEquals(computeResult, 0);
    }

    @Test
    public void computeDistanceBetweenPointsInPairOneDifferentCoordinateTest() throws ComputeException {
        double firstPointX = 1;
        double firstPointY = 2;

        double secondPointX = firstPointX;
        double secondPointY = 22;

        pointsPair = mock(PointsPair.class);

        Point2D firstPoint = mock(Point2D.class);
        when(firstPoint.getX()).thenReturn(firstPointX);
        when(firstPoint.getY()).thenReturn(firstPointY);

        Point2D secondPoint = mock(Point2D.class);
        when(secondPoint.getX()).thenReturn(secondPointX);
        when(secondPoint.getY()).thenReturn(secondPointY);

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        double computeResult = points2DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);

        assertEquals(computeResult, 20);
    }

    @Test
    public void computeDistanceBetweenPointsInPairTest() throws ComputeException {
        double firstPointX = 1;
        double firstPointY = 2;

        double secondPointX = 1;
        double secondPointY = 22;

        pointsPair = mock(PointsPair.class);

        Point2D firstPoint = mock(Point2D.class);
        when(firstPoint.getX()).thenReturn(firstPointX);
        when(firstPoint.getY()).thenReturn(firstPointY);

        Point2D secondPoint = mock(Point2D.class);
        when(secondPoint.getX()).thenReturn(secondPointX);
        when(secondPoint.getY()).thenReturn(secondPointY);

        double expectedDistance = Math.sqrt(
                Math.pow(firstPointX - secondPointX, 2) +
                        Math.pow(firstPointY - secondPointY, 2));

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        double computeResult = points2DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);

        assertEquals(computeResult, expectedDistance);
    }

    @Test
    public void computeDistanceBetweenPointsInPairInfinityTest() {
        double firstPointX = Double.POSITIVE_INFINITY;
        double firstPointY = 2;

        double secondPointX = 1;
        double secondPointY = 22;

        pointsPair = mock(PointsPair.class);

        Point2D firstPoint = mock(Point2D.class);
        when(firstPoint.getX()).thenReturn(firstPointX);
        when(firstPoint.getY()).thenReturn(firstPointY);

        Point2D secondPoint = mock(Point2D.class);
        when(secondPoint.getX()).thenReturn(secondPointX);
        when(secondPoint.getY()).thenReturn(secondPointY);

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        assertThrows(ComputeException.class, () -> {
            points2DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);
        });
    }

    @Test
    public void computeDistanceBetweenPointsInPairNaNTest() {
        double firstPointX = 1;
        double firstPointY = 2;

        double secondPointX = Double.NaN;
        double secondPointY = 22;

        pointsPair = mock(PointsPair.class);

        Point2D firstPoint = mock(Point2D.class);
        when(firstPoint.getX()).thenReturn(firstPointX);
        when(firstPoint.getY()).thenReturn(firstPointY);

        Point2D secondPoint = mock(Point2D.class);
        when(secondPoint.getX()).thenReturn(secondPointX);
        when(secondPoint.getY()).thenReturn(secondPointY);

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        assertThrows(ComputeException.class, () -> {
            points2DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);
        });
    }
}
