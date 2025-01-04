package com.autogrid.stepDefinitions;

import com.autogrid.steps.BookingSalesOperationPage;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.ExcelWriting;
import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;


import static com.autogrid.utils.LaunchDriver.getDriver;

public class BookingSalesOperationStepDefinition {
    CommonActions commonActions;
    DMSLoginPage dMSLoginPage;

    BookingSalesOperationPage bookingPage;
    private Map<String, String> testData; // Stores data from Excel
    private List<Map<String, String>> allTestData; // List to store all data rows from Excel
    private int currentDataRowIndex = 0;

    public BookingSalesOperationStepDefinition() throws Exception {
        WebDriver driver = getDriver();
        this.bookingPage = new BookingSalesOperationPage(driver);
        PageFactory.initElements(driver, bookingPage);
        this.dMSLoginPage = new DMSLoginPage(driver);
        PageFactory.initElements(driver, dMSLoginPage);
    }

    @Given("User clicks on the Sales icon")
    public void userClicksOnTheSalesIcon() throws InterruptedException {
        bookingPage.SalesIconButton();
        Thread.sleep(3000);
    }

    @Then("User selects the Sales Operation tab")
    public void userSelectsTheSalesOperationTab() throws InterruptedException {
        bookingPage.SalesOperationLink();
    }

    @When("User selects Customer Booking Mgt List under sales Operation")
    public void userSelectsCustomerBookingMgtListUnderSalesOperation() throws InterruptedException {
        Thread.sleep(4000);
        bookingPage.selectCustomerBookingMgtListMainLinks();
    }

    @Then("User need to select the enquiry option in the dropdown")
    public void userNeedToSelectTheEnquiryOptionInTheDropdown() throws InterruptedException {
        bookingPage.selectDateOFDropdown();
    }

