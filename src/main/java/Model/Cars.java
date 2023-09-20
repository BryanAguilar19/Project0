package Model;

import java.util.Objects;

public class Cars {
    //Setting my variables to private since it is only accessible within the same class
    private int carID;
    private String carName;
    private double price;
    private double mpg;
    private int companyFKey;

    //Setting my Contructor
    public Cars(int carID, String carName, double price, double mpg, int companyFKey) {
        this.carID = carID;
        this.carName = carName;
        this.price = price;
        this.mpg = mpg;
        this.companyFKey = companyFKey;
    }

    //Setters and Getters
    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
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

    //Hashcode() and equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return carID == cars.carID && Double.compare(price, cars.price) == 0 && Double.compare(mpg, cars.mpg) == 0 && companyFKey == cars.companyFKey && Objects.equals(carName, cars.carName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carID, carName, price, mpg, companyFKey);
    }
}
