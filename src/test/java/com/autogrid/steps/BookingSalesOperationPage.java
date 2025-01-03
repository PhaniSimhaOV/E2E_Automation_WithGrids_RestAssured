package com.autogrid.steps;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import com.autogrid.utils.LaunchDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.utils.CommonActions;
import org.testng.Assert;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static com.autogrid.utils.LaunchDriver.getDriver;

public class BookingSalesOperationPage {

    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DMSLoginPage.class);
    private final CommonActions commonActions;
    private Map<String, String> testData; // Stores data from Excel
    private List<Map<String, String>> allTestData; // List to store all data rows from Excel
    private int currentDataRowIndex = 0;

    @FindBy(xpath = "//a[text()='Sales']")
    private WebElement SalesIcon;
    @FindBy(xpath = "//a[text()='Sales Operation']")
    private WebElement SalesOperationButton;
    @FindBy(xpath = "//*[@id=\"gnb\"]/li[3]/div/ul/li[3]/ul/li[1]")
    private WebElement selectCustomerBookingMgtListMainLinks;
    @FindBy(xpath = "/html/body/section/div/section/section[1]/div[2]/dl[1]/dd[1]")
    private WebElement dropdownDateOf;
    @FindBy(xpath = "//li[text()=\"Enquiry\"]")
    private WebElement SelectEnqiryDropdown;
    @FindBy(id = "baseTxt")
    private WebElement MobileNoTextField;
    @FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[1]/span/span")
    private WebElement BasedOnDropdown;

    @FindBy(xpath = "//*[@id='sStartDt']")
    private WebElement StartDate;
    @FindBy(xpath = "//*[@id=\"sEndDt\"]")
    private WebElement EndDate;
    @FindBy(xpath = "  //button[text()=\"Search\"]")
    private WebElement SearchButton;
    @FindBy(xpath = "//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td[2]")
    private WebElement SalesTable;
    @FindBy(xpath = "//iframe[@name='tabMenuFrame2']")
    private WebElement iframename2;
    @FindBy(xpath = "//ul[@id='dSearchCd_listbox']/li")
    private List<WebElement> optionsDateOF;
    @FindBy(xpath = "//input[@class=\"form_input\"]")
    private WebElement MobileNumberField;
    @FindBy(xpath = "//ul[@id=\"baseStatCd_listbox\"]/li")
    List<WebElement> BasedOnDropdownoptions;
    @FindBy(xpath = "//iframe[@name='tabMenuFrame3']")
    private WebElement iframename3;
    @FindBy(xpath = "//*[@id='basicInfo']/div[3]/dl[2]/dd[1]")
    private WebElement basicinfoEnquiry;

    @FindBy(xpath = "//*[@id=\"enType_listbox\"]/li[1]")
    private WebElement basicinfoEnquiryList;
    @FindBy(xpath = "//*[@id='basicInfo']/div[3]/dl[2]/dd[2]")
    private WebElement EnquiryCategoryField;
    @FindBy(xpath = "//ul[@id=\"enCategory_listbox\"]/li")
    private List<WebElement> optionsEnquiryCategory;
    @FindBy(xpath = " //*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[3]")
    private WebElement SalesConsultantField;
    @FindBy(xpath = "//ul[@id=\"enSaleEmpNo_listbox\"]/li")
    private List<WebElement> SalesConsultantFieldoptions;
    @FindBy(xpath = "//*[@id='eqryRfrlBy']")
    private WebElement ReferredByField;
    @FindBy(xpath = "//*[@id=\"bookingInfo\"]/section[2]/div[2]/dl[2]/dd[1]/span/span")
    private WebElement TitleField;
    @FindBy(xpath = "//*[@id=\"titleType_listbox\"]")
    private List<WebElement> optionsTitleFieldDropdown;
    @FindBy(xpath = "//*[@id=\"pan\"]")
    private WebElement panfield;
    @FindBy(xpath = "//button[@id='btnBookingRegister']")
    private WebElement RegisterButton;
    @FindBy(xpath = "/html/body/div[116]/div[2]/p[2]/button[1]")
    private WebElement confirmRegisterButton;
    @FindBy(xpath = "//*[@id=\"btnBookingModify\"]")
    private WebElement modifybutton;
    @FindBy(xpath = "/html/body/div[116]/div[2]/p[2]/button[1]")
    private WebElement confirmmodifybutton;
    @FindBy(xpath = "//*[@id=\"orpTab\"]")
    private WebElement QuotationButton;
    @FindBy(xpath = "//*[@id=\"btnFinanceModify\"]")
    private WebElement QuotationButtonModify;
    @FindBy(xpath = "//*[@id=\"paymentGrid\"]/div[2]/table/tbody/tr[1]/td[8]/button")
    private WebElement ShareReceiptButton;
    @FindBy(xpath = "//*[@id=\"receiptTab\"]")
    private WebElement ReceiptField;
    @FindBy(xpath = "//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[2]")
    private WebElement AmountPage;
    @FindBy(xpath = "//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[3]")
    private WebElement OnAccountOf;
    @FindBy(xpath = "//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[3]")
    private WebElement OnAccountOf1;
    @FindBy(xpath = "/html/body/div[115]/div/div[2]/ul/li[3]")
    private WebElement OnAccountList;
    @FindBy(xpath = "//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[4]")
    private WebElement PaymentAccount;
    @FindBy(xpath = "//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[5]")
    private WebElement DrawnBank;
    @FindBy(xpath = "/html/body/div[115]/div/div[2]/ul/li[3]")
    private WebElement DrawnBankList;
    @FindBy(xpath = "//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[6]")
    private WebElement TransactionDate;
    @FindBy(xpath = "//input[@data-format='dd/MM/yyyy']")
    private WebElement TransactionDateEnter;
    @FindBy(xpath = "//*[@id='paymentGrid']/div[3]/table/tbody/tr/td[7]")
    private WebElement ChequeNo;
    @FindBy(xpath = "//input[@name='chqNo']")
    private WebElement ChequeNumberEnter;
    @FindBy(xpath = "//*[@id=\"btnPaySave\"]")
    private WebElement saveButtonAccount;
    @FindBy(xpath = "//input[@id='usrId']")
    private WebElement AccountUsername;
    @FindBy(xpath = "//input[@id='usrPswdNo']")
    private WebElement AccountPassword;
    @FindBy(xpath = "//*[text()='Send Customer Consent Link']")
    private WebElement SendConsentField;
    @FindBy(xpath = "/html/body/div[116]/div[2]/p[2]/button[1]")
    private WebElement sendConsentconfirm;
    @FindBy(xpath = "//a[text()='Order/Stock']")
    private WebElement OrderStockButton;
    @FindBy(xpath = "//*[@id=\"receiptTab\"]")
    private WebElement ReceiptTab;
    @FindBy(xpath = "//*[@id=\"gnb\"]/li[3]/div/ul/li[4]/ul/li[4]/a")
    private WebElement DealerVechileStock;
    @FindBy(xpath = "//*[@id=\"sVin\"]")
    private WebElement vinNumber;
    @FindBy(xpath = "//*[@class='btn_m btn_search k-button']")
    private WebElement vinSearch;
    @FindBy(xpath = "//*[@id=\"gnb\"]/li[2]/div/ul/li[3]/ul/li[1]/a")
    private WebElement mgtListSales;
    @FindBy(xpath = "//*[@id=\"grid\"]/div[3]/table/tbody/tr/td[11]")
    private WebElement VariantValueTab;
    @FindBy(xpath = "//*[@id=\"grid\"]/div[3]/table/tbody/tr/td[15]")
    private WebElement ExteriorColorTab;
    @FindBy(xpath = "//*[@id=\"grid\"]/div[3]/table/tbody/tr/td[17]")
    private WebElement InteriorColorTab;
    @FindBy(xpath = "//*[@id=\"sidebar\"]/div[1]/ul/li[3]")
    private WebElement VerifyData;
    @FindBy(xpath = "/html/body/section/div/section/section[1]/div[2]/dl[1]/dd[1]")
    private WebElement verifydata1;
    @FindBy(xpath = "//ul[@id='dSearchCd_listbox']/li")
    private List<WebElement> VerifyOptions;
    @FindBy(xpath = "//*[@id=\"orpInfo\"]/section/div[2]/dl[2]/dd[1]/span/span/input[1]")
    private WebElement RTOAmountField;
    @FindBy(xpath = " //*[@id=\"orpInfo\"]/section/div[2]/dl[1]/dd[2]/span/span/input[1]")
    private WebElement ExShowrroom;
    @FindBy(xpath = "//*[@id='registrationNm']")
    private WebElement RegistrationNm;
    @FindBy(xpath = "//*[@id=\"bkngShipToName\"]")
    private WebElement ShipToName;
    @FindBy(xpath = "//*[@id=\"addressTab\"]")
    private WebElement AddressTab;
    @FindBy(xpath = "//a[@href='javascript:fn_pinCodeSearch()")
    private WebElement pinCodeSearchBilling;
    @FindBy(xpath = "//*[@id='window']/div[2]/dl[1]/dd[1]/span/span")
    private WebElement statedropdown;
    @FindBy(xpath = "//*[@id=\"sPinCode\"]")
    private WebElement pinCodeBilling;
    @FindBy(xpath = "//*[@id='window']/div[1]/div/button[2]")
    private WebElement serachBilling;
    @FindBy(xpath = "//*[@id=\"bkngShipToAddr\"]")
    private WebElement ShipToAddr;
    @FindBy(xpath = "//*[@id=\"bkngFileLoginDate\"]")
    private WebElement fileLoginDate;
    @FindBy(xpath = "//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[3]/dd[1]/span/span/span[1]")
    private WebElement bookingInfoFinancer;
    @FindBy(xpath = "//*[@id=\"financierCd_listbox\"]")
    List<WebElement> financierCdList;
    @FindBy(xpath = "//*[@id=\"btnBookingRegister\"]")
    private WebElement btnBookingRegister;
    @FindBy(xpath = "//*[@id=\"btnBookingModify\"]")
    private WebElement btnBookingModify;


    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    public BookingSalesOperationPage(WebDriver driver) throws Exception {
        this.commonActions = new CommonActions(driver);

        PageFactory.initElements(driver, this);

    }

    public void SalesIconButton() throws InterruptedException {
        try {
            Thread.sleep(3000 * 5);
            SalesIcon.click();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.err.println("Error in Clicking the Sales Icon : " + e.getMessage());
            throw e;
        }
    }

    public void SalesTable() {
        try {
            Actions actions = new Actions(getDriver());
            actions.doubleClick(SalesTable).perform();

        } catch (Exception e) {
            System.err.println("Error in Clicking the row of data: " + e.getMessage());
            throw e;
        }

    }

    public void
    SalesOperationLink() throws InterruptedException {
        try {
            Thread.sleep(4000);
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

    public void iframe2() {
        LaunchDriver.getDriver().switchTo().defaultContent();
        LaunchDriver.getDriver().switchTo().frame(iframename2);
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void iframe3() {
        LaunchDriver.getDriver().switchTo().defaultContent();
        LaunchDriver.getDriver().switchTo().frame(iframename3);
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void selectDateOFDropdown() throws InterruptedException {
        try {
            iframe2();
            dropdownDateOf.click();
            WebElement selectedOption = optionsDateOF.get(0);
            selectedOption.click();
            System.out.println("Selected Option: " + selectedOption.getText());


        } catch (Exception e) {
            System.err.println("Error in Selecting the enquiry from the dropdown: " + e.getMessage());
            throw e;

        }
    }


    public void MobileNumberTextBox(String mobileNumber) {
        try {

            MobileNumberField.clear();
            MobileNumberField.sendKeys(mobileNumber);
        } catch (Exception e) {
            System.err.println("Error entering Mobile in Filter: " + e.getMessage());
            throw e;
        }
    }

    public void BasedOnDropdown() throws InterruptedException {
        try {

            BasedOnDropdown.click();
            Thread.sleep(2000);
            WebElement BasedOnMobileDropdown = BasedOnDropdownoptions.get(3);
            BasedOnMobileDropdown.click();
            System.out.println("Selected value: " + BasedOnMobileDropdown);

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

    public void SelectDates() throws InterruptedException {
        try {
            StartDate.clear();
            EndDate.clear();
            Thread.sleep(3000);
            StartDate.sendKeys("01102024");
            EndDate.sendKeys("30122024");
        } catch (Exception e) {
            System.err.println("Error in passing the dates: " + e.getMessage());
            throw e;
        }
    }

    public void fillfieldsBookingPage(String pan, String RegistrationName, String address, String StateValue, String pincode, String LoginDate) throws InterruptedException, AWTException {
        try {
            iframe3();
            Thread.sleep(3000);
            //EnquiryType:
            basicinfoEnquiry.click();
            basicinfoEnquiryList.click();
            System.out.println("The Enquiry type as being selected");
            Thread.sleep(6000);


            //Pan
            panfield.clear();
            panfield.sendKeys(pan);
            System.out.println("The Pan as being selected" + panfield.getText());
            iframe3();
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("window.scrollBy(0,1000)");
            System.out.println("Scrolled down successfully.");
            //RegisteredName
            RegistrationNm.clear();
            RegistrationNm.sendKeys(RegistrationName);
            ShipToName.clear();
            ShipToName.sendKeys(RegistrationName);
            System.out.println("RegistrationNm is." + RegistrationNm.getText());
            AddressTab.clear();
            AddressTab.sendKeys(address);
            ShipToAddr.clear();
            ShipToAddr.sendKeys(address);
            //ModeOfPurchase
            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[2]/dd[1]/span/span/span[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"purchaseType_listbox\"]/li[4]")).click();
            System.out.println("The Purchase Type as being selected");
            Thread.sleep(3000);
            //BillingAddress
            pinCodeSearchBilling.click();
            WebElement iframenameBilling = getDriver().findElement(By.xpath("//iframe[@class='k-content-frame']"));
            LaunchDriver.getDriver().switchTo().defaultContent();
            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
            LaunchDriver.getDriver().switchTo().frame(iframenameBilling);
            System.out.println("Successfully interacted with the element inside the iframe. for billing address");
            statedropdown.click();
            Thread.sleep(2000);
            List<WebElement> ShippingAddress = getDriver().findElements(By.xpath("//ul[@id=\"sStcdName_listbox\"]/li"));
            for (WebElement option : ShippingAddress) {
                if (option.getText().equals(StateValue)) {
                    option.click();
                    break;
                }
            }
            pinCodeBilling.clear();
            pinCodeBilling.sendKeys(pincode);
            serachBilling.click();
            WebElement SalesTable = getDriver().findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]"));
            Actions actions = new Actions(getDriver());
            actions.doubleClick(SalesTable).perform();
            Thread.sleep(2000);
            System.out.println("Billing address is updated");
            fileLoginDate.click();
            fileLoginDate.sendKeys(LoginDate);
            bookingInfoFinancer.click();
            financierCdList.get(3).click();
            btnBookingRegister.click();
            iframe3();
            confirmRegisterButton.click();
            System.out.println("Successfully clicked on register");
            Thread.sleep(5000);
            btnBookingModify.click();
            iframe3();
            iframe3();
            confirmmodifybutton.click();
            System.out.println("Modified successfully");


//            //EnquiryCategory
////            EnquiryCategoryField.click();
////            WebElement Enquirycategory = optionsEnquiryCategory.get(3);
//            Thread.sleep(3000);
////            Enquirycategory.click();
//            //System.out.println("The Enquiry Category as being selected");
            //SalesConsultant
//            SalesConsultantField.click();
//            WebElement selectedOption = SalesConsultantFieldoptions.get(15);
//            Thread.sleep(3000);
//            selectedOption.click();
//            System.out.println("Selected Option: " + selectedOption.getText());
//            System.out.println("The Sales Consultant as being selected");
//            //Referred by :
//            ReferredByField.sendKeys("CHENIGACHERLA SAMPATH RAJ");
//            System.out.println("The Referred by as being selected");
//            Thread.sleep(3000);
//            //Title
//            TitleField.click();
//            String valueToSelect = "Mr.";
//            boolean valueFound = false;
//            for (WebElement item : optionsTitleFieldDropdown) {
//                if (item.getText().equals(valueToSelect)) {
//                    item.click();
//                    valueFound = true;
//                    System.out.println("Selected value: " + valueToSelect);
//                    break;
//                }
//            }
//            System.out.println("Title is selected");
            //            getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).clear();
//            getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).sendKeys("34512678");
//            System.out.println("The GST number as been passed" +getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).getText());
            //RegisteredCorp
//            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[4]/dl[1]/dd[2]/div/a")).click();
//            Thread.sleep(2000);
//            LaunchDriver.getDriver().switchTo().defaultContent();
//            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']"))); getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl/dd[1]/span/span")).click();
//            getDriver().findElement(By.xpath("//li[text()=\"Classic\"]")).click();
//            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[1]/div")).click();
            //Select a coulumn from the search table
//                getDriver().findElement(By.xpath("//*[@id=\"gridBody\"]/div[2]/table/tbody/tr[1]/td[1]")).click();
//                getDriver().findElement(By.xpath("//*[@id=\"gridBody\"]/div[2]/table/tbody/tr[1]/td[1]")).click();
//            System.out.println("Registered corp is slected successfully");


            //Registered Loan amount
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).clear();
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).sendKeys("15000");
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).sendKeys(Keys.ENTER);

            //Shipping address
//            getDriver().findElement(By.xpath("//*[@id=\"bkngShipToName\"]")).clear();
//            getDriver().findElement(By.xpath("//*[@id=\"bkngShipToName\"]")).sendKeys("Srujan");
//            System.out.println("Name is updated");
//            Thread.sleep(2000);
//            getDriver().findElement(By.xpath("//*[@id=\"bkngShipToAddr\"]")).clear();
//            getDriver().findElement(By.xpath("//*[@id=\"bkngShipToAddr\"]")).sendKeys("Assam");
//            System.out.println("Address is updated");
//            getDriver().findElement(By.xpath("//a[@href=\"javascript:fn_pinCodeSearch2('Ship');\"]")).click();
//            Thread.sleep(2000);
//            WebElement iframenameShipping = getDriver().findElement(By.xpath("//iframe[@class='k-content-frame']"));
//            LaunchDriver.getDriver().switchTo().defaultContent();
//            Thread.sleep(2000);
//            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
//            LaunchDriver.getDriver().switchTo().frame(iframenameShipping);
//            Thread.sleep(2000);
//            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl[1]/dd[1]/span/span")).click();
//            Thread.sleep(2000);
//            getDriver().findElement(By.xpath("//*[@id=\"sStcdName_listbox\"]/li[4]")).click();
//            getDriver().findElement(By.xpath("//*[@id='window']/div[1]/div/button[2]")).click();
//            WebElement ShippingTable = getDriver().findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]"));
//            actions.doubleClick(ShippingTable).perform();
//            Thread.sleep(2000);
//            Thread.sleep(2000);
//            System.out.println("Shipping Table address is updated");
//            Thread.sleep(2000);
//            LaunchDriver.getDriver().switchTo().defaultContent();
//            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
//

        } catch (Exception e) {
            System.err.println("Error in passing the dates: " + e.getMessage());
            throw e;
        }
    }

    public void clickbutton(String button) {
        //Register
        if (button.equalsIgnoreCase("Register")) {
            RegisterButton.click();
            iframe3();
            confirmRegisterButton.click();
            System.out.println("Successfully clicked on register");
        }
        //modify
        else {
            modifybutton.click();
            iframe3();
            confirmmodifybutton.click();
            System.out.println("Modified successfully");

        }
    }

    public void QuotationPage(String RTOamount, String ExShowrroomamount) throws InterruptedException {
        Thread.sleep(3000);
        QuotationButton.click();

        try {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(RTOAmountField).click() // Move to the field and click to focus
                    .keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
                    .keyUp(Keys.CONTROL)
                    .sendKeys(Keys.BACK_SPACE) // Delete the selected text
                    .perform();

            actions.click(RTOAmountField).sendKeys(RTOamount).build().perform();
            System.out.println("Successfully entered text: " + RTOamount);
            actions.moveToElement(ExShowrroom).click() // Move to the field and click to focus
                    .keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
                    .keyUp(Keys.CONTROL)
                    .sendKeys(Keys.BACK_SPACE) // Delete the selected text
                    .perform();

            actions.click(ExShowrroom).sendKeys(ExShowrroomamount).build().perform();
            System.out.println("Successfully entered text: " + ExShowrroomamount);


        } catch (Exception e) {
            System.err.println("Error while entering text using Actions: " + e.getMessage());
            throw new RuntimeException("Failed to enter text using Actions.", e);
        }

        QuotationButtonModify.click();
        Thread.sleep(4000);
        //          getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[2]/dd[1]/span/span/input[1]")).clear();
//          getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[2]/dd[1]/span/span/input[1]")).sendKeys(RTOamount);
//        Thread.sleep(3000);
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[1]/dd[3]/span/span/input[1]")).sendKeys(Keys.ENTER);
//        System.out.println("Basic insurance is updated successfully");
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[3]/dd[3]/span/span/input[1]")).click();
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[3]/dd[3]/span/span/input[1]")).clear();
//        Thread.sleep(3000);
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[3]/dd[3]/span/span/input[1]")).sendKeys("1");
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[3]/dd[3]/span/span/input[1]")).click();
//
//        System.out.println("Ex warranty amount is updated successfully");
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[2]/dd[1]/span/span/input[1]")).clear();
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[2]/dd[1]/span/span/input[1]")).sendKeys("106450");
//        Thread.sleep(3000);
//        System.out.println("RTO Amount has been updated successfully");
        Thread.sleep(3000);

    }

    public void ReceiptTab() throws InterruptedException {
        Thread.sleep(4000);
        ReceiptField.click();
        Thread.sleep(4000);
        iframe3();
        Thread.sleep(5000);
        ShareReceiptButton.click();
        Thread.sleep(4000);
        System.out.println("successfully clicked on share reciept");
    }

    public void AmountReceiptPage() throws InterruptedException {
        Actions actions = new Actions(getDriver());
        AmountPage.click();
        Thread.sleep(4000);
        actions.doubleClick(OnAccountOf).perform();
        Thread.sleep(7000);
        OnAccountList.click();
        actions.doubleClick(PaymentAccount).perform();
        actions.doubleClick(DrawnBank).perform();
        Thread.sleep(3000);
        DrawnBankList.click();
        actions.doubleClick(TransactionDate).perform();
        Thread.sleep(3000);
        TransactionDateEnter.sendKeys("03/12/2024");
        actions.doubleClick(ChequeNo).perform();
        Thread.sleep(3000);
        ChequeNumberEnter.sendKeys("12345678000");
        saveButtonAccount.click();


    }

    public void AcocuntLoginUseraname() {

        AccountUsername.sendKeys("ACCOUNTS37");
    }

    public void AccountLoginPassword() {

        AccountPassword.sendKeys("Creta@2023");

    }

    public void SendConsentLink() {

        SendConsentField.click();
        sendConsentconfirm.click();
    }


    public void orderStock() throws InterruptedException {
        Thread.sleep(3000);

        OrderStockButton.click();
        Thread.sleep(3000);
    }

    public void DealerVechileStock() throws InterruptedException {
        Thread.sleep(3000);

        DealerVechileStock.click();
        Thread.sleep(3000);
    }

    public void vinNumber(String VinNo) {
        iframe2();

        vinNumber.sendKeys(VinNo);
        //MALB341CYRM313126
        //MALB351CLRM593451
    }

    public void vinSearch() throws InterruptedException {
        Thread.sleep(3000);
        vinSearch.click();
    }

    public void mgtListSales() throws InterruptedException {
        Thread.sleep(3000);
        mgtListSales.click();
    }

    public void verifyDataMGT() throws InterruptedException, IOException {

        String VariantValue = VariantValueTab.getText();
        String ExteriorColor = ExteriorColorTab.getText();
        String InteriorColor = InteriorColorTab.getText();
        getDriver().switchTo().defaultContent();

        VerifyData.click();
        SalesOperationLink();
        selectCustomerBookingMgtListMainLinks();
        iframe3();
        verifydata1.click();
        WebElement selectedOption = VerifyOptions.get(0);
        selectedOption.click();
        System.out.println("Selected Option: " + selectedOption.getText());

//
//        String CustExtColor = getDriver().findElement(By.xpath("//*[@aria-owns='extColorCd_listbox']/span")).getText();
//        String CustIntColor = getDriver().findElement(By.xpath("//span[@aria-owns='intColorCd_listbox']")).getText();
//        String CustVariant = getDriver().findElement(By.xpath("//span[@aria-owns='subVariantCd_listbox']")).getText();
//        Assert.assertEquals(VariantValue, CustVariant.contains(VariantValue));
//        Assert.assertEquals(ExteriorColor, CustExtColor);
//        Assert.assertEquals(InteriorColor, CustIntColor);
        String Enquirynumber = getDriver().findElement(By.xpath("//*[@id=\"enquiryNo\"]")).getText();
        // Path to your existing Excel file
        String excelFilePath = "C:/Users/Anjali/OneDrive/Downloads/BookingDetails.xlsx";
        // Load the Excel file
        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fis);
        // Get the first sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);
        // Find the next available row to write
        int nextRowNum = sheet.getPhysicalNumberOfRows();
        // Create a new row at the next available index
        Row row = sheet.createRow(nextRowNum);
        // Add data to each cell in the new row (You can change the data format as per your needs)
        row.createCell(0).setCellValue(Enquirynumber);
        // Save the Excel file after editing
        FileOutputStream fos = new FileOutputStream(new File(excelFilePath));
        workbook.write(fos);

        // Close the resources
        fos.close();
        fis.close();

        System.out.println("Data has been added to the Excel file successfully.");
        getDriver().close();
    }

    public void receiptLink() {
        iframe3();
        ReceiptTab.click();
    }


//    public void VerifyConscentLink() {
//
//        getDriver().findElement(By.xpath("//*[text()=\"Customer Booking Mgt List\" and @class='k-link']")).click();
//        String statusValue = getDriver().findElement(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td[24]")).getText();
//        Assert.assertEquals(statusValue, "Pending");
//    }


}
