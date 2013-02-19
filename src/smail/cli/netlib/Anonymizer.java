/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smail.cli.netlib;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.silvertunnel.netlib.adapter.java.JvmGlobalUtil;
import org.silvertunnel.netlib.adapter.socket.SocketGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;
import org.silvertunnel.netlib.api.NetSocket;

/**
 *
 * @author lyubentodorov
 */
public class Anonymizer {
    
    private NetSocket netSocket;
    
    private static final Logger log = Logger.getLogger("");
    private static final long timeoutInMs = 5000;
    private static final String __SERVER__ = "github.com";
    
    public Anonymizer() {
        
    }
    
        
    // Overloads the Sockets to use the TOR anonymity network.
    // Tunnels connection
    //
    public static boolean useTor(){
        try{
//            SocketGlobalUtil.initSocketImplFactory();
//            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR);
//            netLayer.waitUntilReady();
//            SocketGlobalUtil.setNetLayerUsedBySocketImplFactory(netLayer);
//            System.out.println("SECOND READY TEST:");
//            netLayer.waitUntilReady();
            
            
            
            // enable redirection (globally for the complete JVM)
            JvmGlobalUtil.init();

            // select the NetLayer implementation that should be used;
            //   default is TcpipNetLayer with NetLayerIDs.TCPIP;
            //   here we set TorNetLayer with NetLayerIDs.TOR:
            NetLayer netLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR); 

            // wait (block the current thread) until this netLayer instance is up and ready and
            // redirect to the selected netLayer and its corresponding name service implementation
            JvmGlobalUtil.setNetLayerAndNetAddressNameService(netLayer, true);
            System.out.println("NOW USING TOR");

            return true;
        } catch (Exception ex) {
            Logger.getLogger(Anonymizer.class.getName()).log(Level.SEVERE, 
                    "Failed to connect to tor.", ex);
        }

        return false;

    }
}
