package Model;

import java.util.Objects;

public class Cars {
    //Setting my variables to private since it is only accessible within the same class
    private int carId;
    private String carName;
    private int yearMade;
    private double price;
    private double mpg;
    private int companyFKey;


//    Initializing Cars object
    public Cars() {

    }

    //Setting my Contructor
    public Cars(int carId, String carName, int year_made, double price, double mpg, int companyFKey) {
        this.carId = carId;
        this.carName = carName;
        this.yearMade = year_made;
        this.price = price;
        this.mpg = mpg;
        this.companyFKey = companyFKey;
    }


    //Setters and Getters

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getYearMade() {
        return yearMade;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMpg() {
        return mpg;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public int getCompanyFKey() {
        return companyFKey;
    }

    public void setCompanyFKey(int companyFKey) {
        this.companyFKey = companyFKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return carId == cars.carId && yearMade == cars.yearMade && Double.compare(price, cars.price) == 0 && Double.compare(mpg, cars.mpg) == 0 && companyFKey == cars.companyFKey && Objects.equals(carName, cars.carName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, carName, yearMade, price, mpg, companyFKey);
    }
}