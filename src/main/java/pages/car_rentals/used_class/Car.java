package pages.car_rentals.used_class;

import pages.BasePage;

public class Car extends BasePage {

	private String carName;

	private double priceOfCar;

	private String pickupLocationOfSelectedCar;

	private String transmissionOfSelectedCar;

	private String categoryOfSelectedCar;

	public Car() {

	}

	public Car(String carName, double priceOfCar) {
		this.carName = carName;
		this.priceOfCar = priceOfCar;
	}

	public void setPickupLocationOfSelectedCar(String pickupLocationOfSelectedCar) {
		this.pickupLocationOfSelectedCar = pickupLocationOfSelectedCar;
	}

	public void setTransmissionOfSelectedCar(String transmissionOfSelectedCar) {
		this.transmissionOfSelectedCar = transmissionOfSelectedCar;
	}

	public void setCategoryOfSelectedCar(String categoryOfSelectedCar) {
		this.categoryOfSelectedCar = categoryOfSelectedCar;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getPickupLocationOfSelectedCar() {
		return pickupLocationOfSelectedCar;
	}

	public String getTransmissionOfSelectedCar() {
		return transmissionOfSelectedCar;
	}

	public String getCategoryOfSelectedCar() {
		return categoryOfSelectedCar;
	}

	public void setPriceOfCar(double priceOfCar) {
		this.priceOfCar = priceOfCar;
	}

	public String getSelectedCarName() {
		return carName;
	}

	public double getPriceOfSelectedCar() {
		return priceOfCar;
	}

}
