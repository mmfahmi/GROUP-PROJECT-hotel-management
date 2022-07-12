package com.hotel.dao;

import com.hotel.util.HoteldbConn;
import com.hotel.model.booking;
import java.util.LinkedList;
import java.sql.*;

public class BookingDao {
    //Members
    public Connection con; //DB connection
    private LinkedList bklist = null;
    
    //Methods
    //Connect to database
    private void openDB(){ con=HoteldbConn.connectToDB();}
    //Close the database
    private void closeDB() throws SQLException {con.close();}
    
    //1) Add booking
    public void addBooking(booking newBk){
        openDB(); //Open the connection
        String bookingid = newBk.getBookingID();
        String employeeid = newBk.getEmployeeID();
        String roomid = newBk.getRoomID();
        String customerid = newBk.getCustomerID();
        String date = newBk.getBookingDate();
        String sql = "insert into booking(bookingid,employeeid,roomid,customerid,bookingdate) "+
                "values (?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, bookingid);
            ps.setString(2, employeeid);
            ps.setString(3, roomid);
            ps.setString(4, customerid);
            ps.setString(5, date);
            ps.executeUpdate();
            closeDB();//close connection
            
        } catch (Exception e) {
            System.out.println((e));
        }
    }
    //2) Get generate booking id
    public String generateBookID() {
        openDB();
        String sql = "select count(bookingid) from booking";
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int id = 100;
            if(rs.next()){
                if(rs.getString(1)==null)
                    id = 100; //If SQL is executed but no result
                else
                    id = 100 + rs.getInt(1);//Copy the value of the result
            }
            int bkID = id + 1; //Adding value '1'
            String bookID = "BK"+Integer.toString(bkID);
            closeDB(); //close connection
            return bookID;//Return the generated ID
        } catch (Exception e) {
            System.out.println(e);
        }
        return ""; //Return something
    }
    
    //3) Get list of booking data
    public LinkedList getBookList(){
        //If the data list of booking exists, returns anyway
        if(bklist != null) return bklist;
        
        openDB(); //Get Connection
        //Initialize list of booking
        bklist = new LinkedList();
        String sql = "select * from booking";
        Statement stmt;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //Get the data for each row
                String bkID = rs.getString("bookingid");
                String empID = rs.getString("employeeid");
                String roomID = rs.getString("roomid");
                String custID = rs.getString("customerid");
                String bkdate = rs.getString("bookingdate");
                
                //Create booking object
                booking newBk = new booking(bkID,empID,roomID,custID,bkdate);
                
                //Add booking object to list
                bklist.add(newBk);
            } //end of ResultSet data access
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return bklist;
    }
    
    //4) Get booking data individually
    public booking getBookingID(String id) {
        if(bklist==null){ //If no data available
            getBookList();
        }
        for(Object obj: bklist){
            if(id.equals(((booking)obj).getBookingID())){
                return (booking) obj;
            }
        }
        return null;
    }
    
    //5) Clear booking data list 
    public void releaseBookList(){
        bklist.clear();
    }
}
