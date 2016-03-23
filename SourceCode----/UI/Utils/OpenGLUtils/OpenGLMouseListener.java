package UI.Utils.OpenGLUtils;

import UI.Utils.MouseLocationPanel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import javax.media.opengl.glu.GLU;

public class OpenGLMouseListener implements MouseMotionListener, MouseListener, MouseWheelListener {

    public enum NetworkItemType {

        Node, Link, Zone, Nothing
    }
    public NetworkItemType PickerItemType = NetworkItemType.Nothing;
    protected int PressedButton = 0;
    protected OpenGLRenderer OpenGLRenderer = null;
    protected OpenGLCamera OpenGLCamera = null;
    protected OpenGLZoomer OpenGLZoomer = null;
    protected Point3D Location = new Point3D();
    protected int ViewPort[] = new int[4];
    protected double ModelMatrix[] = new double[16];
    protected double ProjectionMatrix[] = new double[16];
    protected double WorldCoordinates[] = new double[4];
    protected DecimalFormat DecimalFormat = new DecimalFormat("00.0000");

    public OpenGLMouseListener() {
    }

    public OpenGLMouseListener(OpenGLRenderer newOpenGLRenderer) {
        OpenGLRenderer = newOpenGLRenderer;
        OpenGLCamera = OpenGLRenderer.getOpenGLCamera();
        OpenGLZoomer = OpenGLRenderer.getOpenGLZoomer();
    }

    protected void Dispose() {
        OpenGLRenderer = null;
        OpenGLCamera = null;
        OpenGLZoomer = null;
        ViewPort = null;
        ProjectionMatrix = null;
        ModelMatrix = null;
        WorldCoordinates = null;
        Location = null;
    }

