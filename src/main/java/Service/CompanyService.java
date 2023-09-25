package Service;

import DAO.CompanyDAO;

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
}