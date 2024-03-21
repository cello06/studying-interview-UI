package pages.car_rentals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.NoSuchElementException;

public class CarRentalsCheckoutPage extends BasePage {
    @FindBy(css = ".carRentalItemDetails > h3")
    private WebElement brandOfSelectedCarInCheckoutPage;

    @FindBy(css = ".carRentalItemDetails > .d-flex > .fs-4")
    private WebElement pickedUpLocationOfSelectedCarInCheckoutPage;
    @FindBy(css = ".my-4 > .p-2> .fs-4")
    private List<WebElement> categoryAndTransmissionsOfSelectedCarInCheckoutPage;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@name = 'phone']")
    private WebElement phoneNumberField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "address")
    private WebElement addressField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "postalCode")
    private WebElement postalCodeField;

    @FindBy(css = "[placeholder = \"Cardholder's Name\"]")
    private WebElement cardholderNameField;

    @FindBy(css = "[name = 'cardNumber']")
    private WebElement cardNumberField;

    @FindBy(css = "[name = 'expirationDate']")
    private WebElement expirationDateField;

    @FindBy(css = "[name = 'cvv']")
    private WebElement cvvCodeField;

    @FindBy(css = ".text-danger")
    private List<WebElement> errorMessages;

    @FindBy(css = ".btn-blue.fs-4.px-5.py-3")
    private WebElement bookNowButton;

    @FindBy(xpath = "//h1[@class='fs-3 ']")
    private WebElement bookingSuccessfullyMessage;

    @FindBy(xpath = "(//span[@class='fw-bold'])[2]")
    private WebElement pickupLocationInTheThankMessage;

    @FindBy(css = ".btn-danger")
    private WebElement closeButton;

    public String getBrandOfSelectedCarInCheckoutPage() {
        return brandOfSelectedCarInCheckoutPage.getText();
    }

    public String getPickedUpLocationOfSelectedCarInCheckoutPage() {
        return pickedUpLocationOfSelectedCarInCheckoutPage.getText();
    }

    public String getTransmissionsOfSelectedCarInCheckoutPage() {
        return categoryAndTransmissionsOfSelectedCarInCheckoutPage.get(1).getText();
    }

    public String getCategoryOfSelectedCarInCheckoutPage() {
        return categoryAndTransmissionsOfSelectedCarInCheckoutPage.get(3).getText();
    }

    public void enterFirstName(String firstName) {
        actions.moveToElement(firstNameField).build().perform();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        actions.moveToElement(lastNameField).build().perform();
        lastNameField.sendKeys(lastName);
    }

    public void enterPhoneNumber(String phoneNumber) {
        actions.moveToElement(phoneNumberField).build().perform();
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void enterCountryName(String countryName) {
        actions.moveToElement(countryField).build().perform();
        countryField.sendKeys(countryName);
    }

    public void enterAddress(String address) {
        actions.moveToElement(addressField).build().perform();
        addressField.sendKeys(address);
    }

    public void enterCity(String city) {
        actions.moveToElement(cityField).build().perform();
        cityField.sendKeys(city);
    }

    public void enterPostalCode(String postalCode) {
        actions.moveToElement(postalCodeField).build().perform();
        postalCodeField.sendKeys(postalCode);
    }

    public void enterCardHolder(String cardHolder) {
        actions.moveToElement(cardholderNameField).build().perform();
        cardholderNameField.sendKeys(cardHolder);
    }

    public void enterCardNumber(String cardNumber) {
        actions.moveToElement(cardNumberField).build().perform();
        cardNumberField.sendKeys(cardNumber);
    }

    public void enterExpirationDate(String date) {
        actions.moveToElement(expirationDateField).build().perform();
        expirationDateField.sendKeys(date);
    }

    public void enterCvvCode(String cvvCode) {
        actions.moveToElement(cvvCodeField).build().perform();
        cvvCodeField.sendKeys(cvvCode);
    }

    public void enterCheckoutProcessInformation(String firstName, String lastName, String phoneNumber,
                                                String countyName, String address, String city, String postalCode, String cartHolder, String cardNumber,
                                                String expirationDate, String cvvCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPhoneNumber(phoneNumber);
        enterCountryName(countyName);
        enterAddress(address);
        enterCity(city);
        enterPostalCode(postalCode);
        enterCardHolder(cartHolder);
        enterCardNumber(cardNumber);
        enterExpirationDate(expirationDate);
        enterCvvCode(cvvCode);
    }

    public String getErrorMessageOfSpecifiedElement(String elementName) {
        List<String> strOfErrorMessages = errorMessages.stream().map(WebElement::getText).toList();
        for (String errorMessage : strOfErrorMessages) {
            if (errorMessage.toLowerCase().contains(elementName)) {
                return errorMessage;
            }
        }

        throw new NoSuchElementException("There is not error message");
    }

    public void clickOnTheBookNowButton() {
        actions.moveToElement(bookNowButton).build().perform();
        try {
            bookNowButton.click();
        } catch (Exception e) {
            bookNowButton.click();
        }
    }

    public String getBookingSuccessfullyMessage() {
        return bookingSuccessfullyMessage.getText();
    }

    public String getPickupLocationInTheThankMessage() {
        String[] partsOfLocation = pickupLocationInTheThankMessage.getText().split("-");
        int sizeOfLocation = partsOfLocation[0].length();
        return partsOfLocation[0].substring(0, sizeOfLocation - 1);
    }

    public void clickOnTheCloseButton() {
        closeButton.click();
    }
}
