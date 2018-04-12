/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Location;
import model.Market;

/**
 *
 * @author RATHA
 */
public class MarketDao {
    
    private String url;
    private String userDB;
    private String passDB;

    public MarketDao(String url, String userDB, String passDB) {
        this.url = url;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    public MarketDao() {
    }

    
    protected Connection getConnection(){
    
    Connection conn = null;
       
    try{
           Class.forName("com.mysql.jdbc.Driver");
           if(conn==null || conn.isClosed()){
                conn = DriverManager.getConnection(url, userDB, passDB);
           }
       } 
    catch(SQLException sqlEx){
           sqlEx.printStackTrace();
           } 
    catch(ClassNotFoundException ex){
           ex.printStackTrace();
       }
       return conn;
   }
    
    
    public int addMarket(Market marketObj){
        int res = 0;
        String sql = "INSERT INTO marketingagent (firstName , lastName , phoneNo, email, userName, password) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Connection conn = getConnection();
            if(conn!=null){
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,marketObj.getFirstName());
                stmt.setString(2,marketObj.getLastName());
                stmt.setString(3,marketObj.getPhoneNo());
                stmt.setString(4,marketObj.getEmail());
                stmt.setString(5,marketObj.getUserName());
                stmt.setString(6,marketObj.getPassword());
                res = stmt.executeUpdate();
                conn.close();
            }
            
        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        return res;
    }
    
    
     public ArrayList<Market> viewMarket(){
       ArrayList<Market> marketList = new ArrayList();
       String sql = "SELECT * FROM marketingagent";
      
       int id = 0;
       String fname = "";
       String lname = "";
       String phone = "";
       String email = "";
       String Uname = "";
       String pass = "";
       String conf = "";
       
       
       try{
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        while(resultSet.next()){
            
            
            id = resultSet.getInt("id");
            fname = resultSet.getString("firstName");
            lname = resultSet.getString("lastName");
            phone = resultSet.getString("phoneNo");
            email = resultSet.getString("email");
            
            
            Market marketObj = new Market();
            
            marketObj.setId(id);
            marketObj.setFirstName(fname);
            marketObj.setLastName(lname);
            marketObj.setPhoneNo(phone);
            marketObj.setEmail(email);
            marketList.add(marketObj);
            
            
        }
       resultSet.close();
       stmt.close();
        if(conn!=null && !conn.isClosed()){
         conn.close();
        }
       } catch(SQLException sqlEx){
           sqlEx.printStackTrace();
       }
       return marketList;
   }

    
     public Market showMarket(int id) throws SQLException{
       Market marketObj = null;
       String sql = "SELECT * FROM marketingagent ";
       sql += "WHERE id = ?";
       
       Connection con = getConnection();
       PreparedStatement statement = con.prepareStatement(sql);
       statement.setInt(1, id);
       ResultSet result = statement.executeQuery();
       
       while(result.next()){
           marketObj = new Market();
           marketObj.setId(result.getInt("id"));
           marketObj.setFirstName(result.getString("firstName"));
           marketObj.setLastName(result.getString("lastName"));
           marketObj.setPhoneNo(result.getString("phoneNo"));
           marketObj.setEmail(result.getString("email"));
           marketObj.setUserName(result.getString("userName"));
           marketObj.setPassword(result.getString("password"));
           
           
       }
       statement.close();
       con.close();
       result.close();
       return marketObj;
   } 
    
     
     
     
     
     public boolean updateMarket(Market marketObj) throws SQLException{
       
       String sql = "UPDATE marketingagent SET firstName = ?, lastName = ?, phoneNo = ?, email = ?, userName = ?, password = ?";
       sql += "WHERE id = ?";
       
       Connection con = getConnection();
       PreparedStatement statement = con.prepareStatement(sql);
       
       
       statement.setString(1, marketObj.getFirstName());
       statement.setString(2, marketObj.getLastName());
       statement.setString(3, marketObj.getPhoneNo());
       statement.setString(4, marketObj.getEmail());
       statement.setString(5, marketObj.getUserName());
       statement.setString(6, marketObj.getPassword());
       statement.setInt(7, marketObj.getId());
       
       boolean res = statement.executeUpdate() > 0;
       statement.close();
       con.close();
       return res;
   }
     
    public boolean deleteMarket(Market marketObj) throws SQLException
    {
     
     String sql = "DELETE FROM marketingagent where id = ?";
       
     Connection con = getConnection();
     PreparedStatement statement = con.prepareStatement(sql);
     statement.setInt(1, marketObj.getId());
         
    
     boolean res = statement.executeUpdate() > 0;
     statement.close();
     con.close();
     return res;
        
    }
    
    
}
