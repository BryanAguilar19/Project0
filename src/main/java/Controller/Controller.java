package Controller;

import Model.Cars;
import Model.Company;
import Service.CarsService;
import Service.CompanyService;
import io.javalin.Javalin;
import io.javalin.http.Context;

//import java.util.ArrayList;
import java.util.List;

/**
 * A Controller is a class that manages web interaction - it takes in requests and produces responses
 * The reason why it's called a 'controller' is that it needs to route a request to the most appropriate method
 * to handle it
 */
public class Controller {
    CarsService carsService;
    CompanyService companyService;

    public Controller(CarsService carsService, CompanyService companyService){
        this.carsService = carsService;
        this.companyService = companyService;
    }
    //    ------------------------->  API Endpoints  <-------------------------  //
    public Javalin getAPI() {
        Javalin app = Javalin.create();
//    ------------------------->  Company API Section  <-------------------------  //
        /*
           GET API -> gets all companies
           Query Parameters: Company_Id, Name, Country
         */
        app.get("/api/v1/companies", this::filterCompany);



//    ------------------------->  Cars API Section   <-------------------------  //

        /* CREATE ||    POST -> Creates a new car resource */
        app.post("/api/v1/cars", this::insertCars);


        /* READ ||      GET -> gets all cars in the database
           You can specify optional query parameters for filtering:
           -> minPrice:             Minimum price filter
           -> maxPrice:             Maximum price filter
           -> minMpg:               Minimum MPG filter
           -> maxMpg:               Maximum MPG filter
           -> minYear:              Minimum year filter
           -> maxYear:              Maximum year filter
           -> sortMpgPriceRatio:    Sorting cars in descending MpgPrice Ratio
           -> companyId:            Company_id filter
         */
        app.get("/api/v1/cars", this::filterCars);


        /*  PUT -> Updates a specific car by its ID || @param -> car_id */
        app.put("/api/v1/cars/{car_id}", this::updateCarsOnId);

        return app;
    }


//    ------------------------->  Handlers  <-------------------------  //

    /* Handler for Cars API || @param ctx */
    private void filterCars(Context ctx) {
        // Extract optional query parameters
        Double minPrice = ctx.queryParam("minPrice") != null ? Double.valueOf(ctx.queryParam("minPrice")) : null;
        Double maxPrice = ctx.queryParam("maxPrice") != null ? Double.valueOf(ctx.queryParam("maxPrice")) : null;
        Double minMpg = ctx.queryParam("minMpg") != null ? Double.valueOf(ctx.queryParam("minMpg")) : null;
        Double maxMpg = ctx.queryParam("maxMpg") != null ? Double.valueOf(ctx.queryParam("maxMpg")) : null;
        Integer minYear = ctx.queryParam("minYear") != null ? Integer.valueOf(ctx.queryParam("minYear")) : null;
        Integer maxYear = ctx.queryParam("maxYear") != null ? Integer.valueOf(ctx.queryParam("maxYear")) : null;
        String sortMpgPriceRatio = ctx.queryParam("sortMpgPriceRatio");
        Integer companyId = ctx.queryParam("companyId") != null ? Integer.valueOf(ctx.queryParam("companyId")) : null;

        List<Cars> cars = carsService.filterCars(minPrice, maxPrice, minMpg,
                maxMpg, minYear, maxYear, sortMpgPriceRatio, companyId);

        if (cars == null || cars.isEmpty()) {
            ctx.status(404).json("No cars found for given criteria.");
        } else {
            ctx.json(cars);
        }
    }

    /* Handler for filtering Companies || @param context */
    private void filterCompany(Context context) {
        //Retrieve a list of Company objects from the CompanyService.
        List<Company> companyList = companyService.getCompany();

        //Serializing the companyList to JSON and send it as the response in the provided context.
        context.json(companyList);
    }
}
