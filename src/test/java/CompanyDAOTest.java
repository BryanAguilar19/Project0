import DAO.CarsDAO;
import DAO.CompanyDAO;
import Model.Company;
import Util.ConnectionSingleton;
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


    @Before
    public void CompanyDAO_connectionToCompany(){
        //Obtain a database connection using ConnectionSingleton.
        conn = ConnectionSingleton.getConnection();
        //Initialize a CompanyDAO instance with the obtained connection.
        companyDAO = new CompanyDAO(conn);
    }

    @Test
    public void CompanyDAO_insertCompany(){

    }

    @Test
    public void CompanyDAO_getCompanyList(){
        List<Company> companyList = companyDAO.getCompanyList();

    }
}
