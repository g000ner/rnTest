package study.rnTest.entity.compute;

import study.rnTest.entity.point.Point;

public class ComputeResult {
    Point firstPoint;
    Point secondPoint;
    double computeResult;

    public ComputeResult(Point firstPoint, Point secondPoint, double computeResult) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        this.computeResult = computeResult;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    public double getComputeResult() {
        return computeResult;
    }

    public void setComputeResult(double computeResult) {
        this.computeResult = computeResult;
    }

    public String[] toStringArray() {
        return new String[]{firstPoint.toString(), secondPoint.toString(), String.valueOf(computeResult)};
    }
}
