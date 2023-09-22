package DAO;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDAO {
    Connection conn;
    public CompanyDAO(Connection conn){
        this.conn = conn;
    }
    /** This method retrieves a Company's ID based off its name. If no company is found, return 0.
     */
    public int getCompanyIdByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement("select company_id from company where company_name = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("company_id");
                return id;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}
