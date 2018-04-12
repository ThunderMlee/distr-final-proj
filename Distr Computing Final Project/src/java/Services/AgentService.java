package Services;

import dao.AgentDao;
import model.Location;
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
        Agent markObj = new Agent();
        if (fName != null && lName != null && phoneNo != null && email != null && uName != null && pass != null) {
            markObj.setfName(fName);
            markObj.setlName(lName);
            markObj.setPhoneNo(phoneNo);
            markObj.setEmail(email);
            markObj.setuName(uName);
            markObj.setPass(pass);
            res = dao.addAgent(markObj);
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

    public boolean deleteAgent(Agent markObj, AgentDao dao) throws SQLException {
        boolean res = dao.deleteAgent(markObj);
        return res;
    }
}
