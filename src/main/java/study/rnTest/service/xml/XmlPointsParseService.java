package study.rnTest.service.xml;

import study.rnTest.entity.point.PointsPair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import study.rnTest.exception.IncorrectFileException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlPointsParseService {
    private File xml;
    PointsParser pointsParser;

    private static XmlPointsParseService instance = null;

    private XmlPointsParseService() {
    }

    public static XmlPointsParseService getInstance() {
        if (instance == null) {
            instance = new XmlPointsParseService();
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
            String dimensionType = dimensionNode.getNodeValue();

            createNeededDimensionParserAndSetToField(dimensionType);
            result = pointsParser.parsePointsPairs(points);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private void createNeededDimensionParserAndSetToField(String dimensionType) throws IncorrectFileException {
        if (dimensionType.equals("2")) {
            // todo
            this.pointsParser = new Points2DParser();
        } else if (dimensionType.equals("3")){
            this.pointsParser = new Points3DParser();
        } else {
            throw new IncorrectFileException("Неверная размерность");
        }
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

    public PointsParser getPointsParser() {
        return pointsParser;
    }

    public void setPointsParser(PointsParser pointsParser) {
        this.pointsParser = pointsParser;
    }
}
