<%-- 
    Document   : JSPmenu
    Created on : 23 Jun 2022, 8:45:03 AM
    Author     : FAHMI
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="util.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Hotel Menu</title>
        <link rel="stylesheet" href="menuCascading.css">

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>HOTEL MANAGEMENT SYSTEM</h1>
        <div class="topnav">
            <a class="active" href="#home">Dashboard</a>
            <a href="booking.html">New booking</a>
            <a href="checkBooking.jsp">Check booking</a>
            <a href="index.html">Log out</a>
        </div>
        <%
            String userName = null;
            userName = (String)session.getAttribute("userName");
            
            Connection con = null;
            Statement stmt = null;
            ResultSet rs   = null;
            
            try{
            con = DBConnection.createConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM RECEPTIONIST WHERE USERNAME='"+userName+"'";
            
            rs = stmt.executeQuery(sql);
            
        }catch (SQLException e){
            e.printStackTrace();
        }
            while(rs.next()){
        %>
        Employee ID&nbsp;&nbsp;&nbsp;&nbsp; : <c:out value="<%=rs.getString("EMPLOYEEID")%>"></c:out><br>
        Employee Name: <c:out value="<%=rs.getString("EMPLOYEENAME")%>"></c:out>
        <%}%>
    </body>
</html>