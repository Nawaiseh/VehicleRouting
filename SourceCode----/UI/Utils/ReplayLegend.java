package UI.Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author aalnawai
 */
public class ReplayLegend extends javax.swing.JPanel {

    public enum LegendStyleMode {

        LevelOfService, Speed, Queue, Vehicles
    }
    public LegendStyleMode ReplayStyle = LegendStyleMode.LevelOfService;
    public boolean IsShrinked = false;
    public Dimension ExpandSize = new Dimension(200, 140);
    public Dimension ShrinkSize = new Dimension(50, 50);

    public ReplayLegend() {
        initComponents();
        addMouseListener(MouseListener);
        addMouseMotionListener(MouseListener);
    }

   

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        ModeGroup = new javax.swing.ButtonGroup();
        SpeedPanel = new javax.swing.JPanel();
        SpeedGreenPanel = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanelN24 = new javax.swing.JPanel();
        jPanelS24 = new javax.swing.JPanel();
        jPanel1W24 = new javax.swing.JPanel();
        jPanelE24 = new javax.swing.JPanel();
        jPanelCenter24 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        SpeedYellowPanel = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanelN25 = new javax.swing.JPanel();
        jPanelS25 = new javax.swing.JPanel();
        jPanel1W25 = new javax.swing.JPanel();
        jPanelE25 = new javax.swing.JPanel();
        jPanelCenter26 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        SpeedOrangePanel = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanelN26 = new javax.swing.JPanel();
        jPanelS26 = new javax.swing.JPanel();
        jPanel1W26 = new javax.swing.JPanel();
        jPanelE26 = new javax.swing.JPanel();
        jPanelCenter28 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        SpeedRedPanel = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanelN27 = new javax.swing.JPanel();
        jPanelS27 = new javax.swing.JPanel();
        jPanel1W27 = new javax.swing.JPanel();
        jPanelE27 = new javax.swing.JPanel();
        jPanelCenter30 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        QueuePanel = new javax.swing.JPanel();
        jPanelCenter43 = new javax.swing.JPanel();
        jPanelCenter40 = new javax.swing.JPanel();
        jPanelCenter41 = new javax.swing.JPanel();
        VehiclesPanel = new javax.swing.JPanel();
        jPanelCenter42 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        Container = new javax.swing.JPanel();
        LOSPanel = new javax.swing.JPanel();
        SpeedGreenPanel1 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanelN28 = new javax.swing.JPanel();
        jPanelS28 = new javax.swing.JPanel();
        jPanel1W28 = new javax.swing.JPanel();
        jPanelE28 = new javax.swing.JPanel();
        jPanelCenter25 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        SpeedYellowPanel1 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jPanelN29 = new javax.swing.JPanel();
        jPanelS29 = new javax.swing.JPanel();
        jPanel1W29 = new javax.swing.JPanel();
        jPanelE29 = new javax.swing.JPanel();
        jPanelCenter27 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        SpeedOrangePanel1 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanelN30 = new javax.swing.JPanel();
        jPanelS30 = new javax.swing.JPanel();
        jPanel1W30 = new javax.swing.JPanel();
        jPanelE30 = new javax.swing.JPanel();
        jPanelCenter29 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        SpeedRedPanel1 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanelN31 = new javax.swing.JPanel();
        jPanelS31 = new javax.swing.JPanel();
        jPanel1W31 = new javax.swing.JPanel();
        jPanelE31 = new javax.swing.JPanel();
        jPanelCenter31 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();

        SpeedPanel.setName("SpeedPanel"); // NOI18N
        SpeedPanel.setOpaque(false);
        SpeedPanel.setLayout(new java.awt.GridLayout(0, 1));

        SpeedGreenPanel.setMaximumSize(new java.awt.Dimension(136, 25));
        SpeedGreenPanel.setMinimumSize(new java.awt.Dimension(136, 25));
        SpeedGreenPanel.setName("SpeedGreenPanel"); // NOI18N
        SpeedGreenPanel.setOpaque(false);
        SpeedGreenPanel.setPreferredSize(new java.awt.Dimension(136, 25));
        SpeedGreenPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel27.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel27.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel27.setName("jPanel27"); // NOI18N
        jPanel27.setOpaque(false);
        jPanel27.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel27.setLayout(new java.awt.BorderLayout());

