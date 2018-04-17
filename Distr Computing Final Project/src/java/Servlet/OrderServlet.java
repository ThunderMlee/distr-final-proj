package Servlet;

import Services.AgentService;
import Services.OrderService;
import dao.AgentDao;
import dao.OrderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agent;
import model.Order;

/**
 *
 * @author OWNER
 */
public class OrderServlet extends HttpServlet {

    
    OrderService orderService;
    OrderDao orderDao;

    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

    @Override
    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        orderDao = new OrderDao(jdbcURL, jdbcUserName, jdbcPassword);
        orderService = new OrderService();

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
        response.setContentType("text/html;charset=UTF-8");
        
        String action = request.getServletPath();
        
        switch(action){
            case "/orderList":
                viewListOrder(request, response, "OrdersAdmin.jsp");
                break;
            case "":
                
                break;
            default:
                
                break;
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
        processRequest(request, response);
    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        int agentID = Integer.parseInt(request.getParameter("agentID"));
        int clientID = Integer.parseInt(request.getParameter("clientID"));
        int flyerQty = Integer.parseInt(request.getParameter("flyerQty"));
        String flyerLayout = request.getParameter("flyerLayout");
        Blob flyerImg = null;
        int personalCopy = Integer.parseInt(request.getParameter("personalCopy"));
        String paymentInfo = request.getParameter("paymentInfo");
        int invoiceNum = Integer.parseInt(request.getParameter("invoiceNum"));
        String comments = request.getParameter("comments");
        boolean isFlyerArtApproved = Boolean.parseBoolean(request.getParameter("isFlyerArtApproved"));
        boolean isPaymentReceived = Boolean.parseBoolean(request.getParameter("isPaymentReceived"));

        int res = orderService.addOrder(agentID, clientID, flyerQty, flyerLayout, flyerImg, personalCopy, paymentInfo,
            invoiceNum, comments, isFlyerArtApproved, isPaymentReceived, orderDao);

        if (res > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("list");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("Error.jsp");
        }

    }
    
    private void viewListOrder(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException, NullPointerException {
        
        ArrayList<Order> orderList = new ArrayList();
        orderList = orderService.viewOrder(orderDao);

        request.setAttribute("orderList", orderList);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    private void editOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("id"));

        try {
            Order order = orderService.showOrder(ID, orderDao);
            request.setAttribute("order", order);

            RequestDispatcher dispatcher = request.getRequestDispatcher("EditAgent.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int ID = Integer.parseInt(request.getParameter("ID"));
        int agentID = Integer.parseInt(request.getParameter("agentID"));
        int clientID = Integer.parseInt(request.getParameter("clientID"));
        int flyerQty = Integer.parseInt(request.getParameter("flyerQty"));
        String flyerLayout = request.getParameter("flyerLayout");
        Blob flyerImg = null;
        int personalCopy = Integer.parseInt(request.getParameter("personalCopy"));
        String paymentInfo = request.getParameter("paymentInfo");
        int invoiceNum = Integer.parseInt(request.getParameter("invoiceNum"));
        String comments = request.getParameter("comments");
        boolean isFlyerArtApproved = Boolean.parseBoolean(request.getParameter("isFlyerArtApproved"));
        boolean isPaymentReceived = Boolean.parseBoolean(request.getParameter("isPaymentReceived"));

        Order orderObj = new Order(ID, agentID, clientID, flyerQty, flyerLayout, flyerImg, personalCopy, paymentInfo, invoiceNum, comments, isFlyerArtApproved, isPaymentReceived);
        try {
            orderService.updateOrder(orderObj, orderDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOptions.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int ID = Integer.parseInt(request.getParameter("id"));
        Order orderObj = new Order(ID);

        try {
            orderService.deleteOrder(orderObj, orderDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOptions.jsp");
        dispatcher.forward(request, response);
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
