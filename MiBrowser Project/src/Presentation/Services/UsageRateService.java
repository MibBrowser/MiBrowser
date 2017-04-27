package Presentation.Services;

import DAO.Connection;
import Domain.Interface;
import Domain.Octet;
import Domain.UsageRate;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class UsageRateService {

    private Interface iface;
    private Connection connection;

    public UsageRateService(Connection con, Interface _iface) {
        this.iface = _iface;
        this.connection = con;
    }

    public UsageRate readUsageRate() throws IOException, TimeoutException, InterruptedException {
        UsageRate rate = null;
        for (int i = 0; i < 100; i++) {
            Octet lastRead = new Octet(this.connection, this.iface);
            Thread.sleep(30000);
            Octet compareRead = new Octet(this.connection, this.iface);
            rate = new UsageRate(lastRead, compareRead, this.iface.getSpeed());
            System.out.println("Rate " + (i + 1) + ": " + rate.getValor());
        }
        return rate;
    }

}
