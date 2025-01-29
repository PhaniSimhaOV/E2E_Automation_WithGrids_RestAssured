package com.autogrid.steps;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;

public class ExWarrantyPage {
	private static final Logger logger = LoggerFactory.getLogger(ExWarrantyPage.class);
	private final CommonActions commonActions;
	WebDriver driver;

	public ExWarrantyPage(WebDriver driver) {
		this.commonActions = new CommonActions(driver);
		PageFactory.initElements(driver, this);
	}

	// XPath for web elements
	@FindBy(xpath = "//*[text()='Service']")
	private WebElement ServiceIcon;

	@FindBy(xpath = "//*[text()='Extended Warranty']")
	private WebElement ExtendedWarrantySubMenu;

	@FindBy(xpath = "(//*[text()='Extended Warranty Submit'])[2]")
	private WebElement ExtendedWarrantySubmitLink;

	@FindBy(xpath = "//span[text()='Extended Warranty Submit']")
	private WebElement ExtendedWarrantySubmitHeader;

	@FindBy(xpath = "//input[@id='sKeyword']")
	private WebElement VIN;

	@FindBy(xpath = "//input[@class='k-formatted-value form_numeric ar k-input']")
	private WebElement OdometerReading;

	@FindBy(xpath = "//*[@id='frm1']/div[2]/dl[8]/dd[1]/span/span[1]")
	private WebElement EmployeeNameDrpDwn;

	@FindBy(xpath = "//button[@id='btnInquire']")
	private WebElement InquireIcon;

	@FindBy(xpath = "//span[@aria-owns='placeOfSupply_listbox']//span[@class='k-input']")
	private WebElement PlaceOfSupplyDrpDwn;

	@FindBy(xpath = "//*[@id='grid']/div[2]/table/tbody/tr[5]/td[3]")
	private WebElement RequiredExtWarrantyType;

	@FindBy(xpath = "//button[@id='btnSubmit']")
	private WebElement SubmitBtn;

	@FindBy(xpath = "//button[@id='btnClear']")
	private WebElement ClearBtn;

	@FindBy(xpath = "//*[@src='/ser/serf/selectExtendedWarrantySubmitMain.dms']")
	private WebElement iFrameForExtWarranty;

	@FindBy(xpath = "//button[text()='close']")
	private WebElement close;

	@FindBy(xpath = "//div/div/div/div[1]/div[2]/table/tbody/tr/td[3]")
	private WebElement ExWarrantySchemeData;

	@FindBy(xpath = "//p[contains(text(),'Customer No. does not exist. Please enter the CustomerNo')]")
	private WebElement isCustomerNotExistPopupVisible;

	public void clickServiceIcon() {
		try {

			commonActions.explicitWait("//*[text()='Service']");
			ServiceIcon.click();

//    	JavascriptExecutor jse = (JavascriptExecutor)LaunchDriver.getDriver();
//    	jse.executeScript("arguments[0].click();",ServiceIcon);
//		  
//		  Actions act=new Actions(LaunchDriver.getDriver());
//		  act.click(ServiceIcon).perform();

		} catch (Exception e) {
			System.err.println("Failed to click Service Icon" + e.getMessage());
		}
	}

	public void clickExtWarrantySubMenu() {
		try {

			ExtendedWarrantySubMenu.click();

		} catch (Exception e) {
			System.err.println("Failed to click Extended Warranty Sub Menu" + e.getMessage());
		}
	}

	public void clickOnExtdWarrantySubmitLink() {
		try {
			ExtendedWarrantySubmitLink.click();

		} catch (Exception e) {
			System.err.println("Failed to click Extended WArranty Submit Link" + e.getMessage());
		}
	}

//method to check if ExtendedWarrantySubmitHeader is displayed
	public boolean extdWarrantySbtHeaderisDisplayed() {
		try {

			return ExtendedWarrantySubmitHeader.isDisplayed();

		} catch (Exception e) {

			return false;
		}
	}

	public void interactWithIframeExtW() {

		try {
			LaunchDriver.getDriver().switchTo().frame(iFrameForExtWarranty);
			System.out.println("Successfully interacted with iFrame of ExtWarranty");
		} catch (Exception e) {

			System.err.println("Error in interacting with iFrame of ExWarranty" + e.getMessage());
		}

	}

	public void enterVIN(String vin) {
		try {
			commonActions.explicitWait("//input[@id='sKeyword']");
			VIN.clear();
			VIN.sendKeys(vin);
		} catch (Exception e) {
			System.err.println("Error in entering VIN " + e.getMessage());
		}
	}

	public void clickOnInquire() {
		try {
			InquireIcon.click();

		} catch (Exception e) {
			System.err.println("Error in clicking Inquire icon" + e.getMessage());
		}
	}

