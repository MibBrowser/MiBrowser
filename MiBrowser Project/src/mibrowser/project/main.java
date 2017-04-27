package mibrowser.project;

import org.snmp4j.mp.SnmpConstants;
import DAO.Connection;
import Domain.Hardware;
import Domain.Interface;
import Domain.UsageRate;
import Presentation.Services.UsageRateService;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Giorgi Coelho & Guilherme Toniello
 */
public class main {

    public static void main(String[] args) throws InterruptedException {
        Connection con = new Connection("127.0.0.1", 161, "abcBolinhas", SnmpConstants.version2c, 1000, 3);
        try {
            Hardware h = new Hardware(con);
            Interface[] i = h.getInterfaces();
            UsageRateService service = new UsageRateService(con, i[8]);
            UsageRate rate = service.readUsageRate();
            System.out.println("Rate: " + rate.getValor());

        } catch (IOException ex) {
            System.out.println("erro de conexão");
        } catch (TimeoutException ex) {
            System.out.println("tempo excedido");
        }
    }
}
