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
     * check if car match already exists in the database, if it does, throw an exception.
     * otherwise, save the car.
     *
     * @param c    The car to be saved.
     * @param name The name of the company associated with the car.
     * @throws Exception If there's an issue with saving the car.
     */
    public void saveCar(Cars c, String name) throws Exception {
        int companyId = companyService.getIdFromName(name);

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
                                 Integer minYear, Integer maxYear, String sortMpgPriceRatio, Integer companyId) {
        return carsDAO.filterCars(minPrice, maxPrice, minMpg, maxMpg, minYear, maxYear, sortMpgPriceRatio, companyId);
    }
}
