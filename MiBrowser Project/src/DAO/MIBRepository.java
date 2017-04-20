/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

/**
 *
 * @author Giorgi Coelho
 */
public class MIBRepository {

    public Connection connection;

    public MIBRepository(Connection con) {
        this.connection = con;
    }

    public ResponseEvent getInformation(String oid) throws IOException {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(PDU.GET);
        pdu.setRequestID(new Integer32(1));

        CommunityTarget comtarget = this.connection.open();
        // Create Snmp object for sending data to Agent
        Snmp snmp = new Snmp(this.connection.getTransport());
        
        ResponseEvent re = snmp.get(pdu, comtarget);
        
        snmp.close();
        
        return re;
    }    
}
