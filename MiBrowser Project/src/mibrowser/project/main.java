package mibrowser.project;

import org.snmp4j.mp.SnmpConstants;
import DAO.Connection;
import DAO.MIBRepository;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.snmp4j.PDU;
import org.snmp4j.event.ResponseEvent;

/**
 *
 * @author Giorgi Coelho & Guilherme Toniello
 */
public class main {

    public static void main(String[] args) {
        Connection con = new Connection("127.0.0.1", 161, "abcBolinhas", SnmpConstants.version2c, 1000, 3);
        MIBRepository mibr = new MIBRepository(con);
        ResponseEvent response = null;
        try {
            response = mibr.getInformation(".1.3.6.1.2.1.1.1.0");
        } catch (IOException ex) {
            System.out.println("Deu erro amigo, belê?" + ex.getMessage());
        }

        if (response != null) {
            System.out.println("Got Response from Agent");
            PDU responsePDU = response.getResponse();

            if (responsePDU != null) {
                int errorStatus = responsePDU.getErrorStatus();
                int errorIndex = responsePDU.getErrorIndex();
                String errorStatusText = responsePDU.getErrorStatusText();

                if (errorStatus == PDU.noError) {
                    System.out.println("Snmp Get Response = " + responsePDU.getVariableBindings());
                } else {
                    System.out.println("Error: Request Failed");
                    System.out.println("Error Status = " + errorStatus);
                    System.out.println("Error Index = " + errorIndex);
                    System.out.println("Error Status Text = " + errorStatusText);
                }
            } else {
                System.out.println("Error: Response PDU is null");
            }
        } else {
            System.out.println("Error: Agent Timeout... ");
        }
    }
}
