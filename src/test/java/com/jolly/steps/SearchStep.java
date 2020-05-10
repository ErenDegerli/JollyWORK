package com.jolly.steps;

import com.jolly.cucumber.TestContext;
import com.jolly.pages.HomePage;
import com.jolly.pages.ReservationHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import java.io.IOException;

public class SearchStep {

    TestContext testContext;
    HomePage homePage;
    ReservationHelper reservationHelper;

    public SearchStep(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
        reservationHelper = testContext.getPageObjectManager().getReservationHelper();
    }

    @Given("User is on the HomePage")
    public void user_is_on_the_HomePage() throws IOException {
        homePage.getHomePage();
    }

    @When("^User makes a search for a reservation to \"([^\"]*)\" from \"([^\"]*)\" to \"([^\"]*)\" for \"([^\"]*)\" adult and \"([^\"]*)\" children$")
    public void user_makes_a_search_for_a_reservation_to_from_to_for_adult_and_children(String city, String departTime, String returnTime, String adultNumber, String childrenNumber) throws IOException, InterruptedException {
        reservationHelper.makeSearch(city,departTime,returnTime,adultNumber,childrenNumber);
    }

    @Then("^User should see \"([^\"]*)\" on HomePage$")
    public void user_should_see_on_HomePage(String msg) {
        Assert.assertTrue(homePage.checkMsg(msg));
    }
}
