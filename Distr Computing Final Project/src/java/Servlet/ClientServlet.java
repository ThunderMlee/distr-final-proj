package Servlet;

import Services.ClientService;
import dao.ClientDao;
import java.io.IOException;
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
        
        String action = request.getParameter("client");
        
        switch(action){
            case "add":
                addClient(request, response);
                break;
            case "list":
                viewListClient(request, response);
                break;
            case "edit":
                editClient(request, response);
                break;
            case "update":
                updateClient(request,response);
                break;
            case "delete":
                deleteClient(request, response);
                break;
            default:
                response.sendRedirect("SiteHome.jsp");
                break;
        }
    }

    //Insert new client
    private void addClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        int agentId = Integer.parseInt(request.getParameter("agentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("ClientIndex.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("SiteError.jsp");
        }

    }
    
    //View all clients
    private void viewListClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {
        
        try{
            ArrayList<Client> clientList = new ArrayList();
            clientList = clientService.viewClient(clientDao);

            request.setAttribute("clientList", clientList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("ClientIndex.jsp");
            dispatcher.forward(request, response);
        }catch(SQLException ex){
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }
        
    }
    
    private void editClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("id"));

        try {
            Client client = clientService.showClient(ID, clientDao);
            request.setAttribute("client", client);

            RequestDispatcher dispatcher = request.getRequestDispatcher("ClientEdit.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }

    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int Id = Integer.parseInt(request.getParameter("Id"));
        int agentId = Integer.parseInt(request.getParameter("agentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
        String streetName = request.getParameter("streetName");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String postalCode = request.getParameter("postalCode");
        String telOffice = request.getParameter("telOffice");
        String telCell = request.getParameter("telCell");
        String email = request.getParameter("email");
        String company = request.getParameter("company");
        String companyType = request.getParameter("companyType");

        Client clientObj = new Client(Id ,agentId, firstName,lastName,streetNumber,streetName,
                city,province,postalCode,telOffice,telCell,email,company,companyType);
        
        try {
            clientService.updateAgent(clientObj, clientDao);
        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }
        
        response.sendRedirect("ClientIndex.jsp");
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int ID = Integer.parseInt(request.getParameter("id"));
        Client clientObj = new Client(ID);

        try {
            clientService.deleteAgent(clientObj, clientDao);
        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }

        response.sendRedirect("ClientIndex.jsp");
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
