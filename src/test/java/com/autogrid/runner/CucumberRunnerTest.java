package com.autogrid.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",  // Path to your .feature files
        glue = "com.autogrid.stepDefinitions",  // Package name where step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports.html"},  // Output report in 'pretty' format and generate an HTML report
        monochrome = true,  // Makes the console output more readable
        tags = "@SmokeTest"  // Tags to control which scenarios to run
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}

