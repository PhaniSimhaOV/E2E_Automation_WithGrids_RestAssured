package com.autogrid.stepDefinitions;

import com.autogrid.steps.BookingSalesOperationPage;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.steps.TestDriveEnquiryPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.ExcelWriting;
import com.autogrid.utils.LaunchDriver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static com.autogrid.utils.LaunchDriver.getDriver;
public class TestDriveEnquiryStepDefinition {
    CommonActions commonActions;
    DMSLoginPage dMSLoginPage;
    TestDriveEnquiryPage testDriveEnquiryPage;
    BookingSalesOperationPage bookingPage;
    private Map<String, String> testData; // Stores data from Excel
    private List<Map<String, String>> allTestData; // List to store all data rows from Excel
    private int currentDataRowIndex = 0;

    public TestDriveEnquiryStepDefinition() throws Exception {
        WebDriver driver = getDriver();
        this.bookingPage = new BookingSalesOperationPage(driver);
        PageFactory.initElements(driver, bookingPage);
        this.dMSLoginPage = new DMSLoginPage(driver);
        PageFactory.initElements(driver, dMSLoginPage);
        this.testDriveEnquiryPage = new TestDriveEnquiryPage(driver);
        PageFactory.initElements(driver, testDriveEnquiryPage);
    }

    @And("User reads data from the Excel sheet regarding TestDrive Appointment")
    public void userReadsDataFromTheExcelSheetRegardingTestDriveAppointment() throws IOException {

        String filePath = "C:/Users/Anjali/OneDrive/Desktop/output.xlsx";
        String sheetName = "Test Drive - Enquiry";
        // Fetch all data from the Excel sheet
        allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);
        if (allTestData == null || allTestData.isEmpty()) {
            throw new RuntimeException("No data found in Excel sheet: " + sheetName);
        }
        System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");
    }


    @And("User processes the TestDrive appointment for walk-in Enquiry for all rows from the Excel sheet")
    public void userProcessesTheTestDriveAppointmentForWalkInEnquiryForAllRowsFromTheExcelSheet() throws IOException {
        int passedCount = 0;
        int failedCount = 0;

        String filePath = "C:/Users/Anjali/OneDrive/Desktop/output.xlsx";
        String sheetName = "Test Drive - Enquiry";
        ExcelWriting.addColumnToSheet(filePath, sheetName, "Error Logs");
        for (int currentDataRowIndex = 0; currentDataRowIndex < allTestData.size(); currentDataRowIndex++) {
            System.out.println("\nProcessing Row: " + (currentDataRowIndex + 1));

            // Fetch and log current row data
            testData = allTestData.get(currentDataRowIndex);
            System.out.println("Current Test Data: " + testData);

            boolean rowExecutionPassed = true;

            try {
                System.out.println("Refreshing the browser to reset the application state...");
                LaunchDriver.getDriver().navigate().refresh();
                restartFromTestDriveSalesMenuStep();
                executeTestStepsForRow_TestDrive();
                System.out.println("Row " + (currentDataRowIndex + 1) + " execution PASSED.");
                ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", "PASSED");
                passedCount++;
            } catch (Exception e) {
                try {
                    System.out.println("Navigating to the application's base URL...");
                    LaunchDriver.getDriver().navigate().refresh();
                    restartFromTestDriveSalesMenuStep();
                    executeTestStepsForRow_TestDrive();
                } catch (Exception navigationException) {
                    System.err.println("Error while navigating to the base URL: " + navigationException.getMessage());
                    navigationException.printStackTrace();
                } catch (Throwable ex) {
                    throw new RuntimeException(ex);
                }
                String errorMessage = "Row " + (currentDataRowIndex + 1) + " execution FAILED: " + e.getMessage();
                System.err.println(errorMessage);
                e.printStackTrace();
                ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", errorMessage);
                rowExecutionPassed = false;
                failedCount++;
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
    }

    private void restartFromTestDriveSalesMenuStep() throws Throwable {
        try {
            testDriveEnquiryPage.Sales();
            testDriveEnquiryPage.CustomerEnquiry();
            Thread.sleep(6000);
            testDriveEnquiryPage.CustomerEnquiryLink();
        } catch (Exception e) {
            System.err.println("Error restarting from Sales Menu step: " + e.getMessage());
            throw new RuntimeException("Failed to restart execution from Sales Menu step.", e);
        }
    }
    public void mobileNumberEntry(){
        try {
            if (testData != null) {
                String mobileNumber=testData.get("Mobile");
                testDriveEnquiryPage.mobileNumberData(testData.get("Mobile"));
                System.out.println("Entered Lead Mobile Number: " + testData.get("Mobile"));
            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during entering Lead Mobile Number : " + e.getMessage());
        }
    }
    public void BasicData(){
        try {
            if (testData != null) {
                testDriveEnquiryPage.BasicInfo(testData.get("TDOffer"),testData.get("TDVin"),testData.get("DepositCertificate"));
            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during entering the values" + e.getMessage());
        }
    }
    public void testDriveAppointement(){
        try {
            if (testData != null) {
                testDriveEnquiryPage.TestDriveAppointmentTab(testData.get("test_drive_datetime"));
            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during entering the values" + e.getMessage());
        }
    }
    public void followupdetails(){
        try {
            if (testData != null) {
                testDriveEnquiryPage.FollowUpTabDetails(testData.get("NextFollowUptime"),testData.get("TextFollow"),testData.get("FollowUptype"),testData.get("nextFollowUpType"),testData.get("EnquiryTypeValue"));
            } else {
                throw new RuntimeException("Test data is not initialized.");
            }
        } catch (Exception e) {
            System.err.println("Error during entering the values" + e.getMessage());
        }

    }
    private void executeTestStepsForRow_TestDrive() throws Exception {
        testDriveEnquiryPage.walkinEnquiry();
        mobileNumberEntry();
        testDriveEnquiryPage.selecttheEntry();
        BasicData();
        testDriveEnquiryPage.FollowUPTab();
        followupdetails();
        testDriveAppointement();
        getDriver().navigate().refresh();
        System.out.println("\nExecution Summary: The data as been picked from the excel performed required validations, and stored the enquiry number and mobile number in the new excel sheet");
    }

    @And("User reads data from the Excel sheet regarding TestDrive Appointment for leads")
    public void userReadsDataFromTheExcelSheetRegardingTestDriveAppointmentForLeads() throws IOException {
        String filePath = "C:/Users/Anjali/OneDrive/Desktop/output.xlsx";
        String sheetName = "Test Drive - Leads";
        // Fetch all data from the Excel sheet
        allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);
        if (allTestData == null || allTestData.isEmpty()) {
            throw new RuntimeException("No data found in Excel sheet: " + sheetName);
        }
        System.out.println("All Test Data Loaded: " + allTestData.size() + " rows.");

    }

    @And("User processes the TestDrive appointment for Leads Enquiry for all rows from the Excel sheet")
    public void userProcessesTheTestDriveAppointmentForLeadsEnquiryForAllRowsFromTheExcelSheet() throws IOException {
        int passedCount = 0;
        int failedCount = 0;

        String filePath = "C:/Users/Anjali/OneDrive/Desktop/output.xlsx";
        String sheetName = "Test Drive - Enquiry";
        ExcelWriting.addColumnToSheet(filePath, sheetName, "Error Logs");
        for (int currentDataRowIndex = 0; currentDataRowIndex < allTestData.size(); currentDataRowIndex++) {
            System.out.println("\nProcessing Row: " + (currentDataRowIndex + 1));

            // Fetch and log current row data
            testData = allTestData.get(currentDataRowIndex);
            System.out.println("Current Test Data: " + testData);

            boolean rowExecutionPassed = true;

            try {
                System.out.println("Refreshing the browser to reset the application state...");
                LaunchDriver.getDriver().navigate().refresh();
                //restartFromTestDriveSalesMenuStep();
                executeTestStepsForRow_TestDriveLeads();
                System.out.println("Row " + (currentDataRowIndex + 1) + " execution PASSED.");
                ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", "PASSED");
                passedCount++;
            } catch (Exception e) {
                try {
                    System.out.println("Navigating to the application's base URL...");
                    LaunchDriver.getDriver().navigate().refresh();
                    restartFromTestDriveSalesMenuStep();
                    executeTestStepsForRow_TestDriveLeads();
                } catch (Exception navigationException) {
                    System.err.println("Error while navigating to the base URL: " + navigationException.getMessage());
                    navigationException.printStackTrace();
                } catch (Throwable ex) {
                    throw new RuntimeException(ex);
                }
                String errorMessage = "Row " + (currentDataRowIndex + 1) + " execution FAILED: " + e.getMessage();
                System.err.println(errorMessage);
                e.printStackTrace();
                ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", errorMessage);
                rowExecutionPassed = false;
                failedCount++;
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
    }

    private void executeTestStepsForRow_TestDriveLeads() {

    }

}

