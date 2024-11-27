package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustBookingMgmtPage {
    private static final Logger logger = LoggerFactory.getLogger(CustomerBookingMgmtListPage.class);
    private final CommonActions commonActions;
    
    public CustBookingMgmtPage(WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//a[normalize-space()='Sales']")
    private WebElement SalesMenu;

    @FindBy(xpath = "//a[normalize-space()='Sales Operation']")
    private WebElement SalesOperation;
    
    @FindBy(xpath="//span[contains(text(),'Customer Booking Mgt List')]")
    private WebElement CustomerBookingMgmtListTab ; 
    
   
    @FindBy(xpath="//a[normalize-space()='Customer Booking Mgt']")
    private WebElement CustomerBookingMgt;
    
    @FindBy(xpath="//button[@id='btnDwnldDigitalDocket']")
    private WebElement DwnldDigitalDocket;
    
    @FindBy(xpath="//button[@id='btnActiveVariant']")
    private WebElement ActiveVariant;
    
    @FindBy(xpath="//button[@id='btnCtb']")
    private WebElement ClickToBuy;
    
    @FindBy(xpath="//button[@id='btnMainModify']")
    private WebElement Modify;
    
    @FindBy(xpath="//button[@id='btnEnquiryCancel']")
    private WebElement EnquiryCancellation;
    
    @FindBy(xpath=" //input[@id='dotYn']")
    private WebElement Checkboxdot;
    
    @FindBy(xpath=" //input[@id='smsYn']")
    private WebElement Checkboxsms;
    
    @FindBy(xpath="//input[@id='custNo']")
    private WebElement CustomerId;
    
    @FindBy(xpath="//span[@aria-owns='custType_listbox']//span[@class='k-input']")
    private WebElement CustomerType;
    
    @FindBy(xpath="//input[@id='custName']")
    private WebElement CustomerName;
    
    @FindBy(xpath="//input[@id='custBrtDate']")
    private WebElement CustomerDOB;
    
    @FindBy(xpath="//span[@aria-owns='gender_listbox']//span[@class='k-input']")
    private WebElement Gender;
    
    @FindBy(xpath="//input[@id='custGstNo']")
    private WebElement CustomerGSTNo;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[2]/dl[2]/dd[3]/span/span/span[1]")
    private WebElement Occupation;
    
    @FindBy(xpath="//input[@id='eMail']")
    private WebElement EMail;
    
    @FindBy(xpath="//input[@id='addr']")
    private WebElement Address;
    
    @FindBy(xpath="//input[@id='state']")
    private WebElement State;
    
    @FindBy(xpath=" //input[@id='city']")
    private WebElement CityTown;
    
    @FindBy(xpath="//input[@id='tehsilCd']")
    private WebElement District; 
    
    @FindBy(xpath="//input[@id='villageNm']")
    private WebElement Village;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[2]/dl[4]/dd[3]/span/span/span[1]")
    private WebElement Location;
    
    @FindBy(xpath="//input[@id='pinCd']")
    private WebElement Pin; 
    
    @FindBy(xpath="//input[@id='contactNo']")
    private WebElement ContactNo;
    
    @FindBy(xpath=" //input[@id='phonNo']")
    private WebElement PhoneNo;
    
    @FindBy(xpath="//input[@id='addAltNo']")
    private WebElement AlternateNo;
    
    @FindBy(xpath="//input[@id='DlrNm']")
    private WebElement DealerName;
    
    @FindBy(xpath="//span[@aria-owns='enSourceCd_listbox']//span[@class='k-input']")
    private WebElement EnquirySource;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[3]/dl[1]/dd[3]/span/span/span[1]")
    private WebElement EnquirySubSource;
    
    @FindBy(xpath="//input[@id='enquiryDate']")
    private WebElement EnquiryDate;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[1]/span/span/span[1]")
    private WebElement EnquiryType;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[2]/span/span/span[1]")
    private WebElement EnquiryCategory;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[3]/span/span/span[1]")
    private WebElement SalesConsultant;
    
    @FindBy(xpath="//input[@id='eqryRfrlBy']")
    private WebElement ReferredBy;
    
    @FindBy(xpath="//span[contains(text(),'Yes')]")
    private WebElement FinanceReq; 
    
    @FindBy(xpath="//input[@id='corpName']")
    private WebElement RegisteredCorp; 
    
    @FindBy(xpath="//input[@id='eqryCorpName']")
    private WebElement CorpName;
    
    @FindBy(xpath="//input[@id='associateNm']")
    private WebElement Associate;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[4]/dl[2]/dd[1]/span/span/span[1]")
    private WebElement Model1;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[4]/dl[2]/dd[2]/span/span/span[1]")
    private WebElement FuelType;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[4]/dl[2]/dd[3]/span/span/span[1]")
    private WebElement Variant;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[4]/dl[2]/dd[4]/span/span/span[1]")
    private WebElement SubVariant;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[4]/dl[3]/dd[1]/span/span/span[1]")
    private WebElement ExtColor;
    
    @FindBy(xpath="//*[@id=\"basicInfo\"]/div[4]/dl[3]/dd[2]/span/span/span[1]")
    private WebElement IntColor;
    
    @FindBy(xpath="//input[@id='promoCode']")
    private WebElement PromoCode;
    
    @FindBy(xpath="//input[@id='testDriveTakenFlag']")
    private WebElement TestDrive;

   
    private WebElement 
    
   public void clickOnSalesMenu() {
    	commonActions.clickElement(SalesMenu);;
    }
    
    
    public void clickOnSalesOperation() {
    	commonActions.clickElement(SalesOperation);
    	
    }
    
   
    public void launchSite() throws InterruptedException {
        LaunchDriver.launchSite();
    }

   

}
