package Service;

import DAO.CompanyDAO;

public class CompanyService {
    CompanyDAO companyDAO;
    public CompanyService(CompanyDAO authorDAO){
        this.companyDAO = companyDAO;
    }
    public int getIdFromName(String name){
        return companyDAO.getCompanyIdByName(name);
    }
}