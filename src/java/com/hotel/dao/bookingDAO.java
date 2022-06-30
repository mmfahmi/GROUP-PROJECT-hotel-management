package com.hotel.dao;
import com.hotel.bean.Booking;
import java.sql.*;
import util.DBConnection;
/**
 *
 * @author User
 */
public class bookingDAO {
    public Connection con = DBConnection.createConnection();
    
    public void addBooking(Booking bk){
        int employeeID = bk.getEmployeeID();
        int roomID = bk.getRoomID();
        int customerID = bk.getCustomerID();
        String date = bk.getDate();
        
        String sql = "INSERT INTO BOOKING (EMPLOYEEID,ROOMID,CUSTOMERID,BOOKINGID,BOOKINGDATE) VALUES(?,?,?,?,?)";
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, employeeID);
            ps.setInt(2, roomID);
            ps.setInt(3, customerID);
            ps.setString(4, date);
            ps.executeUpdate();
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void deleteBooking(int bookID){
        String query = "DELETE FROM BOOKING WHERE BOOKINGID="+bookID;
        PreparedStatement ps;
        
        try{
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            con.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    
    public void updateBooking(Booking book){
        int bookingID = book.getBookingID();
        int employeeID = book.getEmployeeID();
        int roomID = book.getRoomID();
        int customerID = book.getCustomerID();
        String date = book.getDate();
        
        String sql = "UPDATE BOOKING SET BOOKINGID=?,EMPLOYEEID=?,ROOMID=?,CUSTOMERID=?,date='?'";
        
        PreparedStatement ps;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, bookingID);
            ps.setInt(2, employeeID);
            ps.setInt(3, roomID);
            ps.setInt(4, customerID);
            ps.setDate(5, date);
        }catch(Exception ex){
            System.out.println(ex);
        } 
    }
    
    
    
}
