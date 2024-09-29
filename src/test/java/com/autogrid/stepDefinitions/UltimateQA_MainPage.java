package com.autogrid.stepDefinitions;

import io.cucumber.java.en.*;

public class UltimateQA_MainPage {

    @Given("I am on the Ultimate QA site")
    public void i_am_on_the_ultimate_qa_site() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Landed on QA Site");
    }
    @Then("Verify the presence of the Site Logo")
    public void verify_the_presence_of_the_site_logo() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Logo is present");
    }
}
