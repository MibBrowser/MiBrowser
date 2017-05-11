package Presentation.Services;

import DAO.Connection;
import Domain.Interface;
import Domain.Octet;
import Domain.UsageRate;
import Presentation.Components.Chart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import javax.swing.SwingWorker;
import javax.swing.Timer;

public class UsageRateService {

    private Interface iface;
    private Connection connection;
    private ArrayList<UsageRate> listUsageRate;
    private Timer timer;
    private Chart chart;

    public UsageRateService(Connection con, Interface _iface) {
        this.iface = _iface;
        this.connection = con;
        this.listUsageRate = new ArrayList<UsageRate>();
    }

    public void start(Chart c, int updateDelay) {
        this.chart = c;
        this.timer = new Timer(updateDelay, (ae) -> {
            new SwingWorker<Void, Void>() {
                @Override
                public Void doInBackground() {
                    try {
                        UsageRate usage = readUsageRate();
                        c.addData(usage);
                        return null;
                    } catch (IOException ex) {
                        return null;
                    } catch (TimeoutException ex) {
                        return null;
                    } catch (InterruptedException ex) {
                        return null;
                    }
                }
            }.execute();
        });
        this.timer.setInitialDelay(0);
        this.timer.start();
    }

    public void stop() {
        if (this.timer != null) {
            this.timer.stop();
             this.listUsageRate.clear();
        }
    }

    public void restart(int delay, Interface iface) {
        this.chart.clean();
        this.listUsageRate.clear();
        this.iface = iface;
        this.timer.setDelay(delay);
        this.timer.restart();
    }

    private UsageRate readUsageRate() throws IOException, TimeoutException, InterruptedException {
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
