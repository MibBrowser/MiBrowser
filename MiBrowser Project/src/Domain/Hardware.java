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
public class Hardware {

    private Connection connection;
    private MIBRepository repository;

    private String description;
    private String contact;
    private String name;
    private String location;
    private String timeon;

    public Hardware(Connection con) throws IOException, TimeoutException {
        this.connection = con;
        this.repository = new MIBRepository(con);

        String[] hardwareOIDs = new String[]{
            ".1.3.6.1.2.1.1.1.0", //Description
            "1.3.6.1.2.1.1.4.0", //Contact
            "1.3.6.1.2.1.1.5.0", //Name
            "1.3.6.1.2.1.1.6.0", //Location
            "1.3.6.1.2.1.1.3.0" //Time on    
        };

        String[] results = this.repository.getInformation(hardwareOIDs);
        this.description = results[0];
        this.contact = results[1];
        this.name = results[2];
        this.location = results[3];
        this.timeon = results[4];
    }

    public Interface[] getInterfaces() throws IOException, TimeoutException {
        String numberInterfaces = this.repository.getInformation(".1.3.6.1.2.1.2.1.0");
        Interface[] interfaces = new Interface[Integer.parseInt(numberInterfaces)];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = new Interface(this.connection, Integer.toString(i + 1));
        }
        return interfaces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeon() {
        return timeon;
    }

    public void setTimeon(String timeon) {
        this.timeon = timeon;
    }
}
