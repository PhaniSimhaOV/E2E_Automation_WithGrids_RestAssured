package com.autogrid.steps;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import com.autogrid.utils.LaunchDriver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.ExcelReading;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import static com.autogrid.utils.LaunchDriver.getDriver;

public class BookingSalesOperationPage {

	private static final Logger logger = LoggerFactory.getLogger(DMSLoginPage.class);
	private final CommonActions commonActions;
	private Map<String, String> testData; // Stores data from Excel
	private List<Map<String, String>> allTestData; // List to store all data rows from Excel
	private int currentDataRowIndex = 0;
	private final WebDriver driver;
	private final String featureName = "Booking Sale Operation Locators"; // Updated for ExWarranty Page

	WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

	public BookingSalesOperationPage(WebDriver driver) throws Exception {
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

	public void SalesIconButton() throws Throwable {
		try {
			Thread.sleep(3000 * 15);
			getElement("SalesIcon").click();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.err.println("Error in Clicking the Sales Icon : " + e.getMessage());
			throw e;
		}
	}

	public void SalesTable() throws IOException {
		try {
			Actions actions = new Actions(getDriver());
			actions.doubleClick(getElement("SalesTable")).perform();
//            WebElement contactField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td[10]")));
//            String contactNumber = contactField.getAttribute("value");
//            // Fetch enquiry number
//            WebElement enquiryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td[2]")));
//            String enquiryNumber = enquiryField.getAttribute("value");
//
//            System.out.println("Contact Number: " + contactNumber);
//            System.out.println("Enquiry Number: " + enquiryNumber);
//
//            // Path to your existing Excel file
//            String excelFilePath = "C:/Users/Anjali/OneDrive/Desktop/BookingDetails.xlsx";
//            FileInputStream fis = new FileInputStream(new File(excelFilePath));
//            Workbook workbook = new XSSFWorkbook(fis);
//
//            // Get the first sheet
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // Find the next available row
//            int nextRowNum = sheet.getPhysicalNumberOfRows();
//            Row row = sheet.createRow(nextRowNum);
//
//            // Write data to Excel
//            row.createCell(0).setCellValue(enquiryNumber);
//            row.createCell(1).setCellValue(contactNumber);
//
//            // Save changes to the Excel file
//            FileOutputStream fos = new FileOutputStream(new File(excelFilePath));
//            workbook.write(fos);
//
//            // Close resources
//            fos.close();
//            workbook.close();
//            fis.close();
//
//            System.out.println("Data has been added to the Excel file successfully.");

		} catch (Exception e) {
			System.err.println("Error in Clicking the row of data: " + e.getMessage());
			throw e;
		}

	}

	public void SalesOperationLink() throws Throwable {
		try {
			Thread.sleep(4000);
			getElement("SalesOperationButton").click();
		} catch (Exception e) {
			System.err.println("Error in Clicking the SalesOperation Button  : " + e.getMessage());
			throw e;
		}
	}

	public void selectCustomerBookingMgtListMainLinks() throws Throwable {
		try {
			getElement("selectCustomerBookingMgtListMainLinks").click();
		} catch (Exception e) {
			System.err.println("Error in Clicking the select Customer BookingMgt ListMain Links: " + e.getMessage());
			throw e;
		}
	}

	public void iframe2() throws Throwable {
		LaunchDriver.getDriver().switchTo().defaultContent();
		LaunchDriver.getDriver().switchTo().frame(getElement("iframename2"));
		System.out.println("Successfully interacted with the element inside the iframe.");
	}

	public void iframe3() throws IOException {
		LaunchDriver.getDriver().switchTo().defaultContent();
		LaunchDriver.getDriver().switchTo().frame(getElement("iframename3"));
		System.out.println("Successfully interacted with the element inside the iframe.");
	}

	public void selectDateOFDropdown() throws Throwable {
		try {
			iframe2(); // Switch to iframe if necessary

			// Click on the dropdown element
			getElement("dropdownDateOf").click();

			// Fetch only the locator value (XPath)
			String optionsXPath = ExcelReading.getLocatorValue("Booking Sale Operation Locators", "optionsDateOF");

			// Ensure locator is valid before proceeding
			if (optionsXPath != null && !optionsXPath.isEmpty()) {
				List<WebElement> optionsDateOF = driver.findElements(By.xpath(optionsXPath));

				// Select the first option from the dropdown list
				if (!optionsDateOF.isEmpty()) {
					WebElement selectedOption = optionsDateOF.get(0);
					selectedOption.click();
					System.out.println("Selected Option: " + selectedOption.getText());
				} else {
					System.err.println("No options found in the dropdown.");
				}
			} else {
				System.err.println("Locator for 'optionsDateOF' not found in Excel.");
			}

		} catch (Exception e) {
			System.err.println("Error in selecting the enquiry from the dropdown: " + e.getMessage());
			throw e;
		}
	}

	public void MobileNumberTextBox(String mobileNumber) throws Throwable {
		try {
			getElement("MobileNumberField").clear();
			getElement("MobileNumberField").sendKeys(mobileNumber);
		} catch (Exception e) {
			System.err.println("Error entering Mobile in Filter: " + e.getMessage());
			throw e;
		}
	}

	public void BasedOnDropdown() throws Throwable {
		try {
			// Click on the dropdown element dynamically fetched from Excel
			getElement("BasedOnDropdown").click();
			Thread.sleep(2000);

			// Fetch only the locator value (XPath) for the dropdown options
			String optionsXPath = ExcelReading.getLocatorValue("Booking Sale Operation Locators",
					"BasedOnDropdownoptions");

			// Ensure locator is valid before proceeding
			if (optionsXPath != null && !optionsXPath.isEmpty()) {
				// Retrieve list of all dropdown options dynamically
				List<WebElement> BasedOnDropdownoptions = driver.findElements(By.xpath(optionsXPath));

				// Select the fourth option (index 3) from the dropdown list
				if (!BasedOnDropdownoptions.isEmpty() && BasedOnDropdownoptions.size() > 3) {
					WebElement BasedOnMobileDropdown = BasedOnDropdownoptions.get(3);
					BasedOnMobileDropdown.click();
					System.out.println("Selected value: " + BasedOnMobileDropdown.getText());
				} else {
					System.err.println("Dropdown options not found or index out of bounds.");
				}
			} else {
				System.err.println("Locator for 'BasedOnDropdownoptions' not found in Excel.");
			}

		} catch (Exception e) {
			System.err.println("Error in selecting the BasedOnDropdown: " + e.getMessage());
			throw e;
		}
	}

	public void SearchButton() throws Throwable {
		try {
			getElement("SearchButton").click();
		} catch (Exception e) {
			System.err.println("Error in clicking the search button : " + e.getMessage());
			throw e;
		}
	}

	public void SelectDates() throws Throwable {
		try {
			getElement("StartDate").clear();
			getElement("EndDate").clear();
			Thread.sleep(3000);
			getElement("StartDate").sendKeys("01101990");
			getElement("EndDate").sendKeys("30122024");
		} catch (Exception e) {
			System.err.println("Error in passing the dates: " + e.getMessage());
			throw e;
		}
	}

	public void fillfieldsBookingPage(String pan, String RegistrationName, String address, String StateValue,
			String pincode) throws InterruptedException, AWTException, IOException {
		try {
			iframe3();
			Thread.sleep(3000);

			WebDriver driver = getDriver(); // Initialize WebDriver instance

			// Fetch Enquiry No
			WebElement enquiryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("enquiryNo")));
			String enquiryNumber = enquiryField.getAttribute("value");

			// Fetch Contact No
			WebElement contactField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contactNo")));
			String contactNumber = contactField.getAttribute("value");

			System.out.println("Enquiry Number: " + enquiryNumber);
			String filePath = "C:/Users/Anjali/OneDrive/Desktop/output.xlsx";
			String sheetName = "BookingDetails";

			File excelFile = new File(filePath);
			Workbook workbook;
			Sheet sheet;

			if (excelFile.exists()) {
				FileInputStream fis = new FileInputStream(excelFile);
				workbook = new XSSFWorkbook(fis);
				fis.close();
			} else {
				workbook = new XSSFWorkbook();
				System.out.println("Excel file does not exist. Creating a new file...");
			}

			// Get the sheet or create one if it doesn't exist
			sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				sheet = workbook.createSheet(sheetName);
			}

