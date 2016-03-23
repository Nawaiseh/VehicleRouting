/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OpenGLIntersectionDetails.java
 *
 * Created on Mar 27, 2012, 2:02:27 PM
 */
package UI.OverviewPanel;

import UI.UserInterface;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author aalnawai
 */
public class OverViewTaskPane extends javax.swing.JPanel {

    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~           Variables           ~~~~~~~~~~~~">
    private UserInterface DView = null;
    private OverviewPanel OverviewPanel = null;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~ OverViewTaskPane Constructors ~~~~~~~~~~~~">

    public OverViewTaskPane() {
        initComponents();
    }

    public OverViewTaskPane(UserInterface newDIRECTView) {
        DView = newDIRECTView;
        OverviewPanel = new OverviewPanel(DView, this);
        initComponents();
        TaskPane.add(OverviewPanel);
       // ListViewTaskPane.add(OverviewPanel.getIntersectionsTable());
    }

    public synchronized void setDefaultLayout() {
//          Component Component = ListViewTaskPane.getContentPane().getComponent(0);
//        Rectangle Bounds = OverviewPanel.getBounds();
//        Dimension Size = OverviewPanel.getSize();
//        Dimension ComponentSize = new Dimension(Bounds.width, Bounds.height);
//        if (TaskPane.isCollapsed()) {
//            Size.height = getBounds().height - 37;
//        } else {
//            Size.height = getBounds().height - Size.height - 58;
//        }
//        ComponentSize.height = Size.height - 38;
//        ListViewTaskPane.setPreferredSize(Size);
//        Component.setPreferredSize(ComponentSize);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~   Synchronized Set Methods    ~~~~~~~~~~~~">

    public synchronized void setOverviewPanel(OverviewPanel newOverviewPanel) {
        OverviewPanel = newOverviewPanel;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~   Synchronized Get Methods    ~~~~~~~~~~~~">

    public synchronized OverviewPanel getOverviewPanel() {
        return OverviewPanel;
    }

    public synchronized Rectangle getCloseBounds() {
        return new Rectangle(1, 1, 16, 16);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~            Dispose            ~~~~~~~~~~~~">

    public void Dispose() {
    }
    // </editor-fold>

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ListViewPanel = new javax.swing.JPanel();
        ListViewTaskPaneContainer = new org.jdesktop.swingx.JXTaskPaneContainer();
        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        TaskPane = new org.jdesktop.swingx.JXTaskPane();
        ListViewTaskPane = new org.jdesktop.swingx.JXTaskPane();

        ListViewPanel.setName("ListViewPanel"); // NOI18N
        ListViewPanel.setOpaque(false);
        ListViewPanel.setLayout(new java.awt.BorderLayout());

        ListViewTaskPaneContainer.setBorder(null);
        ListViewTaskPaneContainer.setName("ListViewTaskPaneContainer"); // NOI18N
        ListViewTaskPaneContainer.setOpaque(false);
        ListViewPanel.add(ListViewTaskPaneContainer, java.awt.BorderLayout.CENTER);

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jXTaskPaneContainer1.setBorder(null);
        jXTaskPaneContainer1.setName("jXTaskPaneContainer1"); // NOI18N
        jXTaskPaneContainer1.setOpaque(false);

        TaskPane.setAnimated(false);
        TaskPane.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        TaskPane.setName("TaskPane"); // NOI18N
        TaskPane.setSpecial(true);
        TaskPane.setTitle("Overview Buttons");
        TaskPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TaskPaneMouseReleased(evt);
            }
        });
        jXTaskPaneContainer1.add(TaskPane);

        ListViewTaskPane.setAnimated(false);
        ListViewTaskPane.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ListViewTaskPane.setName("ListViewTaskPane"); // NOI18N
        ListViewTaskPane.setTitle("Listview");
        jXTaskPaneContainer1.add(ListViewTaskPane);

        add(jXTaskPaneContainer1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void TaskPaneMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaskPaneMouseReleased
        Component Component = ListViewTaskPane.getContentPane().getComponent(0);
        Rectangle Bounds = OverviewPanel.getBounds();
        Dimension Size = OverviewPanel.getSize();
        Dimension ComponentSize = new Dimension(Bounds.width, Bounds.height);
        if (TaskPane.isCollapsed()) {
            Size.height = getBounds().height - 37;
        } else {
            Size.height = getBounds().height - Size.height - 58;
        }
        ComponentSize.height = Size.height - 38;
        ListViewTaskPane.setPreferredSize(Size);
        Component.setPreferredSize(ComponentSize);
    }//GEN-LAST:event_TaskPaneMouseReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ListViewPanel;
    public org.jdesktop.swingx.JXTaskPane ListViewTaskPane;
    private org.jdesktop.swingx.JXTaskPaneContainer ListViewTaskPaneContainer;
    public org.jdesktop.swingx.JXTaskPane TaskPane;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    // End of variables declaration//GEN-END:variables
}
