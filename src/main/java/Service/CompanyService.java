package Service;

import DAO.CompanyDAO;
import Model.Company;

import java.util.List;

public class CompanyService {
    CompanyDAO companyDAO;
    //Constructor for CompanyService class that takes a CompanyDAO object as a parameter.
    public CompanyService(CompanyDAO authorDAO){
        //Initialize the 'companyDAO' instance variable with the provided 'authorDAO'.
        this.companyDAO = authorDAO;
    }
    public int getIdFromName(String name){
        return companyDAO.getCompanyIdByName(name);
    }

    /**
     * Call the 'getCompanyList' method on the 'companyDAO' object to retrieve a list of companies.
     * @return the list of companies retrieved from the data source.
     */
    public List<Company> getCompany(){
        return companyDAO.getCompanyList();
    }
}