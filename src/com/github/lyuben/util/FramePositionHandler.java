package com.github.lyuben.util;

import javax.swing.JFrame;

/**
 * @author lyubentodorov
 * @licence - MIT
 * Available at http://lyuben.herokuapp.com/casstor/ 
 * Source at https://github.com/lyubent/CassTor/ 
 */
public class FramePositionHandler {
    
    /**
     * Used for moving a new GuI frame to th center of the screen.
     * @param frameToCenter 
     */
    public static void centerFrame(JFrame frameToCenter){
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
 
        // Determine the new location of the window
        int w = frameToCenter.getSize().width;
        int h = frameToCenter.getSize().height;
        int x = (dim.width-w)/2; // centered on screen
        int y = ((dim.height-h)/10) * 3; // and 30% top border

        // Move the window
        frameToCenter.setLocation(x, y);
    }
}
