package Servlet;


import Connection.DatabaseConnection;
import static java.lang.System.out;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amanda
 */
public class UpdateDataServlet extends HttpServlet {
    String locationName,id;
    int distributionCapacity;
   
    
    String query;
    
    Connection conn;
    Statement stmt;
    ResultSet res;
    DatabaseConnection dbconn;
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try  {
            dbconn = new DatabaseConnection();
            id = request.getParameter("id");
            locationName = request.getParameter("locationName");
            distributionCapacity = Integer.parseInt(request.getParameter("distributionCapacity"));
           
            conn = dbconn.setConnection();
            stmt = conn.createStatement();
            
            //query = "update location set locationName = '"+locationName+"',distributionCapacity = '"+distributionCapacity+"' where id = "+id+"'";
            //query = "UPDATE location SET locationName = "
              //      + "(locationName, distributionCapacity)"
                //    + "values('"+locationName+"',"+distributionCapacity+")"
                  //  + "WHERE id = " + id;
             query = "update location set locationName='"+locationName+"',distributionCapacity='"+distributionCapacity+"' where id=" + id;
            
            stmt.executeUpdate(query);
            
        }catch(Exception e){
            
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
            out.close();
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
