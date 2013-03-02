
import com.github.lyuben.gui.LoginFrame;
import com.github.lyuben.gui.StartupLoadFrame;
import com.github.lyuben.tor.Anonymizer;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class CassTor {

    public static void main(String[] args) {

        try {
            //Build schema, run only once on seed node.
            //com.github.lyuben.bridge.Schema.buildSchema(com.github.lyuben.bridge.Astyanax.getKeyspaceContext());

            //run tests
            //new com.github.lyuben.test.TestSuite().runTests();

            // TOR initialization threading.
            new Thread() {
                @Override
                public void run() {
                    // Display a loding frame white TOR initialises
                    StartupLoadFrame loader = new StartupLoadFrame();
                    loader.setVisible(true);

                    // Run the TOR initialisation procedure
                    Anonymizer anon = new Anonymizer();
                    anon.runTor();

                    try {
                        // InetAddress test prvents deadlock in Astyanax threading.
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    InetAddress.getByName("dundee.ac.uk").isReachable(5000);
                                } catch (java.net.UnknownHostException ex) {
                                    Logger.getLogger(CassTor.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (java.io.IOException ex) {
                                    Logger.getLogger(CassTor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            };
                        } .start(); // InetAddressThread
                    } catch (Exception ex) {
                        Logger.getLogger(CassTor.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    // Once TOR Layer connection is completed, hide the loader frame.
                    loader.dispose();

                    // SWING GUI threading.
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //Prepare the login frame
                                LoginFrame loginFrame = new LoginFrame();
                            } catch (Exception ex) {
                                Logger.getLogger(CassTor.class.getName()).log(Level.SEVERE,
                                        "Error with SWING Components", ex);
                            }
                        }
                    }); // SWING thread
                }
            }.start(); //TOR thread


        } catch (Exception ex) {
            Logger.getLogger(CassTor.class.getName()).log(Level.SEVERE,
                    "Error in main thread.", ex);
        }
    }
}