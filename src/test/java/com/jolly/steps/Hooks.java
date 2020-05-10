package com.jolly.steps;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.jolly.cucumber.TestContext;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;

public class Hooks {

    TestContext testContext;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
                File destinationPath = new File(System.getProperty("user.dir") + "\\target\\" + screenshotName + ".png");
                Files.copy(sourcePath, destinationPath);
            } catch (IOException e) {
            }
        }
    }

    @After(order = 0)
    public void afterSteps() {
        testContext.getWebDriverManager().quitDriver();
    }
}
