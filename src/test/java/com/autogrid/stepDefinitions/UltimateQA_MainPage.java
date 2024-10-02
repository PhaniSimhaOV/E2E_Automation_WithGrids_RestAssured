package com.autogrid.stepDefinitions;

import com.autogrid.steps.UltimateQA_MainPageSteps;
import io.cucumber.java.en.*;

public class UltimateQA_MainPage {

    UltimateQA_MainPageSteps mainPage;

    @Given("I am on the Ultimate QA site {string}")
    public void i_am_on_the_ultimate_qa_site(String url) {
        mainPage.launchQaSite(url);
    }
    @Then("Verify the presence of the Site Logo")
    public void verify_the_presence_of_the_site_logo() {
        mainPage.verifyLogoIsPresent();
    }
}
