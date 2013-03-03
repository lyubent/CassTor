package com.github.lyuben.util;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        
        String fullBody = body;
        fullBody = org.apache.commons.lang.StringEscapeUtils.escapeHtml(
                escape(Base64Crypto.decode(fullBody)));
        
        // Un-encode
        body = escape(Base64Crypto.decode(body));
        subject = escape(Base64Crypto.decode(subject));
        date = escape(Base64Crypto.decode(date));
        from = escape(Base64Crypto.decode(from));
        
        if(body.length() > 75){
            body = body.substring(0, 59);
            body += "...";
        }
        
        String tagData = "<span " +
               "$" + from + "$ " +
               "`£" + subject + "`£ " +
               "`!" + fullBody + "`! " + 
               "`~" + date + 
               "`~ >" +
               "</span>";
        
        String data = "<html>" + 
               "<div id=\"" + 
                    "%" + key + "%\" " +
               "style=\"height:15px;padding:5px;\">" + 
               "<b>" + subject + "</b><br/>" + 
               from + " <span style=\"color:white;\"> . </span> sent-on " + 
               date +
                 "<br/><span style=\"color:gray;\">" + 
               body + "</span>" +
               "<div style='max-height:0px;height:0px;overflow:hidden;'>" +
               
               // stores content between "tags" that can be stripped out.
               tagData + "</div>" +
                
               "</html>";
        return data;
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
               "<span>" + org.apache.commons.lang.StringEscapeUtils.unescapeHtml(
                emailContent[3]) + "</span></div>" 
               + "</html>"; 
    }
    
    
    
    // Uses regular expressions to find the KEY, Sender or title  of the selected list item in jListInboxMail
    // @return returns a string representing the KEY for the selected cassandra row
    //
    public static String applyRegexFilter(String template, String filterType){
        try {
            String pattern;
            
            if(filterType.equals("SENDER")) {
                pattern = "\\$(.*?)\\$"; //Sender
            } else if(filterType.equals("SUBJECT")) {
                pattern = "`£(.*?)`£"; //Subject
            } else if(filterType.equals("BODY")) {
                pattern = "`\\!(.*?)`\\!"; //Body
            } else if(filterType.equals("DATE")) {
                pattern = "`~(.*?)`~"; //Date
            } else {
                // Key
                pattern = "%(.*?)%"; // Key
            }

            Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(template);
            
            if (m.find()) {
                return m.group(1);
            }
            else{
                return "";
            }
            
        } catch(IllegalStateException ex) {
            Logger.getLogger(EmailFormatter.class.getName()).log(java.util.logging.Level.SEVERE, 
                    "Failed to execute regex.", ex);
            return "";
        }
        
    }
    
    
    
    // Custom escape for escaping HTML and Java strings
    // @return Retuns the clean string
    //
    public static String escape(String toEscape) {
        return toEscape.replaceAll("\\\\", "&#92;")
                       .replaceAll("\"", "&#34;")
                       .replaceAll("'", "&#146;")
                       .replaceAll("\t", "&#09;")
                       .replaceAll("\n", "<br>")
                       .replaceAll("/", "&#47;");
    }
}
