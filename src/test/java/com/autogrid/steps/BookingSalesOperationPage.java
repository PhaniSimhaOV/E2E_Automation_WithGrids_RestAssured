package com.autogrid.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.utils.CommonActions;

import java.time.Duration;

import static com.autogrid.utils.LaunchDriver.getDriver;

public class BookingSalesOperationPage {

    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(com.autogrid.steps.DMSLoginPage.class);
    private final CommonActions commonActions;

    @FindBy(xpath = "//a[text()='Sales']")
    private WebElement SalesIcon;
    @FindBy(xpath = "//a[text()='Sales Operation']")
    private WebElement SalesOperationButton;
    @FindBy(xpath = "//*[@id=\"gnb\"]/li[3]/div/ul/li[3]/ul/li[1]")
    private WebElement selectCustomerBookingMgtListMainLinks;
    @FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[1]/span/span")
    private WebElement dropdownDateOf;
    @FindBy(xpath = "//li[text()=\"Enquiry\"]")
    private WebElement SelectEnqiryDropdown;
    @FindBy(xpath = "//input[@id=\"baseTxt\"]")
    private WebElement MobileNoTextField;
    @FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[1]/span/span")
    private WebElement BasedOnDropdown;
    @FindBy(xpath = "//*[@id=\"baseStatCd_listbox\"]/li[4]")
    private WebElement BasedOnMobileDropdown;
    @FindBy(xpath = "//*[@id='sStartDt']")
    private WebElement StartDate;
    @FindBy(xpath = "//*[@id=\"sEndDt\"]")
    private WebElement EndDate;
    @FindBy(xpath = "  //button[text()=\"Search\"]")
    private WebElement SearchButton;
    @FindBy(xpath = "//*[@id=\"mainGrid\"]/div[3]/table/tbody")
    private WebElement SalesTable;

    public BookingSalesOperationPage(WebDriver driver) {
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }

    public void SalesIconButton() throws InterruptedException {
        try {
            Thread.sleep(15000);

            SalesIcon.click();
        } catch (Exception e) {
            System.err.println("Error in Clicking the Sales Icon : " + e.getMessage());
            throw e;
        }
    }

    public void SalesTable() {
        try {
            for (int i = 0; i <= 1; i++) {
                SalesTable.click();
            }
        } catch (Exception e) {
            System.err.println("Error in Clicking the row of data: " + e.getMessage());
            throw e;
        }

    }

    public void SalesOperationLink() {
        try {
            SalesOperationButton.click();
        } catch (Exception e) {
            System.err.println("Error in Clicking the SalesOperation Button  : " + e.getMessage());
            throw e;
        }
    }

    public void selectCustomerBookingMgtListMainLinks() {
        try {
            selectCustomerBookingMgtListMainLinks.click();
        } catch (Exception e) {
            System.err.println("Error in Clicking the select Customer BookingMgt ListMain Links: " + e.getMessage());
            throw e;
        }
    }

    public void selectDateOFDropdown() {
        try {
            dropdownDateOf.click();
            SelectEnqiryDropdown.click();

        } catch (Exception e) {
            System.err.println("Error in Selecting the enquiry from the dropdown: " + e.getMessage());
            throw e;
        }
    }

    public void MobileNumberTextBox() {
        try {
            MobileNoTextField.sendKeys("7799222422");
        } catch (Exception e) {
            System.err.println("Error in passing the mobile number to the text field: " + e.getMessage());
            throw e;
        }
    }

    public void BasedOnDropdown() {
        try {
            BasedOnDropdown.click();
            BasedOnMobileDropdown.click();
        } catch (Exception e) {
            System.err.println("Error in selecting the BasedOnDropdown : " + e.getMessage());
            throw e;
        }
    }

    public void SearchButton() {
        try {
            SearchButton.click();

        } catch (Exception e) {
            System.err.println("Error in clicking the search button : " + e.getMessage());
            throw e;
        }
    }

    public void SelectDates() {
        try {
            StartDate.clear();
            EndDate.clear();
            StartDate.sendKeys("01102024");
            EndDate.sendKeys("30122024");
        } catch (Exception e) {
            System.err.println("Error in passing the dates: " + e.getMessage());
            throw e;
        }
    }

