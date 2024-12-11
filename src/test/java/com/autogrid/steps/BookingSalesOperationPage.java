package com.autogrid.steps;

import com.autogrid.utils.LaunchDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
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
import java.time.Instant;
import java.util.List;
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
            Thread.sleep(15000*2);

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
            getDriver().findElement(By.xpath("//input[@class=\"form_input\"]")).sendKeys("7799222422");
        } catch (Exception e) {
            System.err.println("Error in passing the mobile number to the text field: " + e.getMessage());
            throw e;
        }
    }

    public void BasedOnDropdown() throws InterruptedException {
        try {

            BasedOnDropdown.click();
            List<WebElement> options = getDriver().findElements(By.xpath("//*[@id=\"baseStatCd_listbox\"]/li"));  // Replace with the actual XPath or locator
            WebElement BasedOnMobileDropdown = options.get(3);
            Thread.sleep(2000);
            BasedOnMobileDropdown.click();
            System.out.println("Selected Option: " + BasedOnMobileDropdown.getText());

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

    public void fillfieldsBookingPage() throws InterruptedException {
        try {
            //WebElement iframename = getDriver().findElement(By.xpath("//iframe[@id=\"tabMenuFrame3\"]"));
            LaunchDriver.getDriver().switchTo().defaultContent();
            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
            System.out.println("Successfully interacted with the element inside the iframe.");
            Thread.sleep(3000);
            getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).sendKeys("34512678");
            System.out.println("The GST number as been passed" +getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).getText());
            //EnquiryType:
            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"enType_listbox\"]/li[1]")).click();
            System.out.println("The Enquiry type as being selected");
            Thread.sleep(3000);
            //EnquiryCategory
            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[2]")).click();
            getDriver().findElement(By.xpath("//*[text()='Corporate Company']")).click();
            System.out.println("The Enquiry Category as being selected");
            Thread.sleep(3000);
            //SalesConsultant
            getDriver().findElement(By.xpath(" //*[@id=\"basicInfo\"]/div[3]/dl[2]/dd[3]")).click();
            List<WebElement> options = getDriver().findElements(By.xpath("//ul[@id=\"enSaleEmpNo_listbox\"]/li"));  // Replace with the actual XPath or locator
            WebElement selectedOption = options.get(15);
            selectedOption.click();
            System.out.println("Selected Option: " + selectedOption.getText());
            System.out.println("The Sales Consultant as being selected");
            Thread.sleep(3000);
            //Referred by :
            getDriver().findElement(By.xpath("//*[@id='eqryRfrlBy']")).sendKeys("CHENIGACHERLA SAMPATH RAJ");
            System.out.println("The Referred by as being selected");
            Thread.sleep(3000);
