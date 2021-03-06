package com.github.lyuben.gui;

import com.github.lyuben.bridge.Astyanax;
import com.github.lyuben.bridge.JDBC;
import com.github.lyuben.util.ArchiveUtil;
import com.github.lyuben.util.FileUtil;
import com.github.lyuben.util.FramePositionHandler;
import java.awt.FileDialog;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class FirstRunSetupFrame extends javax.swing.JFrame {
    
    private static final String __LICENCEURL__ =
        FileUtil.getAppName() + "LICENCE.md";
    
    public FirstRunSetupFrame() {
        setupLookAndFeel();
        initComponents();
        hideUnused();
        setupFrame();
    }
    
    
    
    private void setupLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Mac OS X".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                } else if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, 
                    "Failed to setup look and feel, Look&Feel classes not found", ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    "Failed to instantiate the look and feel classes.", ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    "Don't have permissions to access look and feel classes.", ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    "Look and feel look is not supported.", ex);
        }
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Accept = new javax.swing.JButton();
        jCheckBox_AcceptTermsAndConditions = new javax.swing.JCheckBox();
        jScrollPane_Background = new javax.swing.JScrollPane();
        jTextPane_Licence = new javax.swing.JTextPane();
        jButton_Okey = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_Accept.setText("Accept");
        jButton_Accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AcceptActionPerformed(evt);
            }
        });

        jCheckBox_AcceptTermsAndConditions.setText("I Accept the Terms & Conditions");

        jTextPane_Licence.setEditable(false);
        jTextPane_Licence.setToolTipText("Please read the licence, it's not that long or complex.");
        jScrollPane_Background.setViewportView(jTextPane_Licence);

        jButton_Okey.setText("Okey");
        jButton_Okey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OkeyActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane_Background)
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .add(jCheckBox_AcceptTermsAndConditions)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jButton_Okey, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton_Accept, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(jScrollPane_Background, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 315, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jCheckBox_AcceptTermsAndConditions)
                    .add(jButton_Okey)
                    .add(jButton_Accept))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * User has accepted the terms and conditions
     * We can now display Cassandra setup instructions and extract Cassandra.
     * @param evt 
     */
    private void jButton_AcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcceptActionPerformed
        if(jCheckBox_AcceptTermsAndConditions.isSelected()){
            
            //Display loading message.
            jTextPane_Licence.setText("Loading...");
            jCheckBox_AcceptTermsAndConditions.setVisible(false);
            jButton_Accept.setVisible(false);
            
            //first try to extract cassandra to Desktop, then update the UI.
            extractCassandra();
            JDBC.incrementReplicationFactor(Astyanax.getKSName());
            
            jButton_Okey.setVisible(true);
            jTextPane_Licence.setText(getStartupInstructions());
        } else {
            JOptionPane.showMessageDialog(this, "You haven't agreed to terms and conditions."
                    + "\nPlease Read and accept the terms and conditions!",
                "Please accept Terms & Conditions", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton_AcceptActionPerformed

    
    
    /**
     * Display cassandra startup instructions and launch login interface
     * Destroys this frame and displays the parent Login frame.
     * @param evt 
     */
    private void jButton_OkeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OkeyActionPerformed
        JOptionPane.showMessageDialog(this, "The application is now ready to run!\n" +
            "Please restart the application.\nThe secure email client will begin\nloading.",
            "Ready to start!", JOptionPane.WARNING_MESSAGE);
        
        this.setVisible(false);
        
        try {
            FileUtil.startCassandraServer();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to start Cassandra.\n" +
                "The program will now exit",
                "Error - Cassandra startup failed.", JOptionPane.WARNING_MESSAGE);
            Logger.getLogger(FirstRunSetupFrame.class.getName()).log(Level.SEVERE, 
                    "Error starting cassandra.", ex);
        }
        
        this.dispose();
        System.exit(0); // faster
    }//GEN-LAST:event_jButton_OkeyActionPerformed
    
    
    /**
     * Extracts cassandra and updates log file.
     */
    private void extractCassandra() {
        try {        
            // Extracting Cassandra and setting up permissions.
            ArchiveUtil.unzip();
            ArchiveUtil.setExecPermissions();
            FileUtil.writeToLog("[SUCCESS]\tExtracted cassandra to desktop.");

            //Setup the YAML file to store the local ip
            FileUtil.configureCassandraYAML();
            FileUtil.writeToLog("[SUCCESS]\tConfigured YAML file.");
        } catch (Exception ex) {
            FileUtil.writeToLog("[ERROR]\tFailed to extract cassandra.");
            JOptionPane.showMessageDialog(this, "A problem occured while "
                    + "extracting cassandra.\nThe Cassandra.zip resource was not found,"
                    + "\nReinstalling the package should fix this problem."
                    , "Failed to extract cassandra.", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FirstRunSetupFrame.class.getName()).log(Level.SEVERE, 
                    "Error unziping cassandra.", ex);
        }  
        
    }
    
    
    
    /**
     * Sets up the frame when its initialized
     */
    private void setupFrame() {
        FramePositionHandler.centerFrame(this);
        populateAndStyleLicence();
        this.setVisible(true);
    }
    
    
    
    /**
     * Hides components of the application that are not used to speed up app.
     */
    private void hideUnused(){
        jButton_Okey.setVisible(false);
    }
    
    
    
    /**
     * Configures and sets up layout for the Licence textarea
     */
    private void populateAndStyleLicence(){
        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
        
        String licence = FileUtil.getTextFromFile(__LICENCEURL__);
        
        jTextPane_Licence.getStyledDocument().setParagraphAttributes(0,licence.length(),sa,false);
        jTextPane_Licence.setMargin(new java.awt.Insets(2,2,2,2));
        jTextPane_Licence.setText(licence);
    }
    
    
    
    /**
     * Tradeoff, bad coding VS opening uncessessary buffers.
     * @return Returns the startup instructions
     */
    private String getStartupInstructions() {
        return "Extracting and configuring Cassandra has completed successfully.\n\n" +
               "Starting Cassandra:\n\n" +
               "Mac\n" +
               "You are ready to go!\n\n" +
               "Windows\n" +
               "You need to setup an environment variable\n" +
               "1) Press the windows key.\n" +
               "2) Type 'env' and select 'Edit the system environment variable'\n" +
               "3) Select enviroment variables\n" +
               "4) Select 'New...'\n" +
               "5) Type CASSANDRA_HOME in the Variable name\n" +
               "6) Type the path to your dekptop in  the value";
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Accept;
    private javax.swing.JButton jButton_Okey;
    private javax.swing.JCheckBox jCheckBox_AcceptTermsAndConditions;
    private javax.swing.JScrollPane jScrollPane_Background;
    private javax.swing.JTextPane jTextPane_Licence;
    // End of variables declaration//GEN-END:variables
}
