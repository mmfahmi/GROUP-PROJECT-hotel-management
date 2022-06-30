package com.hotel.bean;
import java.io.Serializable;

public class Customer implements Serializable{

    private int customerID;
    private String customerName;
    private String customerPhone;
    
    public Customer()
    {
        customerID=0;
        customerName=null;
        customerPhone=null;
    }
    
    public Customer(int customerID,String customerName,String customerPhone)
    {
        this.customerID=customerID;
        this.customerName=customerName;
        this.customerPhone=customerPhone;
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
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerPhone
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone the customerPhone to set
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
}
