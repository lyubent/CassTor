package smail.cli.util;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringEscapeUtils;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class EmailFormatter {
    
    // Builds the message in HTML to be displayed in the list view.
    // @param requires multiple strings that represent parts of the email
    //
    public static String structureMessage(String key, String body, String from, String date, String subject){
                
        if(body.length() > 75){
            body = body.substring(0, 59);
            body += "...";
        }
        
        System.out.println(
                "key: " + key +
                "\nbody: " + Base64Crypto.decode(body) +
                "\nfrom: " + Base64Crypto.decode(from) +
                "\ndate: " + Base64Crypto.decode(date) +
                "\nsubj: " + Base64Crypto.decode(subject)
                );
        
        String tagData = "<span " +
               "$" + Base64Crypto.decode(from) + "$ " +
               "`£" + Base64Crypto.decode(subject) + "`£ " + 
               "`!" + Base64Crypto.decode(body) + "`! " + 
               "`~" + Base64Crypto.decode(date) + "`~ >" +
               "</span>";
        
        String dataa = "<html>" + 
               "<div id=\"" + 
                    "%" + key + "%\" " + 
                    
               "style=\"height:15px;padding:5px;\">" + 
               "<b>" + Base64Crypto.decode(subject) + "</b><br/>" + 
               Base64Crypto.decode(from) + " <span style=\"color:white;\"> . </span> sent-on " + 
               Base64Crypto.decode(date) +
                 "<br/><span style=\"color:gray;\">" + 
               Base64Crypto.decode(body) + "</span>" +
               "</div style='max-height:0px;height:0px;overflow:hidden;" +
               
               // stores content between "tags" that can be stripped out.
               tagData + "</div>" +
                
               "</html>";
        
        return dataa;
        //org.apache.commons.lang.StringEscapeUtils.escapeJava("");
    }
    
    
    
    // Structures the detailed email message to be displayed using HMTL
    // @return returns string representing the structured email
    //
    public static String structureDetailedMessage(String [] emailContent){
        
        return "<html>" + 
               "<div style=\"background-color:#f0f8ff;padding:12px;\">" + 
               "<p style=\"font-size:13px;color:black;\">" + emailContent[0] + "</p>" + 
               "<span style=\"color:#383838;\">From <span style=\"color:#cd5c5c\">" + emailContent[1] + 
               "</span> sent on " + emailContent[2] + "</span>" + 
               "<br/></div><div style=\"padding:12px;style=\"font-size:13px;\">" + 
               "<span>" + emailContent[3] + "</span></div>" 
               + "</html>"; 
    }
    
    
    
    // Uses regular expressions to find the KEY, Sender or title  of the selected list item in jListInboxMail
    // @return returns a string representing the KEY for the selected cassandra row
    //
    public static String applyRegexFilter(String template, String filterType){
        try {
            String pattern;
            
            switch (filterType) {
                case "SENDER":
                    pattern = "\\$(.*?)\\$"; //Sender
                    break;
                case "SUBJECT":
                    pattern = "`£(.*?)`£"; //Subject
                    break;
                case "BODY":
                    pattern = "`\\!(.*?)`\\!"; //Body
                    break;
                case "DATE":
                    pattern = "`~(.*?)`~"; //Date
                    break;
                default:
                    // Key
                    pattern = "%(.*?)%"; // Key
                    break;
            }

            Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(template);
            
            if (m.find()) {
                return m.group(1);
            }
            else{
                return "nope";
            }
            
        } catch(IllegalStateException ex) {
            Logger.getLogger(EmailFormatter.class.getName()).log(java.util.logging.Level.SEVERE, 
                    "Failed to execute regex.", ex);
            return "failed";
        }
        
    }
}
