package study.rnTest.ui;

import study.rnTest.listener.*;
import study.rnTest.exception.handler.AppExceptionHandler;
import study.rnTest.model.computeResultsTable.ComputeResultsTable;
import study.rnTest.model.logResultsTable.LogResultsTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class AppWindow {

    private JFrame jFrame;

    private JFileChooser fileChooser;

    private JTabbedPane tabbedPane;

    private JPanel computePanel;
    private JPanel logsPanel;

    private JButton openFileBtn;
    private JButton computeBtn;
    private JButton downloadLogBtn;
    private JButton exportToExcelBtn;

    private JScrollPane computeResultsTableScrollPane;
    private ComputeResultsTable computeResultsTable;

    private JScrollPane logResultsTableScrollPane;
    private LogResultsTable logResultsTable;

    private JOptionPane optionPane;

    private AppExceptionHandler exceptionHandler;

    private JLabel selectedFileLabel;

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
        prepareExceptionHandler();
    }

    private void prepareComputePanel() {
        computePanel = new JPanel();
        prepareComputeResultsTable();

        prepareSelectedFileLabel();
        prepareComputeBtn();
        prepareFileChooser();
        prepareOpenFileBtn();

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
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("xml", "xml");
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(extensionFilter);

        fileChooser.addPropertyChangeListener(
                new FileChooserPropertyChangeListener(fileChooser, selectedFileLabel, computeBtn));
        fileChooser.setAcceptAllFileFilterUsed(false);

        ComputeBtnListener computeBtnListener = new ComputeBtnListener(fileChooser, computeResultsTable);
        computeBtn.addActionListener(computeBtnListener);
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
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(openFileBtn)
                                .addComponent(selectedFileLabel)
                        )
                .addComponent(computeBtn)
                .addComponent(computeResultsTableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(openFileBtn)
                                .addComponent(selectedFileLabel)
                        )
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
        computeBtn.setEnabled(false);
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

    private void prepareExceptionHandler() {
        optionPane = new JOptionPane();
        exceptionHandler = new AppExceptionHandler();
        exceptionHandler.setMessagePane(optionPane);
        exceptionHandler.setFrame(jFrame);

        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    }

    private void prepareSelectedFileLabel() {
        selectedFileLabel = new JLabel();
    }
}
