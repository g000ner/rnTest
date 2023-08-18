package study.rnTest.model.logResultsTable;

import javax.swing.*;

public class LogResultsTable {
    private JTable table;
    private LogResultsTableModel tableModel;

    public LogResultsTable() {
        tableModel = new LogResultsTableModel();
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
    }

    public JTable getTable() {
        return table;
    }

    public void updateTableData(Object[][] newData) {
        tableModel.updateTableData(newData);
    }

}
