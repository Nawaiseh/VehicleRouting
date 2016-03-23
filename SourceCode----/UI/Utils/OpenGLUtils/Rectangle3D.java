/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Utils.OpenGLUtils;

/**
 *
 * @author aalnawai
 */
public class Rectangle3D {
    public Point3D P1 = new Point3D();
    public Point3D P2 = new Point3D();
    public Point3D P3 = new Point3D();
    public Point3D P4 = new Point3D();

 public   Rectangle3D(Point3D TP1, Point3D TP2, Point3D TP3, Point3D TP4) {
      P1.X = TP1.X;
      P1.Y = TP1.Y;
      P1.Z = TP1.Z;

      P2.X = TP2.X;
      P2.Y = TP2.Y;
      P2.Z = TP2.Z;

      P3.X = TP3.X;
      P3.Y = TP3.Y;
      P3.Z = TP3.Z;

      P4.X = TP4.X;
      P4.Y = TP4.Y;
      P4.Z = TP4.Z;

    }
    public void ShiftX(double ShiftDistance) {
        P1.X += ShiftDistance;
        P2.X += ShiftDistance;
        P3.X += ShiftDistance;
        P4.X += ShiftDistance;
    }
    public void ShiftY(double ShiftDistance) {
        P1.Y += ShiftDistance;
        P2.Y += ShiftDistance;
        P3.Y += ShiftDistance;
        P4.Y += ShiftDistance;
    }
}
