package UI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Instructions {

    public final String icon_info = "question_icon.png";
    public final String icon_fullscreen = "square_arrows.png";
    public final String userDir = System.getProperty("user.dir") + "\\pics\\";
    private ImageIcon errorIcon = new ImageIcon(getClass().getResource("/Resources/errorIcon32.png"));
    private ImageIcon infoIcon = new ImageIcon(getClass().getResource("/Resources/infoIcon32.png"));
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void welcome(UserInterface directView) {

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        String msg = "Welcome to " + UserInterface.APP_NAME;
        msg += "! To get started, choose File -> Load\n";
        msg += "from the application menu. You will be prompted for the location of\n";
        msg += "the simulation data files, and a location to save the result files.\n\n";

        String title = UserInterface.APP_NAME + " " + UserInterface.APP_VERSION;

        JOptionPane.showMessageDialog(null, msg, title, 0, infoIcon);

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void simLoaded(UserInterface frontend) {
        try {
            Thread.sleep(350);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        String msg = "Now that you have the simulation data loaded.\n";
        msg += "There are a few things you will need to know:\n";
        msg += "\t 1- You can control the simulation by using the Play, Pause,";
        msg += " Speed Up, and Slow Down buttons.\n";
        msg += "\t 2- You can navigate the map by panning and zooming.\n";
        msg += "\t 3- Also note, simulation output is located at the top of the application.";
        String title = UserInterface.APP_NAME + " " + UserInterface.APP_VERSION;
        title += ": Simulation Completed Successfully";
        JOptionPane.showMessageDialog(null, msg, title, 0, infoIcon);

    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void complete(UserInterface frontend) {
        try {
            Thread.sleep(350);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        String msg = "The simulation is now complete.\n";
        msg += "Please review the output directory.";
        String title = UserInterface.APP_NAME + " " + UserInterface.APP_VERSION;
        title += ": Simulation Completed Successfully";
        JOptionPane.showMessageDialog(null, msg, title, 1, infoIcon);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void ScreenshotTaken(UserInterface frontend, String fileName) {
        try {
            Thread.sleep(350);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        String msg = "The image has been saved to\n\t [ " + fileName + " ]\n";
        msg += "Please Take it to your personal folder!";
        String title = UserInterface.APP_NAME + " " + UserInterface.APP_VERSION + ": Screen shot has been taken successfuly";
        JOptionPane.showMessageDialog(null, msg, title, 1, infoIcon);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void loadTheNetworkPop(UserInterface frontend) {

        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        String msg = " Please, load the network data \n\t ";
        String title = UserInterface.APP_NAME + " " + UserInterface.APP_VERSION + ": Error";
        JOptionPane.showMessageDialog(null, msg, title, 0, errorIcon);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void ItemNotFound(UserInterface frontend, String msg) {

        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        String title = UserInterface.APP_NAME + " " + UserInterface.APP_VERSION + ": Error";
        JOptionPane.showMessageDialog(null, msg, title, 0, errorIcon);
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
