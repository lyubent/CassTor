package smail.cli.gui;

import javax.swing.JFrame;

// @author lyubentodorov
// @licence - MIT
// Available at http://lyuben.herokuapp.com/casstor/ 
// Source at https://github.com/lyubent/CassTor/ 
//
public class FramePositionHandler {
    
    public static void centerFrame(JFrame frameToCenter){
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
 
        // Determine the new location of the window
        int w = frameToCenter.getSize().width;
        int h = frameToCenter.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;

        // Move the window
        frameToCenter.setLocation(x, y);
    }
}
