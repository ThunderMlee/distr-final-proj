package Servlet;

import Connection.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditRecordServlet extends HttpServlet {

    Connection conn;
    ResultSet res;
    Statement stmt;
    String query;
    String id;
    DatabaseConnection dbconn;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            id = request.getParameter("id");
            dbconn = new DatabaseConnection();
            conn = dbconn.setConnection();
            stmt = conn.createStatement();
            query = "select * from location where id = "+id;
            res = dbconn.getResult(query, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("EditData",res);
            RequestDispatcher rd = request.getRequestDispatcher("editdata.jsp");
            rd.forward(request, response);

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
