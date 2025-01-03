package com.autogrid.stepDefinitions;

import com.autogrid.steps.BookingSalesOperationPage;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.ExcelReading;
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
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
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
    public void userSelectsCustomerBookingMgtListUnderSalesOperation() {
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
    public void theEnquiryWillBePopulatedThenUserAsToSelectIt() {
        bookingPage.SalesTable();
    }

    @Then("User fills the fields in the Customer Booking MGT")
    public void userFillsTheFieldsInTheCustomerBookingMGT() throws InterruptedException, AWTException {

        try {
            if (testData != null) {
                bookingPage.fillfieldsBookingPage(testData.get("panNo"));
                System.out.println("Entered Pan number: " + testData.get("panNo"));
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
        System.out.println("Entered RTOamount: " + testData.get("RTOamount"));
        try {
            if (testData != null) {
                bookingPage.QuotationPage(testData.get("RTOamount"));
                System.out.println("Entered RTOamount: " + testData.get("RTOamount"));
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
        bookingPage.AmountReceiptPage();

    }

    @Then("User clicks on Send Customer consent link")
    public void userClicksOnSendCustomerConsentLink() {
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
    public void userProcessesTheBookingForAllRowsFromTheExcelSheetFromTheSheetNameBookingLeads() {
        int passedCount = 0;
        int failedCount = 0;

        for (currentDataRowIndex = 0; currentDataRowIndex < allTestData.size(); currentDataRowIndex++) {
            System.out.println("\nProcessing Row: " + (currentDataRowIndex + 1));

            // Log the current row data
            System.out.println("Reading data for Row " + (currentDataRowIndex + 1) + ": " + allTestData.get(currentDataRowIndex));

            // Initialize testData for the current row
            testData = allTestData.get(currentDataRowIndex);
            System.out.println("Current Test Data: " + testData);

            boolean rowExecutionPassed = true;

            try {
                // Execute all test steps for the current row
                executeTestStepsForRow_Booking();

                // Log success for the current row
                System.out.println("Row " + (currentDataRowIndex + 1) + " execution PASSED.");
                passedCount++;
            } catch (Exception e) {
                // Log failure for the current row
                System.err.println("Row " + (currentDataRowIndex + 1) + " execution FAILED: " + e.getMessage());
                e.printStackTrace();
                rowExecutionPassed = false;
                failedCount++;
            } finally {
                // Ensure that execution proceeds to the next row
                if (!rowExecutionPassed) {
                    System.out.println("Row " + (currentDataRowIndex + 1) + " failed. Moving to the next row.");
                } else {
                    System.out.println("Row " + (currentDataRowIndex + 1) + " passed. Moving to the next row.");
                }
            }
    }
}

    private void executeTestStepsForRow_Booking() throws Exception {
        userClicksOnTheSalesIcon();
        userSelectsTheSalesOperationTab();
        userSelectsCustomerBookingMgtListUnderSalesOperation();
        userNeedToSelectTheEnquiryOptionInTheDropdown();

        userEntersTheMobileNumberInTheTextBox();
        userSelectsTheMobileNumberOptionFromTheDropdown();
        userPassedTheStartDateAndEndDateInThePage();
        userClicksOnTheSearchButton();

        theEnquiryWillBePopulatedThenUserAsToSelectIt();
        userFillsTheFieldsInTheCustomerBookingMGT();
        //userClicksOnBasedOnTheValue(button);
      afterSuccessfulRegistrationUserClicksOnQuotation();
        userClicksOnTheReceiptIcon();
        dMSLoginPage.launchDMSSite();
        userEntersAValidUsernameForAccount();
        userEnterAValidPasswordForAccount();
        dMSLoginPage.clickLoginButton();
        //userAsToAddTheAmountInTheReceiptSection();
        userClicksOnTheSalesIcon();
        userSelectsTheSalesOperationTab();
        userSelectsCustomerBookingMgtListUnderSalesOperation();
        userNeedToSelectTheEnquiryOptionInTheDropdown();
        userEntersTheMobileNumberInTheTextBox();
        userSelectsTheMobileNumberOptionFromTheDropdown();
        userPassedTheStartDateAndEndDateInThePage();
        userClicksOnTheSearchButton();
        theEnquiryWillBePopulatedThenUserAsToSelectIt();
        userClicksOnTheReceiptIcon();
        userAsToAddTheAmountInTheReceiptSection();
        userClicksOnSendCustomerConsentLink();
        dMSLoginPage.launchDMSSite();
        dMSLoginPage.enterUsername("S523700");
        dMSLoginPage.enterPassword("Hyundai@2024");
        dMSLoginPage.clickLoginButton();
        userClicksOnTheSalesIcon();
        userClicksOnOrderAndStock();
        userSelectsDealerVechileStockMGT();
        searchesForTheVinNumber();
        verifiesTheValueFromTheCustomerLink();
        bookingPage.mgtListSales();
        bookingPage.verifyDataMGT();
    }
}


