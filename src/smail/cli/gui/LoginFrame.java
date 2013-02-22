package smail.cli.gui;

import com.netflix.astyanax.Keyspace;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import smail.cli.astyanax.Astyanax;
import smail.cli.tor.Anonymizer;
import smail.cli.util.FileUtil;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class LoginFrame extends javax.swing.JFrame {
    
    private Keyspace keyspace;
    
    public LoginFrame() {
        keyspace = Astyanax.getKeyspaceContext();
        setupLookAndFeel();
        initComponents();
        hideUnused();
        setupFrame();
        
        //Check if program is running for the 1st time.
        isFirstRun();
    }
    
    
    
    // Checks if program is runnig for the first time
    // Runs cassandra unzip routine if it is the first run.
    //
    private boolean isFirstRun() {
        if(FileUtil.getTextFromFile("log", true) == null) {
            // First run - displaying licence.
            new FirstRunSetupFrame(this).setVisible(true);
            
            return true;
        } 
        
        this.setVisible(true);
        return false;
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
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
        jTextField_UName.setBounds(125, 170, 230, 28);
        jLayeredPane1.add(jTextField_UName, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPasswordField_Pw.setForeground(new java.awt.Color(204, 204, 204));
        jPasswordField_Pw.setText("password");
        jPasswordField_Pw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField_PwKeyPressed(evt);
            }
        });
        jPasswordField_Pw.setBounds(125, 200, 230, 28);
        jLayeredPane1.add(jPasswordField_Pw, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton_Login.setText("Sign In");
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });
        jButton_Login.setBounds(270, 240, 87, 29);
        jLayeredPane1.add(jButton_Login, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smail/cli/img/login.png"))); // NOI18N
        jLabel1.setBounds(0, 0, 482, 396);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 487, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 411, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jPasswordField_PwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField_PwKeyPressed
        
        if(evt.getKeyChar() == KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_jPasswordField_PwKeyPressed

    
    
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
    
    
    
    // Sets up frame related configs
    // @void
    //
    private void setupFrame() {
        FramePositionHandler.centerFrame(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login ~ Secure Cassandra Mail");
    }
    
    
    // Carries out the login process hiding and displaying necessary forms
    // @return boolean representing sucess of updating viewport
    //
    private boolean login() {
        
        try {
            if(authenticate(jTextField_UName.getText(), jPasswordField_Pw.getPassword())) {
                
                // Create and display the new form each time a user logs in.
                // Cleaner to create new form than tidying and re-initializing old one.
                this.setVisible(false);
                MailHomeFrame mail = new MailHomeFrame(jTextField_UName.getText(), this, keyspace);
                return true;
            }    
        } catch (Exception ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            handleLogginFailiure();
        }
        
        return false;
    }
    
    
    
    // Carries out authentication process
    // @return boolean representing 
    //
    private boolean authenticate(String username, char [] password) {
        
        jTextField_UName.setText(String.valueOf(username));
        return true;
    }
    
    
    
    // Called when an exception is caught while logging in
    // @void - Displays a popup dialogue informing the user of the failiure.
    //
    private void handleLogginFailiure() {
        this.setVisible(true);
        JOptionPane.showMessageDialog(this, "A problem occred while trying to login",
                "Error logging in.", JOptionPane.ERROR_MESSAGE);
        new Anonymizer().fallbackToTPC();
    }
    
    
    
    // Hides components of the application that are not used to speed up app.
    // @void
    //
    private void hideUnused(){
     
    }
    
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPasswordField jPasswordField_Pw;
    private javax.swing.JTextField jTextField_UName;
    // End of variables declaration//GEN-END:variables
}
