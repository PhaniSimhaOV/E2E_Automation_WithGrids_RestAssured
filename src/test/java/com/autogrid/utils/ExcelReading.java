package com.autogrid.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReading {

	public static List<Map<String, String>> getAllDataFromExcel(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		List<Map<String, String>> allData = new ArrayList<>();
		Row headerRow = sheet.getRow(0); // Header row

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row dataRow = sheet.getRow(rowIndex);
			if (dataRow == null)
				continue;

			Map<String, String> rowData = new HashMap<>();
			for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
				String key = headerRow.getCell(cellIndex).getStringCellValue();
				Cell cell = dataRow.getCell(cellIndex);
				String value = cell != null ? cell.toString() : ""; // Handle null cells gracefully
				rowData.put(key, value);
			}
			allData.add(rowData);
		}

		workbook.close();
		fis.close();
		return allData;
	}
}