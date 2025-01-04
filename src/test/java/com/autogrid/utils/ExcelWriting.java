package com.autogrid.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriting {

    /**
     * Adds a new column to the Excel sheet with the specified column name.
     *
     * @param filePath   Path to the Excel file
     * @param sheetName  Name of the sheet where the column should be added
     * @param columnName Name of the new column to add
     * @throws IOException If an I/O error occurs
     */
	public static void addColumnToSheet(String filePath, String sheetName, String columnName) throws IOException {
	    try (FileInputStream fis = new FileInputStream(new File(filePath));
	         Workbook workbook = new XSSFWorkbook(fis)) {

	        Sheet sheet = workbook.getSheet(sheetName);

	        if (sheet == null) {
	            throw new IllegalArgumentException("Sheet with name " + sheetName + " not found in " + filePath);
	        }

	        // Get the first row to add the column header
	        Row headerRow = sheet.getRow(0);
	        if (headerRow == null) {
	            headerRow = sheet.createRow(0); // Create a new header row if none exists
	        }

	        // Check if column already exists
	        int newColumnIndex = headerRow.getLastCellNum();
	        for (Cell cell : headerRow) {
	            if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
	                return; // Column already exists
	            }
	        }

	        // Add the new column header
	        Cell newHeaderCell = headerRow.createCell(newColumnIndex);
	        newHeaderCell.setCellValue(columnName);

	        // Save changes to the Excel file
	        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
	            workbook.write(fos);
	        }
	    }
	}


    /**
     * Updates a specific cell in the Excel sheet based on the row index and column name.
     *
     * @param filePath   Path to the Excel file
     * @param sheetName  Name of the sheet where the cell is located
     * @param rowIndex   Index of the row (0-based)
     * @param columnName Name of the column to update
     * @param value      Value to set in the cell
     * @throws IOException If an I/O error occurs
     */
	public static void updateCell(String filePath, String sheetName, int rowIndex, String columnName, String value) throws IOException {
	    try (FileInputStream fis = new FileInputStream(new File(filePath));
	         Workbook workbook = new XSSFWorkbook(fis)) {

	        Sheet sheet = workbook.getSheet(sheetName);

	        if (sheet == null) {
	            throw new IllegalArgumentException("Sheet with name " + sheetName + " not found in " + filePath);
	        }

	        // Get the header row to find the column index
	        Row headerRow = sheet.getRow(0);
	        if (headerRow == null) {
	            throw new IllegalArgumentException("Header row is missing in sheet " + sheetName);
	        }

	        int columnIndex = -1;
	        for (Cell cell : headerRow) {
	            if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
	                columnIndex = cell.getColumnIndex();
	                break;
	            }
	        }

	        if (columnIndex == -1) {
	            throw new IllegalArgumentException("Column " + columnName + " not found in sheet " + sheetName);
	        }

	        // Get or create the row to update
	        Row rowToUpdate = sheet.getRow(rowIndex + 1); // Add 1 to account for the header row
	        if (rowToUpdate == null) {
	            rowToUpdate = sheet.createRow(rowIndex + 1);
	        }

	        // Get or create the cell to update
	        Cell cellToUpdate = rowToUpdate.getCell(columnIndex);
	        if (cellToUpdate == null) {
	            cellToUpdate = rowToUpdate.createCell(columnIndex);
	        }

	        // Set the value in the cell
	        cellToUpdate.setCellValue(value);

	        // Save changes to the Excel file
	        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
	            workbook.write(fos);
	        }
	    }
	  }
	}
