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
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the communit
     */
    public String getCommunit() {
        return communit;
    }

    /**
     * @param communit the communit to set
     */
    public void setCommunit(String communit) {
        this.communit = communit;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * @return the retransmission
     */
    public int getRetransmission() {
        return retransmission;
    }

    /**
     * @param retransmission the retransmission to set
     */
    public void setRetransmission(int retransmission) {
        this.retransmission = retransmission;
    }

}
