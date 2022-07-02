/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hotel.controller;
//imports
import com.hotel.dao.BookingDao;
import com.hotel.dao.CustomerDao;
import com.hotel.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FAHMI
 */
public class bookingServlet extends HttpServlet {

        private PreparedStatement pstmt;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ///By Amir
        List errorMsgs = new LinkedList();
        //Parameters received which in used:
        String custname = request.getParameter("custName");
        String phoneNo = request.getParameter("custPhone");
        String bkdate = request.getParameter("bookingDate");
        //Get the information of the user employee
        HttpSession session = request.getSession();
        //If there is no session / session time out is reached
        if(session.getAttribute("Employee") == null){
            request.setAttribute("errMessage", "Login to continue ...");
            request.getRequestDispatcher("/index.jsp").forward( request, response);
        }
        Employee emp = (Employee)session.getAttribute("Employee");
        
        //Checking null entry
        if(custname == null || custname.trim().length() == 0){
            errorMsgs.add("Please enter customer's name");
        }
        if(phoneNo == null || phoneNo.trim().length() == 0){
            errorMsgs.add("Please enter customer's phone number");
        }
        if(bkdate == null || bkdate.trim().length() == 0){
            errorMsgs.add("Please enter booking date ");
        }
        if ( ! errorMsgs.isEmpty()) {
            request.setAttribute("errorMsgs", errorMsgs);
            RequestDispatcher view = request.getRequestDispatcher("/JSPmenu.jsp");
            view.forward(request, response);
            return;
        }
        //insert customer data to database
        CustomerDao custdao = new CustomerDao();
        Customer cust = custdao.addCustomer(
                (new Customer("0",custname,phoneNo)));
        
        //Get roomType
        String roomType = request.getParameter("roomType");
        
        //E
        //String bookid = request.getParameter("");
        //String roomid = request.getParameter("");
        
        //Add booking to database
        booking bk = new booking("B001",
                emp.getEmployeeID(),"TST101",cust.getCustomerID(),bkdate);
        BookingDao bkdao = new BookingDao();
        bkdao.addBooking(bk);
        
        //Redirect to successBooking.jsp
        request.setAttribute("custName",custname);
        request.setAttribute("custPhone", phoneNo);
        request.setAttribute("bookingDate",bkdate);
        request.setAttribute("roomType",roomType);
        RequestDispatcher view = request.getRequestDispatcher("/successBooking.jsp");
        view.forward(request, response);
        
        /*//
        try {
            String bookingID = generateBookingID();
            String employeeID = "placeholder";
            String roomID = "placeholder";
            String customerID = "palceholder";
            String driver = "org.apache.jdbc.ClientDriver";
            String connectionString = "jdbc:derby://localhost:1527/Customer;create=true;user=app;password=app";
            Connection conn = DriverManager.getConnection(connectionString);
            String name = request.getParameter("custName");
            String phone = request.getParameter("custPhone");
            String date = request.getParameter("bookingDate");
            String room = request.getParameter("roomType");
            if(null==name||phone==null||date==null||room==null){
                RequestDispatcher view = request.getRequestDispatcher("/booking.html");
                view.forward(request, response);
            }
            else{
                pstmt = conn.prepareStatement(
                        "INSERT INTO BOOKING "
                        + "(BOOKINGID, EMPLOYEEID, ROOMID, CUSTOMERID, BOOKINGDATE)"
                        + "VALUES (?,?,?,?,?)");
                pstmt.setString(1, bookingID);
                pstmt.setString(2, employeeID);
                pstmt.setString(3, roomID);
                pstmt.setString(4, customerID);
                pstmt.executeUpdate();
                pstmt.close();
                RequestDispatcher view = request.getRequestDispatcher("/successBooking.jsp");
                view.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }*///
    }//end of doPost()
    
    /*/
    private String generateBookingID(){
        try{
            String driver = "org.apache.jdbc.ClientDriver";
            String connectionString = "jdbc:derby://localhost:1527/Customer;create=true;user=app;password=app";
            Connection conn = DriverManager.getConnection(connectionString);
            pstmt = conn.prepareStatement("SELECT MAX(BOOKINGID) FROM RECEPTIONIST");
            ResultSet rs = pstmt.executeQuery();
            int id = Integer.parseInt(rs.getString(1));
            id = id+1;
            String bID = String.valueOf(id);
            return bID;
        } catch (SQLException ex){
            Logger.getLogger(bookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }//*/
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
