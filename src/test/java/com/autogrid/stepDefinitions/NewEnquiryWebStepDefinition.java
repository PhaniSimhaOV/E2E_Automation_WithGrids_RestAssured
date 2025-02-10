package com.autogrid.stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.openqa.selenium.WebElement;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.ExcelUtility;
import com.autogrid.utils.ExcelWriting;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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

	@When("User reads data from the Excel sheet regarding New Enquiry\\(Web) feature")
	public void user_reads_data_from_the_excel_sheet_regarding_new_enquiry_web_feature() throws IOException {
		String filePath = "src/test/resources/config/NewEnquiryWeb-1.xlsx";
		String sheetName = "Enquiry Lead Creation";

		// Fetch all data from the Excel sheet
		allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

		if (allTestData == null || allTestData.isEmpty()) {
			throw new RuntimeException("No data found in Excel sheet: " + sheetName);
		}
		System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");
	}

	@When("User processes the New Enquiry \\(Web) for all rows from the Excel sheet of sheet Name Enquiry Lead Creation")
	public void user_processes_the_new_enquiry_web_for_all_rows_from_the_excel_sheet_of_sheet_name_enquiry_lead_creation()
			throws Throwable {
		int passedCount = 0;
		int failedCount = 0;

		String filePath = "src/test/resources/config/NewEnquiryWeb-1.xlsx";
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
				executeTestStepsForWebDigitalEnquiryLeadGeneration();
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
					executeTestStepsForWebDigitalEnquiryLeadGeneration();
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

	private void executeTestStepsForWebDigitalEnquiryLeadGeneration() throws Throwable {
	    try {
	        user_tries_to_clicks_sales_menu_item();
	        user_tries_to_clicks_on_the_customer_enquiry_sub_menu_item();
	        user_tries_to_clicks_on_the_customer_enquiry_link();
	        user_tries_to_clicks_on_the_lead_enquiry_tab();
	        user_clicks_on_new_button_in_lead_enquiry_tab();
	        user_tries_to_enters_a_mobile_number_which_not_registered();
	        user_clicks_on_the_mobile_number_search_icon();

	        // Conditional execution based on screen visibility
	        if (isFindCustomerInfoScreenVisible()) {
	        	i_selected_Previouesly_added_Customer_Details();
	            i_selects_valid_data_enquiry_source_field();
	            i_selects_valid_data_enquiry_sub_source_field();
	            i_selects_valid_data_enquiry_category_field();
	            i_selects_valid_data_in_model_field();
	            i_selects_valid_data_fuel_type_field();
	            i_selects_valid_data_in_variant_field();
	            i_selects_valid_data_in_sub_variant_field();
	            i_selects_valid_data_in_ext_color_field();
	            i_selects_valid_data_in_int_color_field();
	            i_selects_valid_data_in_person_in_charge_field();
	            user_clicks_on_pincode_search_icon_beside_pin_field();
	            user_tries_to_enter_pincode_in_the_pincode_field();
	            user_tries_to_clicks_on_the_search_button_in_the_pincode_search_screen();
	            user_tries_to_select_any_one_pincode_from_the_list();
	            user_tries_to_clicks_on_the_add_selected_button_in_pincode_search_screen();
	            user_clicks_on_the_save_button();
	        } else {
	            i_enters_valid_data_in_email_id_field();
	            i_selects_valid_data_in_cust_type_field();
	            i_enters_valid_data_in_address_field();
	            i_enters_valid_data_in_village_field();
	            i_selects_valid_data_enquiry_source_field();
	            i_selects_valid_data_enquiry_sub_source_field();
	            i_selects_valid_data_enquiry_category_field();
	            i_selects_valid_data_in_model_field();
	            i_selects_valid_data_fuel_type_field();
	            i_selects_valid_data_in_variant_field();
	            i_selects_valid_data_in_sub_variant_field();
	            i_selects_valid_data_in_ext_color_field();
	            i_selects_valid_data_in_int_color_field();
	            i_selects_valid_data_in_person_in_charge_field();
	            user_clicks_on_pincode_search_icon_beside_pin_field();
	            user_tries_to_enter_pincode_in_the_pincode_field();
	            user_tries_to_clicks_on_the_search_button_in_the_pincode_search_screen();
	            user_tries_to_select_any_one_pincode_from_the_list();
	            user_tries_to_clicks_on_the_add_selected_button_in_pincode_search_screen();
	            user_clicks_on_the_save_button();
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Error during execution due to: " + e.getMessage(), e);
	    }
	}

	@When("User tries to clicks Sales Menu Item")
	public void user_tries_to_clicks_sales_menu_item() throws Throwable {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirypage.getSalesMenu());
			newenquirypage.clickSalesMenu();
			System.out.println("Sales Menu clicked.");
		} catch (Exception e) {
			System.err.println("Error during Sales Menu click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks Sales Menu Item.", e);
		}
	}

	@When("User tries to clicks on the Customer Enquiry Sub Menu Item")
	public void user_tries_to_clicks_on_the_customer_enquiry_sub_menu_item() throws Throwable {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirypage.getCustomerEnquirySubmenu());
			newenquirypage.clickCustomerEnquirySubmenu();
			System.out.println("Customer Enquiry Sub Menu Item clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Enquiry Sub Menu Item click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks Customer Enquiry Sub Menu Item.", e);
		}
	}

	@When("User tries to clicks on the Customer Enquiry link")
	public void user_tries_to_clicks_on_the_customer_enquiry_link() throws Throwable {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(newenquirypage.getCustomerEnquiryLink());
			newenquirypage.clickCustomerEnquiryLink();
			System.out.println("Customer Enquiry Link clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Enquiry Link click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks Customer Enquiry link.", e);
		}
	}

	@When("User tries to clicks on the lead Enquiry Tab")
	public void user_tries_to_clicks_on_the_lead_enquiry_tab() throws Throwable {
		try {
			newenquirypage.interactWithIframeElement();
			waitForElementToBeClickable(newenquirypage.getLeadEnquiryTab());
			newenquirypage.clickLeadEnquiryTab();
			System.out.println("Lead Enquiry Tab clicked.");
		} catch (Exception e) {
			System.err.println("Error during Lead Enquiry Tab click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks lead Enquiry Tab.", e);
		}
	}

	@And("User clicks on New button in Lead enquiry tab")
	public void user_clicks_on_new_button_in_lead_enquiry_tab() throws Throwable {
		try {
			Thread.sleep(3000);
			newenquirypage.interactWithIframeElement();
			waitForElementToBeClickable(newenquirypage.getNewEnquiry());
			newenquirypage.clickNewEnquiryButton();
			System.out.println("New Enquiry Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during New Enquiry Button click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks New button in Lead enquiry tab.", e);
		}
	}

	@When("User tries to enters a mobile number Which Not Registered")
	public void user_tries_to_enters_a_mobile_number_which_not_registered() throws Throwable {
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
			throw new RuntimeException("Failed to enters a mobile number.", e);
		}
	}

	@When("User clicks on the mobile number search Icon")
	public void user_clicks_on_the_mobile_number_search_icon() throws Throwable {
		try {
			waitForElementToBeClickable(newenquirypage.getMobileSearchIcon());
			newenquirypage.clickMobileSearchIcon();
			System.out.println("Mobile number search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Mobile number search Icon click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks mobile number search Icon.", e);
		}
	}
	
	
	@When("i selected Previouesly added Customer Details")
	public void i_selected_Previouesly_added_Customer_Details() throws Throwable {
		try {
			Thread.sleep(5000);
			newenquirypage.interactWithFindACustomeriframeElement();
			waitForElementToBeClickable(newenquirypage.getSelectCustomerDetails());
			newenquirypage.doubleClickOnCustomerDetails();
		} catch (Exception e) {
			System.err.println("Error performing double-click on the Customer Details: " + e.getMessage());
			throw new RuntimeException(
					"Failed to select Customer Details from the list after applying filters.",
					e);
		}
	}
	@When("I enters valid data in Email id field")
	public void i_enters_valid_data_in_email_id_field() throws Throwable {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
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
			throw new RuntimeException("Failed to enters valid data in Email id field.", e);
		}
	}

	@Given("I selects valid data in Cust. Type field")
	public void i_selects_valid_data_in_cust_type_field() throws Throwable {
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
					newenquirypage.selectGender(gender.trim().toUpperCase());
					System.out.println("Selected Gender: " + gender.toUpperCase());

					// Enter Customer Name
					waitForVisibilityOfElement(newenquirypage.getCustName());
					String custName = testData.get("custName");
					newenquirypage.enterCustName(custName);
					System.out.println("Entered Customer Name: " + custName);
				} else if ("Goverment".equalsIgnoreCase(custType) || "Corporate (Company)".equalsIgnoreCase(custType)) {
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
			throw new RuntimeException("Failed to entering Customer Details or Duplicate Mobile Number Registration.",
					e);
		}
	}

	@Given("I selects valid data Gender field")
	public void i_selects_valid_data_gender_field() throws Throwable {
		try {
			Thread.sleep(3000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getGenderField());
				String gender = ExcelUtility.getMappedValue(testData.get("gender"));
				newenquirypage.selectGender(gender.trim().toUpperCase());
				System.out.println("selected Gender: " + gender.toUpperCase());
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during selecting Gender: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data Gender field.", e);
		}
	}

	@When("User clicks on Pincode Search Icon beside PIN field")
	public void user_clicks_on_pincode_search_icon_beside_pin_field() throws Throwable {
		try {
			waitForElementToBeClickable(newenquirypage.getPincodeSearchIcon());
			newenquirypage.clickPincodeSearchIcon();
			System.out.println("Pincode Search Icon clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Icon click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on Pincode Search Icon beside PIN field", e);
		}
	}

	@Then("User tries to enter Pincode in the pincode field")
	public void user_tries_to_enter_pincode_in_the_pincode_field() throws Throwable {
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
			throw new RuntimeException("Failed to enter Pincode in the pincode field.", e);
		}
	}

	@Then("User tries to clicks on the search button in the Pincode search Screen")
	public void user_tries_to_clicks_on_the_search_button_in_the_pincode_search_screen() throws Throwable {
		try {
			waitForElementToBeClickable(newenquirypage.getPinCodeSearchButton());
			newenquirypage.clickPinCodeSearchButton();
			System.out.println("Pincode Search Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Pincode Search Button click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks Pincode Search Button click.", e);
		}
	}

	@Then("User tries to select any one pincode from the list")
	public void user_tries_to_select_any_one_pincode_from_the_list() throws Throwable {
		try {
			waitForElementToBeClickable(newenquirypage.getLocationSelection());
			newenquirypage.clickLocationSelection();
			System.out.println("Location Selection clicked.");
		} catch (Exception e) {
			System.err.println("Error during Location Selection click: " + e.getMessage());
			throw new RuntimeException("Failed to select any one pincode from the list.", e);
		}
	}
	
	@Then("^User should see the Find Customer Info screen$")
    public boolean isFindCustomerInfoScreenVisible() {
		newenquirypage.interactWithIframeElement();        
		boolean isVisible = newenquirypage.isFindCustomerInfoScreenVisible();
        if (isVisible) {
            System.out.println("Find Customer Info screen is visible.");
        } else {
            System.out.println("Find Customer Info screen is not visible.");
        }
        return isVisible;
    }

	@Then("User tries to clicks on the Add Selected button in Pincode search Screen")
	public void user_tries_to_clicks_on_the_add_selected_button_in_pincode_search_screen() throws Throwable {
		try {
			waitForElementToBeClickable(newenquirypage.getAddSelectedButton());
			newenquirypage.clickAddSelectedButton();
			System.out.println("Add Selected button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Add Selected button click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks Add Selected button in Pincode search Screen.", e);
		}
	}

	@Given("I enters valid data in Address field")
	public void i_enters_valid_data_in_address_field() throws Throwable {
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
			throw new RuntimeException("Failed to enters valid data in Address field.", e);
		}
	}

	@Given("I enters valid data in Village field")
	public void i_enters_valid_data_in_village_field() throws Throwable {
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
			throw new RuntimeException("Failed to enters valid data in Village field.", e);
		}
	}

	@Given("I selects valid data Enquiry Sub Source field")
	public void i_selects_valid_data_enquiry_sub_source_field() throws Throwable {
		try {
			if (testData != null) {
				String EnquirySubSource = ExcelUtility.getMappedValue(testData.get("subSource"));
				waitForVisibilityOfElement(newenquirypage.getEnquirySubSource());
				newenquirypage.selectEnquirySubSource(EnquirySubSource);
				System.out.println("Selected Enquiry Sub Source: " + EnquirySubSource);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Enquiry Sub Source: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data Enquiry Sub Source field.", e);
		}
	}

	@Given("I selects valid data Enquiry Category field")
	public void i_selects_valid_data_enquiry_category_field() throws Throwable {
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
			throw new RuntimeException("Failed to selects valid data Enquiry Category field.", e);
		}
	}

	@Given("I selects valid data in Model field")
	public void i_selects_valid_data_in_model_field() throws Throwable {
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
			throw new RuntimeException("Failed to selects valid data in Model field.", e);
		}
	}

	@Given("I selects valid data Fuel Type field")
	public void i_selects_valid_data_fuel_type_field() throws Throwable {
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
			throw new RuntimeException("Failed to selects valid data Fuel Type field.", e);
		}
	}

	@Given("I selects valid data in Variant field")
	public void i_selects_valid_data_in_variant_field() throws Throwable {
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
			throw new RuntimeException("Failed to selects valid data in Variant field.", e);
		}
	}

	@Given("I selects valid data in Sub Variant field")
	public void i_selects_valid_data_in_sub_variant_field() throws Throwable {
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
			throw new RuntimeException("Failed to selects valid data in Sub Variant field.", e);
		}
	}

	@Given("I selects valid data enquiry source field")
	public void i_selects_valid_data_enquiry_source_field() throws Throwable {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getEnquirySource());
				String EnquirySource = ExcelUtility.getMappedValue(testData.get("source"));
				newenquirypage.selectEnquirySource(EnquirySource);
				System.out.println("Selected Enquiry Source: " + EnquirySource);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Enquiry Source: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data enquiry source field.", e);
		}
	}

	@Given("I selects valid data in Ext Color field")
	public void i_selects_valid_data_in_ext_color_field() throws Throwable {
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
			throw new RuntimeException("Failed to selects valid data in Ext Color field.", e);
		}
	}

	@Given("I selects valid data in Int Color field")
	public void i_selects_valid_data_in_int_color_field() throws Throwable {
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
			throw new RuntimeException("Failed to selects valid data in Int Color field.", e);
		}
	}

	@Given("I selects valid data in person in charge field")
	public void i_selects_valid_data_in_person_in_charge_field() throws Throwable {
		try {
			Thread.sleep(2000);
			if (testData != null) {
				waitForVisibilityOfElement(newenquirypage.getPersonInCharge());
				newenquirypage.selectPersonInCharge(testData.get("sales_consultant"));
				System.out.println("Selected Sales Consultant: " + testData.get("sales_consultant"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Selecting Person In Charge: " + e.getMessage());
			throw new RuntimeException("Failed to selects valid data in person in charge field.", e);
		}
	}

	@When("User clicks on the Save button")
	public void user_clicks_on_the_save_button() throws Throwable {
		try {
			newenquirypage.interactWithIframeElement();
			newenquirypage.interactWithCustomerEnquiryPopupIframeElement();
			waitForElementToBeClickable(newenquirypage.getSaveButton());
			newenquirypage.clickSaveButton();
			System.out.println("Save button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Save button click: " + e.getMessage());
			throw new RuntimeException("Failed to clicks on the Save button.", e);
		}Thread.sleep(10000);
	}
	
	@When("user tries to fetch and Print the Enquiry Id In The Excel Sheet")
	public void user_tries_to_fetch_and_print_the_enquiry_id_in_the_excel_sheet() throws Throwable {
		String filePath = "src/test/resources/config/NewEnquiryWeb-1.xlsx";
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
				Thread.sleep(5000);
				LaunchDriver.getDriver().navigate().refresh();
				user_tries_to_clicks_sales_menu_item();
				user_tries_to_clicks_on_the_customer_enquiry_sub_menu_item();
				user_tries_to_clicks_on_the_customer_enquiry_link();
				user_tries_to_clicks_on_the_lead_enquiry_tab();
				waitForElementToBeClickable(newenquirypage.getMobileFilter());
				newenquirypage.enterMobileNumberFilter(testData.get("mobileNo"));
				newenquirypage.clickManageScreenSearchButton();

				// Fetch the enquiry number
				enquiryNo = newenquirypage.getEnquiryNoFieldValue();
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
				throw new RuntimeException("Failed to capture Enquiry Numbers.", e);
			}
		}
	}

	@When("update the gdms_stage and enquiry number in the database based on the result")
	public void update_the_gdms_stage_and_enquiry_number_in_the_database_based_on_the_result() throws Throwable {
		String filePath = "src/test/resources/config/NewEnquiryWeb-1.xlsx";
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
					"src/test/resources/config/project.properties"); // path
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
