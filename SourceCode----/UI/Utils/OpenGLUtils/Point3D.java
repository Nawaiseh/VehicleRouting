package UI.Utils.OpenGLUtils;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 *
 * @author Alaa
 */
/*
 * @(#)Point3D.java
 *
 */
/**
 * A point representing a location in {@code (x,y)} coordinate space, specified
 * in integer precision.
 * <p/>
 * @version 1.40, 02/24/06
 * @author Sami Shaio
 * @since 1.0
 */
public class Point3D implements java.io.Serializable {
 public static double Zero= 0.00;
    public static Point3D Parse(String Token) {
        try {
            Point3D Point = new Point3D();
            StringTokenizer Tokenizer = new StringTokenizer(Token, ",");
            Point.Y = Double.parseDouble(Tokenizer.nextToken());
            Point.X = Double.parseDouble(Tokenizer.nextToken());
            return Point;
        } catch (Exception E) {
            int x = 0;
            return null;
        }
    }
    /**
     * The X coordinate of this <code>Point3D</code>. If no X coordinate is set
     * it will default to 0.
     * <p/>
     * @serial @see #getLocation()
     * @see #move(double, double, double)
     * @since 1.0
     */
    public double X;
    /**
     * The Y coordinate of this <code>Point3D</code>. If no Y coordinate is set
     * it will default to 0.
     * <p/>
     * @serial @see #getLocation()
     * @see #move(double, double, double)
     * @since 1.0
     */
    public double Y;
    /**
     * The Z coordinate of this <code>Point3D</code>. If no Z coordinate is set
     * it will default to 0.
     * <p/>
     * @serial @see #getLocation()
     * @see #move(double, double, double)
     * @since 1.0
     */
    public double Z;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = -5276940640259749850L;

    /**
     * Constructs and initializes a point at the origin (0,&nbsp;0) of the
     * coordinate space.
     * <p/>
     * @since 1.1
     */
    public Point3D() {
        this(0.0, 00.0, 0.0);
    }

    /**
     * Constructs and initializes a point with the same location as the
     * specified <code>Point</code> object.
     * <p/>
     * @param p a point
     * <p/>
     * @since 1.1
     */
    public Point3D(Point3D p) {
        this(p.X, p.Y, p.Z);
    }

    /**
     * Constructs and initializes a point at the specified {@code (x,y)}
     * location in the coordinate space.
     * <p/>
     * @param x the X coordinate of the newly constructed <code>Point</code>
     * @param y the Y coordinate of the newly constructed <code>Point</code>
     * <p/>
     * @since 1.0
     */
    public Point3D(double newX, double newY, double newZ) {
        this.X = newX;
        this.Y = newY;
        this.Z = newZ;
    }

    /**
     * {@inheritDoc}
     * <p/>
     * @since 1.2
     */
    public double getX() {
        return this.X;
    }

    /**
     * {@inheritDoc}
     * <p/>
     * @since 1.2
     */
    public double getY() {
        return this.Y;
    }

    /**
     * {@inheritDoc}
     * <p/>
     * @since 1.2
     */
    public double getZ() {
        return this.Z;
    }

    /**
     * Returns the location of this point. This method is included for
     * completeness, to parallel the <code>getLocation</code> method of
     * <code>Component</code>.
     * <p/>
     * @return a copy of this point, at the same location
     * <p/>
     * @see java.awt.Component#getLocation
     * @see java.awt.Point#setLocation(java.awt.Point)
     * @see java.awt.Point#setLocation(int, int)
     * @since 1.1
     */
    public Point3D getLocation() {
        return new Point3D(this.X, this.Y, this.Z);
    }

    /**
     * Sets the location of the point to the specified location. This method is
     * included for completeness, to parallel the <code>setLocation</code>
     * method of <code>Component</code>.
     * <p/>
     * @param p a point, the new location for this point
     * <p/>
     * @see java.awt.Component#setLocation(java.awt.Point)
     * @see java.awt.Point#getLocation
     * @since 1.1
     */
    public void setLocation(Point3D p) {
        setLocation(p.X, p.Y, p.Z);
    }

