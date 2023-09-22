package Util;

import DAO.CarsDAO;
import DAO.CompanyDAO;

import java.sql.Connection;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Application {
    public static void main(String[] args) {
        Connection conn = ConnectionSingleton.getConnection();
        CarsDAO carsDao = new CarsDAO(conn);
        CompanyDAO companyDAO = new CompanyDAO(conn);
//        CarsService carsService = new CarsService(CarsDAO);
//        CompanyService companyService = new CompanyService(CompanyDAO);

          Scanner sc = new Scanner(System.in);

    }


}