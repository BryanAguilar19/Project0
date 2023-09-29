import DAO.CarsDAO;
import Model.Cars;
import Util.ConnectionSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.mockito.Mockito;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


public class CarsDAOTest {
    //Declaration of instant variables within the class
    Connection conn;
    CarsDAO carsDAO;


    /**
     * @Before every test
     * Setting up the database connection and CarsDAO instance for testing.
     */
    @Before
    public void setUpBefore(){
        //Obtain a database connection using Mockito.
        conn = mock(Connection.class);
        //Initialize a CarsDAO instance with the obtained connection.
        carsDAO = new CarsDAO(conn);
    }

    /**
     * Tests insertCar method
     */
    @Test
    public void insertCarTest() throws Exception {
//        Creating a Cars object for testing
        Cars testCar = new Cars();
        testCar.setCarId(111);
        testCar.setCarName("Test Car Model X");
        testCar.setYearMade(2023);
        testCar.setPrice(55000);
        testCar.setMpg(12);
        testCar.setCompanyFKey(7);

//        Mock the PreparedStatement
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(conn.prepareStatement(any(String.class))).thenReturn(preparedStatement);

//        Calling insertCar method
        carsDAO.insertCar(testCar);

//      Verify that the PreparedStatement was called with the expected SQL
        Mockito.verify(conn).prepareStatement("insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (?, ?, ?, ?, ?, ?)");

//      Verify that the appropriate set methods were called on the PreparedStatement
        Mockito.verify(preparedStatement).setInt(1, testCar.getCarId());
        Mockito.verify(preparedStatement).setString(2, testCar.getCarName());
        Mockito.verify(preparedStatement).setInt(3, testCar.getYearMade());
        Mockito.verify(preparedStatement).setDouble(4, testCar.getPrice());
        Mockito.verify(preparedStatement).setDouble(5, testCar.getMpg());
        Mockito.verify(preparedStatement).setInt(6, testCar.getCompanyFKey());

//      Verify that executeUpdate was called on the PreparedStatement
        Mockito.verify(preparedStatement, times(1)).executeUpdate();

    }


    @Test
    public void filterCarsTest() throws SQLException {
        // Create a list of test cars that you expect to be returned by the filterCars method
        List<Cars> expectedCars = new ArrayList<>();
        Cars car1 = new Cars();
        car1.setCarId(1);
        car1.setCarName("Car1");
        // Add other car properties here...
        expectedCars.add(car1);

        // Mock the PreparedStatement
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(conn.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        // Mock the ResultSet to return your expected data
        ResultSet resultSet = mock(ResultSet.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Simulate one row of data
        when(resultSet.getInt("car_id")).thenReturn(car1.getCarId());
        when(resultSet.getString("car_name")).thenReturn(car1.getCarName());
        // Set other expected result values for your test cars

        // Call the filterCars method with your filter criteria
        List<Cars> filteredCars = carsDAO.filterCars(5000.0, 30000.0, 20.0, 40.0, 2010, 2022, "desc", 7, null);

        // Verify that the PreparedStatement was called with the expected SQL
        verify(conn).prepareStatement(any(String.class)); // You can refine this verification

        // Verify that the appropriate set methods were called on the PreparedStatement with the expected values
        verify(preparedStatement).setDouble(1, 5000.0);
        verify(preparedStatement).setDouble(2, 30000.0);
        verify(preparedStatement).setDouble(3, 20.0);
        verify(preparedStatement).setDouble(4, 40.0);
        verify(preparedStatement).setInt(5, 2010);
        verify(preparedStatement).setInt(6, 2022);
        verify(preparedStatement).setString(7, "desc");
        verify(preparedStatement).setInt(8, 7);

        // Verify that executeQuery was called on the PreparedStatement
        verify(preparedStatement).executeQuery();

        // Verify that the expected cars were returned
        Assert.assertEquals(expectedCars, filteredCars);
    }



    /**
     * Performs necessary cleanup after each test
     * May include deleting test data from the database
     **/
    @After
    public void tearDownAfter() {
        // You can add cleanup code here if needed
        ConnectionSingleton.resetTestDatabase();
    }
}
