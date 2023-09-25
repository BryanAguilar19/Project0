package DAO;

import Model.Cars;
import Service.CarsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Data Access Object:
 *      A style of object intended to contain methods that interact with a database, which manages the conversion from
 *      database records to/from java objects.
 *
 *
 */
public class CarsDAO {

    private Connection conn;

    public CarsDAO(Connection conn) {
        this.conn = conn;
    }

    /** JDBC Insert Method (SQL -> Database)
     * @param car
     */
    public void insertCar(Cars car){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (?, ?, ?, ?, ?);");
            ps.setInt(1, car.getCarId());
            ps.setString(2, car.getCarName());
            ps.setInt(3, car.getYearMade());
            ps.setDouble(4, car.getPrice());
            ps.setDouble(5, car.getMpg());
            ps.setInt(6, car.getCompanyFKey());
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /** Query to retrieve all cars -> allow for query parameters based on:
     *  Price, mpg, year, minMpgPriceRatio, companyId
     */
    public List<Cars> filterCars(Double minPrice, Double maxPrice, Double minMpg, Double maxMpg,
                                 Integer minYear, Integer maxYear, Double minMpgPriceRatio, Double maxMpgPriceRatio, Integer companyId) {
        List<Cars> cars = new ArrayList<>();
       // try {
//            Start building the SQL query
            StringBuilder queryBuilder = new StringBuilder("select * from Cars where 1=1");
       // }
        return cars;
    }
}

