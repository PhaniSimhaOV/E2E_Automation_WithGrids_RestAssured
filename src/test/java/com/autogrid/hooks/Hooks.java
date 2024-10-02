package com.autogrid.hooks;

import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void initializeDriver() {
        LaunchDriver.setUpDriver();
    }
    @After
    public void killDriver() {
        LaunchDriver.tearDown();
    }
}
