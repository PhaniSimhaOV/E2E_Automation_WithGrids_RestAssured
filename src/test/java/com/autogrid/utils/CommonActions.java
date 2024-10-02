package com.autogrid.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class CommonActions {
    private LaunchDriver launchDriver;
    private Actions action;
    private WebDriverWait wait;

    public CommonActions() {
        this.action = new Actions(LaunchDriver.getDriver());
        this.wait = new WebDriverWait(LaunchDriver.getDriver(), Duration.ofSeconds(30));
    }
    public WebElement getElement(By var) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(var));
        return launchDriver.getDriver().findElement(var);
    }
    public void clickOnElement(By var) {
        wait.until(ExpectedConditions.elementToBeClickable(var));
        launchDriver.getDriver().findElement(var).click();
    }
    public boolean isElementPresent(By var) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(var));
            return LaunchDriver.getDriver().findElement(var).isDisplayed();
        } catch (Exception e) {
            return false;  // Element is not present
        }
    }

    public boolean isLinkPresent(By var) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(var));
            return LaunchDriver.getDriver().findElement(var).isDisplayed();
        } catch (Exception e) {
            return false;  // Link is not present
        }
    }

}
