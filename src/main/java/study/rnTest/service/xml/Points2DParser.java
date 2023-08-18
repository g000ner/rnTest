package study.rnTest.service.xml;

import study.rnTest.entity.point.Point;
import study.rnTest.entity.point.Point2D;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Points2DParser extends PointsParser {
    protected Point parsePoint(Node pointNode) {
        Point2D point = new Point2D();
        int coordinatesCount = 0;

        NodeList coordinates = pointNode.getChildNodes();
        for (int k = 0; k < coordinates.getLength(); k++) {
            if (coordinates.item(k).getNodeType() != Node.TEXT_NODE) {
                Node coordinateNode = coordinates.item(k);

                String coordinateName = coordinateNode.getNodeName();
                double coordinate = Double.parseDouble(coordinateNode.getTextContent());

                coordinatesCount++;
                if (coordinatesCount > 2) {
                    throw new IllegalStateException("Лишняя координата в точке");
                }

                if (coordinateName.equalsIgnoreCase("x")) {
                    point.setX(coordinate);
                } else if (coordinateName.equalsIgnoreCase("y")) {
                    point.setY(coordinate);
                } else {
                    throw new IllegalStateException("Неизвестная координата в точке");
                }
            }
        }

        return point;
    }
}
