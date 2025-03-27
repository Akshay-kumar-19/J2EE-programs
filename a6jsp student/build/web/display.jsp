<%-- 
    Document   : display
    Created on : 23 Mar, 2025, 7:06:15 PM
    Author     : Akshay
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>reg</th>
                <th>name</th>
                <th>dob</th>
                <th>adress</th>
                <th>clas</th>
                <th>course</th>
            </tr>
            <tr>
                <%     
String reg=request.getParameter("reg");
String name=request.getParameter("name");
String dob=request.getParameter("dob");
String adress=request.getParameter("adress");
String clas=request.getParameter("clas");
String course=request.getParameter("course");

out.print("<td>"+reg+"</td><td>"+name+"</td><td>"+dob+"</td><td>"+adress+"</td><td>"+clas+"</td><td>"+course+"</td>");

                    %>
            </tr>
        </table>
    </body>
</html>
