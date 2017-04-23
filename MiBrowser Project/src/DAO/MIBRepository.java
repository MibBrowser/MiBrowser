/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.TimeoutException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
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

    public String[] getInformation(String[] OIDs) throws IOException, TimeoutException {
        PDU pdu = new PDU();
        for (int i = 0; i < OIDs.length; i++) {
            pdu.add(new VariableBinding(new OID(OIDs[i])));
        }
        pdu.setType(PDU.GET);
        pdu.setRequestID(new Integer32(1));
        return this.callSnmp(pdu);
    }

    public String getInformation(String oid) throws IOException, TimeoutException {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));
        pdu.setType(PDU.GET);
        pdu.setRequestID(new Integer32(1));
        return this.callSnmp(pdu)[0];
    }

    private String[] callSnmp(PDU pdu) throws IOException, TimeoutException {
        CommunityTarget comtarget = this.connection.open();
        // Create Snmp object for sending data to Agent
        Snmp snmp = new Snmp(this.connection.getTransport());

        ResponseEvent response = snmp.get(pdu, comtarget);

        snmp.close();

        if (response != null) {
            PDU responsePDU = response.getResponse();

            if (responsePDU != null) {
                int errorStatus = responsePDU.getErrorStatus();

                if (errorStatus == PDU.noError) {
                    Vector<? extends VariableBinding> variableBindings = responsePDU.getVariableBindings();
                    String[] responseBinding = new String[variableBindings.size()];
                    for (int i = 0; i < variableBindings.size(); i++) {
                        VariableBinding value = variableBindings.elementAt(i);
                        if (value.getVariable() instanceof OctetString) {
                            OctetString octetString = (OctetString) value.getVariable();
                            responseBinding[i] = octetString.toASCII(':');
                        } else {
                            responseBinding[i] = variableBindings.elementAt(i).toValueString();
                        }
                    }
                    return responseBinding;
                } else {
                    throw new IOException("Error: Request Failed");
                }
            } else {
                throw new NullPointerException("Error: Response PDU is null");
            }
        } else {
            throw new TimeoutException("Error: Agent Timeout... ");
        }
    }
}
