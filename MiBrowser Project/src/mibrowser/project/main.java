package mibrowser.project;

import org.snmp4j.mp.SnmpConstants;
import DAO.Connection;
import Domain.Hardware;
import Domain.Interface;
import Domain.UsageRate;
import Domain.Package;
import Presentation.Services.UsageRateService;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Giorgi Coelho & Guilherme Toniello
 */
public class main {

    public static void main(String[] args) throws InterruptedException {
//        Connection con = new Connection("127.0.0.1", 161, "abcBolinhas", SnmpConstants.version2c, 1000, 3);
//        try {
//            Hardware h = new Hardware(con);
//            Interface[] i = h.getInterfaces();
//            UsageRateService service = new UsageRateService(con, i[5]);
//            for (int j = 0; j < 100; j++) {
//                UsageRate rate = service.readUsageRate();
//                System.out.println("Rate: " + rate.getValor());
//                Thread.sleep(3000);
//            }
//
//        } catch (IOException ex) {
//            System.out.println("erro de conexão");
//        } catch (TimeoutException ex) {
//            System.out.println("tempo excedido");
//        }
    }
}
