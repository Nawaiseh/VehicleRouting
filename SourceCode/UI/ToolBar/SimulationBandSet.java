package UI.ToolBar;

import Execution.Master;
import UI.UserInterface;
import UI.SimulationPanel.SimulationPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.pushingpixels.flamingo.api.common.CommandButtonDisplayState;
import org.pushingpixels.flamingo.api.common.CommandToggleButtonGroup;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.JCommandToggleButton;
import org.pushingpixels.flamingo.api.common.popup.JCommandPopupMenu;
import org.pushingpixels.flamingo.api.common.popup.JPopupPanel;
import org.pushingpixels.flamingo.api.common.popup.PopupPanelCallback;
import org.pushingpixels.flamingo.api.ribbon.AbstractRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import Data.Network;
import Data.ReaderWriter.Writer;

/**
 *
 * @author aalnawai
 */
public class SimulationBandSet {

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~          Variables          ~~~~~~~~~~~~~~~ ">
    private UserInterface DView = null;
    private ViewBand ViewBand = null;
        private SimulateBand SimulateBand = null;
    private NetworkItemsBand NetworkItemsBand = null;
    private TaskRibbon TaskRibbon = null;
    public SimulationPanel ActiveSimulationPanel = null;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~SimulationBandSet Constructor~~~~~~~~~~~~~~~ ">

    public SimulationBandSet() {
    }

