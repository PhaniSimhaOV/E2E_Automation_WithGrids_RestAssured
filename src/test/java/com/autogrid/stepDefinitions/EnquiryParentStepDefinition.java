package com.autogrid.stepDefinitions;


import com.autogrid.steps.*;
import com.autogrid.utils.*;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnquiryParentStepDefinition {

    private static final Logger logger = LoggerFactory.getLogger(EnquiryParentStepDefinition.class);

    EnquiryParentSteps enquiryParentSteps;
    CommonActionsAndroid commonActions;
    private Map<String, String> testData;
    private List<Map<String, String>> allTestData;

    public EnquiryParentStepDefinition() {
        ;
        this.commonActions = new CommonActionsAndroid(LaunchAndroidDriver.getAndroidDriver());
        this.enquiryParentSteps = new EnquiryParentSteps(LaunchAndroidDriver.getAndroidDriver());

        PageFactory.initElements(LaunchAndroidDriver.getAndroidDriver(), enquiryParentSteps);
    }

    @Then("enter the user credentials")
    public void enterTheUserCredentials() {
        enquiryParentSteps.enterCredentials();
    }

    @Then("click on Login")
    public void clickOnLogin() {
        enquiryParentSteps.clickLoginButtonThenSkipAndAllow();
    }

    @Then("Create the Lead and get the Enquiry No in the Excel")
    public void createTheLeadAndGetTheEnquiryNoInTheExcel() throws InterruptedException, IOException {

        int passedCount = 0;
        int failedCount = 0;

        String filePath = "./output.xlsx";
        String sheetName = "Enquiry Lead Creation";

        allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

        // Add a new column for error logging
        ExcelWriting.addColumnToSheet(filePath, sheetName, "Error Logs");

        for (int currentDataRowIndex = 0; currentDataRowIndex < allTestData.size(); currentDataRowIndex++) {
            System.out.println("\nProcessing Row: " + (currentDataRowIndex + 1));

            // Fetch and log current row data
            testData = allTestData.get(currentDataRowIndex);
            System.out.println("Current Test Data: " + testData);

            boolean rowExecutionPassed = true;

            try {

                //Customer Details
                enquiryParentSteps.clickOnEnquiries();
                enquiryParentSteps.clickOnEnquiriesPlusIcon();
                commonActions.sendText(enquiryParentSteps.getTextFieldMobileNumber(), testData.get("mobileNo"));
                commonActions.sendText(enquiryParentSteps.getTextFieldCustomerName(), testData.get("custName"));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownCustomerType(), ExcelUtility.getMappedValue(testData.get("enquiryType")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownGender(), testData.get("gender").isEmpty() ? "Unknown" : ExcelUtility.getMappedValue(testData.get("gender")));

                commonActions.sendText(enquiryParentSteps.getTxtWhatsapp(), testData.get("mobileNo"));

                String email = !testData.get("email").equalsIgnoreCase("") || !testData.get("email").equalsIgnoreCase("0") ? testData.get("email")
                        : testData.get("custName").toLowerCase().replaceAll(" ", "") + "@gmail.com";
                commonActions.sendText(enquiryParentSteps.getTextFieldEmail(), email);

                commonActions.sendText(enquiryParentSteps.getTextFieldPinCode(), testData.get("pincode"));

                commonActions.sendTextAndSelectValues(testData.get("pincode") + " - " + testData.get("village"));

                commonActions.sendText(enquiryParentSteps.getTextFieldAddress(), testData.get("address"));

                commonActions.scrollDown();

                String visitedWithFamily = testData.get("visitedWith").isEmpty()
                        || testData.get("visitedWith").equalsIgnoreCase("0") ? "N" : ExcelUtility.getMappedValue(testData.get("visitedWith"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVisitedWithFamily(), visitedWithFamily);
                enquiryParentSteps.clickOnContinueButton();

                //Complete Enquiry Details
                String carModel = ExcelUtility.getMappedValue(testData.get("model")) == null ? testData.get("model") : ExcelUtility.getMappedValue(testData.get("model"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownModel(), carModel);

                String fuelType = testData.get("fuel").equalsIgnoreCase("Electric") ? "Electric Vehicle"
                        : testData.get("fuel").equalsIgnoreCase("PETROL") ? "Petrol"
                        : testData.get("fuel").equalsIgnoreCase("DIESEL") ? "Diesel"
                        : testData.get("fuel").equalsIgnoreCase("LPG_Petrol") ? "LPG" : testData.get("fuel");
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFuelType(), fuelType);

                String variantType = ExcelUtility.getMappedValue(testData.get("variant")) == null ? testData.get("variant") : ExcelUtility.getMappedValue(testData.get("variant"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVariant(), variantType);

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExteriorColor(), ExcelUtility.getMappedValue(testData.get("color")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownInteriorColor(), ExcelUtility.getMappedValue(testData.get("interior_color")));


                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSourceType(), ExcelUtility.getMappedValue(testData.get("source")));
                commonActions.scrollDown();

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSubSourceType(), ExcelUtility.getMappedValue(testData.get("subSource")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownEnquiryCategory(), ExcelUtility.getMappedValue(testData.get("enquiryType")));

                String expectedPlan = testData.get("expectedPlan").isBlank() || testData.get("expectedPlan").equalsIgnoreCase("0") ? "Within 2 months" : ExcelUtility.getMappedValue(testData.get("expectedPlan"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExpectedPlanToPurchaseVehicle(), expectedPlan);

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownWillingToMakeImmediateBooking(),
                        testData.get("immediate_booking_flag").isEmpty() || testData.get("immediate_booking_flag").equalsIgnoreCase("0")
                                ? "N" : ExcelUtility.getMappedValue(testData.get("immediate_booking_flag")));

                if (testData.get("financeReq").equalsIgnoreCase("Y") || testData.get("financeReq").equalsIgnoreCase("0")) {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), testData.get("financeReq"));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceOptions(),
                            testData.get("financeOption").isBlank() || testData.get("financeOption").equalsIgnoreCase("0") ? "Documents not Submitted" : ExcelUtility.getMappedValue(testData.get("financeOption")));
                } else {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), testData.get("financeReq"));
                }
                enquiryParentSteps.clickContinueButton();

                //Exchange Section
                if (!testData.get("BuyerType").equals("Exchange Buyer") || testData.get("BuyerType").isEmpty() || testData.get("BuyerType").equalsIgnoreCase("0")) {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), "No");
                } else {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), ExcelUtility.getMappedValue(testData.get("BuyerType")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarMake(), testData.get("exchangeMake").isEmpty() ||
                            testData.get("exchangeMake").equalsIgnoreCase("0")
                            ? "Hyundai" : ExcelUtility.getMappedValue(testData.get("exchangeMake")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarModel(), testData.get("exchangeModel").isEmpty() ||
                            testData.get("exchangeModel").equalsIgnoreCase("0")
                            ? "EON" : ExcelUtility.getMappedValue(testData.get("exchangeModel")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarFuelType(), testData.get("exchangeFuelType").isEmpty() ||
                            testData.get("exchangeFuelType").equalsIgnoreCase("0")
                            ? "CNG" : ExcelUtility.getMappedValue(testData.get("exchangeFuelType")));

                    commonActions.sendText(enquiryParentSteps.getPresentCarModelYear(), testData.get("exchangeModelYear").isEmpty() ||
                            testData.get("exchangeModelYear").equalsIgnoreCase("0")
                            ? "2010" : testData.get("exchangeModelYear"));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarDoUWantToExchange(), ExcelUtility.getMappedValue(testData.get("BuyerType")));

                    commonActions.scrollDown();
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarSelectSource(), testData.get("exchangeSource").isEmpty() ||
                            testData.get("exchangeSource").equalsIgnoreCase("0")
                            ? "Inhouse" : testData.get("exchangeSource"));

                    commonActions.sendText(enquiryParentSteps.getXchangeCarQuotedPrice(), testData.get("exchangeQuotedPrice").isEmpty() ||
                            testData.get("exchangeQuotedPrice").equalsIgnoreCase("0")
                            ? "240000" : testData.get("exchangeQuotedPrice"));

                    commonActions.sendText(enquiryParentSteps.getXchangeCarExpectedPrice(), testData.get("exchangeExpectedPrice").isEmpty() ||
                            testData.get("exchangeExpectedPrice").equalsIgnoreCase("0")
                            ? "240000" : testData.get("exchangeExpectedPrice"));

                    if (testData.get("certificateOfDeposit").equals("No") || testData.get("certificateOfDeposit").isEmpty() ||
                            testData.get("certificateOfDeposit").equalsIgnoreCase("0")) {
                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapCategory(), testData.get("certificateOfDeposit"));
                    } else {
                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapCategory(), testData.get("certificateOfDeposit"));
                        commonActions.scrollDown();

                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapThroughHyundai(), testData.get("exchangeScrapThroughHyundai").isEmpty()
                                || testData.get("exchangeScrapThroughHyundai").equalsIgnoreCase("0")
                                ? "No" : testData.get("exchangeScrapThroughHyundai"));
                    }
                }

                enquiryParentSteps.clickOnSubmit();

                Assert.assertEquals(commonActions.getElementText(enquiryParentSteps.getTextSuccessMessage()),
                        "Successfully Created Enquiry", "The Success Message is NOT as expected. Please check");
                logger.info("The created Lead id is: {}", commonActions.getElementText(enquiryParentSteps.getTextEnquiryNumber()));
                ExcelUtility.saveTextToExcel(commonActions.getElementText(enquiryParentSteps.getTextEnquiryNumber())
                        + " is created for Mobile Number: " + testData.get("mobileNo") + " on " + LocalDate.now());
                commonActions.clickElement(enquiryParentSteps.getButtonOk());

            } catch (Exception e) {
                try {


                    //Customer Details
                enquiryParentSteps.clickOnEnquiries();
                enquiryParentSteps.clickOnEnquiriesPlusIcon();
                commonActions.sendText(enquiryParentSteps.getTextFieldMobileNumber(), testData.get("mobileNo"));
                commonActions.sendText(enquiryParentSteps.getTextFieldCustomerName(), testData.get("custName"));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownCustomerType(), ExcelUtility.getMappedValue(testData.get("enquiryType")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownGender(), testData.get("gender").isEmpty() ? "Unknown" : ExcelUtility.getMappedValue(testData.get("gender")));

                commonActions.sendText(enquiryParentSteps.getTxtWhatsapp(), testData.get("mobileNo"));

                String email = !testData.get("email").equalsIgnoreCase("") || !testData.get("email").equalsIgnoreCase("0") ? testData.get("email")
                        : testData.get("custName").toLowerCase().replaceAll(" ", "") + "@gmail.com";
                commonActions.sendText(enquiryParentSteps.getTextFieldEmail(), email);

                commonActions.sendText(enquiryParentSteps.getTextFieldPinCode(), testData.get("pincode"));

                commonActions.sendTextAndSelectValues(testData.get("pincode") + " - " + testData.get("village"));

                commonActions.sendText(enquiryParentSteps.getTextFieldAddress(), testData.get("address"));

                commonActions.scrollDown();

                String visitedWithFamily = testData.get("visitedWith").isEmpty()
                        || testData.get("visitedWith").equalsIgnoreCase("0") ? "N" : ExcelUtility.getMappedValue(testData.get("visitedWith"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVisitedWithFamily(), visitedWithFamily);
                enquiryParentSteps.clickOnContinueButton();

                //Complete Enquiry Details
                String carModel = ExcelUtility.getMappedValue(testData.get("model")) == null ? testData.get("model") : ExcelUtility.getMappedValue(testData.get("model"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownModel(), carModel);

                String fuelType = testData.get("fuel").equalsIgnoreCase("Electric") ? "Electric Vehicle"
                        : testData.get("fuel").equalsIgnoreCase("PETROL") ? "Petrol"
                        : testData.get("fuel").equalsIgnoreCase("DIESEL") ? "Diesel"
                        : testData.get("fuel").equalsIgnoreCase("LPG_Petrol") ? "LPG" : testData.get("fuel");
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFuelType(), fuelType);

                String variantType = ExcelUtility.getMappedValue(testData.get("variant")) == null ? testData.get("variant") : ExcelUtility.getMappedValue(testData.get("variant"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVariant(), variantType);

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExteriorColor(), ExcelUtility.getMappedValue(testData.get("color")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownInteriorColor(), ExcelUtility.getMappedValue(testData.get("interior_color")));


                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSourceType(), ExcelUtility.getMappedValue(testData.get("source")));
                commonActions.scrollDown();

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSubSourceType(), ExcelUtility.getMappedValue(testData.get("subSource")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownEnquiryCategory(), ExcelUtility.getMappedValue(testData.get("enquiryType")));

                String expectedPlan = testData.get("expectedPlan").isBlank() || testData.get("expectedPlan").equalsIgnoreCase("0") ? "Within 2 months" : ExcelUtility.getMappedValue(testData.get("expectedPlan"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExpectedPlanToPurchaseVehicle(), expectedPlan);

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownWillingToMakeImmediateBooking(),
                        testData.get("immediate_booking_flag").isEmpty() || testData.get("immediate_booking_flag").equalsIgnoreCase("0")
                                ? "N" : ExcelUtility.getMappedValue(testData.get("immediate_booking_flag")));

                if (testData.get("financeReq").equalsIgnoreCase("Y") || testData.get("financeReq").equalsIgnoreCase("0")) {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), testData.get("financeReq"));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceOptions(),
                            testData.get("financeOption").isBlank() || testData.get("financeOption").equalsIgnoreCase("0") ? "Documents not Submitted" : ExcelUtility.getMappedValue(testData.get("financeOption")));
                } else {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), testData.get("financeReq"));
                }
                enquiryParentSteps.clickContinueButton();

                //Exchange Section
                if (!testData.get("BuyerType").equals("Exchange Buyer") || testData.get("BuyerType").isEmpty() || testData.get("BuyerType").equalsIgnoreCase("0")) {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), "No");
                } else {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), ExcelUtility.getMappedValue(testData.get("BuyerType")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarMake(), testData.get("exchangeMake").isEmpty() ||
                            testData.get("exchangeMake").equalsIgnoreCase("0")
                            ? "Hyundai" : ExcelUtility.getMappedValue(testData.get("exchangeMake")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarModel(), testData.get("exchangeModel").isEmpty() ||
                            testData.get("exchangeModel").equalsIgnoreCase("0")
                            ? "EON" : ExcelUtility.getMappedValue(testData.get("exchangeModel")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarFuelType(), testData.get("exchangeFuelType").isEmpty() ||
                            testData.get("exchangeFuelType").equalsIgnoreCase("0")
                            ? "CNG" : ExcelUtility.getMappedValue(testData.get("exchangeFuelType")));

                    commonActions.sendText(enquiryParentSteps.getPresentCarModelYear(), testData.get("exchangeModelYear").isEmpty() ||
                            testData.get("exchangeModelYear").equalsIgnoreCase("0")
                            ? "2010" : testData.get("exchangeModelYear"));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarDoUWantToExchange(), ExcelUtility.getMappedValue(testData.get("BuyerType")));

                    commonActions.scrollDown();
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarSelectSource(), testData.get("exchangeSource").isEmpty() ||
                            testData.get("exchangeSource").equalsIgnoreCase("0")
                            ? "Inhouse" : testData.get("exchangeSource"));

                    commonActions.sendText(enquiryParentSteps.getXchangeCarQuotedPrice(), testData.get("exchangeQuotedPrice").isEmpty() ||
                            testData.get("exchangeQuotedPrice").equalsIgnoreCase("0")
                            ? "240000" : testData.get("exchangeQuotedPrice"));

                    commonActions.sendText(enquiryParentSteps.getXchangeCarExpectedPrice(), testData.get("exchangeExpectedPrice").isEmpty() ||
                            testData.get("exchangeExpectedPrice").equalsIgnoreCase("0")
                            ? "240000" : testData.get("exchangeExpectedPrice"));

                    if (testData.get("certificateOfDeposit").equals("No") || testData.get("certificateOfDeposit").isEmpty() ||
                            testData.get("certificateOfDeposit").equalsIgnoreCase("0")) {
                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapCategory(), testData.get("certificateOfDeposit"));
                    } else {
                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapCategory(), testData.get("certificateOfDeposit"));
                        commonActions.scrollDown();

                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapThroughHyundai(), testData.get("exchangeScrapThroughHyundai").isEmpty()
                                || testData.get("exchangeScrapThroughHyundai").equalsIgnoreCase("0")
                                ? "No" : testData.get("exchangeScrapThroughHyundai"));
                    }
                }

                enquiryParentSteps.clickOnSubmit();

                Assert.assertEquals(commonActions.getElementText(enquiryParentSteps.getTextSuccessMessage()),
                        "Successfully Created Enquiry", "The Success Message is NOT as expected. Please check");
                logger.info("The created Lead id is: {}", commonActions.getElementText(enquiryParentSteps.getTextEnquiryNumber()));
                ExcelUtility.saveTextToExcel(commonActions.getElementText(enquiryParentSteps.getTextEnquiryNumber())
                        + " is created for Mobile Number: " + testData.get("mobileNo") + " on " + LocalDate.now());
                commonActions.clickElement(enquiryParentSteps.getButtonOk());

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
        }
    }

    @Then("Complete the Test Drive")
    public void completeTheTestDrive() throws InterruptedException, IOException {

        int passedCount = 0;
        int failedCount = 0;

        String filePath = "./output.xlsx";
        String sheetName = "Test Drive - Enquiry";

        allTestData = ExcelReading.getAllDataFromExcel(filePath, sheetName);

        // Add a new column for error logging
        ExcelWriting.addColumnToSheet(filePath, sheetName, "Error Logs");

        for (int currentDataRowIndex = 0; currentDataRowIndex < allTestData.size(); currentDataRowIndex++) {
            System.out.println("\nProcessing Row: " + (currentDataRowIndex + 1));

            // Fetch and log current row data
            testData = allTestData.get(currentDataRowIndex);
            System.out.println("Current Test Data: " + testData);

            boolean rowExecutionPassed = true;

            try {

//                enquiryParentSteps.clickOnEnquiries();
//
//                //click on search icon and search the created lead with mobile number
//                enquiryParentSteps.clickOnEnquiriesFilterIcon();
//                enquiryParentSteps.clickOnEnquiriesNewestToOldest();
//
//                enquiryParentSteps.clickOnSearchIcon();
//                commonActions.sendText(enquiryParentSteps.getTxtFieldMobileNumber(), testData.get("mobileNo"));
//                enquiryParentSteps.clickOnApplyButton();
//
//                //verify the created Lead is present after search
//                enquiryParentSteps.validateTheRequiredLeadIsPresent();
//                enquiryParentSteps.clickOnTheLead();
//
//                //complete and close the Test Drive flow
//                enquiryParentSteps.clickOnTestDriverTab();
//
//                try {
//                    if (enquiryParentSteps.getDrpDownTestDriver().isDisplayed()) {
//                        enquiryParentSteps.clickOnTestDriveOffer();
//                        commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownFuelType(), testData.get("fuelType"));
//
//
//                        if(testData.get("vin_number").isEmpty() || testData.get("vin_number").equalsIgnoreCase("0")){
//                            enquiryParentSteps.getDropDownSelectVinNumber().click();
//                            Thread.sleep(1000);
//                            LaunchAndroidDriver.getAndroidDriver().findElement(By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\"][2]"))
//                                    .click();
//                        }else{
//                            commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSelectVinNumber(), testData.get("vin_number")); // Need to check this
//                        }
//
//                        enquiryParentSteps.selectTimeSlot();
//                        enquiryParentSteps.clickOnScheduleTestDriveButton();
//                    }
//                } catch (Exception e) {
//                    System.out.println("Element is not present in the DOM.");
//                }
//
//                //click on the Followup tab and complete flow within
//                enquiryParentSteps.clickOnFollowUpTab();
//                commonActions.clickElement(enquiryParentSteps.getBtnAddNewFollowup());
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownFollowupType(), testData.get("FollowUpPhoneType"));
//                commonActions.sendText(enquiryParentSteps.getTxtInputSchemeOffered(), testData.get("SchemeText"));
//                commonActions.sendText(enquiryParentSteps.getTxtInputRemarks(), testData.get("followUpRemarks"));
//                commonActions.scrollDown();
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownNextFollowupType(), testData.get("FollowUpPhoneType"));
//                commonActions.clickElement(enquiryParentSteps.getBtnNextFollowupSubmit());
//
//                //Navigate Back to Home Page and click on Smart Consulting
//                enquiryParentSteps.navigateBackToHomePage();
                enquiryParentSteps.clickOnShowMoreDropdownAndClickOnSmartConsulting();

                //close the Test Drive Flow with the selected Model
                enquiryParentSteps.downloadCataloguePart1();

                String CatalogueModelName = ExcelUtility.getMappedValue(testData.get("model"));
                commonActions.scrollDownToElement(CatalogueModelName);
                enquiryParentSteps.downloadCataloguePart2(CatalogueModelName);
                new WebDriverWait(LaunchAndroidDriver.getAndroidDriver(), Duration.ofSeconds(900))
                        .until(ExpectedConditions.textToBePresentInElement(enquiryParentSteps.getDownloadProgressBar(), "Downloaded"));

//                enquiryParentSteps.clickOnModelAndReadCataloguePart1();
//                commonActions.clickElement(enquiryParentSteps.getCatalogue());
//                enquiryParentSteps.clickOnModelAndReadCataloguePart2();
                commonActions.navigateBack();
                commonActions.navigateBack();
                enquiryParentSteps.clickOnEnquiries();
                enquiryParentSteps.clickOnSearchIcon();
                commonActions.sendText(enquiryParentSteps.getTxtFieldMobileNumber(), testData.get("mobileNo"));
                enquiryParentSteps.clickOnApplyButton();
                enquiryParentSteps.validateTheRequiredLeadIsPresent();
                enquiryParentSteps.clickOnTheLead();
                enquiryParentSteps.clickOnTestDriverTab();
                enquiryParentSteps.clickOnLatestCreatedTestDriverLead();
                commonActions.clickElement(enquiryParentSteps.getStartTestDriverButton());
                commonActions.clickElement(enquiryParentSteps.getSubmitButtonToStartTestDriver());
                commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectFuelType(), testData.get("fuelType"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectTransmission(), testData.get("TestTDriveTransmissionType"));
                String variant = testData.get("variant").contains("N6") ? "N6" : "N8";
                commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectVariant(), variant);

                commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectLanguage(), "English");
                commonActions.clickElement(enquiryParentSteps.getNextButton());
                enquiryParentSteps.clickOnTilesWithinTestDriver();
                commonActions.scrollDown();
                enquiryParentSteps.clickOnTilesWithinTestDriver();
                commonActions.scrollDown();
                enquiryParentSteps.clickOnTilesWithinTestDriver();
                commonActions.clickElement(enquiryParentSteps.getCompleteTestDriveCheckBox());
                commonActions.clickElement(enquiryParentSteps.getFinishButton());
                commonActions.clickElement(enquiryParentSteps.getBtnCompleteTestDrive());
                Assert.assertTrue(enquiryParentSteps.getTxtSuccessfulTestDriveCompleted().isDisplayed());
                commonActions.navigateBack();


                // Log success
                System.out.println("Row " + (currentDataRowIndex + 1) + " execution PASSED.");
                ExcelWriting.updateCell(filePath, sheetName, currentDataRowIndex, "Error Logs", "PASSED");
                passedCount++;
            } catch (Exception e) {
                try {

                    Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
                    Runtime.getRuntime().exec("adb shell am start -n com.hyundai.ndms/com.hyundai.ndms.activities.SplashActivity");

                    enquiryParentSteps.clickOnEnquiries();

                    //click on search icon and search the created lead with mobile number
                    enquiryParentSteps.clickOnEnquiriesFilterIcon();
                    enquiryParentSteps.clickOnEnquiriesNewestToOldest();

                    enquiryParentSteps.clickOnSearchIcon();
                    commonActions.sendText(enquiryParentSteps.getTxtFieldMobileNumber(), testData.get("mobileNo"));
                    enquiryParentSteps.clickOnApplyButton();

                    //verify the created Lead is present after search
                    enquiryParentSteps.validateTheRequiredLeadIsPresent();
                    enquiryParentSteps.clickOnTheLead();

                    //complete and close the Test Drive flow
                    enquiryParentSteps.clickOnTestDriverTab();

                    try {
                        if (enquiryParentSteps.getDrpDownTestDriver().isDisplayed()) {
                            enquiryParentSteps.clickOnTestDriveOffer();
                            commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownFuelType(), testData.get("fuelType"));
                            commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSelectVinNumber(), testData.get("vin_number")); // Need to check this
                            enquiryParentSteps.selectTimeSlot();
                            enquiryParentSteps.clickOnScheduleTestDriveButton();
                        }
                    } catch (Exception f) {
                        System.out.println("Element is not present in the DOM.");
                    }

                    //click on the Followup tab and complete flow within
                    enquiryParentSteps.clickOnFollowUpTab();
                    commonActions.clickElement(enquiryParentSteps.getBtnAddNewFollowup());
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownFollowupType(), testData.get("FollowUpPhoneType"));
                    commonActions.sendText(enquiryParentSteps.getTxtInputSchemeOffered(), testData.get("SchemeText"));
                    commonActions.sendText(enquiryParentSteps.getTxtInputRemarks(), testData.get("followUpRemarks"));
                    commonActions.scrollDown();
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownNextFollowupType(), testData.get("FollowUpPhoneType"));
                    commonActions.clickElement(enquiryParentSteps.getBtnNextFollowupSubmit());

                    //Navigate Back to Home Page and click on Smart Consulting
                    enquiryParentSteps.navigateBackToHomePage();
                    enquiryParentSteps.clickOnShowMoreDropdownAndClickOnSmartConsulting();

                    //close the Test Drive Flow with the selected Model
                    enquiryParentSteps.downloadCataloguePart1();

                    String CatalogueModelName = ExcelUtility.getMappedValue(testData.get("model"));
                    System.out.println(CatalogueModelName);
                    commonActions.scrollDownToElement(CatalogueModelName);
                    enquiryParentSteps.downloadCataloguePart2(CatalogueModelName);
                    new WebDriverWait(LaunchAndroidDriver.getAndroidDriver(), Duration.ofSeconds(900))
                            .until(ExpectedConditions.textToBePresentInElement(enquiryParentSteps.getDownloadProgressBar(), "Downloaded"));

