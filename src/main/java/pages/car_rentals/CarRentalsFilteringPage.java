package pages.car_rentals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.car_rentals.used_class.Car;
import utils.BrowserUtils;

import java.util.List;

public class CarRentalsFilteringPage extends BasePage {
    private Car selectedCar = new Car();

    private String brandOfSelectedCar;

    private double priceOfSelectedCar;

    private String pickupLocationOfSelectedCar;

    private String transmissionOfSelectedCar;

    private String categoryOfSelectedCar;

    private String pickupDateOfSelectedCar;

    private String dropOffDateOfSelectedCar;

    @FindBy(xpath = "(//input[@class='form-control'])[1]")
    private WebElement pickupDate;

    @FindBy(xpath = "(//input[@class='form-control'])[2]")
    private WebElement dropOffDate;

    @FindBy(css = "[placeholder='Enter pickup location']")
    private WebElement pickupLocationTextField;

    @FindBy(css = ".position-relative")
    private List<WebElement> pickedUpAndDropOffDates;

    @FindBy(css = ".lsCheckboxInput")
    private List<WebElement> checkBoxes;

    @FindBy(css = ".search-btn-car-rental")
    private WebElement filteringPageSearchButton;

    @FindBy(css = ".fs-1")
    private List<WebElement> pricesOfTheFilteredCars;

    @FindBy(css = ".my-4 > .p-2> .fs-4")
    private List<WebElement> categoryAndTransmissionsOfFilteredCars;

    @FindBy(css = ".carRentalItemDetails > .d-flex > .fs-4")
    private List<WebElement> pickedUpLocationOfFilteredCars;

    @FindBy(css = ".lrb-btn")
    private List<WebElement> highestAndLowestButtons;

    @FindBy(css = ".mt-2.btn-blue")
    private List<WebElement> viewDealButtons;

    @FindBy(css = ".carRentalItemDetails > h3")
    private List<WebElement> brandsOfCars;

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public String getPickupDate() {
        String[] pickupDateParts = pickupDate.getAttribute("value").split("-");
        return pickupDateParts[1] + "/" + pickupDateParts[2] + "/" + pickupDateParts[0];
    }

    public String getDropOffDate() {
        String[] dropOffDateParts = dropOffDate.getAttribute("value").split("-");
        return dropOffDateParts[1] + "/" + dropOffDateParts[2] + "/" + dropOffDateParts[0];

    }

    public int getDayOfPickupDate() {
        String[] partsOfDate = pickupDate.getAttribute("value").split("-");
        return Integer.parseInt(partsOfDate[2]);
    }

    public int getDayOfDropOffDate() {
        String[] partsOfDate = dropOffDate.getAttribute("value").split("-");
        return Integer.parseInt(partsOfDate[2]);
    }

    public void enterPickupLocation(String pick_up_location) {
        pickupLocationTextField.sendKeys(pick_up_location);
    }

    public String getEnteredPickupLocation() {
        return pickupLocationTextField.getAttribute("value");
    }

    public boolean arePickedUpDatesMatch(String expectedDate) {
        return pickedUpAndDropOffDates.get(0).getAttribute("value").equals(expectedDate);
    }

    public boolean areDropOffUpDatesMatch(String expectedDate) {
        return pickedUpAndDropOffDates.get(1).getAttribute("value").equals(expectedDate);
    }

    public void selectTheCheckBoxWithParameter(String boxValue) {
        checkBoxes.forEach(box -> {
            if (box.getAttribute("value").equals(boxValue)) {
                actions.moveToElement(box).build().perform();
                box.click();
            }
        });
    }

    public boolean isTheCheckBoxSelected(String boxValue) {
        boolean isSelected = false;
        for (int i = 0; i < checkBoxes.size(); i++) {
            if (checkBoxes.get(i).getAttribute("value").equals(boxValue)) {
                isSelected = checkBoxes.get(i).isSelected();
            }
        }

        return isSelected;
    }

    public boolean areThePricesOfTheFilteredCarsInTheSelectedPriceRage(String priceRange) {
        String[] bordersOfPrices = priceRange.split("-");
        double minPrice = Double.parseDouble(bordersOfPrices[0]);
        double maxPrice = Double.parseDouble(bordersOfPrices[1]);
        List<Double> doubleOfPricesOfFilteredCars = getPricesOfFilteredCars(pricesOfTheFilteredCars);
        return checkPrices(doubleOfPricesOfFilteredCars, minPrice, maxPrice);
    }

