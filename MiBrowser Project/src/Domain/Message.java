
package Domain;

import DAO.Connection;
import DAO.MIBRepository;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 *
 * @author Giorgi Coelho & Guilherme Toniello
 */
public class Message {    
    
    private Connection connection;
    
    private MIBRepository repository; 
    
    public Message(Connection con) {
        this.connection = con;
        this.repository = new MIBRepository(con);
    }
    
    public Interface[] getInterfaces() throws IOException, TimeoutException {
        String numberInterfaces = this.repository.getInformation(".1.3.6.1.2.1.2.1.0");
        Interface[] interfaces = new Interface[Integer.parseInt(numberInterfaces)];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = new Interface(this.connection, Integer.toString(i + 1));
        }
        return interfaces;
    }
}
