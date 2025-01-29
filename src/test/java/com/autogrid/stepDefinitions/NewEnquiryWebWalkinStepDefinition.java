package com.autogrid.stepDefinitions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.steps.InvoicePage;
import com.autogrid.steps.NewEnquiryWebWalkinPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.WebElement;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.ExcelUtility;
import com.autogrid.utils.ExcelWriting;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NewEnquiryWebWalkinStepDefinition {
	CommonActions commonActions;
	DMSLoginPage dMSLoginPage;
	NewEnquiryWebWalkinPage newenquirywebpage;
	InvoicePage invoicepage;
	DatabaseConnectionStepDefinition DatabaseConnectionStepDefinition;
	private Map<String, String> testData; // Stores data from Excel
	private List<Map<String, String>> allTestData; // List to store all data rows from Excel
	private int currentDataRowIndex = 0; // To keep track of the current row index

	public NewEnquiryWebWalkinStepDefinition() {
		WebDriver driver = LaunchDriver.getDriver();
		this.newenquirywebpage = new NewEnquiryWebWalkinPage(driver);
		PageFactory.initElements(driver, newenquirywebpage);
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
					"D:\\E2E_Automation_WithGrids_RestAssured\\src\\test\\resources\\config\\project.properties");
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

	@When("User reads data from the Excel sheet regarding New Enquiry Walkin\\(Web) feature")
	public void user_reads_data_from_the_excel_sheet_regarding_new_enquiry_walkin_web_feature() throws IOException {
		String filePath = "src/test/resources/config/NewEnquiryWeb.xlsx";
		String sheetName = "Enquiry Lead Creation";

		// Fetch all data from the Excel sheet
		allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

		if (allTestData == null || allTestData.isEmpty()) {
			throw new RuntimeException("No data found in Excel sheet: " + sheetName);
		}
		System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");
	}

	@When("User processes the New Enquiry Walkin\\(Web) for all rows from the Excel sheet of sheet Name Enquiry Lead Creation")
	public void user_processes_the_new_enquiry_walkin_web_for_all_rows_from_the_excel_sheet_of_sheet_name_enquiry_lead_creation()
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
			
			// Conditional execution based on screen visibility
	        if (isWalkinFindCustomerInfoScreenVisible()) {
	        	i_selected_Previouesly_added_Walkin_Customer_Details();
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
	        } else {
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
	        }
		} catch (Exception e) {
			throw new RuntimeException("Error during execution due to" + ": " + e.getMessage(), e);
		}
	}

	@When("i selected Previouesly added Walkin Customer Details")
	public void i_selected_Previouesly_added_Walkin_Customer_Details() {
		try {
			Thread.sleep(5000);
			newenquirywebpage.interactWithWalkinFindACustomeriframeElement();
			waitForElementToBeClickable(newenquirywebpage.getSelectWalkinCustomerDetails());
			newenquirywebpage.doubleClickOnWalkinCustomerDetails();
		} catch (Exception e) {
			System.err.println("Error performing double-click on the Walkin Customer Details: " + e.getMessage());
			throw new RuntimeException(
					"Failed to select Customer Details from the list after applying filters.",
					e);
		}
	}
	
	@When("User clicks Sales Menu Item")
	public void user_clicks_sales_menu_item() throws Throwable {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirywebpage.getSalesMenu());
			newenquirywebpage.clickSalesMenu();
			System.out.println("Sales Menu clicked.");
		} catch (Exception e) {
			System.err.println("Error during Sales Menu click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks Sales Menu Item.", e);
		}
	}

	@When("User clicks on the Customer Enquiry Sub Menu Item")
	public void user_clicks_on_the_customer_enquiry_sub_menu_item() throws Throwable {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirywebpage.getCustomerEnquirySubmenu());
			newenquirywebpage.clickCustomerEnquirySubmenu();
			System.out.println("Customer Enquiry Sub Menu Item clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Enquiry Sub Menu Item click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on the Customer Enquiry Sub Menu Item.", e);
		}
	}

	@When("User clicks on the Customer Enquiry link")
	public void user_clicks_on_the_customer_enquiry_link() throws Throwable {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirywebpage.getCustomerEnquiryLink());
			newenquirywebpage.clickCustomerEnquiryLink();
			System.out.println("Customer Enquiry Link clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Enquiry Link click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on the Customer Enquiry link.", e);
		}
	}

	@When("User tries to clicks on Walkin Enquiry Tab")
	public void user_tries_to_clicks_on_walkin_enquiry_tab() {
		try {
			newenquirywebpage.interactWithIframeElement();
			waitForElementToBeClickable(newenquirywebpage.getWalkinEnquiryTab());
			newenquirywebpage.clickWalkinEnquiryTab();
			System.out.println("Walkin Enquiry Tab clicked.");
		} catch (Exception e) {
			System.err.println("Error during Walkin Enquiry Tab click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on Walkin Enquiry Tab.", e);
		}
	}

	@And("User clicks on New button")
	public void user_clicks_on_new_button() throws Throwable {
		try {
			Thread.sleep(3000);
			newenquirywebpage.interactWithIframeElement();
			waitForElementToBeClickable(newenquirywebpage.getNewEnquiry());
			newenquirywebpage.clickNewEnquiryButton();
			System.out.println("New Enquiry Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during New Enquiry Button click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on New button.", e);
		}
	}

	@When("User tries to enters mobile number Which Not Registered")
	public void user_tries_to_enters_mobile_number_which_not_registered() throws Throwable {
		try {
			newenquirywebpage.interactWithIframeElement();
			newenquirywebpage.interactWithCustomerEnquiryPopupIframeElement();
			Thread.sleep(8000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getMobileNumber());
				Thread.sleep(3000);
				newenquirywebpage.enterMobileNumber(testData.get("mobileNo"));
				System.out.println("Entered Mobile Number: " + testData.get("mobileNo"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Mobile Number: " + e.getMessage());
			throw new RuntimeException("Failed to enters a mobile number.", e);
		}
	}

	@When("User clicks on mobile number search Icon")
	public void user_clicks_on_mobile_number_search_icon() {
		try {
			waitForElementToBeClickable(newenquirywebpage.getMobileSearchIcon());
			newenquirywebpage.clickMobileSearchIcon();
			System.out.println("Mobile number search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Mobile number search Icon click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on mobile number search Icon.", e);
		}
	}

	@When("I enters valid data in Email id")
	public void i_enters_valid_data_in_email_id() throws Throwable {
		try {
			newenquirywebpage.interactWithIframeElement();
			newenquirywebpage.interactWithCustomerEnquiryPopupIframeElement();
			Thread.sleep(5000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getEmail());
				newenquirywebpage.enterEmail(testData.get("email"));
				System.out.println("Entered Mobile Number: " + testData.get("email"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Email: " + e.getMessage());
			throw new RuntimeException("Failed to enters valid data in Email id.", e);
		}
	}
	
	@Then("^User should see the Walkin Find Customer Info screen$")
    public boolean isWalkinFindCustomerInfoScreenVisible() {
		newenquirywebpage.interactWithIframeElement();        
		boolean isVisible = newenquirywebpage.isWalkinFindCustomerInfoScreenVisible();
        if (isVisible) {
            System.out.println("Walkin Find Customer Info screen is visible.");
        } else {
            System.out.println("Walkin Find Customer Info screen is not visible.");
        }
        return isVisible;
    }

	@Given("I selects valid data in Cust. Type")
	public void i_selects_valid_data_in_cust_type() throws Throwable {
		try {
			if (testData != null) {
				// Step 1: Select Cust. Type
				waitForVisibilityOfElement(newenquirywebpage.getCustTypeField());
				String custType = ExcelUtility.getMappedValue(testData.get("custType"));
				newenquirywebpage.selectCustTypeField(custType);
				System.out.println("Selected Cust. Type: " + custType);

				// Step 2: Handle fields based on Cust. Type
				if ("Individual".equalsIgnoreCase(custType)) {
					// Select Gender
					waitForVisibilityOfElement(newenquirywebpage.getGenderField());
					String gender = ExcelUtility.getMappedValue(testData.get("gender"));
					if (gender == null || gender.isEmpty()) {
						// Select "Unknown" if Gender column is empty
						gender = "Unknown";
					}
					newenquirywebpage.selectGender(gender.trim().toUpperCase());
					System.out.println("Selected Gender: " + gender.toUpperCase());

					// Enter Customer Name
					waitForVisibilityOfElement(newenquirywebpage.getCustName());
					String custName = testData.get("custName");
					newenquirywebpage.enterCustName(custName);
					System.out.println("Entered Customer Name: " + custName);
				} else if ("Government".equalsIgnoreCase(custType) || "Corporate".equalsIgnoreCase(custType)) {
					// Enter Company Name
					waitForVisibilityOfElement(newenquirywebpage.getCompanyName());
					String companyName = testData.get("companyName"); // Assuming the same key for simplicity
					newenquirywebpage.enterCompanyName(companyName);
					System.out.println("Entered Company Name: " + companyName);
				} else {
					System.out.println("Invalid Cust. Type: " + custType + ". No further processing required.");
				}
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during processing Customer Details or Duplicate Mobile Number Registration: "
					+ e.getMessage());
			throw new RuntimeException("Failed to enters a Customer Details or Duplicate Mobile Number Registration.",
					e);
		}
	}

	@When("User clicks on Pincode Search Icon from PIN field")
	public void user_clicks_on_pincode_search_icon_from_pin_field() {
		try {
			waitForElementToBeClickable(newenquirywebpage.getPincodeSearchIcon());
			newenquirywebpage.clickPincodeSearchIcon();
			System.out.println("Pincode Search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Icon click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on Pincode Search Icon from PIN field.", e);
		}
	}

	@Then("User tries to enter Pincode in pincode field")
	public void user_tries_to_enter_pincode_in_pincode_field() throws Throwable {
		try {
			newenquirywebpage.interactWithIframeElement();
			newenquirywebpage.interactWithincodeSearchIframeElement();
			Thread.sleep(4000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getPincode());
				newenquirywebpage.enterPincode(testData.get("pincode"));
				System.out.println("Entered Pincode: " + testData.get("pincode"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Pincode: " + e.getMessage());
			throw new RuntimeException("Failed to enter Pincode in pincode field.", e);
		}
	}

	@Then("User tries to clicks on search button in Pincode search Screen")
	public void user_tries_to_clicks_on_search_button_in_pincode_search_screen() {
		try {
			waitForElementToBeClickable(newenquirywebpage.getPinCodeSearchButton());
			newenquirywebpage.clickPinCodeSearchButton();
			System.out.println("Pincode Search Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Button click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on search button in Pincode search Screen.", e);
		}
	}

	@Then("User tries to select one pincode from the list")
	public void user_tries_to_select_one_pincode_from_the_list() {
		try {
			waitForElementToBeClickable(newenquirywebpage.getLocationSelection());
			newenquirywebpage.clickLocationSelection();
			System.out.println("Location Selection clicked.");
		} catch (Exception e) {
			System.err.println("Error during Location Selection click: " + e.getMessage());
			throw new RuntimeException("Failed to select one pincode from the list.", e);
		}
	}

	@Then("User tries to clicks on Add Selected button in Pincode search Screen")
	public void user_tries_to_clicks_on_add_selected_button_in_pincode_search_screen() {
		try {
			waitForElementToBeClickable(newenquirywebpage.getAddSelectedButton());
			newenquirywebpage.clickAddSelectedButton();
			System.out.println("Add Selected button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Add Selected button click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on Add Selected button in Pincode search Screen.", e);
		}
	}

	@Given("I enters valid data in Address")
	public void i_enters_valid_data_in_address() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getAddress());
				newenquirywebpage.enterAddress(testData.get("address"));
				System.out.println("Entered Address: " + testData.get("address"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Address: " + e.getMessage());
			throw new RuntimeException("Failed to enters valid data in Address.", e);
		}
	}

	@Given("I enters valid data in Village")
	public void i_enters_valid_data_in_village() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getVillage());
				newenquirywebpage.enterVillage(testData.get("village"));
				System.out.println("Entered Village: " + testData.get("village"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Village: " + e.getMessage());
			throw new RuntimeException("Failed to enters valid data in Village.", e);
		}
	}

	@Given("I selects valid data Enquiry Sub Source")
	public void i_selects_valid_data_enquiry_sub_source() throws Throwable {
		try {
			newenquirywebpage.interactWithIframeElement();
			newenquirywebpage.interactWithCustomerEnquiryPopupIframeElement();
			waitForVisibilityOfElement(newenquirywebpage.getEnquirySubSource());
			newenquirywebpage.selectEnquirySubSource("Walkin");
		} catch (Exception e) {
			System.err.println("Error during Selecting Enquiry Sub Source: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data Enquiry Sub Source.", e);
		}
	}

	@Given("I selects valid data Enquiry Category")
	public void i_selects_valid_data_enquiry_category() throws Exception {
		try {
			if (testData != null) {
				String EnquiryCategory = ExcelUtility.getMappedValue(testData.get("custType"));
				waitForVisibilityOfElement(newenquirywebpage.getEnquiryCategory());
				newenquirywebpage.selectEnquiryCategory(EnquiryCategory);
				System.out.println("Selected Enquiry Category: " + EnquiryCategory);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Enquiry Category: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data Enquiry Category.", e);
		}
	}

	@Given("I selects valid data in Model")
	public void i_selects_valid_data_in_model() throws Throwable {
		try {
			Thread.sleep(5000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getModel());
				String Model = ExcelUtility.getMappedValue(testData.get("model"));
				newenquirywebpage.selectModel(Model);
				System.out.println("Selected Model: " + Model);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Model: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data in Model.", e);
		}
	}

	@Given("I selects valid data Fuel Type")
	public void i_selects_valid_data_fuel_type() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getFuelType());
				String FuelType = ExcelUtility.getMappedValue(testData.get("fuel"));
				newenquirywebpage.selectFuelType(FuelType.trim().toUpperCase());
				System.out.println("Selected Fuel Type: " + FuelType.toUpperCase());
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Fuel Type: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data Fuel Type.", e);
		}
	}

	@Given("I selects valid data in Variant")
	public void i_selects_valid_data_in_variant() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getVariant());
				newenquirywebpage.selectVariant(testData.get("variant"));
				System.out.println("Selected Variant: " + testData.get("variant"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Variant: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data in Variant.", e);
		}
	}

	@Given("I selects valid data in Sub Variant")
	public void i_selects_valid_data_in_sub_variant() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getSubVariant());
				newenquirywebpage.selectSubVariant(testData.get("variant"));
				System.out.println("Selected Sub Variant: " + testData.get("variant"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Sub Variant: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data in Sub Variant.", e);
		}
	}

	@Given("I selects valid data in Finance Req")
	public void i_selects_valid_data_in_finance_req() throws Throwable {
		try {
			if (testData != null) {
				// Step 1: Select Finance Req
				waitForVisibilityOfElement(newenquirywebpage.getFinanceReq());
				String financeReq = ExcelUtility.getMappedValue(testData.get("financeReq"));
				newenquirywebpage.selectFinanceReq(financeReq);
				System.out.println("Selected Finance Req: " + financeReq);

				// Step 2: Check if Finance Req is "Y"
				if ("Y".equalsIgnoreCase(financeReq)) {
					// Select Financier
					waitForVisibilityOfElement(newenquirywebpage.getFinancier());
					String financier = ExcelUtility.getMappedValue(testData.get("finance_company"));
					newenquirywebpage.selectFinancier(financier);
					System.out.println("Selected Financier: " + financier);

					// Select Finance Options
					waitForVisibilityOfElement(newenquirywebpage.getFinanceOptions());
					String financeOptions = ExcelUtility.getMappedValue(testData.get("financeOption"));
					if (financeOptions == null || financeOptions.isEmpty()) {
						// Select "Unknown" if finance Options column is empty
						financeOptions = "Documents not Submitted";
					}
					newenquirywebpage.selectFinanceOptions(financeOptions);
					System.out.println("Selected Finance Options: " + financeOptions);
				} else {
					System.out.println("Finance Req is 'N'; skipping Financier and Finance Options selection.");
				}
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Finance Details: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Finance Details.", e);
		}
	}

	@Given("I selects valid data in Financier")
	public void i_selects_valid_data_in_financier() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getFinancier());
				String Financier = ExcelUtility.getMappedValue(testData.get("finance_company"));
				newenquirywebpage.selectFinancier(Financier);
				System.out.println("Selected Financier: " + Financier);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Financier: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Financier.", e);
		}
	}

	@Given("I selects valid data in Ext Color")
	public void i_selects_valid_data_in_ext_color() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getExtColor());
				String ExtColor = ExcelUtility.getMappedValue(testData.get("color"));
				newenquirywebpage.selectExtColor(ExtColor);
				System.out.println("Selected Ext Color: " + ExtColor);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Ext Color: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Ext Color.", e);
		}
	}

	@Given("I selects valid data in Int Color")
	public void i_selects_valid_data_in_int_color() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getIntColor());
				String IntColor = ExcelUtility.getMappedValue(testData.get("interior_color"));
				newenquirywebpage.selectIntColor(IntColor);
				System.out.println("Selected Int Color: " + IntColor);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Int Color: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Int Color.", e);
		}
	}

	@Given("I selects valid data in Sales Consultant")
	public void i_selects_valid_data_in_sales_consultant() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getSalesConsultant());
				newenquirywebpage.selectSalesConsultant(testData.get("sales_consultant"));
				System.out.println("Selected Sales Consultant: " + testData.get("sales_consultant"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Sales Consultant: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Sales Consultant.", e);
		}
	}

	@Given("I selects valid data in Certificate Of Deposit")
	public void i_selects_valid_data_in_certificate_of_deposit() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getCftOfDeposit());
				String cftOfDeposit = ExcelUtility.getMappedValue(testData.get("certificateOfDeposit"));
				if (cftOfDeposit == null || cftOfDeposit.isEmpty()) {
					// Select "N" if cftOfDeposit column is empty
					cftOfDeposit = "N";
				}
				newenquirywebpage.selectCftOfDeposit(cftOfDeposit);
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
			throw new RuntimeException("Failed to Selecting Certificate Of Deposit.", e);
		}
	}

	@Given("I selects valid data in Finance Options")
	public void i_selects_valid_data_in_finance_options() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getFinanceOptions());
				String FinanceOptions = ExcelUtility.getMappedValue(testData.get("financeOption"));
				newenquirywebpage.selectFinanceOptions(FinanceOptions);
				System.out.println("Selected Finance Options: " + FinanceOptions);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Finance Options: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Finance Options.", e);
		}
	}

	@Given("I selects valid data in Expected plan")
	public void i_selects_valid_data_in_expected_plan() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getExpectedplan());
				String ExpectedPlan = ExcelUtility.getMappedValue(testData.get("expectedPlan"));
				if (ExpectedPlan == null || ExpectedPlan.isEmpty()) {
					// Select "Within 2 months" if Expected Plan column is empty
					ExpectedPlan = "Within 2 months";
				}
				newenquirywebpage.selectExpectedplan(ExpectedPlan);
				System.out.println("Selected Expected plan: " + ExpectedPlan);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Expected plan: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Expected plan.", e);
		}
	}

	@Given("I selects valid data in Visited with Family")
	public void i_selects_valid_data_in_visited_with_family() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getVisitedwithFamily());
				String VisitedwithFamily = ExcelUtility.getMappedValue(testData.get("visitedWith"));
				if (VisitedwithFamily == null || VisitedwithFamily.isEmpty()) {
					// Select "N" if Visited with Family column is empty
					VisitedwithFamily = "N";
				}
				newenquirywebpage.selectVisitedwithFamily(VisitedwithFamily);
				System.out.println("Selected Visited with Family: " + VisitedwithFamily);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Visited with Family: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Visited with Family.", e);
		}
	}

	@Given("I selects valid data in Immediate booking")
	public void i_selects_valid_data_in_immediate_booking() throws Throwable {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(newenquirywebpage.getImmediatebooking());
				String ImmediateBooking = ExcelUtility.getMappedValue(testData.get("immediate_booking_flag"));
				if (ImmediateBooking == null || ImmediateBooking.isEmpty()) {
					// Select "N" if Immediate Booking column is empty
					ImmediateBooking = "N";
				}
				newenquirywebpage.selectImmediatebooking(ImmediateBooking);
				System.out.println("Selected Immediate booking: " + ImmediateBooking);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Immediate booking: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Immediate booking.", e);
		}
	}

	@Given("I selects valid data in Present Car Dropdown in PreOwned Car Info Section")
	public void i_selects_valid_data_in_present_car_dropdown_in_preowned_car_info_section() throws Throwable {
		try {
			waitForVisibilityOfElement(newenquirywebpage.getPresentCarDropdown());
			newenquirywebpage.selectPresentCarDropdown("N");
		} catch (Exception e) {
			System.err.println("Error during Selecting Present Car Status: " + e.getMessage());
			throw new RuntimeException("Failed to Selecting Present Car Status.", e);
		}
	}

	@When("User clicks on Save button")
	public void user_clicks_on_save_button() throws Throwable {
		try {
			newenquirywebpage.interactWithIframeElement();
			newenquirywebpage.interactWithCustomerEnquiryPopupIframeElement();
			waitForElementToBeClickable(newenquirywebpage.getSaveButton());
			newenquirywebpage.clickSaveButton();
			System.out.println("Save button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Save button click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on Save button.", e);
		}Thread.sleep(10000);
	}
	
	@When("user tries to fetch and Print the Enquiry Id In Excel Sheet")
	public void user_tries_to_fetch_and_print_the_enquiry_id_in_excel_sheet() throws Throwable {
		String filePath = "src/test/resources/config/NewEnquiryWeb.xlsx";
		String sheetName = "Enquiry Lead Creation";

		// Add a new column for "Enquiry Number"
		ExcelWriting.addColumnToSheet(filePath, sheetName, "EnquiryNumber");

		for (int currentDataRowIndex = 0; currentDataRowIndex < allTestData.size(); currentDataRowIndex++) {
			System.out.println("\nProcessing Row: " + (currentDataRowIndex + 1));

			// Fetch and log current row data
			testData = allTestData.get(currentDataRowIndex);
			System.out.println("Current Test Data: " + testData);

			String enquiryNo = "Enquiry was not Available"; // Default message if no enquiry number is found
			try {
				// Interact with the application to fetch enquiry number
				Thread.sleep(15000);
				LaunchDriver.getDriver().navigate().refresh();
				user_clicks_sales_menu_item();
				user_clicks_on_the_customer_enquiry_sub_menu_item();
				user_clicks_on_the_customer_enquiry_link();
				user_tries_to_clicks_on_walkin_enquiry_tab();
				waitForElementToBeClickable(newenquirywebpage.getMobileFilter());
				newenquirywebpage.enterMobileNumberFilter(testData.get("mobileNo"));
				newenquirywebpage.clickManageScreenSearchButton();

				// Fetch the enquiry number
				enquiryNo = newenquirywebpage.getEnquiryNoFieldValue();
				if (enquiryNo == null || enquiryNo.trim().isEmpty()) {
					enquiryNo = "Enquiry was not Available";
				}
				System.out.println("Fetched Enquiry No for Row " + (currentDataRowIndex + 1) + ": " + enquiryNo);

			} catch (Exception e) {
				System.err.println(
						"Error fetching Enquiry No for Row " + (currentDataRowIndex + 1) + ": " + e.getMessage());
				e.printStackTrace();
			}
			// Update the enquiry number column in the Excel sheet
			try {
				ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "EnquiryNumber", enquiryNo);
			} catch (Exception e) {
				System.err.println(
						"Error updating Enquiry Number for Row " + (currentDataRowIndex + 1) + ": " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
@When("update the gdms_stage and enquiry number columns data in the database based on the result")
public void update_the_gdms_stage_and_enquiry_number_columns_data_in_the_database_based_on_the_result() throws Throwable {
	String filePath = "C:\\Users\\ADMIN\\Downloads\\output.xlsx";
	String sheetName = "Enquiry Lead Creation";

	// Load Excel data into allTestData
	allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

	if (allTestData == null || allTestData.isEmpty()) {
		throw new RuntimeException("No data found in Excel sheet: " + sheetName);
	}

	for (int currentDataRowIndex = 0; currentDataRowIndex < allTestData.size(); currentDataRowIndex++) {
		System.out.println("\nProcessing Row: " + (currentDataRowIndex + 1));

		// Fetch and log current row data
		testData = allTestData.get(currentDataRowIndex);
		System.out.println("Current Test Data: " + testData);

		// Load database credentials from properties file
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(
				"D:\\E2E_Automation_WithGrids_RestAssured\\src\\test\\resources\\config\\project.properties"); // path
		properties.load(fis);

		String dbUrl = properties.getProperty("db.url");
		String dbUsername = properties.getProperty("db.username");
		String dbPassword = properties.getProperty("db.password");

		// Now, let's update the database based on Excel data
		String leadId = testData.get("leadId");
		String errorLogs = testData.get("Error Logs");
		String enquiryNumber = testData.get("EnquiryNumber");

		if (leadId == null || enquiryNumber == null) {
			System.err.println("Skipping row due to missing leadId, or enquiryNumber.");
			continue;
		}

		// Determine the gdms_stage value based on errorLogs
		String gdmsStage = "PASSED".equalsIgnoreCase(errorLogs) ? "Done" : "Failed";
		String gdms_id = "Enquiry Not Available".equalsIgnoreCase(enquiryNumber) ? null : enquiryNumber;
		String updateQuery = "UPDATE dms_lead_stage_ref " + "SET gdms_stage = ?, gdms_id = ? "
				+ "WHERE lead_id = ? " + "AND stage_id = (" + "    SELECT id " + "    FROM dms_master_lead_stage "
				+ "    WHERE lead_stage_key = 'enquiry' " + "    AND active IS TRUE" + ");";

		// Connect to the database and update the values
		try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
				PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

			preparedStatement.setString(1, gdmsStage);
			preparedStatement.setString(2, gdms_id); // Use the enquiry number as gdms_id
			preparedStatement.setInt(3, Integer.parseInt(leadId));

			// Execute update
			int rowsUpdated = preparedStatement.executeUpdate();
			System.out.println("Rows updated for Lead ID " + leadId + ": " + rowsUpdated);
		} catch (SQLException e) {
			System.err.println(
					"Error while updating gdms_stage and enquiry number in the database: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
}