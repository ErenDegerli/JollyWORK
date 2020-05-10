package com.jolly.managers;

import com.jolly.enums.DriverType;
import com.jolly.enums.EnvironmentType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverManager {

    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
    private static final String driverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/";

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL: driver = createLocalDriver();
                break;
            case REMOTE: driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX:
                System.setProperty(FIREFOX_DRIVER_PROPERTY, driverPath + "geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty(CHROME_DRIVER_PROPERTY, driverPath + "chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case INTERNETEXPLORER: driver = new InternetExplorerDriver();
                break;
        }
        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        return driver;
    }

    public void quitDriver() {
        driver.close();
        driver.quit();
    }
}