        jPanelN24.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelN24.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelN24.setName("jPanelN24"); // NOI18N
        jPanelN24.setOpaque(false);
        jPanelN24.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelN24.setLayout(new java.awt.BorderLayout());
        jPanel27.add(jPanelN24, java.awt.BorderLayout.NORTH);

        jPanelS24.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelS24.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelS24.setName("jPanelS24"); // NOI18N
        jPanelS24.setOpaque(false);
        jPanelS24.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelS24.setLayout(new java.awt.BorderLayout());
        jPanel27.add(jPanelS24, java.awt.BorderLayout.SOUTH);

        jPanel1W24.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel1W24.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel1W24.setName("jPanel1W24"); // NOI18N
        jPanel1W24.setOpaque(false);
        jPanel1W24.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1W24.setLayout(new java.awt.BorderLayout());
        jPanel27.add(jPanel1W24, java.awt.BorderLayout.WEST);

        jPanelE24.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelE24.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelE24.setName("jPanelE24"); // NOI18N
        jPanelE24.setOpaque(false);
        jPanelE24.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelE24.setLayout(new java.awt.BorderLayout());
        jPanel27.add(jPanelE24, java.awt.BorderLayout.EAST);

        jPanelCenter24.setBackground(new java.awt.Color(0, 255, 0));
        jPanelCenter24.setName("jPanelCenter24"); // NOI18N
        jPanelCenter24.setLayout(new java.awt.BorderLayout());
        jPanel27.add(jPanelCenter24, java.awt.BorderLayout.CENTER);

        SpeedGreenPanel.add(jPanel27);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("  > 60");
        jLabel10.setName("jLabel10"); // NOI18N
        SpeedGreenPanel.add(jLabel10);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("mph");
        jLabel21.setName("jLabel21"); // NOI18N
        SpeedGreenPanel.add(jLabel21);

        SpeedPanel.add(SpeedGreenPanel);

        SpeedYellowPanel.setMaximumSize(new java.awt.Dimension(136, 25));
        SpeedYellowPanel.setMinimumSize(new java.awt.Dimension(136, 25));
        SpeedYellowPanel.setName("SpeedYellowPanel"); // NOI18N
        SpeedYellowPanel.setOpaque(false);
        SpeedYellowPanel.setPreferredSize(new java.awt.Dimension(136, 25));
        SpeedYellowPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel28.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel28.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel28.setName("jPanel28"); // NOI18N
        jPanel28.setOpaque(false);
        jPanel28.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel28.setLayout(new java.awt.BorderLayout());

        jPanelN25.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelN25.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelN25.setName("jPanelN25"); // NOI18N
        jPanelN25.setOpaque(false);
        jPanelN25.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelN25.setLayout(new java.awt.BorderLayout());
        jPanel28.add(jPanelN25, java.awt.BorderLayout.NORTH);

        jPanelS25.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelS25.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelS25.setName("jPanelS25"); // NOI18N
        jPanelS25.setOpaque(false);
        jPanelS25.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelS25.setLayout(new java.awt.BorderLayout());
        jPanel28.add(jPanelS25, java.awt.BorderLayout.SOUTH);

        jPanel1W25.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel1W25.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel1W25.setName("jPanel1W25"); // NOI18N
        jPanel1W25.setOpaque(false);
        jPanel1W25.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1W25.setLayout(new java.awt.BorderLayout());
        jPanel28.add(jPanel1W25, java.awt.BorderLayout.WEST);

        jPanelE25.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelE25.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelE25.setName("jPanelE25"); // NOI18N
        jPanelE25.setOpaque(false);
        jPanelE25.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelE25.setLayout(new java.awt.BorderLayout());
        jPanel28.add(jPanelE25, java.awt.BorderLayout.EAST);

        jPanelCenter26.setBackground(new java.awt.Color(255, 255, 0));
        jPanelCenter26.setName("jPanelCenter26"); // NOI18N
        jPanelCenter26.setLayout(new java.awt.BorderLayout());
        jPanel28.add(jPanelCenter26, java.awt.BorderLayout.CENTER);

        SpeedYellowPanel.add(jPanel28);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("60 - 40");
        jLabel11.setName("jLabel11"); // NOI18N
        SpeedYellowPanel.add(jLabel11);

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("mph");
        jLabel25.setName("jLabel25"); // NOI18N
        SpeedYellowPanel.add(jLabel25);

        SpeedPanel.add(SpeedYellowPanel);

