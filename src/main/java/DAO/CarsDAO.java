package DAO;

import Model.Cars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Data Access Object:
 *      A style of object intended to contain methods that interact with a database, which manages the conversion from
 *      database records to/from java objects.
 */
public class CarsDAO {

    private Connection conn;

    /**
     * Establishing connection to CarsDAO
     * @param conn a Connection object
     */
    public CarsDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * JDBC Insert Method (SQL -> Database)
     *
     * @param car a Car parameter object
     */
    public void insertCar(Cars car) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into Cars (car_id, car_name, year_made, price, mpg, company_id) values (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, car.getCarId());
            ps.setString(2, car.getCarName());
            ps.setInt(3, car.getYearMade());
            ps.setDouble(4, car.getPrice());
            ps.setDouble(5, car.getMpg());
            ps.setInt(6, car.getCompanyFKey());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Query to retrieve all cars -> allow for query parameters based on:
     * Price, mpg, year, minMpgPriceRatio, companyId
     */
    public List<Cars> filterCars(Double minPrice, Double maxPrice, Double minMpg, Double maxMpg,
                                 Integer minYear, Integer maxYear, String sortMpgPriceRatio, Integer companyId, Integer carId) {
        List<Cars> cars = new ArrayList<>();

        try {
//          Start building the SQL query -- First with min/max query params
            StringBuilder sql = new StringBuilder("select * from Cars where 1=1");
            if (minPrice != null) {sql.append(" and price >= ?");}
            if (maxPrice != null) {sql.append(" and price <= ?");}
            if (minMpg != null) {sql.append(" and mpg >= ?");}
            if (maxMpg != null) {sql.append(" and mpg <= ?");}
            if (minYear != null) {sql.append(" and year_made >= ?");}
            if (maxYear != null) {sql.append(" and year_made <= ?");}
//          Add filtering by companyId if provided
            if (companyId != null) {
                sql.append(" and company_id = ?"); }
//              Add sorting condition based on mpgPriceRatio
            if (carId != null) {sql.append(" and car_id = ?");};
            if ("desc".equals(sortMpgPriceRatio)) {
                sql.append(" order by (mpg / price) desc");
            } else {
//               Default sorting behavior (no sorting specified or "asc" provided)
                sql.append(" order by car_id asc");
            }

//            Debug: Print the SQL query and parameter values
//            System.out.println("Generated SQL Query: " + sql.toString());
//            System.out.println("Parameters - minPrice: " + minPrice + ", maxPrice: " + maxPrice + ", minMpg: " + minMpg + ", maxMpg: " + maxMpg +
//                    ", minYear: " + minYear + ", maxYear: " + maxYear + ", sortMpgPriceRatio: " + sortMpgPriceRatio + ", companyId: " + companyId + ", carId: " + carId);

//          Execute SQL Query
            try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
//          Ensures the parameter index is correct if parameter is used
                int parameterIndex = 1;
                if (minPrice != null) {ps.setDouble(parameterIndex++, minPrice);}
                if (maxPrice != null) {ps.setDouble(parameterIndex++, maxPrice);}
                if (minMpg != null) {ps.setDouble(parameterIndex++, minMpg);}
                if (maxMpg != null) {ps.setDouble(parameterIndex++, maxMpg);}
                if (minYear != null) {ps.setInt(parameterIndex++, minYear);}
                if (maxYear != null) {ps.setInt(parameterIndex++, maxYear);}
                if (sortMpgPriceRatio != null) {ps.setString(parameterIndex++, sortMpgPriceRatio);}
//              Set companyId as a parameter if companyId is provided
                if (companyId != null) {ps.setInt(parameterIndex++, companyId);}
                if (carId != null) {ps.setInt(parameterIndex, carId);}
//              Extract data for parameters
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Cars car = new Cars();
                    car.setCarId(rs.getInt("car_id"));
                    car.setCarName(rs.getString("car_name"));
                    car.setYearMade(rs.getInt("year_made"));
                    car.setPrice(rs.getFloat("price"));
                    car.setMpg(rs.getFloat("mpg"));
                    car.setCompanyFKey(rs.getInt("company_id"));
//                  Calculate MPG/Price ratio
                    car.setMpgPriceRatio((car.getMpg() / car.getPrice()) * 1000);
                    cars.add(car);}
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
}

