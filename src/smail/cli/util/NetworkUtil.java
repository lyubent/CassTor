package smail.cli.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
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
    
    
    
    // Collects network interface data
    //
    public static void getNetworkData() {
        
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                displayInterfaceInformation(netint);   
            }
        } catch (SocketException ex) {
            Logger.getLogger(NetworkUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    // Displays network interface data
    // @return A string representation of the ethernet interface address.
    private static String displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        
        //System.out.printf("Display name: %s\n", netint.getDisplayName());
        //System.out.printf("Name: %s\n", netint.getName());
        
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
               
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            if(netint.getName().equals("en1") &&            // We want the ethernet interface
                    inetAddress.toString().length() < 17) { // and we want the IP not MAC address.
                
                System.out.println(String.valueOf(inetAddress).substring(1));
                
                //The ethernet address.
                return String.valueOf(inetAddress).substring(1);
            }
        }
        
        return "";
     }
    
    public static void displayInterfaces() throws SocketException {
        try{
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println(" IP Addr: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println(" Full list of IP addresses:");
                for (int i = 0; i < allMyIps.length; i++) {
                    System.out.println("    " + allMyIps[i]);
                }
            }
          } catch (Exception e) {
              System.out.println(" (error retrieving server host name)");
          }

        }
    
    
}
