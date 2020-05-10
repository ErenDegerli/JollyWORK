package com.jolly.managers;

import com.jolly.core.PageBase;
import com.jolly.pages.HomePage;
import com.jolly.pages.ReservationHelper;
import org.openqa.selenium.WebDriver;

public class PageObjectManager extends PageBase {

    private HomePage homePage;
    private ReservationHelper reservationHelper;

    public PageObjectManager(WebDriver driver) {
        super(driver);
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

    public ReservationHelper getReservationHelper() {
        return (reservationHelper == null) ? reservationHelper = new ReservationHelper(driver) : reservationHelper;
    }
}
