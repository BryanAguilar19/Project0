package DAO;

import Model.Company;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {
    Connection conn;
    public CompanyDAO(Connection conn){
        this.conn = conn;
    }

    /** This method retrieves a Company's ID based off its name. If no company is found, return 0.
     */
    public int getCompanyIdByName(String name){
        try{
            PreparedStatement ps = conn.prepareStatement("select company_id from Company where company_name = ?");
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

    /**
     * Define a public List method to retrieve a list of Company objects from a database.
     */
    public List<Company> getCompanyList(){
        //Initialize an empty list to store Company objects.
        List<Company> companyList = new ArrayList<>();

        try {
            //SQL statement to select all records from the 'Company' table.
            PreparedStatement ps = conn.prepareStatement("select * from Company");

            //Execute the SQL query and retrieve the result set.
            ResultSet rs = ps.executeQuery();

            //Using a While Loop, we iterate through the result set to create Company objects and add them to the list.
            while (rs.next()) {
                //Extract data from the result set for each row.

                int dbCompanyId = rs.getInt("company_id");
                String dbCompanyName = rs.getString("company_name");
                String dbCompanyCountry = rs.getString("country_name");

                //Create a new Company object with the extracted data (Calling Constructor from Company Class).
                Company com = new Company(dbCompanyId, dbCompanyName, dbCompanyCountry);

                //Add the Company object to the list.
                companyList.add(com);
            }
        } catch (SQLException e) {
            //Handle any SQL-related exceptions by printing the stack trace.
            e.printStackTrace();
        }
        //Return the list of Company objects retrieved from the database.
        return companyList;
    }

}
