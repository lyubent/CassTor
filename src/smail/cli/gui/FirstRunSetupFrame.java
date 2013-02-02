package smail.cli.gui;

import java.awt.FileDialog;
import java.util.logging.Level;
import java.util.logging.Logger;


// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class FirstRunSetupFrame extends javax.swing.JFrame {
    
    public FirstRunSetupFrame() {
        setupLookAndFeel();
        initComponents();
        
        FramePositionHandler.centerFrame(this);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 389, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    
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
    // End of variables declaration//GEN-END:variables
}
