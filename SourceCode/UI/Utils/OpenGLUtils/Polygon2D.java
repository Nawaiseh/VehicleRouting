/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Utils.OpenGLUtils;

import java.util.ArrayList;
import javax.media.opengl.GL;

/**
 *
 * @author aalnawai
 */
public class Polygon2D {

    ArrayList<Point3D> Polygon = new ArrayList();

    public Polygon2D() {
    }

    public void CreateCircle(double Radius, double Slices) {
        double PI = (float) Math.PI;
        double PI2 = PI * 2;
        for (int i = 0; i < Slices; i++) {
            double angle = ((1F / (Slices - 2)) * i) * PI2;
            double x = Math.sin(angle) * Radius;
            double y = Math.cos(angle) * Radius;
            Point3D Vertex = new Point3D(x, y, 0);
            Polygon.add(Vertex);
        }
    }

     public void draw(GL GL, int DrawMode) {
        GL.glBegin(DrawMode);
        for (Point3D Point : Polygon) {
            GL.glVertex3d(Point.X, Point.Y, Point.Z);
        }
        GL.glEnd();
    }
    public void draw(GL GL, GLColorD Color, Point3D Location, double LineWidth) {
        GL.glPushMatrix();
        GL.glColor3d(Color.Red, Color.Green, Color.Blue);
        GL.glTranslated(Location.X, Location.Y, 0);
        GL.glLineWidth((float) LineWidth);
        GL.glBegin(GL.GL_POLYGON);
        for (Point3D Point : Polygon) {
            GL.glVertex3d(Point.X, Point.Y, Point.Z);
        }
        GL.glEnd();
        GL.glPopMatrix();
    }
}
