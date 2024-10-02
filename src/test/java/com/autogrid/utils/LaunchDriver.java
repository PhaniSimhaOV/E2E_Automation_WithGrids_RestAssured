package com.autogrid.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaunchDriver {
    static Logger logger = Logger.getLogger(LaunchDriver.class.getName());
    private static WebDriver driver;
    private static final int TIME_OUT = 50;

    // Private constructor to prevent external instantiation
    LaunchDriver() {
        logger.log(Level.INFO, "Initializing WebDriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Arrays.asList("--no-sandbox", "--verbose", "--window-size=1920,1080",
                "--ignore-certificate-errors", "--disable-notifications", "--remote-allow-origins=*"));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_OUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME_OUT));
    }

    // Synchronized to ensure only one thread can initialize the driver
    public static synchronized void setUpDriver() {
        if (driver == null) {
            logger.log(Level.INFO, "Setting up the WebDriver");
            new LaunchDriver();  // Calls the private constructor to initialize driver
        } else {
            logger.log(Level.INFO, "WebDriver is already initialized.");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.log(Level.SEVERE, "Driver is not initialized!");
            throw new IllegalStateException("Driver is not initialized. Call setUpDriver() first.");
        }
        return driver;
    }

    public static void launchSite(String url) {
        if (driver != null) {
            logger.log(Level.INFO, "Launching site: " + url);
            driver.get(url);
        } else {
            logger.log(Level.SEVERE, "Driver is not initialized. Cannot launch site.");
        }
    }

    public static synchronized void tearDown() {
        logger.log(Level.INFO, "Tearing down the WebDriver.");
        if (driver != null) {
            try {
                driver.quit();
                logger.log(Level.INFO, "WebDriver closed successfully.");
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error during WebDriver teardown: " + e);
            } finally {
                driver = null;  // Ensure driver is set to null after quit
            }
        } else {
            logger.log(Level.WARNING, "Driver is already null. Nothing to tear down.");
        }
    }
}
