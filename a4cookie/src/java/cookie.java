/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akshay
 */
@WebServlet(urlPatterns = {"/cookie"})
public class cookie extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet cookie</title>");            
            out.println("</head>");
            out.println("<body>");
          
            String first=request.getParameter("first");
            String last=request.getParameter("last");
            Cookie [] c=request.getCookies();
            
            String firstname=null;
            String lastname=null;
            boolean newuser=true;
            
            if(c!=null)
            {
                for(Cookie cookie:c)
                {
                    if(cookie.getName().equals("first"))
                        firstname=cookie.getValue();
                    if(cookie.getName().equals("last"))
                        lastname=cookie.getValue();
                }
                
                if(firstname!=null && lastname!=null)
                {
                    if(firstname.equals(first)&&lastname.equals(last))
                        newuser=false;
                }
                if(newuser)
                {
                    out.print("<h1> welcome new user</h1>");
                    out.print("<a href='index.html'>go back</a>");
                    Cookie f=new Cookie("first",first);
                    Cookie l=new Cookie("last",last);
                    f.setMaxAge(60*60*5);
                    l.setMaxAge(60*60*5);
                    response.addCookie(f);
                    response.addCookie(l);
                    
                }
                else
                {
                     out.print("<h1> hello repeated user</h1>");
                    out.print("<a href='index.html'>go back</a>");
                }
            }
            else
            {
                out.print("<h1> welcome new user</h1>");
                    out.print("<a href='index.html'>go back</a>");
                    Cookie f=new Cookie("first",first);
                    Cookie l=new Cookie("last",last);
                    f.setMaxAge(60*60*5);
                    l.setMaxAge(60*60*5);
                    response.addCookie(f);
                    response.addCookie(l);
                
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
        processRequest(request, response);
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
        processRequest(request, response);
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