        SpeedOrangePanel.setMaximumSize(new java.awt.Dimension(136, 25));
        SpeedOrangePanel.setMinimumSize(new java.awt.Dimension(136, 25));
        SpeedOrangePanel.setName("SpeedOrangePanel"); // NOI18N
        SpeedOrangePanel.setOpaque(false);
        SpeedOrangePanel.setPreferredSize(new java.awt.Dimension(136, 25));
        SpeedOrangePanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel29.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel29.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel29.setName("jPanel29"); // NOI18N
        jPanel29.setOpaque(false);
        jPanel29.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel29.setLayout(new java.awt.BorderLayout());

        jPanelN26.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelN26.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelN26.setName("jPanelN26"); // NOI18N
        jPanelN26.setOpaque(false);
        jPanelN26.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelN26.setLayout(new java.awt.BorderLayout());
        jPanel29.add(jPanelN26, java.awt.BorderLayout.NORTH);

        jPanelS26.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelS26.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelS26.setName("jPanelS26"); // NOI18N
        jPanelS26.setOpaque(false);
        jPanelS26.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelS26.setLayout(new java.awt.BorderLayout());
        jPanel29.add(jPanelS26, java.awt.BorderLayout.SOUTH);

        jPanel1W26.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel1W26.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel1W26.setName("jPanel1W26"); // NOI18N
        jPanel1W26.setOpaque(false);
        jPanel1W26.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1W26.setLayout(new java.awt.BorderLayout());
        jPanel29.add(jPanel1W26, java.awt.BorderLayout.WEST);

        jPanelE26.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelE26.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelE26.setName("jPanelE26"); // NOI18N
        jPanelE26.setOpaque(false);
        jPanelE26.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelE26.setLayout(new java.awt.BorderLayout());
        jPanel29.add(jPanelE26, java.awt.BorderLayout.EAST);

        jPanelCenter28.setBackground(new java.awt.Color(255, 127, 0));
        jPanelCenter28.setName("jPanelCenter28"); // NOI18N
        jPanelCenter28.setLayout(new java.awt.BorderLayout());
        jPanel29.add(jPanelCenter28, java.awt.BorderLayout.CENTER);

        SpeedOrangePanel.add(jPanel29);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("40 - 20");
        jLabel12.setName("jLabel12"); // NOI18N
        SpeedOrangePanel.add(jLabel12);

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("mph");
        jLabel26.setName("jLabel26"); // NOI18N
        SpeedOrangePanel.add(jLabel26);

        SpeedPanel.add(SpeedOrangePanel);

        SpeedRedPanel.setMaximumSize(new java.awt.Dimension(136, 25));
        SpeedRedPanel.setMinimumSize(new java.awt.Dimension(136, 25));
        SpeedRedPanel.setName("SpeedRedPanel"); // NOI18N
        SpeedRedPanel.setOpaque(false);
        SpeedRedPanel.setPreferredSize(new java.awt.Dimension(136, 25));
        SpeedRedPanel.setLayout(new java.awt.GridLayout(1, 0));

        jPanel30.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel30.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel30.setName("jPanel30"); // NOI18N
        jPanel30.setOpaque(false);
        jPanel30.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jPanelN27.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelN27.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelN27.setName("jPanelN27"); // NOI18N
        jPanelN27.setOpaque(false);
        jPanelN27.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelN27.setLayout(new java.awt.BorderLayout());
        jPanel30.add(jPanelN27, java.awt.BorderLayout.NORTH);

        jPanelS27.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelS27.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelS27.setName("jPanelS27"); // NOI18N
        jPanelS27.setOpaque(false);
        jPanelS27.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelS27.setLayout(new java.awt.BorderLayout());
        jPanel30.add(jPanelS27, java.awt.BorderLayout.SOUTH);

        jPanel1W27.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel1W27.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel1W27.setName("jPanel1W27"); // NOI18N
        jPanel1W27.setOpaque(false);
        jPanel1W27.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1W27.setLayout(new java.awt.BorderLayout());
        jPanel30.add(jPanel1W27, java.awt.BorderLayout.WEST);

        jPanelE27.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelE27.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelE27.setName("jPanelE27"); // NOI18N
        jPanelE27.setOpaque(false);
        jPanelE27.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelE27.setLayout(new java.awt.BorderLayout());
        jPanel30.add(jPanelE27, java.awt.BorderLayout.EAST);

