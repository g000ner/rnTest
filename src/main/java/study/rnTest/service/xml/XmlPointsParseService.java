package study.rnTest.service.xml;

import study.rnTest.entity.point.PointsPair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

            NodeList points = root.getChildNodes();
            String dimensionType = root.getAttributes().item(0).getTextContent();

            createNeededDimensionParserAndSetToField(dimensionType);

            result = pointsParser.parsePoints(points);

        // todo
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private void createNeededDimensionParserAndSetToField(String dimensionType) {
        if (dimensionType.equals("two_dimensional")) {
            this.pointsParser = new Points2DParser();
        } else {
            this.pointsParser = new Points3DParser();
        }
    }

    public File getXml() {
        return xml;
    }

    public void setXml(File xml) {
        this.xml = xml;
    }

    public PointsParser getPointsParser() {
        return pointsParser;
    }

    public void setPointsParser(PointsParser pointsParser) {
        this.pointsParser = pointsParser;
    }
}
