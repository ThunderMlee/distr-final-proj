package model;

/**
 *
 * @author OWNER
 */
public class Account {
    
    int ID;
    String userName;
    String password;
    String role;
    int agentID;
    
    public Account(){
        
    }
    
    public Account(int ID){
        this.ID = ID;
    }
    
    public Account(int ID, String userName, String password, String role){
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
    
    public Account(int ID, String userName, String password, String role, int agentID){
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.agentID = agentID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }
}
