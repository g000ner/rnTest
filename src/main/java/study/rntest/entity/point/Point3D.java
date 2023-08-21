package study.rntest.entity.point;

public class Point3D extends Point2D {
    double z;

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
