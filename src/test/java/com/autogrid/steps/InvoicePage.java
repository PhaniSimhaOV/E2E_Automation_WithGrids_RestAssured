package com.autogrid.steps;

import com.autogrid.utils.CommonActions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import com.autogrid.utils.ExcelReading;

public class InvoicePage {
	private static final Logger logger = LoggerFactory.getLogger(InvoicePage.class);
	private final CommonActions commonActions;
	private final WebDriver driver;
    private final String featureName = "Invoice Screen Locators"; // Updated for Invoice Page

	public InvoicePage(WebDriver driver) {
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
	private void waitForVisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	private void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void interactWithIframeElement1() throws Throwable{
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(getElement("iframe1"));
			System.out.println("Successfully interacted with the iframe1.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe1: " + e.getMessage());
		}
	}

	public void interactWithIframeElement2() {
		try {
			driver.switchTo().frame(getElement("iframe2"));
			System.out.println("Successfully interacted with the iframe2.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe2: " + e.getMessage());
		}
	}

	public void interactWithIframeElement3() {
		try {
			driver.switchTo().frame(getElement("iframe3"));
			System.out.println("Successfully interacted with the iframe3.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe3: " + e.getMessage());
		}
	}

	public void clickSalesMenu() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("SalesMenu"));
			getElement("SalesMenu").click();
		} catch (Exception e) {
			System.err.println("Error clicking Sales Menu: " + e.getMessage());
			throw e;
		}
	} 

	public void clickSalesOperationSubmenu() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("SalesOperationSubmenu"));
			getElement("SalesOperationSubmenu").click();
		} catch (Exception e) {
			System.err.println("Error clicking Sales Operation Sub-menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerBookingMgtListLink() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CustomerBookingMgtListLink"));
			getElement("CustomerBookingMgtListLink").click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Booking Mgt List Link: " + e.getMessage());
			throw e;
		}
	}

	// Method to check if Customer Booking Mgt List screen is displayed
	public boolean isCustomerBookingMgtListScreenDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("CustomerBookingMgtListScreenHeader"));
			return getElement("CustomerBookingMgtListScreenHeader").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Customer Booking Mgt List screen visibility: " + e.getMessage());
			return false;
		}
	}

	// Method to select a Based On from the Field
	public void selectBasedOn(String basedOnName) throws Throwable {
		try {
			WebElement BasedOnDropDownField = getElement("BasedOnDropDown");
			waitForElementToBeClickable(BasedOnDropDownField);
			BasedOnDropDownField.click();
			BasedOnDropDownField.sendKeys(basedOnName);
			BasedOnDropDownField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Based On: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Mobile Number in Filter
	public void enterBasedOnField(String basedOnField) throws Throwable {
		try {
			WebElement BasedOnTheField = getElement("BasedOnField");
			waitForElementToBeClickable(BasedOnTheField);
			BasedOnTheField.clear();
			BasedOnTheField.sendKeys(basedOnField);
		} catch (Exception e) {
			System.err.println("Error entering Mobile in Filter: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerBookingMgtListSearch() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CustomerBookingMgtListSearch"));
			getElement("CustomerBookingMgtListSearch").click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Booking Mgt List Search: " + e.getMessage());
			throw e;
		}
	}

	public void clickCloseCustomerBookingMgt() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CloseCustomerBookingMgtScreen"));
			getElement("CloseCustomerBookingMgtScreen").click();
		} catch (Exception e) {
			System.err.println("Error clicking Close Customer Booking Mgt Screen: " + e.getMessage());
			throw e;
		}
	}

	public void clickCloseCustomerBookingMgtList() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CloseCustomerBookingMgtListScreen"));
			getElement("CloseCustomerBookingMgtListScreen").click();
		} catch (Exception e) {
			System.err.println("Error clicking Close Customer Booking Mgt List Screen: " + e.getMessage());
			throw e;
		}
	}

	public void doubleClickOnEnquiry() {
		try {
			// Perform double-click action
			Actions actions = new Actions(driver);
			actions.doubleClick(getElement("SelectEnquiryFromCustomerBookingMgtList")).perform();
			System.out.println("Successfully double-clicked on the enquiry.");
		} catch (Exception e) {
			System.err.println("Error performing double-click on the enquiry: " + e.getMessage());
			throw new RuntimeException("Failed to double-click on the enquiry.", e);
		}
	}

	// Method to check if Customer Booking Mgt screen is displayed
	public boolean isCustomerBookingMgtScreenDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("CustomerBookingMgtScreenHeader"));
			return getElement("CustomerBookingMgtScreenHeader").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Customer Booking Mgt screen visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickInvoiceTab() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("InvoiceTab"));
			getElement("InvoiceTab").click();
		} catch (Exception e) {
			System.err.println("Error clicking Enquiry From Customer Booking Mgt List: " + e.getMessage());
			throw e;
		}
	}

	public void clickSchemeButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("SchemeButton"));
			getElement("SchemeButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Scheme Button : " + e.getMessage());
			throw e;
		}
	}

	public void clickSchemePopupSaveButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("SchemePopupSaveButton"));
			getElement("SchemePopupSaveButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Scheme Popup Save Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickSchemePopupCloseButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("SchemePopupCloseButton"));
			getElement("SchemePopupCloseButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Scheme Popup Close Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isSchemePopupDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("SchemePopupHeader"));
			return getElement("SchemePopupHeader").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Scheme Pop-up visibility: " + e.getMessage());
			return false;
		}
	}

	public boolean isSchemeSaveConfirmationPopupDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("SchemeSaveConfirmationPopup"));
			return getElement("SchemeSaveConfirmationPopup").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Scheme Save Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickSchemeSaveConfirmationConfirmButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("SchemeSaveConfirmationConfirmButton"));
			getElement("SchemeSaveConfirmationConfirmButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Scheme Save Confirmation Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickMorePromotionsButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("MorePromotionsButton"));
			getElement("MorePromotionsButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking More Promotions Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickPromotionsSectionPlusIcon() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("PromotionsSectionPlusIcon"));
			getElement("PromotionsSectionPlusIcon").click();
		} catch (Exception e) {
			System.err.println("Error clicking Promotions Section Plus Icon: " + e.getMessage());
			throw e;
		}
	}

	public void clickPromotionCheckBoxAll() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("PromotionCheckBoxAll"));
			getElement("PromotionCheckBoxAll").click();
		} catch (Exception e) {
			System.err.println("Error clicking Promotion CheckBox All: " + e.getMessage());
			throw e;
		}
	}

	public void clickPromotionAddSelectedButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("PromotionAddSelectedButton"));
			getElement("PromotionAddSelectedButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Promotions AddSelected Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerBookingMgtModifyButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CustomerBookingMgtModifyButton"));
			getElement("CustomerBookingMgtModifyButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Booking Mgt Modify Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerBookingMgtModifyConfirmationPopupConfirmButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CustomerBookingMgtModifyConfirmationPopupConfirmButton"));
			getElement("CustomerBookingMgtModifyConfirmationPopupConfirmButton").click();
		} catch (Exception e) {
			System.err.println(
					"Error clicking Customer Booking Mgt Modify Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isPromotionsSectionDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("PromotionsSection"));
			return getElement("PromotionsSection").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Promotions Section visibility: " + e.getMessage());
			return false;
		}
	}

	public boolean isCustomerBookingMgtModifyConfirmationPopupDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("CustomerBookingMgtModifyConfirmationPopup"));
			return getElement("CustomerBookingMgtModifyConfirmationPopup").isDisplayed();
		} catch (Exception e) {
			System.err.println(
					"Error checking Customer Booking Mgt Modify Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public boolean isPromotionsPopupDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("PromotionsPopupHeader"));
			return getElement("PromotionsPopupHeader").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Promotions Popup visibility: " + e.getMessage());
			return false;
		}
	}

	// Action to enter Payable By Dealer Amount
	public void enterPayableByDealerAmountField(String PayableByDealerAmount) throws Exception {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("PayableByDealerAmountField"));
			actions.moveToElement(getElement("PayableByDealerAmountField")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("PayableByDealerAmountField")).sendKeys(PayableByDealerAmount).build().perform();
			System.out.println("Successfully entered text: " + PayableByDealerAmount);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions.", e);
		}
	}

	public void enterAdjustmentCreditNoteField(String AdjustmentCreditNoteAmount) {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("AdjustmentCreditNoteAmountField"));
			actions.moveToElement(getElement("AdjustmentCreditNoteAmountField")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("AdjustmentCreditNoteAmountField")).sendKeys(AdjustmentCreditNoteAmount).build().perform();
			System.out.println("Successfully entered text: " + AdjustmentCreditNoteAmount);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions.", e);
		}
	}

	// Action to enter Basic Insurance Amount
	public void enterBasicInsuranceAmountField(String BasicInsuranceAmount) throws Exception {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("BasicInsuranceAmountField"));
			actions.moveToElement(getElement("BasicInsuranceAmountField")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("BasicInsuranceAmountField")).sendKeys(BasicInsuranceAmount).build().perform();
			System.out.println("Successfully entered text: " + BasicInsuranceAmount);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions.", e);
		}
	}

	// Action to enter RTO Amount
	public void enterRTOAmountField(String RTOAmount) throws Exception {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("RTOAmountField"));
			actions.moveToElement(getElement("RTOAmountField")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("RTOAmountField")).sendKeys(RTOAmount).build().perform();
			System.out.println("Successfully entered text: " + RTOAmount);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions.", e);
		}
	}

	// Action to enter Road Tax Amount
	public void enterRoadTaxAmountField(String RoadTaxAmount) throws Exception {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("RoadTaxAmountField"));
			actions.moveToElement(getElement("RoadTaxAmountField")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("RoadTaxAmountField")).sendKeys(RoadTaxAmount).build().perform();
			System.out.println("Successfully entered text: " + RoadTaxAmount);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions.", e);
		}
	}

	// Action to enter Other Charges Amount
	public void enterOtherChargesAmountField(String OtherChargesAmount) throws Exception {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("OtherChargesAmountField"));
			actions.moveToElement(getElement("OtherChargesAmountField")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("OtherChargesAmountField")).sendKeys(OtherChargesAmount).build().perform();
			System.out.println("Successfully entered text: " + OtherChargesAmount);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions.", e);
		}
	}

	// Action to enter Life Tax Amount
	public void enterLifeTaxAmountField(String LifeTaxAmount) throws Exception {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("LifeTaxAmountField"));
			actions.moveToElement(getElement("LifeTaxAmountField")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("LifeTaxAmountField")).sendKeys(LifeTaxAmount).build().perform();
			System.out.println("Successfully entered text: " + LifeTaxAmount);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions.", e);
		}
	}

	public void selectVehicleUsageType(String VehicleUsageType) throws Exception {
		try {
			waitForElementToBeClickable(getElement("VehicleUsageTypeField"));
			getElement("VehicleUsageTypeField").click();

			if (VehicleUsageType != null && !VehicleUsageType.isEmpty()) {
				getElement("VehicleUsageTypeField").sendKeys(VehicleUsageType); // Enter the desired VehicleUsageType
			} else {
				// If no data is provided, simulate selecting the first auto-suggested value
				getElement("VehicleUsageTypeField").sendKeys(Keys.DOWN); // Navigate to the first suggestion
			}
			getElement("VehicleUsageTypeField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Vehicle Usage Type: " + e.getMessage());
			throw e;
		}
	}

	public void clickRegisterButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("RegisterButton"));
			getElement("RegisterButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Register Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isRegisterConfirmationPopupDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("RegisterConfirmationPopup"));
			return getElement("RegisterConfirmationPopup").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Register Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickRegisterConfirmationPopupConfirmButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("RegisterConfirmationPopupConfirmButton"));
			getElement("RegisterConfirmationPopupConfirmButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Register Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickInvoiceModifyButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("InvoiceModifyButton"));
			getElement("InvoiceModifyButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Invoice Modify Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isInvoiceModifyConfirmationPopupDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("InvoiceModifyConfirmationPopup"));
			return getElement("InvoiceModifyConfirmationPopup").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Invoice Modify Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickInvoiceModifyConfirmationPopupConfirmButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("InvoiceModifyConfirmationPopupConfirmButton"));
			getElement("InvoiceModifyConfirmationPopupConfirmButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Invoice Modify Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickInvoiceConfirmButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("InvoiceConfirmButton"));
			getElement("InvoiceConfirmButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Invoice Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isInvoiceConfirmConfirmationPopupDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("InvoiceConfirmConfirmationPopup"));
			return getElement("InvoiceConfirmConfirmationPopup").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Invoice Confirm Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickInvoiceConfirmConfirmationPopupConfirmButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("InvoiceConfirmConfirmationPopupConfirmButton"));
			getElement("InvoiceConfirmConfirmationPopupConfirmButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Invoice Confirm Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickServiceMenu() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("ServiceMenu"));
			getElement("ServiceMenu").click();
		} catch (Exception e) {
			System.err.println("Error clicking Service Menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickBasicInfoSubmenu() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("BasicInfoSubmenu"));
			getElement("BasicInfoSubmenu").click();
		} catch (Exception e) {
			System.err.println("Error clicking Basic Info Sub-menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickVehicleMgtLink() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("VehicleMgtLink"));
			getElement("VehicleMgtLink").click();
		} catch (Exception e) {
			System.err.println("Error clicking Vehicle Mgt Link: " + e.getMessage());
			throw e;
		}
	}

	// Method to check if Vehicle Mgt screen is displayed
	public boolean isVehicleMgtScreenDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("VehicleMgtScreenHeader"));
			return getElement("VehicleMgtScreenHeader").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Vehicle Mgt screen visibility: " + e.getMessage());
			return false;
		}
	}

	public void enterVinField(String vinField) throws Throwable {
		try {
			waitForVisibilityOfElement(getElement("VinField"));
			getElement("VinField").sendKeys(vinField);
		} catch (Exception e) {
			System.err.println("Error entering Vin Field: " + e.getMessage());
			throw e;
		}
	}

	public void clickVehicleMgtSearchButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("VehicleMgtSearchButton"));
			getElement("VehicleMgtSearchButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Vehicle Mgt Search Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickSelectRecordInVehicleMgt() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("SelectRecordInVehicleMgt"));
			getElement("SelectRecordInVehicleMgt").click();
		} catch (Exception e) {
			System.err.println("Error clicking Select Record In Vehicle Mgt: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Delivery Date
	public void enterDeliveryDateField(String deliveryDate) throws Throwable {
		try {
			waitForVisibilityOfElement(getElement("DeliveryDateField"));
			getElement("DeliveryDateField").sendKeys(deliveryDate);
		} catch (Exception e) {
			System.err.println("Error entering Delivery Date: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Enquiry Start Date
	public void enterEnquiryStartDateField(String enquiryStartDateField) throws Throwable {
		try {
            WebElement enquiryStartDateFieldValue = getElement("EnquiryStartDateField");
			waitForVisibilityOfElement(enquiryStartDateFieldValue);
			enquiryStartDateFieldValue.clear();
			enquiryStartDateFieldValue.sendKeys(enquiryStartDateField);
		} catch (Exception e) {
			System.err.println("Error entering Enquiry Start Date Field: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Reg. No
	public void enterRegNoField(String RegNo) throws Throwable {
		try {
			waitForVisibilityOfElement(getElement("RegNoField"));
			getElement("RegNoField").sendKeys(RegNo);
		} catch (Exception e) {
			System.err.println("Error entering Reg. No: " + e.getMessage());
			throw e;
		}
	}

	public void clickVehicleMgtModifyButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("VehicleMgtModifyButton"));
			getElement("VehicleMgtModifyButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Vehicle Mgt Modify Button: " + e.getMessage());
			throw e;
		}
	}

	public boolean isVehicleMgtModifyConfirmationPopupDisplayed() {
		try {
			waitForVisibilityOfElement(getElement("VehicleMgtModifyConfirmationPopup"));
			return getElement("VehicleMgtModifyConfirmationPopup").isDisplayed();
		} catch (Exception e) {
			System.err.println("Error checking Vehicle Mgt Modify Confirmation Popup visibility: " + e.getMessage());
			return false;
		}
	}

	public void clickVehicleMgtModifyConfirmationPopupConfirmButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("VehicleMgtModifyConfirmationPopupConfirmButton"));
			getElement("VehicleMgtModifyConfirmationPopupConfirmButton").click();
		} catch (Exception e) {
			System.err
					.println("Error clicking Vehicle Mgt Modify Confirmation Popup Confirm Button: " + e.getMessage());
			throw e;
		}
	}

	// Getter methods for WebElements used in step definitions
	public WebElement getSalesOperationSubmenu() throws Throwable {
		return getElement("SalesOperationSubmenu");
	}

	public WebElement getCustomerBookingMgtListLink() throws Throwable{
		return getElement("CustomerBookingMgtListLink");
	}

	public WebElement getCustomerBookingMgtListScreenHeader() throws Throwable{
		return getElement("CustomerBookingMgtListScreenHeader");
	}

	public WebElement getBasedOnDropDown() throws Throwable{
		return getElement("BasedOnDropDown");
	}

	public WebElement getBasedOnField() throws Throwable{
		return getElement("BasedOnField");
	}

	public WebElement getCustomerBookingMgtListSearch() throws Throwable{
		return getElement("CustomerBookingMgtListSearch");
	}

	public WebElement getSelectEnquiryFromCustomerBookingMgtList() throws Throwable{
		return getElement("SelectEnquiryFromCustomerBookingMgtList");
	}

	public WebElement getCustomerBookingMgtScreenHeader() throws Throwable{
		return getElement("CustomerBookingMgtScreenHeader");
	}

	public WebElement getInvoiceTab() throws Throwable{
		return getElement("InvoiceTab");
	}

	public WebElement getSchemeButton() throws Throwable{
		return getElement("SchemeButton");
	}

	public WebElement getSchemePopupHeader() throws Throwable{
		return getElement("SchemePopupHeader");
	}

	public WebElement getPayableByDealerAmountField() throws Throwable{
		return getElement("PayableByDealerAmountField");
	}

	public WebElement getAdjustmentCreditNoteAmountField() throws Throwable{
		return getElement("AdjustmentCreditNoteAmountField");
	}

	public WebElement getBasicInsuranceAmountField() throws Throwable{
		return getElement("BasicInsuranceAmountField");
	}

	public WebElement getRTOAmountField() throws Throwable{
		return getElement("RTOAmountField");
	}

	public WebElement getRoadTaxAmountField() throws Throwable{
		return getElement("RoadTaxAmountField");
	}

	public WebElement getOtherChargesAmountField() throws Throwable{
		return getElement("OtherChargesAmountField");
	}

	public WebElement getLifeTaxAmountField() throws Throwable{
		return getElement("LifeTaxAmountField");
	}

	public WebElement getSchemePopupSaveButton() throws Throwable{
		return getElement("SchemePopupSaveButton");
	}

	public WebElement getSchemeSaveConfirmationPopup() throws Throwable{
		return getElement("SchemeSaveConfirmationPopup");
	}

	public WebElement getSchemeSaveConfirmationConfirmButton() throws Throwable{
		return getElement("SchemeSaveConfirmationConfirmButton");
	}

	public WebElement getSchemePopupCloseButton() throws Throwable{
		return getElement("SchemePopupCloseButton");
	}

	public WebElement getMorePromotionsButton() throws Throwable{
		return getElement("MorePromotionsButton");
	}

	public WebElement getPromotionsSection() throws Throwable{
		return getElement("PromotionsSection");
	}

	public WebElement getPromotionsPopupHeader() throws Throwable{
		return getElement("PromotionsPopupHeader");
	}

	public WebElement getPromotionsSectionPlusIcon() throws Throwable{
		return getElement("PromotionsSectionPlusIcon");
	}

	public WebElement getPromotionCheckBoxAll() throws Throwable{
		return getElement("PromotionCheckBoxAll");
	}

	public WebElement getPromotionAddSelectedButton() throws Throwable{
		return getElement("PromotionAddSelectedButton");
	}

	public WebElement getCustomerBookingMgtModifyButton() throws Throwable{
		return getElement("CustomerBookingMgtModifyButton");
	}

	public WebElement getCustomerBookingMgtModifyConfirmationPopup() throws Throwable{
		return getElement("CustomerBookingMgtModifyConfirmationPopup");
	}

	public WebElement getCustomerBookingMgtModifyConfirmationPopupConfirmButton() throws Throwable{
		return getElement("CustomerBookingMgtModifyConfirmationPopupConfirmButton");
	}

	public WebElement getVehicleUsageTypeField() throws Throwable{
		return getElement("VehicleUsageTypeField");
	}

	public WebElement getRegisterButton() throws Throwable{
		return getElement("RegisterButton");
	}

	public WebElement getRegisterConfirmationPopup() throws Throwable{
		return getElement("RegisterConfirmationPopup");
	}

	public WebElement getRegisterConfirmationPopupConfirmButton() throws Throwable{
		return getElement("RegisterConfirmationPopupConfirmButton");
	}

	public WebElement getInvoiceModifyButton() throws Throwable{
		return getElement("InvoiceModifyButton");
	}

	public WebElement getInvoiceModifyConfirmationPopup() throws Throwable{
		return getElement("InvoiceModifyConfirmationPopup");
	}

	public WebElement getInvoiceModifyConfirmationPopupConfirmButton() throws Throwable{
		return getElement("InvoiceModifyConfirmationPopupConfirmButton");
	}

	public WebElement getInvoiceConfirmButton() throws Throwable{
		return getElement("InvoiceConfirmButton");
	}

	public WebElement getInvoiceConfirmConfirmationPopup() throws Throwable{
		return getElement("InvoiceConfirmConfirmationPopup");
	}

	public WebElement getInvoiceConfirmConfirmationPopupConfirmButton() throws Throwable{
		return getElement("InvoiceConfirmConfirmationPopupConfirmButton");
	}

	public WebElement getServiceMenu() throws Throwable{
		return getElement("ServiceMenu");
	}

	public WebElement getBasicInfoSubmenu() throws Throwable{
		return getElement("BasicInfoSubmenu");
	}

	public WebElement getVehicleMgtLink() throws Throwable{
		return getElement("VehicleMgtLink");
	}

	public WebElement getVehicleMgtScreenHeader() throws Throwable{
		return getElement("VehicleMgtScreenHeader");
	}

	public WebElement getVinField() throws Throwable{
		return getElement("VinField");
	}

	public WebElement getVehicleMgtSearchButton() throws Throwable{
		return getElement("VehicleMgtSearchButton");
	}

	public WebElement getSelectRecordInVehicleMgt() throws Throwable{
		return getElement("SelectRecordInVehicleMgt");
	}

	public WebElement getDeliveryDateField() throws Throwable{
		return getElement("DeliveryDateField");
	}

	public WebElement getRegNoField() throws Throwable{
		return getElement("RegNoField");
	}

	public WebElement getVehicleMgtModifyButton() throws Throwable{
		return getElement("VehicleMgtModifyButton");
	}

	public WebElement getVehicleMgtModifyConfirmationPopup() throws Throwable{
		return getElement("VehicleMgtModifyConfirmationPopup");
	}

	public WebElement getVehicleMgtModifyConfirmationPopupConfirmButton() throws Throwable{
		return getElement("VehicleMgtModifyConfirmationPopupConfirmButton");
	}
}
