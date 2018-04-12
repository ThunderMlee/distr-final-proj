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

    public int addMarket(String fName, String lName, String phoneNo, String email, String uName, String pass, MarketDao dao) {
        int res = 0;
        Market markObj = new Market();
        if (fName != null && lName != null && phoneNo != null && email != null && uName != null && pass != null) {
            markObj.setfName(fName);
            markObj.setlName(lName);
            markObj.setPhoneNo(phoneNo);
            markObj.setEmail(email);
            markObj.setuName(uName);
            markObj.setPass(pass);
            res = dao.addMarket(markObj);
        }
        return res;

    }

    public ArrayList<Market> viewMarket(MarketDao dao) {
        ArrayList<Market> marketList = new ArrayList();
        marketList = dao.viewMarket();
        return marketList;
    }

    public Market showMarket(int id, MarketDao dao) throws SQLException {
        Market marketObj = dao.showMarket(id);
        return marketObj;
    }

    public boolean updateMarket(Market marketObj, MarketDao dao) throws SQLException {
        boolean res = dao.updateMarket(marketObj);
        return res;
    }

    public boolean deleteMarket(Market markObj, MarketDao dao) throws SQLException {
        boolean res = dao.deleteMarket(markObj);
        return res;
    }
}