        jPanelCenter30.setBackground(new java.awt.Color(255, 0, 0));
        jPanelCenter30.setName("jPanelCenter30"); // NOI18N
        jPanelCenter30.setLayout(new java.awt.BorderLayout());
        jPanel30.add(jPanelCenter30, java.awt.BorderLayout.CENTER);

        SpeedRedPanel.add(jPanel30);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("  < 20");
        jLabel13.setName("jLabel13"); // NOI18N
        SpeedRedPanel.add(jLabel13);

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("mph");
        jLabel27.setName("jLabel27"); // NOI18N
        SpeedRedPanel.add(jLabel27);

        SpeedPanel.add(SpeedRedPanel);

        QueuePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        QueuePanel.setMaximumSize(new java.awt.Dimension(0, 35));
        QueuePanel.setMinimumSize(new java.awt.Dimension(0, 35));
        QueuePanel.setName("QueuePanel"); // NOI18N
        QueuePanel.setOpaque(false);
        QueuePanel.setPreferredSize(new java.awt.Dimension(50, 30));
        QueuePanel.setLayout(new java.awt.GridLayout(0, 1));

        jPanelCenter43.setName("jPanelCenter43"); // NOI18N
        jPanelCenter43.setOpaque(false);
        jPanelCenter43.setLayout(new java.awt.BorderLayout());
        QueuePanel.add(jPanelCenter43);

        jPanelCenter40.setBackground(new Color(100,200,255));
        jPanelCenter40.setName("jPanelCenter40"); // NOI18N
        jPanelCenter40.setLayout(new java.awt.BorderLayout());
        QueuePanel.add(jPanelCenter40);

        jPanelCenter41.setName("jPanelCenter41"); // NOI18N
        jPanelCenter41.setOpaque(false);
        jPanelCenter41.setLayout(new java.awt.BorderLayout());
        QueuePanel.add(jPanelCenter41);

        VehiclesPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        VehiclesPanel.setMaximumSize(new java.awt.Dimension(0, 35));
        VehiclesPanel.setMinimumSize(new java.awt.Dimension(0, 35));
        VehiclesPanel.setName("VehiclesPanel"); // NOI18N
        VehiclesPanel.setOpaque(false);
        VehiclesPanel.setPreferredSize(new java.awt.Dimension(50, 30));
        VehiclesPanel.setLayout(new java.awt.GridLayout(0, 1));

        jPanelCenter42.setName("jPanelCenter42"); // NOI18N
        jPanelCenter42.setOpaque(false);
        jPanelCenter42.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Not Implemented");
        jLabel1.setName("jLabel1"); // NOI18N
        jPanelCenter42.add(jLabel1, java.awt.BorderLayout.CENTER);

        VehiclesPanel.add(jPanelCenter42);

