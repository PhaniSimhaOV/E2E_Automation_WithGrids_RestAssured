package com.autogrid.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class CommonActions {

    private WebDriver driver;
    public static CommonActions commonActions;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectValue(WebElement element) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        select.selectByIndex(new Random().nextInt(options.size()));
    }
}
