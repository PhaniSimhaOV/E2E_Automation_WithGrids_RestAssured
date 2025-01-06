package com.autogrid.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LaunchAndroidDriver {
    private static final Logger logger = LoggerFactory.getLogger(LaunchAndroidDriver.class);
    private static AndroidDriver androidDriver;
    private static AppiumDriverLocalService service;
    public static String serverAddress = "127.0.0.1";

    private LaunchAndroidDriver() throws MalformedURLException {
        Config.initialize();
        LaunchAndroidDriver();
    }


    public static void LaunchAndroidDriver() throws MalformedURLException {
        if(Config.get("selenium.grid.enabled").equalsIgnoreCase("false")){
            logger.info("Installing the given APK on Virtual Mobile device");

            URL gridUrl = new URL(String.format(Config.get("selenium.mobGrid.urlFormat"), Config.get("selenium.mobGrid.hubHost")));
            androidDriver = new AndroidDriver(gridUrl, getUiAutomator2Options());
            logger.info("Installed the Apk file and Launched application");
        }
    }

    // Synchronized to ensure only one thread can initialize the driver
    public static synchronized void setUpDriver() throws MalformedURLException {
        if (androidDriver == null) {
            logger.info("Setting up the Driver");
            new LaunchAndroidDriver();  // Calls the private constructor to initialize driver
        }
            logger.info("WebDriver is already initialized.");
    }

    public static AndroidDriver getAndroidDriver() {
        if (androidDriver == null) {
            logger.warn("AndroidDriver is not initialized!");
            throw new IllegalStateException("AppiumDriver is not initialized. Call setUpDriver() first.");
        }
        return androidDriver;
    }

    public static synchronized void tearDown() {
        logger.info("Tearing down the WebDriver.");
        if (androidDriver != null) {
            try {
                androidDriver.close();
                androidDriver.quit();
                Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
                logger.info("WebDriver closed successfully.");
            } catch (Exception e) {
                logger.warn("Error during WebDriver teardown: \"{0}\"",  e);
            } finally {
                androidDriver = null;
                try {
                    Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
                } catch (IOException e) {
                    logger.error("An exception has been thrown. Please review: \"{0}\"", e);
                }
            }
        } else {
            logger.warn("Driver is already null. Nothing to tear down.");
        }
    }

    public static UiAutomator2Options getUiAutomator2Options() {
        UiAutomator2Options options =  new UiAutomator2Options();
        options.setPlatformName(Config.get("appium.apk.platformName"))
                .setPlatformVersion(Config.get("appium.apk.platformVersion"))
                .setDeviceName(Config.get("appium.device.name"))
                .setAutomationName(Config.get("appium.apk.automationName"))
                .setAvdLaunchTimeout(Duration.ofSeconds(1200))
                .setAppPackage(Config.get("appium.apk.packageName"))
                .setAppActivity(Config.get("appium.apk.activityName"))
                .setNewCommandTimeout(Duration.ofSeconds(600))
                //.setIgnoreHiddenApiPolicyError(true)
                .setApp(System.getProperty("user.dir")+"/H-Smart.apk")
                .setUiautomator2ServerInstallTimeout(Duration.ofSeconds(360));
        options.setCapability("keepAppActivity", true);;
        return options;
    }

    public static void startAppiumServer(){
        logger.info("Setting Up Appium Server...");
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingPort(4723)
                .withIPAddress(serverAddress)
                .withTimeout(Duration.ofSeconds(300))
                .withArgument(() -> "--log-level", "error")
                .withArgument(() -> "--allow-insecure", "adb_shell")
                .withArgument(() -> "--use-plugins", "gestures");

        // Build the service
        service = AppiumDriverLocalService.buildService(builder);
        logger.info("Starting Appium Server...");

        service.start();
        logger.info("Appium Server have started on URL...{}", service.getUrl());
    }

    public static void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            logger.info("Appium Server stopped...");
        }
    }
}
