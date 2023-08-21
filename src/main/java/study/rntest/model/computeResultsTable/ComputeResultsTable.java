package study.rntest.model.computeResultsTable;

import javax.swing.*;

public class ComputeResultsTable {
    private JTable table;
    private ComputeResultsTableModel tableModel;

    public ComputeResultsTable() {
        tableModel = new ComputeResultsTableModel();
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
