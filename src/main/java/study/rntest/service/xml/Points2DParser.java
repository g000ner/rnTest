package study.rntest.service.xml;

import study.rntest.entity.point.Point;
import study.rntest.entity.point.Point2D;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import study.rntest.exception.PointException;


public class Points2DParser implements PointsParser {
    public Point parsePoint(Node pointNode) throws PointException {
        Point2D point = new Point2D();
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
                if (coordinatesCount > 2) {
                    throw new PointException("Лишняя координата в точке");
                }

                if (coordinateName.equalsIgnoreCase("x")) {
                    point.setX(coordinate);
                } else if (coordinateName.equalsIgnoreCase("y")) {
                    point.setY(coordinate);
                } else {
                    throw new PointException("Неизвестная координата в точке");
                }
            }
        }

        return point;
    }
}
