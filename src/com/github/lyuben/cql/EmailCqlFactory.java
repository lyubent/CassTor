package com.github.lyuben.cql;

import com.github.lyuben.util.Base64Crypto;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public abstract class EmailCqlFactory {
   
    private static final String __COLUMNFAMILY__ = "MESSAGE_CF";
    
    /**
     * Builds CQL for retreiving all emails that haven't been deleted
     * @return CQL string
     */
    public static String allNondeletedEmail(){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = '" 
                + Base64Crypto.encode("No") + "';";
    }
    
    
    
    /**
     * Builds CQL for retreiving all emails that have been deleted 
     * @return CQL string
     */
    public static String allDeletedEmail(){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = '" 
                + Base64Crypto.encode("Yes") + "';";
    }
    
    
    
    /**
     * Builds CQL for retreiving emails that haven't been deleted by user
     * @param username
     * @return CQL string
     */
    public static String nondeletedUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = '" 
                + Base64Crypto.encode("No") + "' AND RECEIVER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    /**
     * Builds CQL for retreiving emails that have been deleted by user
     * @param username
     * @return CQL string
     */
    public static String deletedUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = '"
                + Base64Crypto.encode("Yes") + "' AND RECEIVER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    /**
     * Builds CQL for retreiving emails that haven't been deleted by sender
     * @param username
     * @return CQL string
     */
    public static String nondeletedSenderEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = '" 
                + Base64Crypto.encode("No") + "' AND RECEIVER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    /**
     * Builds CQL for retreiving emails that have been deleted by sender
     * @param username
     * @return CQL string
     */
    public static String deletedSenderEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = '" 
                + Base64Crypto.encode("Yes") + "' AND SENDER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    /**
     * Build CQL for retreiving all email sent by a user which hasn't been deleted
     * @param username
     * @return CQL string
     */
    public static String allSentUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = '" 
                + Base64Crypto.encode("No") + "' AND SENDER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    /**
     * Build UPDATE (crUd) query to delete user's email in the system but not from the DB
     * @param key
     * @param location
     * @return CQL string
     */
    public static String deleteEmail(String key, String location){
        
        if(location.equals("inbox")) {
            return "UPDATE " + __COLUMNFAMILY__ + " SET DELETED_RECEIVER = '" 
                        + Base64Crypto.encode("Yes") + "' WHERE KEY = '" + key + "';";
        } else if(location.equals("sent")) {
            return "UPDATE " + __COLUMNFAMILY__ + " SET DELETED_SENDER = '" 
                        + Base64Crypto.encode("Yes") + "' WHERE KEY = '" + key + "';";
        } else if(location.equals("trash")) {
            return "";
        } else {
            return "";
        }
    }
    
    
    
    /**
     * Permanently Removes email from the DB.
     * @param __KEY__
     * @return CQL string
     */
    public static String hardDeleteEmail(String __KEY__){
        return "DELETE FROM " + __COLUMNFAMILY__ + " where KEY = '" + __KEY__ + "'";
    }
}
