package Servlet;

import Services.AgentService;
import dao.AgentDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agent;

/**
 *
 * @author RATHA
 */
public class AgentServlet extends HttpServlet {

    AgentService agentService;
    AgentDao agentDao;

    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

    @Override
    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        agentDao = new AgentDao(jdbcURL, jdbcUserName, jdbcPassword);
        agentService = new AgentService();

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
     
        String action = request.getParameter("agent");

        switch (action) {
            case "add"://adds new agent
                addAgent(request, response);
                break;

            case "list": //sends list of agents from db to AgentIndex.jsp
                viewListAgent(request, response);
                break;
                
            case "edit":
                editAgent(request, response);
                break;

            case "update":
                updateAgent(request, response);
                break;

            case "delete":
                deleteAgent(request, response);
                break;
                
            default:
                response.sendRedirect("SiteHome.jsp");
                break;
        }
    }

    private void viewListAgent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {
        
        ArrayList<Agent> agentList = new ArrayList();
        agentList = agentService.viewAgent(agentDao);

        request.setAttribute("agentList", agentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("AgentIndex.jsp");
        dispatcher.forward(request, response);
    }

    private void addAgent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        String fName = request.getParameter("fName");
        String lName = request.getParameter("lName");
        String phoneNo = request.getParameter("phone");
        String email = request.getParameter("email");
        String uName = request.getParameter("uName");
        String pass = request.getParameter("pass");
        String conf = request.getParameter("conf");

        if (!pass.equals(conf)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("AgentAdd.jsp");
            dispatcher.forward(request, response);
        }

        int res = agentService.addAgent(fName, lName, phoneNo, email, uName, pass, agentDao);

        if (res > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("AgentIndex.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("SiteError.jsp");
        }

    }

    private void editAgent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("id"));

        try {
            Agent agent = agentService.showAgent(ID, agentDao);
            request.setAttribute("agent", agent);

            RequestDispatcher dispatcher = request.getRequestDispatcher("AgentEdit.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }

    }

    private void updateAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String fName = request.getParameter("fname");
        String lName = request.getParameter("lname");
        String phoneNo = request.getParameter("phone");
        String email = request.getParameter("email");
        String uName = request.getParameter("uname");
        String pass = request.getParameter("pass");
        int ID = Integer.parseInt(request.getParameter("id"));

        Agent agentObj = new Agent(ID, fName, lName, phoneNo, email, uName, pass);
        
        try {
            agentService.updateAgent(agentObj, agentDao);
        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }
        
        response.sendRedirect("AgentIndex.jsp");
    }

    private void deleteAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int ID = Integer.parseInt(request.getParameter("id"));
        Agent agentObj = new Agent(ID);

        try {
            agentService.deleteAgent(agentObj, agentDao);
        } catch (SQLException ex) {
            request.setAttribute("Error", ex);
            RequestDispatcher rd = request.getRequestDispatcher("SiteError.jsp");
            rd.forward(request, response);
        }

        response.sendRedirect("AgentIndex.jsp");
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
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
