package com.hotel.bean;
import java.io.Serializable;

public class Booking implements Serializable{

    private int bookingID;
    private int employeeID;
    private int roomID;
    private int customerID;
    private String date;
    
    public Booking(){
        bookingID=0;
        employeeID=0;
        roomID=0;
        customerID=0;
        date=null;
    }
    
    public Booking(int bookingID,int employeeID,int roomID,int customerID,String date)
    {
        this.bookingID=bookingID;
        this.employeeID=employeeID;
        this.roomID=roomID;
        this.customerID=customerID;
        this.date=date;
    }
    
    /**
     * @return the bookingID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * @param bookingID the bookingID to set
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * @param roomID the roomID to set
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
}
