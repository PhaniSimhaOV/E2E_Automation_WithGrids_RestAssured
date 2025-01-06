package com.autogrid.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CommonActionsAndroid {
    private static final Logger logger = LoggerFactory.getLogger(CommonActionsAndroid.class);

    Wait<AppiumDriver> wait;

    public CommonActionsAndroid(AppiumDriver appiumDriver) {
        this.wait = new FluentWait<>(appiumDriver)
                .withTimeout(Duration.ofSeconds(60))
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        PageFactory.initElements(appiumDriver, this);
    }

    public void sendText(WebElement element, String text) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fluentWaitForVisibility(element);
        assert element.isEnabled() && element.isDisplayed() : "The element either not enabled or Displayed. Please check";
        element.sendKeys(text);
        logger.info("{} value has been entered", text);
    }

    public void clickElement(WebElement element) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new WebDriverWait(LaunchAndroidDriver.getAndroidDriver(), Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(element));
        assert element.isEnabled() && element.isDisplayed() : "The element either not enabled or Displayed. Please check";
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        element.click();
        logger.info("The required Button/Link has been clicked");
    }

    public String getElementText(WebElement element) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fluentWaitForVisibility(element);
        assert element.isDisplayed() : "The element is not Present. Please check";
        return element.getText();
    }

    public void sendTextAndSelectValues(String value){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new WebDriverWait(LaunchAndroidDriver.getAndroidDriver(), Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//android.widget.TextView[@resource-id='android:id/text1' and contains(@text, '%s')]", value))));

        LaunchAndroidDriver.getAndroidDriver().findElement(AppiumBy
                .xpath(String.format(String.format("//android.widget.TextView[@resource-id='android:id/text1' and contains(@text, '%s')]", value)))).click();

    }

    public void selectApkDropdownValue(WebElement element, String value){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        clickElement(element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='%s']", value))));
        LaunchAndroidDriver.getAndroidDriver().findElement(AppiumBy
                .xpath(String.format("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='%s']", value))).click();
    }

    private void fluentWaitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void navigateBack() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) LaunchAndroidDriver.getAndroidDriver();
        jsExecutor.executeScript("mobile: pressKey",
                new Object[] {
                        new java.util.HashMap<String, Object>() {{
                            put("keycode", 4);  // Keycode 4 represents the back button on Android
                        }}
                });
    }

    public void scrollDown() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RemoteWebElement scrollView = (RemoteWebElement) new WebDriverWait(LaunchAndroidDriver.getAndroidDriver(),
                Duration.ofSeconds(120)).until(presenceOfElementLocated(AppiumBy.xpath("//android.widget.ScrollView")));

        LaunchAndroidDriver.getAndroidDriver().executeScript("gesture: swipe", Map.of("elementId", scrollView.getId(),
                "percentage", 10,
                "direction", "up"));
    }

    public void scrollDownToElement(String carName) throws InterruptedException {
        Thread.sleep(2000);

        RemoteWebElement scrollView = (RemoteWebElement) wait.until(presenceOfElementLocated(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView")));

        LaunchAndroidDriver.getAndroidDriver().executeScript("gesture: scrollElementIntoView", Map.of("scrollableView", scrollView.getId(),
                "strategy", "xpath",
                "selector", "//android.widget.TextView[@resource-id=\"com.hyundai.ndms:id/title_view\" and @text='"+carName+"']",
                "percentage", 50,
                "direction", "up",
                "maxCount", 3));

    }

    public void scrollRightToElement(String carName) throws InterruptedException {

        RemoteWebElement scrollView = (RemoteWebElement) wait.until(presenceOfElementLocated(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView")));

        LaunchAndroidDriver.getAndroidDriver().executeScript("gesture: scrollElementIntoView", Map.of(
                "scrollableView", scrollView.getId(),
                "strategy", "xpath",
                "selector", "//android.widget.TextView[@text='"+carName+"']",
                "percentage", 50,
                "direction", "right",
                "maxCount", 4
        ));
    }
}
