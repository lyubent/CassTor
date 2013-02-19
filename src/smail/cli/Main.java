package smail.cli;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.silvertunnel.netlib.adapter.java.JvmGlobalUtil;
import org.silvertunnel.netlib.api.NetFactory;
import org.silvertunnel.netlib.api.NetLayer;
import org.silvertunnel.netlib.api.NetLayerIDs;
import smail.cli.tor.Anonymizer;

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
                    Anonymizer.useTor();
                    for(int i=0;i<100;i++)
                    System.out.println("COMPLETED TOR INIT!!!");
                    //Sleep long enough to allow tor init to be complete
                    Thread.sleep(15000);
                    
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
                    //Display login frame
                    //LoginFrame loginFrame = new LoginFrame();
                    
                    //run tests
                    new smail.cli.test.TestSuite().runTests();

                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    };
}