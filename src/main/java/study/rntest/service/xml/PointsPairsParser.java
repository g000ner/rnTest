package study.rntest.service.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import study.rntest.entity.point.Point;
import study.rntest.entity.point.PointsPair;
import study.rntest.exception.PointException;

import java.util.ArrayList;
import java.util.List;

public class PointsPairsParser {
    PointsParser pointsParser;

    public List<PointsPair> parsePointsPairs(NodeList pointsPairsNodesFromXml) throws PointException {
        List<PointsPair> result = new ArrayList<>();

        for (int i = 0; i < pointsPairsNodesFromXml.getLength(); i++) {
            Node pair = pointsPairsNodesFromXml.item(i);
            if (pair.getNodeName().equals("pair")) {
                result.add(parsePointsPair(pair));
            }
        }

        return result;
    }

    private PointsPair parsePointsPair(Node pairNode) throws PointException {
        PointsPair pair = new PointsPair();

        NodeList pointsPairNode = pairNode.getChildNodes();
        int pointsCounter = 0;

        for (int j = 0; j < pointsPairNode.getLength(); j++) {
            if (pointsPairNode.item(j).getNodeName().equals("point")) {
                Node pointNode = pointsPairNode.item(j);

                Point point = pointsParser.parsePoint(pointNode);
                pointsCounter++;
                if (pointsCounter == 1) {
                    pair.setFirstPoint(point);
                } else if (pointsCounter == 2) {
                    pair.setSecondPoint(point);
                } else {
                    throw new IllegalStateException("Лишняя точка в паре");
                }
            }
        }

        return pair;
    }

    public PointsParser getPointsParser() {
        return pointsParser;
    }

    public void setPointsParser(PointsParser pointsParser) {
        this.pointsParser = pointsParser;
    }
}
