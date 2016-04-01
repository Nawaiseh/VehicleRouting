package UI.SimulationPanel;
// <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~          Imports          ~~~~~~~~~~~~~~~~~~~~~ ">

import UI.UserInterface;
import UI.DSystem;
import UI.SimulationPanel.Utils.FindPanel;
import UI.Utils.MouseListener;
import UI.Utils.OpenGLSimulationPanel;
import UI.Utils.OpenGLUtils.Point3D;
import com.sun.opengl.util.Animator;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import org.jdesktop.swingx.JXTaskPane;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import Data.Node;
import Data.Link;
import Data.Network;
// </editor-fold>

@SuppressWarnings("UnusedAssignment")
public class SimulationPanel extends OpenGLSimulationPanel {

    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~       Variables        ~~~~~~~~~~~~~~~~~~~~~ ">
    private static final long serialVersionUID = 1L;
    public boolean SynchronizeWithCurrentRealTimeData = true;

    public enum SelectedWindow {

        UEPanel, FloatingVehicle, Default
    }
    public boolean IsNotCleared = true;
    private static DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
    private static DateFormat TimeFormat = new SimpleDateFormat("hh:mm:ss");
    public DSystem DSystem = null;
    public boolean SELECTING_NETWORK = false;
    public boolean isfirstPrediction = true;
    private FileWriter FileWriter = null;
    private BufferedWriter BufferedWriter = null;
    private ButtonGroup FilterDemandGroup = new ButtonGroup();
    private GLRenderer GLRenderer;

    //  private JButton ShowHideFloatingVehicleWindow_Button = new JButton("Show Floating Vehicle");
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~     Constructors       ~~~~~~~~~~~~~~~~~~~~~ ">
    public SimulationPanel() {
        super();

    }

