
package Domain;

import DAO.Connection;
import DAO.MIBRepository;


/**
 *
 * @author Giorgi Coelho & Guilherme Toniello
 */
public class Message {
    
    private MIBRepository repository; 
    
    public Message(Connection con) {
        this.repository = new MIBRepository(con);
    }
}
