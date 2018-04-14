package Servlet;

import Connection.DatabaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayRecordServlet extends HttpServlet {

    String query;
    Connection conn;
    Statement stmt;
    ResultSet res;
    DatabaseConnection dbconn;
    List lst = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            dbconn = new DatabaseConnection();
            conn = dbconn.setConnection();
            stmt = conn.createStatement();
            query = "select * from locationtable";
            res = dbconn.getResult(query, conn);
            
            while (res.next()) {
                lst.add(res.getString("id"));
                lst.add(res.getString("locationName"));
                lst.add(res.getString("distributionCapacity"));
            }
            res.close();
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        } finally {
            request.setAttribute("EmpData", lst);
            RequestDispatcher rd = request.getRequestDispatcher("/displayrecord.jsp");
            rd.forward(request, response);

            lst.clear();
            out.close();

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