    public void setViewPort(int[] newViewPort) {
        try {
            System.arraycopy(newViewPort, 0, this.ViewPort, 0, 4);
        } catch (Exception Exception) {
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void setModelMatrix(double[] newModelMatrix) {
        try {
            System.arraycopy(newModelMatrix, 0, ModelMatrix, 0, 16);
        } catch (Exception Exception) {
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void setProjectionMatrix(double[] newProjectionMatrix) {
        try {
            System.arraycopy(newProjectionMatrix, 0, this.ProjectionMatrix, 0, 16);
        } catch (Exception Exception) {
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Point3D GetOpenGLPosition(double MouseX, double MouseY) {
        Point3D result = new Point3D(0, 0, 0);
        Point3D b = null;
        try {
            Point3D c = new Point3D(OpenGLCamera.GetCenter());
            c.X -= OpenGLCamera.GetMovement().X;
            c.Y -= OpenGLCamera.GetMovement().Y;
            Point3D Eye = new Point3D(OpenGLCamera.GetEye());
            double ThetaZ = -OpenGLRenderer.Rotate * Math.PI / 180.0;
            double ThetaX = -OpenGLRenderer.Tilt * Math.PI / 180.0;
            Point3D cosTheta = new Point3D(Math.cos(ThetaX), Math.cos(0.0), Math.cos(ThetaZ));
            Point3D sinTheta = new Point3D(Math.sin(ThetaX), Math.sin(0.0), Math.sin(ThetaZ));
            double Realy = (ViewPort[3] - MouseY);
            GLU glu = new GLU();
            glu.gluUnProject(MouseX, Realy, 0, ModelMatrix, 0, ProjectionMatrix, 0, ViewPort, 0, WorldCoordinates, 0);
            double Xm = WorldCoordinates[0];
            double Ym = WorldCoordinates[1];
            double Zm = WorldCoordinates[2];
            Point3D a = new Point3D(Xm, Ym, Zm);
            Point3D d = new Point3D(0, 0, 0);
            // Reverse the rotation and the tilting
            d.X = cosTheta.Y * (sinTheta.Z * (a.Y - c.Y) + cosTheta.Z * (a.X - c.X)) - sinTheta.Y * (a.Z - c.Z);
            d.Y = sinTheta.X * (cosTheta.Y * (a.Z - c.Z) + sinTheta.Y * (sinTheta.Z * (a.Y - c.Y) + cosTheta.Z * (a.X - c.X))) + cosTheta.X * (cosTheta.Z * (a.Y - c.Y) - sinTheta.Z * (a.X - c.X));
            d.Z = cosTheta.X * (cosTheta.Y * (a.Z - c.Z) + sinTheta.Y * (sinTheta.Z * (a.Y - c.Y) + cosTheta.Z * (a.X - c.X))) - sinTheta.X * (cosTheta.Z * (a.Y - c.Y) - sinTheta.Z * (a.X - c.X));

            b = new Point3D(0, 0, 0);
            b.X = (d.X - Eye.X) * (Eye.Z / d.Z);
            b.Y = (d.Y - Eye.Y) * (Eye.Z / d.Z);
            b.Z = 0;
            double dzy = (Math.abs(Eye.Z) - Math.abs(d.Z));
            double dz = (Math.abs(Eye.Z) - Math.abs(d.Z));
            result.X = -(c.X + ((b.X - c.X) / dz) * -Eye.Z);
            result.Y = -(c.Y + ((b.Y - c.Y) / dz) * -Eye.Z);
            result.Z = 0f;

            ThetaZ = -OpenGLRenderer.Rotate * Math.PI / 180.0;
            cosTheta = new Point3D(1, 1, Math.cos(ThetaZ));
            sinTheta = new Point3D(0, 0, Math.sin(ThetaZ));

            b = new Point3D(result);
            d.X = cosTheta.Y * (sinTheta.Z * (b.Y - c.Y) + cosTheta.Z * (b.X - c.X)) - sinTheta.Y * (b.Z - c.Z);
            d.Y = sinTheta.X * (cosTheta.Y * (b.Z - c.Z) + sinTheta.Y * (sinTheta.Z * (b.Y - c.Y) + cosTheta.Z * (b.X - c.X))) + cosTheta.X * (cosTheta.Z * (b.Y - c.Y) - sinTheta.Z * (b.X - c.X));
            b.Y = d.Y;
            b.X = d.X - c.Y;
        } catch (Exception Exception) {
        }
        return b;
    }

    public Point3D GetOpenGLPosition(Point Mouse) {

        return GetOpenGLPosition(Mouse.x, Mouse.y);
    }

    public Point3D GetOpenGLPosition(Point3D Mouse) {

        return GetOpenGLPosition(Mouse.X, Mouse.Y);
    }

    @Override
    public void mouseDragged(MouseEvent Event) {
        try {
            Point3D Mouse = OpenGLRenderer.TranslatePoint(Event.getPoint());
            Point3D Positon = GetOpenGLPosition(Mouse);
//        Point3D Movement = OpenGLCamera.GetMovement();
//        Positon.x = Movement.x + Positon.x;
//        Positon.y = Movement.y - Positon.y;

//We Need To Fix The Y Poisition
            switch (PressedButton) {
                case MouseEvent.BUTTON1:
                    Pann(Positon);
                    break;
                case MouseEvent.BUTTON2:
                    //  Tilt(((Positon.y - Location.y) > 0) ? 1 : 0);
                    break;
                case MouseEvent.BUTTON3:
                    //   Rotate(((Positon.x - Location.x) > 0) ? 1 : 0);
                    break;
            }

            Location.X = Positon.X;
            Location.Y = Positon.Y;
        } catch (Exception Exception) {
        }
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    protected void Pann(Point3D GLMovePositon) {
        try {
            Point3D deltaPos = new Point3D(0f, 0f, 0f);
            deltaPos.X = (GLMovePositon.X - Location.X);
            deltaPos.Y = (GLMovePositon.Y - Location.Y);
            if (deltaPos.X > 0) {
                deltaPos.X = -1f * (float) deltaPos.X;
                OpenGLCamera.MoveRight((float) deltaPos.X);
            } else if (deltaPos.X < 0) {
                OpenGLCamera.MoveLeft((float) deltaPos.X);
            }
            Location.X = GLMovePositon.X;
            if (deltaPos.Y < 0) {
                OpenGLCamera.MoveForward((float) deltaPos.Y);
            } else if (deltaPos.Y > 0) {
                deltaPos.Y = -1f * (float) deltaPos.Y;
                OpenGLCamera.MoveBackward((float) deltaPos.Y);
            }
            Location.Y = GLMovePositon.Y;
        } catch (Exception Exception) {
        }
    }

    protected void Rotate(int Direction) {
        try {
            float RotateAngle = 0.3f;
            final int RotateClockWise = 0;
            final int RotateCounterClockWise = 1;
            switch (Direction) {
                case RotateClockWise:
                    OpenGLZoomer.RotateClockWise(RotateAngle);
                    break;
                case RotateCounterClockWise:
                    OpenGLZoomer.RotateCounterClockWise(RotateAngle);
                    break;
            }
        } catch (Exception Exception) {
        }
    }

    protected void Tilt(int Direction) {
        try {
            float TiltAngle = 0.3f;
            final int Towards2D = 0;
            final int Towards3D = 1;
            switch (Direction) {
                case Towards2D:
                    OpenGLZoomer.TiltTowards2D(TiltAngle);
                    break;
                case Towards3D:
                    OpenGLZoomer.TiltTowards3D(TiltAngle);
                    break;
            }
        } catch (Exception Exception) {
        }
    }
    protected Point3D MouseMovePositon = null;

    @Override
    public void mouseMoved(MouseEvent Event) {
        try {
            Point3D Mouse = OpenGLRenderer.TranslatePoint(Event.getPoint());
            MouseMovePositon = GetOpenGLPosition(Mouse);
            Point3D OpenGLCenter = OpenGLRenderer.GetOpenGLCenter();
            Point3D Movement = OpenGLCamera.GetMovement();

            Location.X = Movement.X + MouseMovePositon.X;
            Location.Y = Movement.Y - MouseMovePositon.Y;
            // Convert GL Position to Miles X,Y
            MouseMovePositon.X = OpenGLCenter.X + Location.X;
            MouseMovePositon.Y = OpenGLCenter.Y + Location.Y;
            MouseLocationPanel MouseLocationPanel = OpenGLRenderer.OpenGLSimulationPanel.MouseLocationPanel;
            MouseLocationPanel.MousePosition = MouseMovePositon;
            DecimalFormat = new DecimalFormat("00.0000");
            MouseLocationPanel.Location.setText(String.format("(%s , %s )", DecimalFormat.format(MouseMovePositon.X), DecimalFormat.format(MouseMovePositon.Y)));
        } catch (Exception Exception) {
        }
    }

    @Override
    public void mouseClicked(MouseEvent Event) {
        try {
            PressedButton = Event.getButton();
            Point3D Mouse = OpenGLRenderer.TranslatePoint(Event.getPoint());
            Location = GetOpenGLPosition(Mouse);
            PressedButton = Event.getButton();
        } catch (Exception Exception) {
        }
    }

    @Override
    public void mousePressed(MouseEvent Event) {
        try {
            Point3D Mouse = OpenGLRenderer.TranslatePoint(Event.getPoint());
            Location = GetOpenGLPosition(Mouse);
            PressedButton = Event.getButton();
        } catch (Exception Exception) {
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PressedButton = MouseEvent.NOBUTTON;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        try {
            if (mouseWheelEvent.getWheelRotation() > 0) {
                OpenGLZoomer.zoomOut();
            } else {
                OpenGLZoomer.zoomIn();
            }
        } catch (Exception Exception) {
        }
    }

    public OpenGLRenderer getOpenGLRenderer() {
        return OpenGLRenderer;
    }

    public void setOpenGLRenderer(OpenGLRenderer newOpenGLRenderer) {
        OpenGLRenderer = newOpenGLRenderer;
    }
}