    public List<Double> getPricesOfFilteredCars(List<WebElement> pricesOfTheFilteredCars) {
        List<String> strOfPricesOfFilteredCars = pricesOfTheFilteredCars.stream().map(WebElement::getText).toList();
        return strOfPricesOfFilteredCars.stream()
                .map(priceInString -> Double.parseDouble(priceInString.substring(1)))
                .toList();
    }

    public boolean checkPrices(List<Double> doubleOfPricesOfFilteredCars, double minPrice, double maxPrice) {
        for (double price : doubleOfPricesOfFilteredCars) {
            if (price > maxPrice || price < minPrice) {
                return false;
            }
        }

        return true;
    }

    public void clickOnTheFilteringPageSearchButton() {
        try {
            filteringPageSearchButton.click();
        }
        catch (Exception e) {
            filteringPageSearchButton.click();
        }
    }

    public boolean areTransmissionsOfFilteredCarsMatch(String expectedTransmission) {
        for (int i = 1; i < categoryAndTransmissionsOfFilteredCars.size(); i += 5) {
            if (!categoryAndTransmissionsOfFilteredCars.get(i).getText().equals(expectedTransmission)) {
                return false;
            }
        }
        return true;
    }

    public boolean areCategoriesOfFilteredCarsMatch(String expectedCategory) {
        for (int i = 3; i < categoryAndTransmissionsOfFilteredCars.size(); i += 5) {
            if (!categoryAndTransmissionsOfFilteredCars.get(i).getText().equals(expectedCategory)) {
                return false;
            }
        }
        return true;
    }

    public boolean arePickedUpLocationsOfFilteredCarsMatch(String expectedLocations) {
        List<String> filteredLocation = pickedUpLocationOfFilteredCars.stream().map(WebElement::getText).toList();
        return filteredLocation.stream().allMatch(expectedLocations::equals);
    }

    public void clickOnTheHighestSortButton() {
        try {
            highestAndLowestButtons.get(1).click();
        }
        catch (Exception e) {
            highestAndLowestButtons.get(1).click();
        }
    }

    public void clickOnTheLowestSortButton() {
        try {
            highestAndLowestButtons.get(0).click();
        }
        catch (Exception e) {
            highestAndLowestButtons.get(0).click();
        }
    }

    public boolean areAllPricesArrangedFromHighestToLowest() {
        List<Double> filteredPrices = getPricesOfFilteredCars(pricesOfTheFilteredCars);
        for (int i = 0; i < filteredPrices.size() - 1; i++) {
            if (filteredPrices.get(i) < filteredPrices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllPricesArrangedFromLowestToHighest() {
        List<Double> filteredPrices = getPricesOfFilteredCars(pricesOfTheFilteredCars);
        for (int i = 0; i < filteredPrices.size() - 1; i++) {
            if (filteredPrices.get(i) > filteredPrices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public void clickOnTheViewDealButton(int index) {

        brandOfSelectedCar = brandsOfCars.get(index - 1).getText();
        priceOfSelectedCar = getPricesOfFilteredCars(pricesOfTheFilteredCars).get(index - 1);
        pickupLocationOfSelectedCar = pickedUpLocationOfFilteredCars.get(0).getText();
        transmissionOfSelectedCar = categoryAndTransmissionsOfFilteredCars.get(1).getText();
        categoryOfSelectedCar = categoryAndTransmissionsOfFilteredCars.get(3).getText();
        try {
            viewDealButtons.get(index - 1).click();
        }
        catch (Exception e) {
            viewDealButtons.get(index - 1).click();
        }
        BrowserUtils.wait(1.0);
    }

    public Car getSelectedCar() {
        selectedCar.setCategoryOfSelectedCar(categoryOfSelectedCar);
        selectedCar.setTransmissionOfSelectedCar(transmissionOfSelectedCar);
        selectedCar.setPriceOfCar(priceOfSelectedCar);
        selectedCar.setPickupLocationOfSelectedCar(pickupLocationOfSelectedCar);
        selectedCar.setCarName(brandOfSelectedCar);

        return selectedCar;
    }
}
