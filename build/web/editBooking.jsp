<%-- 
    Document   : editBooking
    Created on : Jun 30, 2022, 7:25:02 AM
    Author     : User
--%>

<%@page import="com.hotel.dao.bookingDAO"%>
<%@page import="com.hotel.bean.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Booking</title>
    </head>
    <body>
        <h1>Edit Booking</h1>
        <form action="deleteServlet" method="post">
            <table>
                <%
                    String bookingID=request.getParameter("bkid");
                    Booking book;
                    bookingDAO dao = new bookingDAO();
                    book = dao
                %>
            </table>
        </form>
    </body>
</html>
