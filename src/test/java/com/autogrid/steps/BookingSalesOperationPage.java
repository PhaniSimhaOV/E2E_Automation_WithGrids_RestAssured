package com.autogrid.steps;

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

import java.awt.*;
import java.time.Duration;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.autogrid.utils.LaunchDriver.getDriver;

public class BookingSalesOperationPage {

    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DMSLoginPage.class);
    private final CommonActions commonActions;

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

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    public BookingSalesOperationPage(WebDriver driver) throws Exception {
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);

    }

    public void SalesIconButton() throws InterruptedException {
        try {
            //Thread.sleep(15000*3);

            SalesIcon.click();
        } catch (Exception e) {
            System.err.println("Error in Clicking the Sales Icon : " + e.getMessage());
            throw e;
        }
    }

    public void SalesTable() {
        try {
            WebElement SalesTable = getDriver().findElement(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td[2]"));
            Actions actions = new Actions(getDriver());
            actions.doubleClick(SalesTable).perform();

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

    public void selectDateOFDropdown() throws InterruptedException {
        try {
            WebElement iframename = getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame2']"));
            LaunchDriver.getDriver().switchTo().defaultContent();
            LaunchDriver.getDriver().switchTo().frame(iframename);
            System.out.println("Successfully interacted with the element inside the iframe.");
            dropdownDateOf.click();
            List<WebElement> options = getDriver().findElements(By.xpath("//ul[@id='dSearchCd_listbox']/li"));  // Replace with the actual XPath or locator
            WebElement selectedOption = options.get(0);
            selectedOption.click();
            System.out.println("Selected Option: " + selectedOption.getText());


        } catch (Exception e) {
            System.err.println("Error in Selecting the enquiry from the dropdown: " + e.getMessage());
            throw e;

        }
    }


    public void MobileNumberTextBox() {
        try {
            getDriver().findElement(By.xpath("//input[@class=\"form_input\"]")).sendKeys("8099261232");
            //7799222422
            //8886376262
        } catch (Exception e) {
            System.err.println("Error in passing the mobile number to the text field: " + e.getMessage());
            throw e;
        }
    }

    public void BasedOnDropdown() throws InterruptedException {
        try {

            BasedOnDropdown.click();
            List<WebElement> options = getDriver().findElements(By.xpath("//ul[@id=\"baseStatCd_listbox\"]/li"));
            Thread.sleep(2000);
           WebElement BasedOnMobileDropdown=options.get(3);
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

    public void fillfieldsBookingPage() throws InterruptedException, AWTException {
        try {
            LaunchDriver.getDriver().switchTo().defaultContent();
            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
            System.out.println("Successfully interacted with the element inside the iframe.");
            Thread.sleep(3000);
//            getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).clear();
//            getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).sendKeys("34512678");
//            System.out.println("The GST number as been passed" +getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).getText());
//            //EnquiryType:
            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"enType_listbox\"]/li[1]")).click();
            System.out.println("The Enquiry type as being selected");
            Thread.sleep(3000);
            //EnquiryCategory

            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[2]")).click();
            List<WebElement> optionsCategory = getDriver().findElements(By.xpath("//ul[@id=\"enCategory_listbox\"]/li"));  // Replace with the actual XPath or locator
            WebElement Enquirycategory=optionsCategory.get(3);
            Thread.sleep(3000);
            Enquirycategory.click();
            System.out.println("The Enquiry Category as being selected");
            Thread.sleep(3000);
            //SalesConsultant
            getDriver().findElement(By.xpath(" //*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[3]")).click();
            List<WebElement> options = getDriver().findElements(By.xpath("//ul[@id=\"enSaleEmpNo_listbox\"]/li"));  // Replace with the actual XPath or locator
            WebElement selectedOption = options.get(15);
            Thread.sleep(3000);
            selectedOption.click();
            System.out.println("Selected Option: " + selectedOption.getText());
            System.out.println("The Sales Consultant as being selected");
            Thread.sleep(3000);
            //Referred by :
            getDriver().findElement(By.xpath("//*[@id='eqryRfrlBy']")).sendKeys("CHENIGACHERLA SAMPATH RAJ");
            System.out.println("The Referred by as being selected");
            Thread.sleep(3000);
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
            //ModeOfPurchase
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[2]/dd[1]/span/span/span[1]")).click();
//            getDriver().findElement(By.xpath("//*[@id=\"purchaseType_listbox\"]/li[4]")).click();
//            System.out.println("The Purchase Type as being selected");
//            Thread.sleep(3000);
            //Title
            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[2]/div[2]/dl[2]/dd[1]/span/span")).click();
            List<WebElement> optionsTitle = getDriver().findElements(By.xpath("//*[@id=\"titleType_listbox\"]"));
            String valueToSelect = "Mr.";
            boolean valueFound = false;
            for (WebElement item : optionsTitle) {
                if (item.getText().equals(valueToSelect)) {
                    item.click();
                    valueFound = true;
                    System.out.println("Selected value: " + valueToSelect);
                    break;
                }
            }
            System.out.println("Title is selected");
            //Pan
            getDriver().findElement(By.xpath("//*[@id=\"pan\"]")).sendKeys("sdfghj345");
            System.out.println("The Pan as being selected" +getDriver().findElement(By.xpath("//*[@id=\"pan\"]")).getText());
            //BillingAddress
//            getDriver().findElement(By.xpath("//a[@href='javascript:fn_pinCodeSearch();']")).click();
//            WebElement iframenameBilling = getDriver().findElement(By.xpath("//iframe[@class='k-content-frame']"));
//            LaunchDriver.getDriver().switchTo().defaultContent();
//            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
//            LaunchDriver.getDriver().switchTo().frame(iframenameBilling);
//            System.out.println("Successfully interacted with the element inside the iframe. for billing address");
//            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl[1]/dd[1]/span/span")).click();
//            Thread.sleep(2000);
//            List<WebElement> ShippingAddress= getDriver().findElements(By.xpath("//ul[@id=\"sStcdName_listbox\"]/li"));
//            ShippingAddress.get(1).click();
//            getDriver().findElement(By.xpath("//*[@id='window']/div[1]/div/button[2]")).click();
//            WebElement SalesTable = getDriver().findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]"));
//            Actions actions = new Actions(getDriver());
//            actions.doubleClick(SalesTable).perform();
//            Thread.sleep(2000);
//            System.out.println("Billing address is updated");
            LaunchDriver.getDriver().switchTo().defaultContent();
            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("window.scrollBy(0,1000)");
            System.out.println("Scrolled down successfully.");
            //Registered Loan amount
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).clear();
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).sendKeys("15000");
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).sendKeys(Keys.ENTER);
            Robot robot = new Robot();
            Random random = new Random();
            int x = random.nextInt(1920);
            int y = random.nextInt(1080);
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // Left-click press
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            System.out.println("Registered loan amount as been entered successfully");

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
public void clickbutton(String button){
        //Register
    if(button.equalsIgnoreCase("Register")) {
        getDriver().findElement(By.xpath("//button[@id='btnBookingRegister']")).click();
        LaunchDriver.getDriver().switchTo().defaultContent();
        LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
        getDriver().findElement(By.xpath("/html/body/div[116]/div[2]/p[2]/button[1]")).click();
        System.out.println("Successfully clicked on register");
    }
    //modify
    else{
    getDriver().findElement(By.xpath("//*[@id=\"btnBookingModify\"]")).click();
    LaunchDriver.getDriver().switchTo().defaultContent();
    LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));

    getDriver().findElement(By.xpath("/html/body/div[116]/div[2]/p[2]/button[1]")).click();
    System.out.println("Modified successfully");
//            String SuccesMessage = getDriver().findElement(By.xpath("//*[@id=\"template\"]/div/div/p")).getText();
//            System.out.println(SuccesMessage);
}}
    public void QuotationPage() throws InterruptedException {
      Thread.sleep(3000);
      getDriver().findElement(By.xpath("//*[@id=\"orpTab\"]")).click();
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[1]/dd[3]/span/span/input[1]")).clear();
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[1]/dd[3]/span/span/input[1]")).sendKeys("150000");
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[1]/dd[3]/span/span/input[1]")).sendKeys(Keys.ENTER);
//        System.out.println("Basic insurance is updated successfully");
    //    getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[3]/dd[3]/span/span/input[1]")).click();
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[3]/dd[3]/span/span/input[1]")).clear();
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[3]/dd[3]/span/span/input[1]")).sendKeys("1");
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[3]/dd[3]/span/span/input[1]")).click();

        System.out.println("Ex warranty amount is updated successfully");
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[2]/dd[1]/span/span")).clear();
//        getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[2]/dd[1]/span/span")).sendKeys("106450");
//        System.out.println("RTO Amount has been updated successfully");
        getDriver().findElement(By.xpath("//*[@id=\"btnFinanceModify\"]")).click();
        Thread.sleep(4000);

    }

    public void ReceiptTab() throws InterruptedException {
//        LaunchDriver.getDriver().switchTo().defaultContent();
//        LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
getDriver().findElement(By.xpath("//*[@id=\"receiptTab\"]")).click();
        Thread.sleep(4000);
       LaunchDriver.getDriver().switchTo().defaultContent();
       Thread.sleep(4000);
        LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
       getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[2]/table/tbody/tr[1]/td[8]/button")).click();
        System.out.println("successfully clicked on share reciept");
    }

    public void AmountReceiptPage() throws InterruptedException {
        getDriver().findElement(By.xpath(" //*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[2]")).click();
        Thread.sleep(4000);
       // getDriver().findElement(By.xpath("//*[@id=\"paymentGrid_active_cell\"]/span/span/span[2]/span[1]/span")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[3]")).click();
        Thread.sleep(4000);
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[3]")).click();
        Thread.sleep(4000);
        getDriver().findElement(By.xpath("/html/body/div[115]/div/div[2]/ul/li[3]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[4]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[4]")).click();
        Thread.sleep(3000);
        getDriver().findElement(By.xpath("/html/body/div[115]/div/div[2]/ul/li[4]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[5]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[5]")).click();
        Thread.sleep(3000);
        getDriver().findElement(By.xpath("/html/body/div[115]/div/div[2]/ul/li[3]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[6]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"paymentGrid\"]/div[3]/table/tbody/tr/td[6]")).click();
        Thread.sleep(3000);
        getDriver().findElement(By.xpath("//input[@data-format='dd/MM/yyyy']")).sendKeys("03/12/2024");
        getDriver().findElement(By.xpath("//*[@id='paymentGrid']/div[3]/table/tbody/tr/td[7]")).click();
        getDriver().findElement(By.xpath("//*[@id='paymentGrid']/div[3]/table/tbody/tr/td[7]")).click();
        Thread.sleep(3000);
        getDriver().findElement(By.xpath("//input[@name='chqNo']")).sendKeys("12345678000");
        getDriver().findElement(By.xpath("//*[@id=\"btnPaySave\"]")).click();


    }

}
