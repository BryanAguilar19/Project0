import Controller.Controller;
import DAO.CarsDAO;
import Model.Cars;
import Service.CarsService;
import Util.ConnectionSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import java.sql.Connection;
import java.util.List;

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
        //Obtain a database connection using ConnectionSingleton.
        conn = ConnectionSingleton.getConnection();
        //Initialize a CarsDAO instance with the obtained connection.
        carsDAO = new CarsDAO(conn);
    }

    /**
     * Tests insertCar method
     */
    @Test
    public void insertCarTest(){
//        Creating a Cars object for testing
        Cars testCar = new Cars();
        testCar.setCarId(111);
        testCar.setCarName("Test Car Model X");
        testCar.setYearMade(2023);
        testCar.setPrice(55000);
        testCar.setMpg(12);
        testCar.setCompanyFKey(7);

//        Calling insertCar method
        carsDAO.insertCar(testCar);

//          Fetch list of cars filtered by CarId
        List<Cars> filteredCars = carsDAO.filterCars(null, null, null, null, null, null, null, null, testCar.getCarId());

//          Verify that the inserted car exists in the list of the filtered cars
        boolean carFound = false;
        for (Cars car : filteredCars) {
            if (car.getCarId() == testCar.getCarId()) {
                carFound = true;

//              Verifying the inserted car's details match the expected attribute values
                Assert.assertEquals(testCar.getCarName(), car.getCarName());
                Assert.assertEquals(testCar.getYearMade(), car.getYearMade());
                Assert.assertEquals(testCar.getPrice(), car.getPrice(), 0.001);
                Assert.assertEquals(testCar.getMpg(), car.getMpg(), 0.001);
                Assert.assertEquals(testCar.getCompanyFKey(), car.getCompanyFKey());
                break;
            }
        }
        Assert.assertTrue(carFound);
    }


    @Test
    public void filterCarsTest(){
//        Test filterCars method with specific filter criteria
        List<Cars> filteredCars = carsDAO.filterCars(20000.0, 30000.0, 15.0, 30.0, 2015, 2023, "asc", 1, null);
//        Add assertions to verify the results
        Assert.assertNotNull(filteredCars);
        Assert.assertFalse(filteredCars.isEmpty());

    }

    /**
     * Performs necessary cleanup after each test
     * May include deleting test data from the database
     **/
    @After
    public void tearDownAfter() {

    }



}
