package Controller;

import Service.CarsService;
import Service.CompanyService;
import io.javalin.Javalin;
import io.javalin.http.Context;


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
    public Javalin getAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
}