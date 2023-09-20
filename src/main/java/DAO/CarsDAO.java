package DAO;

import Model.Cars;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            PreparedStatement ps = conn.prepareStatement("insert into Cars (car_id, car_name, price, mpg, ) values (?, ?, ?, ?)");
            ps.setInt(1, car.getCarID());
            ps.setString(2, car.getCarName());
            ps.setInt(3, car.getCompanyFKey());

            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
