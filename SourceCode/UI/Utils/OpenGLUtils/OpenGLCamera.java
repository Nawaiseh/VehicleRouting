package UI.Utils.OpenGLUtils;

public class OpenGLCamera {

    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~      Variables        ~~~~~~~~~~~~~~~~~~~~~~~~~ ">
    private OpenGLRenderer OpenGLRenderer = null;
    private Point3D Eye;
    private Point3D Center;
    private Point3D UpVector;
    private Point3D ResetEye;
    private Point3D ResetCenter;
    private Point3D ResetPosition = new Point3D(0, 0, -27);
    private float MOVINGFACTOR = 0.05f;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~     Constructors      ~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    public OpenGLCamera() {
        Eye = new Point3D(0f, 0f, -27f);
        Center = new Point3D(0f, 0f, 0.0);
        UpVector = new Point3D(0.0, 1.0, 0.0);
        ResetEye = Eye;
        ResetCenter = Center;
    }

    public OpenGLCamera(OpenGLRenderer newOpenGLRenderer) {
        OpenGLRenderer = newOpenGLRenderer;
        Eye = new Point3D(0f, 0f, -27f);
        Center = new Point3D(0f, 0f, 0.0);
        UpVector = new Point3D(0.0, 1.0, 0.0);
        ResetEye = Eye;
        ResetCenter = Center;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~    Public Methods     ~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    public void reset() {
        Eye = new Point3D(this.ResetPosition.X, this.ResetPosition.Y, this.ResetPosition.Z);
        Center = new Point3D(this.ResetPosition.X, this.ResetPosition.Y, 0.0);
        UpVector = new Point3D(0.0, 1.0, 0.0);
    }

    public void Dispose() {
        OpenGLRenderer = null;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~     Get Methods       ~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    public Point3D GetMovement() {
        return new Point3D(Center.X - ResetPosition.X, Center.Y - ResetPosition.Y, 0);
    }

    public Point3D GetResetEye() {
        return ResetEye;
    }
    public Point3D GetEye() {
        return Eye;
    }
    public Point3D GetCenter() {
        return Center;
    }
    public Point3D GetUpVector() {
        return UpVector;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~     Set Methods       ~~~~~~~~~~~~~~~~~~~~~~~~~ ">
    public void SetResetPosition(Point3D rP) {
        ResetPosition = new Point3D(rP);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~  Movements Methods    ~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    public void MoveForward(float distance) {
        double tiltingAngle = OpenGLRenderer.Tilt * Math.PI / 180.0;
        double moveDistance = distance * Math.cos(tiltingAngle);
        for (int x = 0; x < 1; x++) {
            Eye.Y += moveDistance;
            Center.Y += moveDistance;
        }
    }

    public void MoveBackward(float distance) {
        double tiltingAngle = OpenGLRenderer.Tilt * Math.PI / 180.0;
        double moveDistance = distance * Math.cos(tiltingAngle);
        for (int x = 0; x < 1; x++) {
            Eye.Y -= moveDistance;
            Center.Y -= moveDistance;
        }
    }

    public void MoveLeft(float distance) {
        for (int x = 0; x < 1; x++) {
            Eye.X -= distance;
            Center.X -= distance;
        }
    }

    public void MoveRight(float distance) {
        for (int x = 0; x < 1; x++) {
            Eye.X += distance;
            Center.X += distance;
        }
    }
    // </editor-fold>
}
