package com.github.lyuben.cql;

import com.github.lyuben.util.BCrypto;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public abstract class SchemaCqlFactory {
    private static final String __COLUMNFAMILY__ = "MESSAGE_CF";
    
    /**
     * Builds CQL for retreiving all emails that haven't been deleted
     * 
     * @param newReplicationFactor
     * @param keyspaceName
     * @return 
     */
    public static String updateReplicationFactor(int newReplicationFactor, String keyspaceName){
        return "ALTER KEYSPACE \"" + keyspaceName + "\" WITH REPLICATION = { " + 
               "'class' : 'SimpleStrategy', 'replication_factor' : " + (newReplicationFactor+1) + " };";
    }
    
    
    
    /**
     * Builds CQL for retrieving all emails that haven't been deleted
     * 
     * @param keyspace - used for retrieving replication factor.
     * @return 
     */
    public static String getCurrentReplicationFactor(String keyspace){
        return "SELECT strategy_options " + 
               "FROM system.schema_keyspaces " + 
               "WHERE keyspace_name = '" + keyspace + "';";
    }
    
    
    
    /**
     * Builds CQL for creating the USER keyspace
     * 
     * @param keyspace - used for retrieving replication factor.
     * @return 
     */
    public static String createUserKS(){
        return  "CREATE KEYSPACE casstor with " +
                "replication = {'class':'SimpleStrategy','replication_factor':3};";
    }
    
    
    
    /**
     * Builds CQL for creating the USER table (column family)
     * 
     * @param keyspace - used for retrieving replication factor.
     * @return 
     */
    public static String createUserCF(){
        return    "CREATE TABLE users ( "
                + "username varchar, "
                + "password varchar, "
                + "salt varchar, "
                + "registered bigint, "
                + "PRIMARY KEY (username)"
                + ");";
    }
    
    
    
    
    /**
     * Inserts a user record into the casstor.user column family
     * 
     * @param username
     * @param password
     * @return 
     */
    public static String registerUser(String username, String password){
        //dummy variable... lets trick the hackers
        String salt = org.apache.commons.lang.RandomStringUtils.random(13);
        String hashedPassword = BCrypto.generateHast(password);
                
        return  "INSERT INTO users (username, password, salt, registered) " +
                "VALUES ('" + username + "', " + 
                "'" + hashedPassword + "', " + 
                "'" + salt + "', " + System.currentTimeMillis() + ");";
    }
    
    
    
    public static String userExists(String username){
        return  "SELECT count(*) FROM users WHERE username='" + username + "';";
    }
    
    
    
    public static String getHashedPw(String username){
        return  "SELECT password FROM users WHERE username='" + username + "';";
    }
}
