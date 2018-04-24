package Services;

import dao.AgentDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Agent;

/**
 *
 * @author RATHA
 */
public class AgentService {

    public int addAgent(String fName, String lName, String phoneNo, String email, String uName, String pass, AgentDao dao) {
        int res = 0;
        Agent agentObj = new Agent();
        if (fName != null && lName != null && phoneNo != null && email != null && uName != null && pass != null) {
            agentObj.setfName(fName);
            agentObj.setlName(lName);
            agentObj.setPhoneNo(phoneNo);
            agentObj.setEmail(email);
            agentObj.setuName(uName);
            agentObj.setPass(pass);
            res = dao.addAgent(agentObj);
        }
        return res;
    }

    public ArrayList<Agent> viewAgent(AgentDao dao) {
        ArrayList<Agent> agentList = new ArrayList();
        agentList = dao.viewAgent();
        return agentList;
    }

    public Agent showAgent(int id, AgentDao dao) throws SQLException {
        Agent agentObj = dao.showAgent(id);
        return agentObj;
    }

    public boolean updateAgent(Agent agentObj, AgentDao dao) throws SQLException {
        boolean res = dao.updateAgent(agentObj);
        return res;
    }

    public boolean deleteAgent(Agent agentObj, AgentDao dao) throws SQLException {
        boolean res = dao.deleteAgent(agentObj);
        return res;
    }
}
