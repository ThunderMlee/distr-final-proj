package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import model.Order;
import sun.misc.BASE64Encoder;

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
        String sql = "INSERT INTO orders (agentId , clientId, flyerQty, flyerLayout, flyerImg, personalCopy, paymentInformation, invoiceNumber, comments, isFlyerArtApproved, isPaymentReceived)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO locationxorders (orderId, locationId) "
                + "VALUES (?, ?)";
        try (Connection conn = getConnection()) {

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, orderObj.getAgentID());
                stmt.setInt(2, orderObj.getClientID());
                stmt.setInt(3, orderObj.getFlyerQty());
                stmt.setString(4, orderObj.getFlyerLayout());
                stmt.setBlob(5, new javax.sql.rowset.serial.SerialBlob(orderObj.getFlyerImg()));
                stmt.setInt(6, orderObj.getPersonalCopy());
                stmt.setString(7, orderObj.getPaymentInfo());
                stmt.setInt(8, orderObj.getInvoiceNum());
                stmt.setString(9, orderObj.getComments());
                stmt.setBoolean(10, orderObj.getIsFlyerArtApproved());
                stmt.setBoolean(11, orderObj.getIsPaymentReceived());
                res = stmt.executeUpdate();
            }

            if (conn != null) {
                for (int i = 0; i < orderObj.getLocation().length; i++) {
                    PreparedStatement stmt = conn.prepareStatement(sql2);
                    stmt.setInt(1, getOrderId());
                    stmt.setInt(2, orderObj.getLocation()[i]);
                    res = stmt.executeUpdate();
                }
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

        int flyerImgLength;

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

                flyerImgLength = (int) flyerImg.length();

                Order orderObj = new Order();

                orderObj.setID(ID);
                orderObj.setAgentID(agentID);
                orderObj.setClientID(clientID);
                orderObj.setFlyerQty(flyerQty);
                orderObj.setFlyerLayout(flyerLayout);
                orderObj.setFlyerImgBase64(encodeImage((flyerImg.getBytes(1, flyerImgLength))));
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
        Order orderObj = null;
        String sql = "SELECT * FROM orders "
                + "WHERE id = ?";
        ResultSet result;

        int flyerImgLength;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, ID);
            result = statement.executeQuery();
            while (result.next()) {
                orderObj = new Order();

                flyerImgLength = (int) result.getBlob("flyerImg").length();

                orderObj.setID(result.getInt("id"));
                orderObj.setAgentID(result.getInt("agentId"));
                orderObj.setClientID(result.getInt("clientId"));
                orderObj.setFlyerQty(result.getInt("flyerQty"));
                orderObj.setFlyerLayout(result.getString("flyerLayout"));
                orderObj.setFlyerImgBase64(encodeImage(result.getBlob("flyerImg").getBytes(1, flyerImgLength)));
                orderObj.setPersonalCopy(result.getInt("personalCopy"));
                orderObj.setLocation(getLocations(ID));
                orderObj.setPaymentInfo(result.getString("paymentInformation"));
                orderObj.setInvoiceNum(result.getInt("invoiceNumber"));
                orderObj.setComments(result.getString("comments"));
                orderObj.setIsFlyerArtApproved(result.getBoolean("isFlyerArtApproved"));
                orderObj.setIsPaymentReceived(result.getBoolean("isPaymentReceived"));

            }
        }

        result.close();

        return orderObj;
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
            statement.setBlob(5, new javax.sql.rowset.serial.SerialBlob(orderObj.getFlyerImg()));
            statement.setInt(6, orderObj.getPersonalCopy());
            statement.setString(7, orderObj.getPaymentInfo());
            statement.setInt(8, orderObj.getInvoiceNum());
            statement.setString(9, orderObj.getComments());
            statement.setBoolean(10, orderObj.getIsFlyerArtApproved());
            statement.setBoolean(11, orderObj.getIsPaymentReceived());
            statement.setInt(12, orderObj.getID());
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

    public String encodeImage(byte[] image) {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        StringBuilder imageString = new StringBuilder();
        imageString.append("data:image/png;base64,");
        imageString.append(base64Encoder.encode(image));

        return imageString.toString();
    }

    public byte[] getImage(Order orderObj) throws SQLException {

        String sql = "SELECT flyerImg FROM orders "
                + "WHERE id = ?";

        int flyerImgLength = 0;
        Blob flyerImg = null;
        byte[] imgs;

        ResultSet result;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, orderObj.getID());
            result = statement.executeQuery();

            while (result.next()) {
                flyerImg = result.getBlob("flyerImg");
            }
            flyerImgLength = (int) flyerImg.length();
            imgs = flyerImg.getBytes(1, flyerImgLength);
        }

        return imgs;
    }

    public int getInvoiceNum() throws SQLException {
        String sql = "SELECT MAX(invoiceNumber) FROM orders";
        ResultSet result;

        int invoiceNum = 1;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            result = statement.executeQuery();

            result.next();

            int test = result.getInt("MAX(invoiceNumber)");

            if (test != 0) {
                invoiceNum = test + 1;
            }
        }

        return invoiceNum;
    }

    public int getOrderId() throws SQLException {
        String sql = "SELECT MAX(id) FROM orders";
        ResultSet result;

        int invoiceNum = 1;

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {

            result = statement.executeQuery();

            result.next();

            int test = result.getInt("MAX(id)");

            if (test != 0) {
                invoiceNum = test;
            }
        }

        return invoiceNum;
    }
    
    public int[] getLocations(int id) throws SQLException {
        String sql = "SELECT locationId FROM locationxorders "
                + "WHERE orderId = ?";
        ResultSet result;

        

        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            result = statement.executeQuery();
            int[] invoiceNum = {};

            while (result.next()) {
                int i = 0;
                invoiceNum[i] = result.getInt("locationId");
                i++;
            }
            return invoiceNum;
        }

        
    }
}
