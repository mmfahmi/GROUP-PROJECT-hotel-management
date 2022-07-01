/** Author 1: Mohammad Amir
 * @
 */
package com.hotel.dao;
//imports
import com.hotel.util.HoteldbConn; //DB connection
import com.hotel.model.Employee;
import java.sql.*;
//Employee data access object (DAO)
public class EmployeeDao {
    //Get connection
    public Connection con = HoteldbConn.connectToDB();
    
    //methods
    //1) Get employee from the database
    public Employee getEmployee(String usr,String pwd){
        try{
            String sql = "select * from receptionist where username='"+usr+
                    "' and password='"+pwd+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next(); //Calls fisrt row
            String empid = rs.getString("employeeid");
            String empname = rs.getString("employeename");
            Employee emp = new Employee(empid,empname,usr,pwd);
            con.close();//close connection
            return emp;
            
        } catch (Exception e) {
            System.out.println((e));
        }
        return null;
    }
}
