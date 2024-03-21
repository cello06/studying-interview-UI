package step_defs.car_rentals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.car_rentals.used_class.Car;
import step_defs.BaseStep;
import utils.BrowserUtils;
import utils.Pages;

import static org.assertj.core.api.BDDAssertions.then;

public class CarRentalsFilteringSteps extends BaseStep {
    public static Car SELECTED_CAR;

    private static final Logger LOGGER = LogManager.getLogger(CarRentalsFilteringSteps.class);

    @Then("the user validates that car rental page is visible")
    public void theUserValidatesThatCarRentalPageIsVisible() {
        String expectedURL = "https://InarAcademy:Fk160621.@test.inar-academy.com/booking/cars";
        String actualURL = PAGES.getCarRentalsFilteringPage().getCurrentURL();

        then(actualURL).isEqualTo(expectedURL);
        LOGGER.debug("The user validates that car rental page is visible");
    }

    @Then("the user face with correct {string},{string} and {string} in Car Rental filtering page")
    public void theUserFaceWithAndInCarRentalFilteringPage(String expectedPickupDate, String expectedDropOffDate,
                                                           String expectedPickupLocation) {
        String actualPickupDate = PAGES.getCarRentalsFilteringPage().getPickupDate();
        String actualDropOffDate = PAGES.getCarRentalsFilteringPage().getDropOffDate();
        String actualPickupLocation = PAGES.getCarRentalsFilteringPage().getEnteredPickupLocation();

        then(actualPickupDate).withFailMessage("Wrong pickup date!").isEqualTo(expectedPickupDate);
        then(actualDropOffDate).withFailMessage("Wrong drop-off date!").isEqualTo(expectedDropOffDate);
        then(actualPickupLocation).withFailMessage("Wrong pickup location!").isEqualTo(expectedPickupLocation);
        LOGGER.debug(
                "The user face with correct  pickup_date, drop-off_date and pick up location in Car Rental filtering page");
    }

    @And("the user selects the {string}")
    public void theUserClicksOnTheButton(String priceRange) {
        PAGES.getCarRentalsFilteringPage().selectTheCheckBoxWithParameter(priceRange);
        LOGGER.info("The user selects the " + priceRange);
    }

    @Then("the user validates that {string} is selected")
    public void theUserValidatesThatIsSelected(String boxValue) {
        PAGES.getCarRentalsFilteringPage().isTheCheckBoxSelected(boxValue);
        LOGGER.debug("The user validates that " + boxValue + " is selected");
    }

    @And("the user enters {string},{string},{string},{string} and {string}")
    public void the_user_enters_criteria(String pick_up_location, String price_range, String car_spec,
                                         String transmission, String car_category) {
        PAGES.getCarRentalsFilteringPage().enterPickupLocation(pick_up_location);
        LOGGER.info("The user enters " + pick_up_location);

        PAGES.getCarRentalsFilteringPage().selectTheCheckBoxWithParameter(price_range);
        LOGGER.info("The user enters " + price_range);

        PAGES.getCarRentalsFilteringPage().selectTheCheckBoxWithParameter(car_spec);
        LOGGER.info("The user enters " + car_spec);

        PAGES.getCarRentalsFilteringPage().selectTheCheckBoxWithParameter(transmission);
        LOGGER.info("The user enters " + transmission);

        PAGES.getCarRentalsFilteringPage().selectTheCheckBoxWithParameter(car_category);
        LOGGER.info("The user enters " + car_category);
    }

    @And("the user clicks on search button in filtering page")
    public void the_user_clicks_on_search_button_in_filtering_page() {
        PAGES.getCarRentalsFilteringPage().clickOnTheFilteringPageSearchButton();
        LOGGER.info("The user clicks on search button in filtering page");
    }

    @Then("the user validates that {string},{string},{string} and {string} matches with displayed cars' information")
    public void theUserValidatesThatAndMatchesWithDisplayedCarsInformation(String pick_up_location, String price_range,
                                                                           String transmission, String car_category) {
        then(PAGES.getCarRentalsFilteringPage().arePickedUpLocationsOfFilteredCarsMatch(pick_up_location)).isTrue();
        then(PAGES.getCarRentalsFilteringPage().areThePricesOfTheFilteredCarsInTheSelectedPriceRage(price_range))
                .isTrue();
        then(PAGES.getCarRentalsFilteringPage().areTransmissionsOfFilteredCarsMatch(transmission)).isTrue();
        then(PAGES.getCarRentalsFilteringPage().areCategoriesOfFilteredCarsMatch(car_category)).isTrue();
        LOGGER.debug("The user validates that all of information match with selected boxes");

    }

    @When("the user clicks on price highest sort button")
    public void theUserClicksOnPriceHighestSortButton() {
        PAGES.getCarRentalsFilteringPage().clickOnTheHighestSortButton();
        LOGGER.info("The user clicks on price highest sort button");

    }

    @Then("the user validates that cars are sorted from highest to lowest")
    public void theUserValidatesThatCarsAreSortedFromHighestToLowest() {
        then(PAGES.getCarRentalsFilteringPage().areAllPricesArrangedFromHighestToLowest()).isTrue();
        LOGGER.debug("The user validates that cars are sorted from highest to lowest");
    }

    @When("the user clicks on price lowest sort button")
    public void theUserClicksOnPriceLowestSortButton() {
        PAGES.getCarRentalsFilteringPage().clickOnTheLowestSortButton();
        LOGGER.info("The user clicks on price lowest sort button");
    }

    @Then("the user validates that cars are sorted from lowest to highest")
    public void theUserValidatesThatCarsAreSortedFromLowestToHighest() {
        then(PAGES.getCarRentalsFilteringPage().areAllPricesArrangedFromLowestToHighest()).isTrue();
        LOGGER.debug("The user validates that cars are sorted from lowest to heighest");
    }

    @And("the user clicks View Dial button of the #{int} element")
    public void theUserClicksViewDialButtonOfTheFirstElement(int index) {
        BrowserUtils.wait(1.0);
        PAGES.getCarRentalsFilteringPage().clickOnTheViewDealButton(index);
        LOGGER.info("The user clicks View Dial button of the first element");
        SELECTED_CAR = PAGES.getCarRentalsFilteringPage().getSelectedCar();
    }

    @And("the user enters {string},{string},{string} and {string}")
    public void theUserEntersAnd(String pickUpLocation, String priceRange, String transmission, String category) {
        PAGES.getCarRentalsFilteringPage().enterPickupLocation(pickUpLocation);
        PAGES.getCarRentalsFilteringPage().selectTheCheckBoxWithParameter(priceRange);
        PAGES.getCarRentalsFilteringPage().selectTheCheckBoxWithParameter(transmission);
        PAGES.getCarRentalsFilteringPage().selectTheCheckBoxWithParameter(category);
        LOGGER.info("The user enters pickUpLocation,priceRange,transmission and category");
    }
}
