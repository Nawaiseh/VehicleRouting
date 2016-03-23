package UI.Utils.OpenGLUtils;

public class OpenGLZoomer {
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~      Variables        ~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    private OpenGLRenderer OpenGLRenderer = null;
    private OpenGLCamera OpenGLCamera = null;
    private static int TiltingCounter = 0;
    private float ZOOMFACTOR = 0.2f;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~     Constructors      ~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    public OpenGLZoomer(OpenGLRenderer newOpenGLRenderer, OpenGLCamera newOopenGLCamera) {
        OpenGLRenderer = newOpenGLRenderer;
        OpenGLCamera = newOopenGLCamera;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~    Public Methods     ~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    public void Dispose() {
        OpenGLRenderer = null;
        OpenGLCamera = null;
    }

    public void Reset() {
        TiltingCounter = 0;
        this.OpenGLRenderer.Tilt = 0;
        this.OpenGLRenderer.Rotate = 0;
        this.OpenGLRenderer.Scale = 1;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~~~~~~~  Movements Methods    ~~~~~~~~~~~~~~~~~~~~~~~~~ ">

    public void TiltTowards3D(float tiltAngle) {
        if (TiltingCounter < 20) {
            this.OpenGLRenderer.Tilt += -tiltAngle;
            TiltingCounter++;
        }
    }

    public void TiltTowards2D(float tiltAngle) {
        if (TiltingCounter > 0) {
            this.OpenGLRenderer.Tilt += tiltAngle;
            TiltingCounter--;
        }
    }

    public void RotateClockWise(float rotateAngle) {
        this.OpenGLRenderer.Rotate -= rotateAngle;
    }

    public void RotateCounterClockWise(float rotateAngle) {
        this.OpenGLRenderer.Rotate += rotateAngle;
    }

    public void zoomIn() {
        Point3D Eye = OpenGLCamera.GetEye();
        double ratio = (OpenGLCamera.GetResetEye().Z / Eye.Z);
        double zoomValue = this.ZOOMFACTOR * ratio;
        zoomValue = this.ZOOMFACTOR;
        Eye.Z += zoomValue * 0.5;
        if (Eye.Z >= -0.0500) {
            Eye.Z = -0.0500;
        }
    }

    public void zoomOut() {
        Point3D Eye = OpenGLCamera.GetEye();

        double ratio = (OpenGLCamera.GetResetEye().Z / Eye.Z);
        double zoomValue = this.ZOOMFACTOR * ratio;
        zoomValue = this.ZOOMFACTOR;
        Eye.Z -= zoomValue;
        if (Eye.Z <= -100) {
            Eye.Z = -100;
        }
    }
    // </editor-fold>
}
