package UI.SimulationPanel.Utils;

import UI.UIListner;
import UI.Utils.MouseListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author aalnawai
 */
public class TravelersStatistics extends javax.swing.JPanel {

    public enum RunningMode {

        Prediction, Estimation
    }
    private static Dimension IconSize = new Dimension(25, 25);
    private ImageIcon ShrinkIcon = UIListner.createResizedIcon(new ImageIcon(getClass().getResource("/Resources/Shrink.png")).getImage(), IconSize);
    private ImageIcon ExpandIcon = UIListner.createResizedIcon(new ImageIcon(getClass().getResource("/Resources/Expand.png")).getImage(), IconSize);
    //  private ImageIcon ExpandIcon = new ImageIcon(getClass().getResource("/Resources/Expand.png"));
    public boolean IsShrinked = false;
    public Dimension ExpandSize = new Dimension(200, 140);
    public Dimension ShrinkSize = new Dimension(50, 50);

    public TravelersStatistics() {
        initComponents();
        addMouseListener(MouseListener);
        addMouseMotionListener(MouseListener);

        ShrinkExpand.setIcon(ShrinkIcon);
        ShrinkExpand.addMouseListener(ShrinkExpandMouseListener);

    }

    public void SetMode(RunningMode runningMode) {
        if (runningMode == RunningMode.Estimation) {
            BodyPanel.remove(StartingPanel);
            BodyPanel.revalidate();
            BodyPanel.repaint();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BodyPanel = new javax.swing.JPanel();
        StartingPanel = new javax.swing.JPanel();
        StartingNumberOfTravelers3 = new javax.swing.JLabel();
        StartingNumberOfTravelers = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        StartingNumberOfTravelers2 = new javax.swing.JLabel();
        LoadedNumberOftravelers = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        LoadedNumberOftravelers1 = new javax.swing.JLabel();
        ExitedNumberOfTravelers = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        RemainingLoadedNumberOftravelers1 = new javax.swing.JLabel();
        RemainingNumberOfTravelers = new javax.swing.JLabel();
        TitlePanel = new javax.swing.JPanel();
        TitleLabel = new javax.swing.JLabel();
        ShrinkExpand = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        BodyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        BodyPanel.setName("BodyPanel"); // NOI18N
        BodyPanel.setOpaque(false);
        BodyPanel.setLayout(new java.awt.GridLayout(0, 1));

        StartingPanel.setName("StartingPanel"); // NOI18N
        StartingPanel.setOpaque(false);
        StartingPanel.setLayout(new java.awt.BorderLayout());

        StartingNumberOfTravelers3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        StartingNumberOfTravelers3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        StartingNumberOfTravelers3.setText("Starting:  ");
        StartingNumberOfTravelers3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        StartingNumberOfTravelers3.setName("StartingNumberOfTravelers3"); // NOI18N
        StartingNumberOfTravelers3.setPreferredSize(new java.awt.Dimension(70, 30));
        StartingPanel.add(StartingNumberOfTravelers3, java.awt.BorderLayout.WEST);

        StartingNumberOfTravelers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        StartingNumberOfTravelers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StartingNumberOfTravelers.setText("0");
        StartingNumberOfTravelers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        StartingNumberOfTravelers.setName("StartingNumberOfTravelers"); // NOI18N
        StartingNumberOfTravelers.setPreferredSize(new java.awt.Dimension(30, 25));
        StartingPanel.add(StartingNumberOfTravelers, java.awt.BorderLayout.CENTER);

        BodyPanel.add(StartingPanel);

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.BorderLayout());

        StartingNumberOfTravelers2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        StartingNumberOfTravelers2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        StartingNumberOfTravelers2.setText("Loaded:  ");
        StartingNumberOfTravelers2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        StartingNumberOfTravelers2.setName("StartingNumberOfTravelers2"); // NOI18N
        StartingNumberOfTravelers2.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel5.add(StartingNumberOfTravelers2, java.awt.BorderLayout.WEST);

        LoadedNumberOftravelers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        LoadedNumberOftravelers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LoadedNumberOftravelers.setText("0");
        LoadedNumberOftravelers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoadedNumberOftravelers.setName("LoadedNumberOftravelers"); // NOI18N
        LoadedNumberOftravelers.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel5.add(LoadedNumberOftravelers, java.awt.BorderLayout.CENTER);

        BodyPanel.add(jPanel5);

        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.BorderLayout());

        LoadedNumberOftravelers1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        LoadedNumberOftravelers1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        LoadedNumberOftravelers1.setText("Exited:  ");
        LoadedNumberOftravelers1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LoadedNumberOftravelers1.setName("LoadedNumberOftravelers1"); // NOI18N
        LoadedNumberOftravelers1.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel6.add(LoadedNumberOftravelers1, java.awt.BorderLayout.WEST);

