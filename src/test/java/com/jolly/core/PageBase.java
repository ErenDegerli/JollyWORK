package com.jolly.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class PageBase {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public JavascriptExecutor js;

    protected PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement waitUntilVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public void mouseOver(WebElement webElement) {
        actions.moveToElement(webElement).perform();
    }
}
