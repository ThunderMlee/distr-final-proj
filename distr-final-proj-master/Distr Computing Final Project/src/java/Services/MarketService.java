/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import dao.MarketDao;
import model.Location;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Market;

/**
 *
 * @author RATHA
 */
public class MarketService {
    
    public int addMarket(String firstName, String lastName, String phoneNo, String email, String userName, String password, MarketDao dao)
    {
     int res = 0;
     Market markObj = new Market();
     if(firstName!=null && lastName!=null && phoneNo!=null && email!=null && userName!= null && password!=null )
     {
        markObj.setFirstName(firstName);
        markObj.setLastName(lastName);
        markObj.setPhoneNo(phoneNo);
        markObj.setEmail(email);
        markObj.setUserName(userName);
        markObj.setPassword(password);        
        res = dao.addMarket(markObj);
     }
     return res;
     
    }
    
    
    public ArrayList<Market> viewMarket(MarketDao dao){
        ArrayList<Market> marketList = new ArrayList();
        marketList = dao.viewMarket();
        return marketList;
    }
    
    
    public Market showMarket(int id, MarketDao dao) throws SQLException{
        Market marketObj = dao.showMarket(id);
        return marketObj;
    }
    
    public boolean updateMarket(Market marketObj, MarketDao dao) throws SQLException
    {
        boolean res = dao.updateMarket(marketObj);
        return res;
    }
    
    
    public boolean deleteMarket(Market markObj, MarketDao dao) throws SQLException
    {
        boolean res = dao.deleteMarket(markObj);
        return res;
    }
}
