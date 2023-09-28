import DAO.CarsDAO;
import DAO.CompanyDAO;
import Model.Company;
import Util.ConnectionSingleton;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOTest {
    //Declaration of instant variables within the class
    public CompanyDAO companyDAO;
    public Connection conn;

    /**
     * Setting up connection with the Database
     */
    @Before
    public void CompanyDAO_connectionToCompany(){
        //Obtain a database connection using ConnectionSingleton.
        conn = ConnectionSingleton.getConnection();
        //Initialize a CompanyDAO instance with the obtained connection.
        companyDAO = new CompanyDAO(conn);
    }

    /**
     * Tests the 'getCompanyList' method with specific filter criteria and checks for non-null results
     */
    @Test
    public void CompanyDAO_getCompanyList(){
        // Test getCompanyList method with specific filter criteria
        List<Company> companyList = companyDAO.getCompanyList(1, "TestName","TestCountry");
        //Add assertions to verify the results
        Assert.assertNotNull(companyList);
    }

    /**
     * Test the 'insertCompany' method with specific filter criteria and
     * asserts that the retrieved company matches the inserted company
     */
    @Test
    public void CompanyDAO_testInsertCompany() {
        // Create a test company (c1)
        Company c1 = new Company(55, "TestName", "TestCountry");

        // Inserting the company into the database
        companyDAO.insertCompany(c1);

        // Retrieving the company from the database
        List<Company> isCompanyInserted = companyDAO.getCompanyList(c1.getCompanyID(), c1.getCompanyName(), c1.getCountryOrigin());

        // Assert that the retrieved company matches the inserted company
        Assert.assertTrue(isCompanyInserted.contains(c1));
    }

    /**
     * After each test, it is essential to drop and reset the tables used
     * for the test to maintain a clean isolated testing environment
     */
    @After
    public void CompanyDAO_tearDown(){
        ConnectionSingleton.resetTestDatabase();
    }
}
