package com.hotel.dao;
import com.hotel.bean.LoginBean;
import util.DBConnection;
import java.sql.*;
/**
 *
 * @author User
 */
public class LoginDAO {
    
    public String authenticateUser(LoginBean loginBean){
        String userName = loginBean.getUserName();
        String password = loginBean.getUserPassword();
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs   = null;
        
        try{
            con = DBConnection.createConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM RECEPTIONIST WHERE USERNAME='"+userName+
                  "' AND PASSWORD='"+password+"'";
            
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                return "SUCCESS";
                }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }
    
}
