package smail.cli.gui;

import java.awt.FileDialog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import smail.cli.util.ArchiveUtil;
import smail.cli.util.FileUtil;


// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class FirstRunSetupFrame extends javax.swing.JFrame {
    
    private static final String __LICENCEURL__ = "LICENCE.md";
    
    public FirstRunSetupFrame() {
        setupLookAndFeel();
        initComponents();
        setupFrame();
        isFirstRun();
    }
    
    
    
    private void setupLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Mac OS X".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Accept = new javax.swing.JButton();
        jCheckBox_AcceptTermsAndConditions = new javax.swing.JCheckBox();
        jScrollPane_Background = new javax.swing.JScrollPane();
        jTextPane_Licence = new javax.swing.JTextPane();

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

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .add(jCheckBox_AcceptTermsAndConditions)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton_Accept)
                .add(18, 18, 18))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane_Background)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(jScrollPane_Background, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 315, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton_Accept)
                    .add(jCheckBox_AcceptTermsAndConditions))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AcceptActionPerformed
        if(jCheckBox_AcceptTermsAndConditions.isSelected()){
            
        } else {
            JOptionPane.showMessageDialog(this, "You haven't agreed to terms and conditions."
                    + "\nPlease Read and accept the terms and conditions!",
                "Please accept Terms & Conditions", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton_AcceptActionPerformed
    
    
    
    //
    //
    //
    private boolean isFirstRun() {
        if(FileUtil.getTextFromFile("log", true) == null) {
            
            ArchiveUtil.unzip();
            ArchiveUtil.setExecPermissions();
            
            FileUtil.writeToLog("[SUCCESS]\tExecuted first line procedure.");
            
            return true;
        } 
        
        return false;
    }
    
    
    
    // Sets up the frame when its initialized
    //
    //
    private void setupFrame() {
        FramePositionHandler.centerFrame(this);
        //jTextArea_Licence.setText(FileUtil.getTextFromFile(__LICENCEURL__));
        populateAndStyleLicence();
       
        
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

            return pathFindingFileDialog.getFiles()[0].getAbsolutePath();
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            Logger.getLogger(FirstRunSetupFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Accept;
    private javax.swing.JCheckBox jCheckBox_AcceptTermsAndConditions;
    private javax.swing.JScrollPane jScrollPane_Background;
    private javax.swing.JTextPane jTextPane_Licence;
    // End of variables declaration//GEN-END:variables
}
