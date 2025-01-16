package com.autogrid.steps;

import com.autogrid.utils.ExcelReading;
import io.cucumber.java.eo.Se;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import com.autogrid.utils.LaunchDriver;
import org.junit.Assert;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.autogrid.utils.LaunchDriver.getDriver;
import static org.testng.AssertJUnit.assertNotNull;

public class TestDriveEnquiryPage {

    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(DMSLoginPage.class);
    private final CommonActions commonActions;
    private Map<String, String> testData; // Stores data from Excel
    private List<Map<String, String>> allTestData; // List to store all data rows from Excel
    private int currentDataRowIndex = 0;

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    public TestDriveEnquiryPage(WebDriver driver) throws Exception {
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//a[text()='Sales']")
    private WebElement SalesBtn;
    @FindBy(xpath = "//*[@id='gnb']/li[3]/div/ul/li[2]/a")
    private WebElement CustomerEnquirybtn;
    @FindBy(xpath = "//*[@id=\"gnb\"]/li[3]/div/ul/li[2]/ul/li[1]/a")
    private WebElement CustomerEnquiryLink;
    @FindBy(xpath = "//a[text()='Walk-in Enquiry ']")
    private WebElement walkInEnquiryBtn;
    @FindBy(xpath = "//input[@id=\"mobileNo\"]")
    private WebElement mobilenumberBtn;
    @FindBy(xpath = "//*[@id=\"visitFromDate\"]")
    private WebElement visitDatebtn;
    @FindBy(xpath = "//*[@id=\"visitToDate\"]")
    private WebElement viistToDateBtn;
    @FindBy(xpath = "//button[@id=\"btnSearch\"]")
    private WebElement searchBtn;
    @FindBy(xpath = "//*[@id=\"resizableContainer\"]/section[2]/div/div/div/table/tbody/tr/td[2]")
    private WebElement selectTableRow;
    @FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[6]/dd[1]/span/span/span[1]")
    private WebElement TDofferDropDown;
    @FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[6]/dd[2]/span/span/span[1]")
    private WebElement TDVinDropDown;
    @FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[6]/dd[3]/span/span/span[1]")
    private WebElement CertificateOFDepositDropDwon;
    @FindBy(xpath = "//*[text()=\"Follow Up\"]")
    private WebElement followUpTab;
    @FindBy(xpath = "//*[@id=\"nextFollowUpDate\"]")
    private WebElement followUpTimeTestBtn;
    @FindBy(xpath = "//textarea[@id=\"eqfuRmrks\"]")
    private WebElement FollowUpTestArea;
    @FindBy(xpath = "//*[@id=\"followUpInfoForm\"]/div[2]/dl[1]/dd[2]/span/span/span[1]")
    private WebElement FollowUpTypeDropDown;
    @FindBy(xpath = "//*[@id=\"followUpInfoForm\"]/div[2]/dl[4]/dd[2]/span/span/span[1]")
    private WebElement nextFollowUpTypedropdown;
    @FindBy(xpath = "//*[@id=\"followUpInfoForm\"]/div[2]/dl[5]/dd[1]/span/span")
    private WebElement EnquiryTypedropDown;
    @FindBy(xpath = "//*[@id=\"btnFollowUpSave\"]")
    private WebElement btnFollowUpSave;
    @FindBy(xpath = "//*[@id=\"btnTD\"]")
    private WebElement TestDriveappointmentbtn;
    @FindBy(xpath = "//*[@id=\"schedulerIn\"]/div/ul/li[4]/a/span[1]")
    private WebElement calenderTestdrivebtn;
    @FindBy(xpath = "//button[@id=\"btnSave\"]")
    private WebElement saveTestDrivebtn;
    @FindBy(xpath = "//*[@id=\"btnBasicSave\"]")
    private WebElement basicSaveBtn;
    @FindBy(xpath = "//a[@aria-label='Close']")
    private WebElement closeBtn;
    @FindBy(xpath = "//button[@class=\"tab_close\"]")
    private WebElement tabClosebtn;
    @FindBy(xpath = "//iframe[@name='tabMenuFrame1']")
    private WebElement iframe1;
    @FindBy(xpath = "//iframe[@name='tabMenuFrame4']")
    private WebElement iframe4;
    @FindBy(xpath = "//*[@id=\"selectCustomerMeetingPopup\"]/iframe")
    private WebElement iframewindow;
    @FindBy(xpath = "//iframe[@name='tabMenuFrame3']")
    private WebElement iframe3;
    @FindBy(xpath = "//iframe[@name='tabMenuFrame2']")
    private WebElement iframe2;
    @FindBy(xpath = "//*[@class='k-window-content k-content k-window-iframecontent']")
    private WebElement iframeBasicTab;

    public void iframe4() {
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(iframe4);
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void iframe2() {
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(iframe2);
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void iframe3() {
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(iframe3);
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void iframeWindow() {
        getDriver().switchTo().frame(iframewindow);
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void Sales() throws InterruptedException {
        Thread.sleep(3000);
        SalesBtn.click();
        System.out.println("Sales button is clicked successfully");
    }

    public void CustomerEnquiry() {
        CustomerEnquirybtn.click();
        System.out.println("Customer Enquiry is clicked successfully");
    }

    public void CustomerEnquiryLink() {
        CustomerEnquiryLink.click();
        System.out.println("Customer Enquiry link is clicked successfully");
    }

    public void walkinEnquiry() {
        iframe2();
        walkInEnquiryBtn.click();
        System.out.println("User selects the walk in enquiry tab successfully");
    }

    public void mobileNumberData(String Mobile) {

        mobilenumberBtn.sendKeys(Mobile);
        LocalDate today = LocalDate.now();
        LocalDate ninetyDaysBefore = today.minusDays(90);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDateStr = today.format(formatter);
        String ninetyDaysBeforeDateStr = ninetyDaysBefore.format(formatter);
        System.out.println("User enters the mobile number successfully");
        visitDatebtn.clear();
        visitDatebtn.sendKeys(ninetyDaysBeforeDateStr);
        viistToDateBtn.clear();
        viistToDateBtn.sendKeys(todayDateStr);
        System.out.println("User enters the date successfully");
        searchBtn.click();
        System.out.println("User searched for the entry successfully");
    }

    public void selecttheEntry() {
        Actions actions = new Actions(getDriver());
        actions.doubleClick(selectTableRow).perform();
        System.out.println("Successfully selects the entry which is displayed in the table");
    }


    public void selectDropdownIfVisible(WebDriver driver, WebElement dropdownElement, String valueToSelect, String xpathForValue) {
        try {
            dropdownElement.click();
            // Introducing a wait to ensure the dropdown menu appears
            Thread.sleep(3000);

            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            String opacity = js.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('opacity');", dropdownElement).toString();

            if (Double.parseDouble(opacity) > 0) {
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathForValue)));
                    WebElement optionToSelect = getDriver().findElement(By.xpath(xpathForValue));
                    optionToSelect.click();
                    System.out.println(valueToSelect + " value is selected successfully.");
                } catch (NoSuchElementException e) {
                    System.out.println(valueToSelect + " option not found in the dropdown.");
                }
            } else {
                // If the dropdown is faded out, print a message but don't skip the rest
                System.out.println("Dropdown faded out, skipping this selection for now.");
            }
        } catch (Exception e) {
            System.out.println("Error encountered during dropdown selection: " + e.getMessage());
        }
    }

    public void BasicInfo(String TDOfferValue, String TDVinValue, String CertificateOFDeposit) throws InterruptedException {
        Thread.sleep(3000);
        iframe2();
        Thread.sleep(3000);
        iframeWindow();

        // Call selectDropdownIfVisible for each dropdown
        selectDropdownIfVisible(getDriver(), TDofferDropDown, TDOfferValue, "//*[@id=\"testDriveTakenFlag1_listbox\"]/li[text()='" + TDOfferValue + "']");

        selectDropdownIfVisible(getDriver(), TDVinDropDown, TDVinValue, "//*[@id=\"testDriveTakenFlag2_listbox\"]/li[text()='" + TDVinValue + "')]");

        selectDropdownIfVisible(getDriver(), CertificateOFDepositDropDwon, CertificateOFDeposit, "//*[@id=\"certOfDeposit-list\"]/div[2]/ul/li[text()='" + CertificateOFDeposit + "']");

    }

    public void TestDriveAppointmentTab(String test_drive_datetime) throws InterruptedException {
            TestDriveappointmentbtn.click();
            System.out.println("Test Drive appointment button clicked successfully");
            iframe3();
            // Format target date
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDate targetDate = LocalDate.parse(test_drive_datetime, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
            String formattedTargetDate = targetDate.format(outputFormatter);
            // Find and match the target date
            getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement currentDateElement = getDriver().findElement(By.xpath("//*[@class=\"k-lg-date-format\"]"));
            assertNotNull("Current date element not found", currentDateElement);
            String currentDateText = currentDateElement.getText();
            int maxAttempts = 50;
            int count = 0;
            while (!currentDateText.equals(formattedTargetDate)) {
                if (count >= maxAttempts) {
                    System.err.println("Max attempts reached. Target date not found.");
                    break;
                }
                WebElement nextArrow = getDriver().findElement(By.xpath("//a[@aria-label='Next' and @title='Next']"));
                assertNotNull("Next arrow element not found", nextArrow);
                nextArrow.click();
                count++;
                System.out.println("Clicked next arrow, current date is: " + currentDateText);
                currentDateText = getDriver().findElement(By.xpath("//*[@class=\"k-lg-date-format\"]")).getText();
            }

            if (!currentDateText.equals(formattedTargetDate)) {
                System.err.println("Failed to find the target date.");
            } else {
                System.out.println("Target date matched: " + formattedTargetDate);
            }
                // Calculate and locate the nearest time slot
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime inputDateTime = LocalDateTime.parse(test_drive_datetime, formatter);
                int hour = inputDateTime.getHour();
                int minute = inputDateTime.getMinute();

                if (minute >= 30) {
                    hour = (hour == 23) ? 0 : hour + 1;
                }
                int displayHour = hour % 12;
                displayHour = (displayHour == 0) ? 12 : displayHour;
                String amPm = (hour >= 12) ? "PM" : "AM";
                String roundedTime = String.format("%02d:00 %s", displayHour, amPm);

                String timeSlotXPath = "//div[@class='k-scheduler-times']//th[contains(text(), '" + roundedTime + "')]";
                String sideRowXPath = "//div[@class='k-scheduler-content']//tr[position() = (count(" +
                        timeSlotXPath + "/preceding-sibling::tr) + 1)]";

                WebElement timeSlot = getDriver().findElement(By.xpath(timeSlotXPath));
                assertNotNull("Time slot element not found", timeSlot);
                timeSlot.click();

                System.out.println("Time slot selected: " + roundedTime);

                Thread.sleep(3000);
                WebElement sideRow = getDriver().findElement(By.xpath(sideRowXPath));
                assertNotNull("Side row element not found", sideRow);

                Actions action = new Actions(getDriver());
                action.doubleClick(sideRow).perform();
                System.out.println("Side row for time slot " + roundedTime + " selected.");


                // Save the test drive
                assertNotNull("Save button element not found", saveTestDrivebtn);
                saveTestDrivebtn.click();
                System.out.println("Successfully clicked on save button");

                Thread.sleep(8000);
                iframe3();
        }



        public void iframetestappoint2 () {
            WebDriver driver = getDriver();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                // Wait and switch to the next iframe if needed
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@name='frame4']")));
                System.out.println("Switched to iframe 4.");
            } catch (TimeoutException e) {
                System.out.println("Unable to switch to iframe 4.");
            }
        }


