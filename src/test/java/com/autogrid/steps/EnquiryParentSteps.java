package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.ExcelUtility;
import com.autogrid.utils.LaunchDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class EnquiryParentSteps {

    private static final Logger logger = LoggerFactory.getLogger(EnquiryParentSteps.class);

    private final CommonActions commonActions;

    @FindBy(id = "com.hyundai.ndms:id/et_customer_mobile_no")
    private WebElement textFieldMobileNumber;

    @FindBy(id = "com.hyundai.ndms:id/et_customer_name")
    private WebElement textFieldCustomerName;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Customer Type\"]")
    private WebElement dropDownCustomerType;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Gender\"]")
    private WebElement dropDownGender;

    @FindBy(id = "com.hyundai.ndms:id/et_customer_email")
    private WebElement textFieldEmail;

    @FindBy(id = "com.hyundai.ndms:id/et_customer_pincode")
    private WebElement textFieldPinCode;

    @FindBy(id = "com.hyundai.ndms:id/et_customer_address")
    private WebElement textFieldAddress;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Visited with Family\"]")
    private WebElement dropDownVisitedWithFamily;

    @FindBy(id = "com.hyundai.ndms:id/save_and_proceed")
    private WebElement buttonContinue;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Model\"]")
    private WebElement dropDownModel;

    @FindBy(xpath = "//android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]")
    private WebElement dropDownFuelType;

    @FindBy(xpath = "//android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[3]")
    private WebElement dropDownVariant;

    @FindBy(xpath = "//android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[4]")
    private WebElement dropDownExteriorColor;

    @FindBy(xpath = "//android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[5]")
    private WebElement dropDownInteriorColor;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Source Type\"]")
    private WebElement dropDownSourceType;

    @FindBy(xpath = "//android.widget.ScrollView/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]")
    private WebElement dropDownSubSourceType;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Enquiry Category\"]")
    private WebElement dropDownEnquiryCategory;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Expected plan to purchase the vehicle\"]")
    private WebElement dropDownExpectedPlanToPurchaseVehicle;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select immediate booking\"]")
    private WebElement dropDownWillingToMakeImmediateBooking;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Finance Required\"]")
    private WebElement dropDownFinanceRequired;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\" and @text=\"Select Finance Option\"]")
    private WebElement dropDownFinanceOptions;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Test Drive\"]")
    private WebElement tabTestDriver;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\"]")
    private WebElement drpDownTestDriver;

    @FindBy(id = "com.hyundai.ndms:id/in_house_rb")
    private WebElement toggleInHouse;

    @FindBy(id = "com.hyundai.ndms:id/btnProceed")
    private WebElement btnProceed;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Followup\"]")
    private WebElement tabFollowUp;

    @FindBy(id = "com.hyundai.ndms:id/ib_add_followup")
    private WebElement btnAddNewFollowup;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\"]")
    private WebElement drpDownFollowupType;

    @FindBy(xpath = "//android.widget.TextView[@text='Select Next Follow up']")
    private WebElement drpDownNextFollowupType;

    @FindBy(id = "com.hyundai.ndms:id/et_scheme_offered")
    private WebElement txtInputSchemeOffered;

    @FindBy(id = "com.hyundai.ndms:id/et_remarks")
    private WebElement txtInputRemarks;

    @FindBy(id = "com.hyundai.ndms:id/btn_submit_add_followup_frag")
    private WebElement btnNextFollowupSubmit;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.hyundai.ndms:id/lln\"]")
    private WebElement createdLeadTestDriver;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.hyundai.ndms:id/startBtn\"]")
    private WebElement startTestDriverButton;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.hyundai.ndms:id/btnSubmit\"]")
    private WebElement submitButtonToStartTestDriver;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Select Transmission Type\"]")
    private WebElement selectTransmission;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Select Variant\"]")
    private WebElement selectVariant;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Select Fuel Type\"]")
    private WebElement selectFuelType;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Select Language\"]")
    private WebElement selectLanguage;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.hyundai.ndms:id/btnNext\"]")
    private WebElement nextButton;

    @FindBy(xpath = "//android.widget.GridView[@resource-id=\"com.hyundai.ndms:id/trigger_gv\"]/*")
    private List<WebElement> availableTiles;

    @FindBy(xpath = "//android.widget.CheckBox[@resource-id=\"com.hyundai.ndms:id/test_done\"]")
    private WebElement completeTestDriveCheckBox;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.hyundai.ndms:id/btn_finish\"]")
    private WebElement finishButton;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.hyundai.ndms:id/frmyes\"]")
    private WebElement btnCompleteTestDrive;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Test Drive Successful!\"]")
    private WebElement txtSuccessfulTestDriveCompleted;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/drop_down_text\"]")
    private WebElement dropdownPresentCar;

    @FindBy(id = "com.hyundai.ndms:id/btnSubmitButton")
    private WebElement buttonSubmit;

    @FindBy(id = "com.hyundai.ndms:id/tv_testdrive_schedule_status")
    private WebElement textSuccessMessage;

    @FindBy(id = "com.hyundai.ndms:id/enquiry_no")
    private WebElement textEnquiryNumber;

    @FindBy(id = "com.hyundai.ndms:id/ok")
    private WebElement buttonOk;

    @FindBy(id = "com.hyundai.ndms:id/imb_create_enquiry")
    private WebElement enquiriesPlusIcon;

    @FindBy(id = "com.hyundai.ndms:id/filter")
    private WebElement enquiriesFilterIcon;

    @FindBy(id = "com.hyundai.ndms:id/search")
    private WebElement enquiriesSearchIcon;

    @FindBy(id = "com.hyundai.ndms:id/enquiryDateBtn")
    private WebElement sortNewestToOldest;

    @FindBy(id = "com.hyundai.ndms:id/et_mobile_no")
    private WebElement txtFieldMobileNumber;

    @FindBy(id = "com.hyundai.ndms:id/btn_apply")
    private WebElement btnApply;

    @FindBy(id = "com.hyundai.ndms:id/parent")
    private WebElement leadTile;

    @FindBy(id = "com.hyundai.ndms:id/tv_mobile_no")
    private WebElement leadTileMobileNumber;

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.hyundai.ndms:id/btn_create_lead\"]/android.widget.ImageView")
    private WebElement homeQLEnquiries;

    @FindBy(id = "com.hyundai.ndms:id/ll_quick_actions_show_more")
    private WebElement drpDownShowMore;

    @FindBy(id = "com.hyundai.ndms:id/btn_digital_consulting")
    private WebElement tileSmartConsulting;

    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    private WebElement notificationAllow;

    @FindBy(id = "android:id/ok")
    private WebElement notificationOk;

    @FindBy(xpath = "//android.widget.Button[@resource-id=\"com.hyundai.ndms:id/skipButton\"]")
    private WebElement buttonSkip;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Select Fuel Type\"]")
    private WebElement drpDownFuelType;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Select Vin No\"]")
    private WebElement dropDownSelectVinNumber;

    @FindBy(xpath = "//android.widget.RadioButton")
    private List<WebElement> timeSlots;

    @FindBy(xpath = "//android.widget.Button[@text='Schedule Test Drive']")
    private WebElement btnScheduleTestDriver;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/show_room\"]")
    private WebElement navShowroom;

    @FindBy(id = "com.hyundai.ndms:id/progressBar")
    private WebElement aniLoading;

    @FindBy(id = "com.hyundai.ndms:id/settings")
    private WebElement downloadModelIcon;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/title_view\" and @text=\"VENUE\"]/preceding-sibling::*[1]")
    private WebElement downloadModelCheckbox;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/downloadLayout\"]")
    private WebElement downloadButton;

    @FindBy(xpath = "(//android.widget.ProgressBar[@resource-id=\"com.hyundai.ndms:id/progress_bar\"])[3]")
    private WebElement downloadLoader;

    @FindBy(xpath = "//android.widget.TextView[starts-with(@text,'Downloaded')]")
    private WebElement downloadProgressBar;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.hyundai.ndms:id/pop_main_brochure\"]")
    private WebElement catalogue;

    @FindBy(xpath = "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/openPdf1\"]")
    private WebElement viewCatalogue;

    @FindBy(id = "com.hyundai.ndms:id/et_customer_whatsapp_number")
    private WebElement txtWhatsapp;

    public WebElement getTxtWhatsapp() {
        return txtWhatsapp;
    }

    public WebElement getTextFieldMobileNumber() {
        return textFieldMobileNumber;
    }

    public WebElement getTextFieldCustomerName() {
        return textFieldCustomerName;
    }

    public WebElement getDropDownCustomerType() {
        return dropDownCustomerType;
    }

    public WebElement getDropDownGender() {
        return dropDownGender;
    }

    public WebElement getTextFieldEmail() {
        return textFieldEmail;
    }

    public WebElement getTextFieldPinCode() {
        return textFieldPinCode;
    }

    public WebElement getTextFieldAddress() {
        return textFieldAddress;
    }

    public WebElement getDropDownVisitedWithFamily() {
        return dropDownVisitedWithFamily;
    }

    public WebElement getDropDownModel() {
        return dropDownModel;
    }

    public WebElement getDropDownFuelType() {
        return dropDownFuelType;
    }

    public WebElement getDropDownVariant() {
        return dropDownVariant;
    }

    public WebElement getDropDownExteriorColor() {
        return dropDownExteriorColor;
    }

    public WebElement getDropDownInteriorColor() {
        return dropDownInteriorColor;
    }

    public WebElement getDropDownSourceType() {
        return dropDownSourceType;
    }

    public WebElement getDropDownSubSourceType() {
        return dropDownSubSourceType;
    }

    public WebElement getDropDownEnquiryCategory() {
        return dropDownEnquiryCategory;
    }

    public WebElement getDropDownExpectedPlanToPurchaseVehicle() {
        return dropDownExpectedPlanToPurchaseVehicle;
    }

    public WebElement getDropDownWillingToMakeImmediateBooking() {
        return dropDownWillingToMakeImmediateBooking;
    }

    public WebElement getDropDownFinanceRequired() {
        return dropDownFinanceRequired;
    }

    public WebElement getDropDownFinanceOptions() {
        return dropDownFinanceOptions;
    }

    public WebElement getDrpDownTestDriver() {
        return drpDownTestDriver;
    }

    public WebElement getBtnAddNewFollowup() {
        return btnAddNewFollowup;
    }

    public WebElement getDrpDownFollowupType() {
        return drpDownFollowupType;
    }

    public WebElement getDrpDownNextFollowupType() {
        return drpDownNextFollowupType;
    }

    public WebElement getTxtInputSchemeOffered() {
        return txtInputSchemeOffered;
    }

    public WebElement getTxtInputRemarks() {
        return txtInputRemarks;
    }

    public WebElement getBtnNextFollowupSubmit() {
        return btnNextFollowupSubmit;
    }

    public WebElement getStartTestDriverButton() {
        return startTestDriverButton;
    }

    public WebElement getSubmitButtonToStartTestDriver() {
        return submitButtonToStartTestDriver;
    }

    public WebElement getSelectTransmission() {
        return selectTransmission;
    }

    public WebElement getSelectVariant() {
        return selectVariant;
    }

    public WebElement getSelectFuelType() {
        return selectFuelType;
    }

    public WebElement getSelectLanguage() {
        return selectLanguage;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    public WebElement getCompleteTestDriveCheckBox() {
        return completeTestDriveCheckBox;
    }

    public WebElement getFinishButton() {
        return finishButton;
    }

    public WebElement getBtnCompleteTestDrive() {
        return btnCompleteTestDrive;
    }

    public WebElement getTxtSuccessfulTestDriveCompleted() {
        return txtSuccessfulTestDriveCompleted;
    }

    public WebElement getDropdownPresentCar() {
        return dropdownPresentCar;
    }

    public WebElement getTxtFieldMobileNumber() {
        return txtFieldMobileNumber;
    }

    public WebElement getNotificationAllow() {
        return notificationAllow;
    }

    public WebElement getButtonSkip() {
        return buttonSkip;
    }

    public WebElement getDrpDownFuelType() {
        return drpDownFuelType;
    }

    public WebElement getDropDownSelectVinNumber() {
        return dropDownSelectVinNumber;
    }

    public WebElement getDownloadProgressBar() {
        return downloadProgressBar;
    }

    public WebElement getCatalogue() {
        return catalogue;
    }


    public EnquiryParentSteps(AndroidDriver androidDriver){
        this.commonActions = new CommonActions(androidDriver);
        PageFactory.initElements(LaunchDriver.getAndroidDriver(), this);
    }

    public void clickOnContinueButton(){
        commonActions.clickElement(buttonContinue);
    }

    public void clickContinueButton(){
        commonActions.clickElement(buttonContinue);
    }

    //Below is for Test Driver Section in Enquiry Details
    public void clickOnTestDriverTab(){
        commonActions.clickElement(tabTestDriver);
    }

    public void clickOnTestDriveOffer(){
        commonActions.selectApkDropdownValue(drpDownTestDriver, "Yes");
        commonActions.clickElement(btnScheduleTestDriver);
        commonActions.clickElement(toggleInHouse);
        commonActions.clickElement(btnProceed);
    }

    public void clickOnFollowUpTab(){
        commonActions.clickElement(tabFollowUp);
    }

    public void navigateBackToHomePage() throws InterruptedException {
        commonActions.navigateBack();
        commonActions.navigateBack();
    }


    public void clickOnLatestCreatedTestDriverLead(){
        commonActions.clickElement(createdLeadTestDriver);
    }

    public void clickOnTilesWithinTestDriver(){
        try{
            for (WebElement element : availableTiles) {
                new WebDriverWait(LaunchDriver.getAndroidDriver(), Duration.ofSeconds(10))
                        .until(e -> element.isDisplayed() && element.isEnabled());
                commonActions.clickElement(element);
            }
        }catch(Exception e){
            logger.info("Nothing to Click..");
        }
    }

    public void clickOnSubmit(){
        commonActions.clickElement(buttonSubmit);
    }

    public void verifySuccessMessageAndClickOK() throws InterruptedException {
        Assert.assertEquals(commonActions.getElementText(textSuccessMessage), "Successfully Created Enquiry", "The Success Message is NOT as expected. Please check");
        logger.info("The created Lead id is: {}", commonActions.getElementText(textEnquiryNumber));
        ExcelUtility.saveTextToExcel(commonActions.getElementText(textEnquiryNumber));
        commonActions.clickElement(buttonOk);
        Thread.sleep(5000);
    }

    public void clickOnEnquiriesPlusIcon(){
        commonActions.clickElement(enquiriesPlusIcon);
        logger.info("User clicked on plus icon on Enquiries Page...");
    }

    public void clickOnEnquiriesFilterIcon(){
        commonActions.clickElement(enquiriesFilterIcon);
        logger.info("User clicked on filter icon on Enquiries Page...");
    }

    public void clickOnEnquiriesNewestToOldest(){
        commonActions.clickElement(sortNewestToOldest);
        logger.info("User clicked on Newest to Oldest to sort...");
    }

    public void clickOnSearchIcon(){
        commonActions.clickElement(enquiriesSearchIcon);
    }

    public void clickOnApplyButton(){
        commonActions.clickElement(btnApply);
    }

    public void validateTheRequiredLeadIsPresent(){
        assert leadTileMobileNumber.isDisplayed() &&  leadTile.isDisplayed() :
                "The Lead in NOT shown as expected. Please check";
    }

    public void clickOnTheLead(){
        commonActions.clickElement(leadTile);
    }

    public void clickOnEnquiries(){
        commonActions.clickElement(homeQLEnquiries);
        logger.info("User clicked on Enquiries...");
    }

    public void clickOnShowMoreDropdownAndClickOnSmartConsulting() throws InterruptedException {
        commonActions.clickElement(drpDownShowMore);
        commonActions.clickElement(tileSmartConsulting);
        commonActions.clickElement(notificationAllow);
        try {
            commonActions.clickElement(notificationOk);
        }catch(Exception e){
            logger.info("Button did not popped up");
        }
    }

    public void selectTimeSlot() throws InterruptedException {
        Thread.sleep(5000);
        for (WebElement slot : timeSlots) {
            if (slot.getDomAttribute("clickable").equals("true")) {
                slot.click();
                break;
            }
        }
    }

    public void clickOnScheduleTestDriveButton(){
        commonActions.scrollDown();
        commonActions.clickElement(btnScheduleTestDriver);
    }

    public void clickOnModelAndReadCataloguePart1() throws InterruptedException {
        commonActions.clickElement(navShowroom);
        new WebDriverWait(LaunchDriver.getAndroidDriver(), Duration.ofSeconds(300))
                .until(ExpectedConditions.invisibilityOf(aniLoading));
    }

    public void clickOnModelAndReadCataloguePart2() throws InterruptedException {
        commonActions.clickElement(viewCatalogue);
        Thread.sleep(5000);
        commonActions.navigateBack();
        Thread.sleep(5000);
        commonActions.navigateBack();
        Thread.sleep(5000);
        try{
            commonActions.navigateBack();
            logger.info("navigated back to Home Screen");
        }catch(Exception e){
            logger.info("Was trying to navigate back to HomeScreen");
        }
    }

    public void downloadCataloguePart1() throws InterruptedException {
        new WebDriverWait(LaunchDriver.getAndroidDriver(), Duration.ofSeconds(120))
                .until(ExpectedConditions.visibilityOf(downloadModelIcon));
        new WebDriverWait(LaunchDriver.getAndroidDriver(), Duration.ofSeconds(120))
                .until(ExpectedConditions.elementToBeClickable(downloadModelIcon));
        commonActions.clickElement(downloadModelIcon);
    }

    public void downloadCataloguePart2() throws InterruptedException, IOException {
        new WebDriverWait(LaunchDriver.getAndroidDriver(), Duration.ofSeconds(120))
                .until(ExpectedConditions.elementToBeClickable(downloadModelCheckbox));
        commonActions.clickElement(downloadModelCheckbox);
        commonActions.clickElement(downloadButton);
        Thread.sleep(3000);
        new WebDriverWait(LaunchDriver.getAndroidDriver(), Duration.ofSeconds(900))
                .until(ExpectedConditions.invisibilityOf(downloadLoader));
        Thread.sleep(10000);
    }

    public void clickOnExpectedModelCatalogue(String ModelName){

        switch (ModelName.toLowerCase()) {
            case "grand i10 nios":
                System.out.println("Monday");
                break;
            case "i20":
                System.out.println("Tuesday");
                break;
            case "i20 n line":
                System.out.println("Wednesday");
                break;
            case "aura":
                System.out.println("Thursday");
                break;
            case "verna":
                System.out.println("Friday");
                break;
            case "venue n line":
                System.out.println("Saturday");
                break;
            case "venue":
                System.out.println("Sunday");
                break;
            case "venue knight":
                System.out.println("Sunday");
                break;
            case "creta":
                System.out.println("Sunday");
                break;
            case "creta n line":
                System.out.println("Sunday");
                break;
            case "creta knight":
                System.out.println("Sunday");
                break;
            case "exter knight":
                System.out.println("Sunday");
                break;
            case "alcazar ad":
                System.out.println("Sunday");
                break;
            case "alcazar":
                System.out.println("Sunday");
                break;
            case "exter":
                System.out.println("Sunday");
                break;
            case "tucson":
                System.out.println("Sunday");
                break;
            case "ioniq 5":
                System.out.println("Sunday");
                break;
            case "kona electric":
                System.out.println("Sunday");
                break;
            default:
                System.out.println("Unable to find the Car Catalogue");
                break;
        }


        //Grand i10 NIOS
        //i20
        //i20 NLine
        //AURA
        //VERNA

        //Venue N Line
        //Venue
        //Venue Knight
        //CRETA
        //CRETA N Line

        //Creta Knight
        //Exter Knight
        //Alcazar AD
        //Alcazar
        //Exter

        //Tucson
        //IONIQ 5
        //Kona Electric
    }
}
