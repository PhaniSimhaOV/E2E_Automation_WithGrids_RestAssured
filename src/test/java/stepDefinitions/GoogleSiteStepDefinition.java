package stepDefinitions;

import io.cucumber.java.en.*;
import steps.GoogleSitePage;
import utils.CommonActions;
import utils.LaunchDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GoogleSiteStepDefinition {

    CommonActions commonActions;

    GoogleSitePage googleSitePage;

    public GoogleSiteStepDefinition(){
        WebDriver driver = LaunchDriver.getDriver();
        this.googleSitePage = new GoogleSitePage(driver);
        PageFactory.initElements(driver, googleSitePage);
    }

    @Given("I am on the Google site")
    public void i_am_on_the_google_site() throws InterruptedException {
        // Write code here that turns the phrase above into concrete actions
        googleSitePage.launchGoogleSite();
    }
    @Then("in the search field enter Whatever")
    public void in_the_search_field_enter() {
        // Write code here that turns the phrase above into concrete actions
        googleSitePage.sendQueryToSearchBox();
    }
    @Then("click on Search Button")
    public void click_on_search_button() {
        // Write code here that turns the phrase above into concrete actions
        googleSitePage.clickOnSearchButton();
    }
}
