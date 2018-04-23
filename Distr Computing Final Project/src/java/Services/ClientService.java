package Services;

import dao.ClientDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Client;

/**
 *
 * @author Shanshan
 */
public class ClientService {

    //Add client
    public int addClient(int agentID, String firstName, String lastName, String streetNumber, String streetName,
            String city, String province, String postalCode, String telOffice, String telCell, String email, String company,
            String companyType, ClientDao dao) {
        int res = 0;
        Client clientObj = new Client();
        if (agentID != 0 && firstName != null
                && lastName != null && streetNumber != null && streetName != null
                && city != null && province != null && postalCode != null
                && telOffice != null && telCell != null && email != null
                && company != null && companyType != null) {
            clientObj.setAgentId(agentID);
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
            res = dao.addClient(clientObj);
        }
        return res;
    }

    //Show all clients
    public ArrayList<Client> viewClient(ClientDao dao) {
        ArrayList<Client> clientList = new ArrayList();
        clientList = dao.viewClient();

        return clientList;
    }

    //Show specific client
    public Client showClient(int id, ClientDao dao) throws SQLException {
        Client clientObj = dao.showClient(id);

        return clientObj;
    }
}
