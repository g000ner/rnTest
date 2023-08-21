package study.rntest.listener;

import study.rntest.entity.compute.ComputeResult;
import study.rntest.entity.point.PointsPair;
import study.rntest.exception.IncorrectFileException;
import study.rntest.exception.handler.IncorrectPointPairException;
import study.rntest.service.compute.ComputeService;
import study.rntest.service.xml.XmlPointsPairsParseService;
import study.rntest.model.computeResultsTable.ComputeResultsTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ComputeBtnListener implements ActionListener {
    private JFileChooser fileChooser;
    private ComputeResultsTable computeResultsTable;

    private XmlPointsPairsParseService pointsFileParseService;
    private ComputeService computeService;

    public ComputeBtnListener(JFileChooser fileChooser, ComputeResultsTable computeResultsTable) {
        this.fileChooser = fileChooser;
        this.pointsFileParseService = XmlPointsPairsParseService.getInstance();
        this.computeService = ComputeService.getInstance();
        this.computeResultsTable = computeResultsTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            pointsFileParseService.setXml(fileChooser.getSelectedFile());


            List<PointsPair> pointsPairs = pointsFileParseService.parsePoints();
            List<ComputeResult> resultRows = computeService.computeDistanceBetweenPointsInPairs(pointsPairs);

            Object[][] newTableData = new Object[resultRows.size()][];
            for (int i = 0; i < resultRows.size(); i++) {
                newTableData[i] = resultRows.get(i).toStringArray();
            }

            computeResultsTable.updateTableData(newTableData);
        } catch (IncorrectFileException | IncorrectPointPairException ex) {
            throw new RuntimeException(ex);
        }
    }
}
