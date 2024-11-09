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
    private final CommonActions commonActions;
    private final LaunchDriver launchDriver;

    @FindBy(xpath = "//*[@id='mainNav']//a[@class='navbar-brand']")
    private WebElement siteLogo;

    @FindBy(xpath = "//*[@id='registration-section']//h2[text()='Customer Registration']")
    private WebElement siteHeader;

    @FindBy(xpath = "//*[@id='registration-section']//p[@class='mt-1']")
    private WebElement siteDescription;

    @FindBy(xpath = "//*[text()='First Name']/following-sibling::input")
    private WebElement formFirstName;

    @FindBy(xpath = "//*[text()='Last Name']/following-sibling::input")
    private WebElement formLastName;

    @FindBy(xpath = "//*[text()='Email']/following-sibling::input")
    private WebElement formEmail;

    @FindBy(xpath = "//*[text()='Password']/following-sibling::input")
    private WebElement formpassword;

    @FindBy(xpath = "//*[text()='Street']/following-sibling::input")
    private WebElement formStreet;

    @FindBy(xpath = "//*[text()='City']/following-sibling::input")
    private WebElement formCity;

    @FindBy(xpath = "//*[text()='Zip']/following-sibling::input")
    private WebElement formZip;

    @FindBy(xpath = "//*[text()='State']/following-sibling::select")
    private WebElement formState;

    @FindBy(xpath = "//*[@id='register-btn']")
    private WebElement formRegButton;

    public FlightRegistrationPage(WebDriver driver, LaunchDriver launchDriver){
        this.launchDriver = launchDriver;
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }

    public void launchCustomerRegSiteSite(){
        launchDriver.launchSite();
    }
    public void verifyLogoIsPresent(){
        commonActions.isElementPresent(siteLogo);
        logger.info("Site Logo is Displayed");
    }

    public void verifySiteHeaderIsPresent(){
        commonActions.isElementPresent(siteHeader);
        logger.info("Site Header is Present");
    }

    public void verifySiteDescriptionIsPresent(){
        commonActions.isElementPresent(siteDescription);
        logger.info("Site Description is Present");
    }

    public void enterFirstAndLastNames(){
        commonActions.sendText(formFirstName, new Faker().name().firstName());
        commonActions.sendText(formLastName, new Faker().name().lastName());
        logger.info("Customer First & Last Name has been provided");
    }

    public void enterEmailAndPassword(){
        commonActions.sendText(formEmail, new Faker().internet().emailAddress());
        commonActions.sendText(formpassword, "Broken#123");
        logger.info("Customer Email & Pwd has been taken");
    }

    public void enterUserAddress(){
        commonActions.sendText(formStreet, new Faker().address().streetName());
        commonActions.sendText(formCity, new Faker().address().city());
        commonActions.sendText(formZip, new Faker().address().zipCode());
        commonActions.selectValue(formState);
        logger.info("Customer Address has been Entered");
    }

    public void clickOnRegisterButton(){
        commonActions.clickElement(formRegButton);
        logger.info("Customer Registration Completed");
    }
}
