import DAO.CarsDAO;
import Model.Cars;
import Service.CarsService;
import Service.CompanyService;
import Util.ConnectionSingleton;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CarsServiceTest {

    CarsService carsService;
    CarsDAO carsDAO;
    CompanyService companyService;

    /**
     * Setting up the database connection in CarServices instance for testing.
     */
    @Before
    public void setUp() {
        carsDAO = mock(CarsDAO.class);
        companyService = mock(CompanyService.class);
        carsService = new CarsService(carsDAO, companyService);
    }

    /**
     * Tests the insertCar method by verifying that it correctly
     * inserts a car record into the database.
     * @throws Exception
     */
    @Test
    public void insertCarTest() throws Exception {
        // Create a test car
        Cars testCar = new Cars();
        testCar.setCarId(111);
        testCar.setCarName("Test Car Model X");
        testCar.setYearMade(2023);
        testCar.setPrice(55000);
        testCar.setMpg(12);
        testCar.setCompanyFKey(7);

        // Mock any necessary behavior in the companyService if needed

        // Call the insertCar method
        carsService.insertCar(testCar);

        // Verify that the insertCar method of carsDAO was called with the test car
        verify(carsDAO).insertCar(testCar);
    }

    /**
     * Tests the filterCars method by verifying that it correctly filters
     * and retrieves a list of cars based on criteria.
     */
    @Test
    public void filterCarsTest() {
        // Create test filter criteria
        Double minPrice = 5000.0;
        Double maxPrice = 30000.0;
        Double minMpg = 20.0;
        Double maxMpg = 40.0;
        Integer minYear = 2010;
        Integer maxYear = 2022;
        String sortMpgPriceRatio = "desc";
        Integer companyId = 7;
        Integer carId = null;

        // Mock the behavior of the carsDAO
        List<Cars> expectedFilteredCars = new ArrayList<>(); // Add expected cars here

        when(carsDAO.filterCars(minPrice, maxPrice, minMpg, maxMpg, minYear, maxYear, sortMpgPriceRatio, companyId, carId))
                .thenReturn(expectedFilteredCars);

        // Call the filterCars method
        List<Cars> filteredCars = carsService.filterCars(minPrice, maxPrice, minMpg, maxMpg, minYear, maxYear, sortMpgPriceRatio, companyId, carId);

        // Verify that the expectedFilteredCars is returned
        assertEquals(expectedFilteredCars, filteredCars);
    }

    /**
     * Tests the filterCars method with null parameters to verify that it behaves as expected.
     */
    @Test
    public void filterCarsWithNullParametersTest() {
//        Test filtering with no parameters
        Double minPrice = null;
        Double maxPrice = null;
        Double minMpg = null;
        Double maxMpg = null;
        Integer minYear = null;
        Integer maxYear = null;
        String sortMpgPriceRatio = null;
        Integer companyId = null;
        Integer carId = null;

//    Mock the behavior of the carsDAO to return a different set of expectedFilteredCars
        List<Cars> differentExpectedFilteredCars = new ArrayList<>();
//    Add different expected cars here
        when(carsDAO.filterCars(minPrice, maxPrice, minMpg, maxMpg, minYear, maxYear, sortMpgPriceRatio, companyId, carId))
            .thenReturn(differentExpectedFilteredCars);

        List<Cars> filteredCars = carsService.filterCars(null, null, null, null, null, null, null, null, null);

        assertEquals(differentExpectedFilteredCars, filteredCars);


    }

    /**
     * Perform cleanup or reset operations if needed
     */
    @After
    public void tearDown() {
        ConnectionSingleton.resetTestDatabase();
    }
}
