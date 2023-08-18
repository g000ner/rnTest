package study.rnTest.ui;

import study.rnTest.actionListener.ComputeBtnListener;
import study.rnTest.actionListener.DownloadLogBtnListener;
import study.rnTest.actionListener.ExportToExcelBtnListener;
import study.rnTest.actionListener.OpenFileBtnListener;
import study.rnTest.model.computeResultsTable.ComputeResultsTable;
import study.rnTest.model.logResultsTable.LogResultsTable;

import javax.swing.*;
import java.awt.*;

public class AppWindow {

    JFrame jFrame;

    JFileChooser fileChooser;

    JTabbedPane tabbedPane;

    JPanel computePanel;
    JPanel logsPanel;

    JButton openFileBtn;
    JButton computeBtn;
    JButton downloadLogBtn;
    JButton exportToExcelBtn;

    JScrollPane computeResultsTableScrollPane;
    ComputeResultsTable computeResultsTable;

    JScrollPane logResultsTableScrollPane;
    LogResultsTable logResultsTable;

    public AppWindow() {
        prepareGui();
    }

    public static void main(String[] args) {
        AppWindow ui = new AppWindow();
    }

    private void prepareGui() {
        prepareComputePanel();
        prepareLogsPanel();
        prepareTabbedPane();
        prepareFrame();
    }

    private void prepareComputePanel() {
        computePanel = new JPanel();
        prepareComputeResultsTable();

        prepareFileChooser();
        prepareOpenFileBtn();

        prepareComputeBtn();
        prepareComputePanelLayout();
    }

    private void prepareLogsPanel() {
        logsPanel = new JPanel();
        prepareLogResultsTable();

        prepareDownloadLogBtn();
        prepareExportToExcelBtn();
        prepareLogsPanelLayout();
    }

    private void prepareFileChooser() {
        fileChooser = new JFileChooser();
    }

    private void prepareOpenFileBtn() {
        openFileBtn = new JButton();

        OpenFileBtnListener openFileBtnListener = new OpenFileBtnListener(jFrame, fileChooser);
        openFileBtn.addActionListener(openFileBtnListener);

        openFileBtn.setText("Открыть файл");
        openFileBtn.setSize(new Dimension(100, 20));
    }

    private void prepareComputePanelLayout() {
        GroupLayout layout = new GroupLayout(computePanel);
        computePanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(openFileBtn)
                .addComponent(computeBtn)
                .addComponent(computeResultsTableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(openFileBtn)
                .addComponent(computeBtn)
                .addComponent(computeResultsTableScrollPane)

        );
    }

    private void prepareLogsPanelLayout() {
        GroupLayout layout = new GroupLayout(logsPanel);
        logsPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup()
                .addComponent(downloadLogBtn)
                .addComponent(exportToExcelBtn)
                .addComponent(logResultsTableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(downloadLogBtn)
                .addComponent(exportToExcelBtn)
                .addComponent(logResultsTableScrollPane)
        );
    }

    private void prepareComputeBtn() {
        computeBtn = new JButton();
        computeBtn.setText("Начать расчеты");
        computeBtn.setSize(new Dimension(100, 20));

        ComputeBtnListener computeBtnListener = new ComputeBtnListener(fileChooser, computeResultsTable);
        computeBtn.addActionListener(computeBtnListener);
    }

    private void prepareComputeResultsTable() {
        computeResultsTable = new ComputeResultsTable();
        computeResultsTableScrollPane = new JScrollPane(computeResultsTable.getTable());
    }

    private void prepareLogResultsTable() {
        logResultsTable = new LogResultsTable();
        logResultsTableScrollPane = new JScrollPane(logResultsTable.getTable());
    }

    private void prepareDownloadLogBtn() {
        downloadLogBtn = new JButton();
        downloadLogBtn.setText("Загрузить лог из БД");
        downloadLogBtn.setSize(new Dimension(100, 20));

        DownloadLogBtnListener downloadLogBtnListener = new DownloadLogBtnListener(logResultsTable);
        downloadLogBtn.addActionListener(downloadLogBtnListener);
    }

    private void prepareExportToExcelBtn() {
        exportToExcelBtn = new JButton();
        exportToExcelBtn.setText("Выгрузить лог в Excel");
        exportToExcelBtn.setSize(new Dimension(100, 20));

        ExportToExcelBtnListener exportToExcelBtnListener = new ExportToExcelBtnListener();
        exportToExcelBtn.addActionListener(exportToExcelBtnListener);
    }

    private void prepareTabbedPane() {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Рассчеты", computePanel);
        tabbedPane.addTab("Логи", logsPanel);
    }

    private void prepareFrame() {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jFrame.setSize(new Dimension(1200, 1200));
        jFrame.setLocationRelativeTo(null);

        jFrame.add(tabbedPane);
        jFrame.setVisible(true);
    }
}
