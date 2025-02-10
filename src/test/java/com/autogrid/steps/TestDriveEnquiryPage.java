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
    private static final Logger logger = LoggerFactory.getLogger(DMSLoginPage.class);
    private final CommonActions commonActions;
    private final WebDriver driver;
    private final String featureName = "Test Drive Enquiry Locators "; // Updated for Test Drive Enquiry Web Page

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    public TestDriveEnquiryPage(WebDriver driver) throws Exception {
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
		this.driver = driver;
    }
    /**
     * Fetches the WebElement dynamically from Excel.
     *
     * @param elementName - The logical name of the element from Excel.
     * @return WebElement - The located web element.
     * @throws IOException If there is an issue with reading the Excel file.
     */
    private WebElement getElement(String elementName) throws IOException {
        try {
            Map<String, String> locator = ExcelReading.getLocator(featureName, elementName);
            String locatorType = locator.get("type");
            String locatorValue = locator.get("value");

            switch (locatorType.toLowerCase()) {
                case "xpath":
                    return driver.findElement(By.xpath(locatorValue));
                case "css":
                    return driver.findElement(By.cssSelector(locatorValue));
                case "id":
                    return driver.findElement(By.id(locatorValue));
                case "name":
                    return driver.findElement(By.name(locatorValue));
                case "class":
                    return driver.findElement(By.className(locatorValue));
                case "linktext":
                    return driver.findElement(By.linkText(locatorValue));
                default:
                    throw new IllegalArgumentException("Invalid locator type: " + locatorType);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error locating element '" + elementName + "': " + e.getMessage());
        }
    }

    public void iframe4() throws Throwable {
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(getElement("iframe4"));
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void iframe2() throws Throwable {
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(getElement("iframe2"));
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void iframe3() throws Throwable {
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(getElement("iframe3"));
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void iframeWindow() throws Throwable {
        getDriver().switchTo().frame(getElement("iframewindow"));
        System.out.println("Successfully interacted with the element inside the iframe.");
    }

    public void Sales() throws InterruptedException, Throwable {
        Thread.sleep(3000);
        getElement("SalesBtn").click();
        System.out.println("Sales button is clicked successfully");
    }

    public void CustomerEnquiry() throws Throwable {
    	getElement("CustomerEnquirybtn").click();
        System.out.println("Customer Enquiry is clicked successfully");
    }

    public void CustomerEnquiryLink() throws Throwable {
    	getElement("CustomerEnquiryLink").click();
        System.out.println("Customer Enquiry link is clicked successfully");
    }

    public void walkinEnquiry() throws Throwable {
        iframe2();
        getElement("walkInEnquiryBtn").click();
        System.out.println("User selects the walk in enquiry tab successfully");
    }

    public void mobileNumberData(String Mobile) throws Throwable {

    	getElement("mobilenumberBtn").sendKeys(Mobile);
        LocalDate today = LocalDate.now();
        LocalDate ninetyDaysBefore = today.minusDays(90);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String todayDateStr = today.format(formatter);
        String ninetyDaysBeforeDateStr = ninetyDaysBefore.format(formatter);
        System.out.println("User enters the mobile number successfully");
        getElement("visitDatebtn").clear();
        getElement("visitDatebtn").sendKeys(ninetyDaysBeforeDateStr);
        getElement("viistToDateBtn").clear();
        getElement("viistToDateBtn").sendKeys(todayDateStr);
        System.out.println("User enters the date successfully");
        getElement("searchBtn").click();
        System.out.println("User searched for the entry successfully");
    }

    public void selecttheEntry() throws Throwable {
        Actions actions = new Actions(getDriver());
        actions.doubleClick(getElement("selectTableRow")).perform();
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

    public void BasicInfo(String TDOfferValue, String TDVinValue, String CertificateOFDeposit) throws InterruptedException, Throwable {
        Thread.sleep(3000);
        iframe2();
        Thread.sleep(3000);
        iframeWindow();

        // Call selectDropdownIfVisible for each dropdown
        selectDropdownIfVisible(getDriver(), getElement("TDofferDropDown"), TDOfferValue, "//*[@id=\"testDriveTakenFlag1_listbox\"]/li[text()='" + TDOfferValue + "']");

        selectDropdownIfVisible(getDriver(), getElement("TDVinDropDown"), TDVinValue, "//*[@id=\"testDriveTakenFlag2_listbox\"]/li[text()='" + TDVinValue + "')]");

        selectDropdownIfVisible(getDriver(), getElement("CertificateOFDepositDropDwon"), CertificateOFDeposit, "//*[@id=\"certOfDeposit-list\"]/div[2]/ul/li[text()='" + CertificateOFDeposit + "']");

    }

    public void TestDriveAppointmentTab(String test_drive_datetime) throws InterruptedException, Throwable {
    	getElement("TestDriveappointmentbtn").click();
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
                assertNotNull("Save button element not found", getElement("saveTestDrivebtn"));
                getElement("saveTestDrivebtn").click();
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


        public void FollowUPTab () throws Throwable {
            iframe2();
            //LaunchDriver.getDriver().switchTo().frame(iframeBasicTab);
            getDriver().switchTo().frame(getDriver().findElement(By.xpath("//*[@class='k-content-frame']")));
            getElement("followUpTab").click();
            System.out.println("Successfully clicked on follow up tab");
        }

        public void FollowUpTabDetails (String NextFollowUptime, String TextFollow, String FollowUptype, String
        nextFollowUpType, String EnquiryTypeValue) throws Throwable{
            String inputDateTime = NextFollowUptime;
            System.out.println(inputDateTime);
            String nextFollowUptime = inputDateTime.replaceAll("[^0-9]", "");
            getElement("followUpTimeTestBtn").sendKeys(nextFollowUptime);
            getElement("FollowUpTestArea").sendKeys(TextFollow);
            selectDropdownIfVisible(getDriver(), getElement("FollowUpTypeDropDown"), FollowUptype, "//ul[@id=\"eqfuFlupType_listbox\"]/li[text()='" + FollowUptype + "']");
            selectDropdownIfVisible(getDriver(), getElement("nextFollowUpTypedropdown"), nextFollowUpType, "//*[@id=\"eqfuNtfuType_listbox\"]/li[text()='" + nextFollowUpType + "']");
            // selectDropdownIfVisible(getDriver(),EnquiryTypedropDown,EnquiryTypeValue,"");
            getElement("btnFollowUpSave").click();
            getDriver().findElement(By.xpath("//*[text()='Basic Info.']")).click();
        }


        public void closeOptntabs () throws Throwable {
        	getElement("closeBtn").click();
        	getElement("tabClosebtn").click();
            System.out.println("Successfully closed all the ope tabs");
        }

    }