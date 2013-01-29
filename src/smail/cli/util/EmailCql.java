package smail.cli.util;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class EmailCql {
   
    private static final String __COLUMNFAMILY__ = "MESSAGE_CF";
    // Builds CQL for retreiving all emails that haven't been deleted
    //
    public static String allNondeletedEmail(){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = 'No';";
    }
    
    
    
    // Builds CQL for retreiving all emails that have been deleted 
    //
    public static String allDeletedEmail(){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = 'Yes';";
    }
    
    
    
    // Builds CQL for retreiving emails that haven't been deleted by user
    //
    public static String nondeletedUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = 'No' AND RECEIVER = '" + username + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that have been deleted by user
    //
    public static String deletedUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_RECEIVER = 'Yes' AND RECEIVER = '" + username + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that haven't been deleted by sender
    //
    public static String nondeletedSenderEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = 'No' AND RECEIVER = '" + username + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that have been deleted by sender
    //
    public static String deletedSenderEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = 'Yes' AND SENDER = '" + username + "';";
    }
    
    
    
    // Build CQL for retreiving all email sent by a user which hasn't been deleted
    //
    public static String allSentUserEmail(String username){
        return "SELECT * FROM " + __COLUMNFAMILY__ + " WHERE DELETED_SENDER = 'No' AND SENDER = '" + username + "';";
    }
    
    
    
    // Build UPDATE (crUd) query to delete user's email in the system but not from the DB
    //
    public static String deleteEmail(String key, String location){
        
        switch (location) {
            case "inbox":
                return "UPDATE " + __COLUMNFAMILY__ + " SET DELETED_RECEIVER = 'Yes' WHERE KEY = '" + key + "';";
            case "sent":
                return "UPDATE " + __COLUMNFAMILY__ + " SET DELETED_SENDER = 'Yes' WHERE KEY = '" + key + "';";
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
