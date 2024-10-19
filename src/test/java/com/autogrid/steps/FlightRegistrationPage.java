package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightRegistrationPage {
    private static final Logger logger = LoggerFactory.getLogger(FlightRegistrationPage.class);
    final private CommonActions commonActions;
    final private LaunchDriver launchDriver;

    @FindBy(xpath = "//*[@id='mainNav']//a[@class='navbar-brand']")
    private WebElement site_logo;

    @FindBy(xpath = "//*[@id='registration-section']//h2[text()='Customer Registration']")
    private WebElement site_Header;

    @FindBy(xpath = "//*[@id='registration-section']//p[@class='mt-1']")
    private WebElement site_Description;

    @FindBy(xpath = "//*[text()='First Name']/following-sibling::input")
    private WebElement form_FirstName;

    @FindBy(xpath = "//*[text()='Last Name']/following-sibling::input")
    private WebElement form_LastName;

    @FindBy(xpath = "//*[text()='Email']/following-sibling::input")
    private WebElement form_Email;

    @FindBy(xpath = "//*[text()='Password']/following-sibling::input")
    private WebElement form_password;

    @FindBy(xpath = "//*[text()='Street']/following-sibling::input")
    private WebElement form_Street;

    @FindBy(xpath = "//*[text()='City']/following-sibling::input")
    private WebElement form_City;

    @FindBy(xpath = "//*[text()='Zip']/following-sibling::input")
    private WebElement form_Zip;

    @FindBy(xpath = "//*[text()='State']/following-sibling::select")
    private WebElement form_State;

    @FindBy(xpath = "//*[@id='register-btn']")
    private WebElement form_RegButton;

    public FlightRegistrationPage(WebDriver driver, LaunchDriver launchDriver){
        this.launchDriver = launchDriver;
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }

    public void launchCustomerRegSiteSite(){
        launchDriver.launchSite();
    }
    public void verifyLogoIsPresent(){
        commonActions.isElementPresent(site_logo);
        logger.info("Site Logo is Displayed");
    }

    public void verifySiteHeaderIsPresent(){
        commonActions.isElementPresent(site_Header);
        logger.info("Site Header is Present");
    }

    public void verifySiteDescriptionIsPresent(){
        commonActions.isElementPresent(site_Description);
        logger.info("Site Description is Present");
    }

    public void enterFirstAndLastNames(){
        commonActions.sendText(form_FirstName, new Faker().name().firstName());
        commonActions.sendText(form_LastName, new Faker().name().lastName());
        logger.info("Customer First & Last Name has been provided");
    }

    public void enterEmailAndPassword(){
        commonActions.sendText(form_Email, new Faker().internet().emailAddress());
        commonActions.sendText(form_password, "Broken#123");
        logger.info("Customer Email & Pwd has been taken");
    }

    public void enterUserAddress(){
        commonActions.sendText(form_Street, new Faker().address().streetName());
        commonActions.sendText(form_City, new Faker().address().city());
        commonActions.sendText(form_Zip, new Faker().address().zipCode());
        commonActions.selectValue(form_State);
        logger.info("Customer Address has been Entered");
    }

    public void clickOnRegisterButton(){
        commonActions.clickElement(form_RegButton);
        logger.info("Customer Registration Completed");
    }
}
