package UI.Utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class AnalogClock extends JPanel {
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~     Variables      ~~~~~~~~~~~~~~~~ ">

    private static final Logger MyLogger = Logger.getLogger(Clock.class.getName());
    private int Radius = 70;
    public Point Center = new Point(Radius+5, Radius+5);
    private Calendar TimeCalendar = Calendar.getInstance();
    private Hand SecondsHand = new Hand(this, TimeCalendar.get(Calendar.SECOND), Radius - 15, 15, Color.red, 2);    //  value,length,neglen,Color,thick,
    private Hand MinutesHand = new Hand(this, TimeCalendar.get(Calendar.MINUTE), Radius * 5 / 7, Color.green, 9);
    private Hand HoursHand = new Hand(this, TimeCalendar.get(Calendar.HOUR_OF_DAY), Radius / 2, Color.blue, 14);
    private String CurrentTime = String.format("%d:%d:%d", (int) HoursHand.Value, (int) MinutesHand.Value, (int) SecondsHand.Value);
    private DecimalFormat DF = new DecimalFormat("00");
    private String Date = String.format("%s/%s/%s", DF.format(TimeCalendar.get(Calendar.MONTH) + 1), DF.format(TimeCalendar.get(Calendar.DAY_OF_MONTH)), DF.format(TimeCalendar.get(Calendar.YEAR)));
    private Rectangle DateBounds = new Rectangle(Center.x - 42, Center.y + Radius / 2 - 10, 85, 16);
    private Rectangle AMPMBounds = new Rectangle(Center.x - 12, Center.y - Radius / 2 - 10, 25, 16);
    private Font DateFont = new Font("Tahoma", Font.BOLD, 12);
    private Color BackColor = Color.BLACK;
    private String AMPM = "AM";
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~ AnalogClock Constructors ~~~~~~~~~~~~~~~~ ">

    public AnalogClock() {
        initComponents();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~  Private Methods   ~~~~~~~~~~~~~~~~ ">

    private void SetHoursHandAngle(double Hour, double Minute) {

        HoursHand.Angle = Hour * Math.PI / 6 + Minute * Math.PI / 360;
    }

    private void SetMinutesHandAngle(double Minute, double Second) {
        MinutesHand.Angle = (Minute * Math.PI / 30) + (Second * Math.PI / 1800);
    }

    private void SetSecondsHandAngle(double Second) {
        SecondsHand.Angle = Second * Math.PI / 30;
    }

    private void SetTimeHandsAngles() {
        SetHoursHandAngle(HoursHand.Value, MinutesHand.Value);
        SetMinutesHandAngle(MinutesHand.Value, SecondsHand.Value);
        SetSecondsHandAngle(SecondsHand.Value);
    }

    @Override
    public void paint(Graphics Graphics) {
        update(Graphics);
    }

    @Override
    public void update(Graphics Graphics) {
        try {
            Graphics2D g2 = (Graphics2D) Graphics;
//            if (AMPM.equalsIgnoreCase("AM")) {
//               AMPM = "PM";
//                BackColor = (HoursHand.Value > 5) ? Color.BLACK : new Color(100, 100, 150);
//            }
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            Graphics.setColor(Color.LIGHT_GRAY);
            Graphics.fillOval(Center.x - Radius - 4, Center.x - Radius - 4, 2 * Radius + 7, 2 * Radius + 7);
            Graphics.setColor(BackColor);
            Graphics.fillOval(Center.x - Radius, Center.x - Radius, 2 * Radius, 2 * Radius);

            Graphics.setColor(Color.BLACK);
            Graphics.fillRect(DateBounds.x - 1, DateBounds.y - 1, DateBounds.width + 2, DateBounds.height + 2);     //116,73
            Graphics.setColor(Color.LIGHT_GRAY);
            Graphics.setFont(DateFont);
            Graphics.fillRect(DateBounds.x, DateBounds.y, DateBounds.width, DateBounds.height);     //116,73
            Graphics.setColor(Color.BLACK);
            Graphics.drawString(Date, DateBounds.x + 5, DateBounds.y + 12);

            //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~ Display AM or PM ~~~~~~~~~~~~~~ ">

            Graphics.setColor(Color.BLACK);
            Graphics.fillRect(AMPMBounds.x - 1, AMPMBounds.y - 1, AMPMBounds.width + 2, AMPMBounds.height + 2);     //116,73
            Graphics.setColor(Color.LIGHT_GRAY);
            Graphics.fillRect(AMPMBounds.x, AMPMBounds.y, AMPMBounds.width, AMPMBounds.height);     //116,73
            Graphics.setColor(Color.BLACK);
            Graphics.drawString(AMPM, AMPMBounds.x + 2, AMPMBounds.y + 12);
            //</editor-fold>
            for (int i = 0; i < 60; i++) {
                if (i % 5 == 0) {
                    Graphics.setColor(Color.RED);
                    Graphics.drawLine(Center.x + (int) ((Radius * 6 / 7) * Math.sin(Math.toRadians(i * 6))), Center.y - (int) ((Radius * 6 / 7) * Math.cos(Math.toRadians(i * 6))) - 1, Center.x + (int) (Radius * Math.sin(Math.toRadians(i * 6))), Center.y - 1 - (int) (Radius * Math.cos(Math.toRadians(i * 6))));
                    Graphics.drawLine(Center.x + (int) ((Radius * 6 / 7) * Math.sin(Math.toRadians(i * 6))), Center.y - (int) ((Radius * 6 / 7) * Math.cos(Math.toRadians(i * 6))), Center.x + (int) (Radius * Math.sin(Math.toRadians(i * 6))), Center.y - (int) (Radius * Math.cos(Math.toRadians(i * 6))));
                } else {
                    Graphics.setColor(Color.white);
                    Graphics.drawLine(Center.x + (int) ((Radius * 6.5 / 7) * Math.sin(Math.toRadians(i * 6))), Center.y - (int) ((Radius * 6.5 / 7) * Math.cos(Math.toRadians(i * 6))), Center.x + (int) (Radius * Math.sin(Math.toRadians(i * 6))), Center.y - (int) (Radius * Math.cos(Math.toRadians(i * 6))));
                }
            }

            SetTimeHandsAngles();

            HoursHand.display(Graphics);
            MinutesHand.display(Graphics);
            SecondsHand.display(Graphics);

            Graphics.setColor(Color.red);
            Graphics.fillOval(Center.x - 4, Center.y - 4, 8, 8);
            Graphics.setColor(Color.yellow);
            Graphics.fillOval(Center.x - 2, Center.y - 2, 4, 4);
        } catch (Exception e) {
        }

    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~  Public Methods    ~~~~~~~~~~~~~~~~ ">

    public void SetDate(String newDate) {
        Date = newDate.trim();
    }

    public void SetRadius(int newRadius) {
        Radius = newRadius;
        Center = new Point(Radius + 20, Radius + 20);
        SecondsHand = new Hand(this, TimeCalendar.get(Calendar.SECOND), Radius - 15, 15, Color.red, 0);    //  value,length,neglen,Color,thick,
        MinutesHand = new Hand(this, TimeCalendar.get(Calendar.MINUTE), Radius * 5 / 7, Color.green, 9);
        HoursHand = new Hand(this, TimeCalendar.get(Calendar.HOUR_OF_DAY), Radius / 2, Color.blue, 14);
    }

    public void SetBounds(Rectangle Bounds) {
        setBounds(Bounds);
        Radius = Bounds.width / 2 - 20;
        Center = new Point(Radius + 20, Radius + 20);
        SecondsHand = new Hand(this, TimeCalendar.get(Calendar.SECOND), Radius - 15, 15, Color.red, 0);    //  value,length,neglen,Color,thick,
        MinutesHand = new Hand(this, TimeCalendar.get(Calendar.MINUTE), Radius * 5 / 7, Color.green, 9);
        HoursHand = new Hand(this, TimeCalendar.get(Calendar.HOUR_OF_DAY), Radius / 2, Color.blue, 14);
    }

    public void SetTime(int Hour, int Minute, int Second) {
        HoursHand.Value = Hour;
        MinutesHand.Value = Minute;
        SecondsHand.Value = Second;
        if (Hour >= 17) {
            BackColor = (Hour >= 17) ? Color.BLACK : new Color(240, 240, 200);
        } else {
            BackColor = (Hour <= 5) ? Color.BLACK : new Color(240, 240, 200);
        }
        if (Hour >= 12) {
            Hour -= (Hour == 12) ? 0 : 12;
            AMPM = "PM";
        } else {
            AMPM = "AM";
        }
        revalidate();
        repaint();

    }

    //</editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

 }