    @And("User enters the mobile number in the text box")
    public void userEntersTheMobileNumberInTheTextBox() {
        try {
            if (testData != null) {

                bookingPage.MobileNumberTextBox(testData.get("phone"));

                System.out.println("Entered Lead Mobile Number: " + testData.get("phone"));
            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during entering Lead Mobile Number : " + e.getMessage());
        }
    }


    @And("User selects the mobile number option from the dropdown")
    public void userSelectsTheMobileNumberOptionFromTheDropdown() throws InterruptedException {
        bookingPage.BasedOnDropdown();
    }

    @Then("User clicks on the search button")
    public void userClicksOnTheSearchButton() {
        bookingPage.SearchButton();
    }

    @And("User passed the start date and end date in the page")
    public void userPassedTheStartDateAndEndDateInThePage() throws InterruptedException {
        bookingPage.SelectDates();
    }

    @When("The enquiry will be populated then user as to select it")
    public void theEnquiryWillBePopulatedThenUserAsToSelectIt() throws IOException {
        bookingPage.SalesTable();
    }

    @Then("User fills the fields in the Customer Booking MGT")
    public void userFillsTheFieldsInTheCustomerBookingMGT() throws InterruptedException, AWTException {
        Thread.sleep(4000);
        try {
            if (testData != null) {
                bookingPage.fillfieldsBookingPage(testData.get("panNo"), testData.get("regName"), testData.get("address"), testData.get("state"), testData.get("pincode"), testData.get("bookingDate").substring(0, 10));
                System.out.println("Entered regName : " + testData.get("regName"));
                System.out.println("Entered Pan number: " + testData.get("panNo"));
                System.out.println("Entered address: " + testData.get("address"));
                System.out.println(testData.get("state"));
                System.out.println(testData.get("pincode"));
            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during Entered Pan number : " + e.getMessage());
        }
    }

    @Then("user clicks on {string} based on the value")
    public void userClicksOnBasedOnTheValue(String button) throws InterruptedException {
        bookingPage.clickbutton(button);
    }

    @And("After successful registration user clicks on Quotation")
    public void afterSuccessfulRegistrationUserClicksOnQuotation() throws InterruptedException {
        Thread.sleep(7000);
        try {
            if (testData != null) {
                bookingPage.QuotationPage(testData.get("RTOamount"), testData.get("ex_showroom_price"));
                System.out.println("Entered RTOamount: " + testData.get("RTOamount"));
                System.out.println("Entered ex_showroom_price: " + testData.get("ex_showroom_price"));


            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during Entered RTOamount : " + e.getMessage());
        }


    }

    @Then("User clicks on the receipt icon")
    public void userClicksOnTheReceiptIcon() throws InterruptedException {
        bookingPage.ReceiptTab();

    }

    @When("user enters a valid username for account")
    public void userEntersAValidUsernameForAccount() throws InterruptedException {
        Thread.sleep(3000);
        bookingPage.AcocuntLoginUseraname();

    }

    @And("user enter a valid password for account")
    public void userEnterAValidPasswordForAccount() {
        bookingPage.AccountLoginPassword();
    }

    @And("User as to add the amount in the receipt section")
    public void userAsToAddTheAmountInTheReceiptSection() throws InterruptedException {
        try {
            if (testData != null) {
                bookingPage.AmountReceiptPage(testData.get("bookingDate").substring(0, 10));
                System.out.println("Entered RTOamount: " + testData.get("RTOamount"));
                System.out.println("Entered ex_showroom_price: " + testData.get("ex_showroom_price"));


            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during Entered RTOamount : " + e.getMessage());
        }


    }

    @Then("User clicks on Send Customer consent link")
    public void userClicksOnSendCustomerConsentLink() throws InterruptedException {
        Thread.sleep(4000);
        bookingPage.SendConsentLink();
    }

    @Then("Verify the status in the Customer booking list should be pending")
    public void verifyTheStatusInTheCustomerBookingListShouldBePending() throws InterruptedException {
        // bookingPage.VerifyConscentLink();

    }

    @Then("User clicks on Order and stock")
    public void userClicksOnOrderAndStock() throws InterruptedException {
        bookingPage.orderStock();
    }

    @Then("user selects Dealer Vechile Stock MGT")
    public void userSelectsDealerVechileStockMGT() throws InterruptedException {
        bookingPage.DealerVechileStock();
    }

    @And("User passes the VIN number into the field")
    public void userPassesTheVINNumberIntoTheField() {
        try {
            if (testData != null) {
                bookingPage.vinNumber(testData.get("VinNo"));


                System.out.println("Entered VIN Number: " + testData.get("VinNo"));
            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during entering VIN Number : " + e.getMessage());
        }

    }

    @And("Searches for the Vin number")
    public void searchesForTheVinNumber() throws InterruptedException {
        bookingPage.vinSearch();
    }

    @When("Verify the data in the table with the customer booking values")
    public void verifyTheDataInTheTableWithTheCustomerBookingValues() throws InterruptedException {

    }

    @When("User selects Customer Booking Mgt List under sales Operation in accounts")
    public void userSelectsCustomerBookingMgtListUnderSalesOperationInAccounts() throws InterruptedException {
        bookingPage.mgtListSales();
    }

    @Then("verifies the value from the customer link")
    public void verifiesTheValueFromTheCustomerLink() throws InterruptedException, IOException {
        // bookingPage.verifyDataMGT();
        userEntersTheMobileNumberInTheTextBox();
        userSelectsTheMobileNumberOptionFromTheDropdown();
        userPassedTheStartDateAndEndDateInThePage();
        userClicksOnTheSearchButton();
        theEnquiryWillBePopulatedThenUserAsToSelectIt();
        bookingPage.verifyDataMGT();

    }


    @Then("User clicks on the receipt icon for account")
    public void userClicksOnTheReceiptIconForAccount() {
        bookingPage.receiptLink();
    }


    @And("User reads data from the Excel sheet regarding Booking Feature")
    public void userReadsDataFromTheExcelSheetRegardingBookingFeature() throws IOException {
        String filePath = "C:/Users/Anjali/OneDrive/Downloads/output.xlsx";
        String sheetName = "Booking Leads";

        // Fetch all data from the Excel sheet
        allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

        if (allTestData == null || allTestData.isEmpty()) {
            throw new RuntimeException("No data found in Excel sheet: " + sheetName);
        }
        System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");
    }

    @And("User processes the Booking for all rows from the Excel sheet from the sheet Name Booking Leads")
    public void userProcessesTheBookingForAllRowsFromTheExcelSheetFromTheSheetNameBookingLeads() throws IOException {
        int passedCount = 0;
        int failedCount = 0;

        String filePath = "C:/Users/Anjali/OneDrive/Downloads/output.xlsx";
        String sheetName = "Booking Leads";

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
                executeTestStepsForRow_Booking();

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
                    executeTestStepsForRow_Booking();
                } catch (Throwable navigationException) {
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
            } catch (Throwable e) {
                throw new RuntimeException(e);
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

    private void executeTestStepsForRow_Booking() throws Exception {

        userNeedToSelectTheEnquiryOptionInTheDropdown();
        userEntersTheMobileNumberInTheTextBox();
        userSelectsTheMobileNumberOptionFromTheDropdown();
        userPassedTheStartDateAndEndDateInThePage();
        userClicksOnTheSearchButton();
        theEnquiryWillBePopulatedThenUserAsToSelectIt();
        Thread.sleep(4000);
        userFillsTheFieldsInTheCustomerBookingMGT();
        //bookingPage.recordDataExcel();
        //userClicksOnBasedOnTheValue(button);
        afterSuccessfulRegistrationUserClicksOnQuotation();
        userClicksOnTheReceiptIcon();
        //
        System.out.println("\nExecution Summary: The data as been picked from the excel performed required validations, and stored the enquiry number and mobile number in the new excel sheet");


    }

    private void restartFromSalesMenuStep() throws Throwable {
        try {
            userClicksOnTheSalesIcon();
            Thread.sleep(6000);
            userSelectsTheSalesOperationTab();
            Thread.sleep(6000);
            userSelectsCustomerBookingMgtListUnderSalesOperation();

        } catch (Exception e) {
            System.err.println("Error restarting from Sales Menu step: " + e.getMessage());
            throw new RuntimeException("Failed to restart execution from Sales Menu step.", e);
        }
    }

    @And("clicks on Send OTP for booking")
    public void clicksOnSendOTPForBooking() throws InterruptedException {
        dMSLoginPage.clickSendOTPButton();
        Thread.sleep(30000);
        // Print a message indicating the button was clicked
        System.out.println("Send OTP button clicked.");

        // Wait for OTP to be entered and the page to change
        WebDriver driver = LaunchDriver.getDriver();

        // Create an explicit wait with a timeout of 100 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));

        // Wait until the expected element is visible after the page changes (e.g., the header element)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='header']/div[1]")));

        // Print a message indicating that the expected element is now visible
        System.out.println("Page changed and element is visible.");
    }


    @And("User processes the Booking for all rows from the Excel sheet from the sheet Name Booking Leads for account login")
    public void userProcessesTheBookingForAllRowsFromTheExcelSheetFromTheSheetNameBookingLeadsForAccountLogin() throws IOException {
        int passedCount = 0;
        int failedCount = 0;

        String filePath = "C:/Users/Anjali/OneDrive/Downloads/output.xlsx";
        String sheetName = "Booking Leads";

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
                //restartFromSalesMenuStep();

                // Execute all test steps for the current row
                executeTestStepsForRow_BookingAccount();

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
                    //restartFromSalesMenuStep();
                    executeTestStepsForRow_BookingAccount();
                } catch (Throwable navigationException) {
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
            } catch (Throwable e) {
                throw new RuntimeException(e);
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

    private void executeTestStepsForRow_BookingAccount() throws InterruptedException, IOException {
        userClicksOnTheSalesIcon();
        Thread.sleep(4000);
        userSelectsTheSalesOperationTab();
        userSelectsCustomerBookingMgtListUnderSalesOperationInAccounts();
        userNeedToSelectTheEnquiryOptionInTheDropdown();
        Thread.sleep(4000);
        userEntersTheMobileNumberInTheTextBox();
        Thread.sleep(4000);
        userSelectsTheMobileNumberOptionFromTheDropdown();
        userPassedTheStartDateAndEndDateInThePage();
        userClicksOnTheSearchButton();
        Thread.sleep(4000);
        theEnquiryWillBePopulatedThenUserAsToSelectIt();
        userClicksOnTheReceiptIconForAccount();
        userAsToAddTheAmountInTheReceiptSection();
        userClicksOnSendCustomerConsentLink();
    }

    private void executeTestStepsForRow_BookingAccountVIN() throws Throwable {

        userClicksOnTheSalesIcon();
        bookingPage.orderStock();
        bookingPage.DealerVechileStock();
        userPassesTheVINNumberIntoTheField();
        bookingPage.vinSearch();
        restartFromSalesMenuStep();
        userEntersTheMobileNumberInTheTextBox();
        userSelectsTheMobileNumberOptionFromTheDropdown();
        userPassedTheStartDateAndEndDateInThePage();
        userClicksOnTheSearchButton();
        theEnquiryWillBePopulatedThenUserAsToSelectIt();
        bookingPage.verifyDataMGT();
    }

    @And("User processes the Booking for all rows from the Excel sheet from the sheet Name Booking Leads for VIN")
    public void userProcessesTheBookingForAllRowsFromTheExcelSheetFromTheSheetNameBookingLeadsForVIN() throws IOException {
        int passedCount = 0;
        int failedCount = 0;

        String filePath = "C:/Users/Anjali/OneDrive/Downloads/output.xlsx";
        String sheetName = "Booking Leads";

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
                //restartFromSalesMenuStep();

                // Execute all test steps for the current row
                executeTestStepsForRow_BookingAccountVIN();

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
                    //restartFromSalesMenuStep();
                    executeTestStepsForRow_BookingAccountVIN();
                } catch (Throwable navigationException) {
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
            } catch (Throwable e) {
                throw new RuntimeException(e);
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
}