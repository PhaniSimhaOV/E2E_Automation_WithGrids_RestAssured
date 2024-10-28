package com.autogrid.hooks;

import com.autogrid.testData.FlightRegCustomerData;
import com.autogrid.utils.JsonReader;
import com.autogrid.utils.LaunchDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    private FlightRegCustomerData flightRegCustomerData;
//    @Before
//    public void setParameters(){
//        if(System.getProperty("testDataPath") == null){
//            throw new IllegalArgumentException("Test data path cannot be null.");
//        }
//        this.flightRegCustomerData = JsonReader.getTestData(System.getProperty("testDataPath"), FlightRegCustomerData.class);
//        if (this.flightRegCustomerData == null) {
//            throw new IllegalStateException("Failed to load test data from: " + System.getProperty("testDataPath"));
//        }
//        logger.info("Test Data has been Loaded from: {}", System.getProperty("testDataPath"));
//    }

    public FlightRegCustomerData getFlightRegCustomerData() {
        return flightRegCustomerData;
    }
    @Before
    public void initializeDriver() throws MalformedURLException {
        LaunchDriver.setUpDriver();
    }
    @After
    public void killDriver() {
        LaunchDriver.tearDown();
    }
}
