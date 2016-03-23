/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.OverviewPanel;

import UI.UserInterface;
import Execution.Master;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JTable;

/**
 *
 * @author aalnawai
 */
public class OverviewPanel extends javax.swing.JPanel {

    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~           Variables           ~~~~~~~~~~~~ ">
    private static final long serialVersionUID = 1L;
    private Component DefaultComponent = null;
    private OverviewButton PreviouslySelectedButton = null;
    private Component CurrentComponent = null;
    private ArrayList<JTable> Tables = new ArrayList<JTable>();
    private UserInterface DView = null;
    private OverViewTaskPane OverViewTaskPane = null;
    private ButtonGroup MainButtonsGroup = new ButtonGroup();
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~       Action Listeners        ~~~~~~~~~~~~ ">
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~         Header Item Listeners         ~~~~~~~~~~~~ ">
    ItemListener AddItemListener = (java.awt.event.ItemEvent evt) -> {
    };
    ItemListener SelectItemListener = new java.awt.event.ItemListener() {

        @Override
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            // HeaderButton.AddButton.setSelected(!HeaderButton.SelectButton.isSelected());
            if (HeaderButton.getSelectButtonSelectionStatus() && MeasureOfPerformanceButton.getMainButtonSelectedStatus()) {
                OverViewTaskPane.ListViewTaskPane.repaint();
                OverViewTaskPane.ListViewTaskPane.revalidate();
                OverViewTaskPane.ListViewTaskPane.repaint();
            }
        }
    };
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~     Intersections ActionListener      ~~~~~~~~~~~~ ">
    ActionListener IntersectionsActionListener = (ActionEvent e) -> {
        if (this.IntersectionsButton.getMainButtonSelectedStatus()) {
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(false);
            setDefaultListView();
            
            //  OverViewTaskPane.ListViewTaskPane.add(CurrentComponent = IntersectionsTable);
            this.PreviouslySelectedButton = this.IntersectionsButton;
        } else {
            this.PreviouslySelectedButton = this.IntersectionsButton;
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(true);
        }
    };
     ActionListener LinksActionListener = (ActionEvent e) -> {
        if (this.LinksButton.getMainButtonSelectedStatus()) {
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(false);
            setDefaultListView();

            //  OverViewTaskPane.ListViewTaskPane.add(CurrentComponent = LinksTable);
            this.PreviouslySelectedButton = this.LinksButton;
        } else {
            this.PreviouslySelectedButton = this.IntersectionsButton;
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(true);
        }
    };
    ActionListener ZonesActionListener = (ActionEvent e) -> {
        if (this.ZonesButton.getMainButtonSelectedStatus()) {
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(false);
            setDefaultListView();
            //  OverViewTaskPane.ListViewTaskPane.add(CurrentComponent = ZonesTable);
            this.PreviouslySelectedButton = this.ZonesButton;
        } else {
            this.PreviouslySelectedButton = this.IntersectionsButton;
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(true);
        }
    };
    ActionListener OD_PairsActionListener = (ActionEvent e) -> {
    };
    ActionListener SignalsActionListener = (ActionEvent e) -> {
    };
    ActionListener StopSignsActionListener = (ActionEvent e) -> {
        if (this.StopSignsButton.getMainButtonSelectedStatus()) {
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(false);
            setDefaultListView();
            // OverViewTaskPane.ListViewTaskPane.add(CurrentComponent = StopSignsTable);
            this.PreviouslySelectedButton = this.StopSignsButton;
        } else {
            this.PreviouslySelectedButton = this.IntersectionsButton;
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(true);
        }
    };
    ActionListener DMSActionListener = (ActionEvent e) -> {
        if (this.DMSButton.getMainButtonSelectedStatus()) {
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(false);
            setDefaultListView();
            //   OverViewTaskPane.ListViewTaskPane.add(CurrentComponent = DynamicMessageSignsTable);
            this.PreviouslySelectedButton = this.DMSButton;
        } else {
            this.PreviouslySelectedButton = this.IntersectionsButton;
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(true);
        }
    };
    ActionListener DetectorsActionListener = (ActionEvent e) -> {
    };
    ActionListener IncidentsActionListener = (ActionEvent e) -> {
    };
    ActionListener TransitLinesActionListener = (ActionEvent e) -> {
    };
    ActionListener TransitStopsActionListener = (ActionEvent e) -> {
    };
    ActionListener ParkingLotsActionListener = (ActionEvent e) -> {
    };
    ActionListener TollGantriesActionListener = (ActionEvent e) -> {
    };
    ActionListener MOPActionListener = (ActionEvent e) -> {
    };
    ActionListener MovementsActionListener = (ActionEvent e) -> {
    };
    ActionListener CamerasActionListener = (ActionEvent e) -> {
    };
    ActionListener FacilitiesActionListener = (ActionEvent e) -> {
        if (this.FacilitiesButton.getMainButtonSelectedStatus()) {
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(false);
            setDefaultListView();
            //   OverViewTaskPane.ListViewTaskPane.add(CurrentComponent = CamerasTable);
            this.PreviouslySelectedButton = this.FacilitiesButton;
        } else {
            this.PreviouslySelectedButton = this.IntersectionsButton;
            this.PreviouslySelectedButton.setMainButtonSelectedStatus(true);
        }
    };
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~   OverviewPanel Constructors  ~~~~~~~~~~~~ ">

    public OverviewPanel() {
        initComponents();
    }

    public OverviewPanel(UserInterface newDIRECTView, OverViewTaskPane newOverViewTaskPane) {
        OverViewTaskPane = newOverViewTaskPane;
        DView = newDIRECTView;
        //   CurrentComponent = IntersectionsTable;
        initComponents();
        if (Master.Release) {
            this.HeaderButton.getAddButton().setEnabled(false);
        }

        PreviouslySelectedButton = IntersectionsButton;

        MainButtonsGroup.add(this.IntersectionsButton.getMainButton());
        MainButtonsGroup.add(this.LinksButton.getMainButton());
        MainButtonsGroup.add(this.ZonesButton.getMainButton());
        MainButtonsGroup.add(this.OD_PairsButton.getMainButton());
        MainButtonsGroup.add(this.SignalsButton.getMainButton());
        MainButtonsGroup.add(this.StopSignsButton.getMainButton());
        MainButtonsGroup.add(this.DMSButton.getMainButton());
        MainButtonsGroup.add(this.DetectorsButton.getMainButton());
        MainButtonsGroup.add(this.IncidentsButton.getMainButton());
        MainButtonsGroup.add(this.TransitLinesButton.getMainButton());
        MainButtonsGroup.add(this.TransitStopsButton.getMainButton());
        MainButtonsGroup.add(this.ParkingLotsButton.getMainButton());
        MainButtonsGroup.add(this.TollGantriesButton.getMainButton());
        MainButtonsGroup.add(this.MeasureOfPerformanceButton.getMainButton());
        MainButtonsGroup.add(this.MovementsButton.getMainButton());
        MainButtonsGroup.add(this.CamerasButton.getMainButton());
        MainButtonsGroup.add(this.FacilitiesButton.getMainButton());

        if (Master.Release) {
            CamerasButton.getFilterButton().setEnabled(false);
            CamerasButton.getMainButton().setEnabled(false);
            CamerasButton.getEditButton().setEnabled(false);

            DMSButton.getFilterButton().setEnabled(false);
            DMSButton.getMainButton().setEnabled(false);
            DMSButton.getEditButton().setEnabled(false);

            MovementsButton.getFilterButton().setEnabled(false);
            MovementsButton.getMainButton().setEnabled(false);
            MovementsButton.getEditButton().setEnabled(false);

            TollGantriesButton.getFilterButton().setEnabled(false);
            TollGantriesButton.getMainButton().setEnabled(false);
            TollGantriesButton.getEditButton().setEnabled(false);

        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~   Synchronized Set Methods    ~~~~~~~~~~~~ ">
    private synchronized void setDefaultListView() {
        if (CurrentComponent != null) {
            OverViewTaskPane.ListViewTaskPane.remove(CurrentComponent);
            OverViewTaskPane.ListViewTaskPane.repaint();
            OverViewTaskPane.ListViewTaskPane.revalidate();
            OverViewTaskPane.ListViewTaskPane.repaint();
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~   Synchronized Get Methods    ~~~~~~~~~~~~ ">
   
    public synchronized ArrayList<JTable> getTables() {
        return Tables;
    }
       // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~   Synchronized Get Buttons    ~~~~~~~~~~~~ ">

    public synchronized OverviewButton getIntersectionsButton() {
        return IntersectionsButton;
    }

    public synchronized OverviewButton getFacilitiesButton() {
        return FacilitiesButton;
    }

    public synchronized OverviewButton getLinksButton() {
        return LinksButton;
    }

    public synchronized OverviewButton getZonesButton() {
        return ZonesButton;
    }

    public synchronized OverviewButton getODPairsButton() {
        return OD_PairsButton;
    }

    public synchronized OverviewButton getStopSignsButton() {
        return StopSignsButton;
    }

    public synchronized OverviewButton getSignalsButton() {
        return SignalsButton;
    }

    public synchronized OverviewButton getIncidentsButton() {
        return IncidentsButton;
    }

    public synchronized OverviewButton getDynamicMessageSignsButton() {
        return DMSButton;
    }

    public synchronized OverviewButton getDetectorsButton() {
        return this.DetectorsButton;
    }

    public synchronized OverviewButton getParkingLotsButton() {
        return ParkingLotsButton;
    }

    public synchronized OverviewButton getMeasureOfPerformanceButton() {
        return MeasureOfPerformanceButton;
    }

    public synchronized OverviewButton getCamerasButton() {
        return CamerasButton;
    }

    public synchronized OverviewButton getTransitLinesButton() {
        return TransitLinesButton;
    }

    public synchronized OverviewButton getTransitStopsButton() {
        return TransitStopsButton;
    }

    // </editor-fold>
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~  Synchronized Public Methods  ~~~~~~~~~~~~ ">
    public synchronized void ClearAllSelectionExcept(JTable ExceptionTable) {
        for (JTable Table : Tables) {
            if (Table != ExceptionTable) {
                Table.clearSelection();
                Table.firePropertyChange("Selection Changed", true, false);
            }
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~            Dispose            ~~~~~~~~~~~~ ">

    public void Dispose() {
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        controls1 = new UI.SimulationPanel.Controls();
        jPanel39 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        HeaderButton = new UI.OverviewPanel.OverviewHeaderButton();
        jPanel3 = new javax.swing.JPanel();
        IntersectionsButton = new UI.OverviewPanel.OverviewButton("Intersections");
        LinksButton = new UI.OverviewPanel.OverviewButton("Links");
        ZonesButton = new UI.OverviewPanel.OverviewButton("Zones");
        OD_PairsButton = new UI.OverviewPanel.OverviewButton("OD Pairs");
        SignalsButton = new UI.OverviewPanel.OverviewButton("Signals");
        StopSignsButton = new UI.OverviewPanel.OverviewButton("Stop Signs");
        DMSButton = new UI.OverviewPanel.OverviewButton("Dynamic Message Signs");
        DetectorsButton = new UI.OverviewPanel.OverviewButton("Detectors");
        IncidentsButton = new UI.OverviewPanel.OverviewButton("Incidents");
        TransitLinesButton = new UI.OverviewPanel.OverviewButton("Transit Lines");
        TransitStopsButton = new UI.OverviewPanel.OverviewButton("Transit Stops");
        ParkingLotsButton = new UI.OverviewPanel.OverviewButton("Parking Lots");
        TollGantriesButton = new UI.OverviewPanel.OverviewButton("Toll Gantries");
        MeasureOfPerformanceButton = new UI.OverviewPanel.OverviewButton("Measure of Performance");
        FacilitiesButton = new UI.OverviewPanel.OverviewButton("Facilities");
        MovementsButton = new UI.OverviewPanel.OverviewButton("Movements");
        CamerasButton = new UI.OverviewPanel.OverviewButton("Cameras");

        setPreferredSize(new java.awt.Dimension(220, 465));
        setLayout(new java.awt.CardLayout());

        jPanel39.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(0, 27));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(HeaderButton, java.awt.BorderLayout.CENTER);
        HeaderButton.getAddButton().addItemListener(AddItemListener);
        HeaderButton.getSelectButton().addItemListener(SelectItemListener);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.GridLayout(17, 1));
        jPanel3.add(IntersectionsButton);
        IntersectionsButton.getMainButton().addActionListener(IntersectionsActionListener);
        IntersectionsButton.setMainButtonSelectedStatus(true);
        jPanel3.add(LinksButton);
        LinksButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Link.png")).getImage());
        LinksButton.getMainButton().addActionListener(LinksActionListener);
        LinksButton.setShowHideButtonSelectedStatus(true);
        jPanel3.add(ZonesButton);
        ZonesButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Zone.png")).getImage());
        ZonesButton.getMainButton().addActionListener(ZonesActionListener);
        ZonesButton.setMainButtonSelectedStatus(false);
        jPanel3.add(OD_PairsButton);
        OD_PairsButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/OD.png")).getImage());
        OD_PairsButton.getMainButton().addActionListener(OD_PairsActionListener);
        OD_PairsButton.setMainButtonSelectedStatus(false);
        jPanel3.add(SignalsButton);
        SignalsButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Signal.png")).getImage());
        SignalsButton.getMainButton().addActionListener(SignalsActionListener);
        SignalsButton.setMainButtonSelectedStatus(false);
        jPanel3.add(StopSignsButton);
        StopSignsButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/StopSign.png")).getImage());
        StopSignsButton.getMainButton().addActionListener(StopSignsActionListener);
        StopSignsButton.setMainButtonSelectedStatus(false);
        jPanel3.add(DMSButton);
        DMSButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/DMS.png")).getImage());

        DMSButton.getMainButton().addActionListener(DMSActionListener);
        DMSButton.setMainButtonSelectedStatus(false);
        jPanel3.add(DetectorsButton);
        DetectorsButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Detector.png")).getImage());
        DetectorsButton.getMainButton().addActionListener(DetectorsActionListener);
        DetectorsButton.setMainButtonSelectedStatus(false);
        jPanel3.add(IncidentsButton);
        IncidentsButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Incident.png")).getImage());
        IncidentsButton.getMainButton().addActionListener(IncidentsActionListener);
        IncidentsButton.setMainButtonSelectedStatus(false);
        jPanel3.add(TransitLinesButton);
        TransitLinesButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Bus.png")).getImage());
        TransitLinesButton.getMainButton().addActionListener(TransitLinesActionListener);
        TransitLinesButton.setMainButtonSelectedStatus(false);
        jPanel3.add(TransitStopsButton);
        TransitStopsButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/BusStop.png")).getImage());
        TransitStopsButton.getMainButton().addActionListener(TransitStopsActionListener);
        TransitStopsButton.setMainButtonSelectedStatus(false);
        jPanel3.add(ParkingLotsButton);
        ParkingLotsButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/ParkAndRide.png")).getImage());
        ParkingLotsButton.getMainButton().addActionListener(ParkingLotsActionListener);
        ParkingLotsButton.setMainButtonSelectedStatus(false);
        jPanel3.add(TollGantriesButton);
        TollGantriesButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/TollTag.png")).getImage());
        TollGantriesButton.getMainButton().addActionListener(TollGantriesActionListener);
        TollGantriesButton.setMainButtonSelectedStatus(false);
        jPanel3.add(MeasureOfPerformanceButton);
        MeasureOfPerformanceButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/MOP.png")).getImage());
        MeasureOfPerformanceButton.getMainButton().addActionListener(MOPActionListener);
        MeasureOfPerformanceButton.setMainButtonSelectedStatus(false);
        jPanel3.add(FacilitiesButton);
        IntersectionsButton.getMainButton().addActionListener(FacilitiesActionListener);
        IntersectionsButton.setMainButtonSelectedStatus(true);
        jPanel3.add(MovementsButton);
        MovementsButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Movements.png")).getImage());
        MovementsButton.getMainButton().addActionListener(MovementsActionListener);
        MovementsButton.setMainButtonSelectedStatus(false);
        jPanel3.add(CamerasButton);
        CamerasButton.setShowHideIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/OverViewPanel/Camera.png")).getImage());
        CamerasButton.getMainButton().addActionListener(CamerasActionListener);
        CamerasButton.setMainButtonSelectedStatus(false);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel39.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(jPanel39, "card3");
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private UI.OverviewPanel.OverviewButton CamerasButton;
    private UI.OverviewPanel.OverviewButton DMSButton;
    private UI.OverviewPanel.OverviewButton DetectorsButton;
    private UI.OverviewPanel.OverviewButton FacilitiesButton;
    public UI.OverviewPanel.OverviewHeaderButton HeaderButton;
    private UI.OverviewPanel.OverviewButton IncidentsButton;
    private UI.OverviewPanel.OverviewButton IntersectionsButton;
    private UI.OverviewPanel.OverviewButton LinksButton;
    private UI.OverviewPanel.OverviewButton MeasureOfPerformanceButton;
    private UI.OverviewPanel.OverviewButton MovementsButton;
    private UI.OverviewPanel.OverviewButton OD_PairsButton;
    private UI.OverviewPanel.OverviewButton ParkingLotsButton;
    private UI.OverviewPanel.OverviewButton SignalsButton;
    private UI.OverviewPanel.OverviewButton StopSignsButton;
    private UI.OverviewPanel.OverviewButton TollGantriesButton;
    private UI.OverviewPanel.OverviewButton TransitLinesButton;
    private UI.OverviewPanel.OverviewButton TransitStopsButton;
    private UI.OverviewPanel.OverviewButton ZonesButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private UI.SimulationPanel.Controls controls1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel39;
    // End of variables declaration//GEN-END:variables

    public synchronized void ResetView() {
        HeaderButton.setNavigationButtonSelectionStatus(true);
        CamerasButton.setShowHideButtonSelectedStatus(false);
        DMSButton.setShowHideButtonSelectedStatus(false);
        DetectorsButton.setShowHideButtonSelectedStatus(false);
        FacilitiesButton.setShowHideButtonSelectedStatus(false);
        IncidentsButton.setShowHideButtonSelectedStatus(false);
        IntersectionsButton.setShowHideButtonSelectedStatus(false);
        LinksButton.setShowHideButtonSelectedStatus(true);
        MeasureOfPerformanceButton.setShowHideButtonSelectedStatus(false);
        MovementsButton.setShowHideButtonSelectedStatus(false);
        OD_PairsButton.setShowHideButtonSelectedStatus(false);
        ParkingLotsButton.setShowHideButtonSelectedStatus(false);
        SignalsButton.setShowHideButtonSelectedStatus(false);
        StopSignsButton.setShowHideButtonSelectedStatus(false);
        TransitLinesButton.setShowHideButtonSelectedStatus(false);
        TransitStopsButton.setShowHideButtonSelectedStatus(false);
        ZonesButton.setShowHideButtonSelectedStatus(false);
        FacilitiesButton.setShowHideButtonSelectedStatus(false);
        TollGantriesButton.setShowHideButtonSelectedStatus(false);
    }
}
