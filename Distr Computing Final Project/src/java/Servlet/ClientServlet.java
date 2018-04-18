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

    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

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
    
    private void viewListClient(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException, NullPointerException {
        
        ArrayList<Client> clientList = new ArrayList();
        clientList = clientService.viewClient(clientDao);

        request.setAttribute("clientList", clientList);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

//    private void editClient(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int ID = Integer.parseInt(request.getParameter("id"));
//
//        try {
//            Client order = clientService.showClient(ID, clientDao);
//            request.setAttribute("order", order);
//
//            RequestDispatcher dispatcher = request.getRequestDispatcher("OrderEdit.jsp");
//            dispatcher.forward(request, response);
//
//        } catch (SQLException ex) {
//            request.setAttribute("Error", ex);
//            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
//            rd.forward(request, response);
//        }
//
//    }
//
//    private void updateClient(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//
//        int ID = Integer.parseInt(request.getParameter("ID"));
//        int agentID = Integer.parseInt(request.getParameter("agentID"));
//        int clientID = Integer.parseInt(request.getParameter("clientID"));
//        int flyerQty = Integer.parseInt(request.getParameter("flyerQty"));
//        String flyerLayout = request.getParameter("flyerLayout");
//        Blob flyerImg = null;
//        int personalCopy = Integer.parseInt(request.getParameter("personalCopy"));
//        String paymentInfo = request.getParameter("paymentInfo");
//        int invoiceNum = Integer.parseInt(request.getParameter("invoiceNum"));
//        String comments = request.getParameter("comments");
//        boolean isFlyerArtApproved = Boolean.parseBoolean(request.getParameter("isFlyerArtApproved"));
//        boolean isPaymentReceived = Boolean.parseBoolean(request.getParameter("isPaymentReceived"));
//
//        Client clientObj = new Client(ID, agentID, clientID, flyerQty, flyerLayout, flyerImg, personalCopy, paymentInfo, invoiceNum, comments, isFlyerArtApproved, isPaymentReceived);
//        
//        try {
//            clientService.updateClient(clientObj, clientDao);
//        } catch (SQLException ex) {
//            request.setAttribute("Error", ex);
//            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
//            rd.forward(request, response);
//        }
//        
//        RequestDispatcher dispatcher = request.getRequestDispatcher("OrderAdminIndex.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//
//        int ID = Integer.parseInt(request.getParameter("id"));
//        Client clientObj = new Client(ID);
//
//        try {
//            clientService.deleteClient(clientObj, clientDao);
//        } catch (SQLException ex) {
//            request.setAttribute("Error", ex);
//            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
//            rd.forward(request, response);
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("OrderAdminIndex.jsp");
//        dispatcher.forward(request, response);
//    }
    
    
    

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
