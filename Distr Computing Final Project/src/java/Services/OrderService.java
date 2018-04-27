package Services;

import dao.OrderDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Order;

/**
 *
 * @author GhavinBahra
 */
public class OrderService {
    
    public int addOrder(int agentID, int clientID, int flyerQty, String flyerLayout, byte[] flyerImg, int[] location, int personalCopy, String paymentInfo,
            int invoiceNum, String comments, boolean isFlyerArtApproved, boolean isPaymentReceived, OrderDao dao) {
        int res = 0;
        Order orderObj = new Order();
        if (agentID != 0 && clientID != 0 && flyerQty != 0 && flyerLayout != null && flyerImg != null && personalCopy != 0 && paymentInfo != null && 
                comments != null) {
            orderObj.setAgentID(agentID);
            orderObj.setClientID(clientID);
            orderObj.setFlyerQty(flyerQty);
            orderObj.setFlyerLayout(flyerLayout);
            orderObj.setFlyerImg(flyerImg);
            orderObj.setPersonalCopy(personalCopy);
            orderObj.setLocation(location);
            orderObj.setPaymentInfo(paymentInfo);
            orderObj.setInvoiceNum(invoiceNum);
            orderObj.setComments(comments);
            orderObj.setIsFlyerArtApproved(isFlyerArtApproved);
            orderObj.setIsPaymentReceived(isPaymentReceived);
            res = dao.addOrder(orderObj);
        }
        
        return res;

    }

    public ArrayList<Order> viewOrder(OrderDao dao) {
        ArrayList<Order> orderList = new ArrayList();
        orderList = dao.viewOrder();
        
        return orderList;
    }

    public Order showOrder(int id, OrderDao dao) throws SQLException {
        Order orderObj = dao.showOrder(id);
        
        return orderObj;
    }

    public boolean updateOrder(Order orderObj, OrderDao dao) throws SQLException {
        boolean res = dao.updateOrder(orderObj);
        
        return res;
    }

    public boolean deleteOrder(Order orderObj, OrderDao dao) throws SQLException {
        boolean res = dao.deleteOrder(orderObj);
        
        return res;
    }
    
    public byte[] getImage(Order orderObj, OrderDao dao) throws SQLException{ 
       
        return dao.getImage(orderObj);
    }
}
