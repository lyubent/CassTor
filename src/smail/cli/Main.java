package smail.cli;

import java.util.logging.Level;
import java.util.logging.Logger;
import smail.cli.gui.LoginFrame;
import smail.cli.netlib.TorLib;
import smail.cli.netlib.TorSocket;

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
                    
                    /*************************
                     ***********TESTS*********
                     *************************/
                    //new smail.cli.netlib.Anonymizer().tryConn();
                    //new smail.cli.netlib.Anonymizer().tryPureConn();
                    new smail.cli.netlib.Anonymizer().buildTORSocket();
                    
                    //String [] jj = new String [10];
                    //TorLib.main(jj);
                    
                    //TorSocket.main(new String(""));
                    //TorSocket.main(new String(""), true);
                    
                    /*************************
                     *********TESTS - END*****
                     *************************/
                    
                    
                    
                    //Display login frame
                    //LoginFrame loginFrame = new LoginFrame();
                    
                    //run tests
                    //new smail.cli.test.TestSuite().runTests();

                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    };
}