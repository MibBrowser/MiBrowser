/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import DAO.Connection;
import DAO.MIBRepository;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author Giorgi Coelho
 */
public class Interface {

    private Connection connection;
    private MIBRepository repository;

    private String index;
    private String description;
    private String type;
    private double speed;
    private String MACAddress;
    private String AdminStatus;
    private String OperationalStatus;
    private double inErrors;
    private double outErrors;
    private double inDiscards;
    private double outDiscards;
    private double inCast;
    private double outCast;
    private double inNcast;
    private double outNcast;

    public Interface(Connection con, String interfaceIndex) throws IOException, TimeoutException {
        this.connection = con;
        this.repository = new MIBRepository(con);
        this.index = interfaceIndex;

        String[] interfaceOIDs = new String[]{
            ".1.3.6.1.2.1.2.2.1.2." + index, //Description   
            ".1.3.6.1.2.1.2.2.1.3." + index, //Type
            ".1.3.6.1.2.1.2.2.1.5." + index, //Speed
            ".1.3.6.1.2.1.2.2.1.6." + index, //MAC
            ".1.3.6.1.2.1.2.2.1.7." + index, //Admin Status
            ".1.3.6.1.2.1.2.2.1.8." + index, //Ope  rational Status
            ".1.3.6.1.2.1.2.2.1.14." + index, //InErrors
            ".1.3.6.1.2.1.2.2.1.20." + index, //OutErrors
            ".1.3.6.1.2.1.2.2.1.13." + index, //InDiscards
            ".1.3.6.1.2.1.2.2.1.19." + index, //OutDiscards
            ".1.3.6.1.2.1.2.2.1.11." + index, //InCast
            ".1.3.6.1.2.1.2.2.1.17." + index, //OutCast
            ".1.3.6.1.2.1.2.2.1.12." + index, //InNCast
            ".1.3.6.1.2.1.2.2.1.18." + index, //OutNCast
        };
        String[] results = this.repository.getInformation(interfaceOIDs);

        this.description = results[0];
        this.type = results[1];
        this.speed = Double.parseDouble(results[2]);
        this.MACAddress = results[3];
        this.AdminStatus = results[4];
        this.OperationalStatus = results[5];
        this.inErrors = Double.parseDouble(results[6]);
        this.outErrors = Double.parseDouble(results[7]);
        this.inDiscards = Double.parseDouble(results[8]);
        this.outDiscards = Double.parseDouble(results[9]);
        this.inCast = Double.parseDouble(results[10]);
        this.outCast = Double.parseDouble(results[11]);
        this.inNcast = Double.parseDouble(results[12]);
        this.outNcast = Double.parseDouble(results[13]);
    }

    @Override
    public String toString() {
        return this.description;
    }

    public Package getPackageInformation() {
        return new Package(this);
    }

    public String getDescription() {
        return description;
    }

    public String getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public double getSpeed() {
        return speed;
    }

    public String getMACAddress() {
        return MACAddress;
    }

    public String getAdminStatus() {
        return AdminStatus;
    }

    public String getOperationalStatus() {
        return OperationalStatus;
    }

    public double getInErrors() {
        return inErrors;
    }

    public double getOutErrors() {
        return outErrors;
    }

    public double getInDiscards() {
        return inDiscards;
    }

    public double getOutDiscards() {
        return outDiscards;
    }

    public double getInCast() {
        return inCast;
    }

    public double getOutCast() {
        return outCast;
    }

    public double getInNcast() {
        return inNcast;
    }

    public double getOutNcast() {
        return outNcast;
    }
}
