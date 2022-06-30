/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hotel.controller;

import com.hotel.bean.Booking;
import com.hotel.bean.Customer;
import com.hotel.dao.bookingDAO;
import com.hotel.dao.customerDAO;
import com.hotel.dao.sessionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;
import javax.servlet.http.*;  
/**
 *
 * @author FAHMI
 */
public class bookingServlet extends HttpServlet {

        public Connection con = DBConnection.createConnection();
    
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
        List errorMsgs = new LinkedList();
        try{
            Booking bk = new Booking();
            String customerName = request.getParameter("custName");
            String customerPhone = request.getParameter("custPhone");
            String roomType = request.getParameter("roomType");
            String date = request.getParameter("bookingDate");
            
            
            
            int roomID = roomIDCheck(roomType);
            
            Customer cst = new Customer();
            cst.setCustomerName(customerName);
            cst.setCustomerPhone(customerPhone);
            customerDAO dao = new customerDAO();
            int customerID=dao.addCustomer(cst);
            
            HttpSession session=request.getSession(false); 
            int test = (int)session.getAttribute("ID");
            bk.setCustomerID(customerID);
            bk.setEmployeeID(test);
            bk.setRoomID(roomID);
            bk.setDate(date);
            
            bookingDAO dao2 = new bookingDAO();
            dao2.addBooking(bk);
            if(customerID==1000){
                request.setAttribute("et", roomID);
            RequestDispatcher view = request.getRequestDispatcher("successBooking.jsp");
            view.forward(request,response);
            }
            
            
        }catch(Exception ex){
            System.out.println(ex);
        }
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

    private int roomIDCheck(String roomType) {
        
        try{
            String sql = "SELECT ROOM.ROOMID FROM ROOM,BOOKING WHERE ROOM.ROOMID = BOOKING.ROOMID "+
                     " AND ROOM.ROOMTYPE='"+roomType+"'";
            
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            
            rs.next();
            int i = rs.getInt("ROOMID");
            return(i);
            
        }catch (Exception ex){
            System.out.println(ex);
        }return 0;
    }

}