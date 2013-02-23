package smail.cli;

import smail.cli.gui.LoginFrame;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class Main {
    
    public static void main(String[] args) {
        
        //Build schema, run only once on seed node.
        //smail.cli.astyanax.Schema.buildSchema(smail.cli.astyanax.Astyanax.getKeyspaceContext());
        
        //run tests
        new smail.cli.test.TestSuite().runTests();
        
        // TOR initialization threading.
        new Thread () {
            @Override
            public void run () {
//                StartupLoadFrame loader = new StartupLoadFrame();
//                loader.setVisible(true);
//
//                Anonymizer anon = new Anonymizer();
//                anon.runTor();
//
//                // Once TOR Layer connection is completed, hide the loader frame.
//                loader.dispose();
//                
//                // SWING threading.
//                javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run(){
//                        try {
//                            //Prepare the login frame
                            LoginFrame loginFrame = new LoginFrame();
//                        } catch (Exception ex) {
//                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, 
//                                    "Error with SWING Components", ex);
//                        }
//                    }
//                });

            }
        }.start();
    }
}