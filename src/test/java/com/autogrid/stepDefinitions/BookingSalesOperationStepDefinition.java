package com.autogrid.stepDefinitions;

import com.autogrid.steps.BookingSalesOperationPage;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BookingSalesOperationStepDefinition {
    CommonActions commonActions;

    BookingSalesOperationPage bookingPage;

    public BookingSalesOperationStepDefinition(){
        WebDriver driver = LaunchDriver.getDriver();
        this.bookingPage = new BookingSalesOperationPage(driver);
        PageFactory.initElements(driver, bookingPage);
    }
    @Given("User clicks on the Sales icon")
    public void userClicksOnTheSalesIcon() {
        bookingPage.SalesIconButton();
    }

    @Then("User selects the Sales Operation tab")
    public void userSelectsTheSalesOperationTab() {
        bookingPage.SalesOperationLink();
    }

    @When("User selects Customer Booking Mgt List under sales Operation")
    public void userSelectsCustomerBookingMgtListUnderSalesOperation() {
        bookingPage.selectCustomerBookingMgtListMainLinks();
    }

    @Then("User need to select the enquiry option in the dropdown")
    public void userNeedToSelectTheEnquiryOptionInTheDropdown() {
        bookingPage.selectDateOFDropdown();
    }

    @And("User enters the mobile number in the text box")
    public void userEntersTheMobileNumberInTheTextBox() {
        bookingPage.MobileNumberTextBox();
    }

    @And("User selects the mobile number option from the dropdown")
    public void userSelectsTheMobileNumberOptionFromTheDropdown() {
        bookingPage.BasedOnDropdown();
    }

    @Then("User clicks on the search button")
    public void userClicksOnTheSearchButton() {
        bookingPage.SearchButton();
    }

    @And("User passed the start date and end date in the page")
    public void userPassedTheStartDateAndEndDateInThePage() {
   bookingPage.SelectDates();
    }

    @When("The enquiry will be populated then user as to select it")
    public void theEnquiryWillBePopulatedThenUserAsToSelectIt() {
        bookingPage.SalesTable();
    }
}