//            //RegisteredCorp
//            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[4]/dl[1]/dd[2]/div/a")).click();
//            Thread.sleep(2000);
//            WebElement iframenamePop = getDriver().findElement(By.xpath("//*[@id="corporateInfoSearchPopup"]/iframe"));
//            LaunchDriver.getDriver().switchTo().defaultContent();
//            LaunchDriver.getDriver().switchTo().frame(iframenamePop);
//
//            String mainWindowHandle = getDriver().getWindowHandle();
//            // Wait for the new window and get all window handles
//            Set<String> allWindowHandles = getDriver().getWindowHandles();
//            // Identify the new window handle
//            String popupWindowHandle = null;
//            for (String handle : allWindowHandles) {
//                if (!handle.equals(mainWindowHandle)) {
//                    popupWindowHandle = handle;
//                    break;
//                }
//            }
//
//            // Switch to the popup window
//            if (popupWindowHandle != null) {
//                getDriver().switchTo().window(popupWindowHandle);
//                // Perform actions in the popup window
//                getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl/dd[1]/span/span")).click();
//                getDriver().findElement(By.xpath("//li[text()=\"Classic\"]")).click();
//                getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[1]/div")).click();
//                //Select a coulumn from the search table
//                getDriver().findElement(By.xpath("//*[@id=\"gridBody\"]/div[2]/table/tbody/tr[1]/td[1]")).click();
//                getDriver().findElement(By.xpath("//*[@id=\"gridBody\"]/div[2]/table/tbody/tr[1]/td[1]")).click();
//
//                getDriver().close();
//            } else {
//                System.out.println("Popup window not found!");
//            }
//            getDriver().switchTo().window(mainWindowHandle);
//            System.out.println("Returned to main window.");
            //ModeOfPurchase
            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[2]/dd[1]/span/span/span[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"purchaseType_listbox\"]/li[4]")).click();
            System.out.println("The Purchase Type as being selected");
            //Title
            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[2]/div[2]/dl[2]/dd[1]/span/span")).click();
            List<WebElement> optionsTitle = getDriver().findElements(By.xpath("//ul[@id=\"enSaleEmpNo_listbox\"]/li"));  // Replace with the actual XPath or locator
            WebElement selectedOptionTitle = optionsTitle.get(1);
            selectedOptionTitle.click();
            System.out.println("Selected Option: " + selectedOptionTitle.getText());
            System.out.println("Title is selected");
            //Pan
            getDriver().findElement(By.xpath("//*[@id=\"pan\"]")).sendKeys("sdfghj345");
            System.out.println("The Pan as being selected" +getDriver().findElement(By.xpath("//*[@id=\"pan\"]")).getText());
            //BillingAddress
            getDriver().findElement(By.xpath("//a[@href='javascript:fn_pinCodeSearch();']")).click();
            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl[1]/dd[1]/span/span")).click();
            getDriver().findElement(By.xpath("//*[@id=\"sStcdName_listbox\"]/li[4]")).click();
            getDriver().findElement(By.xpath("//*[@id='window']/div[1]/div/button[2]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[2]/table/tbody/tr[1]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[2]/table/tbody/tr[1]")).click();
            System.out.println("Billing address is updated");
            //Shipping address
            getDriver().findElement(By.xpath("//*[@id=\"bkngShipToName\"]")).sendKeys("Srujan");
            getDriver().findElement(By.xpath("//*[@id=\"bkngShipToAddr\"]")).sendKeys("Assam");
            getDriver().findElement(By.xpath("//a[@href=\"javascript:fn_pinCodeSearch2('Ship');\"]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl[1]/dd[1]/span/span")).click();
            getDriver().findElement(By.xpath("//*[@id=\"sStcdName_listbox\"]/li[4]")).click();
            getDriver().findElement(By.xpath("//*[@id='window']/div[1]/div/button[2]")).click();
            getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[2]/table/tbody/tr[1]")).click();
            System.out.println("Shipping address is updated");
            //Register
            getDriver().findElement(By.xpath("//button[@id='btnBookingRegister']")).click();
            getDriver().findElement(By.xpath("/html/body/div[117]/div[2]/p[2]/button[1]")).click();

            String SuccesMessage = getDriver().findElement(By.xpath("//*[@id=\"template\"]/div/div/p")).getText();
            System.out.println(SuccesMessage);

        } catch (Exception e) {
            System.err.println("Error in passing the dates: " + e.getMessage());
            throw e;
        }
    }

    public void QuotationPage() {
        getDriver().findElement(By.xpath("//*[text()=\"Quotation\"]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"basicInsurance\"]")).sendKeys("150000");
        getDriver().findElement(By.xpath("//*[@id=\"exWarrantyAmount\"]")).sendKeys("1");
        getDriver().findElement(By.xpath("//*[@id=\"rtoCharges\"]")).sendKeys("106450");
        getDriver().findElement(By.xpath("//*[@id=\"btnFinanceModify\"]")).click();

    }

    public void ReceiptTab() {
        getDriver().findElement(By.xpath("//*[@id=\"receiptTab\"]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"btnPayAdd\"]")).click();
    }

    public void AmountReceiptPage() {
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