			// Find the next empty row
			int nextRowNum = sheet.getPhysicalNumberOfRows();
			Row row = sheet.createRow(nextRowNum);

			// Write data to the row
			row.createCell(0).setCellValue(enquiryNumber);
			row.createCell(1).setCellValue(contactNumber);

			// Save the changes to the Excel file
			FileOutputStream fos = new FileOutputStream(excelFile);
			workbook.write(fos);

			// Close resources
			fos.close();
			workbook.close();

			System.out.println("Data has been successfully added to the Excel file.");

			// Pan
			getElement("panfield").clear();
			getElement("panfield").sendKeys(pan);
			System.out.println("The Pan as being selected" + getElement("panfield").getText());
			iframe3();
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("window.scrollBy(0,1000)");
			System.out.println("Scrolled down successfully.");
			// RegisteredName
			iframe3();
			getElement("RegistrationNm").clear();
			getElement("RegistrationNm").sendKeys(RegistrationName);
			iframe3();
			getElement("ShipToName").clear();
			getElement("ShipToName").sendKeys(RegistrationName);
			System.out.println("RegistrationNm is." + getElement("RegistrationNm").getText());
			iframe3();
			getElement("AddressTab").clear();
			getElement("SalesIcon").sendKeys(address);
			iframe3();
			getElement("btnBookingModify").click();
			iframe3();
			getElement("confirmmodifybutton").click();
			System.out.println("Modified successfully");
			getElement("ShipToAddr").clear();
			getElement("ShipToAddr").sendKeys(address);

