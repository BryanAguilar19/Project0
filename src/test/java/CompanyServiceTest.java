import DAO.CompanyDAO;
import Service.CompanyService;
import Util.Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;

public class CompanyServiceTest {
    // Declaring instance variables within the class
    public CompanyService companyService;
    public CompanyDAO mockCompanyDAO;

    /**
     * Test for setting up connection to the Database
     */
    @Before
    public void CompanyService_setUp(){
        // Create a mock instance of the CompanyDAO class using Mockito.
        mockCompanyDAO = Mockito.mock(CompanyDAO.class);

        // Create an instance of the CompanyService class and inserting mockCompanyDAO
        companyService = new CompanyService(mockCompanyDAO);
    }

    @Test
    public void CompanyService_getCompany(){

    }

    @Test
    public void CompanyService_addCompany(){

    }
}
