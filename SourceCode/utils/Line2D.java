package utils;

import UI.Utils.OpenGLUtils.Point3D;

public class Line2D {

    public Point2D P1 = null;
    public Point2D P2 = null;
    public double Length = 0;
    public double Slope = 0;
    public double Perpendicular = 0;
    public double b = 0;
    public double AngleInRad = 0;
    public double AngleInDeg = 0;
    public boolean isSlopeHorizontal = false;
    public boolean isSlopeVertical = false;
    /**
     * <P><B>0</B>: North </P> <P><B>1</B>: South </P> <P><B>2</B>: East </P>
     * <P><B>3</B>: West </P>
     */
    public int Direction = 0;
    private double P1DotP2 = 0;
    public Point2D Delta = null;
    public static double I80_PI = 180.0 / Math.PI;

    public Line2D(Point2D Point1, Point2D Point2) {
        P1 = new Point2D(Point1);
        P2 = new Point2D(Point2);

        Delta = CalculateDelta();
        P1DotP2 = CalculateP1DotP2();
        Length = CalculateLength();
        Slope = CalculateSlope();
        if (this.isSlopeHorizontal == false && this.isSlopeVertical == false) {
            Perpendicular = -1 / Slope;
            b = P1.Y - (Slope * P1.X);
        } else if (this.isSlopeHorizontal) {
            b = P1.Y;
            Slope = 0;
        } else if (this.isSlopeVertical) {
            b = P1.X;
            Perpendicular = 0;
        }

        AngleInRad = CalculateAngleInRad();
        AngleInDeg = CalculateAngleInDeg();
        Direction = CalculateDirection();
    }

    public Line2D(Point3D Point1, Point3D Point2) {
        P1 = new Point2D(Point1.X, Point1.Y);
        P2 = new Point2D(Point2.X, Point2.Y);

        Delta = CalculateDelta();
        P1DotP2 = CalculateP1DotP2();
        Length = CalculateLength();
        Slope = CalculateSlope();
        if (this.isSlopeHorizontal == false && this.isSlopeVertical == false) {
            Perpendicular = -1 / Slope;
            b = P1.Y - (Slope * P1.X);
        } else if (this.isSlopeHorizontal) {
            b = P1.Y;
            Slope = 0;
        } else if (this.isSlopeVertical) {
            b = P1.X;
            Perpendicular = 0;
        }

        AngleInRad = CalculateAngleInRad();
        AngleInDeg = CalculateAngleInDeg();
        Direction = CalculateDirection();
    }

    private Point2D CalculateDelta() {
        Point2D D = new Point2D();
        D.X = P2.X - P1.X;
        D.Y = P2.Y - P1.Y;
        return D;
    }

    private double CalculateP1DotP2() {
        double Dot = (Delta.X * Delta.X) + (Delta.Y * Delta.Y);
        return Dot;
    }

    private double CalculateSlope() {
        double S = 0;
        if (Delta.Y == 0) {
            this.isSlopeHorizontal = true;
        } else if (Delta.X == 0) {
            this.isSlopeVertical = true;
        } else {
            S = Delta.Y / Delta.X;
        }
        return S;
    }

    private double CalculateAngleInRad() {
        double Angle = Math.atan2(Delta.Y, Delta.X);
        return Angle;
    }

    private double CalculateAngleInDeg() {
        double Angle = AngleInRad * I80_PI;
        Angle = (Angle < 0) ? (360.0 + Angle) : (Angle > 360) ? (Angle - 360.0) : Angle;
        return Angle;
    }

    private double CalculateLength() {
        double L = Math.sqrt(P1DotP2);
        return L;
    }

    /**
     * Determine The Direction of GOing From Point P1 to Point P2
     *
     * @return <P><B>0</B>: North </P> <P><B>1</B>: South </P> <P><B>2</B>: East
     * </P> <P><B>3</B>: West </P>
     */
    private int CalculateDirection() {
        /**
         * 0: North 1: South 2: East 3: West
         */
        double Angle =
                (AngleInDeg < 0.0) ? (AngleInDeg + 360.0)
                : (AngleInDeg > 360.0) ? (360.0 - AngleInDeg)
                : AngleInDeg;
        int Result =
                (Angle > 315.0 || Angle < 45.0) ? 2
                : (Angle >= 45.0 && Angle <= 135.0) ? 0
                : (Angle > 135.0 && Angle < 225.0) ? 3
                : 1;

        return Result;
    }

    public double Distance(Point3D Point) {
        Point2D P = new Point2D(Point.X, Point.Y);
        Point2D PP = GetProjectionPoint(P);
        return new Line2D(P, PP).Length;
    }
    public Point2D GetProjectionPoint(Point2D P) {
        Point2D ProjectionPoint = new Point2D(0, 0, true);
        if (this.isSlopeHorizontal == false && this.isSlopeVertical == false) {
            ProjectionPoint.X = ((P.Y - P.X * Perpendicular) - b) / (Slope - Perpendicular);
            ProjectionPoint.Y = Slope * ProjectionPoint.X + b;
        } else if (this.isSlopeHorizontal) {
            ProjectionPoint.X = P.X;
            ProjectionPoint.Y = P1.Y;
        } else if (this.isSlopeVertical) {
            ProjectionPoint.X = P1.X;
            ProjectionPoint.Y = P.Y;
        }
        return ProjectionPoint;
    }

    @Override
    public String toString() {
        return String.format("P1 = %s, P2 = %s, Length = %d, Slope = %d", P1.toString(), P2.toString(), Length, Slope);
    }

    public boolean Contains(Point2D P) {
        boolean PXInRange = (P.X >= P1.X && P.X <= P2.X) || (P.X <= P1.X && P.X >= P2.X);
        boolean PYInRange = (P.Y >= P1.Y && P.Y <= P2.Y) || (P.Y <= P1.Y && P.Y >= P2.Y);
        return (PXInRange && PYInRange);
    }
}
