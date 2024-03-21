package pages.car_rentals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

public class CarRentalsDetailPage extends BasePage {
    @FindBy(css = ".carRentalItemDetails > h3")
    private WebElement brandOfSelectedCarInDetailsPage;

    @FindBy(css = ".carRentalItemDetails > .d-flex > .fs-4")
    private WebElement pickedUpLocationOfSelectedCar;

    @FindBy(css = ".my-4 > .p-2> .fs-4")
    private List<WebElement> categoryAndTransmissionsOfSelectedCar;

    @FindBy(xpath = "//div[@class='py-3 w-100 d-flex justify-content-between align-items-center'][1]/span[2]")
    private WebElement feeOfSelectedCar;

    @FindBy(css = ".mt-5 > .fw-bold > span:nth-child(2)")
    private WebElement totalPrice;

    @FindBy(css = ".check-option ")
    private List<WebElement> insuranceButtons;

    // (//div[@class=' insurance-table-inner row m-0 w-100 text-start
    // actived']/div)[15]/div
    @FindBy(xpath = "(//div[@class=' insurance-table-inner row m-0 w-100 text-start actived']/div)[15]/div")
    private WebElement totalCoverPrice;

    @FindBy(css = ".btn-blue")
    private WebElement goToBookButton;

    public String getBrandOfSelectedCarInDetailsPage() {
        return brandOfSelectedCarInDetailsPage.getText();
    }

    public String getPickedUpLocationOfSelectedCar() {
        return pickedUpLocationOfSelectedCar.getText();
    }

    public String getTransmissionOfSelectedCar() {
        return categoryAndTransmissionsOfSelectedCar.get(1).getText();
    }

    public String getCategoryOfTheSelectedCar() {
        return categoryAndTransmissionsOfSelectedCar.get(3).getText();
    }

    public double getFeeOfSelectedCar() {
        return Double.parseDouble(feeOfSelectedCar.getText().substring(1));
    }

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().substring(1));
    }

    public void clickOnTheWhatIsCovered() {
        actions.moveToElement(insuranceButtons.get(1)).build().perform();
        insuranceButtons.get(1).click();
    }

    public double getTotalCoverPrice() {
        return Double.parseDouble(totalCoverPrice.getText().substring(1));
    }

    public void clickOnTheGoToBookButton() {
        actions.moveToElement(goToBookButton).build().perform();
        goToBookButton.click();
    }
}
