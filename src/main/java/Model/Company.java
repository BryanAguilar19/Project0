package Model;

import java.util.Objects;

public class Company {
    private Integer companyID;
    private String companyName;
    private String countryOrigin;

    //Constructor
    public Company(Integer companyID, String companyName, String countryOrigin) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.countryOrigin = countryOrigin;
    }

    //default constructor
    public Company(){
    }

    //Getters and Setters

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyID=" + companyID +
                ", companyName='" + companyName + '\'' +
                ", countryOrigin='" + countryOrigin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyID, company.companyID) && Objects.equals(companyName, company.companyName) && Objects.equals(countryOrigin, company.countryOrigin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyID, companyName, countryOrigin);
    }
}
