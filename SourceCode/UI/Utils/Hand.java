package UI.Utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public final class Hand {

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~     Variables     ~~~~~~~~~~~~~~~~ ">
    private int Length;
    private int NegativeLength;
    private Color HandColor;
    private Shape Shape;
    private double Rectifier;
    public double Angle;
    public int Value;
    public AnalogClock AnalogClock = null;
    AffineTransform AffineTransform;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~ Hand Constructors ~~~~~~~~~~~~~~~~ ">

    Hand(AnalogClock newAnalogClock, int newValue, Color newHandColor, int newRectifier) {
        AnalogClock = newAnalogClock;
        Length = 100;
        Value = newValue;
        NegativeLength = 0;
        HandColor = newHandColor;
        Rectifier = Math.PI * newRectifier / 180;
        initialize();
    }

    Hand(AnalogClock newAnalogClock, int newValue, int newLength, Color newHandColor, int newRectifier) {
        AnalogClock = newAnalogClock;
        Length = newLength;
        Value = newValue;
        NegativeLength = 0;
        HandColor = newHandColor;
        Rectifier = Math.PI * newRectifier / 180;
        initialize();
    }

    Hand(AnalogClock newAnalogClock, int newValue, int newLength, int newNegativeLength, Color newHandColor, int newRectifier) {
         AnalogClock = newAnalogClock;
        Length = newLength;
        Value = newValue;
        NegativeLength = newNegativeLength;
        HandColor = newHandColor;
        Rectifier = Math.PI * newRectifier / 180;
        initialize();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~  Private Methods  ~~~~~~~~~~~~~~~~ ">

    private void initialize() {
        int x[] = new int[4];
        int y[] = new int[4];

        x[0] = AnalogClock.Center.x - NegativeLength;
        y[0] = AnalogClock.Center.y;

        x[1] = AnalogClock.Center.x + (int) ((Length / 2) * Math.cos(Rectifier));
        y[1] = AnalogClock.Center.y - (int) ((Length / 2) * Math.sin(Rectifier));

        x[2] = AnalogClock.Center.x + Length;
        y[2] = AnalogClock.Center.y;

        x[3] = AnalogClock.Center.x + (int) ((Length / 2) * Math.cos(Rectifier));
        y[3] = AnalogClock.Center.y + (int) ((Length / 2) * Math.sin(Rectifier));

        Shape = new Polygon(x, y, 4);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~  Public Methods   ~~~~~~~~~~~~~~~~ ">

    public void display(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g.setColor(HandColor);
        AffineTransform = AffineTransform.getRotateInstance(Angle - Math.PI / 2, AnalogClock.Center.x, AnalogClock.Center.y);
        g1.fill(AffineTransform.createTransformedShape(Shape));
        g1.draw(AffineTransform.createTransformedShape(Shape));
    }
    //</editor-fold>
}