        ExitedNumberOfTravelers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ExitedNumberOfTravelers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExitedNumberOfTravelers.setText("0");
        ExitedNumberOfTravelers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ExitedNumberOfTravelers.setName("ExitedNumberOfTravelers"); // NOI18N
        ExitedNumberOfTravelers.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel6.add(ExitedNumberOfTravelers, java.awt.BorderLayout.CENTER);

        BodyPanel.add(jPanel6);

        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.BorderLayout());

        RemainingLoadedNumberOftravelers1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RemainingLoadedNumberOftravelers1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        RemainingLoadedNumberOftravelers1.setText("Remaining:  ");
        RemainingLoadedNumberOftravelers1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RemainingLoadedNumberOftravelers1.setName("RemainingLoadedNumberOftravelers1"); // NOI18N
        RemainingLoadedNumberOftravelers1.setPreferredSize(new java.awt.Dimension(70, 30));
        jPanel7.add(RemainingLoadedNumberOftravelers1, java.awt.BorderLayout.WEST);

        RemainingNumberOfTravelers.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RemainingNumberOfTravelers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RemainingNumberOfTravelers.setText("0");
        RemainingNumberOfTravelers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        RemainingNumberOfTravelers.setName("RemainingNumberOfTravelers"); // NOI18N
        RemainingNumberOfTravelers.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel7.add(RemainingNumberOfTravelers, java.awt.BorderLayout.CENTER);

        BodyPanel.add(jPanel7);

        add(BodyPanel, java.awt.BorderLayout.CENTER);

        TitlePanel.setName("TitlePanel"); // NOI18N
        TitlePanel.setOpaque(false);
        TitlePanel.setLayout(new java.awt.BorderLayout());

        TitleLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("Number of Travelers");
        TitleLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        TitleLabel.setName("TitleLabel"); // NOI18N
        TitleLabel.setPreferredSize(new java.awt.Dimension(30, 30));
        TitlePanel.add(TitleLabel, java.awt.BorderLayout.CENTER);

        ShrinkExpand.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ShrinkExpand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShrinkExpand.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ShrinkExpand.setName("ShrinkExpand"); // NOI18N
        ShrinkExpand.setPreferredSize(new java.awt.Dimension(30, 30));
        TitlePanel.add(ShrinkExpand, java.awt.BorderLayout.EAST);

        add(TitlePanel, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BodyPanel;
    public javax.swing.JLabel ExitedNumberOfTravelers;
    public javax.swing.JLabel LoadedNumberOftravelers;
    private javax.swing.JLabel LoadedNumberOftravelers1;
    private javax.swing.JLabel RemainingLoadedNumberOftravelers1;
    public javax.swing.JLabel RemainingNumberOfTravelers;
    private javax.swing.JLabel ShrinkExpand;
    public javax.swing.JLabel StartingNumberOfTravelers;
    private javax.swing.JLabel StartingNumberOfTravelers2;
    private javax.swing.JLabel StartingNumberOfTravelers3;
    private javax.swing.JPanel StartingPanel;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables

    public void SwitchTravelrsLayout(boolean Estimate) {
        int H = 28;
        if (Estimate) {
            BodyPanel.remove(StartingPanel);
            BodyPanel.revalidate();
            BodyPanel.repaint();
        }
    }
    MouseListener MouseListener = new MouseListener() {

        private Point Position = new Point();

        @Override
        public void mousePressed(MouseEvent MouseEvent) {
            Position.x = MouseEvent.getXOnScreen();
            Position.y = MouseEvent.getYOnScreen();
        }

        @Override
        public void mouseDragged(MouseEvent MouseEvent) {
            int dx = Position.x - MouseEvent.getXOnScreen();
            int dy = Position.y - MouseEvent.getYOnScreen();
            Rectangle bound = getBounds();
            bound.x -= dx;
            bound.y -= dy;
            setBounds(bound);
            Position.x = MouseEvent.getXOnScreen();
            Position.y = MouseEvent.getYOnScreen();
        }
    };
    MouseListener ShrinkExpandMouseListener = new MouseListener() {

        public Dimension ExpandSize = new Dimension(200, 140);
        public Dimension ShrinkSize = new Dimension(50, 50);

        @Override
        public void mouseClicked(MouseEvent MouseEvent) {

            IsShrinked = !IsShrinked;
            Rectangle Bounds = getBounds();
            if (IsShrinked) {
                setBounds(Bounds.x + 150, Bounds.y, ShrinkSize.width, ShrinkSize.height);
                remove(BodyPanel);
                TitlePanel.remove(TitleLabel);
                ShrinkExpand.setIcon(ExpandIcon);
            } else {
                setBounds(Bounds.x - 150, Bounds.y, ExpandSize.width, ExpandSize.height);
                add(BodyPanel, BorderLayout.CENTER);
                TitlePanel.add(TitleLabel, BorderLayout.CENTER);
                ShrinkExpand.setIcon(ShrinkIcon);
            }
            revalidate();
            TitlePanel.revalidate();
        }
    };
}
