package study.rnTest.service.xml;

import study.rnTest.entity.point.Point;
import study.rnTest.entity.point.PointsPair;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public abstract class PointsParser {
    public List<PointsPair> parsePoints(NodeList pointsPairsNodesFromXml) {
        List<PointsPair> result = new ArrayList<>();

        for (int i = 0; i < pointsPairsNodesFromXml.getLength(); i++) {
            Node pair = pointsPairsNodesFromXml.item(i);

            if (pair.getNodeType() != Node.TEXT_NODE) {
                result.add(parsePointsPair(pair));
            }
        }

        return result;
    }

    private PointsPair parsePointsPair(Node pairNode) {
        PointsPair pair = new PointsPair();

        NodeList pointsPairNode = pairNode.getChildNodes();
        int pointsCounter = 0;

        for (int j = 0; j < pointsPairNode.getLength(); j++) {
            if (pointsPairNode.item(j).getNodeType() != Node.TEXT_NODE) {
                Node pointNode = pointsPairNode.item(j);

                pointsCounter++;
                if (pointsCounter == 1) {
                    pair.setFirstPoint(parsePoint(pointNode));
                } else if (pointsCounter == 2) {
                    pair.setSecondPoint(parsePoint(pointNode));
                } else {
                    throw new IllegalStateException("Лишняя точка в паре");
                }
            }
        }

        return pair;
    }

    protected abstract Point parsePoint(Node pointNode);
}
