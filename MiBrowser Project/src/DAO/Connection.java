/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Giorgi Coelho
 */
class Connection {

    private String ip;
    private int port;
    private String communit;
    private String version;
    private int timeout;
    private int retransmission;
    
    public Connection (String _ip, int _port, String _communit, String _version, int _timeout, int _retransmission) {
        this.ip = _ip;
        this.port = _port;
        this.communit = _communit;
        this.version = _version;
        this.timeout = _timeout;
        this.retransmission = _retransmission;
    }

    public void open() {

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
    public String getVersion() {
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
}
