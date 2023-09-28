import DAO.CarsDAO;
import Util.ConnectionSingleton;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

public class projectTest {
    //Declaration of instant variables within the class
    Connection conn;
    CarsDAO carsDAO;

    //Setting up the database connection and CarsDAO instance for testing.
    @Before
    public void setUpBefore(){
        //Obtain a database connection using ConnectionSingleton.
        conn = ConnectionSingleton.getConnection();
        //Initialize a CarsDAO instance with the obtained connection.
        carsDAO = new CarsDAO(conn);
    }


    @Test
    public void insertCarTest(){

    }

    @Test
    public void insertCompanyTest(){

    }





}
