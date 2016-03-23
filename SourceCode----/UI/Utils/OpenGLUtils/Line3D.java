/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Utils.OpenGLUtils;

import javax.media.opengl.GL;

/**
 *
 * @author aalnawai
 */
public class Line3D {

    public Point3D P1 = new Point3D();
    public Point3D P2 = new Point3D();
    public double Slope = 0;
    public double AngleInRad = 0;
    public double AngleInDeg = 0;
    public double Length = 0;
    private double Dot = 0;
    public Point3D Delta = new Point3D();
    public static double I80_PI = 180.0 / Math.PI;
    public boolean isSlopeHorizontal = false;
    public boolean isSlopeVertical = false;
    public double Perpendicular = 0;
    public double b = 0;

    public Line3D(Point3D Point1, Point3D Point2) {
        P1.X = Point1.X;
        P1.Y = Point1.Y;
        P2.X = Point2.X;
        P2.Y = Point2.Y;
        Initialize();
    }

    private void Initialize() {
        GetSlope();
        Length = Math.sqrt((Delta.X * Delta.X) + (Delta.Y * Delta.Y) + (Delta.Z * Delta.Z));
        GetAngleInRad();
        GetAngleInDeg();
    }

    @Override
    public String toString() {
        return ("P1=( " + P1.X + " , " + P1.Y + " , " + P1.Z + " ) and P2=( " + P2.X + " , " + P2.Y + " , " + P2.Z + " )");
    }

    public void ShiftX(double ShiftDistance) {
        P1.X += ShiftDistance;
        P2.X += ShiftDistance;
    }

    public double GetSlope() {
        Delta.X = P2.X - P1.X;
        Delta.Y = P2.Y - P1.Y;
        Delta.Z = P2.Z - P1.Z;
        Slope = Delta.Y / Delta.X;
        return Slope;
    }

    public double GetAngleInRad() {
        AngleInRad = Math.atan2(Delta.Y, Delta.X);
        return AngleInRad;
    }

    public double GetAngleInDeg() {
        AngleInDeg = AngleInRad * I80_PI;
        AngleInDeg = (AngleInDeg < 0) ? (360.0 + AngleInDeg) : (AngleInDeg > 360) ? (AngleInDeg - 360.0) : AngleInDeg;
        return AngleInDeg;
    }

    public static Point3D GetIntersectionPoint(Line3D Line1, Line3D Line2) {

        Point3D Point = new Point3D();
        double xD1, yD1, xD2, yD2, xD3, yD3;
        double dot, deg, len1, len2;
        double segmentLen1, segmentLen2;
        double ua, ub, div;

        // calculate differences
        xD1 = Line1.P2.X - Line1.P1.X;
        xD2 = Line2.P2.X - Line2.P1.X;
        yD1 = Line1.P2.Y - Line1.P1.Y;
        yD2 = Line2.P2.Y - Line2.P1.Y;
        xD3 = Line1.P1.X - Line2.P1.X;
        yD3 = Line1.P1.Y - Line2.P1.Y;

        // calculate the lengths of the two lines
        len1 = Line1.GetLength();
        len2 = Line2.GetLength();

        // calculate angle between the two lines.
        dot = (xD1 * xD2 + yD1 * yD2); // dot product
        deg = dot / (len1 * len2);

        // if abs(angle)==1 then the lines are parallell,  so no intersection is possible
        if (Math.abs(deg) == 1) {
            return null;
        }
        // find intersection Pt between two lines
        div = yD2 * xD1 - xD2 * yD1;
        ua = (xD2 * yD3 - yD2 * xD3) / div;
        ub = (xD1 * yD3 - yD1 * xD3) / div;
        Point.X = Line1.P1.X + ua * xD1;
        Point.Y = Line1.P1.Y + ua * yD1;

        // calculate the combined length of the two segments
        // between Pt-p1 and Pt-p2
        xD1 = Point.X - Line1.P1.X;
        xD2 = Point.X - Line1.P2.X;
        yD1 = Point.Y - Line1.P1.Y;
        yD2 = Point.Y - Line1.P2.Y;
        segmentLen1 = Math.sqrt(xD1 * xD1 + yD1 * yD1) + Math.sqrt(xD2 * xD2 + yD2 * yD2);

        // calculate the combined length of the two segments
        // between Pt-p3 and Pt-p4
        xD1 = Point.X - Line2.P1.X;
        xD2 = Point.X - Line2.P2.X;
        yD1 = Point.Y - Line2.P1.Y;
        yD2 = Point.Y - Line2.P2.Y;
        segmentLen2 = Math.sqrt(xD1 * xD1 + yD1 * yD1) + Math.sqrt(xD2 * xD2 + yD2 * yD2);

        // if the lengths of both sets of segments are the same as
        // the lenghts of the two lines the point is actually
        // on the line segment.
        // if the point isn’t on the line, return null
        if (Math.abs(len1 - segmentLen1) > 0.01 || Math.abs(len2 - segmentLen2) > 0.01) {
            return null;
        }
        return Point;
    }

