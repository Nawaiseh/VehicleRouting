/*
 * Screenshot.java
 *
 * Created on September 20, 2007, 10:53 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package UI;

/*
 * Screenshot.java (requires Java 1.4+)
 */
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ScreenShot {
    private UserInterface myDIRECTView = null;
    private DSystem DSystem = null;
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public ScreenShot(UserInterface directView) throws Exception {
        this.myDIRECTView = directView;
        DSystem = myDIRECTView.MainPassengersView.DSystem;

        // a waiting period and a file name
        counter++;
        // wait for a user-specified time
        try {

            time *= 1000L;
            DSystem.println("Waiting " + (time / 1000L) + " second(s)...");
            Thread.sleep(time);
        } catch (NumberFormatException nfe) {
            System.err.println(time + " does not seem to be a " + "valid number of seconds.");
        }
        // determine current screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        Rectangle screenRect = new Rectangle(screenSize);
        // create screen shot
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRect);
        // save captured image to PNG file
        ImageIO.write(image, "png", new File(outFileName));
        // give feedback
        DSystem.println("Saved screen shot (" + image.getWidth() + " x " + image.getHeight() + " pixels) to file \"" + outFileName + "\".");
        instructions.ScreenshotTaken(this.myDIRECTView, outFileName);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static Integer counter = 0;
    private String userDir = "";
    private String outFileName = userDir + "\\DIRECT_" + counter + ".png";
    private Instructions instructions = new Instructions();
    private static long time = 0;
    public ScreenShot() throws Exception {
    }
    public String getSceenShotName() {
        return (this.outFileName);
    }
    public void setSceenShotName(String inFileName) {
        this.outFileName = inFileName;
    }
}
