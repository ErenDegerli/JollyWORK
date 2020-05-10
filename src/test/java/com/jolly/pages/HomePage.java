package com.jolly.pages;

import com.jolly.core.PageBase;
import com.jolly.managers.FileReaderManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class HomePage extends PageBase {

    @FindBy(css = "button[id='setrow-push-popup-cancel-button']")
    private WebElement laterBtn;

    @FindBy(css = "i[class='icon icon-close close-icon']")
    private WebElement cookieCloseBtn;

    @FindBy(css = "span[class='moreTextList']")
    private WebElement message;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean resultVerification() {
        List<WebElement> list = driver.findElements(By.cssSelector("div[class='tour-list-area list-main-area'] div"));
        return list.size()>0;
    }

    public void getHomePage() throws IOException {
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        waitUntilClickable(laterBtn).click();
        waitUntilClickable(cookieCloseBtn).click();
    }

    public boolean checkMsg(String msg) {
        return waitUntilVisible(message).getText().contains(msg);
    }
}
