/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WaitingWindow.java
 *
 * Created on Jan 20, 2009, 12:09:28 PM
 */
package UI;
import java.awt.*;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author aalnawai
 */
public final class WaitingWindow extends javax.swing.JFrame
{
    static Image RedHorse = null;
    static Image BlueHorse = null;
    static Image Logo = null;
    private static Dimension RedHorseSize = new Dimension(200, 100);
    private static Dimension BlueHorseSize = new Dimension(200, 100);
    private static Dimension LogoSize = new Dimension(400, 50);
    File imageFile;
    String filename;
    BufferPanel buffer;
    public static String status;
    int perunaPosition;
    boolean drawRed;
    /**
     * Creates new form WaitingWindow
     */
    public WaitingWindow()
    {
        super("Please Wait...");
        RedHorse = ResizeImage(new javax.swing.ImageIcon(getClass().getResource("/Resources/peruna_red.gif")).getImage(), RedHorseSize);
        BlueHorse = ResizeImage(new javax.swing.ImageIcon(getClass().getResource("/Resources/peruna_blue.gif")).getImage(), BlueHorseSize);
        Logo = ResizeImage(new javax.swing.ImageIcon(getClass().getResource("/Resources/lyle.png")).getImage(), LogoSize);
        setIconImage(Logo);

        buffer = new BufferPanel();
        buffer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        add(buffer);

        status = "";
        perunaPosition = -1 * RedHorseSize.width ;
        drawRed = true;
        setAlwaysOnTop(true);
       
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 190);
        setResizable(false);
        setLocationRelativeTo(null);  //display window in the center of the screen

        buffer.repaint();
    }
    public Image ResizeImage(Image img, Dimension size)
    {
        return img.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
    }

    public class BufferPanel extends JPanel
    {
        int windowWidth, windowHeight;
        @Override
        public void paintComponent(Graphics g)
        {
           // super.paintComponent(g);

            windowWidth = getWidth();
            windowHeight = getHeight();

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, windowWidth, windowHeight);

            g.drawImage(Logo, (windowWidth - LogoSize.width) / 2, 0, null);
            g.drawImage(drawRed ? RedHorse : BlueHorse, perunaPosition, LogoSize.height, null);

            g.setColor(drawRed ? new Color(11, 61, 145) : new Color(252, 25, 34));
            g.setFont(new Font("serif", Font.BOLD, 14));
            g.drawString("Status: " + status, 4, windowHeight - 6);

            perunaPosition += 5;
            if (perunaPosition > windowWidth) {
                perunaPosition = -1 * RedHorseSize.width ;
                drawRed = !drawRed;
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException ie) {
            }
            repaint();
        }
    }
}
