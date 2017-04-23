package mibrowser.project;

import org.snmp4j.mp.SnmpConstants;
import DAO.Connection;
import Domain.Hardware;
import Domain.Interface;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Giorgi Coelho & Guilherme Toniello
 */
public class main {

    public static void main(String[] args) {
        Connection con = new Connection("127.0.0.1", 161, "abcBolinhas", SnmpConstants.version2c, 1000, 3);
        try {
            Hardware h = new Hardware(con);
            Interface[] i = h.getInterfaces();
            System.out.println(i.length);
            System.out.println(i[4].getDescription());
        } catch (IOException ex) {
            System.out.println("erro de conexão");
        } catch (TimeoutException ex) {
            System.out.println("tempo excedido");
        }
    }
}
