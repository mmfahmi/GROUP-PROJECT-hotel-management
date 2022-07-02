package com.hotel.dao;

import com.hotel.util.HoteldbConn;
import com.hotel.model.booking;
import java.sql.*;

public class BookingDao {
    //DB connection
    public Connection con;
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
    //Get maximum booking id
    
}
