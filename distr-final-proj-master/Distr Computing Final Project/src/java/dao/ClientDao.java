package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Client;

/**
 *
 * @author Shanshan
 */
public class ClientDao {
    
    private String url;
    private String userDB;
    private String passDB;

    public ClientDao(String url, String userDB, String passDB) {
        this.url = url;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    public ClientDao() {
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

    public int addClient(Client clientObj) {
        int res = 0;
        String sql = "INSERT INTO orders (agentId , clientId, flyerQty, flyerLayout, flyerImg, personalCopy, PaymentInformation, invoiceNumber, comments, isFlyerArtApproved, isPaymentReceived)"
                + " VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection()) {

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, clientObj.getAgentID());
                stmt.setInt(2, clientObj.getClientID());
                stmt.setInt(3, clientObj.getFlyerQty());
                stmt.setString(4, clientObj.getFlyerLayout());
                stmt.setBlob(5, clientObj.getFlyerImg());
                stmt.setInt(6, clientObj.getPersonalCopy());
                stmt.setString(7, clientObj.getPaymentInfo());
                stmt.setInt(8, clientObj.getInvoiceNum());
                stmt.setString(9, clientObj.getComments());
                stmt.setBoolean(10, clientObj.getIsFlyerArtApproved());
                stmt.setBoolean(11, clientObj.getIsPaymentReceived());
                res = stmt.executeUpdate();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return res;
    }

    public ArrayList<Client> viewClient() {
        ArrayList<Client> clientList = new ArrayList();
        String sql = "SELECT * FROM orders";

        int ID = 0;
        int agentID = 0;
        int clientID = 0;
        int flyerQty = 0;
        String flyerLayout = "";
        Blob flyerImg;
        int personalCopy = 0;
        String paymentInfo = "";
        int invoiceNumber = 0;
        String comments = "";
        boolean isFlyerArtApproved = false;
        boolean isPaymentReceived = false;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {

                ID = resultSet.getInt("id");
                agentID = resultSet.getInt("agentId");
                clientID = resultSet.getInt("clientId");
                flyerQty = resultSet.getInt("flyerQty");
                flyerLayout = resultSet.getString("flyerLayout");
                flyerImg = resultSet.getBlob("flyerImg");
                personalCopy = resultSet.getInt("personalCopy");
                paymentInfo = resultSet.getString("paymentInformation");
                invoiceNumber = resultSet.getInt("invoiceNumber");
                comments = resultSet.getString("comments");
                isFlyerArtApproved = resultSet.getBoolean("isFlyerArtApproved");
                isPaymentReceived = resultSet.getBoolean("isPaymentReceived");

                Client clientObj = new Client();

                clientObj.setID(ID);
                clientObj.setAgentID(agentID);
                clientObj.setClientID(clientID);
                clientObj.setFlyerQty(flyerQty);
                clientObj.setFlyerLayout(flyerLayout);
                clientObj.setFlyerImg(flyerImg);
                clientObj.setPersonalCopy(personalCopy);
                clientObj.setPaymentInfo(paymentInfo);
                clientObj.setInvoiceNum(invoiceNumber);
                clientObj.setComments(comments);
                clientObj.setIsFlyerArtApproved(isFlyerArtApproved);
                clientObj.setIsPaymentReceived(isPaymentReceived);
                clientList.add(clientObj);

            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return clientList;
    }

    public Client showClient(int ID)
            throws SQLException {
        Client clientObj = null;
        String sql = "SELECT * FROM clients "
                + "WHERE id = ?";
        ResultSet result;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, ID);
            result = statement.executeQuery();
            while (result.next()) {
                clientObj = new Client();

                clientObj.setId(result.getInt("id"));
                clientObj.setAgentId(result.getInt("agentId"));
                clientObj.setClientID(result.getInt("clientId"));
                clientObj.setFlyerQty(result.getInt("flyerQty"));
                clientObj.setFlyerLayout(result.getString("flyerLayout"));
                clientObj.setFlyerImg(result.getBlob("flyerImg"));
                clientObj.setPersonalCopy(result.getInt("personalCopy"));
                clientObj.setPaymentInfo(result.getString("paymentInformation"));
                clientObj.setInvoiceNum(result.getInt("invoiceNumber"));
                clientObj.setComments(result.getString("comments"));
                clientObj.setIsFlyerArtApproved(result.getBoolean("isFlyerArtApproved"));
                clientObj.setIsPaymentReceived(result.getBoolean("isPaymentReceived"));

            }
        }
        
        result.close();

        return clientObj;
    }

    public boolean updateClient(Client clientObj)
            throws SQLException {

        String sql = "UPDATE clients SET agentId = ?, clientId = ?, flyerQty = ?, flyerLayout = ?, flyerImg = ?, personalCopy = ?, paymentInformation = ?, "
                + "invoiceNumber = ?, comments = ?, isFlyerArtApproved = ?, isPaymentReceived = ? "
                + "WHERE id = ?";
        boolean res;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, clientObj.getAgentID());
            statement.setInt(2, clientObj.getClientID());
            statement.setInt(3, clientObj.getFlyerQty());
            statement.setString(4, clientObj.getFlyerLayout());
            statement.setBlob(5, clientObj.getFlyerImg());
            statement.setInt(6, clientObj.getPersonalCopy());
            statement.setString(7, clientObj.getPaymentInfo());
            statement.setInt(8, clientObj.getInvoiceNum());
            statement.setString(9, clientObj.getComments());
            statement.setBoolean(10, clientObj.getIsFlyerArtApproved());
            statement.setBoolean(11, clientObj.getIsPaymentReceived());
            res = statement.executeUpdate() > 0;
        }

        return res;
    }

    public boolean deleteClient(Client clientObj)
            throws SQLException {

        String sql = "DELETE FROM clients "
                + "WHERE id = ?";
        boolean res;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, clientObj.getId());
            res = statement.executeUpdate() > 0;
        }

        return res;
    }
}