			// ModeOfPurchase
			getDriver()
					.findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[2]/dd[1]/span/span/span[1]"))
					.click();
			getDriver().findElement(By.xpath("//*[@id=\"purchaseType_listbox\"]/li[4]")).click();
			System.out.println("The Purchase Type as being selected");
			Thread.sleep(3000);
			// BillingAddress
			getElement("pinCodeSearchBilling").click();
			WebElement iframenameBilling = getDriver().findElement(By.xpath("//iframe[@class='k-content-frame']"));
			LaunchDriver.getDriver().switchTo().defaultContent();
			LaunchDriver.getDriver().switchTo()
					.frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
			LaunchDriver.getDriver().switchTo().frame(iframenameBilling);
			System.out.println("Successfully interacted with the element inside the iframe. for billing address");
			getElement("statedropdown").click();
			Thread.sleep(2000);
			List<WebElement> ShippingAddress = getDriver().findElements(By.xpath("//ul[@id=\"sStcdName_listbox\"]/li"));
			for (WebElement option : ShippingAddress) {
				if (option.getText().equals(StateValue)) {
					option.click();
					break;
				}
			}
			getElement("pinCodeBilling").clear();
			getElement("pinCodeBilling").sendKeys(pincode);
			getElement("serachBilling").click();
			WebElement SalesTable = getDriver().findElement(By.xpath("//*[@id='grid']/div[2]/table/tbody/tr[1]"));
			Actions actions = new Actions(getDriver());
			actions.doubleClick(SalesTable).perform();
			Thread.sleep(2000);
			System.out.println("Billing address is updated");
			getElement("bookingInfoFinancer").click();
			List<WebElement> financierCdList = driver.findElements(
					By.xpath(ExcelReading.getLocatorValue("Booking Sale Operation Locators", "financierCdList")));

			// Click the fourth option (index 3) from the dropdown list
			financierCdList.get(3).click();

