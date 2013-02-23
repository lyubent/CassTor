package com.github.lyuben.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class DateUtil {
    
    public static String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 
        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
    
    public static String getUnixTimestamp(){
        return String.valueOf(System.currentTimeMillis() / 1000L);
    }
}
