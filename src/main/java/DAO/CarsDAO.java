package DAO;

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
    public void insertCar(Car car){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into Cars (car_id, car_name, price, mpg, ) values (?, ?, ?, ?)");
            ps.setInt(1, p.getPaintingId());
            ps.setString(2, p.getTitle());
            ps.setInt(3, p.getYearMade());
            ps.setInt(4, p.getAuthorFkey());
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
