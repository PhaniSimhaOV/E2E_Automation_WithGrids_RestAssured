package com.autogrid.stepDefinitions;

import com.autogrid.steps.BookingSalesOperationPage;
import com.autogrid.steps.DMSLoginPage;
import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.awt.*;
import java.util.List;

import static com.autogrid.utils.LaunchDriver.getDriver;

public class BookingSalesOperationStepDefinition {
    CommonActions commonActions;

    BookingSalesOperationPage bookingPage;

    public BookingSalesOperationStepDefinition() throws Exception {
        WebDriver driver = getDriver();
        this.bookingPage = new BookingSalesOperationPage(driver);
        PageFactory.initElements(driver, bookingPage);
    }

    @Given("User clicks on the Sales icon")
    public void userClicksOnTheSalesIcon() throws InterruptedException {
        bookingPage.SalesIconButton();
        Thread.sleep(3000);
    }

    @Then("User selects the Sales Operation tab")
    public void userSelectsTheSalesOperationTab() throws InterruptedException {
        bookingPage.SalesOperationLink();
    }

    @When("User selects Customer Booking Mgt List under sales Operation")
    public void userSelectsCustomerBookingMgtListUnderSalesOperation() {
        bookingPage.selectCustomerBookingMgtListMainLinks();
    }

    @Then("User need to select the enquiry option in the dropdown")
    public void userNeedToSelectTheEnquiryOptionInTheDropdown() throws InterruptedException {
        bookingPage.selectDateOFDropdown();
    }

    @And("User enters the mobile number in the text box")
    public void userEntersTheMobileNumberInTheTextBox() {

        bookingPage.MobileNumberTextBox();
    }

    @And("User selects the mobile number option from the dropdown")
    public void userSelectsTheMobileNumberOptionFromTheDropdown() throws InterruptedException {
        bookingPage.BasedOnDropdown();
    }

    @Then("User clicks on the search button")
    public void userClicksOnTheSearchButton() {
        bookingPage.SearchButton();
    }

    @And("User passed the start date and end date in the page")
    public void userPassedTheStartDateAndEndDateInThePage() throws InterruptedException {
        bookingPage.SelectDates();
    }

    @When("The enquiry will be populated then user as to select it")
    public void theEnquiryWillBePopulatedThenUserAsToSelectIt() {
        bookingPage.SalesTable();
    }

    @Then("User fills the fields in the Customer Booking MGT")
    public void userFillsTheFieldsInTheCustomerBookingMGT() throws InterruptedException, AWTException {
        bookingPage.fillfieldsBookingPage();
    }

    @Then("user clicks on {string} based on the value")
    public void userClicksOnBasedOnTheValue(String button) throws InterruptedException {
        bookingPage.clickbutton(button);
    }

    @And("After successful registration user clicks on Quotation")
    public void afterSuccessfulRegistrationUserClicksOnQuotation() throws InterruptedException {
        Thread.sleep(7000);
        bookingPage.QuotationPage();

    }

    @Then("User clicks on the receipt icon")
    public void userClicksOnTheReceiptIcon() throws InterruptedException {
        bookingPage.ReceiptTab();

    }

    @When("user enters a valid username for account")
    public void userEntersAValidUsernameForAccount() {
        bookingPage.AcocuntLoginUseraname();

    }

    @And("user enter a valid password for account")
    public void userEnterAValidPasswordForAccount() {
        bookingPage.AccountLoginPassword();
    }

    @And("User as to add the amount in the receipt section")
    public void userAsToAddTheAmountInTheReceiptSection() throws InterruptedException {
        bookingPage.AmountReceiptPage();

    }

    @Then("User clicks on Send Customer consent link")
    public void userClicksOnSendCustomerConsentLink() {
        bookingPage.SendConsentLink();
    }

    @Then("Verify the status in the Customer booking list should be pending")
    public void verifyTheStatusInTheCustomerBookingListShouldBePending() {
        bookingPage.VerifyConscentLink();

    }

    @Then("User clicks on Order and stock")
    public void userClicksOnOrderAndStock() throws InterruptedException {
        bookingPage.orderStock();
    }

    @Then("user selects Dealer Vechile Stock MGT")
    public void userSelectsDealerVechileStockMGT() throws InterruptedException {
    bookingPage.DealerVechileStock();
    }

    @And("User passes the VIN number into the field")
    public void userPassesTheVINNumberIntoTheField() {
      bookingPage.vinNumber();
    }

    @And("Searches for the Vin number")
    public void searchesForTheVinNumber() {
       bookingPage.vinSearch();
    }

    @When("Verify the data in the table with the customer booking values")
    public void verifyTheDataInTheTableWithTheCustomerBookingValues() throws InterruptedException {

    }

    @When("User selects Customer Booking Mgt List under sales Operation in accounts")
    public void userSelectsCustomerBookingMgtListUnderSalesOperationInAccounts() throws InterruptedException {
        bookingPage.mgtListSales();
    }

    @Then("verifies the value from the customer link")
    public void verifiesTheValueFromTheCustomerLink() throws InterruptedException {
        bookingPage.verifyDataMGT();
    }


    @Then("User clicks on the receipt icon for account")
    public void userClicksOnTheReceiptIconForAccount() {
       bookingPage.receiptLink();
    }


}


