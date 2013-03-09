package com.github.lyuben.gui;

import com.github.lyuben.bridge.Astyanax;
import com.github.lyuben.bridge.JDBC;
import com.github.lyuben.util.ArchiveUtil;
import com.github.lyuben.util.FileUtil;
import com.github.lyuben.util.FramePositionHandler;
import java.awt.FileDialog;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class FirstRunSetupFrame extends javax.swing.JFrame {
    
    private static final String __LICENCEURL__ =
        FileUtil.getAppName() + "LICENCE.md";
    
    public FirstRunSetupFrame() {
        setupLookAndFeel();
        initComponents();
        hideUnused();
        setupFrame();

        JOptionPane.showMessageDialog(this, 
                
            System.getProperty("user.dir") + 
            "\nLicence?: " + new File(__LICENCEURL__).isFile() +
            "\nLog?: " + new File("log").isFile() +
            "\nApp Licence?: " + new File(System.getProperty("user.dir") + "Contents/Resources/Java/" + __LICENCEURL__).isFile() +
            "\nApp Log?: " + new File(System.getProperty("user.dir") + "Contents/Resources/Java/log").isFile() +
            "\nAppDir Licence?: " + new File(System.getProperty("user.dir") + FileUtil.getAppName() + "/Contents/Resources/Java/" + __LICENCEURL__).isFile() +
            "\n" + System.getProperty("user.dir") + FileUtil.getAppName() + "/LICENCE.md\n" +
            __LICENCEURL__ +
            "\nAppDir Log?: " + new File(System.getProperty("user.dir") + FileUtil.getAppName() + "/Contents/Resources/Java/log").isFile()
            ,



            System.getProperty("user.dir"),
            JOptionPane.WARNING_MESSAGE);
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

    
    // User has accepted the terms and conditions
    // We can now display Cassandra setup instructions and extract Cassandra.
    //
    private void jButton_AcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcceptActionPerformed
        if(jCheckBox_AcceptTermsAndConditions.isSelected()){
            
            //Display loading message.
            jTextPane_Licence.setText("Loading...");
            jCheckBox_AcceptTermsAndConditions.setVisible(false);
            jButton_Accept.setVisible(false);
            
            
            //first try to extract cassandra to Desktop, then update the UI.
            extractCassandra();
            JOptionPane.showMessageDialog(this, "Extracted cassandra", "extra casandra", JOptionPane.DEFAULT_OPTION);
            JDBC.incrementReplicationFactor(Astyanax.getKSName());
            JOptionPane.showMessageDialog(this, "++rep factor", "increased rep", JOptionPane.DEFAULT_OPTION);
            
            jButton_Okey.setVisible(true);
            JOptionPane.showMessageDialog(this, "setbutnvisble", "setbtnvis", JOptionPane.DEFAULT_OPTION);
            jTextPane_Licence.setText(getStartupInstructions());
            JOptionPane.showMessageDialog(this, "get startup instructions", "get inst", JOptionPane.DEFAULT_OPTION);
        } else {
            JOptionPane.showMessageDialog(this, "You haven't agreed to terms and conditions."
                    + "\nPlease Read and accept the terms and conditions!",
                "Please accept Terms & Conditions", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton_AcceptActionPerformed

    
    
    // Display cassandra startup instructions and launch login interface
    // Destroys this frame and displays the parent Login frame.
    //
    private void jButton_OkeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OkeyActionPerformed
        JOptionPane.showMessageDialog(this, "The application is now ready to run!\n" +
            "Please restart the application.\nThe secure email client will begin\n loading.",
            "Ready to start!", JOptionPane.WARNING_MESSAGE);
        
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton_OkeyActionPerformed
    
    
    
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
    
    
    
    // Sets up the frame when its initialized
    //
    //
    private void setupFrame() {
        FramePositionHandler.centerFrame(this);
        populateAndStyleLicence();
        this.setVisible(true);
    }
    
    
    
    // Hides components of the application that are not used to speed up app.
    // @void
    //
    private void hideUnused(){
        jButton_Okey.setVisible(false);
    }
    
    
    
    // Configures and sets up layout for the Licence textarea
    //
    //
    private void populateAndStyleLicence(){
        SimpleAttributeSet sa = new SimpleAttributeSet();
        StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);
        
        String licence = FileUtil.getTextFromFile(__LICENCEURL__);
        
        jTextPane_Licence.getStyledDocument().setParagraphAttributes(0,licence.length(),sa,false);
        jTextPane_Licence.setMargin(new java.awt.Insets(2,2,2,2));
        jTextPane_Licence.setText(licence);
    }
    
    
    
    // Displayes a dialogue for user to browse to cassandra.yaml and select it
    // inorder to find the path to the file
    // Replaced with automatic extraction to the desktop.
    @Deprecated
    private String findCassandraConfigPath() {
        try {
            FileDialog pathFindingFileDialog = new  FileDialog(this);
            
            // Set the FileChooser dialogue title and display the dialogue.
            pathFindingFileDialog.setTitle("Find cassandra.YAML");
            pathFindingFileDialog.setVisible(true);

            return ""; //pathFindingFileDialog.getFiles()[0].getAbsolutePath();
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(FirstRunSetupFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    
    
    // Returns the startup instructions
    // Tradeoff, bad coding VS opening uncessessary buffers.
    //
    private String getStartupInstructions() {
        return "Extracting and configuring Cassandra has completed successfully.\n\n" +
               "Starting Cassandra:\n\n" +
               "Mac\n" +
               "1) Open a terminal\n" +
               "2) Navigate to the cassandra on the desktop\n" +
               "$ cd ~/Desktop/cassandra\n" +
               "3) Start the server calling the cassandra executable\n" +
               "$ sudo ./bin/cassandra\n\n" +
               "Windows\n" +
               "1) Open a command prompt\n" +
               "2) Navigate to cassandra on the desktop\n" +
               "> cd desktop/cassandra\n" +
               "3) Call the cassandra.bat startup script\n" +
               "> cassandra.bat";
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Accept;
    private javax.swing.JButton jButton_Okey;
    private javax.swing.JCheckBox jCheckBox_AcceptTermsAndConditions;
    private javax.swing.JScrollPane jScrollPane_Background;
    private javax.swing.JTextPane jTextPane_Licence;
    // End of variables declaration//GEN-END:variables
}
