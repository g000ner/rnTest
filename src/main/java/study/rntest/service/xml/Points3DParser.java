package study.rntest.service.xml;

import study.rntest.entity.point.Point;
import study.rntest.entity.point.Point3D;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import study.rntest.exception.PointException;

public class Points3DParser implements PointsParser {
    @Override
    public Point parsePoint(Node pointNode) throws PointException {
        Point3D point = new Point3D();
        int coordinatesCount = 0;

        NodeList coordinates = pointNode.getChildNodes();
        for (int k = 0; k < coordinates.getLength(); k++) {
            if (coordinates.item(k).getNodeType() != Node.TEXT_NODE) {
                Node coordinateNode = coordinates.item(k);
                String coordinateName = coordinateNode.getNodeName();
                double coordinate = Double.parseDouble(coordinateNode.getTextContent());

                if (Double.isInfinite(coordinate)) {
                    throw new IllegalArgumentException("Ошибка чтения числа: infinite");
                }

                coordinatesCount++;
                if (coordinatesCount > 3) {
                    throw new PointException("Лишняя координата в точке");
                }

                if (coordinateName.equalsIgnoreCase("x")) {
                    point.setX(coordinate);
                } else if (coordinateName.equalsIgnoreCase("y")) {
                    point.setY(coordinate);
                } else if (coordinateName.equalsIgnoreCase("z")) {
                    point.setZ(coordinate);
                } else {
                    throw new PointException("Лишняя координата в точке");
                }
            }
        }

        return point;
    }
}
