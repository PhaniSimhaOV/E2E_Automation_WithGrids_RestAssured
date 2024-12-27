package com.autogrid.stepDefinitions;


import com.autogrid.steps.*;
import com.autogrid.utils.*;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class EnquiryParentStepDefinition {

    EnquiryParentSteps enquiryParentSteps;

    CommonActions commonActions;

    public EnquiryParentStepDefinition(){
        this.commonActions = new CommonActions(LaunchDriver.getAndroidDriver());
        this.enquiryParentSteps = new EnquiryParentSteps(LaunchDriver.getAndroidDriver());
        PageFactory.initElements(LaunchDriver.getAndroidDriver(), enquiryParentSteps);
    }


    @Then("Create the Lead and get the Enquiry No in the Excel")
    public void createTheLeadAndGetTheEnquiryNoInTheExcel(IDataReader dataTable) throws InterruptedException, IOException {

        List<Map<String, String>> data = dataTable.getAllRows();
        String status = "Passed";
        for(int i = 0; i<dataTable.getAllRows().size()+1; i++){

            try {
                if(i != 0) {
                    Runtime.getRuntime().exec("adb shell am start -n com.hyundai.ndms/com.hyundai.ndms.activities.SplashActivity");

                    try{
                        commonActions.clickElement(enquiryParentSteps.getButtonSkip());
                    }catch(Exception e){
                        System.out.println("Skip button did not came");
                    }
                    try{
                        commonActions.clickElement(enquiryParentSteps.getNotificationAllow());
                    }catch(Exception e){
                        System.out.println("Notification button did not came");
                    }
                }

                if(status.equalsIgnoreCase("Failed")){
                    i = i - 1;
                }

                //Customer Details
                enquiryParentSteps.clickOnEnquiries();
                enquiryParentSteps.clickOnEnquiriesPlusIcon();
                commonActions.sendText(enquiryParentSteps.getTextFieldMobileNumber(), data.get(i).get("mobileNo"));
                commonActions.sendText(enquiryParentSteps.getTextFieldCustomerName(), data.get(i).get("custName"));
                String customerType = data.get(i).get("custType").equalsIgnoreCase("Individual") || data.get(i).get("custType").equalsIgnoreCase("Government")
                        || data.get(i).get("custType").equalsIgnoreCase("Corporate (Company)") ? data.get(i).get("custType")
                        : "Individual";
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownCustomerType(), customerType);
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownGender(), data.get(i).get("gender").equalsIgnoreCase("Male")
                        || data.get(i).get("gender").equalsIgnoreCase("Female") ? data.get(i).get("gender") : "Unknown");
                commonActions.sendText(enquiryParentSteps.getTxtWhatsapp(), data.get(i).get("mobileNo"));
                commonActions.sendText(enquiryParentSteps.getTextFieldEmail(), data.get(i).get("email"));
                commonActions.sendText(enquiryParentSteps.getTextFieldPinCode(), data.get(i).get("pincode"));
                commonActions.sendTextAndSelectValues(data.get(i).get("village"));
                commonActions.sendText(enquiryParentSteps.getTextFieldAddress(), data.get(i).get("address"));
                commonActions.scrollDown();
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVisitedWithFamily(),
                        data.get(i).get("visitedWith").equalsIgnoreCase("Y") ? data.get(i).get("visitedWith") : "N");
                enquiryParentSteps.clickOnContinueButton();

                //Complete Enquiry Details
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownModel(), data.get(i).get("model"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFuelType(), data.get(i).get("fuel"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownVariant(), data.get(i).get("variant"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExteriorColor(), data.get(i).get("color"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownInteriorColor(), data.get(i).get("interior_color"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSourceType(), data.get(i).get("source"));
                commonActions.scrollDown();
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSubSourceType(), data.get(i).get("subSource"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownEnquiryCategory(), data.get(i).get("enquiry_category"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownExpectedPlanToPurchaseVehicle(), data.get(i).get("expectedPlan"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownWillingToMakeImmediateBooking(), data.get(i).get("immediate_booking_flag"));

                String FinanceRequired = data.get(i).get("financeReq");
                if (FinanceRequired.equalsIgnoreCase("Y")) {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), FinanceRequired);
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceOptions(), data.get(i).get("financeOption"));
                } else {
                    commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownFinanceRequired(), FinanceRequired);
                }
                enquiryParentSteps.clickContinueButton();

                //Exchange Section
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDropdownPresentCar(), data.get(i).get("PresentCar"));
                enquiryParentSteps.clickOnSubmit();
                enquiryParentSteps.verifySuccessMessageAndClickOK();
                status = "Passed";
            }catch(Exception e){
                Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
                status = "Failed";
            }
        }

    }

    @Then("Complete the Test Drive")
    public void completeTheTestDrive(IDataReader dataTable) throws InterruptedException, IOException {

        List<Map<String, String>> data = dataTable.getAllRows();

        for(int i = 0; i<dataTable.getAllRows().size(); i++){

            try {
                if(i != 0) {
                    Runtime.getRuntime().exec("adb shell am start -n com.hyundai.ndms/com.hyundai.ndms.activities.SplashActivity");

                    try{
                        commonActions.clickElement(enquiryParentSteps.getButtonSkip());
                    }catch(Exception e){
                        System.out.println("Skip button did not came");
                    }
                    try{
                        commonActions.clickElement(enquiryParentSteps.getNotificationAllow());
                    }catch(Exception e){
                        System.out.println("Notification button did not came");
                    }
                }

                //Customer Details
                enquiryParentSteps.clickOnEnquiries();

                //click on search icon and search the created lead with mobile number
                enquiryParentSteps.clickOnEnquiriesFilterIcon();
                enquiryParentSteps.clickOnEnquiriesNewestToOldest();

                enquiryParentSteps.clickOnSearchIcon();
                commonActions.sendText(enquiryParentSteps.getTxtFieldMobileNumber(), data.get(i).get("MobileNo"));
                enquiryParentSteps.clickOnApplyButton();

                //verify the created Lead is present after search
                enquiryParentSteps.validateTheRequiredLeadIsPresent();
                enquiryParentSteps.clickOnTheLead();

                //complete and close the Test Drive flow
                enquiryParentSteps.clickOnTestDriverTab();


                try {
                    if(enquiryParentSteps.getDrpDownTestDriver().isDisplayed()) {
                        System.out.println("Inside Drive Tab");
                        enquiryParentSteps.clickOnTestDriveOffer();
                        commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownFuelType(), data.get(i).get("TestDriveFuelType"));
                        commonActions.selectApkDropdownValue(enquiryParentSteps.getDropDownSelectVinNumber(), data.get(i).get("VinNumber"));
                        enquiryParentSteps.selectTimeSlot();
                        enquiryParentSteps.clickOnScheduleTestDriveButton();
                    }
                } catch (Exception e) {
                    System.out.println("Element is not present in the DOM.");
                }


                //click on the Followup tab and complete flow within
                enquiryParentSteps.clickOnFollowUpTab();
                commonActions.clickElement(enquiryParentSteps.getBtnAddNewFollowup());
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownFollowupType(), data.get(i).get("FollowUpPhoneType"));
                commonActions.sendText(enquiryParentSteps.getTxtInputSchemeOffered(), data.get(i).get("SchemeText"));
                commonActions.sendText(enquiryParentSteps.getTxtInputRemarks(), data.get(i).get("InputRemarks"));
                commonActions.scrollDown();
                commonActions.selectApkDropdownValue(enquiryParentSteps.getDrpDownNextFollowupType(), data.get(i).get("NextFollowupType"));
                commonActions.clickElement(enquiryParentSteps.getBtnNextFollowupSubmit());

                //Navigate Back to Home Page and click on Smart Consulting
                enquiryParentSteps.navigateBackToHomePage();
                enquiryParentSteps.clickOnShowMoreDropdownAndClickOnSmartConsulting();

                //close the Test Drive Flow with the selected Model
                enquiryParentSteps.downloadCataloguePart1();
                commonActions.scrollDownToElement(data.get(i).get("CatalogueModelName"));
                enquiryParentSteps.downloadCataloguePart2();
                new WebDriverWait(LaunchDriver.getAndroidDriver(), Duration.ofSeconds(900))
                        .until(ExpectedConditions.textToBePresentInElement(enquiryParentSteps.getDownloadProgressBar(), "Downloaded"));

                enquiryParentSteps.clickOnModelAndReadCataloguePart1();
                commonActions.clickElement(enquiryParentSteps.getCatalogue());
                enquiryParentSteps.clickOnModelAndReadCataloguePart2();
                enquiryParentSteps.clickOnEnquiries();
                enquiryParentSteps.clickOnSearchIcon();
                commonActions.sendText(enquiryParentSteps.getTxtFieldMobileNumber(), data.get(i).get("MobileNo"));
                enquiryParentSteps.clickOnApplyButton();
                enquiryParentSteps.validateTheRequiredLeadIsPresent();
                enquiryParentSteps.clickOnTheLead();
                enquiryParentSteps.clickOnTestDriverTab();
                enquiryParentSteps.clickOnLatestCreatedTestDriverLead();
                commonActions.clickElement(enquiryParentSteps.getStartTestDriverButton());
                commonActions.clickElement(enquiryParentSteps.getSubmitButtonToStartTestDriver());
                commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectFuelType(), data.get(i).get("CompleteFuelType"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectTransmission(), data.get(i).get("CompleteTransmissionType"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectVariant(), data.get(i).get("CompleteVariant"));
                commonActions.selectApkDropdownValue(enquiryParentSteps.getSelectLanguage(), data.get(i).get("CompleteLanguage"));
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
            }catch(Exception e){
                System.out.println("Unable to complete Lead for: "+data.get(i).get("MobileNo"));
                Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
            }
        }

    }


    @DataTableType
    public IDataReader excelToDataTable(Map<String, String> entry) { // [Excel= <fileName>, Location=<FileLocation> ..]
        ExcelConfiguration config = new ExcelConfiguration.ExcelConfigurationBuilder()
                .setFileName(entry.get("Excel"))
                .setFileLocation(entry.get("Location"))
                .setSheetName(entry.get("Sheet"))
                .setIndex(0)
                .build();
        return new ExcelDataReader(config);

    }

}
