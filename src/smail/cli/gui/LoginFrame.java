package smail.cli.gui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class LoginFrame extends javax.swing.JFrame {

    private MailHomeFrame mailForm;
    
    public LoginFrame() {
        initComponents();
        hideUnused();
        setupFrame();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jTextField_UName = new javax.swing.JTextField();
        jPasswordField_Pw = new javax.swing.JPasswordField();
        jProgressBar_Login = new javax.swing.JProgressBar();
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
        jProgressBar_Login.setBounds(90, 230, 310, 10);
        jLayeredPane1.add(jProgressBar_Login, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
    private void setupFrame(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login ~ Secure Cassandra Mail");
    }
    
    
    // Carries out the login process hiding and displaying necessary forms
    // @return boolean representing sucess of updating viewport
    //
    private boolean login(){
        
        try {
            if(authenticate(jTextField_UName.getText(), jPasswordField_Pw.getPassword())) {
                
                jProgressBar_Login.setVisible(true);
                animateProgressBar(0);
                
                // create and display the new form.
                //mailForm = 
                new MailHomeFrame(jTextField_UName.getText(), this);
                new MailHomeFrame(jTextField_UName.getText(), this);
                this.setVisible(false);
                
                return true;
            }    
        } catch (Exception ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            this.setVisible(true);
            JOptionPane.showMessageDialog(this, "A problem occred while trying to login"
                    , "Error logging in.", JOptionPane.ERROR_MESSAGE);
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
    
    
    
    // Hides components of the application that are not used to speed up app.
    // @void
    //
    private void hideUnused(){
        jProgressBar_Login.setVisible(false);
    }
    
    
    
    // Updates progress bar representing login progression
    // Currently doesnt really do anything - TODO
    //
    private void animateProgressBar(int progress){
        try {
            if(progress < 100){

                progress += 1;
                jProgressBar_Login.setValue(progress);

                animateProgressBar(progress);
            }
        } catch(Exception ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPasswordField jPasswordField_Pw;
    private javax.swing.JProgressBar jProgressBar_Login;
    private javax.swing.JTextField jTextField_UName;
    // End of variables declaration//GEN-END:variables
}
