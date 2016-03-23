package UI;

import DockingInterface.docking.*;
import DockingInterface.docking.util.DockingUtil;
import DockingInterface.docking.util.PropertiesUtil;
import DockingInterface.docking.util.ViewMap;
import DockingInterface.util.Direction;
import Execution.Master;
import UI.OverviewPanel.OverViewTaskPane;
import UI.SimulationPanel.SimulationPanel;
import UI.ToolBar.ApplicationMenu;
import UI.ToolBar.ProjectBandSet;
import UI.ToolBar.RibbonTaskBar;
import UI.ToolBar.SimulationBandSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.pushingpixels.flamingo.api.ribbon.JRibbon;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import Data.Network;

@SuppressWarnings({"SleepWhileHoldingLock", "SynchronizeOnNonFinalField", "NestedSynchronizedStatement"})
public final class UserInterface {

    public static String BasePath = System.getProperty("user.dir").replace("/","\\");
    public String[] DefualtPaths = new String[]{
        String.format("%s\\Samples\\Network 1\\", BasePath), // 0
        String.format("%s\\Samples\\Network 2\\", BasePath), // 1
        String.format("%s\\Samples\\Network 3\\", BasePath), // 2
    };
    static int NetworkIndex = 0;
    private static final Logger MyLogger = Logger.getLogger(UserInterface.class.getName());
    //<editor-fold defaultstate="collapsed" desc="Gui Variables">

    public SimulationPanel FocusedPanel = null;
    public RootWindow rootWindow;  // Main Window
    public int numberOfStaticWindows = 7;
    public int MaximumNumberOfPredictionViews = 20;
    private DynamicView[] views = new DynamicView[numberOfStaticWindows + MaximumNumberOfPredictionViews]; // 20-7 = 13 Available for creating prediction pages
    //private View[] views = new View[numberOfStaticWindows + numberOfDynamicWindows]; // 20-7 = 13 Available for creating prediction pages
    private ViewMap viewMap = new ViewMap(); // Views Container
    private JMenuItem[] viewItems = new JMenuItem[views.length];
    // ----------  Left Panel Components  --------------------------
    private DynamicView Overview = null;
    private DynamicView EditorView = new DynamicView("Editor View", ViewIcon.VIEW_ICON, null);
    // public View Listview = null;
    // --------------   Logger Panel Components -------------------
    private SplitWindow loggerPanel = null;
    private TabWindow SimulationLoggerTabWindow = null;
    private DynamicView MainSimulationLoggerView = null;
    // --------------   Simulation Components -------------------
    public TabWindow SimulationTabWindow = null;
    private SplitWindow SimulationPanelSplitWindow = null;
    public DynamicView MainSimulationView = null;
    // --------------   ResponsePlansPanel Components -------------------
    private View LastView = null;
    TreeMap<Integer, DynamicView> AvailablePredictionViews = new TreeMap<Integer, DynamicView>();
    public UIListner DViewListner = null;
    private int newSimulatorCounter = 0;
    public Network FinalNetwork;

    public synchronized DynamicView[] GetViews() {
        return views;
    }

    public void ShowPage(SimulationPanel SimulationPanel) {
        View View = null;
        if (this.MainSimulationPanel == SimulationPanel) {
            View = this.MainSimulationView;

        }
        if (View != null && View.getRootWindow() != null) {
            View.restoreFocus();
        } else {
            try {
                DockingUtil.addWindow(View, rootWindow);
            } catch (Exception ex) {
            }
        }
        MainSimulationView.restoreFocus();
    }

    public synchronized SimulationPanel GetMainSimulationPanel() {
        return MainSimulationPanel;
    }

