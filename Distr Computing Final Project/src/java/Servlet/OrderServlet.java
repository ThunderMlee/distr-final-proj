package Servlet;

import Services.AgentService;
import Services.ClientService;
import Services.OrderService;
import dao.AgentDao;
import dao.ClientDao;
import dao.OrderDao;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Agent;
import model.Client;
import model.Location;
import model.Order;
import sun.misc.IOUtils;

/**
 *
 * @author GhavinBahra
 */
@MultipartConfig(maxFileSize = 16177215)
public class OrderServlet extends HttpServlet {

    OrderService orderService;
    OrderDao orderDao;

    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

    @Override
    public void init() 
            throws ServletException {
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("order");
        try {
            switch (action) {
                case "get":
                    getLists(request, response);
                    break;
                case "add":
                    addOrder(request, response);
                    break;
                case "list":
                    viewListOrder(request, response);
                    break;
                case "edit":
                    editOrder(request, response);
                    break;
                case "delete":
                    deleteOrder(request, response);
                    break;
                case "update":
                    updateOrder(request, response);
                    break;
                default:
                    response.sendRedirect("SiteHome.jsp");
                    break;
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }

    private void getLists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        request.setAttribute("agentList", getAgentList());
        request.setAttribute("clientList", getClientList());
        request.setAttribute("locList", getLocationList());
        request.setAttribute("invoiceNum", orderDao.getInvoiceNum());
        RequestDispatcher rd = request.getRequestDispatcher("OrderAdd.jsp");
        rd.forward(request, response);
    }
    
    private void addOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException, SQLException {
        
        InputStream in = null;
        
        Part image = request.getPart("flyerImg");
        in = image.getInputStream();
        
        int agentID = Integer.parseInt(request.getParameter("agentID"));
        int clientID = Integer.parseInt(request.getParameter("clientID"));
        int flyerQty = Integer.parseInt(request.getParameter("flyerQty"));
        String flyerLayout = request.getParameter("flyerLayout");
        byte[] flyerImg = IOUtils.readFully(in, (int) image.getSize(), true);
        int personalCopy = Integer.parseInt(request.getParameter("personalCopy"));
        
        
        String[] locations = request.getParameterValues("location");
        int[] location = new int[locations.length];
        
        for( int i = 0 ; i < location.length ; i++ ){
            location[i] = Integer.parseInt(locations[i]);
        }
        
        
        String paymentInfo = request.getParameter("paymentInfoNum") + " " + request.getParameter("paymentInfoDtMM") + " "
                + request.getParameter("paymentInfoDtYY") + " " + request.getParameter("paymentInfoCVV");
        int invoiceNum = Integer.parseInt(request.getParameter("invoiceNum"));
        String comments = request.getParameter("comments");
        boolean isFlyerArtApproved = false;
        boolean isPaymentReceived = false;

        int res = orderService.addOrder(agentID, clientID, flyerQty, flyerLayout, flyerImg, location, personalCopy, paymentInfo,
                invoiceNum, comments, isFlyerArtApproved, isPaymentReceived, orderDao);

        if (res > 0) {
            response.sendRedirect("OrderIndex.jsp");
        } else {
            response.sendRedirect("SiteError.jsp");
        }
    }

    private void viewListOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        ArrayList<Order> orderList = new ArrayList();
        orderList = orderService.viewOrder(orderDao);

        request.setAttribute("orderList", orderList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("OrderIndex.jsp");
        dispatcher.forward(request, response);
    }

    private void editOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("id"));

        try {
            Order order = orderService.showOrder(ID, orderDao);
            request.setAttribute("order", order);
            
            String[] payInfo = order.getPaymentInfo().split(" ");

            request.setAttribute("number", payInfo[0]);
            request.setAttribute("expMM", payInfo[1]);
            request.setAttribute("expYY", payInfo[2]);
            request.setAttribute("CVV", payInfo[3]);
            
            request.setAttribute("agentList",getAgentList());
            request.setAttribute("clientList",getClientList());
            request.setAttribute("locationList",getLocationList());
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("OrderEdit.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }

    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        InputStream in = null;
        
        Part image = request.getPart("flyerImg");
        in = image.getInputStream();
        
        int ID = Integer.parseInt(request.getParameter("ID"));
        int agentID = Integer.parseInt(request.getParameter("agentID"));
        int clientID = Integer.parseInt(request.getParameter("clientID"));
        int flyerQty = Integer.parseInt(request.getParameter("flyerQty"));
        String flyerLayout = request.getParameter("flyerLayout");
        byte[] flyerImg;
        
        if(in.available() != 0){
            flyerImg = IOUtils.readFully(in, (int) image.getSize(), true);
        } else {
            flyerImg = getImage(ID);
        }
        
        String[] locations = request.getParameter("location").split("");
        int[] location = new int[locations.length];
        
        for( int i = 0 ; i < location.length ; i++ ){
            location[i] = Integer.parseInt(locations[i]);
        }
        
        int personalCopy = Integer.parseInt(request.getParameter("personalCopy"));
        String paymentInfo = request.getParameter("paymentInfo");
        int invoiceNum = Integer.parseInt(request.getParameter("invoiceNum"));
        String comments = request.getParameter("comments");
        boolean isFlyerArtApproved = Boolean.parseBoolean(request.getParameter("artApprove"));
        boolean isPaymentReceived = Boolean.parseBoolean(request.getParameter("payReceive"));

        Order orderObj = new Order(ID, agentID, clientID, flyerQty, flyerLayout, flyerImg, location, personalCopy, paymentInfo, invoiceNum, comments, isFlyerArtApproved, isPaymentReceived);

        try {
            orderService.updateOrder(orderObj, orderDao);
            response.sendRedirect("OrderIndex.jsp");
        } catch (SQLException ex) {
            ex.printStackTrace();
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int ID = Integer.parseInt(request.getParameter("id"));
        Order orderObj = new Order(ID);

        try {
            orderService.deleteOrder(orderObj, orderDao);
            response.sendRedirect("OrderIndex.jsp");
        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }
    }
    
    private ArrayList<Client> getClientList() throws ServletException, IOException, SQLException{
        ArrayList<Client> clientList = new ArrayList();
        ClientService cService = new ClientService();
        ClientDao cDao = new ClientDao(jdbcURL, jdbcUserName, jdbcPassword);
        
        clientList = cService.viewClient(cDao);

        return clientList;
    }
    
    public ArrayList<Agent> getAgentList(){
        ArrayList<Agent> agentList = new ArrayList();
        AgentService aService = new AgentService();
        AgentDao aDao = new AgentDao(jdbcURL, jdbcUserName, jdbcPassword);
        
        agentList = aService.viewAgent(aDao);
        
        return agentList;
    }
    
    public ArrayList<Location> getLocationList() throws SQLException{
        ArrayList<Location> locationList = new ArrayList();
        LocationServlet loc = new LocationServlet();
        
        locationList = loc.getList();
        
        return locationList;
    }
    
    private byte[] getImage(int ID){
        Order orderObj = new Order(ID);
        
        byte[] img = null;
        
        try {
            img = orderService.getImage(orderObj, orderDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return img;
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
            Logger.getLogger(OrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
