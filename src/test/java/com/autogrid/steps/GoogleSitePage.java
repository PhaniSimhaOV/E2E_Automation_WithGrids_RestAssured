package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleSitePage {
    private static final Logger logger = LoggerFactory.getLogger(GoogleSitePage.class);
    private final CommonActions commonActions;

    @FindBy(xpath = "//*[@name='q']")
    private WebElement searchBox;

    @FindBy(xpath = "//*[@name='btnG']")
    private WebElement searchButton;


    public GoogleSitePage(WebDriver driver){
        this.commonActions = new CommonActions(driver);
        PageFactory.initElements(driver, this);
    }

    public void launchGoogleSite() throws InterruptedException {
        LaunchDriver.launchSite();
    }

    public void sendQueryToSearchBox(){
        commonActions.sendText(searchBox, "Whatever");
    }

    public void clickOnSearchButton(){
        commonActions.clickElement(searchButton);
    }

}
