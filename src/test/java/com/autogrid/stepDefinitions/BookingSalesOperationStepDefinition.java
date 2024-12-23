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
    public void userClicksOnBasedOnTheValue(String button) {
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
        getDriver().findElement(By.xpath("//input[@id='usrId']")).sendKeys("ACCOUNTS37");
    }

    @And("user enter a valid password for account")
    public void userEnterAValidPasswordForAccount() {
        getDriver().findElement(By.xpath("//input[@id='usrPswdNo']")).sendKeys("Creta@2023");
    }

    @And("User as to add the amount in the receipt section")
    public void userAsToAddTheAmountInTheReceiptSection() throws InterruptedException {
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
        String statusValue = getDriver().findElement(By.xpath("//*[@id=\"mainGrid\"]/div[3]/table/tbody/tr/td[24]")).getText();
        Assert.assertEquals(statusValue, "Pending");
    }

    @Then("User clicks on Order and stock")
    public void userClicksOnOrderAndStock() throws InterruptedException {
        Thread.sleep(3000);
        getDriver().findElement(By.xpath("//a[text()='Order/Stock']")).click();
    }

    @Then("user selects Dealer Vechile Stock MGT")
    public void userSelectsDealerVechileStockMGT() {
        getDriver().findElement(By.xpath("//*[@id=\"gnb\"]/li[3]/div/ul/li[4]/ul/li[4]/a")).click();
    }

    @And("User passes the VIN number into the field")
    public void userPassesTheVINNumberIntoTheField() {
        WebElement iframename = getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame2']"));
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(iframename);
        getDriver().findElement(By.xpath("//*[@id=\"sVin\"]")).sendKeys("MALB551CLRM614650");
//MALB341CYRM313126
        //MALB351CLRM593451
    }

    @And("Searches for the Vin number")
    public void searchesForTheVinNumber() {
        getDriver().findElement(By.xpath("//*[@class=\"btn_m btn_search k-button\"]")).click();
    }

    @When("Verify the data in the table with the customer booking values")
    public void verifyTheDataInTheTableWithTheCustomerBookingValues() throws InterruptedException {

    }

    @When("User selects Customer Booking Mgt List under sales Operation in accounts")
    public void userSelectsCustomerBookingMgtListUnderSalesOperationInAccounts() {
        getDriver().findElement(By.xpath("//*[@id=\"gnb\"]/li[2]/div/ul/li[3]/ul/li[1]/a ")).click();
    }

    @Then("verifies the value from the customer link")
    public void verifiesTheValueFromTheCustomerLink() throws InterruptedException {
        String VariantValue = getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[3]/table/tbody/tr/td[11]")).getText();
        String ExteriorColor = getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[3]/table/tbody/tr/td[15]")).getText();
        String InteriorColor = getDriver().findElement(By.xpath("//*[@id=\"grid\"]/div[3]/table/tbody/tr/td[17]")).getText();

        getDriver().switchTo().defaultContent();
        getDriver().findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/ul/li[3]")).click();
        bookingPage.SalesOperationLink();
        bookingPage.selectCustomerBookingMgtListMainLinks();
        WebElement iframename = getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']"));
        getDriver().switchTo().defaultContent();
        getDriver().switchTo().frame(iframename);
        getDriver().findElement(By.xpath("/html/body/section/div/section/section[1]/div[2]/dl[1]/dd[1]")).click();
        List<WebElement> options = getDriver().findElements(By.xpath("//ul[@id='dSearchCd_listbox']/li"));  // Replace with the actual XPath or locator
        WebElement selectedOption = options.get(0);
        selectedOption.click();
        System.out.println("Selected Option: " + selectedOption.getText());
        bookingPage.MobileNumberTextBox();
        bookingPage.BasedOnDropdown();
        bookingPage.SelectDates();
        bookingPage.SearchButton();
        bookingPage.SalesTable();
//
//        String CustExtColor = getDriver().findElement(By.xpath("//*[@aria-owns='extColorCd_listbox']/span")).getText();
//        String CustIntColor = getDriver().findElement(By.xpath("//span[@aria-owns='intColorCd_listbox']")).getText();
//        String CustVariant = getDriver().findElement(By.xpath("//span[@aria-owns='subVariantCd_listbox']")).getText();
//        Assert.assertEquals(VariantValue, CustVariant.contains(VariantValue));
//        Assert.assertEquals(ExteriorColor, CustExtColor);
//        Assert.assertEquals(InteriorColor, CustIntColor);
    }


    @Then("User clicks on the receipt icon for account")
    public void userClicksOnTheReceiptIconForAccount() {
                LaunchDriver.getDriver().switchTo().defaultContent();
              LaunchDriver.getDriver().switchTo().frame(getDriver().findElement(By.xpath("//iframe[@name='tabMenuFrame3']")));
        getDriver().findElement(By.xpath("//*[@id=\"receiptTab\"]")).click();
    }
}


