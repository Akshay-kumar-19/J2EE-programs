<%-- 
    Document   : index
    Created on : 23 Mar, 2025, 6:43:43 PM
    Author     : Akshay
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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
                <th>student names</th>
            </tr>
            <tr>
                <%
                    try{
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                    Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/a6jspstudent","akshay","akki");
                    Statement s=con.createStatement();
                    ResultSet rs=s.executeQuery("select * from student");
                    while(rs.next())
                    {
                        String reg=rs.getString(1);
                        String name=rs.getString(2);
                        String dob=rs.getString(3);
                        String adress=rs.getString(4);
                        String clas=rs.getString(5);
                        String course=rs.getString(6);
                        %>
                    
               
            </tr>
            <td>
                <a href="display.jsp?reg=<%=reg%>&name=<%=name%>&dob=<%=dob%>&adress=<%=adress%>&clas=<%=clas%>&course=<%=course%>"><%=name%></a>
            </td></tr>
        
             <%
}
}
catch(Exception e)
{}
%>
</table>
        
    </body>
</html>


