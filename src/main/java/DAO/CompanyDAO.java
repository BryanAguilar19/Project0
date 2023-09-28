package DAO;

import Model.Company;
import Util.ConnectionSingleton;

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




    /**
     * Define a public List method to retrieve a list of Company objects from a database.
     */
    public List<Company> getCompanyList(){
        //Initialize an empty list to store Company objects.
        List<Company> companyList = new ArrayList<>();

        try {
            //SQL statement to select all records from the 'Company' table.
            PreparedStatement ps = conn.prepareStatement("select * from Company order by company_id asc");

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

    /**
     * Inserts company information into the database
     * @param company a company object
     */
    public Company insertCompany(Company company){
        //install connection to the Singleton File
        Connection conn = ConnectionSingleton.getConnection();
        //Applying try catch
        try{
            // defines the SQL statement for inserting a company, store in String
            String sql = "insert into Company (company_id, company_name, country_name) values (?,?,?)";

            //Prepared Statement to set and get string methods for info collection
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, company.getCompanyID());
            ps.setString(2, company.getCompanyName());
            ps.setString(3, company.getCountryOrigin());

            //Execute the query to insert company record
            ps.executeUpdate();

            return company;
        }catch (SQLException e){
            //Handle SQL Exceptions if there are any errors
            System.out.println("Execution of SQL did not go through: " + e.getMessage());
        }
        return null;
    }

}


