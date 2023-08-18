package study.rnTest.actionListener;

import study.rnTest.entity.compute.ComputeResultLog;
import study.rnTest.service.compute.ComputeResultsLogService;
import study.rnTest.service.excel.ExcelService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ExportToExcelBtnListener implements ActionListener {
    private ExcelService excelService;
    private ComputeResultsLogService computeResultsLogService;

    public ExportToExcelBtnListener() {
        excelService = ExcelService.getInstance();
        computeResultsLogService = ComputeResultsLogService.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        computeResultsLogService.openSession();
        List<ComputeResultLog> logs = computeResultsLogService.findAll();
        computeResultsLogService.closeSession();
        try {
            excelService.writeComputeResultLogsToExcel(logs);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
