package Presentation.Services;

import DAO.Connection;
import Domain.Interface;
import Domain.Octet;
import Domain.UsageRate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

public class UsageRateService {

    private Interface iface;
    private Connection connection;
    private ArrayList<UsageRate> listUsageRate;

    public UsageRateService(Connection con, Interface _iface) {
        this.iface = _iface;
        this.connection = con;
        this.listUsageRate = new ArrayList<UsageRate>();
    }

    public UsageRate readUsageRate() throws IOException, TimeoutException, InterruptedException {
        UsageRate rate = null;
        Octet lastRead = new Octet(this.connection, this.iface);

        if (this.listUsageRate.size() > 0) {
            rate = new UsageRate(lastRead, this.listUsageRate.get(this.listUsageRate.size() - 1).getLastRead(), this.iface.getSpeed());
        } else {
            rate = new UsageRate(lastRead, this.iface.getSpeed());
        }

        this.listUsageRate.add(rate);
        
        return rate;
    }
}
