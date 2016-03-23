package UI.Utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author TRL@SMU
 */
public class Clock extends javax.swing.JPanel {

    JMenuItem Digital = new JMenuItem("Digital");
    JMenuItem Analog = new JMenuItem("Analog");
    JMenu Menu = new JMenu("Clock Style");
    Dimension AnalogSize = new Dimension(170, 170);
    Dimension DigitalSize = new Dimension(200, 85);

    public Clock() {
        initComponents();
        addMouseListener(MouseListener);
        addMouseMotionListener(MouseListener);
        Menu.add(Digital);
        Menu.add(Analog);
        ClockStyleMenu.add(Menu);
        Digital.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(DigitalClock, BorderLayout.CENTER);
                Rectangle Bounds = getBounds();
                setBounds(Bounds.x, Bounds.y, DigitalSize.width, DigitalSize.height);
                revalidate();
                repaint();

            }
        });
        Analog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(AnalogClock, BorderLayout.CENTER);
                 Rectangle Bounds = getBounds();
                setBounds(Bounds.x, Bounds.y, AnalogSize.width, AnalogSize.height);
                revalidate();
                repaint();
            }
        });
    }

    public void SetDate(String newDate) {
        AnalogClock.SetDate(newDate);
        DigitalClock.SetDate(newDate);
    }

    public void SetDay(String newDay) {
        DigitalClock.SetDay(newDay);
    }

    public void SetTime(int Hour, int Minute, int Second) {
        AnalogClock.SetTime(Hour, Minute, Second);
        DigitalClock.SetTime(Hour, Minute, Second);
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AnalogClock = new UI.Utils.AnalogClock();
        ClockStyleMenu = new javax.swing.JPopupMenu();
        DigitalClock = new UI.Utils.DigitalClock();

        AnalogClock.setBorder(null);
        AnalogClock.setName("AnalogClock"); // NOI18N
        AnalogClock.setLayout(null);

        ClockStyleMenu.setName("ClockStyleMenu"); // NOI18N

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setComponentPopupMenu(ClockStyleMenu);
        setDoubleBuffered(false);
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        DigitalClock.setBackground(new java.awt.Color(204, 255, 204));
        DigitalClock.setBorder(null);
        DigitalClock.setName("DigitalClock"); // NOI18N
        DigitalClock.setOpaque(false);
        add(DigitalClock, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public UI.Utils.AnalogClock AnalogClock;
    private javax.swing.JPopupMenu ClockStyleMenu;
    public UI.Utils.DigitalClock DigitalClock;
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
