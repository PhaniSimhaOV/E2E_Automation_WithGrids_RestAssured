package com.autogrid.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",  // Relative path from the root directory
        glue = {"com.autogrid.stepDefinitions", "com.autogrid.hooks", "com.autogrid.utils"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        tags = "@Invoice"
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

}