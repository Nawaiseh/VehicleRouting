package UI.Utils;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author aalnawai
 */
public class TimePanel extends javax.swing.JPanel {

    public TimePanel() {
        initComponents();
        addMouseListener(MouseListener);
        addMouseMotionListener(MouseListener);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Coordinates5 = new javax.swing.JPanel();
        Time = new javax.swing.JLabel();
        SystemExecutionTime = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        Coordinates5.setMaximumSize(new java.awt.Dimension(150, 100));
        Coordinates5.setMinimumSize(new java.awt.Dimension(150, 100));
        Coordinates5.setName("Coordinates5"); // NOI18N
        Coordinates5.setOpaque(false);
        Coordinates5.setLayout(new java.awt.BorderLayout());

        Time.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        Time.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Time.setText("00:00:00 AM");
        Time.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Time.setName("Time"); // NOI18N
        Coordinates5.add(Time, java.awt.BorderLayout.NORTH);

        SystemExecutionTime.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        SystemExecutionTime.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        SystemExecutionTime.setText("Execution Time = 0 Seconds");
        SystemExecutionTime.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        SystemExecutionTime.setName("SystemExecutionTime"); // NOI18N
        Coordinates5.add(SystemExecutionTime, java.awt.BorderLayout.SOUTH);

        Date.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        Date.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Date.setText("Feb 12, 2012");
        Date.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Date.setName("Date"); // NOI18N
        Coordinates5.add(Date, java.awt.BorderLayout.CENTER);

        add(Coordinates5, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Coordinates5;
    public javax.swing.JLabel Date;
    public javax.swing.JLabel SystemExecutionTime;
    public javax.swing.JLabel Time;
    // End of variables declaration//GEN-END:variables
    MouseListener MouseListener = new MouseListener() {

         private Point Position= new Point(0, 0);

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
}
