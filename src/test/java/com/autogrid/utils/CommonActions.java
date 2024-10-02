package com.autogrid.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class CommonActions {

    LaunchDriver launchDriver;

    Actions action = new Actions(launchDriver.getDriver());
    WebDriverWait wait = new WebDriverWait(launchDriver.getDriver(), Duration.ofSeconds(30));

    public WebElement getElement(By var) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(var));
        return launchDriver.getDriver().findElement(var);
    }

    public void clickOnElement(By var) {
        wait.until(ExpectedConditions.elementToBeClickable(var));
        launchDriver.getDriver().findElement(var).click();
    }

    public void isElementPresent(By var) {
        wait.until(ExpectedConditions.presenceOfElementLocated(var));
        launchDriver.getDriver().findElement(var).isDisplayed();
    }

}
