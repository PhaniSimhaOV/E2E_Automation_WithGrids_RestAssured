package com.autogrid.stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.steps.ExWarrantyPage;
import com.autogrid.steps.SOTPage;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.ExcelWriting;
import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.en.*;
import com.autogrid.steps.NewEnquiryWebPage;

public class ExWarrantyAndSOTStepDefinition {

	DMSLoginPage dmsLogin;
	ExWarrantyPage exWarranty;
	SOTPage sot;
	DatabaseConnectionStepDefinition DatabaseConnectionStepDefinition;
	NewEnquiryWebPage newEnquiryWebPage;
	private Map<String, String> testData; // Stores data from Excel
	private List<Map<String, String>> allTestData; // List to store all data rows from Excel
	private int currentDataRowIndex = 0; // To keep track of the current row index

	
	public ExWarrantyAndSOTStepDefinition() {
		WebDriver driver = LaunchDriver.getDriver();
		this.exWarranty = new ExWarrantyPage(driver);
		PageFactory.initElements(driver, exWarranty);
		this.sot = new SOTPage(driver);
		PageFactory.initElements(driver, sot);
		this.newEnquiryWebPage = new NewEnquiryWebPage(driver);
		PageFactory.initElements(driver, newEnquiryWebPage);
	}
	
	
	
	private void restartFromServiceMenuStepforWarranty() throws Throwable{
		try{
		user_clicks_on_service_icon();
					user_clicks_on_extended_warranty_sub_menu();
					user_clicks_on_extended_warranty_submit_link();
					user_should_be_able_to_navigate_to_extended_warranty_submit_screen();
		}catch (Exception e) {
					System.err.println("Error restarting from Service Menu step: " + e.getMessage());
					throw new RuntimeException("Failed to restart execution from Service Menu step.", e);
				}
			}
		
	private void executeTestStepsForRowForExwarrenty() throws Throwable {
		try {
			// Call test methods for each step in Ex-warrenty Functionality			
			user_enters_vin_number();
			user_clicks_on_inquire();
			//user_should_be_able_to_see_toast_message_as_customer_no_does_not_exist();
			user_enter_current_odometer_reading();
			user_select_employee_name();
			user_select_place_of_supply();
			user_select_required_extented_warranty_type();


			//user_clicks_on_clear_button();
			// user_clicks_on_submit_button();
			//user_clicks_on_service_icon();
			//exWarranty.clickExtWarrantySubMenu();
		} catch (Exception e) {
	        String errorMessage = "Error during execution of steps for Row " + (currentDataRowIndex + 1) +
	                              ": " + e.getMessage();
	        System.err.println(errorMessage);
	        e.printStackTrace(); // Prints full stack trace to console for debugging
	        throw new RuntimeException("Failed to execute steps for Row " + (currentDataRowIndex + 1), e);
	    }
	}

	

	
	@Given("User reads data from the Excel sheet regarding ExWarranty feature")
	public void User_reads_data_from_the_Excel_sheet_regarding_ExWarranty_feature() throws IOException {
		String filePath = "src/test/resources/config/NewEnquiryWeb.xlsx";
		String sheetName = "ExWarranty Leads";

		// Fetch all data from the Excel sheet
		allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

		if (allTestData == null || allTestData.isEmpty()) {
			throw new RuntimeException("No data found in Excel sheet: " + sheetName);
		}
		System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");
	}

