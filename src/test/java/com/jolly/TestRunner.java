package com.jolly;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", glue = "com/jolly/steps", plugin = {
        "pretty",
        "html:target/cucumber",
})
public class TestRunner {

}
