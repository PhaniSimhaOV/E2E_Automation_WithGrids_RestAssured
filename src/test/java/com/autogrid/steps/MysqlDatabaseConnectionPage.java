package com.autogrid.steps;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MysqlDatabaseConnectionPage {

	private Connection connection;

	public MysqlDatabaseConnectionPage(Connection connection) {
		this.connection = connection;
	}

	public String fetchDataAndSaveToExcel(List<String> queries, List<List<Object>> parametersList, String filePath)
			throws SQLException, IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			Sheet sheet = workbook.createSheet("Query Results");

			int rowCount = 0;

			// Execute each query
			for (int i = 0; i < queries.size(); i++) {
				String query = queries.get(i);
				List<Object> parameters = parametersList.get(i);

				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					// Set parameters for the current query
					for (int j = 0; j < parameters.size(); j++) {
						preparedStatement.setObject(j + 1, parameters.get(j));
					}

					try (ResultSet resultSet = preparedStatement.executeQuery()) {
						ResultSetMetaData metaData = resultSet.getMetaData();
						int columnCount = metaData.getColumnCount();

						// Write header row if it is the first query
						if (rowCount == 0) {
							Row headerRow = sheet.createRow(rowCount++);
							for (int col = 1; col <= columnCount; col++) {
								headerRow.createCell(col - 1).setCellValue(metaData.getColumnName(col));
							}
						}

						// Write data rows
						while (resultSet.next()) {
							Row dataRow = sheet.createRow(rowCount++);
							for (int col = 1; col <= columnCount; col++) {
								dataRow.createCell(col - 1).setCellValue(resultSet.getString(col));
							}
						}
					}
				}
			}

			// Save workbook to file
			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
				workbook.write(fileOut);
			}
		}

		return "Data successfully fetched and saved to file: " + filePath;
	}
}
