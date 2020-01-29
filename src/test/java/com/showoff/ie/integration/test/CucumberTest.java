package com.showoff.ie.integration.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty","pretty:target/cucumber-html-report.html"},
        features = "src/test/resources/features",
        glue = "com.showoff.ie.integration.test.steps", tags = {"@integration"})


public class CucumberTest {
}
