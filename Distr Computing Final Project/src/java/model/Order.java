package model;

import java.io.InputStream;
import java.sql.Blob;

/**
 *
 * @author GhavinBahra
 */
public class Order {
    
    int ID;
    int agentID;
    int clientID;
    int flyerQty;
    String flyerLayout; //portrait, landscape, or both
    InputStream flyerImg;
    int personalCopy;//copies requested by the client to keep for themselves
    String paymentInfo; //I think this might be just card number but im not sure
    int invoiceNum;
    String comments;
    boolean isFlyerArtApproved;
    boolean isPaymentReceived;
    
    public Order(){
        
    }
    
    public Order(int ID){
        this.ID = ID;
    }

    public Order(int ID, int agentID, int clientID, int flyerQty, String flyerLayout, InputStream flyerImg, int personalCopy, String paymentInfo,
            int invoiceNum, String comments, boolean isFlyerArtApproved, boolean isPaymentReceived){
        this.ID = ID;
        this.agentID = agentID;
        this.clientID = clientID;
        this.flyerQty = flyerQty;
        this.flyerLayout = flyerLayout;
        this.flyerImg = flyerImg;
        this.personalCopy = personalCopy;
        this.paymentInfo = paymentInfo;
        this.invoiceNum = invoiceNum;
        this.comments = comments;
        this.isFlyerArtApproved = isFlyerArtApproved;
        this.isPaymentReceived = isPaymentReceived;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getFlyerQty() {
        return flyerQty;
    }

    public void setFlyerQty(int flyerQty) {
        this.flyerQty = flyerQty;
    }

    public String getFlyerLayout() {
        return flyerLayout;
    }

    public void setFlyerLayout(String flyerLayout) {
        this.flyerLayout = flyerLayout;
    }

    public InputStream getFlyerImg() {
        return flyerImg;
    }

    public void setFlyerImg(InputStream flyerImg) {
        this.flyerImg = flyerImg;
    }

    public int getPersonalCopy() {
        return personalCopy;
    }

    public void setPersonalCopy(int personalCopy) {
        this.personalCopy = personalCopy;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean getIsFlyerArtApproved() {
        return isFlyerArtApproved;
    }

    public void setIsFlyerArtApproved(boolean isFlyerArtApproved) {
        this.isFlyerArtApproved = isFlyerArtApproved;
    }

    public boolean getIsPaymentReceived() {
        return isPaymentReceived;
    }

    public void setIsPaymentReceived(boolean isPaymentReceived) {
        this.isPaymentReceived = isPaymentReceived;
    }
    
    
}
