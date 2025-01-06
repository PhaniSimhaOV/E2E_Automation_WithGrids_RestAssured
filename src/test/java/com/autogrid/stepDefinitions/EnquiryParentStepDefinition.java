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
    public void createTheLeadAndGetTheEnquiryNoInTheExcel(IDataReader dataTable) throws InterruptedException, IOException {

        List<Map<String, String>> data = dataTable.getAllRows();
        String status = "Passed";
        for (int i = 0; i < dataTable.getAllRows().size() + 1; i++) {
            try {
                if (i != 0) {
                    Runtime.getRuntime().exec("adb shell am start -n com.hyundai.ndms/com.hyundai.ndms.activities.SplashActivity");

                    try {
                        commonActions.clickElement(enquiryParentSteps.getButtonSkip());
                    } catch (Exception e) {
                        System.out.println("Skip button did not came");
                    }
                    try {
                        commonActions.clickElement(enquiryParentSteps.getNotificationAllow());
                    } catch (Exception e) {
                        System.out.println("Notification button did not came");
                    }
                }

                if (status.equalsIgnoreCase("Failed")) {
                    i = i - 1;
                }

                //Customer Details
                enquiryParentSteps.clickOnEnquiries();
                enquiryParentSteps.clickOnEnquiriesPlusIcon();
                commonActions.sendText(enquiryParentSteps.getTextFieldMobileNumber(), data.get(i).get("mobileNo"));
                commonActions.sendText(enquiryParentSteps.getTextFieldCustomerName(), data.get(i).get("custName"));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownCustomerType(), ExcelUtility.getMappedValue(data.get(i).get("enquiryType")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownGender(), data.get(i).get("gender").isEmpty() ? "Unknown" : ExcelUtility.getMappedValue(data.get(i).get("gender")));

                commonActions.sendText(enquiryParentSteps.getTxtWhatsapp(), data.get(i).get("mobileNo"));

                String email = !data.get(i).get("email").equalsIgnoreCase("") || !data.get(i).get("email").equalsIgnoreCase("0") ? data.get(i).get("email")
                        : data.get(i).get("custName").toLowerCase().replaceAll(" ", "") + "@gmail.com";
                commonActions.sendText(enquiryParentSteps.getTextFieldEmail(), email);

                commonActions.sendText(enquiryParentSteps.getTextFieldPinCode(), data.get(i).get("pincode"));

                commonActions.sendTextAndSelectValues(data.get(i).get("pincode") + " - " + data.get(i).get("village"));

                commonActions.sendText(enquiryParentSteps.getTextFieldAddress(), data.get(i).get("address"));

                commonActions.scrollDown();

                String visitedWithFamily = data.get(i).get("visitedWith").isEmpty()
                        || data.get(i).get("visitedWith").equalsIgnoreCase("0") ? "N" : ExcelUtility.getMappedValue(data.get(i).get("visitedWith"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVisitedWithFamily(), visitedWithFamily);
                enquiryParentSteps.clickOnContinueButton();

                //Complete Enquiry Details
                String carModel = ExcelUtility.getMappedValue(data.get(i).get("model")) == null ? data.get(i).get("model") : ExcelUtility.getMappedValue(data.get(i).get("model"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownModel(), carModel);

                String fuelType = data.get(i).get("fuel").equalsIgnoreCase("Electric") ? "Electric Vehicle"
                        : data.get(i).get("fuel").equalsIgnoreCase("PETROL") ? "Petrol"
                        : data.get(i).get("fuel").equalsIgnoreCase("DIESEL") ? "Diesel"
                        : data.get(i).get("fuel").equalsIgnoreCase("LPG_Petrol") ? "LPG" : data.get(i).get("fuel");
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFuelType(), fuelType);

                String variantType = ExcelUtility.getMappedValue(data.get(i).get("variant")) == null ? data.get(i).get("variant") : ExcelUtility.getMappedValue(data.get(i).get("variant"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVariant(), variantType);

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExteriorColor(), ExcelUtility.getMappedValue(data.get(i).get("color")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownInteriorColor(), ExcelUtility.getMappedValue(data.get(i).get("interior_color")));


                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSourceType(), ExcelUtility.getMappedValue(data.get(i).get("source")));
                commonActions.scrollDown();

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSubSourceType(), ExcelUtility.getMappedValue(data.get(i).get("subSource")));

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownEnquiryCategory(), ExcelUtility.getMappedValue(data.get(i).get("enquiryType")));

                String expectedPlan = data.get(i).get("expectedPlan").isBlank() || data.get(i).get("expectedPlan").equalsIgnoreCase("0") ? "Within 2 months" : ExcelUtility.getMappedValue(data.get(i).get("expectedPlan"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExpectedPlanToPurchaseVehicle(), expectedPlan);

                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownWillingToMakeImmediateBooking(),
                        data.get(i).get("immediate_booking_flag").isEmpty() || data.get(i).get("immediate_booking_flag").equalsIgnoreCase("0")
                                ? "N" : ExcelUtility.getMappedValue(data.get(i).get("immediate_booking_flag")));

                if (data.get(i).get("financeReq").equalsIgnoreCase("Y") || data.get(i).get("financeReq").equalsIgnoreCase("0")) {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), data.get(i).get("financeReq"));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceOptions(),
                            data.get(i).get("financeOption").isBlank() || data.get(i).get("financeOption").equalsIgnoreCase("0") ? "Documents not Submitted" : ExcelUtility.getMappedValue(data.get(i).get("financeOption")));
                } else {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), data.get(i).get("financeReq"));
                }
                enquiryParentSteps.clickContinueButton();

                //Exchange Section
                if (!data.get(i).get("BuyerType").equals("Exchange Buyer") || data.get(i).get("BuyerType").isEmpty() || data.get(i).get("BuyerType").equalsIgnoreCase("0")) {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), "No");
                } else {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), ExcelUtility.getMappedValue(data.get(i).get("BuyerType")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarMake(), data.get(i).get("exchangeMake").isEmpty() ||
                            data.get(i).get("exchangeMake").equalsIgnoreCase("0")
                            ? "Hyundai" : ExcelUtility.getMappedValue(data.get(i).get("exchangeMake")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarModel(), data.get(i).get("exchangeModel").isEmpty() ||
                            data.get(i).get("exchangeModel").equalsIgnoreCase("0")
                            ? "EON" : ExcelUtility.getMappedValue(data.get(i).get("exchangeModel")));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarFuelType(), data.get(i).get("exchangeFuelType").isEmpty() ||
                            data.get(i).get("exchangeFuelType").equalsIgnoreCase("0")
                            ? "CNG" : ExcelUtility.getMappedValue(data.get(i).get("exchangeFuelType")));

                    commonActions.sendText(enquiryParentSteps.getPresentCarModelYear(), data.get(i).get("exchangeModelYear").isEmpty() ||
                            data.get(i).get("exchangeModelYear").equalsIgnoreCase("0")
                            ? "2010" : data.get(i).get("exchangeModelYear"));

                    commonActions.selectApkDropdownValue(enquiryParentSteps.getPresentCarDoUWantToExchange(), ExcelUtility.getMappedValue(data.get(i).get("BuyerType")));

                    commonActions.scrollDown();
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarSelectSource(), data.get(i).get("exchangeSource").isEmpty() ||
                            data.get(i).get("exchangeSource").equalsIgnoreCase("0")
                            ? "Inhouse" : data.get(i).get("exchangeSource"));

                    commonActions.sendText(enquiryParentSteps.getXchangeCarQuotedPrice(), data.get(i).get("exchangeQuotedPrice").isEmpty() ||
                            data.get(i).get("exchangeQuotedPrice").equalsIgnoreCase("0")
                            ? "240000" : data.get(i).get("exchangeQuotedPrice"));

                    commonActions.sendText(enquiryParentSteps.getXchangeCarExpectedPrice(), data.get(i).get("exchangeExpectedPrice").isEmpty() ||
                            data.get(i).get("exchangeExpectedPrice").equalsIgnoreCase("0")
                            ? "240000" : data.get(i).get("exchangeExpectedPrice"));

                    if (data.get(i).get("certificateOfDeposit").equals("No") || data.get(i).get("certificateOfDeposit").isEmpty() ||
                            data.get(i).get("certificateOfDeposit").equalsIgnoreCase("0")) {
                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapCategory(), data.get(i).get("certificateOfDeposit"));
                    } else {
                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapCategory(), data.get(i).get("certificateOfDeposit"));
                        commonActions.scrollDown();

                        commonActions.selectApkDropdownValue(enquiryParentSteps.getXchangeCarScrapThroughHyundai(), data.get(i).get("exchangeScrapThroughHyundai").isEmpty()
                                || data.get(i).get("exchangeScrapThroughHyundai").equalsIgnoreCase("0")
                                ? "No" : data.get(i).get("exchangeScrapThroughHyundai"));
                    }
                }

                enquiryParentSteps.clickOnSubmit();

                Assert.assertEquals(commonActions.getElementText(enquiryParentSteps.getTextSuccessMessage()),
                        "Successfully Created Enquiry", "The Success Message is NOT as expected. Please check");
                logger.info("The created Lead id is: {}", commonActions.getElementText(enquiryParentSteps.getTextEnquiryNumber()));
                ExcelUtility.saveTextToExcel(commonActions.getElementText(enquiryParentSteps.getTextEnquiryNumber())
                        + " is created for Mobile Number: " + data.get(i).get("mobileNo") + " on " + LocalDate.now());
                commonActions.clickElement(enquiryParentSteps.getButtonOk());
                Thread.sleep(5000);
                status = "Passed";
            } catch (Exception e) {
                Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
                status = "Failed";
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

}

//This is done