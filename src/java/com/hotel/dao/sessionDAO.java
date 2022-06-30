/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hotel.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
import util.DBConnection;

/**
 *
 * @author User
 */
public class sessionDAO {

    public static int getInfo(String username){
            Connection con = null;
            Statement stmt = null;
            ResultSet rs   = null;
            
            try{
            con = DBConnection.createConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM RECEPTIONIST WHERE USERNAME='"+username+"'";
            
            rs = stmt.executeQuery(sql);
            int ID=0;
            while(rs.next()){
                ID=rs.getInt("EMPLOYEEID");
            }
            
            return ID;
            
        }catch (SQLException e){
            e.printStackTrace();
        }
            
            return 0;
                
    }
}
