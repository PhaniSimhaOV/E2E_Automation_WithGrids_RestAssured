package com.autogrid.stepDefinitions;

import com.autogrid.hooks.Hooks;
import com.autogrid.steps.FlightRegistrationPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

public class FlightRegistrationStepDefinitions {

    private WebDriver driver;

    private FlightRegistrationPage flightRegistrationPage;

    LaunchDriver launchDriver;
    CommonActions commonActions;

    public FlightRegistrationStepDefinitions(){
        this.driver = LaunchDriver.getDriver();
        this.flightRegistrationPage = new FlightRegistrationPage(driver, launchDriver, commonActions);
        PageFactory.initElements(driver, flightRegistrationPage);
    }

    @Given("I am on the Flight Registration site url")
    public void i_am_on_the_flight_registration_site() {
        flightRegistrationPage.launchCustomerRegSiteSite();
    }

    @Then("Verify the presence of the Site Logo")
    public void verify_the_presence_of_the_site_logo() {
        flightRegistrationPage.verifyLogoIsPresent();
    }

    @Then("Verify site Header is present")
    public void verify_site_header_is_present() {
        flightRegistrationPage.verifySiteHeaderIsPresent();
    }
    @Then("Verify site description is present")
    public void verify_site_description_is_present() {
        flightRegistrationPage.verifySiteDescriptionIsPresent();
    }

    @Then("Enter first Name and Last Name")
    public void enter_first_name_and_last_name() {
        // Write code here that turns the phrase above into concrete actions
        flightRegistrationPage.enterFirstAndLastNames();
    }
    @Then("Enter Email address and password")
    public void enter_email_address_and_password() {
        // Write code here that turns the phrase above into concrete actions
        flightRegistrationPage.enterEmailAndPassword();
    }
    @Then("Enter address of the customer")
    public void enter_address_of_the_customer() {
        // Write code here that turns the phrase above into concrete actions
        flightRegistrationPage.enterUserAddress();
    }
    @Then("click on Register")
    public void click_on_register() {
        // Write code here that turns the phrase above into concrete actions
        flightRegistrationPage.clickOnRegisterButton();
    }


}
