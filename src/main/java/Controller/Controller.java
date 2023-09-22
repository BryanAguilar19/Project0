package Controller;

import Model.Cars;
import Service.CarsService;
import Service.CompanyService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;


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
//        Define endpoints
        app.get("/cars/{company_id}", this::getCarsByCompanyId);



        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getCarsByCompanyId(Context context) {
        int companyId = Integer.parseInt(context.pathParam("company_id"));
        List<Cars> cars = carsService.getCarsByCompanyId(companyId);
        if (cars == null || cars.isEmpty()) {
            context.status(404).json("No cars found for the given company Id. Please give company_id 1-8.");
        } else {
            context.json(cars);
        }

    }
}