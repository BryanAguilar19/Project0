package Service;

import DAO.CarsDAO;
import Model.Cars;

import java.util.List;

/**
 * Service used for CRUD operations on Cars
 * Create Read Update Delete
 * (ie, an application that doesn't require any complicated programming logic - just a path from user input to data layer)
 */
public class CarsService {
    private CarsDAO carsDAO;
    private CompanyService companyService;

    public CarsService(CarsDAO carsDAO, CompanyService companyService) {
        this.carsDAO = carsDAO;
        this.companyService = companyService;
    }

    /**
     * Create a new car and insert it into the database.
     *
     * @param car           The car to be created and saved.
     * @param companyName   The name of the company associated with the car.
     * @throws Exception    If there's an issue with creating or saving the car.
     */
    public void insertCar(Cars car, String companyName) throws Exception {
        // 1. Get the company ID based on the provided company name
        int companyId = companyService.getIdFromName(companyName);

        // 2. Set the company ID in the car object
        car.setCompanyFKey(companyId);

        // 3. Call the DAO to insert the new car into the database
        carsDAO.insertCar(car);
    }

    /**
     * Filter cars based on various criteria.
     *
     * @param minPrice                     Minimum price filter
     * @param maxPrice                     Maximum price filter
     * @param minMpg                       Minimum MPG filter
     * @param maxMpg                       Maximum MPG filter
     * @param minYear                      Minimum year filter
     * @param maxYear                      Maximum year filter
     * @param sortMpgPriceRatio Sorting descending for MPG/Price ratio
     * @param companyId                    Company ID filter
     * @return List of filtered cars
     */
    public List<Cars> filterCars(Double minPrice, Double maxPrice, Double minMpg, Double maxMpg,
                                 Integer minYear, Integer maxYear, String sortMpgPriceRatio, Integer companyId, Integer carId) {
        return carsDAO.filterCars(minPrice, maxPrice, minMpg, maxMpg, minYear, maxYear, sortMpgPriceRatio, companyId, carId);
    }
}
