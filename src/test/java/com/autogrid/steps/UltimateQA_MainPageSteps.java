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

    private static LaunchDriver launchDriver;

    private static CommonActions commonAction = new CommonActions();

    public UltimateQA_MainPageSteps(CommonActions commonActions){
        UltimateQA_MainPageSteps.commonAction = commonActions;
        UltimateQA_MainPageSteps UltimateQA_MainPageLocators = new UltimateQA_MainPageSteps(commonAction);
        PageFactory.initElements(launchDriver.getDriver(), UltimateQA_MainPageLocators);
    }
    private static final By logo_UltimateQA = By.xpath("//*[@id='main-menu']//img");

    public static void launchQaSite(String url){
        launchDriver.launchSite(url);
    }

    public static void verifyLogoIsPresent(){
        commonAction.isElementPresent(logo_UltimateQA);
        logger.log(Level.INFO, "Site Logo is Displayed");
    }
}