    public double GetLength() {
        Dot = (Delta.X * Delta.X) + (Delta.Y * Delta.Y);
        Length = Math.sqrt(Dot);
        return Length;
    }

    public Line3D Rotate(Point3D Origin, double Theta) {
        Point3D P11 = new Point3D(P1.Rotate(Origin, Theta));
        Point3D P22 = new Point3D(P2.Rotate(Origin, Theta));
        Line3D newLine = new Line3D(P11, P22);
        return newLine;
    }

    public Line3D Rotate(double Theta) {
        Point3D P11 = new Point3D(P1.Rotate(Theta));
        Point3D P22 = new Point3D(P2.Rotate(Theta));
        Line3D newLine = new Line3D(P11, P22);
        return newLine;
    }

    public void draw(GL GL) {
        GL.glBegin(GL.GL_LINES);
        GL.glVertex3d(P1.X, P1.Y, P1.Z);
        GL.glVertex3d(P2.X, P2.Y, P2.Z);
        GL.glEnd();
    }

    public void Translate(Point3D Shift, double theta, double D) {
        P1.X += Shift.X;
        P2.X += Shift.X;
        P1.Y += Shift.Y;
        P2.Y += Shift.Y;
    }

    public void Translate(Point3D Shift) {
        P1.X += Shift.X;
        P2.X += Shift.X;
        P1.Y += Shift.Y;
        P2.Y += Shift.Y;
    }

    public double Distance(Point3D P) {
        double m = Slope;
        double distance;
        double distance2;
        if (m == Double.POSITIVE_INFINITY || m == Double.NEGATIVE_INFINITY) {
            distance2 = distance = Math.abs(P.X - P1.X);
            if ((P.Y > P1.Y && P.Y > P2.Y) || (P.Y < P1.Y && P.Y < P2.Y)) {
                if (P1.Y > P2.Y) {
                    distance2 = distance = Math.sqrt(distance * distance + (P1.Y - P.Y) * (P1.Y - P.Y));
                } else {
                    distance2 = distance = Math.sqrt(distance * distance + (P2.Y - P.Y) * (P2.Y - P.Y));
                }
            }
        } else if (m == 0.0) {
            distance2 = distance = Math.abs(P.Y - P1.Y);
            if ((P.X > P1.X && P.X > P2.X) || (P.X < P1.X && P.X < P2.X)) {
                if (P1.X > P2.X) {
                    distance2 = distance = Math.sqrt(distance * distance + (P1.X - P.X) * (P1.X - P.X));
                } else {
                    distance2 = distance = Math.sqrt(distance * distance + (P2.X - P.X) * (P2.X - P.X));
                }
            }
        } else {

            if (((P.Y > P.Y && P.Y < P2.Y) || (P.Y < P.Y && P.Y > P2.Y))
                    && ((P.X > P1.X && P.X < P2.X) || (P.X < P1.X && P.X > P2.X))) {
                double intercept = P.Y - (m * P1.X);
                double perpLine = -1.0 / m;
                double x3 = ((P.Y - P.X * perpLine) - intercept) / (m - perpLine);
                double y3 = m * x3 + intercept;
                distance = Math.sqrt((x3 - P.X) * (x3 - P.X) + (y3 - P.Y) * (y3 - P.Y));
                double c = P2.Y - m * P2.X;
                distance2 = Math.abs(m * P.X + P.Y + c) / Math.sqrt(m * m + 1);
            } else {
                double d1 = Math.sqrt((P.X - P1.X) * (P.X - P1.X) + (P.Y - P.Y) * (P.Y - P.Y));
                double d2 = Math.sqrt((P.X - P2.X) * (P.X - P2.X) + (P.Y - P2.Y) * (P.Y - P2.Y));
                if (d1 > d2) {
                    distance = distance2 = d2;
                } else {
                    distance = distance2 = d1;
                }
            }
        }
        return distance;
    }

