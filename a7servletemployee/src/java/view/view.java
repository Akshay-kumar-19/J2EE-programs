/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akshay
 */
@WebServlet(name = "view", urlPatterns = {"/view"})
public class view extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet view</title>");            
            out.println("</head>");
            out.println("<body>");
           
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/a7servletemployee","akshay","akki");
            Statement s=con.createStatement();
            ResultSet rs=s.executeQuery("select * from employee");
            String op = null;
            int r;
            op=request.getParameter("op");
            if(op==null)
            {
                out.print("<table border='1'>");
                out.print("<tr><th>id</th><th>name</th><th>dept</th><th>sal</th>");
                while(rs.next())
                {
                    out.print("<tr><td>"+rs.getString(1)+"</td>");
                    out.print("<td>"+rs.getString(2)+"</td>");
                     out.print("<td>"+rs.getString(3)+"</td>");
                      out.print("<td>"+rs.getString(4)+"</td></tr>");
                }
                out.print("</table");
            }
            else switch(op)
            {
                case "add":
                {
                    String id=request.getParameter("id");
                    String name=request.getParameter("name");
                    String dept=request.getParameter("dept");
                    String sal=request.getParameter("sal");
                    
                    ResultSet check=s.executeQuery("select * from employee where id='"+id+"'");
                    if(check.next())
                    {
                        out.print("employee aldready exist");
                        out.print("<br><a href='index.html'>go back</a>");
                    }
                    else
                    {
                       
                     r=s.executeUpdate("insert into employee values('"+id+"','"+name+"','"+dept+"','"+sal+"')");
                    if(r>0)
                    {
                        out.print("<b>inserted</>");
                    out.print("<br><a href='index.html'>go back</a>");
                    }
                    else
                        out.print("filed to insert");
                    }
                 break; 
                }
                case "delete":
                {
                    String id=request.getParameter("id");
                    ResultSet check=s.executeQuery("select* from employee where id='"+id+"'");
                    if(check.next())
                    {
                        r=s.executeUpdate("delete from employee where id='"+id+"'");
                        if(r>0)
                        {
                           out.println("<b>deleted successfully</b>");
                        out.print("<br><a href='index.html'>go back</a>");
                        }
                        else
                            out.println("failed to delete");
                    }
                    else
                    {
                       out.println("employee doesnt exists");
                         out.print("<br><a href='index.html'>go back</a>");
                    }
                     break;
                }
                default:
                    break;
  
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(view.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(view.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
