package Controller;

import Model.Cars;
import Service.CarsService;
import Service.CompanyService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Objects;


/**
 * A Controller is a class that manages web interaction - it takes in requests and produces responses
 * The reason why it's called a 'controller' is that it need to route a request to the most appropriate method
 * to handle it
 */
public class Controller {
    CarsService carsService;
    CompanyService companyService;

    public Controller(CarsService carsService, CompanyService companyService){
        this.carsService = carsService;
        this.companyService = companyService;
    }

//    API Endpoints go here:
    public Javalin getAPI() {
        Javalin app = Javalin.create();
//        Define endpoints below
        /**
         *  GET API -> gets all cars in database
         *  You can specify optional query parameters for filtering:
         *  - minPrice: Minimum price filter
         *  - maxPrice: Maximum price filter
         *  - minMpg: Minimum MPG filter
         *  - maxMpg: Maximum MPG filter
         *  - minYear: Minimum year filter
         *  - maxYear: Maximum year filter
         *  - sortDirectionOnMpgPriceRatio: Sorting cars in descending MpgPrice Ratio
         *  - companyId: Company Id filter
         */
        app.get("/api/v1/cars/", this::filterCars);


        /** GET API -> gets all companies
         *
         */
//        app.get("/api/v1/cars/companyList=", this::getCompanyList);

        return app;
    }

    /**
     * Handler for filterCars API
     * @param ctx
     */
    private void filterCars(Context ctx) {
        // Extract optional query parameters
        Double minPrice = ctx.queryParam("minPrice") != null ? Double.valueOf(ctx.queryParam("minPrice")) : null;
        Double maxPrice = ctx.queryParam("maxPrice") != null ? Double.valueOf(ctx.queryParam("maxPrice")) : null;
        Double minMpg = ctx.queryParam("minMpg") != null ? Double.valueOf(ctx.queryParam("minMpg")) : null;
        Double maxMpg = ctx.queryParam("maxMpg") != null ? Double.valueOf(ctx.queryParam("maxMpg")) : null;
        Integer minYear = ctx.queryParam("minYear") != null ? Integer.valueOf(ctx.queryParam("minYear")) : null;
        Integer maxYear = ctx.queryParam("maxYear") != null ? Integer.valueOf(ctx.queryParam("maxYear")) : null;
        String sortDirectionOnMpgPriceRatio = ctx.queryParam("sortDirectionOnMpgPriceRatio");
        Integer companyId = ctx.queryParam("companyId") != null ? Integer.valueOf(ctx.queryParam("companyId")) : null;

        List<Cars> cars = carsService.filterCars(minPrice, maxPrice, minMpg,
                maxMpg, minYear, maxYear, sortDirectionOnMpgPriceRatio, companyId);

        if (cars == null || cars.isEmpty()) {
            ctx.status(404).json("No cars found for given criteria.");
        } else {
            ctx.json(cars);
        }
    }
}