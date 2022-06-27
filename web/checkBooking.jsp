<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="menuCascading.css">
        <title>Check Booking</title>
    </head>
    <body>
        <h1>HOTEL MANAGEMENT SYSTEM</h1>
        <div class="topnav">
            <a href="JSPmenu.jsp">Dashboard</a>
            <a href="booking.html">New booking</a>
            <a class="active" href="#">Check booking</a>
            <a href="index.html">Log out</a>
        </div><br><br>
        
        <%
        //loop in database 
        %>
        
        <table border="1" cellspacing="1" celldpadding="1">
            <tr>
                <th>BOOKING ID</th>
        	<th>ROOM ID</th>
        	<th>CUSTOMER ID</th>
		<th>BOOKING DATE</th>
		<th>EMPLOYEE ID</th>
            </tr>
            
            <%
                //print data here
            %>
            
        </table>
        
    </body>
</html>
