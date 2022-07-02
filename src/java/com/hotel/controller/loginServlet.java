package com.hotel.controller;

import com.hotel.model.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FAHMI
 */
public class loginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private PreparedStatement pstmt;
    
    /**
     *
     * @throws ServletExcepti


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String driver = "org.apache.jdbc.ClientDriver";
            String connectionString = "jdbc:derby://localhost:1527/Customer;create=true;user=app;password=app";
            Connection conn = DriverManager.getConnection(connectionString);
            ResultSet rs;
            pstmt = conn.prepareStatement( 
            "SELECT * FROM RECEPTIONIST WHERE USERNAME=? and PASSWORD=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()){
                String employeeId = rs.getString("EMPLOYEEID");
                String employeeName = rs.getString("EMPLOYEENAME");
                Employee employ = new Employee(employeeId, employeeName, username, password);
                request.setAttribute("Employee", employ);
                pstmt.close();
                RequestDispatcher view = request.getRequestDispatcher("/JSPmenu.jsp");
                view.forward(request, response);
            }
         
            else{
                System.out.println("Incorrect login credentials");
                RequestDispatcher view = request.getRequestDispatcher("/index.html");
                view.forward(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