        setBackground(new Color(10,10,10,0));
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(4, 40));
        jPanel5.setLayout(new java.awt.GridLayout(0, 1));

        jPanel10.setName("jPanel10"); // NOI18N
        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(4, 40));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LevelOfService", "Speed", "Queue", "Vehicles" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel10.add(jComboBox1);

        jPanel5.add(jPanel10);

        add(jPanel5, java.awt.BorderLayout.NORTH);

        Container.setName("Container"); // NOI18N
        Container.setOpaque(false);
        Container.setLayout(new java.awt.GridLayout(1, 0));

        LOSPanel.setName("LOSPanel"); // NOI18N
        LOSPanel.setOpaque(false);
        LOSPanel.setLayout(new java.awt.GridLayout(0, 1));

        SpeedGreenPanel1.setMaximumSize(new java.awt.Dimension(136, 25));
        SpeedGreenPanel1.setMinimumSize(new java.awt.Dimension(136, 25));
        SpeedGreenPanel1.setName("SpeedGreenPanel1"); // NOI18N
        SpeedGreenPanel1.setOpaque(false);
        SpeedGreenPanel1.setPreferredSize(new java.awt.Dimension(136, 25));
        SpeedGreenPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel31.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel31.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel31.setName("jPanel31"); // NOI18N
        jPanel31.setOpaque(false);
        jPanel31.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel31.setLayout(new java.awt.BorderLayout());

        jPanelN28.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelN28.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelN28.setName("jPanelN28"); // NOI18N
        jPanelN28.setOpaque(false);
        jPanelN28.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelN28.setLayout(new java.awt.BorderLayout());
        jPanel31.add(jPanelN28, java.awt.BorderLayout.NORTH);

        jPanelS28.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelS28.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelS28.setName("jPanelS28"); // NOI18N
        jPanelS28.setOpaque(false);
        jPanelS28.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelS28.setLayout(new java.awt.BorderLayout());
        jPanel31.add(jPanelS28, java.awt.BorderLayout.SOUTH);

        jPanel1W28.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel1W28.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel1W28.setName("jPanel1W28"); // NOI18N
        jPanel1W28.setOpaque(false);
        jPanel1W28.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1W28.setLayout(new java.awt.BorderLayout());
        jPanel31.add(jPanel1W28, java.awt.BorderLayout.WEST);

        jPanelE28.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelE28.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelE28.setName("jPanelE28"); // NOI18N
        jPanelE28.setOpaque(false);
        jPanelE28.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelE28.setLayout(new java.awt.BorderLayout());
        jPanel31.add(jPanelE28, java.awt.BorderLayout.EAST);

        jPanelCenter25.setBackground(new java.awt.Color(0, 255, 0));
        jPanelCenter25.setName("jPanelCenter25"); // NOI18N
        jPanelCenter25.setLayout(new java.awt.BorderLayout());
        jPanel31.add(jPanelCenter25, java.awt.BorderLayout.CENTER);

        SpeedGreenPanel1.add(jPanel31);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LOS   A");
        jLabel14.setName("jLabel14"); // NOI18N
        SpeedGreenPanel1.add(jLabel14);

        LOSPanel.add(SpeedGreenPanel1);

        SpeedYellowPanel1.setMaximumSize(new java.awt.Dimension(136, 25));
        SpeedYellowPanel1.setMinimumSize(new java.awt.Dimension(136, 25));
        SpeedYellowPanel1.setName("SpeedYellowPanel1"); // NOI18N
        SpeedYellowPanel1.setOpaque(false);
        SpeedYellowPanel1.setPreferredSize(new java.awt.Dimension(136, 25));
        SpeedYellowPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel32.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel32.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel32.setName("jPanel32"); // NOI18N
        jPanel32.setOpaque(false);
        jPanel32.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel32.setLayout(new java.awt.BorderLayout());

        jPanelN29.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelN29.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelN29.setName("jPanelN29"); // NOI18N
        jPanelN29.setOpaque(false);
        jPanelN29.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelN29.setLayout(new java.awt.BorderLayout());
        jPanel32.add(jPanelN29, java.awt.BorderLayout.NORTH);

        jPanelS29.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelS29.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelS29.setName("jPanelS29"); // NOI18N
        jPanelS29.setOpaque(false);
        jPanelS29.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelS29.setLayout(new java.awt.BorderLayout());
        jPanel32.add(jPanelS29, java.awt.BorderLayout.SOUTH);

        jPanel1W29.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel1W29.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel1W29.setName("jPanel1W29"); // NOI18N
        jPanel1W29.setOpaque(false);
        jPanel1W29.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1W29.setLayout(new java.awt.BorderLayout());
        jPanel32.add(jPanel1W29, java.awt.BorderLayout.WEST);

        jPanelE29.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelE29.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelE29.setName("jPanelE29"); // NOI18N
        jPanelE29.setOpaque(false);
        jPanelE29.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelE29.setLayout(new java.awt.BorderLayout());
        jPanel32.add(jPanelE29, java.awt.BorderLayout.EAST);

        jPanelCenter27.setBackground(new java.awt.Color(255, 255, 0));
        jPanelCenter27.setName("jPanelCenter27"); // NOI18N
        jPanelCenter27.setLayout(new java.awt.BorderLayout());
        jPanel32.add(jPanelCenter27, java.awt.BorderLayout.CENTER);

        SpeedYellowPanel1.add(jPanel32);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("LOS   B");
        jLabel15.setName("jLabel15"); // NOI18N
        SpeedYellowPanel1.add(jLabel15);

        LOSPanel.add(SpeedYellowPanel1);

        SpeedOrangePanel1.setMaximumSize(new java.awt.Dimension(136, 25));
        SpeedOrangePanel1.setMinimumSize(new java.awt.Dimension(136, 25));
        SpeedOrangePanel1.setName("SpeedOrangePanel1"); // NOI18N
        SpeedOrangePanel1.setOpaque(false);
        SpeedOrangePanel1.setPreferredSize(new java.awt.Dimension(136, 25));
        SpeedOrangePanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel33.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel33.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel33.setName("jPanel33"); // NOI18N
        jPanel33.setOpaque(false);
        jPanel33.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel33.setLayout(new java.awt.BorderLayout());

        jPanelN30.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelN30.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelN30.setName("jPanelN30"); // NOI18N
        jPanelN30.setOpaque(false);
        jPanelN30.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelN30.setLayout(new java.awt.BorderLayout());
        jPanel33.add(jPanelN30, java.awt.BorderLayout.NORTH);

        jPanelS30.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelS30.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelS30.setName("jPanelS30"); // NOI18N
        jPanelS30.setOpaque(false);
        jPanelS30.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelS30.setLayout(new java.awt.BorderLayout());
        jPanel33.add(jPanelS30, java.awt.BorderLayout.SOUTH);

        jPanel1W30.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel1W30.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel1W30.setName("jPanel1W30"); // NOI18N
        jPanel1W30.setOpaque(false);
        jPanel1W30.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1W30.setLayout(new java.awt.BorderLayout());
        jPanel33.add(jPanel1W30, java.awt.BorderLayout.WEST);

        jPanelE30.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelE30.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelE30.setName("jPanelE30"); // NOI18N
        jPanelE30.setOpaque(false);
        jPanelE30.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelE30.setLayout(new java.awt.BorderLayout());
        jPanel33.add(jPanelE30, java.awt.BorderLayout.EAST);

        jPanelCenter29.setBackground(new java.awt.Color(255, 127, 0));
        jPanelCenter29.setName("jPanelCenter29"); // NOI18N
        jPanelCenter29.setLayout(new java.awt.BorderLayout());
        jPanel33.add(jPanelCenter29, java.awt.BorderLayout.CENTER);

        SpeedOrangePanel1.add(jPanel33);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("LOS   C");
        jLabel16.setName("jLabel16"); // NOI18N
        SpeedOrangePanel1.add(jLabel16);

        LOSPanel.add(SpeedOrangePanel1);

        SpeedRedPanel1.setMaximumSize(new java.awt.Dimension(136, 25));
        SpeedRedPanel1.setMinimumSize(new java.awt.Dimension(136, 25));
        SpeedRedPanel1.setName("SpeedRedPanel1"); // NOI18N
        SpeedRedPanel1.setOpaque(false);
        SpeedRedPanel1.setPreferredSize(new java.awt.Dimension(136, 25));
        SpeedRedPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jPanel34.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel34.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel34.setName("jPanel34"); // NOI18N
        jPanel34.setOpaque(false);
        jPanel34.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel34.setLayout(new java.awt.BorderLayout());

        jPanelN31.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelN31.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelN31.setName("jPanelN31"); // NOI18N
        jPanelN31.setOpaque(false);
        jPanelN31.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelN31.setLayout(new java.awt.BorderLayout());
        jPanel34.add(jPanelN31, java.awt.BorderLayout.NORTH);

        jPanelS31.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelS31.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelS31.setName("jPanelS31"); // NOI18N
        jPanelS31.setOpaque(false);
        jPanelS31.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelS31.setLayout(new java.awt.BorderLayout());
        jPanel34.add(jPanelS31, java.awt.BorderLayout.SOUTH);

        jPanel1W31.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanel1W31.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanel1W31.setName("jPanel1W31"); // NOI18N
        jPanel1W31.setOpaque(false);
        jPanel1W31.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanel1W31.setLayout(new java.awt.BorderLayout());
        jPanel34.add(jPanel1W31, java.awt.BorderLayout.WEST);

        jPanelE31.setMaximumSize(new java.awt.Dimension(0, 35));
        jPanelE31.setMinimumSize(new java.awt.Dimension(0, 35));
        jPanelE31.setName("jPanelE31"); // NOI18N
        jPanelE31.setOpaque(false);
        jPanelE31.setPreferredSize(new java.awt.Dimension(5, 5));
        jPanelE31.setLayout(new java.awt.BorderLayout());
        jPanel34.add(jPanelE31, java.awt.BorderLayout.EAST);

        jPanelCenter31.setBackground(new java.awt.Color(255, 0, 0));
        jPanelCenter31.setName("jPanelCenter31"); // NOI18N
        jPanelCenter31.setLayout(new java.awt.BorderLayout());
        jPanel34.add(jPanelCenter31, java.awt.BorderLayout.CENTER);

        SpeedRedPanel1.add(jPanel34);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("LOS   D,E,F");
        jLabel17.setName("jLabel17"); // NOI18N
        SpeedRedPanel1.add(jLabel17);

        LOSPanel.add(SpeedRedPanel1);

        Container.add(LOSPanel);

        add(Container, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        ReplayStyle = LegendStyleMode.valueOf((String)jComboBox1.getSelectedItem());
        Container.removeAll();
        if (ReplayStyle == LegendStyleMode.LevelOfService){
            Container.add(LOSPanel);
        }else if (ReplayStyle == LegendStyleMode.Speed){
            Container.add(SpeedPanel);
        }else if (ReplayStyle == LegendStyleMode.Queue){
            Container.add(QueuePanel);
        }else if (ReplayStyle == LegendStyleMode.Vehicles){
            Container.add(VehiclesPanel);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    //  Direction[] directions = {Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT};
    //  int index = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JPanel LOSPanel;
    private javax.swing.ButtonGroup ModeGroup;
    private javax.swing.JPanel QueuePanel;
    private javax.swing.JPanel SpeedGreenPanel;
    private javax.swing.JPanel SpeedGreenPanel1;
    private javax.swing.JPanel SpeedOrangePanel;
    private javax.swing.JPanel SpeedOrangePanel1;
    private javax.swing.JPanel SpeedPanel;
    private javax.swing.JPanel SpeedRedPanel;
    private javax.swing.JPanel SpeedRedPanel1;
    private javax.swing.JPanel SpeedYellowPanel;
    private javax.swing.JPanel SpeedYellowPanel1;
    private javax.swing.JPanel VehiclesPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel1W24;
    private javax.swing.JPanel jPanel1W25;
    private javax.swing.JPanel jPanel1W26;
    private javax.swing.JPanel jPanel1W27;
    private javax.swing.JPanel jPanel1W28;
    private javax.swing.JPanel jPanel1W29;
    private javax.swing.JPanel jPanel1W30;
    private javax.swing.JPanel jPanel1W31;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelCenter24;
    private javax.swing.JPanel jPanelCenter25;
    private javax.swing.JPanel jPanelCenter26;
    private javax.swing.JPanel jPanelCenter27;
    private javax.swing.JPanel jPanelCenter28;
    private javax.swing.JPanel jPanelCenter29;
    private javax.swing.JPanel jPanelCenter30;
    private javax.swing.JPanel jPanelCenter31;
    private javax.swing.JPanel jPanelCenter40;
    private javax.swing.JPanel jPanelCenter41;
    private javax.swing.JPanel jPanelCenter42;
    private javax.swing.JPanel jPanelCenter43;
    private javax.swing.JPanel jPanelE24;
    private javax.swing.JPanel jPanelE25;
    private javax.swing.JPanel jPanelE26;
    private javax.swing.JPanel jPanelE27;
    private javax.swing.JPanel jPanelE28;
    private javax.swing.JPanel jPanelE29;
    private javax.swing.JPanel jPanelE30;
    private javax.swing.JPanel jPanelE31;
    private javax.swing.JPanel jPanelN24;
    private javax.swing.JPanel jPanelN25;
    private javax.swing.JPanel jPanelN26;
    private javax.swing.JPanel jPanelN27;
    private javax.swing.JPanel jPanelN28;
    private javax.swing.JPanel jPanelN29;
    private javax.swing.JPanel jPanelN30;
    private javax.swing.JPanel jPanelN31;
    private javax.swing.JPanel jPanelS24;
    private javax.swing.JPanel jPanelS25;
    private javax.swing.JPanel jPanelS26;
    private javax.swing.JPanel jPanelS27;
    private javax.swing.JPanel jPanelS28;
    private javax.swing.JPanel jPanelS29;
    private javax.swing.JPanel jPanelS30;
    private javax.swing.JPanel jPanelS31;
    // End of variables declaration//GEN-END:variables
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
//                if (((bound.x - dx) >= 0) && ((bound.y - dy) >= 0) && ((bound.x - dx + bound.width) <= OpenGLPanel.getBounds().width) && ((bound.y - dy + bound.height) <= OpenGLPanel.getBounds().height)) {
            bound.x -= dx;
            bound.y -= dy;
            setBounds(bound);
//                }
            Position.x = MouseEvent.getXOnScreen();
            Position.y = MouseEvent.getYOnScreen();
        }

        @Override
        public void mouseMoved(MouseEvent MouseEvent) {

            int X = MouseEvent.getX();
            int Y = MouseEvent.getY();


        }
    };

}
