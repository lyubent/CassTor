package com.github.lyuben.gui;

import com.github.lyuben.tor.Anonymizer;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public abstract class FrameHandler {
    
    /**
     * Displays email client frame for sending emails.
     */
    public static void displayMainFrames() {
        new Thread () {	
            @Override
            public void run(){
                // Display a loding frame white TOR initialises
                StartupLoadFrame loader = new StartupLoadFrame();
                loader.setVisible(true);

                // Run the TOR initialisation procedure
                Anonymizer anon = new Anonymizer();
                anon.runTor();

                try {
                    // InetAddress test prvents deadlock in Astyanax threading.
                    InetAddress.getByName("dundee.ac.uk").isReachable(5000);
                } catch(Exception ex) {}

                // Once TOR Layer connection is completed, hide the loader frame.
                loader.dispose();

                // SWING GUI threading.
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //Prepare the login frame
                            new LoginFrame().setVisible(true);
                        } catch (Exception ex) {
                            Logger.getLogger(FrameHandler.class.getName()).log(Level.SEVERE,
                                    "Error with SWING Components", ex);
                        }
                    }
                }); // SWING thread
        }}.start();
    }
    
    
    /**
     * Displays frame for unzipping and bootstraping cassandra.
     */
    public static void displayFirstRunFrames(){
        // SWING GUI threading.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //Prepare the login frame
                    new FirstRunSetupFrame();
                } catch (Exception ex) {
                    Logger.getLogger(FrameHandler.class.getName()).log(Level.SEVERE,
                            "Error with SWING Components", ex);
                }
            }
        }); // SWING thread
    }
    
}
