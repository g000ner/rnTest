package study.rnTest.service.xml;

import study.rnTest.entity.point.Point;
import study.rnTest.entity.point.Point3D;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Points3DParser extends PointsParser {
    @Override
    protected Point parsePoint(Node pointNode) {
        Point3D point = new Point3D();
        int coordinatesCount = 0;

        NodeList coordinates = pointNode.getChildNodes();
        for (int k = 0; k < coordinates.getLength(); k++) {
            if (coordinates.item(k).getNodeType() != Node.TEXT_NODE) {
                Node coordinateNode = coordinates.item(k);
                String coordinateName = coordinateNode.getNodeName();
                double coordinate = Double.parseDouble(coordinateNode.getTextContent());

                coordinatesCount++;
                if (coordinatesCount > 3) {
                    throw new IllegalStateException("Лишняя координата в точке");
                }

                if (coordinateName.equalsIgnoreCase("x")) {
                    point.setX(coordinate);
                } else if (coordinateName.equalsIgnoreCase("y")) {
                    point.setY(coordinate);
                } else if (coordinateName.equalsIgnoreCase("z")) {
                    point.setZ(coordinate);
                } else {
                    throw new IllegalStateException("Лишняя координата в точке");
                }
            }
        }

        return point;
    }
}
