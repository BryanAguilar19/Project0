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

    /** Query to retrieve cars by company from the database
     *
     */
    public List<Cars> getCarsByCompanyId(int companyId) {
        List<Cars> cars = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("select * from Cars where company_id = ?")) {
            ps.setInt(1, companyId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                Create Car objects and add them to the list
                Cars car = new Cars();
                car.setCarId(rs.getInt("car_id"));
                car.setCarName(rs.getString("car_name"));
                car.setYearMade(rs.getInt("year_made"));
                car.setPrice(rs.getFloat("price"));
                car.setMpg(rs.getFloat("mpg"));
                car.setCompanyFKey(rs.getInt("company_id"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }


}
