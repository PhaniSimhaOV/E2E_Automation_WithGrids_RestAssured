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
			List<String> queries = Arrays.asList("SELECT \r\n"
					+ "    CASE \r\n"
					+ "        WHEN da.customer_type IS NOT NULL THEN da.customer_type \r\n"
					+ "        ELSE dc.customer_type \r\n"
					+ "    END AS custType, concat(dl.first_name,\" \",dl.last_name) as custName, dl.phone as mobileNo, dl.email as email,\r\n"
					+ "    CASE \r\n"
					+ "        WHEN da.gender_id IS NOT NULL THEN (select value from dms_master_common where id=da.gender_id)\r\n"
					+ "        ELSE (select value from dms_master_common where id=dc.gender_id)\r\n"
					+ "    END AS gender,dad.state as location,\r\n"
					+ "    dad.state as state, dad.district, dad.city, dad.pincode , dad.village,concat(dad.house_no,\" \",dad.street) as address,\r\n"
					+ "    dl.enquiry_segment as enquiryType, ds.name as source,dss.sub_source as subSource, dl.enquiry_category, dl.model , dlp.fuel , dlp.variant,dlp.color,dlp.interior_color,\r\n"
					+ "    case when dfd.finance_type is not null then \"Y\" else \"N\" end as financeReq,dfd.finance_company,dfd.loan_amount,dl.sales_consultant,\r\n"
					+ "    del.scrap_certificate_flag as cirtificateOfDeposite,(select value from dms_master_common where id= del.expected_plan_id) as expectedPlan,\r\n"
					+ "    del.immediate_booking_flag,(select value from dms_master_common where id=del.visited_with_id) as visitedWith,\r\n"
					+ "    (select value from dms_master_common where id=dfd.finance_option_id) as finaceOption,dwt.task_updated_time as nextFollowUpTime,dwt.customer_remarks as followUpRemarks,dwt.employee_remarks as managerRemarks,dl.enquiry_category as enquiryType\r\n"
					+ "FROM dms_lead dl\r\n"
					+ "LEFT JOIN dms_lead_stage_ref dlsr ON dlsr.lead_id = dl.id\r\n"
					+ "LEFT JOIN dms_master_lead_stage dmls ON dlsr.stage_id = dmls.id\r\n"
					+ "LEFT JOIN dms_account da ON da.id = dl.dms_account_id\r\n"
					+ "LEFT JOIN dms_contact dc ON dc.id = dl.dms_contact_id\r\n"
					+ "LEFT JOIN dms_address dad ON dad.dms_lead_id = dl.id and address_type = 'Communication'\r\n"
					+ "LEFT JOIN dms_source_of_enquiries ds ON ds.id = dl.source_of_enquiry\r\n"
					+ "LEFT JOIN sub_source dss ON dss.id = dl.sub_source_id\r\n"
					+ "LEFT JOIN dms_lead_product dlp ON dlp.lead_id = dl.id and primary_flag is true\r\n"
					+ "LEFT JOIN dms_finance_details dfd ON dfd.lead_id = dl.id and finance_type like 'in house'\r\n"
					+ "LEFT JOIN dms_enquiry_lead del ON del.id = dl.dms_enquiry_id\r\n"
					+ "LEFT JOIN dms_workflow_task dwt ON dl.id = dwt.entity_id and dwt.task_name = 'Enquiry Follow Up'\r\n"
					+ "where dmls.lead_stage_key='ENQUIRY' and dl.organization_id=16 order by dl.id desc LIMIT 100 ;",
					"SELECT \r\n"
					+ "    dl.phone,\r\n"
					+ "    db.created_date AS bookingDate,\r\n"
					+ "    db.mode_of_payment AS modeOfPayment,\r\n"
					+ "    dal.vinno AS VinNo,\r\n"
					+ "    dat.document_number AS panNo,\r\n"
					+ "    CONCAT(dl.first_name, ' ', dl.last_name) AS regName,\r\n"
					+ "    dad.state AS state,\r\n"
					+ "    dad.district,\r\n"
					+ "    dad.city,\r\n"
					+ "    dad.pincode,\r\n"
					+ "    dad.village,\r\n"
					+ "    CONCAT(dad.house_no, ' ', dad.street) AS address,\r\n"
					+ "    dop.ex_showroom_price,\r\n"
					+ "    dop.life_tax AS RTOamount,\r\n"
					+ "    JSON_ARRAYAGG(\r\n"
					+ "        JSON_OBJECT(\r\n"
					+ "            'receiptDate', dbm.receipt_date,\r\n"
					+ "            'amount', dbm.amount,\r\n"
					+ "            'modeOfPayment', dbm.payment_name\r\n"
					+ "        )\r\n"
					+ "    ) AS booking_details\r\n"
					+ "FROM\r\n"
					+ "    dms_lead dl\r\n"
					+ "    LEFT JOIN dms_lead_stage_ref dlsr ON dlsr.lead_id = dl.id\r\n"
					+ "    LEFT JOIN dms_master_lead_stage dmls ON dlsr.stage_id = dmls.id\r\n"
					+ "    LEFT JOIN dms_received_booking_amount dbm ON dbm.lead_id = dl.id\r\n"
					+ "    LEFT JOIN dms_booking db ON db.lead_id = dl.id\r\n"
					+ "    LEFT JOIN dms_allotment dal ON dal.lead_id = dl.id\r\n"
					+ "    LEFT JOIN dms_attachment dat ON dat.dms_lead_id = dl.id\r\n"
					+ "        AND dat.document_type LIKE '%pan%'\r\n"
					+ "    LEFT JOIN dms_address dad ON dad.dms_lead_id = dl.id\r\n"
					+ "        AND dad.address_type = 'Communication'\r\n"
					+ "    LEFT JOIN dms_onroad_price dop ON dop.lead_id = dl.id\r\n"
					+ "WHERE\r\n"
					+ "    dl.organization_id = '16'\r\n"
					+ "    AND dmls.lead_stage_key = 'BOOKING'\r\n"
					+ "GROUP BY \r\n"
					+ "    dl.id, \r\n"
					+ "    db.created_date, \r\n"
					+ "    db.mode_of_payment, \r\n"
					+ "    dal.vinno, \r\n"
					+ "    dat.document_number, \r\n"
					+ "    dad.state, \r\n"
					+ "    dad.district, \r\n"
					+ "    dad.city, \r\n"
					+ "    dad.pincode, \r\n"
					+ "    dad.village, \r\n"
					+ "    dad.house_no, \r\n"
					+ "    dad.street order by db.created_date desc\r\n"
					+ "LIMIT 100;\r\n"
					+ "",
					"select dl.phone,di.invoice_date,\r\n"
					+ "(select value from dms_master_common where id=did.vehicle_usage_type_id) as vehicleUsageType,\r\n"
					+ "di.state_type,di.basic_price as exshowroomPrice,di.total_amount,\r\n"
					+ "dop.cash_discount,dop.promotional_offers,dop.discount,dop.foc_accessories,dop.additional_offer1,dop.additional_offer2,dop.insurance_discount,dop.accessories_discount\r\n"
					+ ",dop.exchange_offers,dop.corporate_offer,dop.special_scheme,dop.life_tax as roadTax,dop.life_tax as lifeTax, (dop.life_tax+dop.registration_charges) as rtoCharges,dop.registration_charges as otherCharges,dop.warranty_name,dop.warranty_amount,dop.handling_charges,dop.essential_kit,dop.tcs,dop.nps_scheme,dop.rural_offer,dop.fast_tag,\r\n"
					+ "(SELECT policy_name FROM `vehicle-management`.insurance_details where id=dli.insurance_type) as basicInsurance,dli.insurance_type_premium as basicInsuranceAmont,dli.add_on_insurance as additionalInsurance\r\n"
					+ "from dms_lead dl\r\n"
					+ "LEFT JOIN dms_lead_stage_ref dlsr ON dlsr.lead_id = dl.id\r\n"
					+ "LEFT JOIN dms_master_lead_stage dmls ON dlsr.stage_id = dmls.id\r\n"
					+ "LEFT JOIN dms_invoice di ON di.lead_id = dl.id\r\n"
					+ "LEFT JOIN dms_invoice_lead_details did ON did.invoice_id = di.id\r\n"
					+ "LEFT JOIN dms_lead_insurance dli ON dli.lead_id = di.lead_id\r\n"
					+ "LEFT JOIN dms_onroad_price dop ON dop.lead_id = dl.id\r\n"
					+ "where dmls.lead_stage_key='INVOICE' and dl.organization_id=16 LIMIT 100;",
					"select da.vinno,dd.etd_warranty_no as schemeNo,dd.warrantyname as schemeDescription,dd.warrantyamount,dad.state as  placeOfSupply,dl.sales_consultant as empName\r\n"
					+ "from dms_lead dl\r\n"
					+ "LEFT JOIN dms_lead_stage_ref dlsr ON dlsr.lead_id = dl.id\r\n"
					+ "LEFT JOIN dms_master_lead_stage dmls ON dlsr.stage_id = dmls.id\r\n"
					+ "LEFT JOIN dms_delivery dd ON dd.lead_id = dl.id\r\n"
					+ "LEFT JOIN dms_allotment da ON da.lead_id = dl.id\r\n"
					+ "LEFT JOIN dms_address dad ON dad.dms_lead_id = dl.id AND dad.address_type = 'Communication'\r\n"
					+ "where dmls.lead_stage_key='DELIVERY' and dl.organization_id=16 LIMIT 100\r\n"
					+ ";");

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
