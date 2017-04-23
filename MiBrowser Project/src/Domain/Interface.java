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
    private String speed;
    private String MACAddress;
    private String AdminStatus;
    private String OperationalStatus;

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
            ".1.3.6.1.2.1.2.2.1.8." + index, //Operational Status
        };
        String[] results = this.repository.getInformation(interfaceOIDs);

        this.description = results[0];
        this.type = results[1];
        this.speed = results[2];
        this.MACAddress = results[3];
        this.AdminStatus = results[4];
        this.OperationalStatus = results[5];
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getMACAddress() {
        return MACAddress;
    }

    public void setMACAddress(String MACAddress) {
        this.MACAddress = MACAddress;
    }

    public String getAdminStatus() {
        return AdminStatus;
    }

    public void setAdminStatus(String AdminStatus) {
        this.AdminStatus = AdminStatus;
    }

    public String getOperationalStatus() {
        return OperationalStatus;
    }

    public void setOperationalStatus(String OperationalStatus) {
        this.OperationalStatus = OperationalStatus;
    }
}
