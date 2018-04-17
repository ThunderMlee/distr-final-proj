package Services;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Account;
import dao.AccountDao;

/**
 *
 * @author GhavinBahra
 */
public class AccountService {
    public int addAccount(String userName, String password, String role, int agentID, AccountDao dao) {
        int res = 0;
        Account accountObj = new Account();
        if (userName != null && password != null && role != null && agentID != 0) {
            accountObj.setUserName(userName);
            accountObj.setPassword(password);
            accountObj.setRole(role);
            accountObj.setAgentID(agentID);
            res = dao.addAccount(accountObj);
        }
        
        return res;
    }

    public ArrayList<Account> viewAccount(AccountDao dao) {
        ArrayList<Account> accountList = new ArrayList();
        accountList = dao.viewAccount();
        return accountList;
    }

    public Account showAgent(int id, AccountDao dao) throws SQLException {
        Account agentObj = dao.showAccount(id);
        return agentObj;
    }

    public boolean updateAgent(Account agentObj, AccountDao dao) throws SQLException {
        boolean res = dao.updateAccount(agentObj);
        return res;
    }

    public boolean deleteAgent(Account accountObj, AccountDao dao) throws SQLException {
        boolean res = dao.deleteAccount(accountObj);
        return res;
    }
}
