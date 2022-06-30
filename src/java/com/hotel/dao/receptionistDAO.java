package com.hotel.dao;
import com.hotel.bean.Receptionist;
import java.sql.*;
import util.DBConnection;

public class receptionistDAO {
    public Connection con = DBConnection.createConnection();
    
    public void addReceptionist(Receptionist rcp){
        String username = rcp.getUsername();
        String password = rcp.getPassword();
        
        
        
    }
    
    public Receptionist getReceptionist(int employeeID){
        try{
            String sql = "SELECT * FROM RECEPTIONIST WHERE EMPLOYEEID="+employeeID;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            String name = rs.getString("EMPLOYEENAME");
            int ID = rs.getInt("EMPLOYEEID");
            String username = rs.getString("USERNAME");
            String password = rs.getString("PASSWORD");
            Receptionist cst = new Receptionist(username,password,ID,name);
            con.close();
            return cst;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    
}
