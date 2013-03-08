/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lyuben.gui;

import com.github.lyuben.tor.Anonymizer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lyubentodorov
 */
public abstract class FrameHandler {
    
    public static void displayMainFrames() {
//        new Thread () {	
//            @Override
//            public void run(){
                System.out.println("fadsfas");
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
                            LoginFrame loginFrame = new LoginFrame();
                        } catch (Exception ex) {
                            Logger.getLogger(FrameHandler.class.getName()).log(Level.SEVERE,
                                    "Error with SWING Components", ex);
                        }
                    }
                }); // SWING thread
//            }}.start();
    }
    
    public static void displayFirstRunFrames(){
        
        // SWING GUI threading.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //Prepare the login frame
                    new FirstRunSetupFrame(new LoginFrame());
                } catch (Exception ex) {
                    Logger.getLogger(FrameHandler.class.getName()).log(Level.SEVERE,
                            "Error with SWING Components", ex);
                }
            }
        }); // SWING thread
    }
    
}
