
import com.github.lyuben.gui.FrameHandler;
import com.github.lyuben.util.FileUtil;
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
            //System.exit(0);
            
            //run tests
            //new com.github.lyuben.test.TestSuite().runTests();
            //System.exit(0);
            
            
//            if(FileUtil.isFirstRun()) {
//                System.out.println("BACON");
//                FrameHandler.displayFirstRunFrames();
//            } else {
                System.out.println("BACONd");
                FrameHandler.displayMainFrames();
//            }

        } catch (Exception ex) {
            Logger.getLogger(CassTor.class.getName()).log(Level.SEVERE,
                    "Error in main thread.", ex);
        }
    }
}