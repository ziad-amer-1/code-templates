package com.template.excel;

import jakarta.servlet.ServletOutputStream;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ExcelGenerator {

    public static <T> void generateExcel(ExcelInfoDTO<T> excelInfoDTO) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(excelInfoDTO.sheetName());
            Row header = sheet.createRow(0);

            for (int i = 0; i < excelInfoDTO.headers().length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(excelInfoDTO.headers()[i]);
                cell.setCellStyle(createHeaderCellStyle(workbook));
            }

            int rowNum = 1;
            for (T item : excelInfoDTO.items()) {
                Row row = sheet.createRow(rowNum++);
                fillItemRow(row, item, excelInfoDTO.headers().length, excelInfoDTO.propertyExtractors());
            }

            for (int i = 0; i < excelInfoDTO.headers().length; i++) {
                sheet.autoSizeColumn(i);
            }

            excelInfoDTO.response().setContentType("application/octet-stream");
            excelInfoDTO.response().setHeader("Content-Disposition", "attachment; filename=" + excelInfoDTO.sheetName() + ".xlsx");

            ServletOutputStream out = excelInfoDTO.response().getOutputStream();

            workbook.write(out);
            workbook.close();
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static <T> void fillItemRow(Row row, T item, int headerLength, PropertyValueExtractor<T> propertyExtractors) {
        for (int i = 0; i < headerLength; i++) {
            Object[] values = propertyExtractors.extract(item);
            row.createCell(i).setCellValue(values[i] != null ? values[i].toString() : "");
        }
    }

    private static CellStyle createHeaderCellStyle(XSSFWorkbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        headerStyle.setFont(font);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        return headerStyle;
    }

}