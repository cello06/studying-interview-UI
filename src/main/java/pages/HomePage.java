package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(linkText = "Booking")
    private WebElement bookingLink;

    public HomePage() {
        super();
    }

    public void clickOnBookingLink() {
        bookingLink.click();
    }
}
