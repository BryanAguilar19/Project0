package Service;

import DAO.CarsDAO;
import DAO.CompanyDAO;
import Model.Cars;

/**
 * Service used for CRUD operations on Cars
 * Create Read Update Delete
 * (ie, an application that doesn't require any complicated programming logic - just a path from user input to data layer)
 */
public class CarsService {
    private CarsDAO carsDAO;
    private CompanyService companyService;

    public CarsService(CarsDAO carsDAO, CompanyService companyService){
        this.carsDAO = carsDAO;
        this.companyService = companyService;
    }

    /**
     * check if car match already exists in the database, if it does, throw an exception.
     * otherwise, save the car.
     * @param c
     */
    public void saveCar(Cars c, String name) throws Exception {
        int companyId = companyService.getIdFromName(name);
    }
}
