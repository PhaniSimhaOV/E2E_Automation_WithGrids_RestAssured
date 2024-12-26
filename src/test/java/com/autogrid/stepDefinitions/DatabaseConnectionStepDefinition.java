package com.autogrid.stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseConnectionStepDefinition {

	private Connection connection;
	private ResultSet resultSet;
	private String filePath = "C:\\Users\\ADMIN\\Downloads\\output.xlsx"; // Output Excel file path

	@Given("User connects to MySQL Workbench database")
	public void user_connects_to_mysql_workbench_database() {
		try {
			// Load database credentials from properties file
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(
					"D:\\E2E_Automation_WithGrids_RestAssured\\src\\test\\resources\\config\\project.properties");
			properties.load(fis);

			String dbUrl = properties.getProperty("db.url");
			String dbUsername = properties.getProperty("db.username");
			String dbPassword = properties.getProperty("db.password");

			// Connect to the database
			connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			System.out.println("Database connection successful!");
		} catch (IOException e) {
			System.err.println("Error loading properties file: " + e.getMessage());
			e.printStackTrace();
			Assert.fail("Failed to load properties file.");
		} catch (SQLException e) {
			System.err.println("Error connecting to the Database: " + e.getMessage());
			e.printStackTrace();
			Assert.fail("Database connection failed.");
		}
	}

	@When("User executes a query to fetch data")
	public void user_executes_query_to_fetch_data() {
		System.out.println("Query prepared to fetch data.");
	}

	@When("User tries to save the fetched data into the file")
	public void user_tries_to_save_the_fetched_data_into_the_file() {
		try {
			// Queries to fetch all records from each table
			List<String> queries = Arrays.asList("SELECT \r\n" + "    CASE \r\n"
					+ "        WHEN da.customer_type IS NOT NULL THEN da.customer_type \r\n"
					+ "        ELSE dc.customer_type \r\n" + "    END AS custType,\r\n"
					+ "    CONCAT(dl.first_name, \" \", dl.last_name) AS custName,\r\n"
					+ "    dl.phone AS mobileNo,\r\n" + "    dl.email AS email,\r\n" + "    CASE \r\n"
					+ "        WHEN da.gender_id IS NOT NULL THEN (SELECT value FROM dms_master_common WHERE id = da.gender_id)\r\n"
					+ "        ELSE (SELECT value FROM dms_master_common WHERE id = dc.gender_id)\r\n"
					+ "    END AS gender,\r\n" + "    dad.state AS location,\r\n" + "    dad.state AS state,\r\n"
					+ "    dad.district,\r\n" + "    dad.city,\r\n" + "    dad.pincode,\r\n" + "    dad.village,\r\n"
					+ "    CONCAT(dad.house_no, \" \", dad.street) AS address,\r\n"
					+ "    dl.enquiry_segment AS enquiryType,\r\n" + "    ds.name AS source,\r\n"
					+ "    dss.sub_source AS subSource,\r\n" + "    dl.enquiry_category,\r\n" + "    dl.model,\r\n"
					+ "    dlp.fuel,\r\n" + "    dlp.variant,\r\n" + "    dlp.color,\r\n"
					+ "    dlp.interior_color,\r\n" + "    CASE \r\n"
					+ "        WHEN dfd.finance_type IS NOT NULL THEN \"Y\" \r\n" + "        ELSE \"N\" \r\n"
					+ "    END AS financeReq,\r\n" + "    dfd.finance_company,\r\n" + "    dfd.loan_amount,\r\n"
					+ "    dl.sales_consultant,\r\n" + "    del.scrap_certificate_flag AS certificateOfDeposit,\r\n"
					+ "    (SELECT value FROM dms_master_common WHERE id = del.expected_plan_id) AS expectedPlan,\r\n"
					+ "    del.immediate_booking_flag,\r\n"
					+ "    (SELECT value FROM dms_master_common WHERE id = del.visited_with_id) AS visitedWith,\r\n"
					+ "    (SELECT value FROM dms_master_common WHERE id = dfd.finance_option_id) AS financeOption,\r\n"
					+ "    dwt.task_updated_time AS nextFollowUpTime,\r\n"
					+ "    dwt.customer_remarks AS followUpRemarks,\r\n"
					+ "    dwt.employee_remarks AS managerRemarks,\r\n" + "    dl.enquiry_category AS enquiryType\r\n"
					+ "FROM \r\n" + "    dms_lead dl\r\n" + "LEFT JOIN \r\n"
					+ "    dms_lead_stage_ref dlsr ON dlsr.lead_id = dl.id\r\n" + "LEFT JOIN \r\n"
					+ "    dms_master_lead_stage dmls ON dlsr.stage_id = dmls.id\r\n" + "LEFT JOIN \r\n"
					+ "    dms_account da ON da.id = dl.dms_account_id\r\n" + "LEFT JOIN \r\n"
					+ "    dms_contact dc ON dc.id = dl.dms_contact_id\r\n" + "LEFT JOIN \r\n"
					+ "    dms_address dad ON dad.dms_lead_id = dl.id AND address_type = 'Communication'\r\n"
					+ "LEFT JOIN \r\n" + "    dms_source_of_enquiries ds ON ds.id = dl.source_of_enquiry\r\n"
					+ "LEFT JOIN \r\n" + "    sub_source dss ON dss.id = dl.sub_source_id\r\n" + "LEFT JOIN \r\n"
					+ "    dms_lead_product dlp ON dlp.lead_id = dl.id AND primary_flag IS TRUE\r\n" + "LEFT JOIN \r\n"
					+ "    dms_finance_details dfd ON dfd.lead_id = dl.id AND finance_type LIKE 'in house'\r\n"
					+ "LEFT JOIN \r\n" + "    dms_enquiry_lead del ON del.id = dl.dms_enquiry_id\r\n" + "LEFT JOIN \r\n"
					+ "    dms_workflow_task dwt ON dl.id = dwt.entity_id AND dwt.task_name = 'Enquiry Follow Up'\r\n"
					+ "WHERE \r\n" + "    dmls.lead_stage_key = 'ENQUIRY' \r\n" + "    AND dl.organization_id = 16\r\n"
					+ "LIMIT 50;\r\n" + "",
					"SELECT \r\n" + "    dl.phone,\r\n" + "    db.created_date AS bookingDate,\r\n"
							+ "    db.mode_of_payment AS modeOfPayment,\r\n" + "    dal.vinno AS VinNo,\r\n"
							+ "    dat.document_number AS panNo,\r\n"
							+ "    CONCAT(dl.first_name, ' ', dl.last_name) AS regName,\r\n"
							+ "    dad.state AS state,\r\n" + "    dad.district,\r\n" + "    dad.city,\r\n"
							+ "    dad.pincode,\r\n" + "    dad.village,\r\n"
							+ "    CONCAT(dad.house_no, ' ', dad.street) AS address,\r\n"
							+ "    dop.ex_showroom_price,\r\n" + "    dop.life_tax AS RTOamount,\r\n"
							+ "    JSON_ARRAYAGG(JSON_OBJECT('receiptDate',\r\n"
							+ "                    dbm.receipt_date,\r\n" + "                    'amount',\r\n"
							+ "                    dbm.amount,\r\n" + "                    'modeOfPayment',\r\n"
							+ "                    dbm.payment_name)) AS booking_details\r\n" + "FROM\r\n"
							+ "    dms_lead dl\r\n" + "    LEFT JOIN\r\n"
							+ "    dms_lead_stage_ref dlsr ON dlsr.lead_id = dl.id\r\n"
							+ "    LEFT JOIN dms_master_lead_stage dmls ON dlsr.stage_id = dmls.id\r\n"
							+ "        LEFT JOIN\r\n" + "    dms_received_booking_amount dbm ON dbm.lead_id = dl.id\r\n"
							+ "        LEFT JOIN\r\n" + "    dms_booking db ON db.lead_id = dl.id\r\n"
							+ "        LEFT JOIN\r\n" + "    dms_allotment dal ON dal.lead_id = dl.id\r\n"
							+ "        LEFT JOIN\r\n" + "    dms_attachment dat ON dat.dms_lead_id = dl.id\r\n"
							+ "        AND dat.document_type LIKE '%pan%'\r\n" + "        LEFT JOIN\r\n"
							+ "    dms_address dad ON dad.dms_lead_id = dl.id\r\n"
							+ "        AND dad.address_type = 'Communication'\r\n" + "        LEFT JOIN\r\n"
							+ "    dms_onroad_price dop ON dop.lead_id = dl.id\r\n" + "WHERE\r\n"
							+ "    dl.organization_id = '16'\r\n" + "        AND dmls.lead_stage_key='BOOKING'\r\n"
							+ "GROUP BY dl.id , db.created_date , db.mode_of_payment , dal.vinno , dat.document_number , dad.state , dad.district , dad.city , dad.pincode , dad.village , dad.house_no , dad.street LIMIT 50;\r\n"
							+ "",
					"select DISTINCT * from dms_lead where lead_status='BOOKINGCOMPLETED'",
					"select DISTINCT * from dms_lead where lead_status='INVOICECOMPLETED'");

			// Descriptive sheet names for each query
			List<String> sheetNames = Arrays.asList("Enquiry Leads", "Booking Leads", "Invoice Leads",
					"ExWarranty & SOT Leads");

			// Validate that the number of queries matches the number of sheet names
			if (sheetNames.size() != queries.size()) {
				throw new IllegalArgumentException("Mismatch between the number of queries and sheet names.");
			}

			saveQueryResultsToExcel(queries, sheetNames, filePath);
			System.out.println("Data successfully saved to Excel file: " + filePath);
		} catch (SQLException | IOException e) {
			System.err.println("Error processing queries: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void saveQueryResultsToExcel(List<String> queries, List<String> sheetNames, String filePath)
			throws SQLException, IOException {
		try (Workbook workbook = new XSSFWorkbook()) {
			for (int i = 0; i < queries.size(); i++) {
				String query = queries.get(i);

				// Execute the query
				try (Statement statement = connection.createStatement()) {
					resultSet = statement.executeQuery(query);

					// Create a new sheet with the corresponding descriptive name
					Sheet sheet = workbook.createSheet(sheetNames.get(i));
					writeResultSetToSheet(resultSet, sheet);
				}
			}

			// Write the workbook to an Excel file
			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
				workbook.write(fileOut);
			}

			System.out.println("Data successfully saved to Excel file with descriptive sheet names: " + filePath);
		}
	}

	private void writeResultSetToSheet(ResultSet resultSet, Sheet sheet) throws SQLException {
		ResultSetMetaData metaData = resultSet.getMetaData();
		int columnCount = metaData.getColumnCount();

		// Write column headers
		Row headerRow = sheet.createRow(0);
		for (int i = 1; i <= columnCount; i++) {
			Cell cell = headerRow.createCell(i - 1);
			cell.setCellValue(metaData.getColumnLabel(i));
		}

		// Write data rows
		int rowIndex = 1;
		while (resultSet.next()) {
			Row dataRow = sheet.createRow(rowIndex++);
			for (int i = 1; i <= columnCount; i++) {
				Cell cell = dataRow.createCell(i - 1);
				cell.setCellValue(resultSet.getString(i));
			}
		}
	}

	@Then("User closes the database connection")
	public void user_closes_the_database_connection() {
		try {
			if (resultSet != null)
				resultSet.close();
			if (connection != null)
				connection.close();
			System.out.println("Database connection closed successfully.");
		} catch (SQLException e) {
			System.err.println("Error closing the database connection: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
