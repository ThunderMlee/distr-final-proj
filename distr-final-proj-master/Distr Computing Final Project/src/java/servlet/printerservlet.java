/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Services.MarketService;
import dao.MarketDao;
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
import model.Market;

/**
 *
 * @author RATHA
 */
public class printerservlet extends HttpServlet {

    MarketService marketService;
    MarketDao marketDao;
    
    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;
    
    

    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
        
        marketDao = new MarketDao(jdbcURL, jdbcUserName, jdbcPassword);
        marketService = new MarketService();
        
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //PrintWriter out = response.getWriter()
         String action=request.getServletPath();
         
        
        switch(action){
            case "/new market":
                NewMarket(request, response);
                break;
                
            case "/login": // check which login you are in addmin or market
                adminlog(request, response);
                break;
            
                
            case "/market": // create loaction in database
                addMarket(request,response);
                break;
                
            case "/edit":
                editMarket(request,response);
                break;
                    
                
            case "/update":
                updateMarket(request, response);
                break;
            
            case "/delete":
                deleteMarket(request, response);
                
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void adminlog(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String uname = request.getParameter("name");
    String upass = request.getParameter("pass");
    PrintWriter out = response.getWriter();
    out.println("The result is: " + uname + "\n\n" + upass);
    
    ArrayList<Market> marketlist = new ArrayList();
    marketlist = marketService.viewMarket(marketDao);
    
    request.setAttribute("marketlist", marketlist);
    
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("marketLogin.jsp");
    dispatcher.forward(request, response);
    
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    RequestDispatcher dispatcher = request.getRequestDispatcher("loginjsp.jsp");
    dispatcher.forward(request, response);
    
    
    }

    private void NewMarket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addMarket.jsp");
        dispatcher.forward(request, response);
    }

    private void addMarket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException , NullPointerException {
        
        String fname = request.getParameter("Fname");
        String lname = request.getParameter("Lname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String Uname = request.getParameter("Uname");
        String pass = request.getParameter("pass");
        String conf = request.getParameter("conf");
        
        if(!pass.equals(conf))
        {
           RequestDispatcher dispatcher = request.getRequestDispatcher("addMarket.jsp");
           dispatcher.forward(request, response);
            
        }
        
        
        
        int res = marketService.addMarket(fname, lname, phone, email, Uname, pass, marketDao);
        
        if(res>0){
           RequestDispatcher dispatcher = request.getRequestDispatcher("list");
           dispatcher.forward(request, response);
        } else {
           response.sendRedirect("error.jsp");
        }
        
        
        
        
    }

    private void editMarket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        try{
            Market market = marketService.showMarket(id,marketDao);
            request.setAttribute("market",market);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("editmarket.jsp");
            dispatcher.forward(request,response);
            
        } catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        
    }

    private void updateMarket(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String Uname = request.getParameter("uname");
        String pass = request.getParameter("pass");
        int id = Integer.parseInt(request.getParameter("id"));
        
        Market marketObj = new Market(id, fname, lname, phone, email, Uname, pass);
        try{
            marketService.updateMarket(marketObj, marketDao);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        response.sendRedirect("login");
    }

    private void deleteMarket(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        Market marketObj = new Market(id);
        try{
            marketService.deleteMarket(marketObj,marketDao);
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("marketLogin.jsp");
        dispatcher.forward(request,response);
       
        // the response gives me an error 
        //response.sendRedirect("login");
        
        
    }

}
