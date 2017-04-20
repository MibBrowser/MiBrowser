package mibrowser.project;

import org.snmp4j.mp.SnmpConstants;
import DAO.Connection;

/**
 *
 * @author Giorgi Coelho & Guilherme Toniello 
 */
public class main {

    public static void main(String[] args) {
        Connection con = new Connection("127.0.0.1", 161, "abcBolinhas", SnmpConstants.version2c, 1000, 3);
    }    
}
