package com.autogrid.steps;

import com.autogrid.utils.ExcelReading;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.Map;

public class DMSLoginPage {
    WebDriver driver;
    private final CommonActions commonActions;
    private final String featureName = "Login Screen Locators"; // Change this for different features

    public DMSLoginPage(WebDriver driver) {
        this.driver = driver;
        this.commonActions = new CommonActions(driver);
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

    public void launchDMSSite() throws InterruptedException {
        LaunchDriver.launchSite();
    }

 // Method to get the page title
    public String getPageTitle() {
        return LaunchDriver.getDriver().getTitle();
    }
    
 // Method to verify the page title
    public boolean verifyPageTitle(String expectedTitle) {
        return LaunchDriver.getDriver().getTitle().equals(expectedTitle);
    }
    
    public void clickLoginButton() throws Throwable {
        try {
        	getElement("LoginButton").click();
        } catch (Exception e) {
            System.err.println("Error clicking login button: " + e.getMessage());
            throw e;
        }
    }
    
    public void clickSendOTPButton() throws Throwable {
        try {
        	getElement("SendOTPButton").click();
        } catch (Exception e) {
            System.err.println("Error clicking Send OTP button: " + e.getMessage());
            throw e;
        }
    }
    public void ClicksForgotPasswordLink() throws Throwable {
        try {
        	getElement("ForgotPasswordLink").click();
        } catch (Exception e) {
            System.err.println("Error clicking Forgot Password Link: " + e.getMessage());
            throw e;
        }
    }
    
    public void ClicksSaveIDCheckBox() throws Throwable {
        try {
        	getElement("SaveIDCheckBox").click();
        } catch (Exception e) {
            System.err.println("Error clicking Save ID Check Box: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to retrieve the validation message for username
    public String getUsernameValidationMessage() throws Throwable {
        try {
            return getElement("UserIDValidation").getText();
        } catch (Exception e) {
            System.err.println("Error retrieving username validation message: " + e.getMessage());
            throw e;
        }   
    }
    
 // Action to retrieve the validation message for Password
    public String getPasswordValidationMessage() throws Throwable {
        try {
            return getElement("PasswordValidation").getText();
        } catch (Exception e) {
            System.err.println("Error retrieving Password validation message: " + e.getMessage());
            throw e;
        }   
    }
    
 // Action to enter username
    public void enterUsername(String username) throws Throwable {
        try {
            WebElement userField = getElement("UserID");
            userField.clear(); // Clear the field if necessary
            userField.sendKeys(username);
        } catch (Exception e) {
            System.err.println("Error entering username: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to get the value in the username field
    public String getUsernameFieldValue() throws Throwable {
        try {
            return getElement("UserID").getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error retrieving username field value: " + e.getMessage());
            throw e;
        }
    }
 // Action to enter Password
    public void enterPassword(String password) throws Throwable {
        try {
            WebElement passwordField = getElement("Password");
            passwordField.clear(); // Clear the field if necessary
            passwordField.sendKeys(password);
        } catch (Exception e) {
            System.err.println("Error entering username: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to get the value in the password field
    public String getPasswordFieldValue() throws Throwable {
        try {
            return getElement("Password").getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error retrieving password field value: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to enter OTP
    public void enterOTP(String otp) throws Throwable {
        try {
            WebElement EnterOTPField = getElement("EnterOTP");
            EnterOTPField.clear(); // Clear the field if necessary
            EnterOTPField.sendKeys(otp);
        } catch (Exception e) {
            System.err.println("Error entering OTP: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to get the value in the otp field
    public String getOTPFieldValue() throws Throwable {
        try {
            return getElement("EnterOTP").getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error retrieving OTP field value: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to retrieve the validation message to Enter OTP
    public String getEnterOTPValidationMessage() throws Throwable {
        try {
            return getElement("EnterOTPValidation").getText();
        } catch (Exception e) {
            System.err.println("Error retrieving Enter OTP validation message: " + e.getMessage());
            throw e;
        }   
    }
 // Method to check if Homepage Icon is displayed
    public boolean isHomepageIconDisplayed() throws Throwable {
        try {
            return getElement("HomepageIcon").isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
 // Method to check if Forgot Password Link is displayed
    public boolean isForgotPasswordLinkDisplayed() throws Throwable {
        try {
            return getElement("ForgotPasswordLink").isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
 // Method to check if Save ID CheckBox is displayed
    public boolean isSaveIDCheckBoxDisplayed() throws Throwable {
        try {
            return getElement("SaveIDCheckBox").isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
 // Method to check Save ID checkbox
    public void checkSaveIDCheckbox() throws Throwable {
        try {
            if (!getElement("SaveIDCheckBox").isSelected()) {
            	getElement("SaveIDCheckBox").click();
                System.out.println("Save ID checkbox is clicked.");
            } else {
                System.out.println("Save ID checkbox is already checked.");
            }
        } catch (Exception e) {
            System.err.println("Error interacting with Save ID checkbox: " + e.getMessage());
            throw e;
        }
    }

    // Method to verify if Save ID checkbox is checked
    public boolean isSaveIDCheckboxChecked() throws Throwable {
        try {
            return getElement("SaveIDCheckBox").isSelected();
        } catch (Exception e) {
            System.err.println("Error verifying Save ID checkbox: " + e.getMessage());
            throw e;
        }
    }
    
 // Method to check if Forgot Password pop-up is displayed
    public boolean isForgotPasswordPopupDisplayed() {
        try {
            return getElement("ForgotPasswordPopup").isDisplayed();
        } catch (Exception e) {
            System.err.println("Error checking Forgot Password pop-up visibility: " + e.getMessage());
            return false;
        }
    }

    public WebElement getEnterOTP() throws Throwable {
		return getElement("EnterOTP");
	}
}