    public synchronized void ShowView(final View View) {
        SwingUtilities.invokeLater(() -> {
            synchronized (rootWindow) {
                try {
                    if (View.getRootWindow() != null) {
                        View.restoreFocus();
                    } else {
                        try {
                            DockingUtil.addWindow(View, rootWindow);
                        } catch (Exception e) {
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (Exception Exception) {
                }
            }
        });
//        
    }

    public synchronized void ShowView(final DynamicView View, boolean ForceShowingFirst) {
        if (ForceShowingFirst) {
            synchronized (rootWindow) {
                if (View.getRootWindow() != null) {
                    View.restoreFocus();
                } else {
                    try {
                        DockingUtil.addWindow(View, rootWindow);
                    } catch (Exception e) {
                    }
                }

            }
            return;
        }

        SwingUtilities.invokeLater(() -> {
            synchronized (rootWindow) {
                if (View.getRootWindow() != null) {
                    View.restoreFocus();
                } else {
                    try {
                        DockingUtil.addWindow(View, rootWindow);
                    } catch (Exception e) {
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public synchronized void HideView(final DynamicView View) {
        SwingUtilities.invokeLater(() -> {
            synchronized (rootWindow) {
                if (View.getRootWindow() != null) {
                    View.close();

                }
            }
        });

    }
    private byte[][] layouts = new byte[3][];
    /**
     * The application frame
     */
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Version Variables">
    private static final long serialVersionUID = 840616867695097853L;
    public final static String APP_NAME = "Network Builder - DFW Area";
    public final static String APP_VERSION = "1.0";
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Engine Variables">
    public static final boolean MultipleExcelFiles = false;
    public static final boolean DEBUG = false;
    public Network myNetwork = null;
    public double SEARCH_ZOOM_FACTOR = 3;
    public String DefualtPath = DefualtPaths[NetworkIndex];
    int currentSimulationTime = 0;
    public SimulationPanel MainSimulationPanel = new SimulationPanel(this, this.myNetwork);
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="GUI Methods">

    public OverViewTaskPane OverViewTaskPane = new OverViewTaskPane(this);

    public synchronized void setApplicationTheme(LookAndFeel newLookAndFeel) {
        try {
            UIManager.setLookAndFeel(newLookAndFeel);

        } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
        }
    }

    public UserInterface() {

        DViewListner = new UIListner(this);
        ImageIcon ImageIcon = UIListner.createResizedIcon(new ImageIcon(getClass().getResource("/Resources/Estimation.png")).getImage(), MainSimulationPanel.WaterMarkSize);

        createRootWindow();
        MainSimulationPanel.setName("Network Estimate");
        MainSimulationPanel.View = MainSimulationView;

        setDefaultLayout();

        ProjectBandSet = new ProjectBandSet(this);
        SimulationBandSet = new SimulationBandSet(this);
        ApplicationMenu = new ApplicationMenu(this);
        RibbonTaskBar = new RibbonTaskBar(this);

        showFrame();
        DViewListner.properties.addSuperObject(PropertiesUtil.createTitleBarStyleRootWindowProperties());
//        Rectangle FrameBounds = frame.getBounds();
//        int HeightGap = 100;
//        int WidthGap = 10;
//        MainSimulationPanel.setBounds(1, 1, FrameBounds.width - WidthGap, FrameBounds.height - HeightGap);
//        MainSimulationPanel.SetDefaultLayout(FrameBounds);
//        PeriodicPredictionPanel.SetDefaultLayout(FrameBounds);
        FocusedPanel = MainSimulationPanel;
    }

    public synchronized JLabel getMouseLocation() {
        return this.MainSimulationPanel.GetMouseLocation();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Interface Methods : Not related to DIRECT Engine">

    /**
     * Creates the root window and the views.
     */
    public ImageIcon getResizableIconFromResource(String resource, Dimension Size) {

        try {
            Image img = new ImageIcon(getClass().getResource(resource)).getImage();
            Image newimg = img.getScaledInstance(Size.width - 1, Size.height - 1, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newimg);

            return icon;

        } catch (Exception Ex) {
            return null;

        }
    }

    private synchronized void createRootWindow() {
        // Left Panel
        ImageIcon Icon = getResizableIconFromResource("/Resources/RibbonBands/SimulationBand/Network.png", new Dimension(30, 15));
        views[0] = Overview = new DynamicView("Network", Icon, OverViewTaskPane);
        Icon = getResizableIconFromResource("/Resources/RibbonBands/SimulationBand/Estimate.png", new Dimension(30, 15));
        views[1] = MainSimulationView = new DynamicView("Network State Estimation", Icon, MainSimulationPanel);  // Main Simulation Panel
        views[2] = MainSimulationLoggerView = new DynamicView("Network State Estimation Log Area", Icon, MainSimulationPanel.GetLogArea()); // Main Simulation Logger Panel

        Icon = getResizableIconFromResource("/Resources/RibbonBands/SimulationBand/Plan.png", new Dimension(30, 15));
        views[3] = new DynamicView("Select", Icon, new JPanel());
        Icon = getResizableIconFromResource("/Resources/RibbonBands/SimulationBand/PeriodicPrediction.png", new Dimension(30, 15));
        views[4] = new DynamicView("Periodic Prediction", Icon, new JPanel());  // Main Simulation Panel
        views[5] = new DynamicView("Periodic Prediction Log Area", Icon, new JPanel()); // Main Simulation Logger Panel
        Icon = getResizableIconFromResource("/Resources/RibbonBands/ToolsBand/DynamicPricing.png", new Dimension(30, 15));

        Icon = getResizableIconFromResource("/Resources/RibbonBands/SimulationBand/Estimate.png", new Dimension(30, 15));
        views[6] = new DynamicView("Real Time", Icon, new JPanel()); // Main Simulation Logger Panel

        MainSimulationView.isfocused = true;
        // The mixed view map makes it easy to mix static and dynamic views inside the same root window

        rootWindow = DockingUtil.createRootWindow(viewMap, null, true);
        DViewListner.properties.addSuperObject(DViewListner.currentTheme.getRootWindowProperties());

        rootWindow.getRootWindowProperties().addSuperObject(DViewListner.properties);
        rootWindow.getWindowBar(Direction.DOWN).setEnabled(true);
    }

    private synchronized void createSimulationTabWindow() {
        DockingWindow[] mainViews = new View[5];
        mainViews[0] = views[3];
        mainViews[1] = MainSimulationView;
        mainViews[2] = views[4];
        mainViews[3] = views[5];
        mainViews[4] = views[6];
        SimulationTabWindow = new TabWindow(mainViews);
    }

    private synchronized void setDefaultLayout() {

        //  leftPanel = new SplitWindow(false, 0.51f, new TabWindow(new View[]{Overview}), Listview);
//        rightPanel = new SplitWindow(false, 0.4f, new TabWindow(new View[]{ModalSplitView}), new SplitWindow(false, 0.5f, new TabWindow(new View[]{SpeedProfileView}), VehicleCountView));
        createSimulationTabWindow();
        rootWindow.setWindow(new SplitWindow(true, 0.138f, new TabWindow(new View[]{Overview}), new SplitWindow(true, 0.60f, SimulationTabWindow, EditorView)));

        views[3].close();
        MainSimulationLoggerView.close();
        views[4].close();
        //PeriodicPredictionView.close();
        EditorView.close();
        views[5].close();
        views[6].close();
        Overview.close();

    }
    private JFrame frame = new JFrame(UserInterface.APP_NAME + " " + UserInterface.APP_VERSION);
    private JRibbonFrame JRibbonFrame = new JRibbonFrame(UserInterface.APP_NAME + " " + UserInterface.APP_VERSION);
    public ProjectBandSet ProjectBandSet = null;
    public SimulationBandSet SimulationBandSet = null;
    public ApplicationMenu ApplicationMenu = null;
    public RibbonTaskBar RibbonTaskBar = null;
    public JMenu WindowsMenu = null;

    public synchronized JRibbon getRibbon() {
        return JRibbonFrame.getRibbon();

    }

    private synchronized void showFrame() {
        JRibbonFrame.getRibbon().addTask(ProjectBandSet.getTaskRibbon());
        JRibbonFrame.getRibbon().addTask(SimulationBandSet.getTaskRibbon());
        // JRibbonFrame.getRibbon().setApplicationMenu(ApplicationMenu);
        JRibbonFrame.getRibbon().setApplicationMenu(null);
        //JRibbonFrame.getRibbon().getApplicationMenu()

        JRibbonFrame.setApplicationIcon(ApplicationMenu.ApplicationIcon);
        JRibbonFrame.getRibbon().addTaskbarComponent(RibbonTaskBar);
        JRibbonFrame.getContentPane().add(rootWindow, BorderLayout.CENTER);
        JRibbonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JRibbonFrame.setVisible(!Master.UserInterfaceIsDisabled);
        JRibbonFrame.setFocusable(true);
        JRibbonFrame.setPreferredSize(new Dimension(1500, 700));
        JRibbonFrame.setSize(new Dimension(1500, 700));
        

        SetActionMap();

        if (Master.AutomatedOpenAndRun) {
            DViewListner.OpenProjectAndRun();
        } else if (Master.AutomatedOpenWithoutRun) {
            DViewListner.OpenProjectWithoutRun();
        }
         ShowOnScreen(1, JRibbonFrame);
         JRibbonFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void ShowOnScreen(int Screen, JFrame JFrame) {
        GraphicsEnvironment Environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] GraphicsDevices = Environment.getScreenDevices();

        if (Screen > -1 && Screen < GraphicsDevices.length) {
            JFrame.setLocation(GraphicsDevices[Screen].getDefaultConfiguration().getBounds().x, JFrame.getY());
        } else if (GraphicsDevices.length > 0) {
            JFrame.setLocation(GraphicsDevices[0].getDefaultConfiguration().getBounds().x, JFrame.getY());
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    public void SetActionMap() {
        JRootPane RootPane = JRibbonFrame.getRootPane();
        InputMap inputMap = RootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        String FindName = "Find";
        String FindAndZoomName = "Find&Zoom";
        String IDName = "ShowHideID";

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.CTRL_DOWN_MASK), FindName);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), FindAndZoomName);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), IDName);

        ActionMap actionMap = RootPane.getActionMap();

        actionMap.put(FindName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean IsModalDialog = true;
                SimulationBandSet.ActiveSimulationPanel.ShowFindPanel(JRibbonFrame, IsModalDialog, false);

            }
        });
        actionMap.put(FindAndZoomName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean IsModalDialog = true;
                SimulationBandSet.ActiveSimulationPanel.ShowFindPanel(JRibbonFrame, IsModalDialog, true);

            }
        });
        actionMap.put(IDName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton IDButton = OverViewTaskPane.getOverviewPanel().HeaderButton.IDButton;
                IDButton.setSelected(!IDButton.isSelected());

            }
        });

    }

    private synchronized void freezeLayout(boolean freeze) {
        // Freeze window operations
        DViewListner.properties.getDockingWindowProperties().setDragEnabled(false);
        DViewListner.properties.getDockingWindowProperties().setCloseEnabled(false);
        DViewListner.properties.getDockingWindowProperties().setMinimizeEnabled(true);
        DViewListner.properties.getDockingWindowProperties().setRestoreEnabled(true);
        DViewListner.properties.getDockingWindowProperties().setMaximizeEnabled(true);
        DViewListner.properties.getDockingWindowProperties().setUndockEnabled(false);
        DViewListner.properties.getDockingWindowProperties().setDockEnabled(true);

        // Freeze tab reordering inside tabbed panel
        DViewListner.properties.getTabWindowProperties().getTabbedPanelProperties().setTabReorderEnabled(!freeze);

    }
    //---------------------------------------------
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Interface Methods : main Method">

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) throws Exception {
        // Set InfoNode Look and Feel
        // Docking windwos should be run in the Swing thread
        SwingUtilities.invokeLater(() -> {
            new UserInterface();
        });
    }

    //</editor-fold>
}
