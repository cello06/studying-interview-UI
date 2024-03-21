package step_defs.car_rentals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.NoAlertPresentException;
import pages.HomePage;
import step_defs.BaseStep;
import utils.BrowserUtils;

import static org.assertj.core.api.BDDAssertions.then;

public class CarRentalsHomeSteps extends BaseStep {
    private static final Logger LOGGER = LogManager.getLogger(CarRentalsHomeSteps.class);

    HomePage homePage;

    @When("the user clicks on the Booking link")
    public void the_user_clicks_on_the_Booking_link() {
        homePage = PAGES.getHomePage();
        homePage.clickOnBookingLink();
        LOGGER.info("The user clicks on the Booking link");
    }

    @Then("the user sees Booking Home page")
    public void the_user_sees_Booking_Home_page(){
        String actualHeader = PAGES.getCarRentalsHomePage().getInarBookingHeaderText();
        String expectedHeader = "inarbooking";
        Assertions.assertThat(expectedHeader)
                .withFailMessage("The user is not on the Booking home page")
                .isEqualTo(actualHeader);
        LOGGER.debug("The user sees Booking Home page");
    }
    @And("the user clicks on the Car rentals tab")
    public void the_user_clicks_on_the_Car_rentals_tab() {
        BrowserUtils.wait(1.0);
        PAGES.getCarRentalsHomePage().clickOnTheCarRentalsLink();
        LOGGER.info("The user clicks on the Car rentals tab");
    }
    @Then("the user validates that {string} message is visible")
    public void the_user_validates_that_true_message_is_visible(String expectedMessage) {
        String actualMessage = PAGES.getCarRentalsHomePage().getHeaderTitleMessage();
        Assertions.assertThat(actualMessage)
                .withFailMessage("The user is not on the " + "Booking home page")
                .isEqualTo(expectedMessage);
        LOGGER.debug("The user validates that " + expectedMessage + " message is visible");
    }

    @And("the user clicks on the search button")
    public void the_user_clicks_on_the_search_button() {
        PAGES.getCarRentalsHomePage().clickOnTheSearchButton();
        LOGGER.info("The user clicks on the search button");
    }

    @And("the user enter {string},{string},{string},{string} and {string}")
    public void theUserEnterCredentialsInCarRentalsHomePage(String pickupLocation, String pickup_date,
                                                            String pickup_hour, String dropOffDate, String drop_hour) {
        PAGES.getCarRentalsHomePage().enterThePickupLocation(pickupLocation);
        LOGGER.info("The user enter " + pickupLocation);

        PAGES.getCarRentalsHomePage().enterThePickUpDate(pickup_date);
        LOGGER.info("The user enter " + pickup_date);

        PAGES.getCarRentalsHomePage().selectThePickupHour(pickup_hour);
        LOGGER.info("The user enter " + pickup_hour);

        PAGES.getCarRentalsHomePage().enterTheDropOffDate(dropOffDate);
        LOGGER.info("The user enter " + dropOffDate);

        PAGES.getCarRentalsHomePage().selectTheDropOffHour(drop_hour);
        LOGGER.info("The user enter " + drop_hour);
    }

    @Then("the user face with {string}")
    public void theUserFaceWith(String errorMessage) {
        try {
            String actualMessage = PAGES.getCarRentalsHomePage().getErrorMessageInHomePage();
            then(actualMessage).isEqualTo(errorMessage);
            LOGGER.debug("Error message displayed");
        }
        catch (NoAlertPresentException ex) {
            LOGGER.error(ex.getMessage());
            throw new NoAlertPresentException("Error message is not displayed");
        }

    }
}
