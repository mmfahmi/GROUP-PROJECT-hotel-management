<% //Session checking (by Amir)
    if(session.getAttribute("Employee") == null) {
        request.setAttribute("errMessage", "You have not login");
        request.getRequestDispatcher("/index.jsp").forward( request, response);
    }%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String name=request.getParameter("custName");
    String phone=request.getParameter("custPhone");
    String date=request.getParameter("bookingDate");
    String type=request.getParameter("roomType");
%>
<html>
    <head>
        <style>
            .complete
            {
               font-weight: bold;
            }
            
            td,th
            {
                text-align:left;
            }
            
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Room</title>
        <link rel="stylesheet" href="menuCascading.css">
    </head>
    <body>
        <h1>HOTEL MANAGEMENT SYSTEM</h1>
        <div class="topnav">
            <a href="JSPmenu.jsp">Dashboard</a>
            <a class="active" href="booking.html">New booking</a>
            <a href="checkBooking.jsp">Check booking</a>
            <a href="logout.do">Log out</a>
        </div><br>
        
        <div class="complete">
            <h1>Booking completed</h1>
            New booking has been created<br><br>
                <table border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <th>Name</th>
                        <td>: <%=name%></td>
                    </tr>
                    
                    <tr>
                        <th>Phone Number</th>
                        <td>: <%=phone%></td>
                    </tr>
                    
                    <tr>
                        <th>Date</th>
                        <td>: <%=date%></td>
                    </tr>
                    
                    <tr>
                        <th> Room type</th>
                        <td>: <%=type%></td>
                    </tr>
                    
                </table>
        </div>
        
    </body>
</html>