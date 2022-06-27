/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hotel.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet bookingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet bookingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        }
    }
    
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
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
