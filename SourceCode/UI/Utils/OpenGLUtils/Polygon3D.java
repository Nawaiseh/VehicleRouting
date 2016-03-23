/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Utils.OpenGLUtils;

import java.util.ArrayList;
import java.util.List;
import javax.media.opengl.GL;

/**
 *
 * @author aalnawai
 */
public class Polygon3D {

    List<Point3D> Polygon = new ArrayList();
    List<Line3D> Edges = new ArrayList();

    public void AddPoint(Point3D newPoint) {
        Polygon.add(newPoint);
        if (Polygon.size() > 1) {
            Edges.add(new Line3D(Polygon.get(Polygon.size() - 2), Polygon.get(Polygon.size() - 1)));
        }
    }

    public boolean IsLineWithinRange(Line3D Line, double AcceptableRange) {
        boolean WithinRange = false;
        for (Line3D Edge : Edges) {
            if (Edge.distance(Line) <= AcceptableRange) {
                WithinRange = true;
                break;
            }
        }
        return WithinRange;
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

    public void draw(GL GL, Point3D Center, GLColorD Color, int DrawMode) {
        GL.glBegin(DrawMode);
        for (Point3D Point : Polygon) {
            GL.glVertex3d(Point.X - Center.X, Point.Y - Center.Y, Point.Z - Center.Z);
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

    @Override
    public String toString() {
        String Result = "";
        for (Point3D Point : Polygon) {
            Result = String.format("%s\n %3f \t %3f", Result, Point.X, Point.Y);
        }
        return Result;
    }

    public boolean Surrounds(Point3D Point) {
        return checkPoint(Point.X, Point.Y);
    }

    private boolean checkPoint(double X, double Y) {
        boolean c = false;
        for (int Index = 0, j = Polygon.size() - 1; Index < Polygon.size(); j = Index++) {
            if (((Polygon.get(Index).Y > Y) != (Polygon.get(j).Y > Y))
                    && (X < (Polygon.get(j).X - Polygon.get(Index).X) * (Y - Polygon.get(Index).Y) / (Polygon.get(j).Y - Polygon.get(Index).Y) + Polygon.get(Index).X)) {
                c = !c;
            }
        }
        return c;
    }

    public void AddPoints(ArrayList<Point3D> Points) {
        Polygon.addAll(Points);
    }
}
