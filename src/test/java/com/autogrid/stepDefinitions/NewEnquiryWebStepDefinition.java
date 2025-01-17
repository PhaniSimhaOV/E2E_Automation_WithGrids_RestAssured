package com.autogrid.stepDefinitions;

import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.steps.InvoicePage;
import com.autogrid.steps.NewEnquiryWebPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.ExcelUtility;
import com.autogrid.utils.ExcelWriting;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class NewEnquiryWebStepDefinition {
	CommonActions commonActions;
	DMSLoginPage dMSLoginPage;
	NewEnquiryWebPage newenquirypage;
	InvoicePage invoicepage;
	DatabaseConnectionStepDefinition DatabaseConnectionStepDefinition;
	private Map<String, String> testData; // Stores data from Excel
	private List<Map<String, String>> allTestData; // List to store all data rows from Excel
	private int currentDataRowIndex = 0; // To keep track of the current row index

	public NewEnquiryWebStepDefinition() {
		WebDriver driver = LaunchDriver.getDriver();
		this.newenquirypage = new NewEnquiryWebPage(driver);
		PageFactory.initElements(driver, newenquirypage);
		this.dMSLoginPage = new DMSLoginPage(driver);
		PageFactory.initElements(driver, dMSLoginPage);
	}

	private void waitForVisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	private void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	@Given("User reads data from the Excel sheet regarding New Enquiry\\(Web) feature")
	public void user_reads_data_from_the_excel_sheet_regarding_new_enquiry_web_feature() throws IOException {
		String filePath = "src/test/resources/config/NewEnquiryWeb.xlsx";
		String sheetName = "Enquiry Lead Creation";

		// Fetch all data from the Excel sheet
		allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

		if (allTestData == null || allTestData.isEmpty()) {
			throw new RuntimeException("No data found in Excel sheet: " + sheetName);
		}
		System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");
	}
	@When("reads OTP from the database")
	public void reads_otp_from_the_database() {
		try {
			// Ensure testData is initialized
			if (testData == null) {
				testData = new HashMap<>(); // Initialize testData if it's null
			}

			// Load database credentials from properties file
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(
					"src/test/resources/config/project.properties");
			properties.load(fis);

			String dbUrl = properties.getProperty("db.url");
			String dbUsername = properties.getProperty("db.username");
			String dbPassword = properties.getProperty("db.password");
			int maxRetries = Integer.parseInt(properties.getProperty("db.retry.OTP.count")); // Default to 12 retries if
																								// not defined

			// Query to fetch the latest OTP
			String otpQuery = "select otp from dms_oem_dual_punching_job where org_id=16;";

			// Initialize variables for retry logic
			int retryCount = 0;
			boolean otpFound = false;

			while (retryCount < maxRetries && !otpFound) {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;

				try {
					// Establish database connection
					connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
					preparedStatement = connection.prepareStatement(otpQuery);

					// Execute query
					resultSet = preparedStatement.executeQuery();
					if (resultSet.next()) {
						String otp = resultSet.getString("otp");
						if (otp != null && !otp.trim().isEmpty()) {
							testData.put("otp", otp); // Store OTP in test data
							System.out.println("Fetched OTP from database: " + otp);
							otpFound = true; // Exit loop
						}
					}

					if (!otpFound) {
						System.out.println("No OTP found. Retrying in 5 seconds...");
						Thread.sleep(5000); // Wait for 5 seconds before retrying
						retryCount++;
					}
				} catch (Exception e) {
					System.err.println("Error during database query execution: " + e.getMessage());
					throw new RuntimeException(e);
				} finally {
					// Close database resources
					if (resultSet != null)
						resultSet.close();
					if (preparedStatement != null)
						preparedStatement.close();
					if (connection != null)
						connection.close();
				}
			}

			// If OTP is not found after max retries, throw an exception
			if (!otpFound) {
				throw new RuntimeException("No OTP found in the database after maximum retries.");
			}

		} catch (Exception e) {
			System.err.println("Error while fetching OTP from the database: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@When("enters the OTP into the OTP field")
	public void enters_the_otp_into_the_otp_field() throws Throwable {
		try {
			// Fetch the OTP from test data
			String otp = testData.get("otp");
			if (otp == null || otp.isEmpty()) {
				throw new RuntimeException("OTP is not available in test data.");
			}

			// Wait for visibility of OTP field and enter the OTP
			waitForVisibilityOfElement(dMSLoginPage.getEnterOTP());
			dMSLoginPage.enterOTP(otp);
			System.out.println("Entered OTP into the OTP field: " + otp);
		} catch (Exception e) {
			System.err.println("Error while entering OTP into the OTP field: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
	@When("User processes the New Enquiry\\(Web) for all rows from the Excel sheet of sheet Name Enquiry Lead Creation")
	public void user_processes_the__new_enquiry_web_for_all_rows_from_the_excel_sheet_of_sheet_name_enquiry_lead_creation()
			throws Throwable {
		int passedCount = 0;
		int failedCount = 0;

		String filePath = "src/test/resources/config/NewEnquiryWeb.xlsx";
		String sheetName = "Enquiry Lead Creation";

		// Add a new column for error logging
		ExcelWriting.addColumnToSheet(filePath, sheetName, "Error Logs");

		for (int currentDataRowIndex = 0; currentDataRowIndex < allTestData.size(); currentDataRowIndex++) {
			System.out.println("\nProcessing Row: " + (currentDataRowIndex + 1));

			// Fetch and log current row data
			testData = allTestData.get(currentDataRowIndex);
			System.out.println("Current Test Data: " + testData);

			boolean rowExecutionPassed = true;

			try {
				// Reset application state for every row
				System.out.println("Refreshing the browser to reset the application state...");
				LaunchDriver.getDriver().navigate().refresh();

				// Execute all test steps for the current row
				executeTestStepsForWebEnquiryLeadGeneration();

				// Log success
				System.out.println("Row " + (currentDataRowIndex + 1) + " execution PASSED.");
				ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", "PASSED");
				passedCount++;
			} catch (Exception e) {
				// Handle row failure
				// Handle application state reset on failure
				try {
					System.out.println("Navigating to the application's base URL...");
					LaunchDriver.getDriver().navigate().refresh();
					executeTestStepsForWebEnquiryLeadGeneration();
				} catch (Exception navigationException) {
					System.err.println("Error while navigating to the base URL: " + navigationException.getMessage());
					navigationException.printStackTrace();
				}
				String errorMessage = "Row " + (currentDataRowIndex + 1) + " execution FAILED: " + e.getMessage();
				System.err.println(errorMessage);
				e.printStackTrace();
				ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", errorMessage);
				rowExecutionPassed = false;
				failedCount++;

				// Skip retry and move to the next row
				System.out.println("Skipping retry for Row " + (currentDataRowIndex + 1) + ". Moving to the next row.");
			} finally {
				if (rowExecutionPassed) {
					System.out.println("Row " + (currentDataRowIndex + 1) + " processed successfully.");
				} else {
					System.err.println("Row " + (currentDataRowIndex + 1) + " processing failed.");
				}
			}
		}

		// Summary after processing all rows
		System.out.println("\nExecution Summary:");
		System.out.println("Total Rows Processed: " + allTestData.size());
		System.out.println("Rows Passed: " + passedCount);
		System.out.println("Rows Failed: " + failedCount);
	}

	private void executeTestStepsForWebEnquiryLeadGeneration() throws Throwable {
		// Example: Call test methods for each step
		try {
			user_clicks_sales_menu_item();
			user_clicks_on_the_customer_enquiry_sub_menu_item();
			user_clicks_on_the_customer_enquiry_link();
			user_tries_to_clicks_on_walkin_enquiry_tab();
			user_clicks_on_new_button();
			user_tries_to_enters_mobile_number_which_not_registered();
			user_clicks_on_mobile_number_search_icon();
			i_enters_valid_data_in_email_id();
			i_selects_valid_data_in_cust_type();
			i_enters_valid_data_in_address();
			i_enters_valid_data_in_village();
			i_selects_valid_data_enquiry_sub_source();
			i_selects_valid_data_enquiry_category();
			i_selects_valid_data_in_model();
			i_selects_valid_data_fuel_type();
			i_selects_valid_data_in_variant();
			i_selects_valid_data_in_sub_variant();
			i_selects_valid_data_in_finance_req();
			i_selects_valid_data_in_ext_color();
			i_selects_valid_data_in_int_color();
			i_selects_valid_data_in_sales_consultant();
			i_selects_valid_data_in_certificate_of_deposit();
			i_selects_valid_data_in_expected_plan();
			i_selects_valid_data_in_visited_with_family();
			i_selects_valid_data_in_immediate_booking();
			user_clicks_on_pincode_search_icon_from_pin_field();
			user_tries_to_enter_pincode_in_pincode_field();
			user_tries_to_clicks_on_search_button_in_pincode_search_screen();
			user_tries_to_select_one_pincode_from_the_list();
			user_tries_to_clicks_on_add_selected_button_in_pincode_search_screen();
			user_clicks_on_save_button();
		} catch (Exception e) {
			throw new RuntimeException("Error during execution due to" + ": " + e.getMessage(), e);
		}
	}

	@When("User clicks Sales Menu Item")
	public void user_clicks_sales_menu_item() {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirypage.getSalesMenu());
			newenquirypage.clickSalesMenu();
			System.out.println("Sales Menu clicked.");
		} catch (Exception e) {
			System.err.println("Error during Sales Menu click: " + e.getMessage());
		}
	}

	@When("User clicks on the Customer Enquiry Sub Menu Item")
	public void user_clicks_on_the_customer_enquiry_sub_menu_item() {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirypage.getCustomerEnquirySubmenu());
			newenquirypage.clickCustomerEnquirySubmenu();
			System.out.println("Customer Enquiry Sub Menu Item clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Enquiry Sub Menu Item click: " + e.getMessage());
		}
	}

	@When("User clicks on the Customer Enquiry link")
	public void user_clicks_on_the_customer_enquiry_link() {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirypage.getCustomerEnquiryLink());
			newenquirypage.clickCustomerEnquiryLink();
			System.out.println("Customer Enquiry Link clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Enquiry Link click: " + e.getMessage());
		}
	}

	@When("User tries to clicks on Walkin Enquiry Tab")
	public void user_tries_to_clicks_on_walkin_enquiry_tab() {
		try {
			newenquirypage.interactWithIframeElement();
			waitForElementToBeClickable(newenquirypage.getWalkinEnquiryTab());
			newenquirypage.clickWalkinEnquiryTab();
			System.out.println("Walkin Enquiry Tab clicked.");
		} catch (Exception e) {
			System.err.println("Error during Walkin Enquiry Tab click: " + e.getMessage());
		}
	}

	@And("User clicks on New button")
	public void user_clicks_on_new_button() {
		try {
			Thread.sleep(3000);
			newenquirypage.interactWithIframeElement();
			waitForElementToBeClickable(newenquirypage.getNewEnquiry());
			newenquirypage.clickNewEnquiryButton();
			System.out.println("New Enquiry Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during New Enquiry Button click: " + e.getMessage());
		}
	}

	@When("User tries to enters mobile number Which Not Registered")
	public void user_tries_to_enters_mobile_number_which_not_registered() {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			Thread.sleep(8000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getMobileNumber());
				Thread.sleep(3000);
				newenquirypage.enterMobileNumber(testData.get("mobileNo"));
				System.out.println("Entered Mobile Number: " + testData.get("mobileNo"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Mobile Number: " + e.getMessage());
		}
	}

	@When("User clicks on mobile number search Icon")
	public void user_clicks_on_mobile_number_search_icon() {
		try {
			waitForElementToBeClickable(newenquirypage.getMobileSearchIcon());
			newenquirypage.clickMobileSearchIcon();
			System.out.println("Mobile number search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Mobile number search Icon click: " + e.getMessage());
		}
	}

	@When("I enters valid data in Email id")
	public void i_enters_valid_data_in_email_id() {
		try {
			Thread.sleep(5000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getEmail());
				newenquirypage.enterEmail(testData.get("email"));
				System.out.println("Entered Mobile Number: " + testData.get("email"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Email: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Cust. Type")
	public void i_selects_valid_data_in_cust_type() {
		try {
			if (testData != null) {
				// Step 1: Select Cust. Type
				waitForVisibilityOfElement(newenquirypage.getCustTypeField());
				String custType = ExcelUtility.getMappedValue(testData.get("custType"));
				newenquirypage.selectCustTypeField(custType);
				System.out.println("Selected Cust. Type: " + custType);

				// Step 2: Handle fields based on Cust. Type
				if ("Individual".equalsIgnoreCase(custType)) {
					// Select Gender
					waitForVisibilityOfElement(newenquirypage.getGenderField());
					String gender = ExcelUtility.getMappedValue(testData.get("gender"));
					if (gender == null || gender.isEmpty()) {
	                    // Select "Unknown" if Gender column is empty
	                    gender = "Unknown";
	                }
					newenquirypage.selectGender(gender.trim().toUpperCase());
					System.out.println("Selected Gender: " + gender.toUpperCase());

					// Enter Customer Name
					waitForVisibilityOfElement(newenquirypage.getCustName());
					String custName = testData.get("custName");
					newenquirypage.enterCustName(custName);
					System.out.println("Entered Customer Name: " + custName);
				} else if ("Government".equalsIgnoreCase(custType) || "Corporate".equalsIgnoreCase(custType)) {
					// Enter Company Name
					waitForVisibilityOfElement(newenquirypage.getCompanyName());
					String companyName = testData.get("companyName"); // Assuming the same key for simplicity
					newenquirypage.enterCompanyName(companyName);
					System.out.println("Entered Company Name: " + companyName);
				} else {
					System.out.println("Invalid Cust. Type: " + custType + ". No further processing required.");
				}
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during processing Customer Details: " + e.getMessage());
		}
	}

	@When("User clicks on Pincode Search Icon from PIN field")
	public void user_clicks_on_pincode_search_icon_from_pin_field() {
		try {
			waitForElementToBeClickable(newenquirypage.getPincodeSearchIcon());
			newenquirypage.clickPincodeSearchIcon();
			System.out.println("Pincode Search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Icon click: " + e.getMessage());
		}
	}

	@Then("User tries to enter Pincode in pincode field")
	public void user_tries_to_enter_pincode_in_pincode_field() {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithincodeSearchIframeElement();
			Thread.sleep(4000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getPincode());
				newenquirypage.enterPincode(testData.get("pincode"));
				System.out.println("Entered Pincode: " + testData.get("pincode"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Pincode: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on search button in Pincode search Screen")
	public void user_tries_to_clicks_on_search_button_in_pincode_search_screen() {
		try {
			waitForElementToBeClickable(newenquirypage.getPinCodeSearchButton());
			newenquirypage.clickPinCodeSearchButton();
			System.out.println("Pincode Search Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Button click: " + e.getMessage());
		}
	}

	@Then("User tries to select one pincode from the list")
	public void user_tries_to_select_one_pincode_from_the_list() {
		try {
			waitForElementToBeClickable(newenquirypage.getLocationSelection());
			newenquirypage.clickLocationSelection();
			System.out.println("Location Selection clicked.");
		} catch (Exception e) {
			System.err.println("Error during Location Selection click: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on Add Selected button in Pincode search Screen")
	public void user_tries_to_clicks_on_add_selected_button_in_pincode_search_screen() {
		try {
			waitForElementToBeClickable(newenquirypage.getAddSelectedButton());
			newenquirypage.clickAddSelectedButton();
			System.out.println("Add Selected button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Add Selected button click: " + e.getMessage());
		}
	}

	@Given("I enters valid data in Address")
	public void i_enters_valid_data_in_address() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getAddress());
				newenquirypage.enterAddress(testData.get("address"));
				System.out.println("Entered Address: " + testData.get("address"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Address: " + e.getMessage());
		}
	}

	@Given("I enters valid data in Village")
	public void i_enters_valid_data_in_village() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getVillage());
				newenquirypage.enterVillage(testData.get("village"));
				System.out.println("Entered Village: " + testData.get("village"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Village: " + e.getMessage());
		}
	}

	@Given("I selects valid data Enquiry Sub Source")
	public void i_selects_valid_data_enquiry_sub_source() {
		try {
			waitForVisibilityOfElement(newenquirypage.getEnquirySubSource());
			newenquirypage.selectEnquirySubSource("Walkin");
		} catch (Exception e) {
			System.err.println("Error during Selecting Enquiry Sub Source: " + e.getMessage());
		}
	}

	@Given("I selects valid data Enquiry Category")
	public void i_selects_valid_data_enquiry_category() {
		try {
			if (testData != null) {
				String EnquiryCategory = ExcelUtility.getMappedValue(testData.get("custType"));
				waitForVisibilityOfElement(newenquirypage.getEnquiryCategory());
				newenquirypage.selectEnquiryCategory(EnquiryCategory);
				System.out.println("Selected Enquiry Category: " + EnquiryCategory);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Enquiry Category: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Model")
	public void i_selects_valid_data_in_model() {
		try {
			Thread.sleep(5000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getModel());
				String Model = ExcelUtility.getMappedValue(testData.get("model"));
				newenquirypage.selectModel(Model);
				System.out.println("Selected Model: " + Model);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Model: " + e.getMessage());
		}
	}

	@Given("I selects valid data Fuel Type")
	public void i_selects_valid_data_fuel_type() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getFuelType());
				String FuelType = ExcelUtility.getMappedValue(testData.get("fuel"));
				newenquirypage.selectFuelType(FuelType.trim().toUpperCase());
				System.out.println("Selected Fuel Type: " + FuelType.toUpperCase());
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Fuel Type: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Variant")
	public void i_selects_valid_data_in_variant() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getVariant());
				newenquirypage.selectVariant(testData.get("variant"));
				System.out.println("Selected Variant: " + testData.get("variant"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Variant: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Sub Variant")
	public void i_selects_valid_data_in_sub_variant() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getSubVariant());
				newenquirypage.selectSubVariant(testData.get("variant"));
				System.out.println("Selected Sub Variant: " + testData.get("variant"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Sub Variant: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Finance Req")
	public void i_selects_valid_data_in_finance_req() {
		try {
			if (testData != null) {
				// Step 1: Select Finance Req
				waitForVisibilityOfElement(newenquirypage.getFinanceReq());
				String financeReq = ExcelUtility.getMappedValue(testData.get("financeReq"));
				newenquirypage.selectFinanceReq(financeReq);
				System.out.println("Selected Finance Req: " + financeReq);

				// Step 2: Check if Finance Req is "Y"
				if ("Y".equalsIgnoreCase(financeReq)) {
					// Select Financier
					waitForVisibilityOfElement(newenquirypage.getFinancier());
					String financier = ExcelUtility.getMappedValue(testData.get("finance_company"));
					newenquirypage.selectFinancier(financier);
					System.out.println("Selected Financier: " + financier);

					// Select Finance Options
					waitForVisibilityOfElement(newenquirypage.getFinanceOptions());
					String financeOptions = ExcelUtility.getMappedValue(testData.get("financeOption"));
					if (financeOptions == null || financeOptions.isEmpty()) {
	                    // Select "Unknown" if finance Options column is empty
						financeOptions = "Documents not Submitted";
	                }
					newenquirypage.selectFinanceOptions(financeOptions);
					System.out.println("Selected Finance Options: " + financeOptions);
				} else {
					System.out.println("Finance Req is 'N'; skipping Financier and Finance Options selection.");
				}
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Finance Details: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Financier")
	public void i_selects_valid_data_in_financier() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getFinancier());
				String Financier = ExcelUtility.getMappedValue(testData.get("finance_company"));
				newenquirypage.selectFinancier(Financier);
				System.out.println("Selected Financier: " + Financier);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Financier: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Ext Color")
	public void i_selects_valid_data_in_ext_color() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getExtColor());
				String ExtColor = ExcelUtility.getMappedValue(testData.get("color"));
				newenquirypage.selectExtColor(ExtColor);
				System.out.println("Selected Ext Color: " + ExtColor);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Ext Color: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Int Color")
	public void i_selects_valid_data_in_int_color() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getIntColor());
				String IntColor = ExcelUtility.getMappedValue(testData.get("interior_color"));
				newenquirypage.selectIntColor(IntColor);
				System.out.println("Selected Int Color: " + IntColor);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Int Color: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Sales Consultant")
	public void i_selects_valid_data_in_sales_consultant() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getSalesConsultant());
				newenquirypage.selectSalesConsultant(testData.get("sales_consultant"));
				System.out.println("Selected Sales Consultant: " + testData.get("sales_consultant"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Sales Consultant: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Certificate Of Deposit")
	public void i_selects_valid_data_in_certificate_of_deposit() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getCftOfDeposit());
				String cftOfDeposit = ExcelUtility.getMappedValue(testData.get("certificateOfDeposit"));
				if (cftOfDeposit == null || cftOfDeposit.isEmpty()) {
                    // Select "N" if cftOfDeposit column is empty
					cftOfDeposit = "N";
                }
				newenquirypage.selectCftOfDeposit(cftOfDeposit);
				System.out.println("Selected Certificate Of Deposit: " + cftOfDeposit);

				// If Certificate of Deposit is "N", select valid data in Present Car Dropdown
				if ("N".equalsIgnoreCase(cftOfDeposit)) {
					i_selects_valid_data_in_present_car_dropdown_in_preowned_car_info_section();
				} else {
					System.out.println("Certificate of Deposit is not 'N'; skipping Present Car Dropdown selection.");
				}
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Certificate Of Deposit: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Finance Options")
	public void i_selects_valid_data_in_finance_options() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getFinanceOptions());
				String FinanceOptions = ExcelUtility.getMappedValue(testData.get("financeOption"));
				newenquirypage.selectFinanceOptions(FinanceOptions);
				System.out.println("Selected Finance Options: " + FinanceOptions);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Finance Options: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Expected plan")
	public void i_selects_valid_data_in_expected_plan() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getExpectedplan());
				String ExpectedPlan = ExcelUtility.getMappedValue(testData.get("expectedPlan"));
				if (ExpectedPlan == null || ExpectedPlan.isEmpty()) {
                    // Select "Within 2 months" if Expected Plan column is empty
					ExpectedPlan = "Within 2 months";
                }
				newenquirypage.selectExpectedplan(ExpectedPlan);
				System.out.println("Selected Expected plan: " + ExpectedPlan);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Expected plan: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Visited with Family")
	public void i_selects_valid_data_in_visited_with_family() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getVisitedwithFamily());
				String VisitedwithFamily = ExcelUtility.getMappedValue(testData.get("visitedWith"));
				if (VisitedwithFamily == null || VisitedwithFamily.isEmpty()) {
                    // Select "N" if Visited with Family column is empty
					VisitedwithFamily = "N";
                }
				newenquirypage.selectVisitedwithFamily(VisitedwithFamily);
				System.out.println("Selected Visited with Family: " + VisitedwithFamily);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Visited with Family: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Immediate booking")
	public void i_selects_valid_data_in_immediate_booking() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getImmediatebooking());
				String ImmediateBooking = ExcelUtility.getMappedValue(testData.get("immediate_booking_flag"));
				if (ImmediateBooking == null || ImmediateBooking.isEmpty()) {
                    // Select "N" if Immediate Booking column is empty
					ImmediateBooking = "N";
                }
				newenquirypage.selectImmediatebooking(ImmediateBooking);
				System.out.println("Selected Immediate booking: " + ImmediateBooking);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Immediate booking: " + e.getMessage());
		}
	}

	@Given("I selects valid data in Present Car Dropdown in PreOwned Car Info Section")
	public void i_selects_valid_data_in_present_car_dropdown_in_preowned_car_info_section() {
		try {
			waitForVisibilityOfElement(newenquirypage.getPresentCarDropdown());
			newenquirypage.selectPresentCarDropdown("N");
		} catch (Exception e) {
			System.err.println("Error during Selecting Present Car Status: " + e.getMessage());
		}
	}

	@When("User clicks on Save button")
	public void user_clicks_on_save_button() {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			waitForElementToBeClickable(newenquirypage.getSaveButton());
			newenquirypage.clickSaveButton();
			System.out.println("Save button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Save button click: " + e.getMessage());
		}
	}
}