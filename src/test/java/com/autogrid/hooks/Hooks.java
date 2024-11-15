package com.autogrid.hooks;

import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void initializeDriver() throws MalformedURLException {
        LaunchDriver.setUpDriver();
    }
    @After
    public void killDriver() {
        LaunchDriver.tearDown();
    }
}