	public void enterCurrentOdoMtrReading(String odoMtrReading) {
		try {
			commonActions.explicitWait("//input[@class='k-formatted-value form_numeric ar k-input']");
			if (OdometerReading.isDisplayed()) {
				// OdometerReading.sendKeys(odoMtrReading);

				JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
				js.executeScript("arguments[0].value='" + odoMtrReading + "';", OdometerReading);
			} else {
				System.out.println(OdometerReading + "Element is not visible");
			}
		} catch (Exception e) {
			System.err.println("Error in entering OdomtrReading" + e.getMessage());
		}
	}

	public void selectEmployeeName(String employeeName) {
		EmployeeNameDrpDwn.click();
		commonActions.explicitWait("//ul[@id='extbEmpNo_listbox']//li");
		WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
		List<WebElement> employeeList = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='extbEmpNo_listbox']//li")));
		try {

			for (WebElement empName : employeeList) {

				if (empName.getText().trim().toUpperCase().equals(employeeName.trim().toUpperCase())) {
					empName.click();
					System.out.println("Employee Name after Click: " + empName.getText().trim().toUpperCase());
					break;
				}

			}

			// JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
			// js.executeScript("arguments[0].value='" + employeeName + "';",
			// EmployeeNameDrpDwn);
		} catch (Exception e) {
			System.err.println("Error in selecting employee Name" + e.getMessage());
		}

	}

	public void selectPlaceOfSupply(String placeOfSupply) {
		PlaceOfSupplyDrpDwn.click();
		commonActions.explicitWait("//ul[@id='placeOfSupply_listbox']//li");

		WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
		List<WebElement> SupplyStates = wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='placeOfSupply_listbox']//li")));
		try {
			for (WebElement stateName : SupplyStates) {

				if (stateName.getText().trim().toUpperCase().equals(placeOfSupply.trim().toUpperCase())) {
					stateName.click();
					System.out.println("Place Of Supply after Click: " + placeOfSupply.trim().toUpperCase());
					break;
				}
			}

			// JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
			// js.executeScript("arguments[0].value='" + placeOfSupply + "';",
			// PlaceOfSupplyDrpDwn);

		} catch (Exception e) {
			System.err.println("Error in selecting State Name" + e.getMessage());
		}
	}

	// Method to find and select the scheme from the "Scheme Description" column
	public void setExtdWarrantyType(String SchemeDes) {
		try {
			// XPath to target the rows of the table containing the scheme descriptions
			String xpathForTableRows = "//*[@id='grid']//tbody/tr"; // Adjust this XPath based on the actual HTML
																	// structure

			// Wait for the rows to become visible
			WebDriverWait wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(10));
			List<WebElement> tableRows = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathForTableRows)));

			System.out.println("Number of rows in the table: " + tableRows.size());

			// Flag to track if the scheme is found
			boolean schemeFound = false;

			// Iterate through each row in the table
			for (WebElement row : tableRows) {
				// Locate the specific column containing the "Scheme Description" text
				WebElement schemeColumn = row.findElement(By.xpath(".//td[3]")); // Adjust the column index (td[3]) if
																					// necessary
				String schemeText = schemeColumn.getText().trim();

				System.out.println("Checking scheme: " + schemeText);

				// Compare with the provided scheme description
				if (schemeText.equalsIgnoreCase(SchemeDes.trim())) {
					// Scroll to the element if it's not in view
					JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
					js.executeScript("arguments[0].scrollIntoView(true);", row);

					// Click on the matching row (or a specific element within the row if needed)
					row.click();
					System.out.println("Scheme selected: " + schemeText);
					schemeFound = true;
					break;
				}
			}

			// If no matching scheme is found, throw an exception
			if (!schemeFound) {
				throw new RuntimeException("Scheme description '" + SchemeDes + "' not found in the table.");
			}
		} catch (Exception e) {
			System.err.println("Error in selecting Scheme: " + e.getMessage());
			throw new RuntimeException("Failed to select the scheme.", e);
		}
	}

	public void clickSubmitBtn() {
		try {
			SubmitBtn.click();
		} catch (Exception e) {
			System.err.println("Error in clicking Submit Button" + e.getMessage());
		}
	}

	public void clickClearBtn() {
		try {
			ClearBtn.click();
		} catch (Exception e) {
			System.err.println("Error in clicking clear button" + e.getMessage());
		}
	}

	public void closeExwarrentyTab() {
		try {
			Thread.sleep(2000);
			LaunchDriver.getDriver().switchTo().defaultContent();
			close.click();
		} catch (Exception e) {
			System.err.println("Error in clicking close button" + e.getMessage());
		}
	}

	public boolean isCustomerNotExistToastVisible() {
		try {
			// Wait for up to 3 seconds for the toast message to appear
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
			WebElement toastMessage = wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//p[contains(text(),'Customer No. does not exist. Please enter the CustomerNo')]"))); 																							// needed
			return toastMessage.isDisplayed();
		} catch (Exception e) {
			// Return false if the element is not found or visible within the timeout
			return false;
		}
	}

}