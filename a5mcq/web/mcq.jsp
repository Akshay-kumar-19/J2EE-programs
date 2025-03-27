<%-- 
    Document   : mcq
    Created on : 25 Mar, 2025, 11:15:58 AM
    Author     : Akshay
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
<%!
public static Connection con;
public static Statement s;
public static ResultSet rs;
public static int score;
public static String name;
public static String ans;

void dbopen()
{
try
{
 Class.forName("org.apache.derby.jdbc.ClientDriver");
con=DriverManager.getConnection("jdbc:derby://localhost:1527/mcq","akshay","akki");
s=con.createStatement();
rs=s.executeQuery("select * from mcq");
}
catch(Exception e)
{}
}

void dbclose()
{
try
{
rs.close();
s.close();
con.close();
con=null;

}catch(Exception e)
{}
}

%>

<%
    if(con==null)
    {
        score=0;
    name=request.getParameter("name");
    dbopen();
    
%>
    <h2>welcome <%=name%></h2>
<%
    }
else
{
String correct=rs.getString(5).trim();
String ans=request.getParameter("ans");        
%>
<h3>enterd answer:<%=ans%></h3>
<h3>correct answer:<%=correct%></h3>
<%
    if(correct.equals(ans))
    {
        score++;
    }

%>
<h3>score: <%=score%></h3>
<%
    
}
try
{
if(rs.next())
{
%>
<form action="mcq.jsp" method="POST">
    <table border="1">
        <tr><td><%=rs.getString(1)%></td></tr>
        <tr><td><input type="radio" name="ans" value="<%=rs.getString(2)%>"><%=rs.getString(2)%></td></tr>
        <tr><td><input type="radio" name="ans" value="<%=rs.getString(3)%>"><%=rs.getString(3)%></td></tr>
        <tr><td><input type="radio" name="ans" value="<%=rs.getString(4)%>"><%=rs.getString(4)%></td></tr>
        <tr><td><input type="submit" name="subit" value="submit"></td></tr>
        
    </table>    
</form>
<%
}else
{
out.print("<h2>your final Score:"+score+"</h2>");
dbclose();
}
}catch(Exception e)
{
out.print(e.getMessage());
}
%>
    </body>
</html>
