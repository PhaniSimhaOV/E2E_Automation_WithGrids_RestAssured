package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.LaunchDriver;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewEnquiryWebWalkinPage {
	private static final Logger logger = LoggerFactory.getLogger(NewEnquiryWebWalkinPage.class);
	private final CommonActions commonActions;
	private final WebDriver driver;
	private final String featureName = "New Enquiry Web Walkin Locators"; // Updated for New Enquiry Walkin Web Page

	public NewEnquiryWebWalkinPage(WebDriver driver) {
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

	public void interactWithincodeSearchIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().frame(getElement("PincodeSearchiframe"));
			System.out.println("Successfully interacted with the element inside the Pincode Search iframe.");
		} catch (Exception e) {
			System.err.println("Error interacting with Pincode Search iframe: " + e.getMessage());
		}
	}

	public void interactWithIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().defaultContent();
			LaunchDriver.getDriver().switchTo().frame(getElement("NewEnquiryiframe"));
			System.out.println("Successfully interacted with the element inside the iframe.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe: " + e.getMessage());
		}
	}

	public void interactWithWalkinFindACustomeriframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().frame(getElement("WalkinFindACustomeriframe"));
			System.out.println("Successfully interacted with the element inside the FindACustomeriframe.");
		} catch (Exception e) {
			System.err.println("Error interacting with FindACustomeriframe: " + e.getMessage());
		}
	}

	public void interactWithCustomerEnquiryPopupIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().frame(getElement("CustomerEnquiryPopupiframe"));
			System.out.println("Successfully interacted with the element inside the iframe in Customer Enquiry Popup.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe in Customer Enquiry Popup: " + e.getMessage());
		}
	}

	public void doubleClickOnEnquiry() throws Exception {
		try {
			waitForElementToBeClickable(getElement("SelectEnquiry"));
			if (driver == null) {
				throw new RuntimeException("Driver must be initialized before performing actions.");
			}
			Actions actions = new Actions(driver);
			actions.doubleClick(getElement("SelectEnquiry")).perform();
			System.out.println("Successfully double-clicked on the respective enquiry.");
		} catch (Exception e) {
			System.err.println("Error performing double-click on the respective enquiry: " + e.getMessage());
			throw new RuntimeException("Failed to double-click on the respective enquiry.", e);
		}
	}

	// Action to enter Mobile Number in filter
	public void enterMobileNumberFilter(String mobile) throws Exception {
		try {
			waitForElementToBeClickable(getElement("MobileFilter"));
			Thread.sleep(5000);
			getElement("MobileFilter").sendKeys(mobile);
		} catch (Exception e) {
			System.err.println("Error entering MobileNumber in filter: " + e.getMessage());
			throw e;
		}
	}

	public void clickManageScreenSearchButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("ManageScreenSearchButton"));
			getElement("ManageScreenSearchButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Mobile Search Icon: " + e.getMessage());
			throw e;
		}
	}

	public void clickMobileSearchIcon() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("MobileSearchIcon"));
			getElement("MobileSearchIcon").click();
		} catch (Exception e) {
			System.err.println("Error clicking Mobile Search Icon: " + e.getMessage());
			throw e;
		}
	}

	// Action to get the value in the Enquiry No field
	public String getEnquiryNoFieldValue() throws Throwable {
		try {
			return getElement("EnquiryNoField").getText();
		} catch (Exception e) {
			System.err.println("Error retrieving Enquiry No Field value: " + e.getMessage());
			throw e;
		}
	}

	public void doubleClickOnWalkinCustomerDetails() {
		try {
			// Perform double-click action
			Actions actions = new Actions(driver);
			actions.doubleClick(getElement("SelectWalkinCustomerDetails")).perform();
			System.out.println("Successfully double-clicked on the Walkin Customer Details.");
		} catch (Exception e) {
			System.err.println("Error performing double-click on the Walkin Customer Details: " + e.getMessage());
			throw new RuntimeException("Failed to double-click on the Walkin Customer Details.", e);
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

	public void clickCustomerEnquirySubmenu() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CustomerEnquirySubmenu"));
			getElement("CustomerEnquirySubmenu").click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Enquiry Sub-menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerEnquiryLink() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CustomerEnquiryLink"));
			getElement("CustomerEnquiryLink").click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Enquiry Link: " + e.getMessage());
			throw e;
		}
	}

	public void clickWalkinEnquiryTab() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("WalkinEnquiryTab"));
			getElement("WalkinEnquiryTab").click();
		} catch (Exception e) {
			System.err.println("Error clicking Walkin Enquiry Tab: " + e.getMessage());
			throw e;
		}
	}

	public void clickNewEnquiryButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("NewEnquiry"));
			getElement("NewEnquiry").click();
		} catch (Exception e) {
			System.err.println("Error clicking New Enquiry button: " + e.getMessage());
			throw e;
		}
	}

	public void clickPincodeSearchIcon() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("PinCodeSearchIcon"));
			getElement("PinCodeSearchIcon").click();
		} catch (Exception e) {
			System.err.println("Error clicking PinCode Search Icon: " + e.getMessage());
			throw e;
		}
	}

	public void clickPinCodeSearchButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("PinCodeSearchButton"));
			getElement("PinCodeSearchButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking PinCode Search Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickLocationSelection() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("LocationSelection"));
			getElement("LocationSelection").click();
		} catch (Exception e) {
			System.err.println("Error clicking Location Selection from Pincodes List : " + e.getMessage());
			throw e;
		}
	}

	public void clickAddSelectedButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("AddSelectedButton"));
			getElement("AddSelectedButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Add Selected Button: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Pincode
	public void enterPincode(String pincode) {
		try {
			Thread.sleep(2000);
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("Pincode"));
			actions.moveToElement(getElement("Pincode")).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("Pincode")).sendKeys(pincode).build().perform();
			System.out.println("Successfully entered text: " + pincode);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions in Pincode: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions in Pincode.", e);
		}
	}

	public void clickPincodesearchScreenClose() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("PincodesearchScreenClose"));
			getElement("PincodesearchScreenClose").click();
		} catch (Exception e) {
			System.err.println("Error clicking Close icon in Pincode search Screen: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Mobile Number
	public void enterMobileNumber(String mobilenumber) throws Exception {
		try {
			waitForElementToBeClickable(getElement("MobileNumber"));
			Thread.sleep(5000);
			getElement("MobileNumber").sendKeys(mobilenumber);
		} catch (Exception e) {
			System.err.println("Error entering MobileNumber: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Email Id
	public void enterEmail(String email) throws Exception {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(getElement("Email"));
			actions.moveToElement(getElement("Email")).click().keyDown(Keys.CONTROL).sendKeys("a") // Select all text
																									// (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(getElement("Email")).sendKeys(email).build().perform();
			System.out.println("Successfully entered text: " + email);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions in Email: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions in Email.", e);
		}
	}

	public void clickSaveButton() throws Throwable {
		try {
			waitForElementToBeClickable(getElement("SaveButton"));
			getElement("SaveButton").click();
		} catch (Exception e) {
			System.err.println("Error clicking Save Button: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Cust Name
	public void enterCustName(String custname) throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CustName"));
			getElement("CustName").clear();
			getElement("CustName").sendKeys(custname);
		} catch (Exception e) {
			System.err.println("Error entering Cust Name: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Company Name
	public void enterCompanyName(String companyname) throws Throwable {
		try {
			waitForElementToBeClickable(getElement("CompanyName"));
			getElement("CompanyName").clear();
			getElement("CompanyName").sendKeys(companyname);
		} catch (Exception e) {
			System.err.println("Error entering Company Name: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Address
	public void enterAddress(String address) throws Throwable {
		try {
			waitForElementToBeClickable(getElement("Address"));
			getElement("Address").clear();
			getElement("Address").sendKeys(address);
		} catch (Exception e) {
			System.err.println("Error entering Address: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Village
	public void enterVillage(String village) throws Throwable {
		try {
			waitForElementToBeClickable(getElement("Village"));
			getElement("Village").clear();
			getElement("Village").sendKeys(village);
		} catch (Exception e) {
			System.err.println("Error entering Village: " + e.getMessage());
			throw e;
		}
	}

	public void selectCustTypeField(String CustType) throws Exception {
		try {
			waitForElementToBeClickable(getElement("CustTypeField"));
			getElement("CustTypeField").click();
			Thread.sleep(5000);
			getElement("CustTypeField").sendKeys(CustType); // Enter the desired Cust Type
			getElement("CustTypeField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Cust Type: " + e.getMessage());
			throw e;
		}
	}

	public void selectPresentCarDropdown(String presentCarDropdown) throws Exception {
		try {
			waitForElementToBeClickable(getElement("PresentCarDropdown"));
			getElement("PresentCarDropdown").click();
			Thread.sleep(5000);
			getElement("PresentCarDropdown").sendKeys(presentCarDropdown); // Enter the desired present Car Dropdown
			getElement("PresentCarDropdown").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting present Car Dropdown: " + e.getMessage());
			throw e;
		}
	}

	public void selectFinancier(String financier) throws Exception {
		try {
			waitForElementToBeClickable(getElement("Financier"));
			getElement("Financier").click();
			Thread.sleep(5000);
			getElement("Financier").sendKeys(financier); // Enter the desired Financier
			getElement("Financier").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting financier: " + e.getMessage());
			throw e;
		}
	}

	public void selectFinanceReq(String FinanceReq) throws Exception {
		try {
			waitForElementToBeClickable(getElement("FinanceReqYN"));
			getElement("FinanceReqYN").click();
			Thread.sleep(5000);
			getElement("FinanceReqYN").sendKeys(FinanceReq); // Enter the desired FinanceReq
			getElement("FinanceReqYN").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Finance Req: " + e.getMessage());
			throw e;
		}
	}

	public void selectGender(String Gender) throws Exception {
		try {
			waitForElementToBeClickable(getElement("GenderField"));
			getElement("GenderField").click();
			Thread.sleep(5000);
			getElement("GenderField").sendKeys(Gender); // Enter the desired Gender
			getElement("GenderField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Gender: " + e.getMessage());
			throw e;
		}
	}

	public void selectLocation(String Location) throws Exception {
		try {
			waitForElementToBeClickable(getElement("LocationField"));
			getElement("LocationField").click();
			Thread.sleep(5000);
			getElement("LocationField").sendKeys(Location); // Enter the desired Location
			getElement("LocationField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Location: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquirySource(String EnquirySource) throws Exception {
		try {
			waitForElementToBeClickable(getElement("EnquirySourceField"));
			getElement("EnquirySourceField").click();
			Thread.sleep(5000);
			getElement("EnquirySourceField").sendKeys(EnquirySource); // Enter the desired Enquiry Source
			getElement("EnquirySourceField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Enquiry Source: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquirySubSource(String EnquirySubSource) throws Exception {
		try {
			waitForElementToBeClickable(getElement("EnquirySubSourceField"));
			getElement("EnquirySubSourceField").click();
			Thread.sleep(5000);
			getElement("EnquirySubSourceField").sendKeys(EnquirySubSource); // Enter the desired Enquiry Sub Source
			getElement("EnquirySubSourceField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Enquiry Sub Source: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquiryCategory(String enquiryCategory) throws Exception {
		try {
			waitForElementToBeClickable(getElement("EnquiryCategoryField"));
			getElement("EnquiryCategoryField").click();
			Thread.sleep(5000);
			getElement("EnquiryCategoryField").sendKeys(enquiryCategory); // Enter the desired Enquiry Category

			// Wait for the auto-suggestions to appear
			List<WebElement> suggestions = driver.findElements(By.cssSelector("ul.auto-suggestions li"));

			boolean isMatched = false;

			// Check if the expected enquiry category matches any suggestion
			for (WebElement suggestion : suggestions) {
				if (suggestion.getText().equalsIgnoreCase(enquiryCategory)) {
					suggestion.click(); // Click the matching option
					isMatched = true;
					System.out.println("Matched and selected: " + enquiryCategory);
					break;
				}
			}

			// If no match found, select the first suggestion
			if (!isMatched && suggestions.size() > 0) {
				suggestions.get(0).click(); // Select the first suggestion
				System.out.println("No match found. Selected first auto-suggestion.");
			}
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Enquiry Category: " + e.getMessage());
			throw e;
		}
	}

	public void selectModel(String Model) throws Exception {
		try {
			waitForElementToBeClickable(getElement("ModelField"));
			getElement("ModelField").click();
			Thread.sleep(5000);
			getElement("ModelField").sendKeys(Model); // Enter the desired Model
			getElement("ModelField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Model: " + e.getMessage());
			throw e;
		}
	}

	public void selectFuelType(String FuelType) throws Exception {
		try {
			waitForElementToBeClickable(getElement("FuelTypeField"));
			getElement("FuelTypeField").click();
			Thread.sleep(5000);
			getElement("FuelTypeField").sendKeys(FuelType);
			getElement("FuelTypeField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Fuel Type: " + e.getMessage());
			throw e;
		}
	}

	public void selectVariant(String Variant) throws Exception {
		try {
			waitForElementToBeClickable(getElement("VariantField"));
			getElement("VariantField").click();
			Thread.sleep(5000);
			getElement("VariantField").sendKeys(Variant);
			getElement("VariantField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Variant: " + e.getMessage());
			throw e;
		}
	}

	public void selectSubVariant(String SubVariant) throws Exception {
		try {
			waitForElementToBeClickable(getElement("SubVariantField"));
			getElement("SubVariantField").click();
			Thread.sleep(5000);
			getElement("SubVariantField").sendKeys(SubVariant); // Enter the desired Sub Variant

			// Wait for the auto-suggestions to appear
			List<WebElement> suggestions = driver.findElements(By.cssSelector("ul.auto-suggestions li"));

			boolean isMatched = false;

			// Check if the expected Sub Variant matches any suggestion
			for (WebElement suggestion : suggestions) {
				if (suggestion.getText().equalsIgnoreCase(SubVariant)) {
					suggestion.click(); // Click the matching option
					isMatched = true;
					System.out.println("Matched and selected: " + SubVariant);
					break;
				}
			}

			// If no match found, select the first suggestion
			if (!isMatched && suggestions.size() > 0) {
				suggestions.get(0).click(); // Select the first suggestion
				System.out.println("No match found. Selected first auto-suggestion.");
			}
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Sub Variant: " + e.getMessage());
			throw e;
		}
	}

	public void selectExtColor(String ExtColor) throws Exception {
		try {
			waitForElementToBeClickable(getElement("ExtColorField"));
			getElement("ExtColorField").click();
			Thread.sleep(5000);
			getElement("ExtColorField").sendKeys(ExtColor);
			getElement("ExtColorField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting ExtColor: " + e.getMessage());
			throw e;
		}
	}

	public void selectIntColor(String IntColor) throws Exception {
		try {
			waitForElementToBeClickable(getElement("IntColorField"));
			getElement("IntColorField").click();
			Thread.sleep(5000);
			getElement("IntColorField").sendKeys(IntColor);
			getElement("IntColorField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Int Color: " + e.getMessage());
			throw e;
		}
	}

	public void selectSalesConsultant(String SalesConsultant) throws Exception {
		try {
			waitForElementToBeClickable(getElement("SalesConsultantField"));
			getElement("SalesConsultantField").click();
			Thread.sleep(5000);
			getElement("SalesConsultantField").sendKeys(SalesConsultant);
			getElement("SalesConsultantField").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Sales Consultant: " + e.getMessage());
			throw e;
		}
	}

	public void selectCftOfDeposit(String cftOfDeposit) throws Exception {
		try {
			waitForElementToBeClickable(getElement("CftOfDeposit"));
			getElement("CftOfDeposit").click();
			Thread.sleep(5000);
			getElement("CftOfDeposit").sendKeys(cftOfDeposit);
			getElement("CftOfDeposit").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Certificate Of Deposit: " + e.getMessage());
			throw e;
		}
	}

	public void selectFinanceOptions(String financeOptions) throws Exception {
		try {
			waitForElementToBeClickable(getElement("FinanceOptions"));
			getElement("FinanceOptions").click();
			Thread.sleep(5000);
			getElement("FinanceOptions").sendKeys(financeOptions);
			getElement("FinanceOptions").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Finance Options: " + e.getMessage());
			throw e;
		}
	}

	public void selectVisitedwithFamily(String visitedwithFamily) throws Exception {
		try {
			waitForElementToBeClickable(getElement("VisitedwithFamily"));
			getElement("VisitedwithFamily").click();
			Thread.sleep(5000);
			getElement("VisitedwithFamily").sendKeys(visitedwithFamily);
			getElement("VisitedwithFamily").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Visited with Family: " + e.getMessage());
			throw e;
		}
	}

	public void selectExpectedplan(String expectedplan) throws Exception {
		try {
			waitForElementToBeClickable(getElement("Expectedplan"));
			getElement("Expectedplan").click();
			Thread.sleep(5000);
			getElement("Expectedplan").sendKeys(expectedplan);
			getElement("Expectedplan").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Expected plan: " + e.getMessage());
			throw e;
		}
	}

	public void selectImmediatebooking(String immediateBook) throws Exception {
		try {
			waitForElementToBeClickable(getElement("ImmediateBook"));
			getElement("ImmediateBook").click();
			Thread.sleep(5000);
			getElement("ImmediateBook").sendKeys(immediateBook);
			getElement("ImmediateBook").sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Immediate booking: " + e.getMessage());
			throw e;
		}
	}

//Getter methods for WebElements used in step definitions
	public WebElement getSalesMenu() throws Throwable {
		return getElement("SalesMenu");
	}

	public WebElement getCustomerEnquirySubmenu() throws Throwable {
		return getElement("CustomerEnquirySubmenu");
	}

	public WebElement getCustomerEnquiryLink() throws Throwable {
		return getElement("CustomerEnquiryLink");
	}

	public WebElement getWalkinEnquiryTab() throws Throwable {
		return getElement("WalkinEnquiryTab");
	}

	public WebElement getNewEnquiry() throws Throwable {
		return getElement("NewEnquiry");
	}

	public WebElement getMobileNumber() throws Throwable {
		return getElement("MobileNumber");
	}

	public WebElement getMobileSearchIcon() throws Throwable {
		return getElement("MobileSearchIcon");
	}

	public WebElement getCustTypeField() throws Throwable {
		return getElement("CustTypeField");
	}

	public WebElement getPresentCarDropdown() throws Throwable {
		return getElement("PresentCarDropdown");
	}

	public WebElement getCustName() throws Throwable {
		return getElement("CustName");
	}

	public WebElement getGenderField() throws Throwable {
		return getElement("GenderField");
	}

	public WebElement getEmail() throws Throwable {
		return getElement("Email");
	}

	public WebElement getAddress() throws Throwable {
		return getElement("Address");
	}

	public WebElement getVillage() throws Throwable {
		return getElement("Village");
	}

	public WebElement getLocation() throws Throwable {
		return getElement("LocationField");
	}

	public WebElement getCompanyName() throws Throwable {
		return getElement("CompanyName");
	}

	public WebElement getSearchIcon() throws Throwable {
		return getElement("SearchIcon");
	}

	public WebElement getPincode() throws Throwable {
		return getElement("Pincode");
	}

	public WebElement getPinCodeSearchButton() throws Throwable {
		return getElement("PinCodeSearchButton");
	}

	public WebElement getPincodeSearchIcon() throws Throwable {
		return getElement("PinCodeSearchIcon");
	}

	public WebElement getLocationSelection() throws Throwable {
		return getElement("LocationSelection");
	}

	public WebElement getAddSelectedButton() throws Throwable {
		return getElement("AddSelectedButton");
	}

	public WebElement getEnquirySubSource() throws Throwable {
		return getElement("EnquirySubSourceField");
	}

	public WebElement getEnquiryCategory() throws Throwable {
		return getElement("EnquiryCategoryField");
	}

	public WebElement getModel() throws Throwable {
		return getElement("ModelField");
	}

	public WebElement getFuelType() throws Throwable {
		return getElement("FuelTypeField");
	}

	public WebElement getVariant() throws Throwable {
		return getElement("VariantField");
	}

	public WebElement getSubVariant() throws Throwable {
		return getElement("SubVariantField");
	}

	public WebElement getFinanceReq() throws Throwable {
		return getElement("FinanceReqYN");
	}

	public WebElement getFinancier() throws Throwable {
		return getElement("Financier");
	}

	public WebElement getExtColor() throws Throwable {
		return getElement("ExtColorField");
	}

	public WebElement getIntColor() throws Throwable {
		return getElement("IntColorField");
	}

	public WebElement getSalesConsultant() throws Throwable {
		return getElement("SalesConsultantField");
	}

	public WebElement getCftOfDeposit() throws Throwable {
		return getElement("CftOfDeposit");
	}

	public WebElement getFinanceOptions() throws Throwable {
		return getElement("FinanceOptions");
	}

	public WebElement getExpectedplan() throws Throwable {
		return getElement("Expectedplan");
	}

	public WebElement getVisitedwithFamily() throws Throwable {
		return getElement("VisitedwithFamily");
	}

	public WebElement getImmediatebooking() throws Throwable {
		return getElement("ImmediateBook");
	}

	public WebElement getSaveButton() throws Throwable {
		return getElement("SaveButton");
	}

	public WebElement getPincodesearchScreenClose() throws Throwable {
		return getElement("PincodesearchScreenClose");
	}

	public WebElement getMobileFilter() throws Throwable {
		return getElement("MobileFilter");
	}

	public WebElement getCustomerEnquiryScreenHeader() throws Throwable {
		return getElement("WalkinEnquiryTab");
	}

	public WebElement getSelectWalkinCustomerDetails() throws Throwable {
		return getElement("SelectWalkinCustomerDetails");
	}

	public boolean isCustomerEnquiryScreenDisplayed() throws Throwable {
		try {
			WebElement header = getCustomerEnquiryScreenHeader();
			return header.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isWalkinFindCustomerInfoScreenVisible() {
		try {
			// Wait until the element is visible (adjust timeout as needed)
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(getElement("WalkinfindCustomerInfoScreen")));
			return getElement("WalkinfindCustomerInfoScreen").isDisplayed();
		} catch (Exception e) {
			// Return false if the element is not found or visible
			return false;
		}
	}

}