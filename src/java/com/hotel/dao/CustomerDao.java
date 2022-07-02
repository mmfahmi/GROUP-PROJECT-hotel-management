package com.hotel.dao;
import java.sql.*;
import com.hotel.model.Customer;
import com.hotel.util.HoteldbConn;
import java.util.logging.*;
public class CustomerDao {
    private Connection con;
    
    //methods
    //Connect to database
    private void openDB(){ con=HoteldbConn.connectToDB();}
    //Close the database
    private void closeDB() throws SQLException {con.close();}
    
    //1) Add customer info
    public Customer addCustomer(Customer c){
        //Generate customer id first
        int custID = generateCustomerID();
        
        openDB(); //Opens the database
        String sql = "insert into customer(customerID,customerName,customerPhoneNum) "+
                        "values(?,?,?)";
        String custName = c.getCustomerName();
        String custPhone = c.getCustomerPhoneNum();
        PreparedStatement ps;
        try {
            //Create statement
            ps = con.prepareStatement(sql);
            ps.setInt(1, custID);
            ps.setString(2, custName);
            ps.setString(3, custPhone);
            ps.executeUpdate();
            closeDB(); //close connection
            
            //Set new customer info
            c.setCustomerID(Integer.toString(custID));
            return c;
            
        } catch(Exception e){
            //Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, e);
            System.out.println((e));
        }
        return c;
    }
    //2)
    public Customer getCustomer(String custID){
        openDB(); //Open connection
        String sql = "select * from customer where customer="+
                custID;
        Statement stmt;
        ResultSet rs;
        try {
            //Execute query
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){//calls first row
                Customer c = new Customer(
                        Integer.toString(rs.getInt("customerid")),
                        rs.getString("customername"),
                        rs.getString("customerPhoneNum")
                    );
                return c;
            } else {return null;}
            
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    //3) Get the maximum id of customer
    public int generateCustomerID(){
        openDB(); //open connection
        String sql = "select max(customerid) from customer";
        Statement stmt;
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int id = 100;
            if(rs.next()){
                if(rs.getString(1) == null)
                    id = 100;
                else
                    id = rs.getInt(1);
            }
            int custID = id + 1;
            closeDB(); //close connection
            return custID;
            
        } catch(Exception e){
            //Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, e);
            System.out.println((e));
        }
        return 0;
    }
}
