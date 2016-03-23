package UI.Utils;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author aalnawai
 */
public class CollapsablePanel extends javax.swing.JPanel {

    public enum Closable {

        Enabled, Disabled, Hide
    }

    public enum Draggable {

        Enabled, Disabled
    }
    private ImageIcon HoverCloseIcon = new ImageIcon(getClass().getResource("/Resources/Close/CloseHoverCircle.png"));
    private ImageIcon CloseIcon = new ImageIcon(getClass().getResource("/Resources/Close/Close.png"));
    private Component ParentComponent = null;
    private CollapsablePanel This = this;
    public Closable ClosableState = Closable.Disabled;
    private Draggable DraggableState = Draggable.Disabled;
    protected int RefreshRate = 1000; // 1 Second
    protected ActionListener RefreshUpdater = new ActionListener() {
        @Override
        public synchronized void actionPerformed(ActionEvent evt) {
            Refresh();
        }
    };
    protected Timer RefreshTimer = new Timer(RefreshRate, RefreshUpdater);

    public CollapsablePanel() {
        initComponents();
        InitTaskPane();
    }

    public CollapsablePanel(Closable newClosableState, Draggable newDraggableState) {
        initComponents();
        ClosableState = newClosableState;
        DraggableState = newDraggableState;
        InitTaskPane();
    }

    private void InitTaskPane() {
        Icon Icon = (ClosableState == Closable.Enabled || ClosableState == Closable.Hide) ? TaskPane.getIcon() : null;
        TaskPane.setIcon(Icon);
        if (ClosableState == Closable.Enabled || ClosableState == Closable.Hide) {
            TaskPane.addMouseListener(MouseListener);
            TaskPane.addMouseMotionListener(MouseListener);
        }
    }

    protected void Refresh() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TaskPaneContainer = new JXTaskPaneContainer();
        TaskPane = new JXTaskPane();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        TaskPaneContainer.setBorder(null);
        TaskPaneContainer.setName("TaskPaneContainer"); // NOI18N
        TaskPaneContainer.setOpaque(false);

        TaskPane.setAnimated(false);
        TaskPane.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TaskPane.setIcon(CloseIcon);
        TaskPane.setName("TaskPane"); // NOI18N
        TaskPane.setOpaque(false);
        TaskPaneContainer.add(TaskPane);

        add(TaskPaneContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public org.jdesktop.swingx.JXTaskPane TaskPane;
    public org.jdesktop.swingx.JXTaskPaneContainer TaskPaneContainer;
    // End of variables declaration//GEN-END:variables

    public Rectangle GetCloseBounds() {
        return new Rectangle(1, 1, 16, 16);
    }
    MouseListener MouseListener = new MouseListener() {
        private Point Position = new Point();
        private boolean Dragged = false;
        private boolean CollapseState = false;

        @Override
        public void mouseClicked(MouseEvent MouseEvent) {
            if (ClosableState == Closable.Enabled || ClosableState == Closable.Hide) {
                JPanel Panel = (JPanel) getParent();
                int X = MouseEvent.getX();
                int Y = MouseEvent.getY();
                Rectangle CloseBounds = GetCloseBounds();
                if (CloseBounds.contains(X, Y)) {
                    if (ClosableState == Closable.Enabled) {
                        TaskPane.removeMouseListener(MouseListener);
                        TaskPane.removeMouseMotionListener(MouseListener);
                        Panel.remove(This);
                    } else if (ClosableState == Closable.Hide) {
                        setVisible(false);
                    }
                    Panel.revalidate();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent MouseEvent) {
            Position.x = MouseEvent.getXOnScreen();
            Position.y = MouseEvent.getYOnScreen();
        }

        @Override
        public void mouseReleased(MouseEvent MouseEvent) {
            if (Dragged) {
                TaskPane.setCollapsed(CollapseState);
                Dragged = false;
            }
        }

        @Override
        public void mouseDragged(MouseEvent MouseEvent) {
            int dx = Position.x - MouseEvent.getXOnScreen();
            int dy = Position.y - MouseEvent.getYOnScreen();
            CollapseState = TaskPane.isCollapsed();
            Dragged = true;
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
            if (ClosableState == Closable.Enabled || ClosableState == Closable.Hide) {
                Rectangle CloseBounds = GetCloseBounds();
                int X = MouseEvent.getX();
                int Y = MouseEvent.getY();
                if (CloseBounds.contains(X, Y)) {
                    TaskPane.setIcon(HoverCloseIcon); // NOI18N
                } else {
                    TaskPane.setIcon(CloseIcon); // NOI18N

                }
            }
        }
    };
}
