package study.rntest.model.computeResultsTable;

import javax.swing.table.AbstractTableModel;

public class ComputeResultsTableModel extends AbstractTableModel {
    private final String[] columnNames;
    private Object[][] data;

    public ComputeResultsTableModel() {
        columnNames = new String[]{"Точка A", "Точка B", "Расстояние"};
        data = new Object[][]{};
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public void updateTableData(Object[][] newData) {
        this.data = newData;
        fireTableDataChanged();
    }
}