//                enquiryParentSteps.clickOnModelAndReadCataloguePart1();
//                commonActions.clickElement(enquiryParentSteps.getCatalogue());
//                enquiryParentSteps.clickOnModelAndReadCataloguePart2();
                    commonActions.navigateBack();
                    commonActions.navigateBack();
                    enquiryParentSteps.clickOnEnquiries();
                    enquiryParentSteps.clickOnSearchIcon();
                    commonActions.sendText(enquiryParentSteps.getTxtFieldMobileNumber(), testData.get("mobileNo"));
                    enquiryParentSteps.clickOnApplyButton();
                    enquiryParentSteps.validateTheRequiredLeadIsPresent();
                    enquiryParentSteps.clickOnTheLead();
                    enquiryParentSteps.clickOnTestDriverTab();
                    enquiryParentSteps.clickOnLatestCreatedTestDriverLead();
                    commonActions.clickElement(enquiryParentSteps.getStartTestDriverButton());
                    commonActions.clickElement(enquiryParentSteps.getSubmitButtonToStartTestDriver());
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectFuelType(), testData.get("fuelType"));
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectTransmission(), testData.get("TestTDriveTransmissionType"));
                    String variant = testData.get("variant").contains("N6") ? "N6" : "N8";
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectVariant(), variant);

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectLanguage(), "English");
                    commonActions.clickElement(enquiryParentSteps.getNextButton());
                    enquiryParentSteps.clickOnTilesWithinTestDriver();
                    commonActions.scrollDown();
                    enquiryParentSteps.clickOnTilesWithinTestDriver();
                    commonActions.scrollDown();
                    enquiryParentSteps.clickOnTilesWithinTestDriver();
                    commonActions.clickElement(enquiryParentSteps.getCompleteTestDriveCheckBox());
                    commonActions.clickElement(enquiryParentSteps.getFinishButton());
                    commonActions.clickElement(enquiryParentSteps.getBtnCompleteTestDrive());
                    Assert.assertTrue(enquiryParentSteps.getTxtSuccessfulTestDriveCompleted().isDisplayed());
                    commonActions.navigateBack();

                } catch (Exception navigationException) {
                    Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
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

//    @Then("Create the Lead and get the Enquiry No in the Excel")
//    public void createTheLeadAndGetTheEnquiryNoInTheExcel(IDataReader dataTable) throws InterruptedException, IOException {
//
//        List<Map<String, String>> data = dataTable.getAllRows();
//        String status = "Passed";
//        for (int i = 0; i < dataTable.getAllRows().size() + 1; i++) {
//            try {
//                if (i != 0) {
//                    Runtime.getRuntime().exec("adb shell am start -n com.hyundai.ndms/com.hyundai.ndms.activities.SplashActivity");
//
//                    try {
//                        commonActions.clickElement(enquiryParentSteps.getButtonSkip());
//                    } catch (Exception e) {
//                        System.out.println("Skip button did not came");
//                    }
//                    try {
//                        commonActions.clickElement(enquiryParentSteps.getNotificationAllow());
//                    } catch (Exception e) {
//                        System.out.println("Notification button did not came");
//                    }
//                }
//
//                if (status.equalsIgnoreCase("Failed")) {
//                    i = i - 1;
//                }
//
//                //Customer Details
//                enquiryParentSteps.clickOnEnquiries();
//                enquiryParentSteps.clickOnEnquiriesPlusIcon();
//                commonActions.sendText(enquiryParentSteps.getTextFieldMobileNumber(), testData.get("mobileNo"));
//                commonActions.sendText(enquiryParentSteps.getTextFieldCustomerName(), testData.get("custName"));
//
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownCustomerType(), ExcelUtility.getMappedValue(testData.get("enquiryType")));
//
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownGender(), testData.get("gender").isEmpty() ? "Unknown" : ExcelUtility.getMappedValue(testData.get("gender")));
//
//                commonActions.sendText(enquiryParentSteps.getTxtWhatsapp(), testData.get("mobileNo"));
//
//                String email = !testData.get("email").equalsIgnoreCase("") || !testData.get("email").equalsIgnoreCase("0") ? testData.get("email")
//                        : testData.get("custName").toLowerCase().replaceAll(" ", "") + "@gmail.com";
//                commonActions.sendText(enquiryParentSteps.getTextFieldEmail(), email);
//
//                commonActions.sendText(enquiryParentSteps.getTextFieldPinCode(), testData.get("pincode"));
//
//                commonActions.sendTextAndSelectValues(testData.get("pincode") + " - " + testData.get("village"));
//
//                commonActions.sendText(enquiryParentSteps.getTextFieldAddress(), testData.get("address"));
//
//                commonActions.scrollDown();
//
//                String visitedWithFamily = testData.get("visitedWith").isEmpty()
//                        || testData.get("visitedWith").equalsIgnoreCase("0") ? "N" : ExcelUtility.getMappedValue(testData.get("visitedWith"));
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVisitedWithFamily(), visitedWithFamily);
//                enquiryParentSteps.clickOnContinueButton();
//
//                //Complete Enquiry Details
//                String carModel = ExcelUtility.getMappedValue(testData.get("model")) == null ? testData.get("model") : ExcelUtility.getMappedValue(testData.get("model"));
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownModel(), carModel);
//
//                String fuelType = testData.get("fuel").equalsIgnoreCase("Electric") ? "Electric Vehicle"
//                        : testData.get("fuel").equalsIgnoreCase("PETROL") ? "Petrol"
//                        : testData.get("fuel").equalsIgnoreCase("DIESEL") ? "Diesel"
//                        : testData.get("fuel").equalsIgnoreCase("LPG_Petrol") ? "LPG" : testData.get("fuel");
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFuelType(), fuelType);
//
//                String variantType = ExcelUtility.getMappedValue(testData.get("variant")) == null ? testData.get("variant") : ExcelUtility.getMappedValue(testData.get("variant"));
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVariant(), variantType);
//
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExteriorColor(), ExcelUtility.getMappedValue(testData.get("color")));
//
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownInteriorColor(), ExcelUtility.getMappedValue(testData.get("interior_color")));
//
//
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSourceType(), ExcelUtility.getMappedValue(testData.get("source")));
//                commonActions.scrollDown();
//
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSubSourceType(), ExcelUtility.getMappedValue(testData.get("subSource")));
//
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownEnquiryCategory(), ExcelUtility.getMappedValue(testData.get("enquiryType")));
//
//                String expectedPlan = testData.get("expectedPlan").isBlank() || testData.get("expectedPlan").equalsIgnoreCase("0") ? "Within 2 months" : ExcelUtility.getMappedValue(testData.get("expectedPlan"));
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExpectedPlanToPurchaseVehicle(), expectedPlan);
//
//                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownWillingToMakeImmediateBooking(),
//                        testData.get("immediate_booking_flag").isEmpty() || testData.get("immediate_booking_flag").equalsIgnoreCase("0")
//                                ? "N" : ExcelUtility.getMappedValue(testData.get("immediate_booking_flag")));
//
//                if (testData.get("financeReq").equalsIgnoreCase("Y") || testData.get("financeReq").equalsIgnoreCase("0")) {
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), testData.get("financeReq"));
//
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceOptions(),
//                            testData.get("financeOption").isBlank() || testData.get("financeOption").equalsIgnoreCase("0") ? "Documents not Submitted" : ExcelUtility.getMappedValue(testData.get("financeOption")));
//                } else {
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), testData.get("financeReq"));
//                }
//                enquiryParentSteps.clickContinueButton();
//
//                //Exchange Section
//                if (!testData.get("BuyerType").equals("Exchange Buyer") || testData.get("BuyerType").isEmpty() || testData.get("BuyerType").equalsIgnoreCase("0")) {
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), "No");
//                } else {
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), ExcelUtility.getMappedValue(testData.get("BuyerType")));
//
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarMake(), testData.get("exchangeMake").isEmpty() ||
//                            testData.get("exchangeMake").equalsIgnoreCase("0")
//                            ? "Hyundai" : ExcelUtility.getMappedValue(testData.get("exchangeMake")));
//
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarModel(), testData.get("exchangeModel").isEmpty() ||
//                            testData.get("exchangeModel").equalsIgnoreCase("0")
//                            ? "EON" : ExcelUtility.getMappedValue(testData.get("exchangeModel")));
//
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarFuelType(), testData.get("exchangeFuelType").isEmpty() ||
//                            testData.get("exchangeFuelType").equalsIgnoreCase("0")
//                            ? "CNG" : ExcelUtility.getMappedValue(testData.get("exchangeFuelType")));
//
//                    commonActions.sendText(enquiryParentSteps.getPresentCarModelYear(), testData.get("exchangeModelYear").isEmpty() ||
//                            testData.get("exchangeModelYear").equalsIgnoreCase("0")
//                            ? "2010" : testData.get("exchangeModelYear"));
//
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarDoUWantToExchange(), ExcelUtility.getMappedValue(testData.get("BuyerType")));
//
//                    commonActions.scrollDown();
//                    commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarSelectSource(), testData.get("exchangeSource").isEmpty() ||
//                            testData.get("exchangeSource").equalsIgnoreCase("0")
//                            ? "Inhouse" : testData.get("exchangeSource"));
//
//                    commonActions.sendText(enquiryParentSteps.getXchangeCarQuotedPrice(), testData.get("exchangeQuotedPrice").isEmpty() ||
//                            testData.get("exchangeQuotedPrice").equalsIgnoreCase("0")
//                            ? "240000" : testData.get("exchangeQuotedPrice"));
//
//                    commonActions.sendText(enquiryParentSteps.getXchangeCarExpectedPrice(), testData.get("exchangeExpectedPrice").isEmpty() ||
//                            testData.get("exchangeExpectedPrice").equalsIgnoreCase("0")
//                            ? "240000" : testData.get("exchangeExpectedPrice"));
//
//                    if (testData.get("certificateOfDeposit").equals("No") || testData.get("certificateOfDeposit").isEmpty() ||
//                            testData.get("certificateOfDeposit").equalsIgnoreCase("0")) {
//                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapCategory(), testData.get("certificateOfDeposit"));
//                    } else {
//                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapCategory(), testData.get("certificateOfDeposit"));
//                        commonActions.scrollDown();
//
//                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapThroughHyundai(), testData.get("exchangeScrapThroughHyundai").isEmpty()
//                                || testData.get("exchangeScrapThroughHyundai").equalsIgnoreCase("0")
//                                ? "No" : testData.get("exchangeScrapThroughHyundai"));
//                    }
//                }
//
//                enquiryParentSteps.clickOnSubmit();
//
//                Assert.assertEquals(commonActions.getElementText(enquiryParentSteps.getTextSuccessMessage()),
//                        "Successfully Created Enquiry", "The Success Message is NOT as expected. Please check");
//                logger.info("The created Lead id is: {}", commonActions.getElementText(enquiryParentSteps.getTextEnquiryNumber()));
//                ExcelUtility.saveTextToExcel(commonActions.getElementText(enquiryParentSteps.getTextEnquiryNumber())
//                        + " is created for Mobile Number: " + testData.get("mobileNo") + " on " + LocalDate.now());
//                commonActions.clickElement(enquiryParentSteps.getButtonOk());
//                Thread.sleep(5000);
//                status = "Passed";
//            } catch (Exception e) {
//                Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
//                status = "Failed";
//            }
//        }
//
//    }

}




//This is done