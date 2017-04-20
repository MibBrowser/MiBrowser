/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author Giorgi Coelho & Guilherme Toniello
 */
public class Connection {

    private String ip;
    private int port;
    private String communit;
    private int version;
    private int timeout;
    private int retransmission;
    private TransportMapping transport;

    public Connection(String _ip, int _port, String _communit, int _version, int _timeout, int _retransmission) {
        this.ip = _ip;
        this.port = _port;
        this.communit = _communit;
        this.version = _version;
        this.timeout = _timeout;
        this.retransmission = _retransmission;
    }

    public CommunityTarget open() throws IOException {
        // Create TransportMapping and Listen
        this.transport = new DefaultUdpTransportMapping();
        this.transport.listen();

        // Create Target Address object
        CommunityTarget comtarget = new CommunityTarget();
        comtarget.setCommunity(new OctetString(this.communit));
        comtarget.setVersion(this.version);
        comtarget.setAddress(new UdpAddress(this.ip + "/" + port));
        comtarget.setRetries(this.retransmission);
        comtarget.setTimeout(this.timeout);
        return comtarget;
    }

    public void close() {

    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @return the communit
     */
    public String getCommunit() {
        return communit;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @return the retransmission
     */
    public int getRetransmission() {
        return retransmission;
    }

    /**
     * @return the transport
     */
    public TransportMapping getTransport() {
        return transport;
    }
}
