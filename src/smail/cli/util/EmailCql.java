package smail.cli.util;

public class EmailCql {
   
    // Builds CQL for retreiving all emails that haven't been deleted
    //
    public static String allNondeletedEmail(){
        return "SELECT * FROM MESSAGE WHERE DELETED_RECEIVER = 'No';";
    }
    
    
    
    // Builds CQL for retreiving all emails that have been deleted 
    //
    public static String allDeletedEmail(){
        return "SELECT * FROM MESSAGE WHERE DELETED_RECEIVER = 'Yes';";
    }
    
    
    
    // Builds CQL for retreiving emails that haven't been deleted by user
    //
    public static String nondeletedUserEmail(String username){
        return "SELECT * FROM MESSAGE WHERE DELETED_RECEIVER = 'No' AND RECEIVER = '" + username + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that have been deleted by user
    //
    public static String deletedUserEmail(String username){
        return "SELECT * FROM MESSAGE WHERE DELETED_RECEIVER = 'Yes' AND RECEIVER = '" + username + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that haven't been deleted by sender
    //
    public static String nondeletedSenderEmail(String username){
        return "SELECT * FROM MESSAGE WHERE DELETED_SENDER = 'No' AND RECEIVER = '" + username + "';";
    }
    
    
    
    // Builds CQL for retreiving emails that have been deleted by sender
    //
    public static String deletedSenderEmail(String username){
        return "SELECT * FROM MESSAGE WHERE DELETED_SENDER = 'Yes' AND SENDER = '" + username + "';";
    }
    
    
    
    // Build CQL for retreiving all email sent by a user which hasn't been deleted
    //
    public static String allSentUserEmail(String username){
        return "SELECT * FROM MESSAGE WHERE DELETED_SENDER = 'No' AND SENDER = '" + username + "';";
    }
    
    
    
    // Build UPDATE (crUd) query to delete user's email in the system but not from the DB
    //
    public static String deleteEmail(String key, String location){
        
        switch (location) {
            case "inbox":
                return "UPDATE MESSAGE SET DELETED_RECEIVER = 'Yes' WHERE KEY = '" + key + "';";
            case "sent":
                return "UPDATE MESSAGE SET DELETED_SENDER = 'Yes' WHERE KEY = '" + key + "';";
            case "trash":
                return "";
            default:
                return "";   
        }
    }
    
    
    
    // Permenantly Removes email from the DB.
    //
    public static String hardDeleteEmail(String __KEY__){
        return "DELETE FROM MESSAGE where KEY = '" + __KEY__ + "'";
    }
}
