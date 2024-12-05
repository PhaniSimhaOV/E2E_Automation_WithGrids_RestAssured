package com.autogrid.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.autogrid.utils.CommonActions;

public class BookingSalesOperationPage {

    WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(com.autogrid.steps.DMSLoginPage.class);
    private final CommonActions commonActions;

    @FindBy(xpath = "//a[text()='Sales']")
    private WebElement SalesIcon;
    @FindBy(xpath = "/a[text()='Sales Operation']")
    private WebElement SalesOperationButton;
    @FindBy(xpath = "//a[@data-url='/sal/salg/selectCustomerBookingMgtListMain.dms']")
    private WebElement selectCustomerBookingMgtListMainLinks;
    @FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[1]/dd[1]/span/span")
    private WebElement dropdownDateOf;
    @FindBy(xpath = "//*[@id='34ea21dd-aa41-461c-83a1-11b6bd64ecad']")
    private WebElement SelectEnqiryDropdown;
    @FindBy(xpath = "//input[@id=\"baseTxt\"]")
    private WebElement MobileNoTextField;
    @FindBy(xpath = "//*[@id=\"content\"]/section[1]/div[2]/dl[2]/dd[1]/span/span")
    private WebElement BasedOnDropdown;
    @FindBy(xpath = "//*[@id=\"baseStatCd_listbox\"]/li[4]")
    private WebElement BasedOnMobileDropdown;
    @FindBy(xpath = "//*[@id='sStartDt']")
    private WebElement StartDate;
    @FindBy(xpath = "//*[@id='//*[@id=\"sEndDt\"]")
    private WebElement EndDate;
    @FindBy(xpath = "  //button[text()=\"Search\"]")
    private WebElement SearchButton;
    @FindBy(xpath = "//*[@id=\"mainGrid\"]/div[3]/table/tbody")
    private WebElement SalesTable;

    public BookingSalesOperationPage(WebDriver driver) {
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }

    public void SalesIconButton() {
        try {
            SalesIcon.click();
        } catch (Exception e) {
            System.err.println("Error in Clicking the Sales Icon : " + e.getMessage());
            throw e;
        }
    }
public void SalesTable(){
    try {
        for(int i=0;i<=1;i++){
            SalesTable.click();   }
    } catch (Exception e) {
        System.err.println("Error in Clicking the row of data: " + e.getMessage());
        throw e;
    }

}
    public void SalesOperationLink() {
        try {
            SalesOperationButton.click();
        } catch (Exception e) {
            System.err.println("Error in Clicking the SalesOperation Button  : " + e.getMessage());
            throw e;
        }
    }

    public void selectCustomerBookingMgtListMainLinks() {
        try {
            selectCustomerBookingMgtListMainLinks.click();
        } catch (Exception e) {
            System.err.println("Error in Clicking the select Customer BookingMgt ListMain Links: " + e.getMessage());
            throw e;
        }
    }

    public void selectDateOFDropdown() {
        try {
            dropdownDateOf.click();
            SelectEnqiryDropdown.click();

        } catch (Exception e) {
            System.err.println("Error in Selecting the enquiry from the dropdown: " + e.getMessage());
            throw e;
        }
    }

    public void MobileNumberTextBox() {
        try {
            MobileNoTextField.sendKeys("7799222422");
        } catch (Exception e) {
            System.err.println("Error in passing the mobile number to the text field: " + e.getMessage());
            throw e;
        }
    }

    public void BasedOnDropdown() {
        try {
            BasedOnDropdown.click();
            BasedOnMobileDropdown.click();
        } catch (Exception e) {
            System.err.println("Error in selecting the BasedOnDropdown : " + e.getMessage());
            throw e;
        }
    }

    public void SearchButton() {
        try {
            SearchButton.click();

        } catch (Exception e) {
            System.err.println("Error in clicking the search button : " + e.getMessage());
            throw e;
        }
    }

    public void SelectDates() {
        try {
            StartDate.sendKeys("01102024");
            EndDate.sendKeys("30112024");
        } catch (Exception e) {
            System.err.println("Error in passing the dates: " + e.getMessage());
            throw e;
        }
    }
    public void fillfieldsBookingPage(){

    }
}
