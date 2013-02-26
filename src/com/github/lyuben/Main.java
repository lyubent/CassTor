package com.github.lyuben;

import com.github.lyuben.gui.LoginFrame;
import com.github.lyuben.gui.StartupLoadFrame;
import com.github.lyuben.tor.Anonymizer;
import com.netflix.astyanax.connectionpool.exceptions.ConnectionException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class Main {
    
    public static void main(String[] args) throws IOException, InterruptedException, ConnectionException {
        
        try {
//            //Build schema, run only once on seed node.
//            //com.github.lyuben.bridge.Schema.buildSchema(com.github.lyuben.bridge.Astyanax.getKeyspaceContext());
//            
//            //run tests
//            //new com.github.lyuben.test.TestSuite().runTests();
//            
//            // TOR initialization threading.
            new Thread () {
                @Override
                public void run () {
                    // Display a loding frame white TOR initialises
                    StartupLoadFrame loader = new StartupLoadFrame();
                    loader.setVisible(true);
    
                    // Run the TOR initialisation procedure
                    Anonymizer anon = new Anonymizer();
                     anon.runTor();
                    
                    
                    
    try{
        //"134.36.36.188".equals(InetAddress.getLocalHost().getHostAddress().toString())
        new Thread () {
    @Override
    public void run () {
    try {
        System.out.println("\n\n\n\n\n\n\nCHECKING NETSTATS!!!\n\n\n\n\n");
        InetAddress jj = InetAddress.getByName("dundee.ac.uk");
        if(jj.isReachable(5000)){
            System.out.println("SUCESS");
            System.out.println("SUCESS");
            System.out.println("SUCESS");
            System.out.println("SUCESS");
            System.out.println("SUCESS");
        } else {
            System.out.println("FAILED");
            System.out.println("FAILED");
            System.out.println("FAILED");
            System.out.println("FAILED");
            System.out.println("FAILED");
        }
    } catch (UnknownHostException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }};}.start();

    } catch (Exception ex) {Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);}
                    
                    
                    
                    //anon.cleanNetLayer();
                    
                    // Once TOR Layer connection is completed, hide the loader frame.
                    loader.dispose();
                    
                    // SWING GUI threading.
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run(){
                            try {
                                //Prepare the login frame
                                LoginFrame loginFrame = new LoginFrame();
                            } catch (Exception ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
                                        "Error with SWING Components", ex);
                            }
                        }
                    });
    
                }
            }.start();
            
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
                    "Error in main thread.", ex);
        }
    }
}