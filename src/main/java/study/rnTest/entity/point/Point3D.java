package study.rnTest.entity.point;

public class Point3D extends Point {
    double x;
    double y;
    double z;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", x, y, z);
    }
}
