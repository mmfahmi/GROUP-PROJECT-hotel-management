package com.hotel.bean;

import java.io.Serializable;

public class Receptionist implements Serializable{

    
    private String username;
    private String password;
    private int employeeID;
    private String employeeName;
    
    public Receptionist(){
        username=null;
        password=null;
        employeeID=0;
        employeeName=null;
    }
    
    public Receptionist(String username,String password,int employeeID,String employeeName){
        this.username=username;
        this.password=password;
        this.employeeID=employeeID;
        this.employeeName=employeeName;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
}
