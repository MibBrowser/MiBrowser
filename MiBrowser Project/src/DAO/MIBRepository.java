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
public class MIBRepository {
    
    public Connection connection;
    
    public MIBRepository(Connection con) {
        this.connection = con;
    }
    
    public String getInformation(String oid) {
        return "";
    }
}