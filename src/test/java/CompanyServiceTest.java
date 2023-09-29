import DAO.CompanyDAO;
import Model.Company;
import Service.CompanyService;
import Util.Application;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {
    // Declaring instance variables within the class
    public CompanyService companyService;
    public CompanyDAO mockCompanyDAO;

    /**
     * Test for setting up connection to the Database
     */
    @Before
    public void CompanyService_setUp() {
        // Create a mock instance of the CompanyDAO class using Mockito.
        mockCompanyDAO = Mockito.mock(CompanyDAO.class);

        // Create an instance of the CompanyService class and inserting mockCompanyDAO
        companyService = new CompanyService(mockCompanyDAO);
    }

    @Test
    public void testGetCompany() {
//    Create a list of mock Company objects to return
        List<Company> expectedCompanies = new ArrayList<>();

//    Add the expected companies to the list:
        Company company1 = new Company(1, "Company1", "Country1");
        Company company2 = new Company(2, "Company2", "Country2");
        expectedCompanies.add(company1);
        expectedCompanies.add(company2);

//    Mock the behavior of the CompanyDAO to return the expected companies
        when(mockCompanyDAO.getCompanyList(any(), any(), any())).thenReturn(expectedCompanies);

//    Call the getCompany method of companyService
        List<Company> actualCompanies = companyService.getCompany(1, "TestName", "TestCountry");

//    Verify that the mockCompanyDAO's getCompanyList method was called with the expected arguments
        verify(mockCompanyDAO).getCompanyList(1, "TestName", "TestCountry");

//    Verify that the actualCompanies matches the expectedCompanies
        Assert.assertEquals(expectedCompanies, actualCompanies);

    }


    @Test
    public void testAddCompany() {
        // Create a test company
        Company testCompany = new Company(55, "TestName", "TestCountry");

        // Mock the behavior of the mockCompanyDAO to return the test company when insertCompany is called
        when(mockCompanyDAO.insertCompany(testCompany)).thenReturn(testCompany);

        // Call the addCompany method of companyService
        Company addedCompany = companyService.addCompany(testCompany);

        // Verify that the mockCompanyDAO's insertCompany method was called with the test company
        verify(mockCompanyDAO).insertCompany(testCompany);

        // Verify that the addedCompany matches the testCompany
        Assert.assertEquals(testCompany, addedCompany);
    }
}

