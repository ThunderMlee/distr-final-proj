package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author GhavinBahra
 */
public class AccountDao {
    
    private String url;
    private String userDB;
    private String passDB;

    public AccountDao(String url, String userDB, String passDB) {
        this.url = url;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    public AccountDao() {
    }

    protected Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, userDB, passDB);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        return conn;
    }
    
     public int addAccount(Account accountObj) {
        int res = 0;
        String sql = "INSERT INTO login (userName , password , role, agentId) VALUES (?, ?, ?, ?)";
        try(Connection conn = getConnection()) {
            
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, accountObj.getUserName());
                stmt.setString(2, accountObj.getPassword());
                stmt.setString(3, accountObj.getRole());
                stmt.setInt(4, accountObj.getAgentID());
                res = stmt.executeUpdate();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        
        return res;
    }

    public ArrayList<Account> viewAccount() {
        ArrayList<Account> accountList = new ArrayList();
        String sql = "SELECT * FROM login";

        int ID = 0;
        String uName = "";
        String pass = "";
        String role = "";
        int agentID = 0;

        try(Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {

                ID = resultSet.getInt("id");
                uName = resultSet.getString("userName");
                pass = resultSet.getString("password");
                role = resultSet.getString("role");
                agentID = resultSet.getInt("agentId");

                Account accountObj = new Account();

                accountObj.setID(ID);
                accountObj.setUserName(uName);
                accountObj.setPassword(pass);
                accountObj.setRole(role);
                accountObj.setAgentID(agentID);
                accountList.add(accountObj);

            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        
        return accountList;
    }

    public Account showAccount(int ID) 
            throws SQLException {
        Account accountObj = null;
        String sql = "SELECT * FROM login ";
        sql += "WHERE id = ?";
        ResultSet result;
        
        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, ID);
            result = statement.executeQuery();
            while (result.next()) {
                accountObj = new Account();
                accountObj.setID(result.getInt("id"));
                accountObj.setUserName(result.getString("userName"));
                accountObj.setPassword(result.getString("password"));
                accountObj.setRole(result.getString("role"));
                accountObj.setAgentID(result.getInt("agentId"));
                
            }          
        }
        result.close();
        
        return accountObj;
    }

    public boolean updateAccount(Account accountObj) 
            throws SQLException {

        String sql = "UPDATE login SET userName = ?, password = ?, role = ?, agentID = ?";
        sql += "WHERE id = ?";
        boolean res;
        
        try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, accountObj.getUserName());
            statement.setString(2, accountObj.getPassword());
            statement.setString(3, accountObj.getRole());
            statement.setInt(4, accountObj.getAgentID());
            statement.setInt(5, accountObj.getID());
            res = statement.executeUpdate() > 0;
        }
        
        return res;
    }

    public boolean deleteAccount(Account accountObj) 
            throws SQLException {

        String sql = "DELETE FROM login where id = ?";
        boolean res;
        
        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, accountObj.getID());
            res = statement.executeUpdate() > 0;
        }
        
        return res;
    }
}
