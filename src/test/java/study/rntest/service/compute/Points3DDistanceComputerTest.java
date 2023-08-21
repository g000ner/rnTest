package study.rntest.service.compute;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import study.rntest.entity.point.Point3D;
import study.rntest.entity.point.PointsPair;
import study.rntest.exception.ComputeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Points3DDistanceComputerTest {

    @Mock
    private PointsPair pointsPair;

    private Points3DDistanceComputer points3DDistanceComputer;

    @BeforeEach
    public void init() {
        points3DDistanceComputer = new Points3DDistanceComputer();
    }

    @Test
    public void computeDistanceBetweenPointsInPairSamePointsTest() throws ComputeException {
        pointsPair = mock(PointsPair.class);

        Point3D firstPoint = mock(Point3D.class);
        when(firstPoint.getX()).thenReturn(0.0);
        when(firstPoint.getY()).thenReturn(0.0);
        when(firstPoint.getZ()).thenReturn(0.0);

        Point3D secondPoint = mock(Point3D.class);
        when(secondPoint.getX()).thenReturn(0.0);
        when(secondPoint.getY()).thenReturn(0.0);
        when(secondPoint.getZ()).thenReturn(0.0);

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        double computeResult = points3DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);

        assertEquals(computeResult, 0);
    }

    @Test
    public void computeDistanceBetweenPointsInPairOneDifferentCoordinateTest() throws ComputeException {
        double firstPointX = 1;
        double firstPointY = 2;
        double firstPointZ = 44;

        double secondPointX = firstPointX;
        double secondPointY = 22;
        double secondPointZ = firstPointZ;

        pointsPair = mock(PointsPair.class);

        Point3D firstPoint = mock(Point3D.class);
        when(firstPoint.getX()).thenReturn(firstPointX);
        when(firstPoint.getY()).thenReturn(firstPointY);
        when(firstPoint.getZ()).thenReturn(firstPointZ);

        Point3D secondPoint = mock(Point3D.class);
        when(secondPoint.getX()).thenReturn(secondPointX);
        when(secondPoint.getY()).thenReturn(secondPointY);
        when(secondPoint.getZ()).thenReturn(secondPointZ);

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        double computeResult = points3DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);

        assertEquals(computeResult, Math.abs(firstPointY - secondPointY));
    }

    @Test
    public void computeDistanceBetweenPointsInPairTest() throws ComputeException {
        double firstPointX = 1;
        double firstPointY = 2;
        double firstPointZ = 32;

        double secondPointX = 1;
        double secondPointY = 22;
        double secondPointZ = 2;

        pointsPair = mock(PointsPair.class);

        Point3D firstPoint = mock(Point3D.class);
        when(firstPoint.getX()).thenReturn(firstPointX);
        when(firstPoint.getY()).thenReturn(firstPointY);
        when(firstPoint.getZ()).thenReturn(firstPointZ);

        Point3D secondPoint = mock(Point3D.class);
        when(secondPoint.getX()).thenReturn(secondPointX);
        when(secondPoint.getY()).thenReturn(secondPointY);
        when(secondPoint.getZ()).thenReturn(secondPointZ);

        double expectedDistance = Math.sqrt(
                Math.pow(firstPointX - secondPointX, 2) +
                        Math.pow(firstPointY - secondPointY, 2) +
                        Math.pow(firstPointZ - secondPointZ, 2));

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        double computeResult = points3DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);

        assertEquals(computeResult, expectedDistance);
    }

    @Test
    public void computeDistanceBetweenPointsInPairInfinityTest() {
        double firstPointX = Double.POSITIVE_INFINITY;
        double firstPointY = 2;
        double firstPointZ = 32;

        double secondPointX = 1;
        double secondPointY = 22;
        double secondPointZ = 2;

        pointsPair = mock(PointsPair.class);

        Point3D firstPoint = mock(Point3D.class);
        when(firstPoint.getX()).thenReturn(firstPointX);
        when(firstPoint.getY()).thenReturn(firstPointY);
        when(firstPoint.getZ()).thenReturn(firstPointZ);

        Point3D secondPoint = mock(Point3D.class);
        when(secondPoint.getX()).thenReturn(secondPointX);
        when(secondPoint.getY()).thenReturn(secondPointY);
        when(secondPoint.getZ()).thenReturn(secondPointZ);

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        assertThrows(ComputeException.class, () -> {
            points3DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);
        });
    }

    @Test
    public void computeDistanceBetweenPointsInPairNaNTest() {
        double firstPointX = 1;
        double firstPointY = 2;
        double firstPointZ = 32;

        double secondPointX = Double.NaN;
        double secondPointY = 22;
        double secondPointZ = 2;

        pointsPair = mock(PointsPair.class);

        Point3D firstPoint = mock(Point3D.class);
        when(firstPoint.getX()).thenReturn(firstPointX);
        when(firstPoint.getY()).thenReturn(firstPointY);
        when(firstPoint.getZ()).thenReturn(firstPointZ);

        Point3D secondPoint = mock(Point3D.class);
        when(secondPoint.getX()).thenReturn(secondPointX);
        when(secondPoint.getY()).thenReturn(secondPointY);
        when(secondPoint.getZ()).thenReturn(secondPointZ);

        when(pointsPair.getFirstPoint()).thenReturn(firstPoint);
        when(pointsPair.getSecondPoint()).thenReturn(secondPoint);

        assertThrows(ComputeException.class, () -> {
            points3DDistanceComputer.computeDistanceBetweenPointsInPair(pointsPair);
        });
    }
}
