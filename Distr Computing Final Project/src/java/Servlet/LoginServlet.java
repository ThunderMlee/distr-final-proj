package Servlet;

import Services.AccountService;
import dao.AccountDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author GhavinBahra
 */
public class LoginServlet extends HttpServlet {

    AccountService accountService;
    AccountDao accountDao;
    
    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

    @Override
    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        accountDao = new AccountDao(jdbcURL, jdbcUserName, jdbcPassword);
        accountService = new AccountService();
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

        
        HttpSession session = request.getSession();
        //session.setAttribute("sess", sess);
        //String sess = (String)session.getAttribute("sess"); 
        
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        int[] res = checkCredentials(name, pass);
        RequestDispatcher dispatch;
        
        switch (res[0]) {
            case 0:
                dispatch = request.getRequestDispatcher("/SiteLogin.jsp");
                dispatch.forward(request, response);
                //response.sendRedirect("SiteLogin.jsp");
                break;
            case 1:
                session.setAttribute("ID", res[1]);
                session.setAttribute("ROLE", "USER");
                dispatch = request.getRequestDispatcher("/SiteHome.jsp");
                dispatch.forward(request, response);
                //response.sendRedirect("SiteHome.jsp");
                break;
            case 2:
                session.setAttribute("ID", res[1]);
                session.setAttribute("ROLE", "ADMIN");
                dispatch = request.getRequestDispatcher("/SiteHome.jsp");
                dispatch.forward(request, response);
                //response.sendRedirect("SiteHome.jsp");
                break;
            default:
                dispatch = request.getRequestDispatcher("/SiteLogin.jsp");
                dispatch.forward(request, response);
                //response.sendRedirect("SiteLogin.jsp");
                break;
        }
    }

    protected int[] checkCredentials(String name, String pass)
            throws IOException, ServletException {
        
        ArrayList<Account> accountList = new ArrayList();
        accountList = accountService.viewAccount(accountDao);
        int[] res = new int[2];
        
        for(Account acc : accountList){
            if(name.equals(acc.getUserName()) && pass.equals(acc.getPassword()) && acc.getRole().equals("USER")){
                res[0] = 1;
                res[1] = acc.getAgentID();
                return res;
            } else if(name.equals(acc.getUserName()) && pass.equals(acc.getPassword()) && acc.getRole().equals("ADMIN")) {
                res[0] = 2;
                res[1] = acc.getID();
                return res;
            }
        }
        
        res[0] = 0;

        return res;
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
