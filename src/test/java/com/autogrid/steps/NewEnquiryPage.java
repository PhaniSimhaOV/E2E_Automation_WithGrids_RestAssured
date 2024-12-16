package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewEnquiryPage {
    private static final Logger logger = LoggerFactory.getLogger(NewEnquiryPage.class);
    private final CommonActions commonActions;

    @FindBy(xpath = "//a[normalize-space()='Sales']")
    private WebElement SalesMenu;
    
    @FindBy(xpath = "//*[@id='gnb']/li[3]/div/ul/li[2]/a")
    private WebElement CustomerEnquirySubmenu;
    
    @FindBy(xpath = "//li[@class='active']//ul//li//a[@class='menuItem'][normalize-space()='Customer Enquiry']")
    private WebElement CustomerEnquiryLink;
    
    @FindBy(xpath = "//h1[@class='title_basic']")
    private WebElement CustomerEnquiryscreenHeader;
    
    @FindBy(xpath = "//span[@id='selectCustomerMeetingPopup_wnd_title']")
    private WebElement SalesCustomerEnquiryPopupHeader;
  
    @FindBy(xpath = "//button[@id='btnNew']")
    private WebElement NewEnquiry;
    
    @FindBy(xpath = "//span[@class='k-link']")
    private WebElement BasicInfoTab;
    
    @FindBy(xpath = "//input[@id='mobileNo']")
    private WebElement MobileNumber;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement InvalidMobileNumberToast;

    @FindBy(xpath = "//a[@id='searchCustomer']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//*[@id='template']/div/h3")
    private WebElement NewMobileToast;

    @FindBy(xpath = "//*[@id='enquiry_info']/div[2]/dl[1]/dd[1]/span/span/span[1]")
    private WebElement CustTypeDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement CustTypeValidation;

    @FindBy(xpath = "//*[@id='custName'][@data-name='Cust. Name']")
    private WebElement CustName;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement CustNameValidation;

    @FindBy(xpath = "//*[@id='resdcPhoneNo']")
    private WebElement ResidencePhone;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement ResidencePhoneValidation;
    
    @FindBy(xpath = "//*[@id='whatsAppID']")
    private WebElement WhatsAppId;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement WhatsAppIdValidation;
    
    @FindBy(xpath = "//*[@id='corp5']/span/span/span[1]")
    private WebElement GenderDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement GenderValidation;    
    
    @FindBy(xpath = "//*[@id='email']")
    private WebElement Email;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement InvalidEmailToast;    
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[2]/dl[3]/dd[1]/span/span/span[1]")
    private WebElement LocationDropdown;
    
    @FindBy(xpath = "//*[@id='pin']")
    private WebElement DisabledPincodeField;
    
    @FindBy(xpath = "//a[@id='btnPinN']")
    private WebElement PinCodeSearchIcon;
    
    @FindBy(xpath = "//*[@id='pinCodeSearchPopup_wnd_title']")
    private WebElement PincodesearchScreenHeader;
    
    @FindBy(xpath = "//*[@id='sPinCode']")
    private WebElement Pincode;
    
    @FindBy(xpath = "//*[@id='btnSearch']")
    private WebElement PinCodeSearchButton;
    
    @FindBy(xpath = "//*[@id='grid']/div[2]/table/tbody/tr[1]")
    private WebElement LocationSelection;
    
    @FindBy(xpath = "//button[@id='btnAddSelected']")
    private WebElement AddSelectedButton;
    
    @FindBy(xpath = "//*[@id='window']/div[2]/dl[1]/dd[1]/span")
    private WebElement StateDropdown;
    
    @FindBy(xpath = "//*[@id='window']/div[2]/dl[1]/dd[2]/span")
    private WebElement DistrictDropdown;
    
    @FindBy(xpath = "//*[@id='window']/div[2]/dl[1]/dd[3]/span")
    private WebElement TalukaDropdown;
    
    @FindBy(xpath = "//*[@id='sPostOfceName']")
    private WebElement PostOfficeName;
    
    @FindBy(xpath = "//*[@id='villName']")
    private WebElement Village;    
    
    @FindBy(xpath = "//*[@id='addr']")
    private WebElement Address;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[1]/span/span/span[1]")
    private WebElement EnquirySourceDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement EnquirySourceValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[2]/span/span/span[1]")
    private WebElement EnquirySubSourceDropdown;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[3]/span/span/span[1]")
    private WebElement EnquiryCategoryDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement EnquiryCategoryValidation;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement EnquirySubSourceValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[4]/span/span/span[1]")
    private WebElement PersonInChargeDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement PersonInChargeValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[1]/span/span/span[1]")
    private WebElement ModelDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement ModelValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[2]/span/span/span[1]")
    private WebElement FuelTypeDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement FuelTypeValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[3]/span/span/span[1]")
    private WebElement VariantDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement VariantValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[4]/span/span/span[1]")
    private WebElement SubVariantDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement VariantSubTypeValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[1]/span/span/span[1]")
    private WebElement ExtColorDropdown;
    
    @FindBy(xpath = "///*[@id='template']")
    private WebElement ExtColorValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[2]/span/span/span[1]")
    private WebElement IntColorDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement IntColorValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[4]/span/span/span[1]")
    private WebElement SalesConsultantDropdown;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement SalesConsultantValidation;

    @FindBy(xpath = "//*[@id='associate']")
    private WebElement Associate;
    
    @FindBy(xpath = "//*[@id='corpName']")
    private WebElement RegCrop;
    
    @FindBy(xpath = "//*[@id='btnSave']")
    private WebElement SaveButton;
    
    @FindBy(xpath = "//a[@role='button'][@aria-label='Close']")
    private WebElement CloseIcon;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[1]/div")
    private WebElement SMSOpt;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[1]/div/label")
    private WebElement SMSOptCheckBox;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement ToastMessage;

    @FindBy(xpath = "//*[@id='custName'][@data-name='Cust. Name']")
    private WebElement CompanyName;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement CompanyNameValidation;
   
    @FindBy(xpath = "//*[@id='activityName']")
    private WebElement Activity;
    
    @FindBy(xpath = "//span[normalize-space()='Follow Up']")
    private WebElement FollowUpTab;
    
    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[4]/dd[1]/span/span/span[1]")
    private WebElement FinanceReqYN;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement FinanceReqYNValidation;
    
    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[4]/dd[2]/span/span/span[1]")
    private WebElement Financier;
    
    @FindBy(xpath = "//input[@id='loanAmount']")
    private WebElement LoanAmount;
    
    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[6]/dd[1]/span/span/span[1]")
    private WebElement TDOffer;
    
    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[6]/dd[2]/span/span/span[1]")
    private WebElement TDVIN;

    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[6]/dd[3]/span/span/span[1]")
    private WebElement CftOfDeposit;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement CftOfDepositValidation;
    
    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[7]/dd[1]/span/span/span[1]")
    private WebElement FinanceOptions;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement FinanceOptionsValidation;
    
    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[7]/dd[2]/span/span/span[1]")
    private WebElement Expplan;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement ExpplanValidation;
    
    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[7]/dd[4]/span/span/span[1]")
    private WebElement VisitedFamily;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement VisitedFamilyValidation;
    
    @FindBy(xpath = "//*[@id='eqryForm']/div/dl[8]/dd/span/span/span[1]")
    private WebElement ImmediateBook;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement ImmediateBookValidation;
    
    @FindBy(xpath = "//*[@id='followUpInfoForm']/div[2]/dl[1]/dd[2]/span/span/span[1]")
    private WebElement FollowUpTypeSelection;
    
    @FindBy(xpath = "//textarea[@id='eqfuSchmeRmrks']")
    private WebElement SchemeOffered;
    
    @FindBy(xpath = "//textarea[@id='eqfuRmrks']")
    private WebElement FollowUpRemarks;
    
    @FindBy(xpath = "//input[@id='nextFollowUpDate']")
    private WebElement NextFollowUpTime;    
    
    @FindBy(xpath = "//*[@id='followUpInfoForm']/div[2]/dl[4]/dd[2]/span/span/span[1]")
    private WebElement NextFollowUpType;
    
    @FindBy(xpath = "//*[@id='followUpInfoForm']/div[2]/dl[5]/dd[2]/span/span/span[1]")
    private WebElement Verification;
    
    @FindBy(xpath = "//button[@id='btnFollowUpSave']")
    private WebElement FollowUpSave;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement FollowUpSuccessToast;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement FollowUpValidation;
    
    @FindBy(xpath = "//*[@id='promotionGrid']//a[@role='button']")
    private WebElement PromotionCrossIcon;
    
  //*[@id="promotionGrid"]//a[@role="button"]
   
   
    public NewEnquiryPage(WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
        
    }
    public void clickSalesMenu() {
        try {
        	SalesMenu.click();
        } catch (Exception e) {
            System.err.println("Error clicking Sales Menu: " + e.getMessage());
            throw e;
        }
    }
    
    public void clickCustomerEnquirySubmenu() {
        try {
        	CustomerEnquirySubmenu.click();
        } catch (Exception e) {
            System.err.println("Error clicking Customer Enquiry Sub-menu: " + e.getMessage());
            throw e;
        }
    }
    
    public void clickCustomerEnquiryLink() {
        try {
        	CustomerEnquiryLink.click();
        } catch (Exception e) {
            System.err.println("Error clicking Customer Enquiry Link: " + e.getMessage());
            throw e;
        }
    }
    
    public void clickNewEnquiryButton() {
        try {
        	NewEnquiry.click();
        } catch (Exception e) {
            System.err.println("Error clicking New Enquiry button: " + e.getMessage());
            throw e;
        }
    }
    
 // Method to check if Sales Customer Enquiry Pop-up is displayed
    public boolean isSalesCustomerEnquiryPopupDisplayed() {
        try {
            return SalesCustomerEnquiryPopupHeader.isDisplayed();
        } catch (Exception e) {
            System.err.println("Error checking Sales Customer Enquiry Pop-up visibility: " + e.getMessage());
            return false;
        }
    }
    
    public void clickCloseIcon() {
        try {
        	CloseIcon.click();
        } catch (Exception e) {
            System.err.println("Error clicking Close Icon: " + e.getMessage());
            throw e;
        }
    }
    
 // Method to check if Customer Enquiry screen is displayed
    public boolean isCustomerEnquiryScreenDisplayed() {
        try {
            return CustomerEnquiryscreenHeader.isDisplayed();
        } catch (Exception e) {
            System.err.println("Error checking Customer Enquiry Screen visibility: " + e.getMessage());
            return false;
        }
    }
    
 // Method to check if Promotion Cross Icon is displayed
    public boolean isPromotionCrossIconDisplayed() {
        try {
            return PromotionCrossIcon.isDisplayed();
        } catch (Exception e) {
            System.err.println("Error checking Promotion Cross Icon visibility: " + e.getMessage());
            return false;
        }
    }
    
    public void clickPromotionCrossIcon() {
        try {
        	PromotionCrossIcon.click();
        } catch (Exception e) {
            System.err.println("Error clicking Promotion Cross Icon: " + e.getMessage());
            throw e;
        }
    }
    
    public void clickPincodeSearchIcon() {
        try {
        	PinCodeSearchIcon.click();
        } catch (Exception e) {
            System.err.println("Error clicking PinCode Search Icon: " + e.getMessage());
            throw e;
        }
    }
    
 // Method to check if Pincode search Screen is displayed
    public boolean isPincodeSearchScreenDisplayed() {
        try {
            return PincodesearchScreenHeader.isDisplayed();
        } catch (Exception e) {
            System.err.println("Error checking Pincode search Screen visibility: " + e.getMessage());
            return false;
        }
    }
    
    public void clickPinCodeSearchButton() {
        try {
        	PinCodeSearchButton.click();
        } catch (Exception e) {
            System.err.println("Error clicking PinCode Search Button: " + e.getMessage());
            throw e;
        }
    }
    
    public void clickLocationSelection() {
        try {
        	LocationSelection.click();
        } catch (Exception e) {
            System.err.println("Error clicking Location Selection from Pincodes List : " + e.getMessage());
            throw e;
        }
    }
    
    public void clickAddSelectedButton() {
        try {
        	AddSelectedButton.click();
        } catch (Exception e) {
            System.err.println("Error clicking Add Selected Button: " + e.getMessage());
            throw e;
        }
    }
 // Method to retrieve the text or value from the Pincode field
    public String getPincode() {
        return DisabledPincodeField.getText(); // Use getAttribute("value") for input fields
    }
    
 // Method to select a state from the dropdown
    public void selectState(String state) {
        try {
            Select dropdown = new Select(StateDropdown);
            dropdown.selectByVisibleText(state);
        } catch (Exception e) {
            System.err.println("An error occurred while selecting state: " + e.getMessage());
            throw e;
        }
    }

 // Method to select a District from the dropdown
    public void selectDistrict(String district) {
        try {
            Select dropdown = new Select(DistrictDropdown);
            dropdown.selectByVisibleText(district);
        } catch (Exception e) {
            System.err.println("An error occurred while selecting District: " + e.getMessage());
            throw e;
        }
    }
    
 // Method to select a Taluka from the dropdown
    public void selectTaluka(String taluka) {
        try {
            Select dropdown = new Select(TalukaDropdown);
            dropdown.selectByVisibleText(taluka);
        } catch (Exception e) {
            System.err.println("An error occurred while selecting taluka : " + e.getMessage());
            throw e;
        }
    }
    
 // Action to enter Post Office Name
    public void enterPostOfficeName(String postofficename) {
        try {
        	PostOfficeName.clear(); // Clear the field if necessary
        	PostOfficeName.sendKeys(postofficename);
        } catch (Exception e) {
            System.err.println("Error entering Post Office Name: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to enter Pincode
    public void enterPincode(String pincode) {
        try {
        	Pincode.clear(); // Clear the field if necessary
        	Pincode.sendKeys(pincode);
        } catch (Exception e) {
            System.err.println("Error entering Pincode: " + e.getMessage());
            throw e;
        }
    }
    
    public String getToastMessage() {
        try {
            return ToastMessage.getText();
        } catch (Exception e) {
            System.err.println("Error fetching toast message: " + e.getMessage());
            throw e;
        }
    }
    
    public String getInvalidMobileNumberToast() {
        try {
            return InvalidMobileNumberToast.getText();
        } catch (Exception e) {
            System.err.println("Error fetching Invalid Mobile Number toast message: " + e.getMessage());
            throw e;
        }
    }
    
    public String getNewMobileNumberToast() {
        try {
            return NewMobileToast.getText();
        } catch (Exception e) {
            System.err.println("Error fetching New Mobile Number toast message: " + e.getMessage());
            throw e;
        }
    }
    
    
    public String getInvalidEmailToast() {
        try {
            return InvalidEmailToast.getText();
        } catch (Exception e) {
            System.err.println("Error fetching Invalid Email Toast message: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to enter Mobile Number
    public void enterMobileNumber(String mobilenumber) {
        try {
        	MobileNumber.clear();
        	MobileNumber.sendKeys(mobilenumber);
        } catch (Exception e) {
            System.err.println("Error entering MobileNumber: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to enter Email Id
    public void enterEmail(String email) {
        try {
        	Email.clear();
        	Email.sendKeys(email);
        } catch (Exception e) {
            System.err.println("Error entering Email: " + e.getMessage());
            throw e;
        }
    }
    
    public void clickSaveButton() {
        try {
        	SaveButton.click();
        } catch (Exception e) {
            System.err.println("Error clicking Save Button: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to enter Cust Name
    public void enterCustName(String custname) {
        try {
        	CustName.clear();
        	CustName.sendKeys(custname);
        } catch (Exception e) {
            System.err.println("Error entering Cust Name: " + e.getMessage());
            throw e;
        }
    }
 // Action to enter Residence Phone
    public void enterResidencePhone(String residencephone) {
        try {
        	ResidencePhone.clear();
        	ResidencePhone.sendKeys(residencephone);
        } catch (Exception e) {
            System.err.println("Error entering Residence Phone: " + e.getMessage());
            throw e;
        }
    }
 // Action to enter WhatsApp Id
    public void enterWhatsAppId(String whatsappid) {
        try {
        	WhatsAppId.clear();
        	WhatsAppId.sendKeys(whatsappid);
        } catch (Exception e) {
            System.err.println("Error entering WhatsApp Id: " + e.getMessage());
            throw e;
        }
    }
 // Action to enter Address
    public void enterAddress(String address) {
        try {
        	Address.clear();
        	Address.sendKeys(address);
        } catch (Exception e) {
            System.err.println("Error entering Address: " + e.getMessage());
            throw e;
        }
    }
 // Action to enter Village
    public void enterVillage(String village) {
        try {
        	Village.clear();
        	Village.sendKeys(village);
        } catch (Exception e) {
            System.err.println("Error entering Village: " + e.getMessage());
            throw e;
        }
    }  
    
    public void selectValidCustType(String CustType) throws Exception {
        try {
            
            Select dropdown = new Select(CustTypeDropdown);
            dropdown.selectByVisibleText(CustType);

            System.out.println("Valid customer type selected: " + CustType);
        } catch (Exception e) {
            System.err.println("Error selecting valid customer type: " + e.getMessage());
            throw new Exception("Failed to select valid customer type.", e);
        }
    }
    
    public void selectValidGender(String Gender) throws Exception {
        try {
            
            Select dropdown = new Select(GenderDropdown);
            dropdown.selectByVisibleText(Gender);

            System.out.println("Valid Gender selected: " + Gender);
        } catch (Exception e) {
            System.err.println("Error selecting valid Gender: " + e.getMessage());
            throw new Exception("Failed to select valid Gender.", e);
        }
    }
    
    public void selectValidLocation(String Location) throws Exception {
        try {
            
            Select dropdown = new Select(LocationDropdown);
            dropdown.selectByVisibleText(Location);

            System.out.println("Valid Location selected: " + Location);
        } catch (Exception e) {
            System.err.println("Error selecting valid Location: " + e.getMessage());
            throw new Exception("Failed to select valid Location.", e);
        }
    }
    
    public void selectValidEnquirySource(String EnquirySource) throws Exception {
        try {
            
            Select dropdown = new Select(EnquirySourceDropdown);
            dropdown.selectByVisibleText(EnquirySource);

            System.out.println("Valid Enquiry Source selected: " + EnquirySource);
        } catch (Exception e) {
            System.err.println("Error selecting valid Enquiry Source: " + e.getMessage());
            throw new Exception("Failed to select valid Enquiry Source.", e);
        }
    }
    
    public void selectValidEnquirySubSource(String EnquirySubSource) throws Exception {
        try {
            
            Select dropdown = new Select(EnquirySubSourceDropdown);
            dropdown.selectByVisibleText(EnquirySubSource);

            System.out.println("Valid Enquiry Sub Source selected: " + EnquirySubSource);
        } catch (Exception e) {
            System.err.println("Error selecting valid Enquiry Sub Source: " + e.getMessage());
            throw new Exception("Failed to select valid Enquiry Sub Source.", e);
        }
    }
    
    public void selectValidEnquiryCategory(String EnquiryCategory) throws Exception {
        try {
            
            Select dropdown = new Select(EnquiryCategoryDropdown);
            dropdown.selectByVisibleText(EnquiryCategory);

            System.out.println("Valid Enquiry Category selected: " + EnquiryCategory);
        } catch (Exception e) {
            System.err.println("Error selecting valid Enquiry Category: " + e.getMessage());
            throw new Exception("Failed to select valid Enquiry Category.", e);
        }
    }
    
    public void selectValidPersonInCharge(String PersonInCharge) throws Exception {
        try {
            
            Select dropdown = new Select(PersonInChargeDropdown);
            dropdown.selectByVisibleText(PersonInCharge);

            System.out.println("Valid Enquiry Source selected: " + PersonInCharge);
        } catch (Exception e) {
            System.err.println("Error selecting valid Person In Charge: " + e.getMessage());
            throw new Exception("Failed to select valid Person In Charge.", e);
        }
    }
    
    
    public void selectValidModel(String Model) throws Exception {
        try {
            
            Select dropdown = new Select(ModelDropdown);
            dropdown.selectByVisibleText(Model);

            System.out.println("Valid Model selected: " + Model);
        } catch (Exception e) {
            System.err.println("Error selecting valid Model: " + e.getMessage());
            throw new Exception("Failed to select valid Model.", e);
        }
    }
    
    
    public void selectValidFuelType(String FuelType) throws Exception {
        try {
            
            Select dropdown = new Select(FuelTypeDropdown);
            dropdown.selectByVisibleText(FuelType);

            System.out.println("Valid Enquiry Source selected: " + FuelType);
        } catch (Exception e) {
            System.err.println("Error selecting valid Fuel Type: " + e.getMessage());
            throw new Exception("Failed to select valid Fuel Type.", e);
        }
    }
    
    
    public void selectValidVariant(String Variant) throws Exception {
        try {
            
            Select dropdown = new Select(VariantDropdown);
            dropdown.selectByVisibleText(Variant);

            System.out.println("Valid Enquiry Source selected: " + Variant);
        } catch (Exception e) {
            System.err.println("Error selecting valid Variant: " + e.getMessage());
            throw new Exception("Failed to select valid Variant.", e);
        }
    }
    
    
    public void selectValidSubVariant(String SubVariant) throws Exception {
        try {
            
            Select dropdown = new Select(SubVariantDropdown);
            dropdown.selectByVisibleText(SubVariant);

            System.out.println("Valid Sub Variant selected: " + SubVariant);
        } catch (Exception e) {
            System.err.println("Error selecting valid Sub Variant: " + e.getMessage());
            throw new Exception("Failed to select valid Sub Variant.", e);
        }
    }
    
    
    public void selectValidExtColor(String ExtColor) throws Exception {
        try {
            
            Select dropdown = new Select(ExtColorDropdown);
            dropdown.selectByVisibleText(ExtColor);

            System.out.println("Valid Ext Color selected: " + ExtColor);
        } catch (Exception e) {
            System.err.println("Error selecting valid Ext Color: " + e.getMessage());
            throw new Exception("Failed to select valid Ext Color.", e);
        }
    }
    
    
    public void selectValidIntColor(String IntColor) throws Exception {
        try {
            
            Select dropdown = new Select(IntColorDropdown);
            dropdown.selectByVisibleText(IntColor);

            System.out.println("Valid Int Color selected: " + IntColor);
        } catch (Exception e) {
            System.err.println("Error selecting valid Int Color: " + e.getMessage());
            throw new Exception("Failed to select valid Int Color.", e);
        }
    }
     
    public void selectValidSalesConsultant(String SalesConsultant) throws Exception {
        try {
            
            Select dropdown = new Select(SalesConsultantDropdown);
            dropdown.selectByVisibleText(SalesConsultant);

            System.out.println("Valid Sales Consultant selected: " + SalesConsultant);
        } catch (Exception e) {
            System.err.println("Error selecting valid Sales Consultant: " + e.getMessage());
            throw new Exception("Failed to select valid Sales Consultant.", e);
        }
    }
  
}