			getElement("btnBookingRegister").click();
			iframe3();
			getElement("confirmRegisterButton").click();
			System.out.println("Successfully clicked on register");
			Thread.sleep(5000);
			getElement("btnBookingModify").click();
			iframe3();
			getElement("confirmmodifybutton").click();
			System.out.println("Modified successfully");
			iframe3();

//            //EnquiryCategory
////            EnquiryCategoryField.click();
////            WebElement Enquirycategory = optionsEnquiryCategory.get(3);
//            Thread.sleep(3000);
////            Enquirycategory.click();
//            //System.out.println("The Enquiry Category as being selected");
			// SalesConsultant
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
			// getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).clear();
//            getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).sendKeys("34512678");
//            System.out.println("The GST number as been passed" +getDriver().findElement(By.xpath("//*[@id=\"custGstNo\"]")).getText());
			// RegisteredCorp
//            getDriver().findElement(By.xpath("//*[@id=\"basicInfo\"]/div[4]/dl[1]/dd[2]/div/a")).click();
//            Thread.sleep(2000);
//            LaunchDriver.getDriver().switchTo().defaultContent();
//            LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']"))); getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[2]/dl/dd[1]/span/span")).click();
//            getDriver().findElement(By.xpath("//li[text()=\"Classic\"]")).click();
//            getDriver().findElement(By.xpath("//*[@id=\"window\"]/div[1]/div")).click();
			// Select a coulumn from the search table
//                getDriver().findElement(By.xpath("//*[@id=\"gridBody\"]/div[2]/table/tbody/tr[1]/td[1]")).click();
//                getDriver().findElement(By.xpath("//*[@id=\"gridBody\"]/div[2]/table/tbody/tr[1]/td[1]")).click();
//            System.out.println("Registered corp is slected successfully");

