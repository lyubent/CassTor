package com.github.lyuben.util;

import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class NetworkUtil {
    
    
    
    public NetworkUtil() {
        
    }

    
    
    // Displays network interface data
    // Typically the localHostName is the required one.
    // @return A string representation of the active interface address.
    //
    public static String getInterfaceIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, 
                    "Error getting the localhost interface (IP) of the machine.", ex);
        }
        
        return "";
    }
    
    
    
    // Display all the network interfaces regardless of protocol
    // Displays both IPv4 and IPv6, can cause problems as we only need IPv4
    // @void displays a list of IPs to the terminal
    //
    @Deprecated
    public static void displayAllInterfaceIPs() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            
            // The host might have multiple IP addresses
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null) {
                for (int i = 0; i < allMyIps.length; i++) {
                    System.out.println(allMyIps[i]);
                }
            }
        } catch (Exception ex) {
              Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, 
                      "Error displaying the network interfaces (IPs) of the machine.", ex);
        }
    }
    
    
}
