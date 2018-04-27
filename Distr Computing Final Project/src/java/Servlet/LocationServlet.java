package Servlet;

import Connection.DatabaseConnection;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Location;

/**
 *
 * @author Amanda
 */
public class LocationServlet extends HttpServlet {

    String locationName, id;
    int distributionCapacity;
    List lst = new ArrayList();

    String query;

    Connection conn;
    Statement stmt;
    ResultSet res;
    DatabaseConnection dbconn;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("location");

        switch (action) {
            case "add":
                addLocation(request, response);
                break;
            case "list":
                viewListLocation(request, response);
                break;
            case "edit":
                editLocation(request, response);
                break;
            case "update":
                updateLocation(request, response);
                break;
            case "delete":
                deleteLocation(request, response);
                break;
            default:
                response.sendRedirect("SiteHome.jsp");
                break;
        }

    }

    protected void viewListLocation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            dbconn = new DatabaseConnection();
            conn = dbconn.setConnection();
            stmt = conn.createStatement();
            query = "SELECT * FROM location";
            res = dbconn.getResult(query, conn);

            while (res.next()) {
                lst.add(res.getString("id"));
                lst.add(res.getString("locationName"));
                lst.add(res.getString("distributionCapacity"));
            }
            res.close();
        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        } finally {
            request.setAttribute("LocData", lst);
            conn.close();
            RequestDispatcher rd = request.getRequestDispatcher("LocationIndex.jsp");
            rd.forward(request, response);

            lst.clear();
            out.close();
        }
    }

    protected void addLocation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            dbconn = new DatabaseConnection();
            locationName = request.getParameter("locationName");
            distributionCapacity = Integer.parseInt(request.getParameter("distributionCapacity"));
            query = "INSERT INTO location "
                    + "(locationName, distributionCapacity) "
                    + "VALUES('" + locationName + "'," + distributionCapacity + ")";

            conn = dbconn.setConnection();
            stmt = conn.createStatement();

            stmt.executeUpdate(query);

        } catch (NumberFormatException | SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        } finally {
            conn.close();
            response.sendRedirect("LocationIndex.jsp");
        }
    }

    protected void editLocation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            id = request.getParameter("id");
            dbconn = new DatabaseConnection();
            conn = dbconn.setConnection();
            query = "SELECT * FROM location "
                    + "WHERE id = " + id;
            stmt = conn.createStatement();

            res = dbconn.getResult(query, conn);
            while (res.next()) {
                lst.add(res.getString("id"));
                lst.add(res.getString("locationName"));
                lst.add(res.getString("distributionCapacity"));
            }

            res.close();
        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        } finally {
            request.setAttribute("EditData", lst);
            conn.close();
            RequestDispatcher rd = request.getRequestDispatcher("LocationEdit.jsp");
            rd.forward(request, response);
        }
    }

    protected void updateLocation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");

        try {
            dbconn = new DatabaseConnection();
            id = request.getParameter("id");
            locationName = request.getParameter("locationName");
            distributionCapacity = Integer.parseInt(request.getParameter("distributionCapacity"));

            conn = dbconn.setConnection();
            stmt = conn.createStatement();

            query = "UPDATE location set locationName='" + locationName + "',distributionCapacity='" + distributionCapacity + "' WHERE id=" + id;

            stmt.executeUpdate(query);

        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect("LocationIndex.jsp");
            out.close();
        }
    }

    protected void deleteLocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            dbconn = new DatabaseConnection();
            conn = dbconn.setConnection();
            stmt = conn.createStatement();
            id = request.getParameter("id");
            query = "DELETE from location WHERE id = " + id;
            stmt.executeUpdate(query);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect("LocationIndex.jsp");
            out.close();
        }
    }

    protected ArrayList<Location> getList() throws SQLException {
        ArrayList<Location> locationList = new ArrayList();

        try {
            dbconn = new DatabaseConnection();
            conn = dbconn.setConnection();
            stmt = conn.createStatement();
            query = "SELECT * FROM location";
            res = dbconn.getResult(query, conn);

            int id = 0;
            String locName = "";
            int distrCapacity = 0;

            while (res.next()) {
                id = res.getInt("id");
                locName = res.getString("locationName");
                distrCapacity = res.getInt("distributionCapacity");

                Location locationObj = new Location();

                locationObj.setID(id);
                locationObj.setName(locName);
                locationObj.setCapacity(distrCapacity);
                
                locationList.add(locationObj);
            }
            res.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            conn.close();
            lst.clear();
            out.close();
        }
        
        return locationList;
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
        doPost(request, response);
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
        } catch (SQLException ex) {
            Logger.getLogger(LocationServlet.class.getName()).log(Level.SEVERE, null, ex);
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
