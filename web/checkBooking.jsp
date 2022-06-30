<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.sql.*" %>

    <%

    try {

    // Establish a connection to MySql database
        
    String driver = "org.apache.derby.jdbc.ClientDriver";

    String dbCon = "jdbc:derby://localhost:1527/Customer";

    Class.forName(driver);

    String usr = "app", pass="app";

    Connection conn = DriverManager.getConnection(dbCon,usr, pass);

    %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="menuCascading.css">
        <title>Check Booking</title>
    </head>
    <body>
        
        <c:set var="id" value="${param.delete}"/>
        
        <c:if test="${id!=null}" var="result">
            ${param.delete}
        </c:if>
        
        <h1>HOTEL MANAGEMENT SYSTEM</h1>
        <div class="topnav">
            <a href="JSPmenu.jsp">Dashboard</a>
            <a href="booking.html">New booking</a>
            <a class="active" href="#">Check booking</a>
            <a href="index.html">Log out</a>
        </div><br><br>
        
        <%

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKING");
        
        %>
       
        <table border="1" cellspacing="5" width="80%" >
            <tr>
                    <th>Booking ID</th>
                    <th>Employee ID</th>
                    <th>Room ID</th>
                    <th>Customer ID</th>
                    <th>Booking Date</th>
            </tr>
            <% 
                while(rs.next()){
                String bookingID = rs.getString("BOOKINGID");
            %>
                
                <tr>
                        <td><%=bookingID%></td>
                        <td><%= rs.getString("EMPLOYEEID")%></td>
                        <td><%= rs.getString("ROOMID")    %></td>
                        <td><%= rs.getString("CUSTOMERID")%></td>
                        <td><%= rs.getString("BOOKINGDATE")%></td>
                        <td><a href="editBooking.jsp?bkid=<%=bookingID%>" 
                               title="Update Record" style="text-decoration: none;color: green;">edit</a>
                            <a href="deleteServlet?bkid=<%=bookingID%>"
                               title="Delete Record" style="text-decoration: none;color: green;">Delete</a></td>
                </tr><% } %>
        </table>
                <%
                    rs.close();
                    stmt.close();
                    conn.close();
                    }catch (SQLException sqle) {
                    out.println(sqle.getMessage());
                    } catch (Exception e) {
                    out.println(e.getMessage());
                    }
                %>
                    
    </body>
</html>
