package smail.cli.cql;

// @author lyubentodorov

import smail.cli.util.Base64Crypto;

// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class EmailCql {
   
    private static final String __COLUMNFAMILY__ = "MESSAGE_CF";
    
    // Builds CQL for retreiving all emails that haven't been deleted
    //
    public static String allNondeletedEmail(){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = '" 
                + Base64Crypto.encode("No") + "';";
    }
    
    
    
    // Builds CQL for retreiving all emails that have been deleted 
    //
    public static String allDeletedEmail(){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = '" 
                + Base64Crypto.encode("Yes") + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that haven't been deleted by user
    //
    public static String nondeletedUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = '" 
                + Base64Crypto.encode("No") + "' AND RECEIVER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that have been deleted by user
    //
    public static String deletedUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = '"
                + Base64Crypto.encode("Yes") + "' AND RECEIVER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that haven't been deleted by sender
    //
    public static String nondeletedSenderEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = '" 
                + Base64Crypto.encode("No") + "' AND RECEIVER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that have been deleted by sender
    //
    public static String deletedSenderEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = '" 
                + Base64Crypto.encode("Yes") + "' AND SENDER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    // Build CQL for retreiving all email sent by a user which hasn't been deleted
    //
    public static String allSentUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = '" 
                + Base64Crypto.encode("No") + "' AND SENDER = '" 
                + Base64Crypto.encode(username) + "';";
    }
    
    
    
    // Build UPDATE (crUd) query to delete user's email in the system but not from the DB
    //
    public static String deleteEmail(String key, String location){
        
        switch (location) {
            case "inbox":
                return "UPDATE " + __COLUMNFAMILY__ + " SET DELETED_RECEIVER = '" 
                        + Base64Crypto.encode("Yes") + "' WHERE KEY = '" + key + "';";
            case "sent":
                return "UPDATE " + __COLUMNFAMILY__ + " SET DELETED_SENDER = '" 
                        + Base64Crypto.encode("Yes") + "' WHERE KEY = '" + key + "';";
            case "trash":
                return "";
            default:
                return "";   
        }
    }
    
    
    
    // Permenantly Removes email from the DB.
    //
    public static String hardDeleteEmail(String __KEY__){
        return "DELETE FROM " + __COLUMNFAMILY__ + " where KEY = '" + __KEY__ + "'";
    }
}