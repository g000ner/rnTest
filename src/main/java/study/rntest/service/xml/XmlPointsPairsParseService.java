package study.rntest.service.xml;

import study.rntest.entity.point.PointsPair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import study.rntest.exception.IncorrectFileException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

public class XmlPointsPairsParseService {
    private File xml;
    PointsPairsParser pointsPairsParser;

    private static XmlPointsPairsParseService instance = null;

    private XmlPointsPairsParseService() {
        pointsPairsParser = new PointsPairsParser();
    }

    public static XmlPointsPairsParseService getInstance() {
        if (instance == null) {
            instance = new XmlPointsPairsParseService();
        }

        return instance;
    }

    public List<PointsPair> parsePoints() {
        List<PointsPair> result;

        DocumentBuilder documentBuilder;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(xml);
            Node root = document.getDocumentElement();
            if (! root.getNodeName().equals("pointsPairs")) {
                throw new IncorrectFileException("Неверный корневой элемент. Необходимый: pointsPairs");
            }

            NodeList points = root.getChildNodes();

            Node dimensionNode = root.getAttributes().getNamedItem("dimension");
            if (dimensionNode == null) {
                throw new IncorrectFileException("В pointsPairs не указан dimension");
            }
            String dimension = dimensionNode.getNodeValue();
            setPointsParserInRelationToDimension(dimension);

            result = pointsPairsParser.parsePointsPairs(points);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public File getXml() {
        return xml;
    }

    public void setXml(File xml) throws IncorrectFileException {
        if (! xml.getName().endsWith(".xml")) {
            throw new IncorrectFileException("Неверное разрешение файла");
        }
        this.xml = xml;
    }

    private void setPointsParserInRelationToDimension(String dimension) throws IncorrectFileException {
        if (dimension.equals("2")) {
            this.pointsPairsParser.setPointsParser(new Points2DParser());
        } else if (dimension.equals("3")){
            this.pointsPairsParser.setPointsParser(new Points3DParser());
        } else {
            throw new IncorrectFileException("Неизвестная размерность");
        }
    }
}
