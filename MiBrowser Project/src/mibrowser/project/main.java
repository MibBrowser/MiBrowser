package mibrowser.project;

import org.snmp4j.mp.SnmpConstants;
import DAO.Connection;
import Domain.Hardware;
import Domain.Message;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giorgi Coelho & Guilherme Toniello
 */
public class main {

    public static void main(String[] args) {
        Connection con = new Connection("127.0.0.1", 161, "abcBolinhas", SnmpConstants.version2c, 1000, 3);
        try {
            Hardware h = new Hardware(con);
            System.out.println(h.getDescription());
            System.out.println(h.getContact());
            System.out.println(h.getName());
            System.out.println(h.getLocation());
            System.out.println(h.getTimeon());
        } catch (IOException ex) {
            System.out.println("erro de conexão");
        } catch (TimeoutException ex) {
            System.out.println("tempo excedido");
        }
    }
}
