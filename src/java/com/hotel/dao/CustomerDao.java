package com.hotel.dao;
import java.sql.*;
import java.util.*;
import com.hotel.model.Customer;
import com.hotel.util.HoteldbConn;
public class CustomerDao {
    //Member(s)
    private Connection con; //connection object
    private LinkedList custList = null;
    
    //methods
    //Connect to database
    private void openDB(){ con=HoteldbConn.connectToDB();}
    //Close the database
    private void closeDB() throws SQLException {con.close();}
    
    //1) Add customer info
    public Customer addCustomer(Customer c){
        //Check if the customer is already exist in the database
        //by checking the name and phone number
        Customer get_c = getCustomer(c.getCustomerName(),
                c.getCustomerPhoneNum());
        if(get_c != null)
        {
            return get_c; 
        }
        
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
    //2) Get customer data by customer id
    public Customer getCustomer(String custID){
        Customer c = null;
        openDB(); //Open connection
        String sql = "select * from customer where customerid="+
                custID;
        Statement stmt;
        ResultSet rs;
        try {
            //Execute query
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){//calls first row
                c = new Customer(
                        Integer.toString(rs.getInt("customerid")),
                        rs.getString("customername"),
                        rs.getString("customerPhoneNum")
                    );
                closeDB(); //Close Connection
            } else {return null;}
            
        } catch(Exception e){
            System.out.println(e);
        }
        return c;
    }
    //3) Get the maximum id of customer
    public int generateCustomerID(){
        openDB(); //open connection
        String sql = "select count(customerid) from customer";
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int id = 100;
            if(rs.next()){ //Calls the first row
                //Calls the result column
                if(rs.getString(1) == null) 
                    id = 100;
                else
                    id = 100 + rs.getInt(1);
            }
            int custID = id + 1;
            custID = getDistinctID(custID);
            closeDB(); //close connection
            return custID;//Return the generated ID
            
        } catch(Exception e){
            //Logger.getLogger(CustomerDao.class.getName()).log(Level.SEVERE, null, e);
            System.out.println((e));
        }
        return 0; //Return something
    }
    
    //4) Overload method of getCustomer(): parameters: name, phoneNum
    public Customer getCustomer(String n, String p){
        openDB(); //open DB connection
        String sql = "select * from customer where "+
                "customername = '"+n+"' AND "+
                "customerphonenum = '"+p+"'";
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
                closeDB(); //Close connection
                return c; //Return customer object
            } else {return null;}
            
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    //5) Get list of all customers
    public LinkedList getCustomerList(){
        openDB(); // open connection
        
        String sql = "select * from customer";
        Statement stmt;
        
        try {
            
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String id = rs.getString("customerid");
                String name = rs.getString("customername");
                String phone = rs.getString("customerphonenum");
                
                Customer cust = new Customer(id,name,phone);
                custList.add(cust);
            }
            closeDB(); //Close the connection
            return custList;
            
        } catch (Exception e) { System.out.println(e);}
        
        return custList;
    }
    
    //6) Clear list of customer data
    public void clearCustomerList(){
        custList.clear();
    }
    
    //7) To counter customer id generation duplicates
    public int getDistinctID(int id){
        clearCustomerList(); //Clear the data list
        getCustomerList(); //Get new data list
        
        boolean wloop = true; //to loop
        
        while(wloop) {
            boolean id_found = false;
            for(Object obj: custList){
                Customer c = (Customer) obj;
                int c_id = Integer.parseInt(c.getCustomerID());
                if(c_id == id){
                    id = id + 1;
                    id_found = true;
                    break; //exit for loop
                }
            }
            if(!id_found){break;} //exit while loop
        }
        return id;
    }
}