    public SimulationBandSet(UserInterface newDIRECTView) {
        DView = newDIRECTView;
        ActiveSimulationPanel = DView.GetMainSimulationPanel();
        NetworkItemsBand = new NetworkItemsBand();
        SimulateBand = new SimulateBand();
        ViewBand = new ViewBand();
        TaskRibbon = new TaskRibbon("Simulation", this, new AbstractRibbonBand[]{SimulateBand, ViewBand, NetworkItemsBand});
        TaskRibbon.setKeyTip("S");
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  Synchronized Set Methods   ~~~~~~~~~~~~~~~ ">

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  Synchronized Get Methods   ~~~~~~~~~~~~~~~ ">
    public synchronized TaskRibbon getTaskRibbon() {
        return TaskRibbon;
    }

    public synchronized ViewBand getViewBand() {
        return ViewBand;
    }

    public synchronized NetworkItemsBand getNetworkItemsBand() {
        return NetworkItemsBand;
    }

    // </editor-fold>
    public class NavigationsBand extends RibbonBand {
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~            Variables            ~~~~~~~~~~~~~~~ ">

        private JCommandButton ResetButton = new JCommandButton("Reset", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Reset.png"));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~        Action Listeners         ~~~~~~~~~~~~~~~ ">
        private ActionListener ResetButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reset();

            }
        };

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~   NavigationsBand Constructor   ~~~~~~~~~~~~~~~ ">
        public NavigationsBand() {
            super("Map", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/ViewGroup.png"), 2);
            ActiveSimulationPanel = DView.GetMainSimulationPanel();
            String PanelName = ActiveSimulationPanel.getName();
            //setTitle(PanelName + " [" + RoadMapButton.getText() + "]");
            ResetButton.addActionListener(ResetButton_ActionListener);
            Initialize();

        }

        private synchronized void Initialize() {

            addCommandButton(ResetButton, RibbonElementPriority.TOP);
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~    Synchronized Get Methods     ~~~~~~~~~~~~~~~ ">

        public synchronized TaskRibbon getTaskRibbon() {
            return TaskRibbon;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~     Synchronized  Methods       ~~~~~~~~~~~~~~~ ">

        private synchronized void Reset() {
            ActiveSimulationPanel.GetOpenGLRenderer().getOpenGLCamera().reset();
        }
        // </editor-fold>
    }

        public class SimulateBand extends RibbonBand {
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~            Variables            ~~~~~~~~~~~~~~~ ">

        public JCommandButton RunSimulationButton = new JCommandButton("Run Simulation", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Play.png"));
        public JCommandButton PauseSimulationButton = new JCommandButton("Pause Simulation", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Pause.png"));
        public JCommandButton StopSimulationButton = new JCommandButton("Stop Simulation", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Stop.png"));

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~        Action Listeners         ~~~~~~~~~~~~~~~ ">
        private ActionListener RunSimulation_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResumeSimulation();
            }
        };
        private ActionListener PauseSimulation_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PauseSimulation();
            }
        };
        private ActionListener StopSimulation_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StopSimulation();
            }
        };
      
        
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~     ProjectBand Constructor     ~~~~~~~~~~~~~~~ ">
        public SimulateBand() {
            super("Simulation", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/SimulationGroup.png"), 5);
            Initialize();
        }

        private void Initialize() {
            RunSimulationButton.addActionListener(RunSimulation_ActionListener);
            PauseSimulationButton.addActionListener(PauseSimulation_ActionListener);
            StopSimulationButton.addActionListener(StopSimulation_ActionListener);

            RunSimulationButton.setEnabled(true);
            PauseSimulationButton.setEnabled(false);
            StopSimulationButton.setEnabled(false);

            addCommandButton(RunSimulationButton, RibbonElementPriority.TOP);
            addCommandButton(PauseSimulationButton, RibbonElementPriority.TOP);
            addCommandButton(StopSimulationButton, RibbonElementPriority.TOP);
           

            RunSimulationButton.setActionKeyTip("R");
            PauseSimulationButton.setActionKeyTip("P");
            StopSimulationButton.setActionKeyTip("S");

        

        }

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~    Synchronized Get Methods     ~~~~~~~~~~~~~~~ ">
        public synchronized TaskRibbon getTaskRibbon() {
            return TaskRibbon;
        }

        public synchronized JCommandButton getRunSimulationButton() {
            return RunSimulationButton;
        }

        public synchronized JCommandButton getPauseSimulationButton() {
            return PauseSimulationButton;
        }

        public synchronized JCommandButton getStopSimulationButton() {
            return StopSimulationButton;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~     Synchronized  Methods       ~~~~~~~~~~~~~~~ ">


        private synchronized void ResumeSimulation() {
          
        }

        public synchronized void PauseSimulation() {

        }

        private synchronized void StopSimulation() {
        }

        public synchronized void RunSimulation() {
            ResumeSimulation();
        }

       
        // </editor-fold>
    }

    public class ViewBand extends RibbonBand {
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~            Variables            ~~~~~~~~~~~~~~~ ">

        private JCommandButton MainSimulationButton = new JCommandButton("Network State Estimation", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Estimate.png"));
        private JCommandPopupMenu MainSimulationMenu = new JCommandPopupMenu();
        private JCommandMenuButton MainSimulationPanelButton = new JCommandMenuButton("Simulation Window", getResizableIconFromResource("Resources/RibbonBands/Estimate.png"));
        private JCommandMenuButton MainSimulationLoggerButton = new JCommandMenuButton("Logger Window", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Estimate.png"));
        private JCommandButton OverViewButton = new JCommandButton("Network", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Network.png"));
        private JCommandButton BackgroundButton = new JCommandButton("Background", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Map.png"));
        private JCommandPopupMenu BackgroundMenu = new JCommandPopupMenu();
        private JCommandMenuButton NoBackgroundButton = new JCommandMenuButton("No Background", null);
        private JCommandMenuButton RoadMapButton = new JCommandMenuButton("Road Map", null);
        private JCommandMenuButton SatelliteMapButton = new JCommandMenuButton("Satellite Background", null);
        private JCommandMenuButton HybridMapButton = new JCommandMenuButton("Hybrid Map", null);
        private String SelectedBackground = "Road Map";
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~        Action Listeners         ~~~~~~~~~~~~~~~ ">
        private PopupPanelCallback MainSimulationButtonPopupPanelCallback = new PopupPanelCallback() {
            @Override
            public JPopupPanel getPopupPanel(JCommandButton jcb) {
                return MainSimulationMenu;
            }
        };
        private ActionListener MainSimulationButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainSimulationButton.doPopupClick();
            }
        };
        private ActionListener MainSimulationPanel_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowMainSimulationView();
            }
        };
        private ActionListener MainSimulationLoggerButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowMainSimulationLoggerView();
            }
        };

        private ActionListener OverViewButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowOverView();
            }
        };
        PopupPanelCallback BackgroundPopupPanelCallback = new PopupPanelCallback() {
            @Override
            public JPopupPanel getPopupPanel(JCommandButton jcb) {
                BackgroundMenu = new JCommandPopupMenu();
                BackgroundMenu.addMenuButton(NoBackgroundButton);
                BackgroundMenu.addMenuButton(RoadMapButton);
                BackgroundMenu.addMenuButton(SatelliteMapButton);
                BackgroundMenu.addMenuButton(HybridMapButton);
                NoBackgroundButton.addActionListener(BackgroundMenuButton_ActionListener);
                RoadMapButton.addActionListener(BackgroundMenuButton_ActionListener);
                SatelliteMapButton.addActionListener(BackgroundMenuButton_ActionListener);
                HybridMapButton.addActionListener(BackgroundMenuButton_ActionListener);
                return BackgroundMenu;
            }
        };
        private ActionListener BackgroundWindow_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BackgroundButton.doPopupClick();

            }
        };
        private ActionListener BackgroundMenuButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCommandMenuButton JCommandMenuButton = (JCommandMenuButton) e.getSource();
                SelectedBackground = JCommandMenuButton.getText();
                // GLRenderer GLRenderer = (GLRenderer) ActiveSimulationPanel.GetOpenGLRenderer();
                if (DView.GetMainSimulationPanel().GetOpenGLRenderer() != null) {
                    DView.GetMainSimulationPanel().GetOpenGLRenderer().SetBackgroundMap(SelectedBackground);
                }

            }
        };

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~   NavigationsBand Constructor   ~~~~~~~~~~~~~~~ ">
        public ViewBand() {
            super("Windows", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/ViewGroup.png"), 5);
            Initialize();
        }

        private synchronized void Initialize() {
            addCommandButton(OverViewButton, RibbonElementPriority.TOP);
            OverViewButton.addActionListener(OverViewButton_ActionListener);

            MainSimulationMenu.addMenuButton(MainSimulationPanelButton);
            MainSimulationMenu.addMenuButton(MainSimulationLoggerButton);
            MainSimulationPanelButton.addActionListener(MainSimulationPanel_ActionListener);
            MainSimulationLoggerButton.addActionListener(MainSimulationLoggerButton_ActionListener);

            MainSimulationButton.setCommandButtonKind(CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
            MainSimulationButton.setDisplayState(CommandButtonDisplayState.BIG);
            MainSimulationButton.setPopupCallback(MainSimulationButtonPopupPanelCallback);
            MainSimulationButton.addActionListener(MainSimulationButton_ActionListener);

            addCommandButton(MainSimulationButton, RibbonElementPriority.TOP);

            BackgroundButton.setCommandButtonKind(CommandButtonKind.ACTION_AND_POPUP_MAIN_ACTION);
            BackgroundButton.setDisplayState(CommandButtonDisplayState.MEDIUM);
            BackgroundButton.setPopupCallback(BackgroundPopupPanelCallback);
            BackgroundButton.addActionListener(BackgroundWindow_ActionListener);
            addCommandButton(BackgroundButton, RibbonElementPriority.TOP);
            BackgroundButton.add(BackgroundMenu);

        }

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~    Synchronized Get Methods     ~~~~~~~~~~~~~~~ ">
        public synchronized TaskRibbon getTaskRibbon() {
            return TaskRibbon;
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~     Synchronized  Methods       ~~~~~~~~~~~~~~~ ">

        private synchronized void ShowOverView() {
            DView.ShowView(DView.GetViews()[0]);
        }

        private synchronized void ShowMainSimulationView() {
            DView.ShowView(DView.GetViews()[1]);
        }

        private synchronized void ShowMainSimulationLoggerView() {
            DView.ShowView(DView.GetViews()[2]);
        }

        private synchronized void ShowPeriodicPredictionView() {
            DView.ShowView(DView.GetViews()[4]);
        }

        private synchronized void ShowPeriodicPredictionLoggerView() {
            DView.ShowView(DView.GetViews()[5]);
        }
        // </editor-fold>
    }

    public class NetworkItemsBand extends RibbonBand {
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~            Variables            ~~~~~~~~~~~~~~~ ">

//        private Dimension ButtonSize = new Dimension(120, 80);
        private JCommandToggleButton NoneButton = new JCommandToggleButton("None", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/None.png"));
        private JCommandToggleButton CamerasButton = new JCommandToggleButton("Cameras", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Camera.png"));
        private JCommandToggleButton DMSsButton = new JCommandToggleButton("DMS", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/DMS.png"));
        private JCommandToggleButton IntersectionsButton = new JCommandToggleButton("Intersections", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Intersection.png"));
        private JCommandToggleButton SignalsButton = new JCommandToggleButton("Signals", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Signal.png"));
        private JCommandToggleButton LinksButton = new JCommandToggleButton("Links", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Link.png"));
        private JCommandToggleButton ZonesButton = new JCommandToggleButton("Zones", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Zone.png"));
        private JCommandToggleButton ODPairsButton = new JCommandToggleButton("ODPairs", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/ODPair.png"));
        private JCommandToggleButton TransitLinesButton = new JCommandToggleButton("TransitLines", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/TransitLine.png"));
        private JCommandToggleButton ParkAndRidesButton = new JCommandToggleButton("P&R Facilities", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/ParkAndRide.png"));
        private JCommandToggleButton FacilityButton = new JCommandToggleButton("Facility", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/Facility.png"));
        private CommandToggleButtonGroup SelectModeButtonGroup = new CommandToggleButtonGroup();
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~        Action Listeners         ~~~~~~~~~~~~~~~ ">
        private ActionListener NoneButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectNone();
            }
        };
        private ActionListener CamerasButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectCameras();
            }
        };
        private ActionListener DMSsButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectDMSs();
            }
        };
        private ActionListener IntersectionsButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectIntersections();
            }
        };
        private ActionListener SignalsButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectSignals();
            }
        };
        private ActionListener LinksButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectLinks();
            }
        };
        private ActionListener ZonesButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectZones();
            }
        };
        private ActionListener ODPairsButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectODPairs();
            }
        };
        private ActionListener TransitLinesButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectTransitLines();
            }
        };
        private ActionListener ParkAndRidesButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectParkAndRides();
            }
        };
        private ActionListener FacilityButton_ActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectFacility();
            }
        };

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~    SelectModeBand Constructor   ~~~~~~~~~~~~~~~ ">
        public NetworkItemsBand() {
            super("Network Items", getResizableIconFromResource("Resources/RibbonBands/SimulationBand/NetworkItemsGroup.png"), 11);
            Initialize();
        }

        private synchronized void Initialize() {
            NoneButton.addActionListener(NoneButton_ActionListener);
            IntersectionsButton.addActionListener(IntersectionsButton_ActionListener);
            SignalsButton.addActionListener(SignalsButton_ActionListener);
            LinksButton.addActionListener(LinksButton_ActionListener);
            ZonesButton.addActionListener(ZonesButton_ActionListener);
            ODPairsButton.addActionListener(ODPairsButton_ActionListener);
            TransitLinesButton.addActionListener(TransitLinesButton_ActionListener);
            ParkAndRidesButton.addActionListener(ParkAndRidesButton_ActionListener);
            CamerasButton.addActionListener(CamerasButton_ActionListener);
            DMSsButton.addActionListener(DMSsButton_ActionListener);
            FacilityButton.addActionListener(FacilityButton_ActionListener);

            SelectModeButtonGroup.add(NoneButton);
            SelectModeButtonGroup.add(FacilityButton);
            SelectModeButtonGroup.add(SignalsButton);
            SelectModeButtonGroup.add(CamerasButton);
            SelectModeButtonGroup.add(DMSsButton);
            SelectModeButtonGroup.add(IntersectionsButton);
            SelectModeButtonGroup.add(LinksButton);
            SelectModeButtonGroup.add(ZonesButton);
            SelectModeButtonGroup.add(ODPairsButton);
            SelectModeButtonGroup.add(TransitLinesButton);
            SelectModeButtonGroup.add(ParkAndRidesButton);

            addCommandButton(NoneButton, RibbonElementPriority.TOP);
            addCommandButton(CamerasButton, RibbonElementPriority.TOP);
            addCommandButton(DMSsButton, RibbonElementPriority.TOP);
            addCommandButton(IntersectionsButton, RibbonElementPriority.TOP);
            addCommandButton(SignalsButton, RibbonElementPriority.TOP);
            addCommandButton(LinksButton, RibbonElementPriority.TOP);
            addCommandButton(FacilityButton, RibbonElementPriority.TOP);
            addCommandButton(ZonesButton, RibbonElementPriority.TOP);
            addCommandButton(ODPairsButton, RibbonElementPriority.TOP);
            addCommandButton(TransitLinesButton, RibbonElementPriority.TOP);
            addCommandButton(ParkAndRidesButton, RibbonElementPriority.TOP);

            if (Master.Release) {
                CamerasButton.setEnabled(false);
                DMSsButton.setEnabled(false);
            }
        }
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~    Synchronized Get Methods     ~~~~~~~~~~~~~~~ ">

        public synchronized TaskRibbon getTaskRibbon() {
            return TaskRibbon;
        }

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~     Synchronized  Methods       ~~~~~~~~~~~~~~~ ">
        public synchronized void ClearSelection() {
            SelectModeButtonGroup.clearSelection();
            DView.OverViewTaskPane.getOverviewPanel().ResetView();
        }

        private synchronized void SelectNone() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(NoneButton, true);

        }

        private synchronized void SelectCameras() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(CamerasButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getCamerasButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getCamerasButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getCamerasButton().getMainButton().doClick();
        }

        private synchronized void SelectDMSs() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(DMSsButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getDynamicMessageSignsButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getDynamicMessageSignsButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getDynamicMessageSignsButton().getMainButton().doClick();
        }

        private synchronized void SelectIntersections() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(IntersectionsButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getIntersectionsButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getIntersectionsButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getSignalsButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getSignalsButton().setEditButtonSelectedStatus(false);
            DView.OverViewTaskPane.getOverviewPanel().getIntersectionsButton().setEditButtonSelectedStatus(false);
            DView.OverViewTaskPane.getOverviewPanel().getIntersectionsButton().getMainButton().doClick();
        }

        private synchronized void SelectSignals() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(SignalsButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getIntersectionsButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getIntersectionsButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getSignalsButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getSignalsButton().setEditButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getIntersectionsButton().setEditButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getIntersectionsButton().getMainButton().doClick();
        }

        private synchronized void SelectLinks() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(LinksButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getLinksButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getLinksButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getLinksButton().getMainButton().doClick();
        }

        private synchronized void SelectZones() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(ZonesButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getZonesButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getZonesButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getZonesButton().getMainButton().doClick();
        }

        private synchronized void SelectODPairs() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(ODPairsButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getODPairsButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getZonesButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getODPairsButton().getMainButton().doClick();
        }

        private synchronized void SelectTransitLines() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(TransitLinesButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getTransitLinesButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getTransitLinesButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getTransitLinesButton().getMainButton().doClick();
        }

        private synchronized void SelectParkAndRides() {
            ClearSelection();
            SelectModeButtonGroup.setSelected(ParkAndRidesButton, true);
            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getParkingLotsButton().setMainButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getParkingLotsButton().setShowHideButtonSelectedStatus(true);
            DView.OverViewTaskPane.getOverviewPanel().getParkingLotsButton().getMainButton().doClick();
        }

        private synchronized void SelectFacility() {
            ClearSelection();
//            DView.FinalNetwork = DView.MainSimulationPanel.Network;
            DView.MainPassengersView.Network.DView = DView;
            DView.FinalNetwork.DView = DView;
            Writer Writer = new Writer(DView.MainPassengersView.Network.Directory);
            // Writer.WriteNetworkData(DView.MainSimulationPanel.Network);
            Writer.WriteNetworkData(DView.FinalNetwork);

//            SelectModeButtonGroup.setSelected(FacilityButton, true);
//            DView.OverViewTaskPane.getOverviewPanel().HeaderButton.setSelectButtonSelectionStatus(true);
//            DView.OverViewTaskPane.getOverviewPanel().getFacilitiesButton().setMainButtonSelectedStatus(true);
//            DView.OverViewTaskPane.getOverviewPanel().getFacilitiesButton().setShowHideButtonSelectedStatus(true);
//            DView.OverViewTaskPane.getOverviewPanel().getFacilitiesButton().getMainButton().doClick();
        }
        // </editor-fold>
    }
}
