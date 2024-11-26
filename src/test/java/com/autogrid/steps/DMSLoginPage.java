package com.autogrid.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;

public class DMSLoginPage {
	WebDriver driver;
	private static final Logger logger = LoggerFactory.getLogger(DMSLoginPage.class);
    private final CommonActions commonActions;
    
    @FindBy(xpath = "//input[@id='usrId']")
    private WebElement UserID;
    
    @FindBy(xpath = "//input[@id='usrPswdNo']")
    private WebElement Password;   
    
    @FindBy(xpath = "//div[@id='errorMessageContext']")
    private WebElement UserIDValidation;
    
    @FindBy(xpath = "//div[@id='errorMessageContext']")
    private WebElement PasswordValidation;   
    
    @FindBy(xpath = "//input[@id='nextFollowUpDate']")
    private WebElement LoginToast;  
    
    @FindBy(xpath = "//h1[normalize-space()='NDMS New Dealer Management System']")
    private WebElement LoginHeader;
    
    @FindBy(xpath = "//input[@id='nextFollowUpDate']")
    private WebElement DashboardHeader;
    
    @FindBy(xpath = "//div[@id='errorMessageContext']")
    private WebElement IDPasswordValidation;
    
    @FindBy(xpath = "//button[@id='btnGenerateOtp']")
    private WebElement SendOTPButton;
    
    @FindBy(xpath = "//input[@id='otpEnter']")
    private WebElement EnterOTP;
    
    @FindBy(xpath = "//div[@id='errorMessageContext']")
    private WebElement EnterOTPValidation;
    
    @FindBy(xpath = "//div[@id='errorMessageContext']")
    private WebElement RegenrateOTPValidation;
    
    @FindBy(xpath = "//input[@id='rememberYn']")
    private WebElement SaveIDCheckBox;
    
    @FindBy(xpath = "//button[@id='btnLoginClickGdmsNew']")
    private WebElement LoginButton;
    
    @FindBy(xpath = "//a[@id='passPopupOpen']")
    private WebElement ForgotPasswordLink;
       
    public DMSLoginPage(WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
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
    
    public void clickLoginButton() {
        try {
            LoginButton.click();
        } catch (Exception e) {
            System.err.println("Error clicking login button: " + e.getMessage());
            throw e;
        }
    }
    
    public void clickSendOTPButton() {
        try {
        	SendOTPButton.click();
        } catch (Exception e) {
            System.err.println("Error clicking Send OTP button: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to retrieve the validation message for username
    public String getUsernameValidationMessage() {
        try {
            return UserIDValidation.getText();
        } catch (Exception e) {
            System.err.println("Error retrieving username validation message: " + e.getMessage());
            throw e;
        }   
    }
    
 // Action to retrieve the validation message for Password
    public String getPasswordValidationMessage() {
        try {
            return PasswordValidation.getText();
        } catch (Exception e) {
            System.err.println("Error retrieving Password validation message: " + e.getMessage());
            throw e;
        }   
    }
    
 // Action to enter username
    public void enterUsername(String username) {
        try {
        	UserID.clear(); // Clear the field if necessary
        	UserID.sendKeys(username);
        } catch (Exception e) {
            System.err.println("Error entering username: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to get the value in the username field
    public String getUsernameFieldValue() {
        try {
            return UserID.getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error retrieving username field value: " + e.getMessage());
            throw e;
        }
    }
 // Action to enter Password
    public void enterPassword(String password) {
        try {
        	Password.clear(); // Clear the field if necessary
        	Password.sendKeys(password);
        } catch (Exception e) {
            System.err.println("Error entering username: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to get the value in the password field
    public String getPasswordFieldValue() {
        try {
            return Password.getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error retrieving password field value: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to enter OTP
    public void enterOTP(String otp) {
        try {
        	EnterOTP.clear(); // Clear the field if necessary
        	EnterOTP.sendKeys(otp);
        } catch (Exception e) {
            System.err.println("Error entering OTP: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to get the value in the otp field
    public String getOTPFieldValue() {
        try {
            return EnterOTP.getAttribute("value");
        } catch (Exception e) {
            System.err.println("Error retrieving OTP field value: " + e.getMessage());
            throw e;
        }
    }
    
 // Action to retrieve the validation message to Enter OTP
    public String getEnterOTPValidationMessage() {
        try {
            return EnterOTPValidation.getText();
        } catch (Exception e) {
            System.err.println("Error retrieving Enter OTP validation message: " + e.getMessage());
            throw e;
        }   
    }

}
