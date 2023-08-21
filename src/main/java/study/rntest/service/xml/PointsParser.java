package study.rntest.service.xml;

import study.rntest.entity.point.Point;
import org.w3c.dom.Node;
import study.rntest.exception.PointException;

public interface PointsParser {

     Point parsePoint(Node pointNode) throws PointException;
}
