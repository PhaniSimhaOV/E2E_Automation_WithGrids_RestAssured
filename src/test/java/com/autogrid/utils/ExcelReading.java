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
	private static final String FILE_PATH = "src/test/resources/config/ElementLocators.xlsx"; // Update with actual path

	/**
	 * Fetches the locator details (type and value) for a given element from the
	 * specified feature sheet.
	 *
	 * @param sheetName   Name of the sheet (feature name)
	 * @param elementName Name of the UI element
	 * @return Map containing locator type and value
	 * @throws IOException If an error occurs while reading the Excel file
	 */
	public static Map<String, String> getLocator(String sheetName, String elementName) throws IOException {
		Map<String, String> locatorData = new HashMap<>();
		try (FileInputStream fis = new FileInputStream(FILE_PATH); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IOException("Sheet " + sheetName + " not found in the Excel file");
			}

			DataFormatter formatter = new DataFormatter();
			for (Row row : sheet) {
				if (row.getCell(0) != null && row.getCell(0).getStringCellValue().equalsIgnoreCase(elementName)) {
					locatorData.put("type", row.getCell(1).getStringCellValue().trim());
					locatorData.put("value", formatter.formatCellValue(row.getCell(2)).trim());
					break;
				}
			}
		}
		if (locatorData.isEmpty()) {
			throw new IOException("Locator for element '" + elementName + "' not found in sheet '" + sheetName + "'");
		}
		return locatorData;
	}
	
	public static String getLocatorValue(String sheetName, String elementName) throws IOException {
	    Map<String, String> locatorData = getLocator(sheetName, elementName); // Fetch locator map
	    return locatorData.get("value"); // Return only the XPath/CSS value
	}

	// Existing method to read general data from any Excel sheet
	public static List<Map<String, String>> getAllDataFromExcel(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		DataFormatter formatter = new DataFormatter();

		List<Map<String, String>> allData = new ArrayList<>();
		Row headerRow = sheet.getRow(0);

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row dataRow = sheet.getRow(rowIndex);
			if (dataRow == null)
				continue;

			Map<String, String> rowData = new HashMap<>();
			for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
				String key = headerRow.getCell(cellIndex).getStringCellValue();
				Cell cell = dataRow.getCell(cellIndex);
				String value = cell != null ? formatter.formatCellValue(cell) : "";
				rowData.put(key, value);
			}
			allData.add(rowData);
		}

		workbook.close();
		fis.close();
		return allData;
	}
}