package com.autogrid.steps;

import com.autogrid.utils.CommonActions;
import com.autogrid.utils.LaunchDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UltimateQA_MainPageSteps {
    static Logger logger = Logger.getLogger(UltimateQA_MainPageSteps.class.getName());
    public static LaunchDriver launchDriver;
    private static CommonActions commonAction = new CommonActions();
    public UltimateQA_MainPageSteps(CommonActions commonActions){
        UltimateQA_MainPageSteps.commonAction = commonActions;
        UltimateQA_MainPageSteps UltimateQA_MainPageLocators = new UltimateQA_MainPageSteps(commonAction);
        PageFactory.initElements(LaunchDriver.getDriver(), UltimateQA_MainPageLocators);
    }
    private static final By logo_UltimateQA = By.xpath("//*[@id='main-menu']//img");

    private static final By fakeLandingPage = By.xpath("//a[contains(text(),'Fake Landing Page')]");
    public static void launchQaSite(String url){
        launchDriver.launchSite(url);
    }
    public static void verifyLogoIsPresent(){
        commonAction.isElementPresent(logo_UltimateQA);
        logger.log(Level.INFO, "Site Logo is Displayed");
    }

    public static void verifyFakeLandingPageLinkIsPresent(){
        commonAction.isLinkPresent(fakeLandingPage);
        logger.log(Level.INFO, "Fake Site Link is Present");
    }
}
