package com.autogrid.steps;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.LaunchDriver;

public class ExWarrantyPage {
	private static final Logger logger = LoggerFactory.getLogger(ExWarrantyPage.class);
	private final CommonActions commonActions;
	private final WebDriver driver;
    private final String featureName = "ExWarranty Screen Locators"; // Updated for ExWarranty Page

	public ExWarrantyPage(WebDriver driver) {
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
	
	public void clickServiceIcon() {
		try {
			waitForVisibilityOfElement(getElement("ServiceIcon"));
			getElement("ServiceIcon").click();
		} catch (Exception e) {
			System.err.println("Failed to click Service Icon" + e.getMessage());
		}
	}

	public void clickExtWarrantySubMenu() {
		try {
			getElement("ExtendedWarrantySubMenu").click();
		} catch (Exception e) {
			System.err.println("Failed to click Extended Warranty Sub Menu" + e.getMessage());
		}
	}

	public void clickOnExtdWarrantySubmitLink() {
		try {
			getElement("ExtendedWarrantySubmitLink").click();
		} catch (Exception e) {
			System.err.println("Failed to click Extended WArranty Submit Link" + e.getMessage());
		}
	}

//method to check if ExtendedWarrantySubmitHeader is displayed
	public boolean extdWarrantySbtHeaderisDisplayed() {
		try {
			return getElement("ExtendedWarrantySubmitHeader").isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void interactWithIframeExtW() {
		try {
			LaunchDriver.getDriver().switchTo().frame(getElement("iFrameForExtWarranty"));
			System.out.println("Successfully interacted with iFrame of ExtWarranty");
		} catch (Exception e) {
			System.err.println("Error in interacting with iFrame of ExWarranty" + e.getMessage());
		}
	}

	public void enterVIN(String vin) {
		try {
			waitForVisibilityOfElement(getElement("VIN"));
			getElement("VIN").clear();
			getElement("VIN").sendKeys(vin);
		} catch (Exception e) {
			System.err.println("Error in entering VIN " + e.getMessage());
		}
	}

	public void clickOnInquire() {
		try {
			getElement("InquireIcon").click();
		} catch (Exception e) {
			System.err.println("Error in clicking Inquire icon" + e.getMessage());
		}
	}

	public void enterCurrentOdoMtrReading(String odoMtrReading) {
		try {
			waitForVisibilityOfElement(getElement("OdometerReading"));
			if (getElement("OdometerReading").isDisplayed()) {
				JavascriptExecutor js = (JavascriptExecutor) LaunchDriver.getDriver();
				js.executeScript("arguments[0].value='" + odoMtrReading + "';", getElement("OdometerReading"));
			} else {
				System.out.println(getElement("OdometerReading") + "Element is not visible");
			}
		} catch (Exception e) {
			System.err.println("Error in entering OdomtrReading" + e.getMessage());
		}
	}

	public void selectEmployeeName(String employeeName) throws Throwable {
		getElement("EmployeeNameDrpDwn").click();
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
		} catch (Exception e) {
			System.err.println("Error in selecting employee Name" + e.getMessage());
		}
	}

	public void selectPlaceOfSupply(String placeOfSupply) throws Throwable {
		getElement("PlaceOfSupplyDrpDwn").click();
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
			getElement("SubmitBtn").click();
		} catch (Exception e) {
			System.err.println("Error in clicking Submit Button" + e.getMessage());
		}
	}

	public void clickClearBtn() {
		try {
			getElement("ClearBtn").click();
		} catch (Exception e) {
			System.err.println("Error in clicking clear button" + e.getMessage());
		}
	}

	public void closeExwarrentyTab() {
		try {
			Thread.sleep(2000);
			LaunchDriver.getDriver().switchTo().defaultContent();
			getElement("close").click();
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
