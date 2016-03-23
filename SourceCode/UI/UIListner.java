package UI;

import DockingInterface.docking.properties.RootWindowProperties;
import DockingInterface.docking.theme.*;
import UI.SimulationPanel.SimulationPanel;
import UI.ToolBar.SimulationBandSet;
import UI.ToolBar.TaskRibbon;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import org.pushingpixels.flamingo.api.ribbon.JRibbon;
import Data.Network;

@SuppressWarnings("UnusedAssignment")
public class UIListner {
    //<editor-fold defaultstate="collapsed" desc="Variables">

    private UserInterface DView = null;
    int NumberOfOpenedPredictionWindows = 0;
    public int counterOfHittingPlayButton = 0;
    private final float ButtonMoveDistance = 0.5f;
    private final float ButtonAngle = 3f;
    private final int predictionHorizon = 300;
    public RootWindowProperties properties = new RootWindowProperties();
    public static DockingWindowsTheme[] themes = {new DefaultDockingTheme(),
        new LookAndFeelDockingTheme(),
        new BlueHighlightDockingTheme(),
        new SlimFlatDockingTheme(),
        new GradientDockingTheme(),
        new ShapedGradientDockingTheme(),
        new SoftBlueIceDockingTheme(),
        new ClassicDockingTheme()};
    public DockingWindowsTheme currentTheme = themes[5]; // The currently applied docking windows theme

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Common Tools">
    public static ImageIcon createResizedIcon(Image img, Dimension size) {
        ImageIcon icon = null;
        Image newimg = img.getScaledInstance(size.width - 8, size.height - 8, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        return icon;
    }

    public void centerWindow(Component frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        frame.setLocation(
                (screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Default Constructor">

    public UIListner(UserInterface aDIRECTView) {
        DView = aDIRECTView;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Engine variables">
    private boolean SELECTING_NETWORK = false;
    public String inputPath = "";
    private String outputPath = "";
    private File inputDir = null;
    private File outputDir = null;
    public WaitingWindow WaitingWindow = new WaitingWindow();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="DIRECT Methods">
    Timer RefreshTimer;

    public void OpenProjectAndRun() {
        DView.OverViewTaskPane.setDefaultLayout();
        DView.myNetwork = null;
        SELECTING_NETWORK = false;
        inputPath = DView.DefualtPath + "\\Input";
        outputPath = DView.DefualtPath + "\\Output";
        inputDir = new File(inputPath);
        outputDir = new File(outputPath);

        Load();

        SwingUtilities.invokeLater(() -> {
            final JRibbon DJRibbon = DView.getRibbon();
            final TaskRibbon SimulationTaskRibbon = (TaskRibbon) DJRibbon.getTask(1);
            SimulationBandSet SimulationBandSet = SimulationTaskRibbon.getSimulationBandSet();
        });

    }

    public void OpenProjectWithoutRun() {
        DView.OverViewTaskPane.setDefaultLayout();
        DView.myNetwork = null;
        SELECTING_NETWORK = false;
        inputPath = DView.DefualtPath + "\\Input";
        outputPath = DView.DefualtPath + "\\Output";
        inputDir = new File(inputPath);
        outputDir = new File(outputPath);

        Load();

        SwingUtilities.invokeLater(() -> {
            final JRibbon DJRibbon = DView.getRibbon();
            final TaskRibbon SimulationTaskRibbon = (TaskRibbon) DJRibbon.getTask(0);

            ActionListener RefreshUpdater = (ActionEvent evt) -> {
                if (DView.MainSimulationPanel.NetworkIsPreparedAndLoaded) {
                    RefreshTimer.stop();
                    DJRibbon.setSelectedTask(SimulationTaskRibbon);

                }
            };
            RefreshTimer = new Timer(50, RefreshUpdater);
            RefreshTimer.start();
        });
    }
    String NetworkPath = "";

    public synchronized boolean Load() {
        String FileName = "MOP.txt";
        String MOPPath = inputPath;
        File File = new File(inputPath);
        NetworkPath = File.getParent();

        WaitingWindow.setVisible(true);
     //   Network trafficNetwork = DView.myNetwork = new Network(DView.DefualtPath);
        NetworkLoader networkLoader = new NetworkLoader(DView, DView.myNetwork);
        networkLoader.start();
        return true;
    }

    public synchronized boolean LoadProject() {
        DView.OverViewTaskPane.setDefaultLayout();
        DView.myNetwork = null;
        SELECTING_NETWORK = false;
        //  pauseSimulation();

        if (chooseDirectory()) {
            return Load();
        } else {
            return false;
        }
    }

    public synchronized void LoadNetwork() {
        Network Network = DView.myNetwork;
        SimulationPanel MainSimulationPanel = DView.GetMainSimulationPanel();
        MainSimulationPanel.LoadNetwork();

    }

    private synchronized boolean chooseDirectory() {
        File input, output;
        boolean DIRECTORY_CHOSEN = false;

        // choose the directory containing input data
        this.inputPath = DView.DefualtPath + "\\Input";
        JFileChooser fc = new JFileChooser(inputPath);
        fc.setDialogTitle("Input Data Location: ");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            input = file;
        } else {
            // no file selected
            input = null;
        }
        // now a directory to save output files
        this.outputPath = DView.DefualtPath + "\\Output";
        fc = new JFileChooser(this.outputPath);
        fc.setDialogTitle("Output Data Location: ");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            output = file;
        } else {
            // no file selected
            output = null;
        }
        if (input != null && output != null) {
            this.inputPath = input.getPath();
            this.outputPath = output.getPath();
           
            this.inputDir = input;
            this.outputDir = output;
            DIRECTORY_CHOSEN = true;
           
        } else {
            this.inputPath = "";
            this.outputPath = "";
            this.inputDir = null;
            this.outputDir = null;
        }
        return DIRECTORY_CHOSEN;
    }
    // </editor-fold>

}
