package step_defs.car_rentals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import step_defs.BaseStep;

import static org.assertj.core.api.BDDAssertions.then;

public class CarRentalsDetailSteps extends BaseStep {
    private static final Logger LOGGER = LogManager.getLogger(CarRentalsDetailSteps.class);

    @Then("the user validates that the name of selected car is true")
    public void theUserValidatesThatTheNameOfSelectedCarIsTrue() {
        String expectedResult = CarRentalsFilteringSteps.SELECTED_CAR.getSelectedCarName();
        String actualResult = PAGES.getCarRentalsDetailPage().getBrandOfSelectedCarInDetailsPage();

        then(expectedResult).isEqualTo(actualResult);
        LOGGER.debug("The user validates that the name of selected car is true");
    }

    @And("the user validates that {string}, price, {string} and {string} of displayed car in detail_page matches with the selected car in filtering_page")
    public void theUserValidatesThatPriceAndOfDisplayedCarInDetail_pageMatchesWithTheSelectedCarInFiltering_page(
            String pick_up_location, String transmission, String car_category) {
        then(PAGES.getCarRentalsDetailPage().getPickedUpLocationOfSelectedCar()).isEqualTo(pick_up_location);
        LOGGER.debug("The user validates that " + pick_up_location
                + "of displayed car in detail_page matches with the selected car in filtering_page");

        then(PAGES.getCarRentalsDetailPage().getFeeOfSelectedCar())
                .isEqualTo(CarRentalsFilteringSteps.SELECTED_CAR.getPriceOfSelectedCar() * 3);
        LOGGER.debug(
                "The user validates that Price of displayed car in detail_page matches with the selected car in filtering_page");

        then(PAGES.getCarRentalsDetailPage().getTransmissionOfSelectedCar()).isEqualTo(transmission);
        LOGGER.debug("The user validates that " + transmission
                + "of displayed car in detail_page matches with the selected car in filtering_page");

        then(PAGES.getCarRentalsDetailPage().getCategoryOfTheSelectedCar()).isEqualTo(car_category);
        LOGGER.debug("The user validates that " + car_category
                + "of displayed car in detail_page matches with the selected car in filtering_page");

    }

    @And("the user validates that total_price calculation is true")
    public void theUserValidatesThatTotal_priceCalculationIsTrue() {
        double taxValue = 0.02;
        double taxAmount = PAGES.getCarRentalsDetailPage().getFeeOfSelectedCar() * taxValue;
        double expectedResult = CarRentalsFilteringSteps.SELECTED_CAR.getPriceOfSelectedCar() * 3 + taxAmount;
        then(expectedResult).withFailMessage("Total price is wrong !")
                .isEqualTo(PAGES.getCarRentalsDetailPage().getTotalPrice());
        LOGGER.debug("The user validates that total_price calculation is true");
    }

    @When("the user clicks on the What is covered button")
    public void theUserClicksOnTheWhatIsCoveredButton() {

        PAGES.getCarRentalsDetailPage().clickOnTheWhatIsCovered();
        LOGGER.info("The user clicks on the What is covered button");
    }

    @Then("the user validates that total_price is increased by amount total_cover_price")
    public void theUserValidatesThatTotal_priceIsIncreasedByAmountTotal_cover_price() {
        double taxValue = 0.02;
        double taxAmount = PAGES.getCarRentalsDetailPage().getFeeOfSelectedCar() * taxValue;
        double expectedResult = PAGES.getCarRentalsDetailPage().getFeeOfSelectedCar() + taxAmount
                + PAGES.getCarRentalsDetailPage().getTotalCoverPrice();
        double actualResult = PAGES.getCarRentalsDetailPage().getTotalPrice();
        then(actualResult).withFailMessage("Total price is not correctly calculated!").isEqualTo(expectedResult);
        LOGGER.debug("The user validates that total_price is increased by amount total_cover_price");
    }

    @When("the user clicks on go_to_book button")
    public void theUserClicksOnGo_to_bookButton() {

        PAGES.getCarRentalsDetailPage().clickOnTheGoToBookButton();
        LOGGER.info("The user clicks on go_to_book button");
    }
}