    public double distance(Line3D Line) {
        double x01 = P1.X;
        double x02 = Line.P1.X;
        double y01 = P1.Y;
        double y02 = Line.P1.Y;
        double z01 = P1.Z;
        double z02 = Line.P1.Z;
        double l1 = Length;
        double l2 = Line.Length;
        double m1 = Slope;
        double m2 = Line.Slope;
        double n1 = b;
        double n2 = Line.b;

        double t1 = (-l1 * (Math.pow(m2, 2) + Math.pow(n2, 2)) * (x01 - x02) - (m2 * n1 - m1 * n2) * (n2 * (-y01 + y02)
                + m2 * (z01 - z02)) + Math.pow(l2, 2) * (m1 * (-y01 + y02) + n1 * (-z01 + z02))
                + l2 * (m1 * m2 * (x01 - x02) + n1 * n2 * (x01 - x02) + l1 * (m2 * y01 - m2 * y02 + n2 * z01 - n2 * z02)))
                / (Math.pow(l2, 2) * (Math.pow(m1, 2) + Math.pow(n1, 2))
                + Math.pow(m2 * n1 - m1 * n2, 2) - 2 * l1 * l2 * (m1 * m2 + n1 * n2) + Math.pow(l1, 2) * (Math.pow(m2, 2) + Math.pow(n2, 2)));

        double t2 = -((l1 * l2 + m1 * m2 + n1 * n2) * (l1 * (x01 - x02) + m1 * (y01 - y02)
                + n1 * (z01 - z02)) - (Math.pow(l1, 2) + Math.pow(m1, 2) + Math.pow(n1, 2)) * (l2 * (x01 - x02)
                + m2 * (y01 - y02) + n2 * (z01 - z02)))
                / (-Math.pow(l1 * l2 + m1 * m2 + n1 * n2, 2) + (Math.pow(l1, 2) + Math.pow(m1, 2) + Math.pow(n1, 2)) * (Math.pow(l2, 2) + Math.pow(m2, 2) + Math.pow(n2, 2)));
        double p_x = x01 + l1 * t1;
        double p_y = y01 + m1 * t1;
        double p_z = z01 + n1 * t1;
        double q_x = x02 + l2 * t2;
        double q_y = y02 + m2 * t2;
        double q_z = z02 + n2 * t2;
        double dist = Math.sqrt(Math.pow(p_x - q_x, 2) + Math.pow(p_y - q_y, 2) + Math.pow(p_z - q_z, 2));
        return dist;
    }

    public boolean GetIfPointWithinTheLine(Point3D p) {
        Point3D point = GetProjectionPoint(p);
        double d1 = new Line3D(point, this.P1).Length;
        double d2 = new Line3D(point, this.P2).Length;
        double distance = d1 + d2;
        if (Length <= (distance + 0.01) && Length >= (distance - 0.01)) {
            return true;
        }
        return false;
    }

    public Point3D GetProjectionPoint(Point3D P) {
        Point3D ProjectionPoint = new Point3D(0, 0, 0);
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

}
