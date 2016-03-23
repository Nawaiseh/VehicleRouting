package UI.SimulationPanel.Utils;

import UI.Utils.OpenGLUtils.Point3D;
import UI.SimulationPanel.GLRenderer;
import UI.Utils.OpenGLSimulationPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.TreeMap;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import Data.Node;
import Data.Link;


/**
 *
 * @author TRL
 */
public class FindPanel extends JDialog {

    public static final int RET_CANCEL = 0;
    public static final int RET_FIND = 1;
    private OpenGLSimulationPanel OpenGLSimulationPanel = null;
    private boolean ZoomItem = false;

    public FindPanel(JFrame parent, boolean modal, boolean newZoomItem, OpenGLSimulationPanel newOpenGLSimulationPanel) {
        super(parent, modal);
        OpenGLSimulationPanel = newOpenGLSimulationPanel;
        ZoomItem = newZoomItem;
        initComponents();
        setPreferredSize(new Dimension(500, 500));
        // Close the dialog when Esc is pressed
        String CancelName = "Cancel";
        String FindName = "Find";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CancelName);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), FindName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(CancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
        actionMap.put(FindName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClose(RET_FIND);
            }
        });
        super.setLocationRelativeTo(OpenGLSimulationPanel);
        ClearFoundItems();

        ID.requestFocus();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        NetworkItem = new javax.swing.JComboBox();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        ID = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        Name = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Items To Find"));
        jPanel7.setPreferredSize(new java.awt.Dimension(420, 70));
        jPanel7.setLayout(new java.awt.BorderLayout());

        NetworkItem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Intersection", "Link", "Zone" }));
        jPanel7.add(NetworkItem, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("By ID"));
        jPanel9.setPreferredSize(new java.awt.Dimension(12, 70));
        jPanel9.setLayout(new java.awt.BorderLayout());
        jPanel9.add(ID, java.awt.BorderLayout.CENTER);

        jPanel8.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("By Name"));
        jPanel11.setPreferredSize(new java.awt.Dimension(12, 70));
        jPanel11.setLayout(new java.awt.BorderLayout());

        Name.setEditable(false);
        jPanel11.add(Name, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel11, java.awt.BorderLayout.NORTH);

        jPanel8.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 40));
        jPanel2.setLayout(new java.awt.GridLayout(1, 5));

        jPanel4.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4);

        jPanel5.setOpaque(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5);

        jPanel6.setOpaque(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6);

        okButton.setText("Find");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel2.add(okButton);
        getRootPane().setDefaultButton(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel2.add(cancelButton);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        doClose(RET_FIND);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void doClose(int retStatus) {
        if (retStatus == RET_FIND) {
            String NetorkItemName = (String) NetworkItem.getSelectedItem();
            if (NetorkItemName.equalsIgnoreCase("Intersection")) {
                FindIntersection();
            } else if (NetorkItemName.equalsIgnoreCase("Link")) {
                FindLink();
            } else if (NetorkItemName.equalsIgnoreCase("Zone")) {
                FindZone();
            }
        }
        setVisible(false);
        dispose();
    }

    private void ClearFoundItems() {
        GLRenderer GLRenderer = (GLRenderer) OpenGLSimulationPanel.OpenGLRenderer;
        GLRenderer.SelectedNodes.clear();
        GLRenderer.SelectedLinks.clear();
        GLRenderer.SelectedZones.clear();
    }

    private void FindIntersection() {
        GLRenderer GLRenderer = (GLRenderer) OpenGLSimulationPanel.OpenGLRenderer;
        TreeMap<Long, Node> Nodes = OpenGLSimulationPanel.Network.Nodes;
        Node Node = null;
        String NodeID = ID.getText();

        if (NodeID.length() > 0) {
            // NodeID = (NodeID.contains("n")) ? NodeID : String.format("n%s", NodeID);
            Node = Nodes.get(Long.parseLong(NodeID));
        }
        if (Node != null) {
            GLRenderer.SelectedNodes.put(Node.ID, Node.ID);
            Point3D Position = Node.OpenGLLocation;
            Point3D Center = OpenGLSimulationPanel.OpenGLRenderer.getOpenGLCamera().GetCenter();
            Point3D Eye = OpenGLSimulationPanel.OpenGLRenderer.getOpenGLCamera().GetEye();
            Center.X = Eye.X = Position.X;
            Center.Y = Eye.Y = Position.Y;
            if (ZoomItem) {
                Eye.Z = -3;
            }
        }

    }

    private void FindLink() {
        GLRenderer GLRenderer = (GLRenderer) OpenGLSimulationPanel.OpenGLRenderer;
        TreeMap<String, Link> Links = OpenGLSimulationPanel.Network.Links;
        TreeMap<Long, Node> Nodes = OpenGLSimulationPanel.Network.Nodes;
        Link Link = null;
        if (ID.getText().length() > 0) {
            Link = Links.get(Long.parseLong(ID.getText()));
        }
        if (Link != null) {
            Point3D Center = OpenGLSimulationPanel.OpenGLRenderer.getOpenGLCamera().GetCenter();
            Point3D Eye = OpenGLSimulationPanel.OpenGLRenderer.getOpenGLCamera().GetEye();
            GLRenderer.SelectedLinks.put(Link.ID, Link.ID);

            Point3D Position1 = Nodes.get(Link.UpStream).OpenGLLocation;
            Point3D Position2 = Nodes.get(Link.DownStream).OpenGLLocation;
            Point3D Position = new Point3D((Position1.X + Position2.X) / 2.0, (Position1.Y + Position2.Y) / 2.0, (Position1.Z + Position2.Z) / 2.0);
            Center.X = Eye.X = Position.X;
            Center.Y = Eye.Y = Position.Y;
            if (ZoomItem) {
                Eye.Z = -3;
            }
        }

    }

    private void FindZone() {
     

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//////////        try {
//////////            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//////////                if ("Nimbus".equals(info.getName())) {
//////////                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//////////                    break;
//////////                }
//////////            }
//////////        } catch (ClassNotFoundException ex) {
//////////            java.util.logging.Logger.getLogger(FindPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//////////        } catch (InstantiationException ex) {
//////////            java.util.logging.Logger.getLogger(FindPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//////////        } catch (IllegalAccessException ex) {
//////////            java.util.logging.Logger.getLogger(FindPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//////////        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//////////            java.util.logging.Logger.getLogger(FindPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//////////        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FindPanel dialog = new FindPanel(new JFrame(), true, true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.JTextField Name;
    private javax.swing.JComboBox NetworkItem;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = RET_CANCEL;
}
