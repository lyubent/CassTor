package smail.cli;

import java.util.logging.Level;
import java.util.logging.Logger;
import smail.cli.gui.FirstRunSetupFrame;
import smail.cli.gui.LoginFrame;
import smail.cli.util.ArchiveUtil;
import smail.cli.util.FileUtil;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class Main {    
    
    public static void main(String[] args) {
        
        //Build schema, run only once on seed node.
        //smail.cli.astyanax.Schema.buildSchema(smail.cli.astyanax.Astyanax.getKeyspaceContext());
        
        // SWING threading.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run(){
                try {
                    
                    //Display login frame
                    //LoginFrame loginFrame = new LoginFrame();
                    
                    //For the first run
                    new FirstRunSetupFrame().setVisible(true);
                    

                    
                    //Extract cassandra to the desktop
                    //ArchiveUtil.unzip();
                    //ArchiveUtil.setExecPermissions();
                    
                    //Setup the YAML file to store the local ip
                    //FileUtil.configureCassandraYAML();
                    
                    
                    
                    //run tests
                    //new smail.cli.test.TestSuite().runTests();

                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}