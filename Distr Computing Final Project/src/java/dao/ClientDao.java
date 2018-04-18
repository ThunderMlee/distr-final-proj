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
        String sql = "INSERT INTO clients (agentId, firstName,lastName,streetNumber,streetName,"
                + "city,province,postalCode,telOffice,telCell,email,company,companyType)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection()) {

            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, clientObj.getAgentId());
                stmt.setString(2, clientObj.getFirstName());
                stmt.setString(3, clientObj.getLastName());
                stmt.setString(4, clientObj.getStreetNumber());
                stmt.setString(5, clientObj.getStreetName());
                stmt.setString(6, clientObj.getCity());
                stmt.setString(7, clientObj.getProvince());
                stmt.setString(8, clientObj.getPostalCode());
                stmt.setString(9, clientObj.getTelOffice());
                stmt.setString(10, clientObj.getTelCell());
                stmt.setString(11, clientObj.getEmail());
                stmt.setString(12, clientObj.getCompany());
                stmt.setString(13, clientObj.getCompanyType());
                res = stmt.executeUpdate();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        return res;
    }

    public ArrayList<Client> viewClient() {
        ArrayList<Client> clientList = new ArrayList();
        String sql = "SELECT * FROM clients";

        int ID = 0;
        int agentId = 0;
        String firstName = "";
        String lastName = "";
        String streetNumber = "";
        String streetName = "";
        String city = "";
        String province = "";
        String postalCode = "";
        String telOffice = "";
        String telCell = "";
        String email = "";
        String company = "";
        String companyType = "";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {

                ID = resultSet.getInt("id");
                agentId = resultSet.getInt("agentId");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                streetNumber = resultSet.getString("streetNumber");
                streetName = resultSet.getString("streetName");
                city = resultSet.getString("city");
                province = resultSet.getString("province");
                postalCode = resultSet.getString("postalCode");
                telOffice = resultSet.getString("telOffice");
                telCell = resultSet.getString("telCell");
                email = resultSet.getString("email");
                company = resultSet.getString("company");
                companyType = resultSet.getString("companyType");

                Client clientObj = new Client();

                clientObj.setId(ID);
                clientObj.setAgentId(agentId);
                clientObj.setFirstName(firstName);
                clientObj.setLastName(lastName);
                clientObj.setStreetNumber(streetNumber);
                clientObj.setStreetName(streetName);
                clientObj.setCity(city);
                clientObj.setProvince(province);
                clientObj.setPostalCode(postalCode);
                clientObj.setTelOffice(telOffice);
                clientObj.setTelCell(telCell);
                clientObj.setEmail(email);
                clientObj.setCompany(company);
                clientObj.setCompanyType(companyType);
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
                clientObj.setFirstName(result.getString("firstName"));
                clientObj.setLastName(result.getString("lastName"));
                clientObj.setStreetNumber(result.getString("streetNumber"));
                clientObj.setStreetName(result.getString("streetName"));
                clientObj.setCity(result.getString("city"));
                clientObj.setProvince(result.getString("province"));
                clientObj.setPostalCode(result.getString("postalCode"));
                clientObj.setTelOffice(result.getString("telOffice"));
                clientObj.setTelCell(result.getString("telCell"));
                clientObj.setEmail(result.getString("email"));
                clientObj.setCompany(result.getString("company"));
                clientObj.setCompanyType(result.getString("companyType"));
            }
        }

        result.close();

        return clientObj;
    }
//
//    public boolean updateClient(Client clientObj)
//            throws SQLException {
//
//        String sql = "UPDATE clients SET agentId = ?, clientId = ?, flyerQty = ?, flyerLayout = ?, flyerImg = ?, personalCopy = ?, paymentInformation = ?, "
//                + "invoiceNumber = ?, comments = ?, isFlyerArtApproved = ?, isPaymentReceived = ? "
//                + "WHERE id = ?";
//        boolean res;
//
//        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
//            statement.setInt(1, clientObj.getAgentID());
//            statement.setInt(2, clientObj.getClientID());
//            statement.setInt(3, clientObj.getFlyerQty());
//            statement.setString(4, clientObj.getFlyerLayout());
//            statement.setBlob(5, clientObj.getFlyerImg());
//            statement.setInt(6, clientObj.getPersonalCopy());
//            statement.setString(7, clientObj.getPaymentInfo());
//            statement.setInt(8, clientObj.getInvoiceNum());
//            statement.setString(9, clientObj.getComments());
//            statement.setBoolean(10, clientObj.getIsFlyerArtApproved());
//            statement.setBoolean(11, clientObj.getIsPaymentReceived());
//            res = statement.executeUpdate() > 0;
//        }
//
//        return res;
//    }
//
//    public boolean deleteClient(Client clientObj)
//            throws SQLException {
//
//        String sql = "DELETE FROM clients "
//                + "WHERE id = ?";
//        boolean res;
//
//        try (Connection conn = getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
//            statement.setInt(1, clientObj.getId());
//            res = statement.executeUpdate() > 0;
//        }
//
//        return res;
//    }
}
