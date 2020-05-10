package com.jolly.pages;

import com.jolly.core.PageBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.io.IOException;
import java.util.List;

public class ReservationHelper extends PageBase {

    @FindBy(id = "travelHotelSearch")
    private WebElement location;

    @FindBy(css = "div[class='col first-date leavining']")
    private WebElement departureTime;

    @FindBy(css = "div[class='col last-date returning']")
    private WebElement returningTime;

    @FindBy(id = "col first-date leavining")
    private WebElement datePicker;

    @FindBy(css = "a[class='ui-datepicker-next ui-corner-all']")
    private WebElement datePickerNextBtn;

    @FindBy(css = "span[class='ui-datepicker-month']")
    private WebElement datePickerMonth;

    @FindBy(css = "span[class='ui-datepicker-year']")
    private WebElement datePickerYear;

    @FindBy(css = "[data-handler='selectDay']")
    private List<WebElement> dayList;

    @FindBy(css = "span[name='adult']")
    private WebElement adultNumber;

    @FindBy(css = "div[class='room-info'] div:nth-child(2) label div div")
    private WebElement adultNumberDecreaseBtn;

    @FindBy(css = "div[class='room-info'] div:nth-child(2) label div:nth-child(4)")
    private WebElement adultNumberIncreaseBtn;

    @FindBy(css = "span[name='childNumber']")
    private WebElement childNumber;

    @FindBy(css = "div[class='room-info'] div:nth-child(3) label div div")
    private WebElement childrenNumberDecreaseBtn;

    @FindBy(css = "div[class='room-info'] div:nth-child(3) label div:nth-child(4)")
    private WebElement childrenNumberIncreaseBtn;

    @FindBy(css = "div[class='action-button-area'] button")
    private WebElement applyBtn;

    public ReservationHelper(WebDriver driver) {
        super(driver);
    }

    private void enterLocation(String city) throws IOException, InterruptedException {
        location.sendKeys(city);
        Thread.sleep(2000);
        location.sendKeys(Keys.TAB);
    }

    private void setDate(String date) throws IOException {

        String[] splittedValues = date.split(" ");
        String expectedDay = splittedValues[0];
        String expectedMonth = splittedValues[1];
        String expectedYear = splittedValues[2];

        waitUntilVisible(datePickerMonth);
        if(datePickerMonth.getText() == expectedMonth && datePickerYear.getText() == expectedYear) {
            int count = dayList.size();
            for (int i = 0; i < count; i++) {
                String day = dayList.get(i).getText();
                if (day.equalsIgnoreCase(expectedDay)) {
                    dayList.get(i).click();
                    break;
                }
            }
        } else {
            while(datePickerMonth.getText() != expectedMonth && datePickerYear.getText() != expectedYear) {
                waitUntilClickable(datePickerNextBtn).click();
                break;
            }
            //pick the day
            int count = dayList.size();
            for(int i = 0; i<count; i++) {
                String day = dayList.get(i).getText();
                if(day.equalsIgnoreCase(expectedDay)) {
                    dayList.get(i).click();
                    break;
                }
            }
        }
    }

    private void setAdultNumber(String number) throws IOException {
        if (Integer.parseInt(number) > 2) {
            for (int i=0; i<(Integer.parseInt(number)-2); i++) {
                adultNumberIncreaseBtn.click();
            }
        }else if(Integer.parseInt(number) < 2 ) {
            adultNumberDecreaseBtn.click();
        }
    }

    private void setChildrenNumber(String number) throws IOException {
        for(int i=0; i<Integer.parseInt(number); i++) {
            childrenNumberIncreaseBtn.click();
        }
    }

    public void makeSearch(String city, String departTime, String returningTime, String adultNumber, String childrenNumber) throws IOException, InterruptedException {
        enterLocation(city);
        setDate(departTime);
        setDate(returningTime);
        Thread.sleep(2000);
        setAdultNumber(adultNumber);
        setChildrenNumber(childrenNumber);
        applyBtn.click();
    }
}