	@When("User processes the ExWarranty for all rows from the Excel sheet of sheet Name ExWarranty Leads for Exwarranty")
	public void user_processes_the_ex_warranty_for_all_rows_from_the_excel_sheet_of_sheet_name_ex_warranty_leads_for_exwarranty() throws Throwable{

		int passedCount = 0;
		int failedCount = 0;

		String filePath = "src/test/resources/config/NewEnquiryWeb.xlsx";
		String sheetName = "ExWarranty Leads";
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
				
				//Restart from the initial step
				restartFromServiceMenuStepforWarranty();

				// Execute all test steps for the current row
				executeTestStepsForRowForExwarrenty();
				

				// Log success
				System.out.println("Row " + (currentDataRowIndex + 1) + " execution PASSED.");
				ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", "PASSED");
				passedCount++;
			} catch (Exception e) {
				if (isPopupDisplayed(exWarranty.getPopupElement())) {
	                System.out.println("Popup detected! Restarting the workflow from the first step...");
	                restartFromServiceMenuStepforWarranty();
	                executeTestStepsForRowForExwarrenty();
	            }
				// Handle row failure
				// Handle application state reset on failure
				/*
				 * try { System.out.println("Navigating to the application's base URL...");
				 * LaunchDriver.getDriver().navigate().refresh();
				 * restartFromServiceMenuStepforWarranty();
				 * executeTestStepsForRowForExwarrenty();
				 * 
				 * } catch (Exception navigationException) {
				 * System.err.println("Error while navigating to the base URL: " +
				 * navigationException.getMessage()); navigationException.printStackTrace(); }
				 */
				
				
				String errorMessage = "Row " + (currentDataRowIndex + 1) + " execution FAILED: " + e.getMessage();
				System.err.println(errorMessage);
				e.printStackTrace();
				ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", errorMessage);
				rowExecutionPassed = false;
				failedCount++;

				// Skip retry and move to the next row
				System.out.println("Skipping retry for Row " + (currentDataRowIndex + 1) + ". Moving to the next row.");
			}
			/*catch (Throwable e) {
                throw new RuntimeException(e);
			} */
			
			finally {
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
	
	
	private boolean isPopupDisplayed(WebElement PopupElement) throws Exception {
		    try {
		        WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(5));
		        wait.until(ExpectedConditions.visibilityOf(PopupElement));
		        return true; // Popup is displayed
		    } catch (Exception e) {
		        return false; // Popup is not displayed
		    }
		}



	@When("User clicks on Service Icon")
	public void user_clicks_on_service_icon() {
		try {
			Thread.sleep(2000);
			exWarranty.clickServiceIcon();
			System.out.println("Service Icon clicked ");

		} catch (Exception e) {
			System.err.println("Error to click Service Icon " + e.getMessage());
		}

	}

	@When("User clicks on Extended Warranty Sub Menu")
	public void user_clicks_on_extended_warranty_sub_menu() {
		try {
			Thread.sleep(2000);
			exWarranty.clickExtWarrantySubMenu();
			System.out.println("Extended Warranty Sub Menu clicked");

		} catch (Exception e) {
			System.err.println("Error in clicking Extended Warranty Sub Menu ");
		}

	}

	@When("User clicks on Extended Warranty Submit Link")
	public void user_clicks_on_extended_warranty_submit_link() {
		try {
			Thread.sleep(2000);
			exWarranty.clickOnExtdWarrantySubmitLink();
			System.out.println("Extended Warranty Submit Link clicked");
		} catch (Exception e) {
			System.err.println("Error in clicking Extended Warranty Submit Link");
		}

	}

	@Then("User should be able to navigate to Extended Warranty Submit Screen")
	public void user_should_be_able_to_navigate_to_extended_warranty_submit_screen() {
		try {
			Assert.assertTrue(exWarranty.extdWarrantySbtHeaderisDisplayed());
			System.out.println("Extended Warranty Submit Screen is displayed");

		} catch (Exception e) {
			System.err.println("Error to see Extended Warranty Submit Screen");
			Assert.fail("Extended Warranty Submit Screen is not displayed");

		}

	}

