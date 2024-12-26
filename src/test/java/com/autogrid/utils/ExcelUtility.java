package com.autogrid.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {
    public static void saveTextToExcel(String text) {
        Workbook workbook = null;
        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;

        try {
            // Define the file
            File file = new File("extracted_data.xlsx");

            // If the file exists, open it for reading and writing
            if (file.exists()) {
                fileIn = new FileInputStream(file);
                workbook = new XSSFWorkbook(fileIn);
            } else {
                // If the file does not exist, create a new workbook
                workbook = new XSSFWorkbook();
            }

            // Check if the sheet exists, if not create it
            Sheet sheet = workbook.getSheet("CreatedLeadDataSheet");
            if (sheet == null) {
                sheet = workbook.createSheet("CreatedLeadDataSheet");
            }

            // Get the last row number to append data
            int lastRowNum = sheet.getLastRowNum();

            // Create a new row in the next available row
            Row row = sheet.createRow(lastRowNum + 1);
            Cell cell = row.createCell(0);
            cell.setCellValue(text);

            // Write the changes to the file
            fileOut = new FileOutputStream(file);
            workbook.write(fileOut);

            System.out.println("Text saved to Excel successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Ensure resources are closed to prevent resource leaks
            try {
                if (fileIn != null) {
                    fileIn.close(); // Close the input stream after use
                }
                if (fileOut != null) {
                    fileOut.close(); // Close the output stream after use
                }
                if (workbook != null) {
                    workbook.close(); // Close the workbook to free resources
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
