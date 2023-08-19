package study.rnTest.listener;

import study.rnTest.entity.compute.ComputeResultLog;
import study.rnTest.service.compute.ComputeResultsLogService;
import study.rnTest.model.logResultsTable.LogResultsTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DownloadLogBtnListener implements ActionListener {
    LogResultsTable logResultsTable;
    ComputeResultsLogService computeResultsLogService;

    public DownloadLogBtnListener(LogResultsTable logResultsTable) {
        this.logResultsTable = logResultsTable;
        computeResultsLogService = ComputeResultsLogService.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        computeResultsLogService.openSession();
        List<ComputeResultLog> logs = computeResultsLogService.findAll();
        computeResultsLogService.closeSession();

        Object[][] newTableData = new Object[logs.size()][];
        for (int i = 0; i < logs.size(); i++) {
            ComputeResultLog log = logs.get(i);

            newTableData[i] = new String[]{
                    log.getComputeId().toString(),
                    log.getRunId().toString(),
                    log.getComputeStartTime().toString(),
                    log.getComputeEndTime().toString(),
                    log.getFirstPointDescriptor(),
                    log.getSecondPointDescriptor(),
                    String.valueOf(log.getDistance())
            };
        }

        logResultsTable.updateTableData(newTableData);
    }
}
