package study.rnTest.service.xml;

import study.rnTest.entity.point.Point;
import study.rnTest.entity.point.PointsPair;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import study.rnTest.exception.IncorrectFileException;

import java.util.ArrayList;
import java.util.List;

public interface PointsParser {

     Point parsePoint(Node pointNode);
}
