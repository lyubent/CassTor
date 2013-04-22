/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lyuben.util;

import com.github.lyuben.bridge.JDBC;
import com.github.lyuben.cql.SchemaCqlFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lyubentodorov
 */
public abstract class AuthenticationHandler {
    
    
    public static boolean register(String username, String password) {
        JDBC.jdbcExecCQLQuery(SchemaCqlFactory.registerUser(username, password), "casstor");
        
        return false;
    }
    
    public static boolean savePassword(String password) {
        String hashedPassword = BCrypto.generateHast(password);
        
        // insert in db
        JDBC.jdbcExecCQLQuery(SchemaCqlFactory.createUserKS(), "system");
         
        //
        // INSERT INTO users (username, password, salt, registered)
        // VALUES ('', 'gwashington', 'I chopped down the cherry tree', 12341243124);
        //
        return false;
    }
    
    public static boolean isRegistered() {
        
        
        return false;
    }
    
    public static boolean isAvailable(String username) {
        ResultSet rs = JDBC.jdbcExecCQLQuery(SchemaCqlFactory.userExists(username), "casstor");
        
        try {
            while (rs.next()) {
                return rs.getString("count").equals("0");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
    
    public static String getHashedPw(String username) {
        ResultSet rs = JDBC.jdbcExecCQLQuery(SchemaCqlFactory.getHashedPw(username), "casstor");
        
        try {
            while (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
