package com.hotel.dao;
import com.hotel.bean.Customer;
import java.sql.*;
import util.DBConnection;
/**
 *
 * @author User
 */
public class customerDAO {
    public Connection con = DBConnection.createConnection();
    
    public int addCustomer(Customer cst){
        String name = cst.getCustomerName();
        String phone = cst.getCustomerPhone();
        
        String sql = "INSERT INTO CUSTOMER (CUSTOMERNAME,CUSTOMERPHONENUM) "+"VALUES(?,?)";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.executeUpdate();
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        try{
            String sql2 = "SELECT * FROM CUSTOMER WHERE CUSTOMERNAME='"+name+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql2);
            rs.next();
            int ID = rs.getInt("CUSTOMERID");
            con.close();
            return ID;
        }catch(Exception ex){
            System.out.println(ex);
            return 1000;
        }
        
    }
    
    public Customer getCustomer(int customerID){
        try{
            String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMERID="+customerID;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            String name = rs.getString("CUSTOMERNAME");
            String num = rs.getString("CUSTOMERPHONENUM");
            Customer cst = new Customer(customerID,name,num);
            con.close();
            return cst;
        }catch(Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    
    public void updateCustomer(Customer cst){
        int customerID = cst.getCustomerID();
        String name = cst.getCustomerName();
        String phone = cst.getCustomerPhone();
        
        String sql = "UPDATE CUSTOMER SET CUSTOMERNAME=?, CUSTOMERPHONENUM=? "+
                      "WHERE CUSTOMERID="+customerID;
        
        PreparedStatement ps;
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2, phone);
            ps.executeUpdate();
            con.close();
            
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void deleteCustomer(int customerID){
        String query = "DELETE FROM CUSTOMER WHERE CUSTOMERID="+customerID;
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
}
