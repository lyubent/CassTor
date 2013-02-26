package com.github.lyuben.gui;

import com.github.lyuben.bridge.Astyanax;
import com.github.lyuben.cql.EmailCql;
import com.github.lyuben.tor.Anonymizer;
import com.github.lyuben.util.EmailFormatter;
import com.netflix.astyanax.Keyspace;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class MailHomeFrame extends javax.swing.JFrame {
    
    private Keyspace keyspace;
    private String location;
    private String _USERNAME_;
    private LoginFrame parentFrame;
    
    public MailHomeFrame(String username, LoginFrame parentFrame, Keyspace keyspace) {
        
        //Singleton, only using one login frame.
        this.parentFrame = parentFrame;
        
        // Required for cql execution.
        this.keyspace = keyspace;
        this.location = "inbox";
        this._USERNAME_ = username;
        
        // initialize everything.
        initComponents();
        setupLookAndFeel();
        initInbox();
        setupFrame();
        hideUnused();
    }

    // Initialises JPanel components 
    // Builds the main form
    //
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_Options = new javax.swing.JPanel();
        jButton_Outbox = new javax.swing.JButton();
        jButton_Trash = new javax.swing.JButton();
        jButton_Exit = new javax.swing.JButton();
        jButton_Inbox = new javax.swing.JButton();
        jLayeredPane_Background = new javax.swing.JLayeredPane();
        jPanel_Inbox = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_InboxMail = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane_FullMessage = new javax.swing.JTextPane();
        jButton_Reply = new javax.swing.JButton();
        jButton_New = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jPanel_NewMessage = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_NewMessage_Body = new javax.swing.JTextArea();
        jTextField_NewMessage_To = new javax.swing.JTextField();
        jTextField_NewMessage_Title = new javax.swing.JTextField();
        jButton_Send = new javax.swing.JButton();
        jLabel_SendTo = new javax.swing.JLabel();
        jLabel_Title = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar_TopMenu = new javax.swing.JMenuBar();
        jMenu_Mail = new javax.swing.JMenu();
        jMenu_Folders = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel_Options.setBackground(new java.awt.Color(102, 102, 102));

        jButton_Outbox.setText("Sent");
        jButton_Outbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OutboxActionPerformed(evt);
            }
        });

        jButton_Trash.setText("Trash");
        jButton_Trash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TrashActionPerformed(evt);
            }
        });

        jButton_Exit.setText("Exit");
        jButton_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExitActionPerformed(evt);
            }
        });

        jButton_Inbox.setText("Inbox");
        jButton_Inbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_InboxActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel_OptionsLayout = new org.jdesktop.layout.GroupLayout(jPanel_Options);
        jPanel_Options.setLayout(jPanel_OptionsLayout);
        jPanel_OptionsLayout.setHorizontalGroup(
            jPanel_OptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel_OptionsLayout.createSequentialGroup()
                .add(16, 16, 16)
                .add(jPanel_OptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jButton_Exit, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton_Trash, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton_Outbox, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton_Inbox, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .add(17, 17, 17))
        );
        jPanel_OptionsLayout.setVerticalGroup(
            jPanel_OptionsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel_OptionsLayout.createSequentialGroup()
                .add(21, 21, 21)
                .add(jButton_Inbox)
                .add(18, 18, 18)
                .add(jButton_Outbox)
                .add(18, 18, 18)
                .add(jButton_Trash)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 237, Short.MAX_VALUE)
                .add(jButton_Exit)
                .add(50, 50, 50))
        );

        jLayeredPane_Background.setBackground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.activeTitleBackground"));

        jPanel_Inbox.setBackground(new java.awt.Color(204, 204, 204));

        jList_InboxMail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jList_InboxMail.setSelectionBackground(new java.awt.Color(102, 204, 255));
        jList_InboxMail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_InboxMailMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList_InboxMail);

        jTextPane_FullMessage.setEditable(false);
        jScrollPane4.setViewportView(jTextPane_FullMessage);

        jButton_Reply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/reply.png")));
        jButton_Reply.setText("Reply");
        jButton_Reply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ReplyActionPerformed(evt);
            }
        });

        jButton_New.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/new.png")));
        jButton_New.setText("New");
        jButton_New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NewActionPerformed(evt);
            }
        });

        jButton_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/delete.png")));
        jButton_Delete.setText("Delete");
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel_InboxLayout = new org.jdesktop.layout.GroupLayout(jPanel_Inbox);
        jPanel_Inbox.setLayout(jPanel_InboxLayout);
        jPanel_InboxLayout.setHorizontalGroup(
            jPanel_InboxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
            .add(jPanel_InboxLayout.createSequentialGroup()
                .addContainerGap()
                .add(jButton_New, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton_Reply)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jButton_Delete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(19, 19, 19))
            .add(jScrollPane4)
            .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        );
        jPanel_InboxLayout.setVerticalGroup(
            jPanel_InboxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel_InboxLayout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(4, 4, 4)
                .add(jPanel_InboxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel_InboxLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton_Reply)
                        .add(jButton_Delete))
                    .add(jButton_New, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel_Inbox.setBounds(4, 0, 446, 460);
        jLayeredPane_Background.add(jPanel_Inbox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel_NewMessage.setToolTipText("");

        jTextArea_NewMessage_Body.setColumns(20);
        jTextArea_NewMessage_Body.setRows(5);
        jScrollPane1.setViewportView(jTextArea_NewMessage_Body);

        jButton_Send.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/send.png")));
        jButton_Send.setText("Send");
        jButton_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SendActionPerformed(evt);
            }
        });

        jLabel_SendTo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/sendto_arrow.png")));

        jLabel_Title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/sendto.png")));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/message.png")));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/title_arrow.png")));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/lyuben/img/title.png")));

        org.jdesktop.layout.GroupLayout jPanel_NewMessageLayout = new org.jdesktop.layout.GroupLayout(jPanel_NewMessage);
        jPanel_NewMessage.setLayout(jPanel_NewMessageLayout);
        jPanel_NewMessageLayout.setHorizontalGroup(
            jPanel_NewMessageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel_NewMessageLayout.createSequentialGroup()
                .add(64, 64, 64)
                .add(jLabel_SendTo)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPanel_NewMessageLayout.createSequentialGroup()
                .add(jPanel_NewMessageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel_NewMessageLayout.createSequentialGroup()
                        .add(24, 24, 24)
                        .add(jPanel_NewMessageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel_NewMessageLayout.createSequentialGroup()
                                .add(jLabel_Title)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jTextField_NewMessage_To))
                            .add(jPanel_NewMessageLayout.createSequentialGroup()
                                .add(25, 25, 25)
                                .add(jLabel1)
                                .add(jPanel_NewMessageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel_NewMessageLayout.createSequentialGroup()
                                        .add(jLabel2)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jLabel3)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(jButton_Send, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 70, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(0, 43, Short.MAX_VALUE))
                                    .add(jPanel_NewMessageLayout.createSequentialGroup()
                                        .add(6, 6, 6)
                                        .add(jTextField_NewMessage_Title))))))
                    .add(jPanel_NewMessageLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_NewMessageLayout.setVerticalGroup(
            jPanel_NewMessageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel_NewMessageLayout.createSequentialGroup()
                .add(19, 19, 19)
                .add(jLabel_SendTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jPanel_NewMessageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jTextField_NewMessage_To, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel_Title))
                .add(jPanel_NewMessageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel_NewMessageLayout.createSequentialGroup()
                        .add(2, 2, 2)
                        .add(jTextField_NewMessage_Title, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(4, 4, 4)
                        .add(jPanel_NewMessageLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton_Send)))
                    .add(jPanel_NewMessageLayout.createSequentialGroup()
                        .add(9, 9, 9)
                        .add(jLabel1)))
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 277, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel_NewMessage.setBounds(460, 0, 440, 450);
        jLayeredPane_Background.add(jPanel_NewMessage, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jPanel_NewMessage.setBounds(0, 10, 440, 450);

        jMenu_Mail.setText("Mail");
        jMenuBar_TopMenu.add(jMenu_Mail);

        jMenu_Folders.setText("Folders");
        jMenuBar_TopMenu.add(jMenu_Folders);

        setJMenuBar(jMenuBar_TopMenu);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel_Options, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLayeredPane_Background, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 453, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel_Options, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(jLayeredPane_Background)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    // Sets up the original inbox 
    //
    //
    private void initInbox(){
        // Note* catch exception here means that the logging in process correctly
        populateInbox(Astyanax.processResults(Astyanax.execCQL(
        this.keyspace, EmailCql.nondeletedUserEmail(_USERNAME_)))); 
    }
    
    private void jButton_InboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_InboxActionPerformed
        location = "inbox";
        jButton_Delete.setEnabled(true);
        jPanel_NewMessage.setVisible(false);
        jPanel_Inbox.setVisible(true);
        
        populateInbox(Astyanax.processResults(Astyanax.execCQL(
                        this.keyspace, EmailCql.nondeletedUserEmail(_USERNAME_))));
    }//GEN-LAST:event_jButton_InboxActionPerformed

    private void jList_InboxMailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_InboxMailMouseClicked
        try {
            // Get the parts of the message we need
            String [] emailContent = new String []{ //title, from(sender), at (datE), body
                EmailFormatter.applyRegexFilter(getjListSelectedIndexString(), "SUBJECT"), 
                EmailFormatter.applyRegexFilter(getjListSelectedIndexString(), "SENDER"), 
                EmailFormatter.applyRegexFilter(getjListSelectedIndexString(), "DATE"), 
                EmailFormatter.applyRegexFilter(getjListSelectedIndexString(), "BODY")};
            
            if(jList_InboxMail.getModel().getSize() > 0 ){
                jTextPane_FullMessage.setContentType("text/html");
                
                jTextPane_FullMessage.setText(EmailFormatter.structureDetailedMessage(emailContent));
            }
        } catch (Exception ex) {
            Logger.getLogger(MailHomeFrame.class.getName()).log(Level.SEVERE, 
                    "Error redisplaying and updating inbox", ex);
            JOptionPane.showMessageDialog(this, "A problem occured!"
                    , "Error updating the inbox.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jList_InboxMailMouseClicked

    private void jButton_NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NewActionPerformed

        jPanel_Inbox.setVisible(false);
        jPanel_NewMessage.setVisible(true);
        
        jTextField_NewMessage_To.setText("");
        jTextField_NewMessage_Title.setText("");
        jTextArea_NewMessage_Body.setText("");
    }//GEN-LAST:event_jButton_NewActionPerformed

    private void jButton_ReplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ReplyActionPerformed
        
        if(isEmailSelected("replying to")) { 
            //switch panel visibility
            jPanel_Inbox.setVisible(false);
            jPanel_NewMessage.setVisible(true);
        
            //populate to and title for the reply
            jTextField_NewMessage_To.setText(EmailFormatter.
                    applyRegexFilter(getjListSelectedIndexString(), "SENDER"));
            jTextField_NewMessage_Title.setText("RE: " + EmailFormatter.
                    applyRegexFilter(getjListSelectedIndexString(), "SUBJECT"));
            jTextArea_NewMessage_Body.setText("");
        }
    }//GEN-LAST:event_jButton_ReplyActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        if(isEmailSelected("deleting")){
            String key = EmailFormatter.applyRegexFilter(getjListSelectedIndexString(), "");
            
            // Set email as deleted
            Astyanax.execCQL(keyspace, EmailCql.deleteEmail(key, location));
            
            // Remove item from the list
            DefaultListModel updatedModel = (DefaultListModel) jList_InboxMail.getModel();
            updatedModel.removeElementAt(jList_InboxMail.getSelectedIndex());
            jList_InboxMail.setModel(updatedModel);
            jList_InboxMail.setSelectedIndex(-1);
            jTextPane_FullMessage.setText(new String());
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SendActionPerformed
        
        if(jPanel_NewMessage.isVisible()){
            jButton_Delete.setEnabled(true);
            // Insert the email into the db
            Astyanax.insertEmail(keyspace, _USERNAME_, Arrays.asList(
                    jTextField_NewMessage_To.getText(),     //receiver 
                    jTextField_NewMessage_Title.getText(),  //subject
                    jTextArea_NewMessage_Body.getText())); //body
        } else {
            JOptionPane.showMessageDialog(this, "A problem occured!"
                    , "Error sending email", JOptionPane.ERROR_MESSAGE);
        }
        jPanel_NewMessage.setVisible(false);
        jPanel_Inbox.setVisible(true);
        location = "inbox";
    }//GEN-LAST:event_jButton_SendActionPerformed

    private void jButton_TrashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TrashActionPerformed
        location = "trash";
        // Join all the emails I own that I've deleted.
        List<String> fullDeletedEmailList = new ArrayList<String>();
        //list for emails deleted by the sender where I am the sender
        fullDeletedEmailList.addAll(Astyanax.processResults(Astyanax.execCQL(
                        this.keyspace, EmailCql.deletedSenderEmail(_USERNAME_))));
        //list for emails deleted by the receiver where I am the receiver
        fullDeletedEmailList.addAll(Astyanax.processResults(Astyanax.execCQL(
                        this.keyspace, EmailCql.deletedUserEmail(_USERNAME_))));
        populateInbox(fullDeletedEmailList);
        
        jButton_Delete.setEnabled(false);
    }//GEN-LAST:event_jButton_TrashActionPerformed

    private void jButton_OutboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OutboxActionPerformed
        location = "sent";
        jButton_Delete.setEnabled(true);
        populateInbox(Astyanax.processResults(Astyanax.execCQL(
                        this.keyspace, EmailCql.allSentUserEmail(_USERNAME_))));
    }//GEN-LAST:event_jButton_OutboxActionPerformed

    private void jButton_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExitActionPerformed
        destroyFrame();
    }//GEN-LAST:event_jButton_ExitActionPerformed

    
    
    // Checks if an index from the jListInbox has been selected before replying
    // and displays an error if not.
    // @return boolean representing the selected item.
    private boolean isEmailSelected(String actionType){
        
        if(getjListSelectedIndexString().equals("")){
            JOptionPane.showMessageDialog(this, "You need to select an email before " + actionType + " it!"
                    , "Email not selected", JOptionPane.ERROR_MESSAGE);   
            return false;
        }
        return true;
    }
    
    
    
    // Populates the jList with a user's emails
    // @param Requires a list of HTML representing the columns
    //
    private void populateInbox(List<String> dataList){
        DefaultListModel messageList = new DefaultListModel();
        messageList.ensureCapacity(50);
        
        for(String message : dataList){
            messageList.addElement(message);
        }
        jList_InboxMail.setToolTipText(null);
        jList_InboxMail.setModel(messageList);
    }
    
    
    
    // Multilevel try-catch to try to get native looking buttons / components
    // Makes the java application look nicer.
    //
    private void setupLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                // We want Mac first if we are on OSX or Microsoft first if on windows.
                // Avoids using Nimbus on OSX or Metal on Win7/8.
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
            JOptionPane.showMessageDialog(this, "A problem occured!"
                    , "Error seting up GUI.", JOptionPane.ERROR_MESSAGE);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    "Failed to instantiate the look and feel classes.", ex);
            JOptionPane.showMessageDialog(this, "A problem occured!"
                    , "Error seting up GUI.", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    "Don't have permissions to access look and feel classes.", ex);
            JOptionPane.showMessageDialog(this, "A problem occured!"
                    , "Error seting up GUI.", JOptionPane.ERROR_MESSAGE);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstRunSetupFrame.class.getName()).log(java.util.logging.Level.SEVERE,
                    "Look and feel look is not supported.", ex);
            JOptionPane.showMessageDialog(this, "A problem occured!"
                    , "Error seting up GUI.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    // Gets the String containied in the selected item from jListInboxMail
    // @return String containing data in the list item.
    //
    private String getjListSelectedIndexString() {
        if(jList_InboxMail.getSelectedIndex() != -1){
            return jList_InboxMail.getModel().getElementAt(
                    jList_InboxMail.getSelectedIndex()).toString();
        }
        return "";
    }
    
    
    
    // Hides components of the application that are not used to speed up app.
    // @void
    //
    private void hideUnused() {
        Border emptyBorder = BorderFactory.createEmptyBorder();
        jScrollPane1.setBorder(emptyBorder);
        jScrollPane2.setBorder(emptyBorder);
        jScrollPane4.setBorder(emptyBorder);
        jPanel_NewMessage.setVisible(false);
    }
    
    
    
    // Sets up frame related configs
    // @void
    //
    private void setupFrame(){
        
        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Cassandra SeceMail");
        this.setLocation(parentFrame.getLocation());
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                destroyFrame();
            }
        });
    }
    
    
    
    // Closes the frame and redisplays login frame.
    //
    //
    private void destroyFrame(){
        
        this.setVisible(false);
        parentFrame.setLocation(this.getLocation());
        parentFrame.setVisible(true);
        this.dispose();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Exit;
    private javax.swing.JButton jButton_Inbox;
    private javax.swing.JButton jButton_New;
    private javax.swing.JButton jButton_Outbox;
    private javax.swing.JButton jButton_Reply;
    private javax.swing.JButton jButton_Send;
    private javax.swing.JButton jButton_Trash;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_SendTo;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLayeredPane jLayeredPane_Background;
    private javax.swing.JList jList_InboxMail;
    private javax.swing.JMenuBar jMenuBar_TopMenu;
    private javax.swing.JMenu jMenu_Folders;
    private javax.swing.JMenu jMenu_Mail;
    private javax.swing.JPanel jPanel_Inbox;
    private javax.swing.JPanel jPanel_NewMessage;
    private javax.swing.JPanel jPanel_Options;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea_NewMessage_Body;
    private javax.swing.JTextField jTextField_NewMessage_Title;
    private javax.swing.JTextField jTextField_NewMessage_To;
    private javax.swing.JTextPane jTextPane_FullMessage;
    // End of variables declaration//GEN-END:variables

    
}
