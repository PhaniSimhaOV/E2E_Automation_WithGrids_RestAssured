package com.autogrid.hooks;

import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.autogrid.utils.LaunchAndroidDriver;
import java.io.IOException;
import java.net.MalformedURLException;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before("@web")
    public void initializeDriver() throws MalformedURLException {
        LaunchDriver.setUpDriver();
    }

    @After("@web")
    public void killDriver() {
        LaunchDriver.tearDown();
    }

    @Before("@mobile")
    public void aSetupAppiumServer() {
        LaunchAndroidDriver.stopAppiumServer();
        LaunchAndroidDriver.startAppiumServer();

    }

    @Before("@mobile")
    public void bInitializeAppiumDriver() throws IOException {
        Runtime.getRuntime().exec("adb shell am force-stop com.hyundai.ndms");
        LaunchAndroidDriver.setUpDriver();
    }

    @After("@always_run")
    public void killAppiumServer() {
        LaunchAndroidDriver.stopAppiumServer();
    }
}