    /**
     * Changes the point to have the specified location.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setLocation</code> method of <code>Component</code>. Its behavior
     * is identical with <code>move(int,&nbsp;int)</code>.
     * <p/>
     * @param x the X coordinate of the new location
     * @param y the Y coordinate of the new location
     * <p/>
     * @see java.awt.Component#setLocation(int, int)
     * @see java.awt.Point#getLocation
     * @see java.awt.Point#move(int, int)
     * @since 1.1
     */
    public void setLocation(double newX, double newY, double newZ) {
        move(newX, newY, newZ);
    }

    /**
     * Moves this point to the specified location in the {@code (x,y)}
     * coordinate plane. This method is identical with
     * <code>setLocation(int,&nbsp;int)</code>.
     * <p/>
     * @param x the X coordinate of the new location
     * @param y the Y coordinate of the new location
     * <p/>
     * @see java.awt.Component#setLocation(int, int)
     */
    public void move(double newX, double newY, double newZ) {
        this.X = newX;
        this.Y = newY;
        this.Z = newZ;
    }

    /**
     * Translates this point, at location {@code (x,y)}, by {@code dx} along the
     * {@code x} axis and {@code dy} along the {@code y} axis so that it now
     * represents the point {@code (x+dx,y+dy)}.
     * <p/>
     * @param dx the distance to move this point along the X axis
     * @param dy the distance to move this point along the Y axis
     */
    public void translate(double dx, double dy, double dz) {
        this.X += dx;
        this.Y += dy;
        this.Z += dz;
    }

    /**
     * Determines whether or not two points are equal. Two instances of
     * <code>Point2D</code> are equal if the values of their <code>x</code> and
     * <code>y</code> member fields, representing their position in the
     * coordinate space, are the same.
     * <p/>
     * @param obj an object to be compared with this <code>Point2D</code>
     * <p/>
     * @return < code>true</code> if the object to be compared is an instance of
     * <code>Point2D</code> and has the same values; <code>false</code>
     * otherwise.
     */
    @Override
    public boolean equals(Object obj) {
       
        if (obj instanceof Point3D) {
            Point3D pt = (Point3D) obj;
            return ((Math.abs(this.X - pt.X) <= Zero) && (Math.abs(this.Y - pt.Y) <= Zero) && (Math.abs(this.Z - pt.Z) <= Zero));
        }
        return super.equals(obj);
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.X) ^ (Double.doubleToLongBits(this.X) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.Y) ^ (Double.doubleToLongBits(this.Y) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.Z) ^ (Double.doubleToLongBits(this.Z) >>> 32));
        return hash;
    }
    /**
     * Returns a string representation of this point and its location in the
     * {@code (x,y)} coordinate space. This method is intended to be used only
     * for debugging purposes, and the content and format of the returned string
     * may vary between implementations. The returned string may be empty but
     * may not be <code>null</code>.
     * <p/>
     * @return a string representation of this point
     */
    DecimalFormat DF = new DecimalFormat("0.00");

    @Override
    public String toString() {
        return String.format("( %s, %s, %s ) ", DF.format(X), DF.format(Y), DF.format(Z));
        //  return "( " + this.x + ", " + this.y + " , " + this.z + " )";
    }

    public String toString2() {
        return String.format("%f,%f", Y, X);
        //  return "( " + this.x + ", " + this.y + " , " + this.z + " )";
    }

    public double distanceFrom(Point3D point) {
        double distance = Math.sqrt((point.X - this.X) * (point.X - this.X)
                + (point.Y - this.Y) * (point.Y - this.Y)
                + (point.Z - this.Z) * (point.Z - this.Z));
        return distance;
    }

    public Point3D Rotate(Point3D Origin, double Theta) {
        Point3D P = new Point3D();
        P.X = X - Origin.X;
        P.Y = Y - Origin.Y;

        double xx = X - Origin.X;
        double yy = Y - Origin.Y;

        P.X = xx * Math.cos(Theta) - yy * Math.sin(Theta);
        P.Y = xx * Math.sin(Theta) - yy * Math.cos(Theta);
        P.X += Origin.X;
        P.Y += Origin.Y;
        return P;
    }

    public Point3D Rotate(double Theta) {
        Point3D P = new Point3D();
        double xx = X;
        double yy = Y;
        P.X = xx * Math.cos(Theta) - yy * Math.sin(Theta);
        P.Y = xx * Math.sin(Theta) - yy * Math.cos(Theta);
        return P;
    }
}
