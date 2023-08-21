package study.rntest.entity.point;

import study.rntest.exception.PointException;

public class PointsPair {
    Point firstPoint;
    Point secondPoint;

    public Point getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point firstPoint) throws PointException {
        if (secondPoint != null &&
                ! (secondPoint.getClass().equals(firstPoint.getClass()))) {
            throw new PointException("точка firstPoint не соответствует размерности точки secondPoint в паре");
        }
        this.firstPoint = firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(Point secondPoint) throws PointException {
        if (firstPoint != null &&
                ! (firstPoint.getClass().equals(secondPoint.getClass()))) {
            throw new PointException("точка firstPoint не соответствует размерности точки secondPoint в паре");
        }
        this.secondPoint = secondPoint;
    }
}
