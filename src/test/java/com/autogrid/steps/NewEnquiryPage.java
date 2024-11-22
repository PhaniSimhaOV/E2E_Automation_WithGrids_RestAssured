package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
    private WebElement CustomerEnquiryScreen;
    
    @FindBy(xpath = "//h1[@class='title_basic']")
    private WebElement Header;
    
    @FindBy(xpath = "//button[@id='btnNew']")
    private WebElement NewEnquiry;
    
    @FindBy(xpath = "//span[@class='k-link']")
    private WebElement BasicInfoTab;
    
    @FindBy(xpath = "//input[@id='mobileNo']")
    private WebElement MobileNumber;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement MobileNumberValidation;

    @FindBy(xpath = "//a[@id='searchCustomer']")
    private WebElement searchButton;
    
    @FindBy(xpath = "//*[@id='template']/div/h3")
    private WebElement NewMobilePopup;

    @FindBy(xpath = "//*[@id='enquiry_info']/div[2]/dl[1]/dd[1]/span/span/span[1]")
    private WebElement CustType;
    
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
    private WebElement Gender;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement GenderValidation;    
    
    @FindBy(xpath = "//*[@id='email']")
    private WebElement Email;
    
    @FindBy(xpath = "///*[@id='template']")
    private WebElement EmailValidation;    
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[2]/dl[3]/dd[1]/span/span/span[1]")
    private WebElement Location;
    
    @FindBy(xpath = "//*[@id='villName']")
    private WebElement Village;    
    
    @FindBy(xpath = "//*[@id='addr']")
    private WebElement Address;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[1]/span/span/span[1]")
    private WebElement EnquirySource;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement EnquirySourceValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[2]/span/span/span[1]")
    private WebElement EnquirySubSource;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement EnquirySubSourceValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[1]/dd[4]/span/span/span[1]")
    private WebElement PersonInCharge;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement PersonInChargeValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[1]/span/span/span[1]")
    private WebElement Model;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement ModelValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[2]/span/span/span[1]")
    private WebElement FuelType;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement FuelTypeValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[3]/span/span/span[1]")
    private WebElement Variant;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement VariantValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[2]/dd[4]/span/span/span[1]")
    private WebElement VariantSubType;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement VariantSubTypeValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[1]/span/span/span[1]")
    private WebElement ExtColour;
    
    @FindBy(xpath = "///*[@id='template']")
    private WebElement ExtColourValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[2]/span/span/span[1]")
    private WebElement IntColour;
    
    @FindBy(xpath = "//*[@id='template']")
    private WebElement IntColourValidation;
    
    @FindBy(xpath = "//*[@id='enquiry_info']/div[4]/dl[3]/dd[4]/span/span/span[1]")
    private WebElement SalesConsultant;
    
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
    private WebElement SuccessToast;

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
   
   
    public NewEnquiryPage(WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }
 
    }
