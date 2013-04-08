package com.github.lyuben.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class NetworkUtil {
    
    
    
    public NetworkUtil() {
        
    }

    
   /**
    * Displays network interface data
    * Typically the localHostName is the required one.
    * @return A string representation of the active interface address.
    */
    public static String getInterfaceIP() {
        try {
            
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)){
                System.out.printf("Display name: %s\n", netint.getDisplayName());
                System.out.printf("Name: %s\n", netint.getName());
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    //init ip string as inet.getHostAddress might be null
                    String ip = "" + inetAddress.getHostAddress();
                    // VPN connection
                    if(netint.getName().contains("ppp") && !ip.equals("") && ip.length() < 16){
                        System.out.println("VPN InetAddress: " + ip);
                        return ip;
                    }
                    // Ethernet connection that is IPv4 and starts with 134 / 182 / 10 (private ip ranges)
                    if(netint.getName().contains("eth") && !ip.equals("") && ip.length() < 16
                            && (ip.contains("192.") || ip.contains("134.") || ip.contains("10."))){
                        System.out.println("ETH InetAddress: " + ip);
                        return ip;
                    }
                    // Wifi address (unlikely)
                    if(netint.getName().contains("en") && !ip.equals("") && ip.length() < 16){
                        System.out.println("Wifi InetAddress: " + ip);
                        return ip;
                    }
                }
                System.out.printf("\n");
            }
            
            //Default seed node.
            return "134.36.36.188";
        } catch (Exception ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, 
                    "Error getting the interface IP of the machine.", ex);
        }
        
        return "";
    }
}
