package Servlet;

import Services.ClientService;
import dao.ClientDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;

/**
 *
 * @author Shanshan
 */
public class ClientServlet extends HttpServlet {

    ClientService clientService;
    ClientDao clientDao;

    //Connection strings
    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

    //Get connection strings and objects
    @Override
    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        clientDao = new ClientDao(jdbcURL, jdbcUserName, jdbcPassword);
        clientService = new ClientService();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Redirect request to appropriate method
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("");
        
        switch(action){
            case "/clientList":
                viewListClient(request, response, "ClientAdminIndex.jsp");
                break;
            case "insert":
                insertClient(request, response);
                break;
            default:
                response.sendRedirect("SiteHome.jsp");
                break;
        }
    }

    //Insert new client
    private void insertClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        int agentId = Integer.parseInt(request.getParameter("agentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String streetNumber = request.getParameter("streetNumber");
        String streetName = request.getParameter("streetName");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String postalCode = request.getParameter("postalCode");
        String telOffice = request.getParameter("telOffice");
        String telCell = request.getParameter("telCell");
        String email = request.getParameter("email");
        String company = request.getParameter("company");
        String companyType = request.getParameter("companyType");

        int res = clientService.addClient(agentId, firstName,lastName,streetNumber,streetName,
                city,province,postalCode,telOffice,telCell,email,company,companyType, clientDao);

        if (res > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("list");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("SiteError.jsp");
        }

    }
    
    //View all clients
    private void viewListClient(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException, NullPointerException {
        
        ArrayList<Client> clientList = new ArrayList();
        clientList = clientService.viewClient(clientDao);

        request.setAttribute("clientList", clientList);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
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