        public void FollowUPTab () {
            iframe2();
            //LaunchDriver.getDriver().switchTo().frame(iframeBasicTab);
            getDriver().switchTo().frame(getDriver().findElement(By.xpath("//*[@class='k-content-frame']")));
            followUpTab.click();
            System.out.println("Successfully clicked on follow up tab");
        }

        public void FollowUpTabDetails (String NextFollowUptime, String TextFollow, String FollowUptype, String
        nextFollowUpType, String EnquiryTypeValue){
            String inputDateTime = NextFollowUptime;
            System.out.println(inputDateTime);
            String nextFollowUptime = inputDateTime.replaceAll("[^0-9]", "");
            followUpTimeTestBtn.sendKeys(nextFollowUptime);
            FollowUpTestArea.sendKeys(TextFollow);
            selectDropdownIfVisible(getDriver(), FollowUpTypeDropDown, FollowUptype, "//ul[@id=\"eqfuFlupType_listbox\"]/li[text()='" + FollowUptype + "']");
            selectDropdownIfVisible(getDriver(), nextFollowUpTypedropdown, nextFollowUpType, "//*[@id=\"eqfuNtfuType_listbox\"]/li[text()='" + nextFollowUpType + "']");
            // selectDropdownIfVisible(getDriver(),EnquiryTypedropDown,EnquiryTypeValue,"");
            btnFollowUpSave.click();
            getDriver().findElement(By.xpath("//*[text()='Basic Info.']")).click();
        }


        public void closeOptntabs () {
            closeBtn.click();
            tabClosebtn.click();
            System.out.println("Successfully closed all the ope tabs");
        }

    }