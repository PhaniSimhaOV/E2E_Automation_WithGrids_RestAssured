package com.autogrid.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CommonActions {

    private WebDriver driver;
    public static CommonActions commonActions;
    private Wait<WebDriver> wait;

    private static Logger logger = LoggerFactory.getLogger(CommonActions.class);

    public CommonActions(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        PageFactory.initElements(driver, this);
    }

    public void selectValue(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        List<WebElement> options = new Select(element).getOptions();
        new Select(element).selectByIndex(new Random().nextInt(options.size()));
    }

    public void sendText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        assert element.isEnabled() && element.isDisplayed() : "The element either not enabled or Displayed. Please check";
        element.sendKeys(text);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        assert element.isEnabled() && element.isDisplayed() : "The element either not enabled or Displayed. Please check";
        element.click();
    }

    public void isElementPresent(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        assert element.isDisplayed() : "The element is not Present. Please check";
    }

}
