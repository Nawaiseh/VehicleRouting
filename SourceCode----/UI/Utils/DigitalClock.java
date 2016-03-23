/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DigitalClock.java
 *
 * Created on Aug 27, 2012, 12:17:45 PM
 */
package UI.Utils;

import UI.UIListner;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;

/**
 *
 * @author TRL@SMU
 */
public class DigitalClock extends javax.swing.JPanel {

    private static Dimension IconSize = new Dimension(25, 25);
    private ImageIcon ShrinkIcon = UIListner.createResizedIcon(new ImageIcon(getClass().getResource("/Resources/Shrink.png")).getImage(), IconSize);
    private ImageIcon ExpandIcon = UIListner.createResizedIcon(new ImageIcon(getClass().getResource("/Resources/Expand.png")).getImage(), IconSize);
    //  private ImageIcon ExpandIcon = new ImageIcon(getClass().getResource("/Resources/Expand.png"));
    public boolean IsShrinked = false;
    public Dimension ExpandSize = new Dimension(200, 85);
    public Dimension ShrinkSize = new Dimension(50, 50);
    private DecimalFormat DF = new DecimalFormat("00");
    private Calendar TimeCalendar = Calendar.getInstance();
    //  private String Date = String.format("%s/%s/%s", DF.format(TimeCalendar.get(Calendar.MONTH) + 1), DF.format(TimeCalendar.get(Calendar.DAY_OF_MONTH)), DF.format(TimeCalendar.get(Calendar.YEAR)));
    private String AMPM = "AM";

    public DigitalClock() {
        initComponents();
        ShrinkExpand.setIcon(ShrinkIcon);
        ShrinkExpand.addMouseListener(ShrinkExpandMouseListener);

    }

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~  Public Methods    ~~~~~~~~~~~~~~~~ ">
    public void SetDate(String newDate) {
        Date.setText(newDate);
    }

    public void SetDay(String newDay) {
        Day.setText(newDay);
    }

    public void SetTime(int Hour, int Minute, int Second) {
        if (Hour >= 12) {
            Hour -= (Hour == 12) ? 0 : 12;
            AMPM = "PM";
        } else {
            AMPM = "AM";
        }
        Time.setText(String.format("%s:%s:%s  %s", DF.format(Hour), DF.format(Minute), DF.format(Second), AMPM));
        Time.revalidate();
    }

    //</editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TitlePanel = new javax.swing.JPanel();
        DatePanel = new javax.swing.JPanel();
        Date = new javax.swing.JLabel();
        Day = new javax.swing.JLabel();
        ShrinkExpand = new javax.swing.JLabel();
        TimePanel = new javax.swing.JPanel();
        Time = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        TitlePanel.setName("TitlePanel"); // NOI18N
        TitlePanel.setOpaque(false);
        TitlePanel.setPreferredSize(new java.awt.Dimension(30, 25));
        TitlePanel.setLayout(new java.awt.BorderLayout());

        DatePanel.setName("DatePanel"); // NOI18N
        DatePanel.setOpaque(false);
        DatePanel.setPreferredSize(new java.awt.Dimension(30, 25));
        DatePanel.setLayout(new java.awt.GridLayout(1, 0));

        Date.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Date.setForeground(new java.awt.Color(204, 0, 0));
        Date.setText("  00/00/2000");
        Date.setName("Date"); // NOI18N
        DatePanel.add(Date);

        Day.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        Day.setForeground(new java.awt.Color(204, 0, 0));
        Day.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        Day.setText("WEDNSEDAY   ");
        Day.setName("Day"); // NOI18N
        DatePanel.add(Day);

        TitlePanel.add(DatePanel, java.awt.BorderLayout.CENTER);

        ShrinkExpand.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ShrinkExpand.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ShrinkExpand.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ShrinkExpand.setName("ShrinkExpand"); // NOI18N
        ShrinkExpand.setPreferredSize(new java.awt.Dimension(30, 30));
        TitlePanel.add(ShrinkExpand, java.awt.BorderLayout.EAST);

        add(TitlePanel, java.awt.BorderLayout.NORTH);

        TimePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        TimePanel.setName("TimePanel"); // NOI18N
        TimePanel.setOpaque(false);
        TimePanel.setPreferredSize(new java.awt.Dimension(30, 60));
        TimePanel.setLayout(new java.awt.GridLayout(1, 0));

        Time.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Time.setForeground(new java.awt.Color(204, 0, 0));
        Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Time.setText("00:00:00 AM");
        Time.setName("Time"); // NOI18N
        TimePanel.add(Time);

        add(TimePanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JPanel DatePanel;
    private javax.swing.JLabel Day;
    private javax.swing.JLabel ShrinkExpand;
    private javax.swing.JLabel Time;
    private javax.swing.JPanel TimePanel;
    private javax.swing.JPanel TitlePanel;
    // End of variables declaration//GEN-END:variables
    MouseListener ShrinkExpandMouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent MouseEvent) {
            IsShrinked = !IsShrinked;
            Clock Clock = (Clock) getParent();
            Rectangle Bounds = Clock.getBounds();
            if (IsShrinked) {
                Clock.setBounds(Bounds.x + 150, Bounds.y, ShrinkSize.width, ShrinkSize.height);
                remove(TimePanel);
                TitlePanel.remove(DatePanel);
                ShrinkExpand.setIcon(ExpandIcon);
            } else {
                Clock.setBounds(Bounds.x - 150, Bounds.y, ExpandSize.width, ExpandSize.height);
                add(TimePanel, BorderLayout.CENTER);
                TitlePanel.add(DatePanel, BorderLayout.CENTER);
                ShrinkExpand.setIcon(ShrinkIcon);
            }
            revalidate();
            TitlePanel.revalidate();
        }
    };
}
