package smail.cli.gui;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class StartupLoadFrame extends javax.swing.JFrame {
    
    public StartupLoadFrame() {
        initComponents();
        setupLookAndFeel();
        setupFrame();
    }

    // Initialises JPanel components 
    // Builds the main form
    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_ProgressBar = new javax.swing.JLabel();
        jScrollPane_Background = new javax.swing.JScrollPane();
        jTextPane_WarningMsg = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_ProgressBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smail/cli/img/progressbar.gif"))); // NOI18N

        jTextPane_WarningMsg.setEditable(false);
        jTextPane_WarningMsg.setToolTipText("Please read the licence, it's not that long or complex.");
        jScrollPane_Background.setViewportView(jTextPane_WarningMsg);
        jTextPane_WarningMsg.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jLabel_ProgressBar)
                .add(0, 0, Short.MAX_VALUE))
            .add(jScrollPane_Background)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(jScrollPane_Background, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel_ProgressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    // Multilevel try-catch to try to get native looking buttons / components
    // Makes the java application look nicer.
    //
    private void setupLookAndFeel(){
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
    
    
    
    // Sets up frame related configs
    // @void
    //
    private void setupFrame() {
        FramePositionHandler.centerFrame(this);
        this.setTitle("Loading SecTor");
        jTextPane_WarningMsg.setContentType("text/html");
        jTextPane_WarningMsg.setText(
            "<html>" +
            "<div style='padding:5px;font-size:16px;background-color:#F0F0F0'>Initialising TOR network...</div>" +
            "<div style='padding:5px;font-size:12px;'>Please be patient, this can take upto 10 minutes " + 
            "depending on your internet connection.</div>" +
            "</html>"
        );
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_ProgressBar;
    private javax.swing.JScrollPane jScrollPane_Background;
    private javax.swing.JTextPane jTextPane_WarningMsg;
    // End of variables declaration//GEN-END:variables
}
