package smail.cli.tor;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.silvertunnel.netlib.adapter.java.JvmGlobalUtil;
import org.silvertunnel.netlib.adapter.socket.SocketGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class Anonymizer {
    
        
    // Overloads the Application to use the TOR anonymity network.
    // Tunnels connection directly
    //
    public static boolean useTor(){
        
        try{
            // enable redirection for the application
            JvmGlobalUtil.init();
            // Set the netlayer to use the TOR network
            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);
            // Waiting until ready causes a deadlock, thread stalls and does nothing.
            //netLayer.waitUntilReady();
            // Override the deafault net implementation with the TOR netlayer.
            JvmGlobalUtil.setNetLayerAndNetAddressNameService(netLayer, true);
            // Sleep for 10sec giving the netlayer time to complete initalization.
            Thread.sleep(10000);
            
            return true;
        } catch (Exception ex) {
            Logger.getLogger(Anonymizer.class.getName()).log(Level.SEVERE, 
                    "Failed to connect to TOR network.", ex);
            return false;
        }
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
