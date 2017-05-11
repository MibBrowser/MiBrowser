package Presentation.Services;

import DAO.Connection;
import Domain.Interface;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import Domain.Package;

public class PackageInformationService {

    private Interface iface;
    private Connection connection;
    private Timer timer;

    public PackageInformationService(Connection connection, Interface iface) {
        this.iface = iface;
        this.connection = connection;
    }

    public void start(JTextField txtErrorIn, JTextField txtErrorOut, JTextField txtDiscardIn, JTextField txtDiscardOut, int updateDelay) {
        this.timer = new Timer(updateDelay, (ae) -> {
            new SwingWorker<Void, Void>() {
                @Override
                public Void doInBackground() {
                    try {
                        Package p = iface.getPackageInformation();
                        txtErrorIn.setText(Double.toString(p.getInErrorPercent()));
                        txtErrorOut.setText(Double.toString(p.getOutErrorPercent()));
                        txtDiscardIn.setText(Double.toString(p.getInDiscardPercent()));
                        txtDiscardOut.setText(Double.toString(p.getOutDiscardPercent()));
                        return null;
                    } catch (Exception ex) {
                        return null;
                    }
                }
            }.execute();
        });
        this.timer.setInitialDelay(0);
        this.timer.start();
    }

    public void restart(int delay, Interface iface) {
        this.iface = iface;
        this.timer.setDelay(delay);
        this.timer.restart();
    }

}
