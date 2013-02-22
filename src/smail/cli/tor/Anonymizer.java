package smail.cli.tor;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.silvertunnel.netlib.adapter.java.JvmGlobalUtil;
import org.silvertunnel.netlib.adapter.socket.SocketGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;
import smail.cli.Main;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class Anonymizer {
    
    // Redirect all of the program's traffic using this netlayer obj.
    private NetLayer netLayer;
    private Thread updateProgress;
    private Thread initTor;
    
    
    public Anonymizer() {
        
    }
    
    
    // Runs the initialisation and status updates of the TOR connection.
    //
    public boolean runTor() {
        try {
            JvmGlobalUtil.init();
            // Set the netlayer to use the TOR network
            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);
            // Override the deafault net implementation with the TOR netlayer.
            JvmGlobalUtil.setNetLayerAndNetAddressNameService(netLayer, true);

            //Sleep long enough to allow tor init to be complete
            Thread.sleep(15000);
            return true;
        } catch (InterruptedException ex) {
            Logger.getLogger(Anonymizer.class.getName()).log(Level.SEVERE, 
                    "Failed to connect to the TOR anonymity network.", ex);
        }
        return false;
    }
    
    
    
    // Falls back to TCP/IP incase TOR connection fails
    //
    //
    public boolean fallbackToTPC() {
        
        try {
            JvmGlobalUtil.init();
            // Set the netlayer to use the TOR network
            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP);
            // Override the deafault net implementation with the TOR netlayer.
            JvmGlobalUtil.setNetLayerAndNetAddressNameService(netLayer, true);
            System.out.println("Falling back to TCP/IP");
        } catch (Exception ex) {
            Logger.getLogger(Anonymizer.class.getName()).log(Level.SEVERE, 
                    "Failed to connect to the TCP/IP network.", ex);
        }
        
        return false;
    }
    
    
    // Accessor to the progress of the NetLayer object initialization
    // @return int representing percentage completion
    //
    public int getNetLayerStatus() {
        return (int)(netLayer.getStatus().getReadyIndicator() * 100);
    }
    
    
    
    // NetLayer accessor
    // @return NetLayer object
    public NetLayer getNetLayer() {
        return netLayer;
    }
    
    
    
    // Used to redirect sockets from TCP to TOR
    // Tunnels traffic of sockets allowing for anonymity.
    // Depricated - Too many applications block the sockets + slower than Global redirect.
    @Deprecated
    public static boolean useTorSockets(){
        try {
            SocketGlobalUtil.initSocketImplFactory();
            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);
            netLayer.waitUntilReady();
            SocketGlobalUtil.setNetLayerUsedBySocketImplFactory(netLayer);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Anonymizer.class.getName()).log(Level.SEVERE, 
                    "Failed to build TOR sockets.", ex);
            return false;
        }
    }
}
