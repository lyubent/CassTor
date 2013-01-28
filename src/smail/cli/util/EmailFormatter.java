package smail.cli.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormatter {
    
    //
    //
    //
    public static String structureMessage(String key, String body, String from, String date, String subject){
                
        if(body.length() > 75){
            body = body.substring(0, 59);
            body += "...";
        }
        
        return "<html>" + 
               "<div id='" + 
                    "%" + key + "% " + 
                    "$" + from + "$ " + 
                    "&" + subject + "& " + 
                    "!" + body + "! " + 
                    "~" + date + "~' " + 
               "style='height:15px;padding:5px;'>" + 
               "<b>" + subject + "</b><br/>" + 
               from + " <span style='color:white;'> . </span> sent-on " + 
               date + "<br/><span style='color:gray;'>" + 
               body + "</span>" +
               "</html>";
    }
    
    
    
    // Structures the detailed email message to be displayed using HMTL
    // @return returns string representing the structured email
    //
    public static String structureDetailedMessage(String [] emailContent){
        
        return "<html>" + 
               "<div style='background-color:#f0f8ff;padding:12px;'>" + 
               "<p style='font-size:13px;color:black;'>" + emailContent[0] + "</p>" + 
               "<span style='color:#383838;'>From <span style='color:#cd5c5c'>" + emailContent[1] + 
               "</span> sent on " + emailContent[2] + "</span>" + 
               "<br/></div><div style='padding:12px;style='font-size:13px;'>" + 
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
                    pattern = "&(.*?)&"; //Subject
                    break;
                case "BODY":
                    pattern = "\\!(.*?)\\!"; //Body
                    break;
                case "DATE":
                    pattern = "~(.*?)~"; //Date
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
            java.util.logging.Logger.getLogger(EmailFormatter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            return "failed";
        }
        
    }
}
