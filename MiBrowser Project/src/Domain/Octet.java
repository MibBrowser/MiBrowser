/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import DAO.Connection;
import DAO.MIBRepository;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class Octet {

    private double in;
    private double out;
    private double time;

    public Octet(Connection con, Interface iface) throws IOException, TimeoutException {
        MIBRepository repository = new MIBRepository(con);
        String[] octetOIDs = new String[]{
            "1.3.6.1.2.1.2.2.1.10." + iface.getIndex(), //ifIn
            "1.3.6.1.2.1.2.2.1.16." + iface.getIndex() //ifOut
        };
        Date date = new Date();
        this.time = date.getTime();
        String[] results = repository.getInformation(octetOIDs);
        this.in = Long.parseLong(results[0]);
        this.out = Long.parseLong(results[1]);
    }
   
    public Octet() {
        this.in = 0;
        this.out = 0;
        this.time = 0;
    }
    
    public double getIn() {
        return in;
    }

    public void setIn(long in) {
        this.in = in;
    }

    public double getOut() {
        return out;
    }

    public void setOut(long out) {
        this.out = out;
    }

    public double getTime() {
        return time;
    }
}
