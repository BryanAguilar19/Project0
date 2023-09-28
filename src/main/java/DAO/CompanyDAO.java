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
    public List<Company> getCompanyList(Integer companyID, String companyName, String countryName) {
        List<Company> companyList = new ArrayList<>();
        try {
            // Start building the SQL query
            StringBuilder sql = new StringBuilder("SELECT * FROM Company WHERE 1=1");
            if (companyID != null) {
                sql.append(" AND company_id = ?");
            }
            if (companyName != null) {
                sql.append(" AND company_name = ?");
            }
            if (countryName != null) {
                sql.append(" AND country_name = ?");
            }
            try (PreparedStatement ps = conn.prepareStatement(sql.toString())) {
//          Ensures the parameter index is correct if parameter is used
                int parameterIndex = 1;
                if (companyID != null) {
                    ps.setInt(parameterIndex++, companyID);
                }
                if (companyName != null) {
                    ps.setString(parameterIndex++, companyName);
                }
                if (countryName != null) {
                    ps.setString(parameterIndex, countryName);
                }
//              Extract data for parameters
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Company company = new Company();
                    // Use column names from the database
                    company.setCompanyID(rs.getInt("company_id"));
                    company.setCompanyName(rs.getString("company_name"));
                    company.setCountryOrigin(rs.getString("country_name"));
                    companyList.add(company);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


