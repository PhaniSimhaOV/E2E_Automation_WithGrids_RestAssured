package com.autogrid.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        features = "features",  // Relative path from the root directory
        glue = {"com.autogrid.stepDefinitions", "com.autogrid.hooks", "com.autogrid.utils"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)

public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

}