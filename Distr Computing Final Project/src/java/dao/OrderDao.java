package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Order;

/**
 *
 * @author GhavinBahra
 */
public class OrderDao {

    private String url;
    private String userDB;
    private String passDB;

    public OrderDao(String url, String userDB, String passDB) {
        this.url = url;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    public OrderDao() {
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

    public int addOrder(Order orderObj) {
        int res = 0;
        String sql = "INSERT INTO orders (agentId , clientId, flyerQty, flyerLayout, flyerImg, personalCopy, PaymentInformation, invoiceNumber, comments, isFlyerArtApproved, isPaymentReceived)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection()) {

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, orderObj.getAgentID());
                stmt.setInt(2, orderObj.getClientID());
                stmt.setInt(3, orderObj.getFlyerQty());
                stmt.setString(4, orderObj.getFlyerLayout());
                stmt.setBlob(5, orderObj.getFlyerImg());
                stmt.setInt(6, orderObj.getPersonalCopy());
                stmt.setString(7, orderObj.getPaymentInfo());
                stmt.setInt(8, orderObj.getInvoiceNum());
                stmt.setString(9, orderObj.getComments());
                stmt.setBoolean(10, orderObj.getIsFlyerArtApproved());
                stmt.setBoolean(11, orderObj.getIsPaymentReceived());
                res = stmt.executeUpdate();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return res;
    }

    public ArrayList<Order> viewOrder() {
        ArrayList<Order> accountList = new ArrayList();
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

                Order orderObj = new Order();

                orderObj.setID(ID);
                orderObj.setAgentID(agentID);
                orderObj.setClientID(clientID);
                orderObj.setFlyerQty(flyerQty);
                orderObj.setFlyerLayout(flyerLayout);
                orderObj.setFlyerImg(flyerImg);
                orderObj.setPersonalCopy(personalCopy);
                orderObj.setPaymentInfo(paymentInfo);
                orderObj.setInvoiceNum(invoiceNumber);
                orderObj.setComments(comments);
                orderObj.setIsFlyerArtApproved(isFlyerArtApproved);
                orderObj.setIsPaymentReceived(isPaymentReceived);
                accountList.add(orderObj);

            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return accountList;
    }

    public Order showOrder(int ID)
            throws SQLException {
        Order accountObj = null;
        String sql = "SELECT * FROM orders "
                + "WHERE id = ?";
        ResultSet result;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, ID);
            result = statement.executeQuery();
            while (result.next()) {
                accountObj = new Order();

                accountObj.setID(result.getInt("id"));
                accountObj.setAgentID(result.getInt("agentId"));
                accountObj.setClientID(result.getInt("clientId"));
                accountObj.setFlyerQty(result.getInt("flyerQty"));
                accountObj.setFlyerLayout(result.getString("flyerLayout"));
                accountObj.setFlyerImg(result.getBlob("flyerImg"));
                accountObj.setPersonalCopy(result.getInt("personalCopy"));
                accountObj.setPaymentInfo(result.getString("paymentInformation"));
                accountObj.setInvoiceNum(result.getInt("invoiceNumber"));
                accountObj.setComments(result.getString("comments"));
                accountObj.setIsFlyerArtApproved(result.getBoolean("isFlyerArtApproved"));
                accountObj.setIsPaymentReceived(result.getBoolean("isPaymentReceived"));

            }
        }
        
        result.close();

        return accountObj;
    }

    public boolean updateOrder(Order orderObj)
            throws SQLException {

        String sql = "UPDATE orders SET agentId = ?, clientId = ?, flyerQty = ?, flyerLayout = ?, flyerImg = ?, personalCopy = ?, paymentInformation = ?, "
                + "invoiceNumber = ?, comments = ?, isFlyerArtApproved = ?, isPaymentReceived = ? "
                + "WHERE id = ?";
        boolean res;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, orderObj.getAgentID());
            statement.setInt(2, orderObj.getClientID());
            statement.setInt(3, orderObj.getFlyerQty());
            statement.setString(4, orderObj.getFlyerLayout());
            statement.setBlob(5, orderObj.getFlyerImg());
            statement.setInt(6, orderObj.getPersonalCopy());
            statement.setString(7, orderObj.getPaymentInfo());
            statement.setInt(8, orderObj.getInvoiceNum());
            statement.setString(9, orderObj.getComments());
            statement.setBoolean(10, orderObj.getIsFlyerArtApproved());
            statement.setBoolean(11, orderObj.getIsPaymentReceived());
            res = statement.executeUpdate() > 0;
        }

        return res;
    }

    public boolean deleteOrder(Order orderObj)
            throws SQLException {

        String sql = "DELETE FROM orders WHERE "
                + "id = ?";
        boolean res;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, orderObj.getID());
            res = statement.executeUpdate() > 0;
        }

        return res;
    }
}
