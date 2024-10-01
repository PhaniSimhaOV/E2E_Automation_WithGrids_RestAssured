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
    public static WebDriver driver;
    public static LaunchDriver launchDriver;
    public static final int TIME_OUT = 50;
    private LaunchDriver(){
            ChromeOptions options = new ChromeOptions();
            options.addArguments(Arrays.asList("--no-sandbox", "--verbose", "--window-size=1920,1080",
                    "--ignore-certificate-errors", "--disable-notofocations", "--remote-allow-origins=*"));

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_OUT));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME_OUT));
    }

    public static void launchSite(String url){
        logger.log(Level.INFO, "Launching Site: "+url);
        driver.get(url);
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void setUpDriver(){
        logger.log(Level.INFO, "Setting up the driver");
        if(launchDriver == null){
            launchDriver = new LaunchDriver();
        }
    }

    public static void tearDown(){
        logger.log(Level.INFO, "Tearing down the Browser.");
        if(driver != null){
            try{
                driver.quit();
            }catch(Exception e){
                logger.log(Level.WARNING, "Teardown of driver failed with Error: "+e);
            }
        }
        launchDriver = null;
    }
}
