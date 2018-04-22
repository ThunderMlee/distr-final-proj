package Servlet;


import Connection.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Amanda
 */
public class InsertDataServlet extends HttpServlet {

    String locationName;
    int distributionCapacity;
    String query;
    
    Connection conn;
    Statement stmt;
    ResultSet res;
    DatabaseConnection dbconn;
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            
            dbconn = new DatabaseConnection();
            locationName = request.getParameter("locationName");
            distributionCapacity = Integer.parseInt(request.getParameter("distributionCapacity"));
            query = "INSERT INTO location"
                    + "(locationName, distributionCapacity)"
                    + "values('"+locationName+"',"+distributionCapacity+")";
            
            conn = dbconn.setConnection();
            stmt = conn.createStatement();
            
            stmt.executeUpdate(query);
            
        }catch(NumberFormatException | SQLException e){
            request.setAttribute("Error", e);
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
            conn.close();
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InsertDataServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InsertDataServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
