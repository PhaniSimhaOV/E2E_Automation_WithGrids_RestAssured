package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewEnquiryWebPage {
	private static final Logger logger = LoggerFactory.getLogger(NewEnquiryWebPage.class);
	private final CommonActions commonActions;
	private WebDriver driver;

	@FindBy(xpath = "//*[@id='gnb']/li[3]/a")
	private WebElement SalesMenu;

	@FindBy(xpath = "//*[@id='gnb']/li[3]/div/ul/li[2]/a")
	private WebElement CustomerEnquirySubmenu;

	@FindBy(xpath = "//li[@class='active']//ul//li//a[@class='menuItem'][normalize-space()='Customer Enquiry']")
	private WebElement CustomerEnquiryLink;

	@FindBy(xpath = "//a[normalize-space()='Walk-in Enquiry']")
	private WebElement WalkinEnquiryTab;

	@FindBy(xpath = "//button[contains(text(), 'New')]")
	private WebElement NewEnquiry;

	@FindBy(xpath = "//*[@id=\"customerInfoForm\"]/dl[1]/dd[3]/div/span/span/input[1]")
	private WebElement MobileNumber;

	@FindBy(xpath = "//a[@id='searchCustomer']")
	private WebElement MobileSearchIcon;

	@FindBy(xpath = "//*[@id=\"customerInfoForm\"]/dl[1]/dd[1]/span")
	private WebElement CustTypeField;

	@FindBy(xpath = "//*[@id='custName'][@data-name='Cust. Name']")
	private WebElement CustName;

	@FindBy(xpath = "//*[@id=\"customerInfoForm\"]/dl[2]/dd[2]/span")
	private WebElement GenderField;

	@FindBy(xpath = "//*[@id='email']")
	private WebElement Email;

	@FindBy(xpath = "//*[@id=\"customerInfoForm\"]/dl[3]/dd[1]/span")
	private WebElement LocationField;

	@FindBy(xpath = "//a[@id='btnPinN']")
	private WebElement PinCodeSearchIcon;

	@FindBy(css = "#sPinCode")
	private WebElement Pincode;

	@FindBy(xpath = "//*[@id='btnSearch']")
	private WebElement PinCodeSearchButton;

	@FindBy(xpath = "//input[contains(@id, 'searchCustomer')]")
	private WebElement SearchIcon;

	@FindBy(xpath = "//*[@id='grid']/div[2]/table/tbody/tr[1]")
	private WebElement LocationSelection;

	@FindBy(xpath = "//button[@id='btnAddSelected']")
	private WebElement AddSelectedButton;

	@FindBy(xpath = "//*[@id=\"village\"]")
	private WebElement Village;

	@FindBy(xpath = "//*[@id=\"address\"]")
	private WebElement Address;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[1]/dd[2]/span")
	private WebElement EnquirySourceField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[1]/dd[3]/span")
	private WebElement EnquirySubSourceField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[1]/dd[4]/span")
	private WebElement EnquiryCategoryField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[2]/dd[1]/span")
	private WebElement ModelField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[2]/dd[2]/span")
	private WebElement FuelTypeField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[2]/dd[3]/span")
	private WebElement VariantField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[2]/dd[4]/span")
	private WebElement SubVariantField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[3]/dd[1]/span")
	private WebElement ExtColorField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[3]/dd[2]/span")
	private WebElement IntColorField;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[5]/dd[2]/span")
	private WebElement SalesConsultantField;
	
	@FindBy(xpath = "//*[@id=\"btnBasicSaveNew\"]")
	private WebElement SaveButton;

	@FindBy(xpath = "//a[@class='k-button k-bare k-button-icon k-window-action k-state-hover']//span[@class='k-icon k-i-close']")
	private WebElement PincodesearchScreenClose;

	@FindBy(xpath = "//*[@id='grid_active_cell']/input")
	private WebElement SelectEnquiry;

	@FindBy(xpath = "//*[@id='custName'][@data-name='Cust. Name']")
	private WebElement CompanyName;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[4]/dd[1]/span")
	private WebElement FinanceReqYN;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[4]/dd[2]/span")
	private WebElement Financier;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[6]/dd[3]/span")
	private WebElement CftOfDeposit;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[7]/dd[1]/span")
	private WebElement FinanceOptions;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[7]/dd[2]/span")
	private WebElement Expectedplan;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[7]/dd[4]/span")
	private WebElement VisitedwithFamily;

	@FindBy(xpath = "//*[@id=\"eqryForm\"]/div/dl[8]/dd/span")
	private WebElement ImmediateBook;

	@FindBy(xpath = "//*[@id='btnBasicSave']")
	private WebElement BasicInfoTabSaveButton;
	
	@FindBy(xpath = "//*[@id=\"usedCarForm\"]/dl[1]/dd[1]/span")
	private WebElement PresentCarDropdown;

	// Locator for iframe
	@FindBy(xpath = "(//*[starts-with(@id, 'tabMenuFrame')])[2]")
	private WebElement NewEnquiryiframe;

	@FindBy(xpath = "//iframe[contains(@class, 'k-content-frame')]")
	private WebElement CustomerEnquiryPopupiframe;

	@FindBy(xpath = "//iframe[contains(@title, 'Pin Code Search')]")
	private WebElement PincodeSearchiframe;

	public NewEnquiryWebPage(WebDriver driver) {
		this.commonActions = new CommonActions(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
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
			LaunchDriver.getDriver().switchTo().frame(PincodeSearchiframe);
			System.out.println("Successfully interacted with the element inside the Pincode Search iframe.");
		} catch (Exception e) {
			System.err.println("Error interacting with Pincode Search iframe: " + e.getMessage());
		}
	}

	public void interactWithIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().defaultContent();
			LaunchDriver.getDriver().switchTo().frame(NewEnquiryiframe);
			System.out.println("Successfully interacted with the element inside the iframe.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe: " + e.getMessage());
		}
	}

	public void interactWithCustomerEnquiryPopupIframeElement() {
		try {
			LaunchDriver.getDriver().switchTo().frame(CustomerEnquiryPopupiframe);
			System.out.println("Successfully interacted with the element inside the iframe in Customer Enquiry Popup.");
		} catch (Exception e) {
			System.err.println("Error interacting with iframe in Customer Enquiry Popup: " + e.getMessage());
		}
	}

	public void doubleClickOnEnquiry() throws Exception {
		try {
			waitForElementToBeClickable(SelectEnquiry);
			if (driver == null) {
				throw new RuntimeException("Driver must be initialized before performing actions.");
			}

			Actions actions = new Actions(driver);
			actions.doubleClick(SelectEnquiry).perform();
			System.out.println("Successfully double-clicked on the respective enquiry.");
		} catch (Exception e) {
			System.err.println("Error performing double-click on the respective enquiry: " + e.getMessage());
			throw new RuntimeException("Failed to double-click on the respective enquiry.", e);
		}
	}

	public void clickMobileSearchIcon() {
		try {
			waitForElementToBeClickable(MobileSearchIcon);
			MobileSearchIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking Mobile Search Icon: " + e.getMessage());
			throw e;
		}
	}

	public void clickSalesMenu() {
		try {
			waitForElementToBeClickable(SalesMenu);
			SalesMenu.click();
		} catch (Exception e) {
			System.err.println("Error clicking Sales Menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerEnquirySubmenu() {
		try {
			waitForElementToBeClickable(CustomerEnquirySubmenu);
			CustomerEnquirySubmenu.click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Enquiry Sub-menu: " + e.getMessage());
			throw e;
		}
	}

	public void clickCustomerEnquiryLink() {
		try {
			waitForElementToBeClickable(CustomerEnquiryLink);
			CustomerEnquiryLink.click();
		} catch (Exception e) {
			System.err.println("Error clicking Customer Enquiry Link: " + e.getMessage());
			throw e;
		}
	}

	public void clickWalkinEnquiryTab() {
		try {
			waitForElementToBeClickable(WalkinEnquiryTab);
			WalkinEnquiryTab.click();
		} catch (Exception e) {
			System.err.println("Error clicking Walkin Enquiry Tab: " + e.getMessage());
			throw e;
		}
	}

	public void clickNewEnquiryButton() {
		try {
			waitForElementToBeClickable(NewEnquiry);
			NewEnquiry.click();
		} catch (Exception e) {
			System.err.println("Error clicking New Enquiry button: " + e.getMessage());
			throw e;
		}
	}

	public void clickPincodeSearchIcon() {
		try {
			waitForElementToBeClickable(PinCodeSearchIcon);
			PinCodeSearchIcon.click();
		} catch (Exception e) {
			System.err.println("Error clicking PinCode Search Icon: " + e.getMessage());
			throw e;
		}
	}

	public void clickPinCodeSearchButton() {
		try {
			waitForElementToBeClickable(PinCodeSearchButton);
			PinCodeSearchButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking PinCode Search Button: " + e.getMessage());
			throw e;
		}
	}

	public void clickLocationSelection() {
		try {
			waitForElementToBeClickable(LocationSelection);
			LocationSelection.click();
		} catch (Exception e) {
			System.err.println("Error clicking Location Selection from Pincodes List : " + e.getMessage());
			throw e;
		}
	}

	public void clickAddSelectedButton() {
		try {
			waitForElementToBeClickable(AddSelectedButton);
			AddSelectedButton.click();
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
			waitForVisibilityOfElement(Pincode);
			actions.moveToElement(Pincode).click() // Move to the field and click to focus
					.keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(Pincode).sendKeys(pincode).build().perform();
			System.out.println("Successfully entered text: " + pincode);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions in Pincode: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions in Pincode.", e);
		}
	}

	public void clickPincodesearchScreenClose() {
		try {
			waitForElementToBeClickable(PincodesearchScreenClose);
			PincodesearchScreenClose.click();
		} catch (Exception e) {
			System.err.println("Error clicking Close icon in Pincode search Screen: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Mobile Number
	public void enterMobileNumber(String mobilenumber) throws Exception {
		try {
			waitForElementToBeClickable(MobileNumber);
			Thread.sleep(5000);
			MobileNumber.sendKeys(mobilenumber);
		} catch (Exception e) {
			System.err.println("Error entering MobileNumber: " + e.getMessage());
			throw e;
		}
	}
	
	// Action to enter Email Id
	public void enterEmail(String email) throws Exception {
		try {
			Actions actions = new Actions(driver);
			waitForVisibilityOfElement(Email);
			actions.moveToElement(Email).click().keyDown(Keys.CONTROL).sendKeys("a") // Select all text (CTRL + A)
					.keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE) // Delete the selected text
					.perform();
			actions.click(Email).sendKeys(email).build().perform();
			System.out.println("Successfully entered text: " + email);
		} catch (Exception e) {
			System.err.println("Error while entering text using Actions in Email: " + e.getMessage());
			throw new RuntimeException("Failed to enter text using Actions in Email.", e);
		}
	}

	public void clickSaveButton() {
		try {
			waitForElementToBeClickable(SaveButton);
			SaveButton.click();
		} catch (Exception e) {
			System.err.println("Error clicking Save Button: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Cust Name
	public void enterCustName(String custname) {
		try {
			waitForElementToBeClickable(CustName);
			CustName.clear();
			CustName.sendKeys(custname);
		} catch (Exception e) {
			System.err.println("Error entering Cust Name: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Company Name
	public void enterCompanyName(String companyname) {
		try {
			waitForElementToBeClickable(CompanyName);
			CompanyName.clear();
			CompanyName.sendKeys(companyname);
		} catch (Exception e) {
			System.err.println("Error entering Company Name: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Address
	public void enterAddress(String address) {
		try {
			waitForElementToBeClickable(Address);
			Address.clear();
			Address.sendKeys(address);
		} catch (Exception e) {
			System.err.println("Error entering Address: " + e.getMessage());
			throw e;
		}
	}

	// Action to enter Village
	public void enterVillage(String village) {
		try {
			waitForElementToBeClickable(Village);
			Village.clear();
			Village.sendKeys(village);
		} catch (Exception e) {
			System.err.println("Error entering Village: " + e.getMessage());
			throw e;
		}
	}

	public void selectCustTypeField(String CustType) throws Exception {
		try {
			waitForElementToBeClickable(CustTypeField);
			CustTypeField.click();
			Thread.sleep(5000);
			CustTypeField.sendKeys(CustType); // Enter the desired Cust Type
			CustTypeField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Cust Type: " + e.getMessage());
			throw e;
		}
	}
	
	public void selectPresentCarDropdown(String presentCarDropdown) throws Exception {
		try {
			waitForElementToBeClickable(PresentCarDropdown);
			PresentCarDropdown.click();
			Thread.sleep(5000);
			PresentCarDropdown.sendKeys(presentCarDropdown); // Enter the desired present Car Dropdown
			PresentCarDropdown.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting present Car Dropdown: " + e.getMessage());
			throw e;
		}
	}

	public void selectFinancier(String financier) throws Exception {
		try {
			waitForElementToBeClickable(Financier);
			Financier.click();
			Thread.sleep(5000);
			Financier.sendKeys(financier); // Enter the desired Financier
			Financier.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting financier: " + e.getMessage());
			throw e;
		}
	}

	public void selectFinanceReq(String FinanceReq) throws Exception {
		try {
			waitForElementToBeClickable(FinanceReqYN);
			FinanceReqYN.click();
			Thread.sleep(5000);
			FinanceReqYN.sendKeys(FinanceReq); // Enter the desired FinanceReq
			FinanceReqYN.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Finance Req: " + e.getMessage());
			throw e;
		}
	}

	public void selectGender(String Gender) throws Exception {
		try {
			waitForElementToBeClickable(GenderField);
			GenderField.click();
			Thread.sleep(5000);
			GenderField.sendKeys(Gender); // Enter the desired Gender
			GenderField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Gender: " + e.getMessage());
			throw e;
		}
	}

	public void selectLocation(String Location) throws Exception {
		try {
			waitForElementToBeClickable(LocationField);
			LocationField.click();
			Thread.sleep(5000);
			LocationField.sendKeys(Location); // Enter the desired Location
			LocationField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Location: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquirySource(String EnquirySource) throws Exception {
		try {
			waitForElementToBeClickable(EnquirySourceField);
			EnquirySourceField.click();
			Thread.sleep(5000);
			EnquirySourceField.sendKeys(EnquirySource); // Enter the desired Enquiry Source
			EnquirySourceField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Enquiry Source: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquirySubSource(String EnquirySubSource) throws Exception {
		try {
			waitForElementToBeClickable(EnquirySubSourceField);
			EnquirySubSourceField.click();
			Thread.sleep(5000);
			EnquirySubSourceField.sendKeys(EnquirySubSource); // Enter the desired Enquiry Sub Source
			EnquirySubSourceField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Enquiry Sub Source: " + e.getMessage());
			throw e;
		}
	}

	public void selectEnquiryCategory(String enquiryCategory) throws Exception {
		try {
			waitForElementToBeClickable(EnquiryCategoryField);
			EnquiryCategoryField.click();
			Thread.sleep(5000);
			EnquiryCategoryField.sendKeys(enquiryCategory); // Enter the desired Enquiry Category

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
			waitForElementToBeClickable(ModelField);
			ModelField.click();
			Thread.sleep(5000);
			ModelField.sendKeys(Model); // Enter the desired Model
			ModelField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Model: " + e.getMessage());
			throw e;
		}
	}

	public void selectFuelType(String FuelType) throws Exception {
		try {
			waitForElementToBeClickable(FuelTypeField);
			FuelTypeField.click();
			Thread.sleep(5000);
			FuelTypeField.sendKeys(FuelType);
			FuelTypeField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Fuel Type: " + e.getMessage());
			throw e;
		}
	}

	public void selectVariant(String Variant) throws Exception {
		try {
			waitForElementToBeClickable(VariantField);
			VariantField.click();
			Thread.sleep(5000);
			VariantField.sendKeys(Variant);
			VariantField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Variant: " + e.getMessage());
			throw e;
		}
	}

	public void selectSubVariant(String SubVariant) throws Exception {
//		try {
//			waitForElementToBeClickable(SubVariantField);
//			SubVariantField.click();
//			Thread.sleep(5000);
//			SubVariantField.sendKeys(SubVariant);
//			SubVariantField.sendKeys(Keys.ENTER);
//		} catch (Exception e) {
//			System.err.println("An error occurred while selecting Sub Variant: " + e.getMessage());
//			throw e;
//		}
//	}
		try {
			waitForElementToBeClickable(SubVariantField);
			SubVariantField.click();
			Thread.sleep(5000);
			SubVariantField.sendKeys(SubVariant); // Enter the desired Sub Variant

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
			waitForElementToBeClickable(ExtColorField);
			ExtColorField.click();
			Thread.sleep(5000);
			ExtColorField.sendKeys(ExtColor);
			ExtColorField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting ExtColor: " + e.getMessage());
			throw e;
		}
	}

	public void selectIntColor(String IntColor) throws Exception {
		try {
			waitForElementToBeClickable(IntColorField);
			IntColorField.click();
			Thread.sleep(5000);
			IntColorField.sendKeys(IntColor);
			IntColorField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Int Color: " + e.getMessage());
			throw e;
		}
	}

	public void selectSalesConsultant(String SalesConsultant) throws Exception {
		try {
			waitForElementToBeClickable(SalesConsultantField);
			SalesConsultantField.click();
			Thread.sleep(5000);
			SalesConsultantField.sendKeys(SalesConsultant);
			SalesConsultantField.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Sales Consultant: " + e.getMessage());
			throw e;
		}
	}

	public void selectCftOfDeposit(String cftOfDeposit) throws Exception {
		try {
			waitForElementToBeClickable(CftOfDeposit);
			CftOfDeposit.click();
			Thread.sleep(5000);
			CftOfDeposit.sendKeys(cftOfDeposit);
			CftOfDeposit.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Certificate Of Deposit: " + e.getMessage());
			throw e;
		}
	}

	public void selectFinanceOptions(String financeOptions) throws Exception {
		try {
			waitForElementToBeClickable(FinanceOptions);
			FinanceOptions.click();
			Thread.sleep(5000);
			FinanceOptions.sendKeys(financeOptions);
			FinanceOptions.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Finance Options: " + e.getMessage());
			throw e;
		}
	}

	public void selectVisitedwithFamily(String visitedwithFamily) throws Exception {
		try {
			waitForElementToBeClickable(VisitedwithFamily);
			VisitedwithFamily.click();
			Thread.sleep(5000);
			VisitedwithFamily.sendKeys(visitedwithFamily);
			VisitedwithFamily.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Visited with Family: " + e.getMessage());
			throw e;
		}
	}

	public void selectExpectedplan(String expectedplan) throws Exception {
		try {
			waitForElementToBeClickable(Expectedplan);
			Expectedplan.click();
			Thread.sleep(5000);
			Expectedplan.sendKeys(expectedplan);
			Expectedplan.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Expected plan: " + e.getMessage());
			throw e;
		}
	}

	public void selectImmediatebooking(String immediateBook) throws Exception {
		try {
			waitForElementToBeClickable(ImmediateBook);
			ImmediateBook.click();
			Thread.sleep(5000);
			ImmediateBook.sendKeys(immediateBook);
			ImmediateBook.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			System.err.println("An error occurred while selecting Immediate booking: " + e.getMessage());
			throw e;
		}
	}

//Getter methods for WebElements used in step definitions
	public WebElement getSalesMenu() {
		return SalesMenu;
	}

	public WebElement getCustomerEnquirySubmenu() {
		return CustomerEnquirySubmenu;
	}

	public WebElement getCustomerEnquiryLink() {
		return CustomerEnquiryLink;
	}

	public WebElement getWalkinEnquiryTab() {
		return WalkinEnquiryTab;
	}

	public WebElement getNewEnquiry() {
		return NewEnquiry;
	}

	public WebElement getMobileNumber() {
		return MobileNumber;
	}

	public WebElement getMobileSearchIcon() {
		return MobileSearchIcon;
	}

	public WebElement getCustTypeField() {
		return CustTypeField;
	}
	
	public WebElement getPresentCarDropdown() {
		return PresentCarDropdown;
	}

	public WebElement getCustName() {
		return CustName;
	}

	public WebElement getGenderField() {
		return GenderField;
	}

	public WebElement getEmail() {
		return Email;
	}

	public WebElement getAddress() {
		return Address;
	}

	public WebElement getVillage() {
		return Village;
	}

	public WebElement getLocation() {
		return LocationField;
	}

	public WebElement getCompanyName() {
		return CompanyName;
	}

	public WebElement getSearchIcon() {
		return SearchIcon;
	}

	public WebElement getPincode() {
		return Pincode;
	}

	public WebElement getPinCodeSearchButton() {
		return PinCodeSearchButton;
	}

	public WebElement getPincodeSearchIcon() {
		return PinCodeSearchIcon;
	}

	public WebElement getLocationSelection() {
		return LocationSelection;
	}

	public WebElement getAddSelectedButton() {
		return AddSelectedButton;
	}

	public WebElement getEnquirySubSource() {
		return EnquirySubSourceField;
	}

	public WebElement getEnquiryCategory() {
		return EnquiryCategoryField;
	}

	public WebElement getModel() {
		return ModelField;
	}

	public WebElement getFuelType() {
		return FuelTypeField;
	}

	public WebElement getVariant() {
		return VariantField;
	}

	public WebElement getSubVariant() {
		return SubVariantField;
	}

	public WebElement getFinanceReq() {
		return FinanceReqYN;
	}

	public WebElement getFinancier() {
		return Financier;
	}

	public WebElement getExtColor() {
		return ExtColorField;
	}

	public WebElement getIntColor() {
		return IntColorField;
	}

	public WebElement getSalesConsultant() {
		return SalesConsultantField;
	}

	public WebElement getCftOfDeposit() {
		return CftOfDeposit;
	}

	public WebElement getFinanceOptions() {
		return FinanceOptions;
	}

	public WebElement getExpectedplan() {
		return Expectedplan;
	}

	public WebElement getVisitedwithFamily() {
		return VisitedwithFamily;
	}

	public WebElement getImmediatebooking() {
		return ImmediateBook;
	}

	public WebElement getSaveButton() {
		return SaveButton;
	}

	public WebElement getPincodesearchScreenClose() {
		return PincodesearchScreenClose;
	}
}