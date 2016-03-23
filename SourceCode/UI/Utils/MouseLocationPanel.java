package UI.Utils;

import UI.UIListner;
import UI.Utils.OpenGLUtils.Point3D;
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
public class MouseLocationPanel extends javax.swing.JPanel {

    public Point3D MousePosition = new Point3D();
    private static Dimension IconSize = new Dimension(25, 25);
    private ImageIcon ShrinkIcon = UIListner.createResizedIcon(new ImageIcon(getClass().getResource("/Resources/Shrink.png")).getImage(), IconSize);
    private ImageIcon ExpandIcon = UIListner.createResizedIcon(new ImageIcon(getClass().getResource("/Resources/Expand.png")).getImage(), IconSize);
    public boolean IsShrinked = false;
    public Dimension ExpandSize = new Dimension(200, 50);
    public Dimension ShrinkSize = new Dimension(50, 50);

    public MouseLocationPanel() {
        initComponents();
        addMouseListener(MouseListener);
        addMouseMotionListener(MouseListener);

        ShrinkExpand.setIcon(ShrinkIcon);
        ShrinkExpand.addMouseListener(ShrinkExpandMouseListener);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Location = new javax.swing.JLabel();
        ShrinkExpand = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        Location.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Location.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Location.setText("(00.00 , 00.00)");
        Location.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Location.setName("Location"); // NOI18N
        add(Location, java.awt.BorderLayout.CENTER);

        ShrinkExpand.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ShrinkExpand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShrinkExpand.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ShrinkExpand.setName("ShrinkExpand"); // NOI18N
        ShrinkExpand.setPreferredSize(new java.awt.Dimension(30, 30));
        add(ShrinkExpand, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Location;
    private javax.swing.JLabel ShrinkExpand;
    // End of variables declaration//GEN-END:variables
    MouseListener MouseListener = new MouseListener() {

        private Point Position = new Point(0, 0);

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
    };
    MouseListener ShrinkExpandMouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent MouseEvent) {

            IsShrinked = !IsShrinked;
            Rectangle Bounds = getBounds();
            if (IsShrinked) {
                setBounds(Bounds.x + 150, Bounds.y, ShrinkSize.width, ShrinkSize.height);
                remove(Location);
                ShrinkExpand.setIcon(ExpandIcon);
            } else {
                setBounds(Bounds.x - 150, Bounds.y, ExpandSize.width, ExpandSize.height);
                add(Location, BorderLayout.CENTER);
                ShrinkExpand.setIcon(ShrinkIcon);
            }
            revalidate();
        }
    };
}