	@When("User enters VIN number")
	public void user_enters_vin_number() throws InterruptedException {
try {
			Thread.sleep(2000);
			exWarranty.interactWithIframeExtW();
			if (testData != null) {
				exWarranty.enterVIN(testData.get("vinno")); //modified this need to remove 1
				System.out.println("Entered VIN " + testData.get("vinno"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}	
			  } catch (Exception e) {System.err.println("Error in entering VIN " + e.getMessage()); 
			 }
	}

	@When("User clicks on Inquire")
	public void user_clicks_on_inquire() throws InterruptedException {
//		try {
			Thread.sleep(12000);
			exWarranty.clickOnInquire();
			System.out.println("Clicked Inquire button");
//		} catch (Exception e) {
//			System.err.println("Error in clicking Inquire button " + e.getMessage());
//		}

	}
	
////	@Then("User should be able to see toast message as Customer No does not exist")
//	public void user_should_be_able_to_see_toast_message_as_customer_no_does_not_exist() throws Exception, Throwable {
//		try {
//	        // Try to fetch the toast message
//	        String actualMessage = exWarranty.getInvalidVINToast();
//	        String expectedMessage = "Customer No. does not exist. Please enter the CustomerNo";
//	        
//	        // Validate the toast message
//	        Assert.assertEquals(actualMessage, expectedMessage, "Toast message mismatch!");
//
//	        // Log success message
//	        System.out.println("Toast message validated successfully: " + actualMessage);
//
//	    } catch (Exception e) {
//	        // Handle case where the toast message is not displayed
//	        System.err.println("Error during toast message validation: " + e.getMessage());
//	        
//	        // Check if a popup is displayed
//	        if (isPopupDisplayed(exWarranty.getPopupElement())) {
//	            System.out.println("Popup detected! Restarting the workflow from the first step...");
//	            
//	            // Restart the workflow
//	            restartFromServiceMenuStepforWarranty();
//	            
//	            // Execute the test steps for the current row
//	            executeTestStepsForRowForExwarrenty();
//	        } else {
//	            // Navigate to the next steps if no popup is detected
//	            System.out.println("Toast message not displayed. Navigating to the next steps...");
//	            // Add logic here for navigating to the next steps
//	        }
//	    }
//	}
//
//	

	@When("User enter Current Odometer reading")
	public void user_enter_current_odometer_reading() {
		try {
			String odoMtrReading = "150";
			exWarranty.enterCurrentOdoMtrReading(odoMtrReading);
			System.out.println("Entered Current Odometer Reading : " + odoMtrReading);
		} catch (Exception e) {
			System.err.println("Error in Entering Current OdoMeter Reading" + e.getMessage());

		}

	}

	
	@When("User select Employee Name")
	public void user_select_employee_name() throws Throwable{
//		try {
			if (testData != null) {

				String employeeName = testData.get("empName");
				exWarranty.selectEmployeeName(employeeName);

			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
//		} catch (Exception e) {
//			System.err.println("Error in Selecting Employee Name " + e.getMessage());
//		}
	}

	@When("User select Place Of Supply")
	public void user_select_place_of_supply() throws InterruptedException {
//		try {
		Thread.sleep(13000);
			String PlaceOfSupply =testData.get("placeOfSupply");
			if (testData != null) {
				exWarranty.selectPlaceOfSupply(PlaceOfSupply);
				System.out.println("Selected place of supply" + PlaceOfSupply );
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
//		} catch (Exception e) {
//			System.err.println("Error in selecting place of supply");
//
//		}

	}

	@When("User select required extented Warranty type")
	public void user_select_required_extented_warranty_type() {
	

		
		try {
		if (testData != null) {

			String SchemeDes = testData.get("schemeDescription");
			exWarranty.setExtdWarrantyType(SchemeDes);;
		}else {
			throw new RuntimeException("Test data is not initialized.");
		}
		
		} catch (Exception e) {
			System.err.println("Error in Selecting Scheme " + e.getMessage());
			
		}
			
		}
	

	@Then("User Clicks on clear button")
	public void user_clicks_on_clear_button() throws Throwable {
		try {
			Thread.sleep(4000);
			exWarranty.clickClearBtn();
			System.out.println("Clicked clear button");
		} catch (Exception e) {
			System.err.println("Error in clicking clear button " + e.getMessage());
		}
		//exWarranty.closeExwarrentyTab();
		//user_clicks_on_service_icon();
		//exWarranty.clickExtWarrantySubMenu();
	}

	@Then("User Clicks on Submit button")
	public void user_clicks_on_submit_button() {
		try {
			Thread.sleep(2000);
			exWarranty.clickSubmitBtn();
			System.out.println("Clicked Submit Button");
		} catch (Exception e) {
			System.err.println("Error in clicking submit button " + e.getMessage());
		}
		//exWarranty.closeExwarrentyTab();
	}
	
	
	

	


	
	//STEPS FOR SOT

	private void restartFromServiceMenuStepforSOT() throws Throwable{
		try{
		user_clicks_on_service_icon();
					user_clicks_on_extended_warranty_sub_menu();
					user_clicks_on_hyundai_shield_of_trust_package_register_link();
					user_should_be_able_to_navigate_to_hyundai_shield_ot_trust_package_register_screen();
					
					}catch (Exception e) {
					System.err.println("Error restarting from Service Menu step: " + e.getMessage());
					throw new RuntimeException("Failed to restart execution from Service Menu step.", e);
				}
			}
	
	private void executeTestStepsForRowForSOT() throws Throwable {
		try {
		// Call test methods for each step in SOT Functionality
		user_enter_vin_number_in_sot();
		user_clicks_on_inquire_in_sot();
		user_enter_current_odometer_reading_in_sot();
		user_select_employee_name_in_sot();
		user_select_place_of_supply_in_sot();
		user_select_required_SOTtype();
		//user_clicks_on_clear_button_in_sot();
		// User_clicks_on_Submit_in_sot();
		//exWarranty.clickServiceIcon();
		//user_clicks_on_extended_warranty_sub_menu();
	} catch (Exception e) {
		System.err.println(
				"Error during execution of steps for Row " + (currentDataRowIndex + 1) + ": " + e.getMessage());
		throw new RuntimeException("Failed to execute steps for Row " + (currentDataRowIndex + 1), e);
	}
}
	
	
	@Given("User reads data from the Excel sheet regarding SOT feature")
	public void User_reads_data_from_the_Excel_sheet_regarding_Sot_feature() throws IOException {
		String filePath = "src/test/resources/config/NewEnquiryWeb.xlsx";
		String sheetName = "SOT Leads";

		// Fetch all data from the Excel sheet
		allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

		if (allTestData == null || allTestData.isEmpty()) {
			throw new RuntimeException("No data found in Excel sheet: " + sheetName);
		}
		System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");
	}

	
	@When("User processes the SOT for all rows from the Excel sheet of sheet Name SOT Leads for SOT")
	public void User_processes_the_sot_for_all_rows_from_the_Excel_sheet_of_sheet_Name_SOT_Leads_for_SOT()
			throws Throwable {

		int passedCount = 0;
		int failedCount = 0;

		String filePath = "src/test/resources/config/NewEnquiryWeb.xlsx";
		String sheetName = "SOT Leads";
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
				restartFromServiceMenuStepforSOT();
				// Execute all test steps for the current row
				
				executeTestStepsForRowForSOT();

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
					restartFromServiceMenuStepforSOT();
					executeTestStepsForRowForSOT();
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
			}  catch (Throwable e) {
                throw new RuntimeException(e);
			}finally {
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
	
	@When("User clicks on Hyundai Shield of Trust Package Register link")
	public void user_clicks_on_hyundai_shield_of_trust_package_register_link() {

		try {
			Thread.sleep(3000);
			sot.clickHyundaiShieldOfTrustPackageRegisterLink();
			System.out.println("Clicked Hyundai SOT Package Register Link ");
		} catch (Exception e) {
			System.err.println("Error in clicking Hyundai SOT Package Register Link " + e.getMessage());
		}

	}

	@Then("User should be able to navigate to Hyundai Shield ot Trust Package Register Screen")
	public void user_should_be_able_to_navigate_to_hyundai_shield_ot_trust_package_register_screen() {
		try {
			Assert.assertTrue(sot.HyundaiSOTPackageRegisterHeaderisDisplayed());
			System.out.println("Hyundai SOT Package Register Screen is displayed ");
		} catch (Exception e) {
			System.err.println(" Hyundai SOT Package Register Screen is not displayed" + e.getMessage());

		}

	}

	

	@When("User enter VIN number in SOT")
	public void user_enter_vin_number_in_sot() {
		try {
			Thread.sleep(2000);
			sot.interactWithIframeSOT();
			if (testData != null) {
				sot.enterVIN(testData.get("vinno"));
				System.out.println("Entered VIN in SOT " + testData.get("vinno"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error in entering VIN in SOT" + e.getMessage());
		}

	}

	@When("User clicks on Inquire in SOT")
	public void user_clicks_on_inquire_in_sot() {
		try {
			sot.clickOnInquire();
			System.out.println("Clicked Inquire btn in SOT");
		} catch (Exception e) {
			System.err.println("Error while Clicking Inquire button in SOT " + e.getMessage());
		}
	}

	@When("User enter Current Odometer reading in SOT")
	public void user_enter_current_odometer_reading_in_sot() throws Throwable {
		Thread.sleep(2000);
		try {
			String odoMtrReading = "150";
			sot.enterCurrentOdoMtrReading(odoMtrReading);
			System.out.println("Entered Current Odometer reading in SOT : " + odoMtrReading);
		} catch (Exception e) {
			System.err.println("Error in entering current odometer reading " + e.getMessage());

		}

	}

	@When("User select Employee Name in SOT")
	public void user_select_employee_name_in_sot() throws Throwable {
		Thread.sleep(2000);
		try {
			if (testData != null) {
				String employeeName= testData.get("empName");
				sot.selectEmployeeName(employeeName);
				System.out.println("Employee Name selected in SOT : " + employeeName);
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error in Selecting Employee Name " + e.getMessage());

		}

	}

	@When("User select Place Of Supply in SOT")
	public void user_select_place_of_supply_in_sot() throws Throwable {
		Thread.sleep(2000);
		try {
			String PlaceOfSupply=testData.get("placeOfSupply");
			if (testData != null) {
				sot.selectPlaceOfSupply(testData.get("placeOfSupply"));
				System.out.println("Selected Place of Supply " + testData.get("placeOfSupply"));
			} else {
				throw new RuntimeException("Test data is not initialized.");
			}
		} catch (Exception e) {
			System.err.println("Error in Selecting place of supply " + e.getMessage());
		}

	}

	@When("User select required SOT Scheme type in SOT")
	public void user_select_required_SOTtype() {
		/*try {
			sot.setRequiredSOTSchemeType();
			System.out.println("Selected required SOT Scheme type in SOT ");
		} catch (Exception e) {
			System.err.println("Error in selecting SOT Scheme " + e.getMessage());

		}*/
		
		
		try {
			if (testData != null) {

				String SOTSchemeDes = testData.get("schemeDescription").toUpperCase();  ///schemeDescription needs to change for SOT by giving different column name with corresponding data 
				sot.setRequiredSOTSchemeType(SOTSchemeDes);
			}else {
				throw new RuntimeException("Test data is not initialized.");
			}
			
			} catch (Exception e) {
				System.err.println("Error in Selecting Scheme " + e.getMessage());
				
			}
				
			}
	
	@Then("User Clicks on clear button in SOT")
	public void user_clicks_on_clear_button_in_sot() {
		try {
			Thread.sleep(2000);
			sot.clickClearBtn();
			System.out.println("Clicked clear button");
		} catch (Exception e) {
			System.err.println("Error while clicking clear button in SOT");
		}
		sot.closeSOTTab();

	}

	@Then("User Clicks on Submit button in SOT")
	public void User_clicks_on_Submit_in_sot() {
		try {
			sot.clickSubmitBtn();
			System.out.println("Clicked Submit button in SOT");
		} catch (Exception e) {
			System.err.println("Error in Clicking submit button in SOT" + e.getMessage());
		}
		sot.closeSOTTab();
	}

}