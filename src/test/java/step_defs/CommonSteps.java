package step_defs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;

public class CommonSteps extends BaseStep{
    private static final Logger LOGGER = LogManager.getLogger(CommonSteps.class);

    @Given("the user on the Inar Academy Home page")
    public void the_user_on_the_inar_academy_home_page(){
        Assertions.assertThat(DRIVER)
                .as("Driver is not created!")
                .isNotNull();
        LOGGER.info("The user on the Inar Academy Home Page");
    }

    @When("the user clicks on the Booking link")
    public void theUserClicksOnTheBookingLink() {
        PAGES.getHomePage().clickOnBookingLink();
        LOGGER.info("the user clicks on the Booking link");
    }
}