    public SimulationPanel(UserInterface newDIRECTView, Network newNetwork) {
        super(newDIRECTView);
        Network = newNetwork;

        DView = newDIRECTView;

        OpenGLPanel.setFocusable(true);
        OpenGLPanel.setRequestFocusEnabled(true);
        OpenGLPanel.requestFocus();
        DSystem = new DSystem(this);
        // Date.setText(dateFormat.format(now));
        OpenGLAnimator = new Animator(OpenGLPanel);
        OpenGLAnimator.setIgnoreExceptions(false);
        OpenGLAnimator.setRunAsFastAsPossible(true);

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~    Dispose Methods     ~~~~~~~~~~~~~~~~~~~~~ ">
    public synchronized void DisposeGLRenderer() {
        if (OpenGLAnimator != null && OpenGLAnimator.isAnimating()) {
            OpenGLAnimator.stop();
            OpenGLAnimator = null;
        }
        if (GLRenderer != null) {
            GLRenderer.Dispose();
            GLRenderer = null;
        }

    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~      Set Methods       ~~~~~~~~~~~~~~~~~~~~~ ">
    Rectangle FrameBounds = new Rectangle();

    public void SetDefaultLayout(Rectangle newFrameBounds) {

    }

    public synchronized void SetNetwork(Network newNetwork) {
        if (newNetwork != null) {
            if (GLRenderer == null) {
                GLRenderer = new GLRenderer(this, newNetwork);
            }
            super.SetNetwork(Network = newNetwork, GLRenderer);

        }
    }

    public void SetMOPFile(String FileName) {
        try {
            FileWriter = new FileWriter(FileName, true);
            BufferedWriter = new BufferedWriter(FileWriter);
            String[] Header0 = {"Number of Travelers", "Travel Time", "Travel Delay", "Travel Distance"};

            String[] Header1 = {"Facility", "TimeStamp",
                "Mode", "Share (Travelers)", "Percentage (%)",
                "Total (Minutes)", "Average (Minutes)",
                "Total (Travelers-Minutes)", "Average (Minutes)",
                "Total (Travelers-Miles)", "Average (Miles)"};
            int ColumnCount = Header0.length;
            BufferedWriter.write("\t\t");
            for (int ModeIndex = 0; ModeIndex < 6; ModeIndex++) {
                for (int Column = 0; Column < ColumnCount; Column++) {
                    BufferedWriter.write("\t" + Header0[Column] + "\t");
                }
                BufferedWriter.write("\t\t\t");
            }
            BufferedWriter.newLine();
            BufferedWriter.flush();
            ColumnCount = Header1.length;
            for (int ModeIndex = 0; ModeIndex < 6; ModeIndex++) {
                for (int Column = 0; Column < ColumnCount; Column++) {
                    if (ModeIndex > 0 && Column <= 1) {
                        continue;
                    }
                    BufferedWriter.write(Header1[Column] + "\t");
                }
                BufferedWriter.write("\t\t");
            }
            BufferedWriter.newLine();
            BufferedWriter.flush();
        } catch (IOException ex) {
            MyLogger.log(Level.WARNING, String.format("Error in Writing MOP Files to file [%s]", FileName), ex);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~      Get Methods       ~~~~~~~~~~~~~~~~~~~~~ ">

    public BufferedWriter GetFileWriter() {
        return BufferedWriter;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~   Show/Hide Methods    ~~~~~~~~~~~~~~~~~~~~~ ">

    public synchronized void ShowFindPanel(JRibbonFrame JRibbonFrame, boolean IsModalDialog, boolean FindAndZoom) {
        FindPanel FindNetworkItemPanel = new FindPanel(JRibbonFrame, IsModalDialog, FindAndZoom, this);
        FindNetworkItemPanel.setVisible(true);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~ Tables Update Methods  ~~~~~~~~~~~~~~~~~~~~~ ">
    public MouseListener TablesPropertyChangeListener = new MouseListener() {
        @Override
        public void propertyChange(PropertyChangeEvent PropertyChangeEvent) {

        }
    };

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~    Public Methods      ~~~~~~~~~~~~~~~~~~~~~ ">
    public synchronized void RefreshOpenWindows() {

        Container.revalidate();
        Container.repaint();
    }

    public synchronized void LoadNetwork() {

        if (OpenGLAnimator != null && OpenGLAnimator.isAnimating()) {
            OpenGLAnimator.stop();
        }
        try {
            if (Network == null) {
                MyLogger.log(Level.WARNING, "Network is null");
                return;
            } else if (Network.Furthest_Node_On_X_Axis == -1 || Network.Furthest_Node_On_Y_Axis == -1) {
                MyLogger.log(Level.WARNING, "Network is not Prepared");
                return;
            }
            OpenGLRenderer.SetNetwork(Network);
            Point3D OpenGLCenter = DView.MainPassengersView.GLRenderer.GetOpenGLCenter();
            TreeMap<Long, Node> Nodes = Network.Nodes;
            for (Node Node : Nodes.values()) {
                Node.OpenGLLocation.X = Node.Location.X - OpenGLCenter.X;
                Node.OpenGLLocation.Y = Node.Location.Y - OpenGLCenter.Y;
                Node.OpenGLLocation.Z = Node.Location.Z - OpenGLCenter.Z;

            }
            NetworkIsPreparedAndLoaded = true;
            //Network.OpenGLSimulationPanel = this;

            if (OpenGLAnimator != null && !OpenGLAnimator.isAnimating()) {
                OpenGLAnimator.start();
            }

        } catch (Exception Exception) {
            MyLogger.log(java.util.logging.Level.WARNING, "Error In LoadNetwork Method", Exception);
        }
    }
    // </editor-fold>
 
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~     MouseListener      ~~~~~~~~~~~~~~~~~~~~~ ">


    MouseListener MouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent Event) {
            Point MousePosition = Event.getPoint();
            if (Event.getSource() instanceof JXTaskPane) {
                JXTaskPane TaskPane = (JXTaskPane) Event.getSource();
                Container Container = (Container) TaskPane.getParent().getParent();
               

            }
        }
    };
    // </editor-fold>
}
