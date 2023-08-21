package study.rntest.service.excel;

import study.rntest.entity.compute.ComputeResultLog;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

public class ExcelService {

    private Workbook currWorkbook;
    private Path exportFilesDirectory = Paths.get("");

    private static ExcelService instance = null;

    private ExcelService() {

    }

    public static ExcelService getInstance() {
        if (instance == null) {
            instance = new ExcelService();
        }

        return instance;
    }

    public void writeComputeResultLogsToExcel(List<ComputeResultLog> resultLogs) throws IOException {
        currWorkbook = new HSSFWorkbook();
        Sheet sheet = currWorkbook.createSheet("Логи расчетов из БД");

        String[] columnNames = {
                "id",
                "run_id",
                "compute_start_time",
                "compute_end_time",
                "first_point_descriptor",
                "second_point_descriptor",
                "compute_type",
                "distance"
        };
        createAndFillHeaderCells(sheet, columnNames);

        HSSFCellStyle cellsStyle = (HSSFCellStyle) currWorkbook.createCellStyle();

        cellsStyle.setBorderBottom(BorderStyle.DOUBLE);
        cellsStyle.setBorderLeft(BorderStyle.DOUBLE);
        cellsStyle.setBorderRight(BorderStyle.DOUBLE);

        Row row;
        for (int i = 0; i < resultLogs.size(); i++) {
            ComputeResultLog logRow = resultLogs.get(i);
            row = sheet.createRow(i + 1);

            createAndFillCellsFromTheLogRow(row, logRow, cellsStyle);
        }

        String fileName = String.format("%s-compute-log.xls", LocalDateTime.now().getNano());

        currWorkbook.write(Files.newOutputStream(Paths.get(fileName)));
        currWorkbook.close();
    }

    public Path getExportFilesDirectory() {
        return exportFilesDirectory;
    }

    public void setExportFilesDirectory(Path exportFilesDirectory) {
        this.exportFilesDirectory = exportFilesDirectory;
    }

    private void createAndFillCellsFromTheLogRow(Row row, ComputeResultLog logRow, HSSFCellStyle cellsStyle) {

        Cell idCell = row.createCell(0);
        idCell.setCellValue(logRow.getComputeId().toString());
        idCell.setCellStyle(cellsStyle);

        Cell runIdCell = row.createCell(1);
        runIdCell.setCellValue(logRow.getRunId().toString());
        runIdCell.setCellStyle(cellsStyle);

        Cell computeStartTimeCell = row.createCell(2);
        computeStartTimeCell.setCellValue(logRow.getComputeStartTime().toString());
        computeStartTimeCell.setCellStyle(cellsStyle);

        Cell computeEndTimeCell = row.createCell(3);
        computeEndTimeCell.setCellValue(logRow.getComputeEndTime().toString());
        computeEndTimeCell.setCellStyle(cellsStyle);

        Cell firstPointDescrCell = row.createCell(4);
        firstPointDescrCell.setCellValue(logRow.getFirstPointDescriptor());
        firstPointDescrCell.setCellStyle(cellsStyle);

        Cell secondPointDescrCell = row.createCell(5);
        secondPointDescrCell.setCellValue(logRow.getSecondPointDescriptor());
        secondPointDescrCell.setCellStyle(cellsStyle);

        Cell computeTypeCell = row.createCell(6);
        computeTypeCell.setCellValue(logRow.getComputeType().name());
        computeTypeCell.setCellStyle(cellsStyle);

        Cell distanceCell = row.createCell(7);
        distanceCell.setCellValue(logRow.getDistance());
        distanceCell.setCellStyle(cellsStyle);
    }

    private void createAndFillHeaderCells(Sheet sheet, String[] columnNames) {
        HSSFCellStyle cellsStyle = (HSSFCellStyle) currWorkbook.createCellStyle();
        cellsStyle.setBorderBottom(BorderStyle.THICK);
        cellsStyle.setBorderTop(BorderStyle.THICK);
        cellsStyle.setBorderLeft(BorderStyle.THICK);
        cellsStyle.setBorderRight(BorderStyle.THICK);

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columnNames.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cellsStyle);
        }
    }
}
