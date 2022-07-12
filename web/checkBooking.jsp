<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% //Session checking (by Amir)
    if(session.getAttribute("Employee") == null) {
        request.setAttribute("errMessage", "You have not login");
        request.getRequestDispatcher("/index.jsp").forward( request, response);
    }%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.sql.*" %>
<%@page import="java.sql.*"%>
<%@page import="com.hotel.dao.*"%>
<%@page import="com.hotel.model.*"%>
<%@page import="java.util.*"%>

    <%//JSP code
        //Get Booking List
        BookingDao bkdao = new BookingDao(); //Edited by Amir
        List bklist = bkdao.getBookList();
        
        //Get customer list
        CustomerDao custdao = new CustomerDao();
    %>

<!DOCTYPE html>
<html>
<style>
.tooltip {
  position: relative;
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 120px;
  background-color: #99ffcc;
  color: #006666;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;

  /* Position the tooltip */
  position: absolute;
  z-index: 1;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
}
</style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="menuCascading.css">
        <title>Check Booking</title>
    </head>
    <body>
        <h1>HOTEL MANAGEMENT SYSTEM</h1>
        <div class="topnav">
            <a href="JSPmenu.jsp">Dashboard</a>
            <a href="booking.jsp">New booking</a>
            <a class="active" href="#">Check booking</a>
            <a href="logout.do">Log out</a>
        </div><br><br>
       
        <table border="1" cellspacing="5" celldpadding="10" width="80%" >
            <tr>
                    <th>Booking ID</th>
                    <th>Employee ID</th>
                    <th>Room ID</th>
                    <th>Customer ID</th>
                    <th>Booking Date</th>
                    <th>Actions</th>
            </tr>
            <% //Display all data
                for(Object obj: bklist) { 
                    booking bk = (booking) obj;
                    Customer c = custdao.getCustomer(bk.getCustomerID());
            %>
                
                <tr>
                        <td><%= bk.getBookingID() %></td>
                        <td><%= bk.getEmployeeID() %></td>
                        <td><%= bk.getRoomID()  %></td>
                        <td class="tooltip"><%= bk.getCustomerID() %>
                            <span class="tooltiptext">
                                <%= c.getCustomerName() %> <br>
                                <%= c.getCustomerPhoneNum() %>
                            </span>
                        </td>
                        <td><%= bk.getBookingDate() %></td>
                        <td><a>Edit</a>
                            <a>Delete</a>
                            </td>
                </tr><% } %>
        </table>
    </body>
</html>
