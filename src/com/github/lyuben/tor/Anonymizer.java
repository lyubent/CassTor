package com.github.lyuben.tor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.silvertunnel.netlib.adapter.java.JvmGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class Anonymizer {
    
    // Redirect all of the program's traffic using this netlayer obj.
    private Thread updateProgress;
    private Thread initTor;
    private NetLayer netLayer;
    
    
    public Anonymizer() {
        
    }
    
    
    /**
     * Runs the initialisation and status updates of the TOR connection.
     */
    public void runTor() {
        JvmGlobalUtil.init();
        // Set the netlayer to use the TOR network
        netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);

        //below line sounds like a good thing but after 
        // tests causes 50% connection failiure
        // netLayer.waitUntilReady();

        // Override the deafault net implementation with the TOR netlayer.
        JvmGlobalUtil.setNetLayerAndNetAddressNameService(netLayer, true);
    }
    
    
    
    /**
     * Clears the netlayer used for tunneling of traffic.
     * @throws IOException 
     */
    public void cleanNetLayer() throws IOException {
        netLayer.clear();
        netLayer.waitUntilReady();
    }
    
    
    
    /**
     * Falls back to TCP/IP incase TOR connection fails
     * 
     * @return Boolean representing success of the fallback
     */
    public boolean fallbackToTPC() {
        
        try {
            netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP);
            netLayer.clear();
            netLayer.waitUntilReady();
            
        } catch (Exception ex) {
            Logger.getLogger(Anonymizer.class.getName()).log(Level.SEVERE, 
                    "Failed to connect to the TCP/IP network.", ex);
        }
        
        return false;
    }
    
    /**
     * Accesor to the progress of the NetLayer object initialization
     * 
     * @return int representing percentage completion
     */
    public int getNetLayerStatus() {
        return (int)(netLayer.getStatus().getReadyIndicator() * 100);
    }
    
    
    /**
     * NetLayer accessor
     * 
     * @return A silvertunnel netlayer instance used for tunneling application's traffic.
     */
    public NetLayer getNetLayer() {
        return netLayer;
    }
}
