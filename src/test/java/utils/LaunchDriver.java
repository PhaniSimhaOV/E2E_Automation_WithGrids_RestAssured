package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class LaunchDriver {
    private static final Logger logger = LoggerFactory.getLogger(LaunchDriver.class);
    private static WebDriver driver;
    private static final int TIME_OUT = 50;

    private LaunchDriver() throws MalformedURLException {
        Config.initialize();

        if(Config.get("browser").equalsIgnoreCase("chrome")
                && Config.get("selenium.grid.enabled").equalsIgnoreCase("false")
                && !Config.get("selenium.run.device").equalsIgnoreCase("mobile")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments(Arrays.asList("--no-sandbox", "--verbose", "--window-size=1920,1080",
                    "--ignore-certificate-errors", "--disable-notifications", "--remote-allow-origins=*", "--headless"));
            logger.info("Initializing WebDriver & launching Chrome Locally");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }else if(Config.get("browser").equalsIgnoreCase("firefox")
                && Config.get("selenium.grid.enabled").equalsIgnoreCase("false")
                && !Config.get("selenium.run.device").equalsIgnoreCase("mobile")){
            logger.info("Initializing WebDriver & launching Firefox Locally");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(Arrays.asList("--no-sandbox", "--verbose", "--window-size=1920,1080",
                    "--ignore-certificate-errors", "--disable-notifications"));

            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();
        }else if(Config.get("browser").equalsIgnoreCase("chrome")
                && Config.get("selenium.grid.enabled").equalsIgnoreCase("true")
                && !Config.get("selenium.run.device").equalsIgnoreCase("mobile")){
            logger.info("Initializing WebDriver & launching Chrome on Grid");

            ChromeOptions options = new ChromeOptions();
            options.addArguments(Arrays.asList("--no-sandbox", "--verbose", "--window-size=1920,1080",
                    "--ignore-certificate-errors", "--disable-notifications", "--remote-allow-origins=*", "--headless"));

            URL gridUrl = new URL(String.format(Config.get("selenium.grid.urlFormat"), Config.get("selenium.grid.hubHost")));
            driver = new RemoteWebDriver(gridUrl, options);
            logger.info("Remote WebDriver launched on Grid");
            driver.manage().window().maximize();
        }else if(Config.get("browser").equalsIgnoreCase("chrome")
                && Config.get("selenium.grid.enabled").equalsIgnoreCase("false")
                && Config.get("selenium.run.device").equalsIgnoreCase("mobile")){
            logger.info("Initializing WebDriver & launching Chrome locally on Mobile device");

            ChromeOptions options = new ChromeOptions();
            options.addArguments(Arrays.asList("--no-sandbox", "--verbose",
                    "--ignore-certificate-errors", "--disable-notifications", "--remote-allow-origins=*"));
            options.setExperimentalOption("mobileEmulation", createMobileEmulation());
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_OUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME_OUT));
    }

    // Synchronized to ensure only one thread can initialize the driver
    public static synchronized void setUpDriver() throws MalformedURLException {
        if (driver == null) {
            logger.info("Setting up the WebDriver");
            new LaunchDriver();  // Calls the private constructor to initialize driver
        } else {
            logger.info("WebDriver is already initialized.");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            logger.warn("Driver is not initialized!");
            throw new IllegalStateException("Driver is not initialized. Call setUpDriver() first.");
        }
        return driver;
    }

    public static void launchSite() {
        if (driver != null) {
            logger.info("Launching site \"{}\"", Config.get("site.url"));
            driver.get(Config.get("site.url"));
        } else {
            logger.warn("Driver is not initialized. Cannot launch site.");
        }
    }

    public static synchronized void tearDown() {
        logger.info("Tearing down the WebDriver.");
        if (driver != null) {
            try {
                driver.quit();
                driver = null;
                logger.info("WebDriver closed successfully.");
            } catch (Exception e) {
                logger.warn("Error during WebDriver teardown: \"{0}\"",  e);
            } finally {
                driver = null;  // Ensure driver is set to null after quit
                try {
                    if (System.getProperty("os.name").toLowerCase().contains("win")) {
                        // Windows: Kill chromedriver.exe using taskkill
                        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                        Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
                        logger.info("WebDriver Killed successfully on win.");
                    } else {
                        // macOS/Linux: Kill chromedriver using killall
                        Runtime.getRuntime().exec("killall chromedriver");
                        Runtime.getRuntime().exec("killall geckodriver");
                        logger.info("WebDriver Killed successfully on others.");
                    }
                } catch (IOException e) {
                    logger.error("An exception has been thrown. Please review: \"{0}\"", e);
                }
            }
        } else {
            logger.warn("Driver is already null. Nothing to tear down.");
        }
    }

    private static java.util.Map<String, Object> createMobileEmulation() {
        // Create a Map for mobile emulation (you can use predefined devices or custom configurations)
        java.util.Map<String, Object> mobileEmulation = new java.util.HashMap<>();
        mobileEmulation.put("deviceName", Config.get("selenium.device.name")); // Predefined device name
        return mobileEmulation;
    }

}