    public void fillfieldsBookingPage() {
        try {
            getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).sendKeys("34512678");
            System.out.println("The GST number as been passed");
            //EnquiryType:
            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"enType_listbox\"]/li[1]")).click();
            System.out.println("The Enquiry type as being selected");
            //EnquiryCategory
            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[2]")).click();
            getDriver().findElement(By.xpath("//*[text()='Corporate Company']")).click();
            System.out.println("The Enquiry Category as being selected");
            //SalesConsultant
            getDriver().findElement(By.xpath(" //*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[3]")).click();
            getDriver().findElement(By.xpath("//li[text()='CHENIGACHERLA SAMPATH RAJ [ES52370427]']")).click();
            System.out.println("The Sales Consultant as being selected");
            //Referred by :
            getDriver().findElement(By.xpath("//*[@id='eqryRfrlBy']")).sendKeys("CHENIGACHERLA SAMPATH RAJ");
            System.out.println("The Referred by as being selected");
            //RegisteredCorp
            getDriver().findElement(By.xpath("//a[@href='javascript:fn_searchRegisteredCorp()")).click();
            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl/dd[1]/span/span")).click();
            getDriver().findElement(By.xpath("//li[text()=\"Classic\"]")).click();
            getDriver().findElement(By.xpath("//*[@id='window']/div[1]/div/button[2]]")).click();
            //Select a coulumn from the search table
            getDriver().findElement(By.xpath("//*[@id=\"gridBody\"]/div[2]/table/tbody/tr[1]/td[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"gridBody\"]/div[2]/table/tbody/tr[1]/td[1]")).click();
            //ModeOfPurchase
            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[2]/dd[1]/span/span/span[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"purchaseType_listbox\"]/li[4]")).click();
            //Title
            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[2]/div[2]/dl[2]/dd[1]/span/span")).click();
            getDriver().findElement(By.xpath("//li[text()='Mr.']")).click();
            //Pan
            getDriver().findElement(By.xpath("//*[@id=\"pan\"]")).sendKeys("sdfghj345");
            //BillingAddress
            getDriver().findElement(By.xpath("//a[@href='javascript:fn_pinCodeSearch();']")).click();
            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl[1]/dd[1]/span/span")).click();
            getDriver().findElement(By.xpath("//*[@id=\"sStcdName_listbox\"]/li[4]")).click();
            getDriver().findElement(By.xpath("//*[@id='window']/div[1]/div/button[2]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[2]/table/tbody/tr[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[2]/table/tbody/tr[1]")).click();
            //Shipping address
            getDriver().findElement(By.xpath("//*[@id=\"bkngShipToName\"]")).sendKeys("Srujan");
            getDriver().findElement(By.xpath("//*[@id=\"bkngShipToAddr\"]")).sendKeys("Assam");
            getDriver().findElement(By.xpath("//a[@href=\"javascript:fn_pinCodeSearch2('Ship');\"]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl[1]/dd[1]/span/span")).click();
            getDriver().findElement(By.xpath("//*[@id=\"sStcdName_listbox\"]/li[4]")).click();
            getDriver().findElement(By.xpath("//*[@id='window']/div[1]/div/button[2]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[2]/table/tbody/tr[1]")).click();
            //Register
            getDriver().findElement(By.xpath("//button[@id='btnBookingRegister']")).click();
            getDriver().findElement(By.xpath("/html/body/div[117]/div[2]/p[2]/button[1]")).click();

            String SuccesMessage=getDriver().findElement(By.xpath("//*[@id=\"template\"]/div/div/p")).getText();
            System.out.println(SuccesMessage);

        } catch (Exception e) {
            System.err.println("Error in passing the dates: " + e.getMessage());
            throw e;
        }
    }
    public void QuotationPage(){
        getDriver().findElement(By.xpath("//*[text()=\"Quotation\"]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"basicInsurance\"]")).sendKeys("150000");
        getDriver().findElement(By.xpath("//*[@id=\"exWarrantyAmount\"]")).sendKeys("1");
        getDriver().findElement(By.xpath("//*[@id=\"rtoCharges\"]")).sendKeys("106450");
        getDriver().findElement(By.xpath("//*[@id=\"btnFinanceModify\"]")).click();

    }
    public void ReceiptTab(){
        getDriver().findElement(By.xpath("//*[@id=\"receiptTab\"]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"btnPayAdd\"]")).click();
    }
    public void AmountReceiptPage(){
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[2]")).sendKeys("11000");
       getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[3]")).click();
        getDriver().findElement(By.xpath("/html/body/div[115]/div/div[2]/ul/li[2]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[4]")).click();
        getDriver().findElement(By.xpath("/html/body/div[115]/div/div[2]/ul/li[4]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[5]")).click();
        getDriver().findElement(By.xpath("/html/body/div[115]/div/div[2]/ul/li[3]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[6]")).sendKeys("03/12/2024");
        getDriver().findElement(By.xpath("//*[@id='paymentGrid']/div[3]/table/tbody/tr/td[7]")).sendKeys("12345678000");
        getDriver().findElement(By.xpath("//*[@id=\"btnPaySave\"]")).click();
    }
}
