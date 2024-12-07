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
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.autogrid.utils.LaunchDriver.getDriver;

public class BookingSalesOperationStepDefinition {
    CommonActions commonActions;

    BookingSalesOperationPage bookingPage;

    public BookingSalesOperationStepDefinition() {
        WebDriver driver = getDriver();
        this.bookingPage = new BookingSalesOperationPage(driver);
        PageFactory.initElements(driver, bookingPage);
    }

    @Given("User clicks on the Sales icon")
    public void userClicksOnTheSalesIcon() throws InterruptedException {
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

    @Then("User fills the fields in the Customer Booking MGT")
    public void userFillsTheFieldsInTheCustomerBookingMGT() {
        bookingPage.fillfieldsBookingPage();
    }

    @And("After successful registration user clicks on Quotation")
    public void afterSuccessfulRegistrationUserClicksOnQuotation() {
        bookingPage.QuotationPage();

    }

    @Then("User clicks on the receipt icon")
    public void userClicksOnTheReceiptIcon() {
        bookingPage.ReceiptTab();

    }

    @When("user enters a valid username for account")
    public void userEntersAValidUsernameForAccount() {
    getDriver().findElement(By.xpath("//input[@id='usrId']")).sendKeys("ACCOUNTS37");
    }

    @And("user enter a valid password for account")
    public void userEnterAValidPasswordForAccount() {
        getDriver().findElement(By.xpath("//input[@id='usrPswdNo']")).sendKeys("Creta@2023");
    }

    @And("User as to add the amount in the receipt section")
    public void userAsToAddTheAmountInTheReceiptSection() {
        bookingPage.AmountReceiptPage();

    }

    @Then("User clicks on Send Customer consent link")
    public void userClicksOnSendCustomerConsentLink() {
        getDriver().findElement(By.xpath("//*[text()='Send Customer Consent Link']")).click();
        getDriver().findElement(By.xpath("/html/body/div[116]/div[2]/p[2]/button[1]")).click();
    }

    @Then("Verify the status in the Customer booking list should be pending")
    public void verifyTheStatusInTheCustomerBookingListShouldBePending() {
        getDriver().findElement(By.xpath("//*[text()=\"Customer Booking Mgt List\" and @class='k-link']")).click();
        String statusValue=getDriver().findElement(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td[24]")).getText();
        Assert.assertEquals(statusValue,"Pending");
    }
}
