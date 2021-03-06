
import com.github.lyuben.bridge.SchemaManager;
import com.github.lyuben.util.AuthenticationHandler;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */ 
public class CassTor {

    public static void main(String[] args) {
        try {
            //Build schema, run only once on seed node.
            //com.github.lyuben.bridge.SchemaManager.buildSchema(com.github.lyuben.bridge.Astyanax.getKeyspaceContext());System.exit(0);
            
            //run tests
            //new com.github.lyuben.test.TestSuite().runTests();System.exit(0);
            
            
            if(com.github.lyuben.util.FileUtil.isFirstRun()) {
                com.github.lyuben.modelview.FrameController.displayFirstRunFrames();
            } else {
                com.github.lyuben.modelview.FrameController.displayMainFrames();
            }

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CassTor.class.getName()).log(
                java.util.logging.Level.SEVERE, "Error in main thread.", ex);
        }
    }
}