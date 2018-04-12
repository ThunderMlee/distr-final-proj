/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RATHA
 */
public class Agent {
    
    int ID;
    String fName;
    String lName;
    String phoneNo;
    String email;
    String uName;
    String pass;

    public Agent(int id) {
        this.ID = id;
    }
    

    public Agent() {
    }

    public Agent(String userName, String password) {
        this.uName = userName;
        this.pass = password;
    }

    public Agent(int id, String firstName, String lastName, String phoneNo, String email, String userName, String password) {
        this.ID = id;
        this.fName = firstName;
        this.lName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.uName = userName;
        this.pass = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
    
    
}
