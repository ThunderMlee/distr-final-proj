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
     
    public int addClient(int agentID, int clientID, int flyerQty, String flyerLayout, Blob flyerImg, int personalCopy, String paymentInfo,
            int invoiceNum, String comments, boolean isFlyerArtApproved, boolean isPaymentReceived, ClientDao dao) {
        int res = 0;
        Client clientObj = new Client();
        if (agentID != 0 && clientID != 0 && flyerQty != 0 && flyerLayout != null && flyerImg != null && personalCopy != 0 && paymentInfo != null && invoiceNum != 0 && 
                comments != null) {
            clientObj.setAgentID(agentID);
            clientObj.setClientID(clientID);
            clientObj.setFlyerQty(flyerQty);
            clientObj.setFlyerLayout(flyerLayout);
            clientObj.setFlyerImg(flyerImg);
            clientObj.setPersonalCopy(personalCopy);
            clientObj.setPaymentInfo(paymentInfo);
            clientObj.setInvoiceNum(invoiceNum);
            clientObj.setComments(comments);
            clientObj.setIsFlyerArtApproved(isFlyerArtApproved);
            clientObj.setIsPaymentReceived(isPaymentReceived);
            res = dao.addOrder(clientObj);
        }
        
        return res;

    }

    public ArrayList<Client> viewClient(ClientDao dao) {
        ArrayList<Client> clientList = new ArrayList();
        clientList = dao.viewClient();
        
        return clientList;
    }

    public Client showClient(int id, ClientDao dao) throws SQLException {
        Client clientObj = dao.showClient(id);
        
        return clientObj;
    }

    public boolean updateClient(Client clientObj, ClientDao dao) throws SQLException {
        boolean res = dao.updateClient(clientObj);
        
        return res;
    }

    public boolean deleteClient(Client clientObj, ClientDao dao) throws SQLException {
        boolean res = dao.deleteClient(clientObj);
        
        return res;
    }
}
