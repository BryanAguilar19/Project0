package Util;

import Controller.Controller;
import DAO.CarsDAO;
import DAO.CompanyDAO;
import Service.CarsService;
import Service.CompanyService;
import io.javalin.Javalin;

import java.sql.Connection;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Application {
    public static void main(String[] args) {
        Connection conn = ConnectionSingleton.getConnection();
        CarsDAO carsDao = new CarsDAO(conn);
        CompanyDAO companyDAO = new CompanyDAO(conn);
        CompanyService companyService = new CompanyService(companyDAO);
        CarsService carsService = new CarsService(carsDao, companyService);

        Scanner sc = new Scanner(System.in);
        Controller controller = new Controller(carsService, companyService);
        Javalin server = controller.getAPI();
        server.start();
    }


}