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
import model.Agent;

/**
 *
 * @author RATHA
 */
public class AgentDao {

    private String url;
    private String userDB;
    private String passDB;

    public AgentDao(String url, String userDB, String passDB) {
        this.url = url;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    public AgentDao() {
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

    public int addAgent(Agent agentObj) {
        int res = 0;
        String sql = "INSERT INTO marketingagent (firstName , lastName , phoneNo, email, userName, password) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, agentObj.getfName());
                stmt.setString(2, agentObj.getlName());
                stmt.setString(3, agentObj.getPhoneNo());
                stmt.setString(4, agentObj.getEmail());
                stmt.setString(5, agentObj.getuName());
                stmt.setString(6, agentObj.getPass());
                res = stmt.executeUpdate();
                conn.close();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return res;
    }

    public ArrayList<Agent> viewAgent() {
        ArrayList<Agent> agentList = new ArrayList();
        String sql = "SELECT * FROM marketingagent";

        int ID = 0;
        String fName = "";
        String lName = "";
        String phoneNo = "";
        String email = "";
        String uName = "";
        String pass = "";
        String conf = "";

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {

                ID = resultSet.getInt("id");
                fName = resultSet.getString("firstName");
                lName = resultSet.getString("lastName");
                phoneNo = resultSet.getString("phoneNo");
                email = resultSet.getString("email");

                Agent agentObj = new Agent();

                agentObj.setID(ID);
                agentObj.setfName(fName);
                agentObj.setlName(lName);
                agentObj.setPhoneNo(phoneNo);
                agentObj.setEmail(email);
                agentList.add(agentObj);

            }
            resultSet.close();
            stmt.close();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return agentList;
    }

    public Agent showAgent(int id) 
            throws SQLException {
        Agent agentObj = null;
        String sql = "SELECT * FROM marketingagent ";
        sql += "WHERE id = ?";

        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            agentObj = new Agent();
            agentObj.setID(result.getInt("id"));
            agentObj.setfName(result.getString("firstName"));
            agentObj.setlName(result.getString("lastName"));
            agentObj.setPhoneNo(result.getString("phoneNo"));
            agentObj.setEmail(result.getString("email"));
            agentObj.setuName(result.getString("userName"));
            agentObj.setPass(result.getString("password"));

        }
        statement.close();
        conn.close();
        result.close();
        return agentObj;
    }

    public boolean updateAgent(Agent agentObj) 
            throws SQLException {

        String sql = "UPDATE marketingagent SET firstName = ?, lastName = ?, phoneNo = ?, email = ?, userName = ?, password = ?";
        sql += "WHERE id = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, agentObj.getfName());
        statement.setString(2, agentObj.getlName());
        statement.setString(3, agentObj.getPhoneNo());
        statement.setString(4, agentObj.getEmail());
        statement.setString(5, agentObj.getuName());
        statement.setString(6, agentObj.getPass());
        statement.setInt(7, agentObj.getID());

        boolean res = statement.executeUpdate() > 0;
        statement.close();
        con.close();
        return res;
    }

    public boolean deleteAgent(Agent agentObj) 
            throws SQLException {

        String sql = "DELETE FROM marketingagent where id = ?";

        Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, agentObj.getID());

        boolean res = statement.executeUpdate() > 0;
        statement.close();
        conn.close();
        return res;

    }

}
