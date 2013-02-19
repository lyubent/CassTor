package smail.cli;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.silvertunnel.netlib.adapter.java.JvmGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;
import smail.cli.netlib.Anonymizer;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class Main {    
    
    public static void main(String[] args) {
        
        //Build schema, run only once on seed node.
        //smail.cli.astyanax.Schema.buildSchema(smail.cli.astyanax.Astyanax.getKeyspaceContext());

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run(){
                try {
                    //Anonymizer.useTor();
                    Thread.sleep(10000);
                    System.out.println("COMPLETED TOR INIT!!!\n\n\n\n\n\n");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        // SWING threading.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run(){
                
                
                try {
                    
                    /*************************
                     ***********TESTS*********
                     *************************/
                    //new smail.cli.netlib.Anonymizer().tryConn();
                    //new smail.cli.netlib.Anonymizer().tryPureConn();
                    //new smail.cli.netlib.Anonymizer().buildTORSocket();
                    
                    //String [] jj = new String [10];
                    //TorLib.main(jj);
                    
                    //TorSocket.main(new String(""));
                    //TorSocket.main(new String(""), true);
                    
                    /*************************
                     *********TESTS - END*****
                     *************************/
                    
                    
                    
                    //Display login frame
                    //LoginFrame loginFrame = new LoginFrame();
                    
                    //run tests
                    //smail.cli.netlib.Anonymizer.useTor();
                    //perpareAnonymousCommunication();
                    new smail.cli.test.TestSuite().runTests();
                    //new smail.cli.test.TestSuite().runTests();

                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    };
    
    
//    public static void perpareAnonymousCommunication() {
//        // do not log here to avoid to disturb the next command
//        
//        // prepare redirection of lower services
//        JvmGlobalUtil.init();
//        //System.out.println("perpareAnonymousCommunication() initialization done");
//
//        // classic communication:   NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP) (--> HTTP over plain TCP/IP)
//        // anonymous communication: NetFactory.getInstance().getNetLayerById(NetLayerIDs.TOR)   (--> HTTP over TCP/IP over Tor network)
//        NetLayer nextNetLayer = NetFactory.getInstance().getNetLayerById(NetLayerIDs.TCPIP); 
//        
//        JvmGlobalUtil.setNetLayerAndNetAddressNameService(nextNetLayer, true);
//
//        //System.out.println("perpareAnonymousCommunication() end");
//    }
}