			// Registered Loan amount
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).clear();
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).sendKeys("15000");
//            getDriver().findElement(By.xpath("//*[@id=\"bookingInfo\"]/section[1]/div[2]/dl[8]/dd[1]/span/span/input[1]")).sendKeys(Keys.ENTER);

			// Shipping address
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

			// Fetch contact number
		} catch (Exception e) {
			System.err.println("Error while processing: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void clickbutton(String button) throws Throwable {
		// Register
		if (button.equalsIgnoreCase("Register")) {
			getElement("RegisterButton").click();
			iframe3();
			getElement("confirmRegisterButton").click();
			System.out.println("Successfully clicked on register");
		}
		// modify
		else {
			getElement("modifybutton").click();
			iframe3();
			getElement("confirmmodifybutton").click();
			System.out.println("Modified successfully");

		}
	}

	public void QuotationPage(String RTOamount, String ExShowrroomamount) throws InterruptedException, Throwable {
		Thread.sleep(6000);
		iframe3();
		getElement("QuotationButton").click();

		try {
			Actions actions = new Actions(getDriver());
			Thread.sleep(3000);
			actions.moveToElement(getElement("RTOAmountField")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();

			actions.click(getElement("RTOAmountField")).sendKeys(RTOamount).build().perform();
			System.out.println("Successfully entered text: " + RTOamount);
			actions.moveToElement(getElement("ExShowrroom")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();

			actions.click(getElement("ExShowrroom")).sendKeys(ExShowrroomamount).build().perform();
			System.out.println("Successfully entered text: " + ExShowrroomamount);

		} catch (Exception e) {
			System.err.println("Error while entering text using Actions: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions.", e);
		}

		getElement("QuotationButtonModify").click();
		Thread.sleep(4000);
		// getDriver().findElement(By.xpath("//*[@id=\"orpInfo\"]/section/div[2]/dl[2]/dd[1]/span/span/input[1]")).clear();
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

	public void ReceiptTab() throws InterruptedException, Throwable {
		Thread.sleep(4000);
		getElement("ReceiptField").click();
		Thread.sleep(4000);
		iframe3();
		Thread.sleep(5000);
		getDriver().findElement(By.xpath("//*[@id=\"btnPayAdd\"]")).click();
		Thread.sleep(2000);
		getElement("ShareReceiptButton").click();
		Thread.sleep(4000);
		System.out.println("successfully clicked on share reciept");
	}

	public void AmountReceiptPage(String bookingDate) throws InterruptedException, Throwable {
		Actions actions = new Actions(getDriver());
		getElement("AmountPage").click();
		Thread.sleep(4000);
		getElement("AmountTransactionDate").click();
		getElement("AmountTransactionDate").sendKeys(bookingDate);
		// AmountTransaction.click();
//        AmountTransaction.sendKeys();

//        actions.doubleClick(OnAccountOf).perform();
//        Thread.sleep(7000);
//        OnAccountList.click();
		// actions.doubleClick(PaymentAccount).perform();
//        actions.doubleClick(DrawnBank).perform();
//        Thread.sleep(3000);
//        DrawnBankList.click();
		// actions.doubleClick(TransactionDate).perform();
		Thread.sleep(3000);
//        TransactionDateEnter.sendKeys(bookingDate);
//        actions.doubleClick(ChequeNo).perform();
//        Thread.sleep(3000);
		// ChequeNumberEnter.sendKeys("12345678000");
		getElement("saveButtonAccount").click();
	}

	public void AcocuntLoginUseraname() throws Throwable {
		getElement("AccountUsername").sendKeys("ACCOUNTS37");
	}

	public void AccountLoginPassword() throws Throwable {
		getElement("AccountPassword").sendKeys("Creta@2023");
	}

	public void SendConsentLink() throws InterruptedException, Throwable {
		getElement("SendConsentField").click();
		Thread.sleep(4000);
		getElement("sendConsentconfirm").click();
		Thread.sleep(6000);
	}

	public void orderStock() throws InterruptedException, IOException {
		Thread.sleep(3000);
		getElement("OrderStockButton").click();
		Thread.sleep(3000);
	}

	public void DealerVechileStock() throws InterruptedException, Throwable {
		Thread.sleep(3000);
		getElement("DealerVechileStock").click();
		Thread.sleep(3000);
	}

	public void vinNumber(String VinNo) throws Throwable {
		iframe2();
		getElement("vinNumber").sendKeys(VinNo);
		// MALB341CYRM313126
		// MALB351CLRM593451
	}

	public void vinSearch() throws InterruptedException, Throwable {
		Thread.sleep(3000);
		getElement("vinSearch").click();
	}

	public void mgtListSales() throws InterruptedException, Throwable {
		Thread.sleep(3000);
		getElement("mgtListSales").click();
	}

	public void mgtListSalesvin() throws InterruptedException {
		Thread.sleep(2000);
		getDriver().findElement(By.xpath("//*[@id=\"gnb\"]/li[3]/div/ul/li[3]/ul/li[1]/a")).click();
	}
//    public void verifyDataMGT() throws InterruptedException, IOException {
//
//        String VariantValue = getElement("VariantValueTab").getText();
//        String ExteriorColor = getElement("ExteriorColorTab").getText();
//        String InteriorColor = getElement("InteriorColorTab").getText();
//        getDriver().switchTo().defaultContent();
//
//        getElement("VerifyData").click();
//        SalesOperationLink();
//        selectCustomerBookingMgtListMainLinks();
//        iframe3();
//        getElement("verifydata1").click();
//        WebElement selectedOption = VerifyOptions.get(0);
//        selectedOption.click();
//        System.out.println("Selected Option: " + selectedOption.getText());
//        String CustExtColor = getDriver().findElement(By.xpath("//*[@aria-owns='extColorCd_listbox']/span")).getText();
//        String CustIntColor = getDriver().findElement(By.xpath("//span[@aria-owns='intColorCd_listbox']")).getText();
//        String CustVariant = getDriver().findElement(By.xpath("//span[@aria-owns='subVariantCd_listbox']")).getText();
//        Assert.assertEquals(VariantValue, CustVariant.contains(VariantValue));
//        Assert.assertEquals(ExteriorColor, CustExtColor);
//        Assert.assertEquals(InteriorColor, CustIntColor);
//
//    }

	public void verifyDataMGT() throws Throwable {
		// Retrieve text dynamically using getElement(), ensuring locators come from
		// Excel
		String VariantValue = getElement("VariantValueTab").getText();
		String ExteriorColor = getElement("ExteriorColorTab").getText();
		String InteriorColor = getElement("InteriorColorTab").getText();

		// Switch back to the main content
		getDriver().switchTo().defaultContent();

		// Perform navigation and interactions dynamically
		getElement("VerifyData").click();
		SalesOperationLink();
		selectCustomerBookingMgtListMainLinks();
		iframe3();
		getElement("verifydata1").click();

		// Fetch the locator for VerifyOptions dynamically from Excel
		String verifyOptionsXPath = ExcelReading.getLocatorValue("Booking Sale Operation Locators", "VerifyOptions");

		// Ensure locator is valid before proceeding
		if (verifyOptionsXPath != null && !verifyOptionsXPath.isEmpty()) {
			List<WebElement> VerifyOptions = driver.findElements(By.xpath(verifyOptionsXPath));

			// Select the first option
			if (!VerifyOptions.isEmpty()) {
				WebElement selectedOption = VerifyOptions.get(0);
				selectedOption.click();
				System.out.println("Selected Option: " + selectedOption.getText());
			} else {
				System.err.println("No options found in VerifyOptions dropdown.");
			}
		} else {
			System.err.println("Locator for 'VerifyOptions' not found in Excel.");
		}

		String CustExtColor = getDriver().findElement(By.xpath("//*[@aria-owns='extColorCd_listbox']/span")).getText();
		String CustIntColor = getDriver().findElement(By.xpath("//span[@aria-owns='intColorCd_listbox']")).getText();
		String CustVariant = getDriver().findElement(By.xpath("//span[@aria-owns='subVariantCd_listbox']")).getText();

		// Perform assertions
		Assert.assertEquals(VariantValue, CustVariant.contains(VariantValue));
		Assert.assertEquals(ExteriorColor, CustExtColor);
		Assert.assertEquals(InteriorColor, CustIntColor);
	}

	public void recordDataExcel() throws IOException {
		iframe3();
		String ContactNumber = getDriver().findElement(By.xpath("//*[@id=\"contactNo\"]")).getText();
		String Enquirynumber = getDriver().findElement(By.xpath("//*[@id=\"enquiryNo\"]")).getText();
		// Path to your existing Excel file
		String excelFilePath = "C:/Users/Anjali/OneDrive/Desktop/BookingDetails.xlsx";
		// Load the Excel file
		FileInputStream fis = new FileInputStream(new File(excelFilePath));
		Workbook workbook = new XSSFWorkbook(fis);
		// Get the first sheet from the workbook
		Sheet sheet = workbook.getSheetAt(0);
		// Find the next available row to write
		int nextRowNum = sheet.getPhysicalNumberOfRows();
		// Create a new row at the next available index
		Row row = sheet.createRow(nextRowNum);
		// Add data to each cell in the new row (You can change the data format as per
		// your needs)
		row.createCell(0).setCellValue(Enquirynumber);
		row.createCell(1).setCellValue(ContactNumber);
		// Save the Excel file after editing
		FileOutputStream fos = new FileOutputStream(new File(excelFilePath));
		workbook.write(fos);

		// Close the resources
		fos.close();
		fis.close();

		System.out.println("Data has been added to the Excel file successfully.");
	}

	public void receiptLink() throws Throwable {
		iframe3();
		getElement("ReceiptTab").click();
	}

//    public void VerifyConscentLink() {
//
//        getDriver().findElement(By.xpath("//*[text()=\"Customer Booking Mgt List\" and @class='k-link']")).click();
//        String statusValue = getDriver().findElement(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td[24]")).getText();
//        Assert.assertEquals(statusValue, "Pending");
//    }

}
