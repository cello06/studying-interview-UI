package utils;

import lombok.Data;
import pages.HomePage;
import pages.car_rentals.CarRentalsCheckoutPage;
import pages.car_rentals.CarRentalsDetailPage;
import pages.car_rentals.CarRentalsFilteringPage;
import pages.car_rentals.CarRentalsHomePage;
@Data
public class Pages {
    protected HomePage homePage;

    //CAR-RENTALS
    protected CarRentalsHomePage carRentalsHomePage;
    protected CarRentalsDetailPage carRentalsDetailPage;
    protected CarRentalsFilteringPage carRentalsFilteringPage;
    protected CarRentalsCheckoutPage carRentalsCheckoutPage;
    public Pages(){
        homePage = new HomePage();

        //CAR-RENTALS
        carRentalsHomePage = new CarRentalsHomePage();
        carRentalsDetailPage = new CarRentalsDetailPage();
        carRentalsFilteringPage = new CarRentalsFilteringPage();
        carRentalsCheckoutPage = new CarRentalsCheckoutPage();
    }
}
