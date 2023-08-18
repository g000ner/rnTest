package study.rnTest.actionListener;

import study.rnTest.entity.compute.ComputeResult;
import study.rnTest.entity.point.PointsPair;
import study.rnTest.service.compute.ComputeService;
import study.rnTest.service.xml.XmlPointsParseService;
import study.rnTest.model.computeResultsTable.ComputeResultsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ComputeBtnListener implements ActionListener {
    private JFileChooser fileChooser;
    private ComputeResultsTable computeResultsTable;

    private XmlPointsParseService pointsFileParseService;
    private ComputeService computeService;

    public ComputeBtnListener(JFileChooser fileChooser, ComputeResultsTable computeResultsTable) {
        this.fileChooser = fileChooser;
        this.pointsFileParseService = XmlPointsParseService.getInstance();
        this.computeService = ComputeService.getInstance();
        this.computeResultsTable = computeResultsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pointsFileParseService.setXml(fileChooser.getSelectedFile());
        List<PointsPair> pointsPairs = pointsFileParseService.parsePoints();
        List<ComputeResult> resultRows = computeService.computeDistanceBetweenPointsInPairs(pointsPairs);

        Object[][] newTableData = new Object[resultRows.size()][];
        for (int i = 0; i < resultRows.size(); i++) {
            newTableData[i] = resultRows.get(i).toStringArray();
        }

        computeResultsTable.updateTableData(newTableData);
    }
}
