package com.github.lyuben.gui;

import com.github.lyuben.bridge.Astyanax;
import com.github.lyuben.tor.Anonymizer;
import com.github.lyuben.util.AuthenticationHandler;
import com.github.lyuben.util.BCrypto;
import com.github.lyuben.util.FramePositionHandler;
import com.netflix.astyanax.Keyspace;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class LoginFrame extends javax.swing.JFrame {
    
    private Keyspace keyspace;
    
    public LoginFrame() {
        
        keyspace = Astyanax.getKeyspaceContext();
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

        jLayeredPane2 = new javax.swing.JLayeredPane();
        jTextField_UName = new javax.swing.JTextField();
        jPasswordField_Pw = new javax.swing.JPasswordField();
        jButton_Login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField_UName.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_UName.setText("Username");
        jTextField_UName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_UNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_UNameFocusLost(evt);
            }
        });
        jTextField_UName.setBounds(130, 180, 230, 28);
        jLayeredPane2.add(jTextField_UName, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPasswordField_Pw.setForeground(new java.awt.Color(204, 204, 204));
        jPasswordField_Pw.setText("password");
        jPasswordField_Pw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField_PwFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField_PwFocusLost(evt);
            }
        });
        jPasswordField_Pw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField_PwKeyPressed(evt);
            }
        });
        jPasswordField_Pw.setBounds(130, 210, 230, 28);
        jLayeredPane2.add(jPasswordField_Pw, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton_Login.setText("Sign in");
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });
        jButton_Login.setBounds(270, 240, 87, 29);
        jLayeredPane2.add(jButton_Login, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setBackground(new java.awt.Color(238, 238, 237));
        jLabel1.setForeground(new java.awt.Color(238, 238, 238));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/login.png"))); // NOI18N
        jLabel1.setText("icon");
        jLabel1.setToolTipText("");
        jLabel1.setBounds(0, 0, 480, 390);
        jLayeredPane2.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 391, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * If enter pressed or login clicked begin login process.
     * @param evt 
     */
    private void jPasswordField_PwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField_PwKeyPressed
        if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_jPasswordField_PwKeyPressed

    
    /**
     * On button click begin the login process.
     * @param evt 
     */
    private void jButton_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoginActionPerformed
        login();
    }//GEN-LAST:event_jButton_LoginActionPerformed

    
    
    private void jTextField_UNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_UNameFocusGained
        if(jTextField_UName.getText().trim().equals("Username")){
            jTextField_UName.setText("");
            jTextField_UName.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField_UNameFocusGained

    
    
    private void jTextField_UNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_UNameFocusLost
        if(jTextField_UName.getText().trim().equals("")){
            jTextField_UName.setText("Username");
            jTextField_UName.setForeground(Color.LIGHT_GRAY);
        }
    }//GEN-LAST:event_jTextField_UNameFocusLost

    private void jPasswordField_PwFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField_PwFocusGained
        this.jPasswordField_Pw.setText("");
    }//GEN-LAST:event_jPasswordField_PwFocusGained

    private void jPasswordField_PwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField_PwFocusLost
        if(new String(this.jPasswordField_Pw.getPassword()).equals("")) {
            this.jPasswordField_Pw.setText("lolzcake");
        }
    }//GEN-LAST:event_jPasswordField_PwFocusLost
    
    
    
    /**
     * Sets up frame related configs
     */
    private void setupFrame() {
        FramePositionHandler.centerFrame(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login ~ Secure Cassandra Mail");
    }
    
    
    /**
     * Carries out the login process hiding and displaying necessary forms
     * @return boolean representing sucess of updating viewport
     */
    private boolean login() {
        
        try {
            if(authenticate(jTextField_UName.getText(), new String(jPasswordField_Pw.getPassword()))) {
                System.out.println("AUTHED!!!!");
                // Create and display the new form each time a user logs in.
                // Cleaner to create new form than tidying and re-initializing old one.
                this.setVisible(false);
                MailHomeFrame mail = new MailHomeFrame(jTextField_UName.getText(), this, keyspace);
                return true;
            }    
        } catch (Exception ex) {
            handleLogginFailiure();
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, 
                    "ERROR: Handling login failiure.", ex);
        }
        
        return false;
    }
    
    
    
    /**
     * Carries out authentication process
     * @param username
     * @param password
     * @return boolean representing authentication success.
     */
    private boolean authenticate(String username, String password) {
        if(jTextField_UName.getText().trim().equals("Username")) {
            JOptionPane.showMessageDialog(null, 
                "Please supply a username and password!",
                "Register", JOptionPane.WARNING_MESSAGE);
        }
        // check if registered 
        else if(AuthenticationHandler.isAvailable(username)) {
            
            // register new user
            int dialogButton = JOptionPane.showConfirmDialog(null, "This username is avaiable.\nDo you wish to register\n"
                    + "with the supplied password?",
                "Register", JOptionPane.YES_NO_OPTION);
            
            if(dialogButton == JOptionPane.YES_OPTION) {
                //register
                AuthenticationHandler.register(username, password);
                //login
                return true;
            } else {
                // user chose not to register
            }
        } else {
            String hashedPw = AuthenticationHandler.getHashedPw(username);
            
            if(BCrypto.checkPassword(password.toString(), hashedPw)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect username / password.\n"
                        + "Contact the Administartor if\n"
                        + "you've forgotton your password",
                "Correct Password!", JOptionPane.OK_OPTION);
            }
        }
        
        jTextField_UName.setText(String.valueOf(username));
        
        return false;
    }
    
    
    
    /**
     * Called when an exception is caught while logging in
     * Displays a popup dialogue informing the user of the failure.
     */
    private void handleLogginFailiure() {
        this.setVisible(true);
        JOptionPane.showMessageDialog(this, "A problem occred while trying to login",
                "Error logging in.", JOptionPane.ERROR_MESSAGE);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        Anonymizer anon = new Anonymizer();
        try {
            anon.cleanNetLayer();
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /**
     * Hides components of the application that are not used to speed up app.
     */
    private void hideUnused(){
    }
    
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JPasswordField jPasswordField_Pw;
    private javax.swing.JTextField jTextField_UName;
    // End of variables declaration//GEN-END:variables
}
