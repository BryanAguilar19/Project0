package Service;

import DAO.CompanyDAO;
import Model.Company;
import Util.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CompanyService {
    CompanyDAO companyDAO;

    //Constructor for CompanyService class that takes a CompanyDAO object as a parameter.
    public CompanyService(CompanyDAO companyDAO) {
        //Initialize the 'companyDAO' instance variable with the provided 'authorDAO'.
        this.companyDAO = companyDAO;
    }

    /**
     * Call the 'getCompanyList' method on the 'companyDAO' object to retrieve a list of companies.
     *
     * @return the list of companies retrieved from the data source.
     */
    public List<Company> getCompany(Integer companyID, String companyName, String countryName) {
        return companyDAO.getCompanyList(companyID, companyName, countryName);
    }

    /**
     * Using CompanyDAO to add Company
     * @param company a Company object
     * @return the persisted company
     */
    public Company addCompany (Company company){
        return companyDAO.insertCompany(company);
    }
}

