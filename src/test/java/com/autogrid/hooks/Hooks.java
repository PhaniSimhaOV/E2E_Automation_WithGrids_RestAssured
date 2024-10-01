package com.autogrid.hooks;

import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    public static LaunchDriver launchDriver;

    @Before
    public static void initializeDriver(){
        launchDriver.setUpDriver();
    }

    @After
    public static void killDriver(){
        launchDriver.tearDown();
    }

}
