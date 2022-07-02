<%-- 
    Document   : booking.jsp
    Created on : 30 Jun 2022, 9:08:24 AM
    Author     : FAHMI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Booking Room</title>
        <link rel="stylesheet" href="menuCascading.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>HOTEL MANAGEMENT SYSTEM</h1>
        <div class="topnav">
            <a href="JSPmenu.jsp">Dashboard</a>
            <a class="active" href="#">New booking</a>
            <a href="checkBooking.jsp">Check booking</a>
            <a href="index.html">Log out</a>
        </div><br><br>
        
        <div class="divBooking">
            <form action="bookingServlet.java" method="POST">
                           
                <br><br>Name&nbsp&nbsp <input type="text" name="custName"><br><br>

                Phone&nbsp&nbsp<input type="text" name="custPhone"><br><br>
                    
                Date&nbsp&nbsp<input type="date" name="bookingDate"><br><br>

                    Room Reservation type&nbsp&nbsp<select name="roomType">
                                    <option value="single">RM 100 : Single</option>
                                    <option value="double">RM 150 : Double</option>
                                    <option value="suite">RM 200 : Suite</option>
                            </select><br><br>
                            
                            <input type="submit" value="Submit"><br><br>
            </form>
        </div>
    </body>
</html>
