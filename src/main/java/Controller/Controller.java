package Controller;

import Model.Cars;
import Model.Company;
import Service.CarsService;
import Service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.ArrayList;

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
        /**
         * GET API -> gets all companies
         *Query Parameters: Company_Id, Name, Country
         */
        app.get("/api/v1/companies", this::getCompany);

        /*
          POST API -> add company
          Query Parameters: Company_Id, Name, Country
         */
        app.post("/api/v1/companies", this::postCompany);

        /**
         * GET API -> get a single ID from company Name
         *
         */



//    ------------------------->  Cars API Section   <-------------------------  //

        /* CREATE ||    POST -> Creates a new car resource */

        app.post("/api/v1/cars", this::insertCar);

        //app.post("/api/v1/cars", this::insertCars);



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


        return app;
    }


//    ------------------------->  Handlers  <-------------------------  //

    /**
     * Handler for inserting a new car
     * @param ctx The Javalin context
     */
    private void insertCar(Context ctx) {
        try {
//            Parse JSON request body into a Cars object
            Cars newCar = ctx.bodyAsClass(Cars.class);
//            Get company name associated with the car from the request
            String companyName = ctx.queryParam("companyName");
//            Insert new car using CarsService
            carsService.insertCar(newCar);
            ctx.status(201).json("Car successfully created.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).json("Failed to create car: " + e.getMessage());
        }
    }

    /* Handler for GET Car API || @param ctx */
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
        Integer carId = ctx.queryParam("carId") != null ? Integer.valueOf(ctx.queryParam("carId")) : null;

        List<Cars> cars = carsService.filterCars(minPrice, maxPrice, minMpg,
                maxMpg, minYear, maxYear, sortMpgPriceRatio, companyId, carId);

        if (cars == null || cars.isEmpty()) {
            ctx.status(404).json("No cars found for given criteria.");
        } else {
            ctx.json(cars);
        }
    }



    /**
     * Retrieving all Companies
     * @param context a context object that URL will pass through request and response
     */
    private void getCompany(Context context) {
        //Retrieve a list of Company objects from the CompanyService.
        List<Company> companyList = companyService.getCompany();

        //Serializing the companyList to JSON and send it as the response in the provided context.
        context.json(companyList);
    }

    /**
     * Handler for POST request for adding a new company
     * @param context representing the URL request and response
     */
    private void postCompany(Context context){
        //  Creating an Object Mapper object for deserialization
        ObjectMapper op = new ObjectMapper();

        try {
            // Deserialize the request body (JSON) into a Company object
            Company company = op.readValue(context.body(), Company.class);

            Company addCompany = companyService.addCompany(company);

            // If added successfully, respond with the added company as JSON
            //Else return the status error
            if(addCompany!=null){
                context.json(op.writeValueAsString(addCompany));
                context.status(201).json("Company Successfully Added.............");
            }else{
                context.status(404).json("Company was not added, please try again");
            }
        }
        catch (JsonProcessingException e){
            // Handle JSON processing exception
            context.status(400).json("Invalid JSON request body: " + e.getMessage());
        }
    }
}


