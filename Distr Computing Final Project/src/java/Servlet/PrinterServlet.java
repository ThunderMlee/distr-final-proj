package Servlet;

import Services.AgentService;
import dao.AgentDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Location;
import model.Agent;

/**
 *
 * @author RATHA
 */
public class PrinterServlet extends HttpServlet {

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //PrintWriter out = response.getWriter()
        String action = request.getServletPath();

        switch (action) {
            case "/add"://adds new agent
                newAgent(request, response);
                break;

            case "/login": // check which login you are an admin or an agent
                adminLog(request, response);
                break;

            case "/insert": // create new loaction in database
                insertAgent(request, response);
                break;

            case "/edit":
                editAgent(request, response);
                break;

            case "/update":
                updateAgent(request, response);
                break;

            case "/delete":
                deleteAgent(request, response);

            default:
                login(request, response);
                break;

        }
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

    private void adminLog(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String uName = request.getParameter("name");
        String uPass = request.getParameter("pass");
        PrintWriter out = response.getWriter();
        out.println("The result is: " + uName + "\n\n" + uPass);

        ArrayList<Agent> agentList = new ArrayList();
        agentList = agentService.viewAgent(agentDao);

        request.setAttribute("agentlist", agentList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOptions.jsp");
        dispatcher.forward(request, response);

    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        dispatcher.forward(request, response);

    }

    private void newAgent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddAgent.jsp");
        dispatcher.forward(request, response);
    }

    private void insertAgent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NullPointerException {

        String fName = request.getParameter("Fname");
        String lName = request.getParameter("Lname");
        String phoneNo = request.getParameter("phone");
        String email = request.getParameter("email");
        String uName = request.getParameter("Uname");
        String pass = request.getParameter("pass");
        String conf = request.getParameter("conf");

        if (!pass.equals(conf)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("AddAgent.jsp");
            dispatcher.forward(request, response);
        }

        int res = agentService.addAgent(fName, lName, phoneNo, email, uName, pass, agentDao);

        if (res > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("list");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("Error.jsp");
        }

    }

    private void editAgent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ID = Integer.parseInt(request.getParameter("id"));

        try {
            Agent agent = agentService.showAgent(ID, agentDao);
            request.setAttribute("agent", agent);

            RequestDispatcher dispatcher = request.getRequestDispatcher("EditAgent.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
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
            ex.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOptions.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int ID = Integer.parseInt(request.getParameter("id"));
        Agent agentObj = new Agent(ID);

        try {
            agentService.deleteAgent(agentObj, agentDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminOptions.jsp");
        dispatcher.forward(request, response);

        // the response gives me an error 
        //response.sendRedirect("login");
    }

}
