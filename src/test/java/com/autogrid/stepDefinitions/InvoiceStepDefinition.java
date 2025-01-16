package com.autogrid.stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.steps.InvoicePage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.ExcelWriting;
import com.autogrid.utils.LaunchDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class InvoiceStepDefinition {
	CommonActions commonActions;
	DMSLoginPage dMSLoginPage;
	InvoicePage invoicepage;
	DatabaseConnectionStepDefinition DatabaseConnectionStepDefinition;
	private Map<String, String> testData; // Stores data from Excel
	private List<Map<String, String>> allTestData; // List to store all data rows from Excel
	private int currentDataRowIndex = 0; // To keep track of the current row index

	public InvoiceStepDefinition() {
		WebDriver driver = LaunchDriver.getDriver();
		this.invoicepage = new InvoicePage(driver);
		PageFactory.initElements(driver, invoicepage);
	}

	private void waitForVisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	private void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	@Given("User reads data from the Excel sheet regarding Invoice feature")
	public void user_reads_data_from_the_excel_sheet_regarding_invoice_feature() throws IOException {
		String filePath = "src/test/resources/config/output.xlsx";
		String sheetName = "Invoice Leads";

		// Fetch all data from the Excel sheet
		allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

		if (allTestData == null || allTestData.isEmpty()) {
			throw new RuntimeException("No data found in Excel sheet: " + sheetName);
		}
		System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");
	}

	@When("User processes the Invoice for all rows from the Excel sheet of sheet Name Invoice Leads")
	public void user_processes_the_invoice_for_all_rows_from_the_excel_sheet_of_sheet_name_invoice_leads()
			throws Throwable {
		int passedCount = 0;
	    int failedCount = 0;

	    String filePath = "src/test/resources/config/output.xlsx";
	    String sheetName = "Invoice Leads";

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

	            // Restart from the initial step
	            restartFromSalesMenuStep();

	            // Execute all test steps for the current row
	            executeTestStepsForRow();

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
		            restartFromSalesMenuStep();
		            executeTestStepsForRow();
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

	private void restartFromSalesMenuStep() throws Throwable {
		try {
			user_clicks_on_sales_menu_item();
			user_clicks_on_the_sales_operation_sub_menu_item();
			user_clicks_on_the_customer_booking_mgt_list_link();
		} catch (Exception e) {
			System.err.println("Error restarting from Sales Menu step: " + e.getMessage());
			throw new RuntimeException("Failed to restart execution from Sales Menu step.", e);
		}
	}

	private void executeTestStepsForRow() throws Throwable {
		// Example: Call test methods for each step
		try {
			user_tries_to_selects_mobile_number_in_the_based_on_auto_suggestion_in_customer_booking_mgt_list_screen();
			user_tries_to_enters_lead_mobile_number_in_the_based_on_field();
			user_tries_to_clicks_on_the_search_button_in_customer_booking_mgt_list_screen();
			user_tries_to_select_enquiry_from_the_list_after_applying_filters_in_customer_booking_mgt_list_screen();
			user_tries_to_clicks_on_the_invoice_tab_in_the_customer_booking_management_screen();
			user_tries_to_clicks_on_scheme_button_in_the_invoice_tab_in_the_customer_booking_management_screen();
			user_tries_to_enters_valid_data_in_the_payable_by_dealer_amount_in_tax_adjustment_allowed_table();
			user_tries_to_enters_valid_data_in_the_adjustment_credit_note_amount_in_tax_adjustment_allowed_table();
			user_tries_to_enters_valid_data_in_the_basic_insurance_amount_in_chargeable_sharing_table();
			user_tries_to_enters_valid_data_in_the_rto_amount_in_chargeable_sharing_table();
			user_tries_to_enters_valid_data_in_the_road_tax_amount_in_chargeable_sharing_table();
			user_tries_to_enters_valid_data_in_the_other_charges_amount_in_chargeable_sharing_table();
			user_tries_to_enters_valid_data_in_the_life_tax_amount_in_chargeable_sharing_table();
			user_tries_to_clicks_on_save_button_in_scheme_popup_screen();
			user_tries_to_clicks_on_confirm_button_in_do_you_want_to_save_it_popup();
			user_tries_to_clicks_on_close_button_in_scheme_popup_screen();
			user_tries_to_clicks_on_the_more_promotions_button_in_basic_info_section_in_the_customer_booking_management_screen();
			user_tries_to_clicks_on_the_plus_icon_in_promotions_section_in_the_customer_booking_management_screen();
			user_tries_to_checks_the_all_the_promotions_from_promotions_table_in_the_promotion_pop_up();
			user_tries_to_clicks_on_add_selected_button_in_the_promotion_pop_up();
			user_tries_to_click_on_the_modify_button_in_basic_info_section_in_the_customer_booking_management_screen();
			user_tries_to_clicks_on_confirm_button_in_do_you_want_to_modify_it_popup();
			user_tries_to_selects_valid_data_in_vehicle_usage_type_field_in_customer_info_section_in_the_customer_booking_management_screen();
			user_tries_to_clicks_on_the_register_button_in_invoice_tab_in_the_customer_booking_management_screen();
			user_tries_to_clicks_on_confirm_button_in_do_you_want_to_save_it_popup_in_the_customer_booking_management_screen();
			user_tries_to_click_on_the_modify_button_in_invoice_tab_in_the_customer_booking_management_screen();
			user_tries_to_clicks_on_confirm_button_in_do_you_want_to_modify_it_popup_in_the_customer_booking_management_screen();
			user_tries_to_clicks_on_invoice_confirm_button_in_invoice_tab_in_the_customer_booking_management_screen();
			user_tries_to_clicks_on_confirm_button_in_do_you_want_to_confirm_it_popup();
		} catch (Exception e) {
	        throw new RuntimeException("Error during execution due to"+ ": " + e.getMessage(), e);
		}
	}

	@Then("user should be able to see the Home Icon on the dashboard")
	public void user_should_be_able_to_see_the_home_icon_on_dashboard() {
		try {
			Assert.assertTrue(dMSLoginPage.isHomepageIconDisplayed(), "Home Icon is not displayed on the dashboard.");
			System.out.println("Home Icon is displayed on the dashboard.");
		} catch (Exception e) {
			System.err.println("Error verifying Home Icon: " + e.getMessage());
			Assert.fail("Home Icon verification failed.");
		}
	}

	@When("User clicks On Sales Menu Item")
	public void user_clicks_on_sales_menu_item() {
		try {
			Thread.sleep(3000);
			invoicepage.clickSalesMenu();
			System.out.println("Sales Menu clicked.");
		} catch (Exception e) {
			System.err.println("Error during Sales Menu click: " + e.getMessage());
		}
	}

	@Then("User clicks on the Sales Operation Sub Menu Item")
	public void user_clicks_on_the_sales_operation_sub_menu_item() {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(invoicepage.getSalesOperationSubmenu());
			invoicepage.clickSalesOperationSubmenu();
			System.out.println("Sales Operation Sub Menu Item clicked.");
		} catch (Exception e) {
			System.err.println("Error during Sales Operation Sub Menu Item click: " + e.getMessage());
		}
	}

	@Then("User clicks on the Customer Booking Mgt List link")
	public void user_clicks_on_the_customer_booking_mgt_list_link() {
		try {
			Thread.sleep(3000);
			waitForElementToBeClickable(invoicepage.getCustomerBookingMgtListLink());
			invoicepage.clickCustomerBookingMgtListLink();
			System.out.println("Customer Booking Mgt List Link clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Booking Mgt List Link click: " + e.getMessage());
		}
	}

	@When("User tries to Selects Mobile Number in the Based On Auto-Suggestion in Customer Booking Mgt List Screen")
	public void user_tries_to_selects_mobile_number_in_the_based_on_auto_suggestion_in_customer_booking_mgt_list_screen() {
		try {
			invoicepage.interactWithIframeElement1();
			Thread.sleep(5000);
			String enquirystartDate = "01091947";
			invoicepage.enterEnquiryStartDateField(enquirystartDate);
			Thread.sleep(3000);
			waitForVisibilityOfElement(invoicepage.getBasedOnDropDown());
			invoicepage.selectBasedOn("Mobile No");
		} catch (Exception e) {
			System.err.println("Error during entering & selecting Based On Type: " + e.getMessage());
		}
	}

	@When("User tries to enters Lead Mobile Number in the Based On Field")
	public void user_tries_to_enters_lead_mobile_number_in_the_based_on_field() {
		try {
			if (testData != null) {
				waitForVisibilityOfElement(invoicepage.getBasedOnField());
				invoicepage.enterBasedOnField(testData.get("phone"));
				System.out.println("Entered Lead Mobile Number: " + testData.get("phone"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Lead Mobile Number : " + e.getMessage());
		}
	}

	@And("User tries to clicks on the Search button in Customer Booking Mgt List Screen")
	public void user_tries_to_clicks_on_the_search_button_in_customer_booking_mgt_list_screen() {
		try {
			waitForElementToBeClickable(invoicepage.getCustomerBookingMgtListSearch());
			invoicepage.clickCustomerBookingMgtListSearch();
			System.out.println("Customer Booking Mgt List Search Button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Customer Booking Mgt List Search Button click: " + e.getMessage());
		}
	}

	@When("User tries to select Enquiry from the list after applying filters in Customer Booking Mgt List Screen")
	public void user_tries_to_select_enquiry_from_the_list_after_applying_filters_in_customer_booking_mgt_list_screen() {
		try {
			waitForElementToBeClickable(invoicepage.getSelectEnquiryFromCustomerBookingMgtList());
			invoicepage.doubleClickOnEnquiry();
		} catch (Exception e) {
			System.err.println("Error performing double-click on the enquiry: " + e.getMessage());
			throw new RuntimeException("Failed to double-click on the enquiry.", e);
		}
	}

	@When("User tries to clicks on the Invoice Tab in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_the_invoice_tab_in_the_customer_booking_management_screen() {
		try {
			LaunchDriver.getDriver().switchTo().defaultContent();
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getInvoiceTab());
			invoicepage.clickInvoiceTab();
			System.out.println("Invoice Tab clicked.");
		} catch (Exception e) {
			System.err.println("Error during Invoice Tab click: " + e.getMessage());
		}
	}

	@When("User tries to clicks on Scheme button in the Invoice Tab in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_scheme_button_in_the_invoice_tab_in_the_customer_booking_management_screen() {
		try {
			waitForElementToBeClickable(invoicepage.getSchemeButton());
			invoicepage.clickSchemeButton();
			System.out.println("Scheme button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Scheme button click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}
	
	@Then("User tries to enters valid data in the Payable By Dealer Amount in TAX Adjustment Allowed Table")
	public void user_tries_to_enters_valid_data_in_the_payable_by_dealer_amount_in_tax_adjustment_allowed_table() {
		try {
			invoicepage.interactWithIframeElement2();
			invoicepage.interactWithIframeElement3();
			if (testData != null) {
				Thread.sleep(5000);
				invoicepage.enterPayableByDealerAmountField(testData.get("cash_discount"));
				System.out.println("Entered Payable By Dealer Amount: " + testData.get("cash_discount"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Payable By Dealer Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Adjustment Credit Note Amount in TAX Adjustment Allowed Table")
	public void user_tries_to_enters_valid_data_in_the_adjustment_credit_note_amount_in_tax_adjustment_allowed_table() {
		try {
			if (testData != null) {
				Thread.sleep(5000);
				invoicepage.enterAdjustmentCreditNoteField(testData.get("cash_discount"));
				System.out.println("Entered Adjustment Credit Note Amount: " + testData.get("cash_discount"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Adjustment Credit Note Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Basic Insurance Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_basic_insurance_amount_in_chargeable_sharing_table() {
		try {
			if (testData != null) {
				invoicepage.enterBasicInsuranceAmountField(testData.get("basicInsuranceAmont"));
				System.out.println("Entered Basic Insurance Amount: " + testData.get("basicInsuranceAmont"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Basic Insurance Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the RTO Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_rto_amount_in_chargeable_sharing_table() {
		try {
			if (testData != null) {
				invoicepage.enterRTOAmountField(testData.get("rtoCharges"));
				System.out.println("Entered RTO Amount: " + testData.get("rtoCharges"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering RTO Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Road Tax Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_road_tax_amount_in_chargeable_sharing_table() {
		try {
			if (testData != null) {
				invoicepage.enterRoadTaxAmountField(testData.get("roadTax"));
				System.out.println("Entered Road Tax Amount: " + testData.get("roadTax"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Road Tax Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Other Charges Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_other_charges_amount_in_chargeable_sharing_table() {
		try {
			if (testData != null) {
				invoicepage.enterOtherChargesAmountField(testData.get("otherCharges"));
				System.out.println("Entered Other Charges Amount: " + testData.get("otherCharges"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Other Charges Amount: " + e.getMessage());
		}
	}

	@Then("User tries to enters valid data in the Life Tax Amount in Chargeable\\/Sharing Table")
	public void user_tries_to_enters_valid_data_in_the_life_tax_amount_in_chargeable_sharing_table() {
		try {
			if (testData != null) {
				invoicepage.enterLifeTaxAmountField(testData.get("lifeTax"));
				System.out.println("Entered Life Tax Amount: " + testData.get("lifeTax"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during entering Life Tax Amount: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on Save button in Scheme Popup screen")
	public void user_tries_to_clicks_on_save_button_in_scheme_popup_screen() {
		try {
			waitForElementToBeClickable(invoicepage.getSchemePopupSaveButton());
			invoicepage.clickSchemePopupSaveButton();
			System.out.println("Save button in Scheme Popup screen clicked.");
		} catch (Exception e) {
			System.err.println("Error during Save button in Scheme Popup screen click: " + e.getMessage());
		}
		
	}
	
	@Then("User tries to clicks on Confirm button in Do you want to save it? Popup")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_save_it_popup() {
		try {
			LaunchDriver.getDriver().switchTo().defaultContent();
			invoicepage.interactWithIframeElement2();
			invoicepage.interactWithIframeElement3();
			waitForElementToBeClickable(invoicepage.getSchemeSaveConfirmationConfirmButton());
			invoicepage.clickSchemeSaveConfirmationConfirmButton();
			System.out.println("Confirm button in Do you want to save it? Popup screen clicked.");
		} catch (Exception e) {
			System.err.println(
					"Error during Confirm button in Do you want to save it? Popup screen click: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on Close button in Scheme Popup screen")
	public void user_tries_to_clicks_on_close_button_in_scheme_popup_screen() {
		try {
			Thread.sleep(12000);
			waitForElementToBeClickable(invoicepage.getSchemePopupCloseButton());
			invoicepage.clickSchemePopupCloseButton();
			System.out.println("Close button in Scheme Popup screen clicked.");
		} catch (Exception e) {
			System.err.println("Error during Close button in Scheme Popup screen click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@When("User tries to clicks on the More Promotions button in Basic info Section in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_the_more_promotions_button_in_basic_info_section_in_the_customer_booking_management_screen() {
		try {
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getMorePromotionsButton());
			invoicepage.clickMorePromotionsButton();
			System.out.println("More Promotions button clicked.");
		} catch (Exception e) {
			System.err.println("Error during More Promotions button click: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on the Plus icon in Promotions Section in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_the_plus_icon_in_promotions_section_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(14000);
			waitForElementToBeClickable(invoicepage.getPromotionsSectionPlusIcon());
			invoicepage.clickPromotionsSectionPlusIcon();
			System.out.println("Plus icon in Promotions Section clicked.");
		} catch (Exception e) {
			System.err.println("Error during Plus icon in Promotions Section click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@Then("User tries to Checks the All the promotions from promotions table in the Promotion Pop-up")
	public void user_tries_to_checks_the_all_the_promotions_from_promotions_table_in_the_promotion_pop_up() {
		try {
			invoicepage.interactWithIframeElement2();
			invoicepage.interactWithIframeElement3();
			waitForElementToBeClickable(invoicepage.getPromotionCheckBoxAll());
			invoicepage.clickPromotionCheckBoxAll();
			System.out.println("Checks the All the promotions clicked.");
		} catch (Exception e) {
			System.err.println("Error during Checks the All the promotions click: " + e.getMessage());
		}
	}

	@Then("User tries to clicks on Add Selected button in the Promotion Pop-up")
	public void user_tries_to_clicks_on_add_selected_button_in_the_promotion_pop_up() {
		try {
			waitForElementToBeClickable(invoicepage.getPromotionAddSelectedButton());
			invoicepage.clickPromotionAddSelectedButton();
			System.out.println("Add Selected button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Add Selected button click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@When("User tries to click on the Modify button in Basic info Section in the Customer Booking Management Screen")
	public void user_tries_to_click_on_the_modify_button_in_basic_info_section_in_the_customer_booking_management_screen() {
		try {
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getCustomerBookingMgtModifyButton());
			invoicepage.clickCustomerBookingMgtModifyButton();
			System.out.println("Modify button in Basic info Section clicked.");
		} catch (Exception e) {
			System.err.println("Error during Modify button in Basic info Sectionclick: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@When("User tries to clicks on Confirm button in Do you want to Modify it? Popup")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_modify_it_popup() {
		try {
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getCustomerBookingMgtModifyConfirmationPopupConfirmButton());
			invoicepage.clickCustomerBookingMgtModifyConfirmationPopupConfirmButton();
			System.out.println("Confirm button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Confirm button click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@When("User tries to selects valid data in Vehicle usage Type field in customer info Section in the Customer Booking Management Screen")
	public void user_tries_to_selects_valid_data_in_vehicle_usage_type_field_in_customer_info_section_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(12000);
			invoicepage.interactWithIframeElement2();
			waitForVisibilityOfElement(invoicepage.getVehicleUsageTypeField());
			if (testData != null) {
				invoicepage.selectVehicleUsageType(testData.get("vehicleUsageType"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error during Vehicle usage Type selection: " + e.getMessage());
		}
	}

	@When("User tries to clicks on the Register button in invoice tab in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_the_register_button_in_invoice_tab_in_the_customer_booking_management_screen() {
		try {
			waitForElementToBeClickable(invoicepage.getRegisterButton());
			invoicepage.clickRegisterButton();
			System.out.println("Register button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Register button click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@When("User tries to clicks on Confirm button in Do you want to save it? Popup in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_save_it_popup_in_the_customer_booking_management_screen() {
		try {
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getRegisterConfirmationPopupConfirmButton());
			invoicepage.clickRegisterConfirmationPopupConfirmButton();
			System.out.println("Confirm button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Confirm button click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@When("User tries to click on the Modify button in invoice tab in the Customer Booking Management Screen")
	public void user_tries_to_click_on_the_modify_button_in_invoice_tab_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(12000);
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getInvoiceModifyButton());
			invoicepage.clickInvoiceModifyButton();
			System.out.println("Modify button in invoice tab clicked.");
		} catch (Exception e) {
			System.err.println("Error during Modify button in invoice tab click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@When("User tries to clicks on Confirm button in Do you want to Modify it? Popup in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_modify_it_popup_in_the_customer_booking_management_screen() {
		try {
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getInvoiceModifyConfirmationPopupConfirmButton());
			invoicepage.clickInvoiceModifyConfirmationPopupConfirmButton();
			System.out.println("Confirm button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Confirm button click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}

	@When("User tries to clicks on Invoice Confirm button in invoice tab in the Customer Booking Management Screen")
	public void user_tries_to_clicks_on_invoice_confirm_button_in_invoice_tab_in_the_customer_booking_management_screen() {
		try {
			Thread.sleep(12000);
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getInvoiceConfirmButton());
			invoicepage.clickInvoiceConfirmButton();
			System.out.println("Invoice Confirm button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Invoice Confirm button click: " + e.getMessage());
		}
		LaunchDriver.getDriver().switchTo().defaultContent();
	}


	@When("User tries to clicks on Confirm button in Do you want to confirm it? Popup")
	public void user_tries_to_clicks_on_confirm_button_in_do_you_want_to_confirm_it_popup() {
		try {
			invoicepage.interactWithIframeElement2();
			waitForElementToBeClickable(invoicepage.getInvoiceConfirmConfirmationPopupConfirmButton());
			invoicepage.clickInvoiceConfirmConfirmationPopupConfirmButton();
			System.out.println("Confirm button clicked.");
		} catch (Exception e) {
			System.err.println("Error during Confirm button click: " + e.getMessage());
		}
//		LaunchDriver.getDriver().switchTo().defaultContent();
//		invoicepage.clickCloseCustomerBookingMgt();
//		invoicepage.clickCloseCustomerBookingMgtList();
//		invoicepage.clickSalesMenu();
//		invoicepage.clickSalesOperationSubmenu();
	}
}