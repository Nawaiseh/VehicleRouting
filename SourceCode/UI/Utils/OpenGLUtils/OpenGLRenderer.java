package UI.Utils.OpenGLUtils;

import UI.Utils.OpenGLSimulationPanel;
import UI.Utils.OpenGLUtils.OpenGLLink.ShiftLocation;
import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureCoords;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.ImageIcon;
import Data.Link;
import Data.Network;
import utils.DistanceConversion;

public class OpenGLRenderer implements GLEventListener {
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    Variables     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~">

    protected GLJPanel DrawablePanel = null;
    public OpenGLSimulationPanel OpenGLSimulationPanel = null;
    protected OpenGLZoomer OpenGLZoomer = null;
    protected OpenGLMouseListener OpenGLMouseListener = null;
    protected TreeMap<String, OpenGLLink> OpenGLLinks = new TreeMap();
    protected Network Network = null;
    protected OpenGLCamera OpenGLCamera = null;
    protected Point3D StartPosition = new Point3D(0, 0, 0);
    protected GLU glu = new GLU();
    protected GLColorD Background = new GLColorD(0.96f, 0.94f, 0.9f, 0.9f);
    protected double Tilt = 0;
    protected double Rotate = 0;
    protected double Scale = 1;
    protected double VerticalRange = 1;
    protected final GLUT GLUT = new GLUT();
    protected Point3D OpenGLCenter = new Point3D();
    protected int ViewPort[] = new int[4];
    protected double ModelMatrix[] = new double[16];
    protected double ProjectionMatrix[] = new double[16];
    protected Texture FlagTexture;
    protected Texture IncidentTexture;
    protected Texture HighlightedIncidentTexture;
    protected Texture RoadMapTexture;
    protected Texture SatelliteMapTexture;
    protected Texture HybridMapTexture;
    protected Texture CameraTexture;
    protected Texture DMSTexture;
    protected Texture StopSignTexture;
    protected Texture SignalTexture;
    protected Texture TransitStopTexture;
    protected Texture ParkAndRideTexture;
    protected String MapName = "Road Map";
    protected Point3D MapP1 = new Point3D();
    protected Point3D MapP2 = new Point3D();
    public String[] HightedItemKeys = {"", "", ""};

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    Constructor   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~">

    public OpenGLRenderer() {
    }

    public OpenGLRenderer(GLJPanel newDrawablePanel) {
        DrawablePanel = newDrawablePanel;
        OpenGLZoomer = new OpenGLZoomer(this, OpenGLCamera = new OpenGLCamera(this));
        Point3D Eye = OpenGLCamera.GetEye();
        Point3D Center = OpenGLCamera.GetCenter();
        Eye.X = Center.X = 0;
        Eye.Y = Center.Y = 0;
        Center.Z = 0;
        Eye.Z = -1 * VerticalRange;
        OpenGLCamera.SetResetPosition(Eye);
    }

    public OpenGLRenderer(OpenGLSimulationPanel newOpenGLSimulationPanel) {
        OpenGLSimulationPanel = newOpenGLSimulationPanel;
        DrawablePanel = OpenGLSimulationPanel.OpenGLPanel;
        OpenGLZoomer = new OpenGLZoomer(this, OpenGLCamera = new OpenGLCamera(this));
        Point3D Eye = OpenGLCamera.GetEye();
        Point3D Center = OpenGLCamera.GetCenter();
        Eye.X = Center.X = 0;
        Eye.Y = Center.Y = 0;
        Center.Z = 0;
        Eye.Z = -1 * VerticalRange;
        OpenGLCamera.SetResetPosition(Eye);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    Get Methods   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~">

    public OpenGLZoomer getOpenGLZoomer() {
        return OpenGLZoomer;
    }

    public OpenGLCamera getOpenGLCamera() {
        return OpenGLCamera;
    }

    public Point3D GetOpenGLCenter() {
        return OpenGLCenter;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~    Set Methods   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~">

    protected void setOpenGLCenter(Point3D newOpenCenter) {
        OpenGLCenter = newOpenCenter;
    }

    protected void SetMouseListenerViewPort(GL GL) {
        GL.glGetIntegerv(GL.GL_VIEWPORT, ViewPort, 0);
        GL.glGetDoublev(GL.GL_MODELVIEW_MATRIX, ModelMatrix, 0);
        GL.glGetDoublev(GL.GL_PROJECTION_MATRIX, ProjectionMatrix, 0);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~~~~~~ OpenGL Initialization Methods ~~~~~~~~~~~~~~~~~~~~~~~~">

    protected Texture LoadTexture(String FileName) {

        try {
            File File = new File(FileName);
            return TextureIO.newTexture(File, true);
        } catch (IOException e) {
            return null;
        }
    }

    protected BufferedImage ImageToBufferedImage(Image Image) {
        BufferedImage TempBufferedImage = new BufferedImage(Image.getWidth(null), Image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics Graphics = TempBufferedImage.getGraphics();
        Graphics.drawImage(Image, 0, 0, null);
        Graphics.dispose();
        return TempBufferedImage;
    }

    protected Texture LoadTextureFromResource(String Resourse) {

        try {
            Image Image = new ImageIcon(getClass().getResource(Resourse)).getImage();
            BufferedImage BufferedImage = ImageToBufferedImage(Image);
            return TextureIO.newTexture(BufferedImage, true);
        } catch (Exception e) {
            return null;
        }
    }

    protected Texture LoadTextureFromFile(String Resourse) {
        return LoadTexture(Resourse);
    }

    @Override
    public void init(GLAutoDrawable GLAutoDrawable) {
        GL GL = GLAutoDrawable.getGL();
        GL.glClearDepth(10.0f);
        GL.glEnable(GL.GL_DEPTH_TEST);
        GL.glDepthFunc(GL.GL_LEQUAL);
        GL.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
        GL.setSwapInterval(1);
        GL.glClearColor(Background.Red, Background.Green, Background.Blue, Background.Alpha);
        GL.glShadeModel(GL.GL_SMOOTH);
        int height = GLAutoDrawable.getHeight();
        int width = GLAutoDrawable.getWidth();
        height = (height <= 0) ? 1 : height;
        float ratio = (float) width / (float) height;
        GL.glViewport(0, 0, width, height);
        GL.glMatrixMode(GL.GL_PROJECTION);
        glu.gluPerspective(45, ratio, 0.0001, 10000);
        GL.glMatrixMode(GL.GL_MODELVIEW);

        String BasePath = System.getProperty("user.dir");
        RoadMapTexture = LoadTextureFromResource("/Resources/Maps/Google.png");
        SatelliteMapTexture = LoadTextureFromResource("/Resources/Maps/Bing.png");
        HybridMapTexture = LoadTextureFromResource("/Resources/Maps/OpenStreetMap.png");

//        IncidentTexture = LoadTextureFromResource("/Resources/Incident.png");
        // IncidentTexture = LoadTextureFromResource("/Resources/Maps/CarIncident.png");
        FlagTexture = LoadTextureFromFile(String.format("%s\\Resources\\Flag.png", BasePath));
        IncidentTexture = LoadTextureFromFile(String.format("%s\\Resources\\Incident.png", BasePath));
        HighlightedIncidentTexture = LoadTextureFromFile(String.format("%s\\Resources\\HighlightedIncident.png", BasePath));
        StopSignTexture = LoadTextureFromResource("/Resources/Maps/StopSign.png");
        SignalTexture = LoadTextureFromResource("/Resources/Maps/Signal.png");
        TransitStopTexture = LoadTextureFromResource("/Resources/Maps/BusStop.png");
        ParkAndRideTexture = LoadTextureFromResource("/Resources/Maps/ParkAndRide.png");
        //CameraTexture = LoadTextureFromResource("/Resources/Maps/VideoCamera.png");
        CameraTexture = LoadTextureFromFile(String.format("%s\\Resources\\Camera.png", BasePath));
        DMSTexture = LoadTextureFromResource("/Resources/Maps/DMS.png");

        DistanceConversion DV = new DistanceConversion();

        MapP1.X = DV.distance(0, -103.628044128418, 0, 0, "M"); // East  Side
        MapP2.X = DV.distance(0, -105.987350463867, 0, 0, "M"); // West  Side
        MapP2.Y = DV.distance(39.0258407592773, 0, 0, 0, "M");// South Side
        MapP1.Y = DV.distance(40.3125152587891, 0, 0, 0, "M"); // North Side 

//        MapP1.x = DV.distance(0, -103.66, 0, 0, "M"); // East  Side
//        MapP2.x = DV.distance(0, -105.93, 0, 0, "M"); // West  Side
//        MapP2.y = DV.distance(  39.05, 0, 0, 0, "M"); // South Side
//        MapP1.y = DV.distance(  40.30, 0, 0, 0, "M"); // North Side 
    }

    public void SetBackgroundMap(String SelectedBackground) {
        MapName = SelectedBackground;
    }
    public boolean FirstTime = true;

    @SuppressWarnings("static-access")
    protected void InitializeOpenGL(GL GL) {
        GL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        Point3D Eye = OpenGLCamera.GetEye();

        Point3D Center = OpenGLCamera.GetCenter();
        Point3D UpVector = OpenGLCamera.GetUpVector();

        glu.gluLookAt(Eye.X, Eye.Y, Eye.Z, Center.X, Center.Y, Center.Z, UpVector.X, UpVector.Y, UpVector.Z);
        GL.glLoadIdentity();
        StartPosition.X = -Center.X;
        StartPosition.Y = -Center.Y;
        StartPosition.Z = Eye.Z;

        GL.glTranslated(StartPosition.X, StartPosition.Y, StartPosition.Z);
        GL.glRotated(Tilt, 1, 0, 0);
        GL.glRotated(Rotate, 0, 0, 1);
        GL.glScaled(Scale, Scale, 1);
        GL.glClearColor(Background.Red, Background.Green, Background.Blue, Background.Alpha);

    }

    @Override
    public void reshape(GLAutoDrawable GLAutoDrawable, int x, int y, int width, int height) {
//        // init( GLAutoDrawable);
//        GL GL = GLAutoDrawable.getGL();
//        height = (height <= 0) ? 1 : height;
//        float ratio = (float) width / (float) height;
//        GL.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
//        GL.glLoadIdentity();
//        GL.glViewport(0, 0, width, height);
//        GL.glInitNames();
//        glu.gluPerspective(45, ratio, 0.000001, 100000000);
//        Point3D Eye = OpenGLCamera.GetEye();
//        Point3D Center = OpenGLCamera.GetCenter();
//        Point3D UpVector = OpenGLCamera.GetUpVector();
//        glu.gluLookAt(Eye.x, Eye.y, Eye.z, Center.x, Center.y, Center.z, UpVector.x, UpVector.y, UpVector.z);
//        //      GL.glGetIntegerv(GL.GL_VIEWPORT, ViewPort, 0);
    }

    @Override
    public void displayChanged(GLAutoDrawable GLAutoDrawable, boolean ModeChanged, boolean DeviceChanged) {
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~     Dispose      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~">

    public void Dispose() {
        OpenGLZoomer.Dispose();
        OpenGLCamera.Dispose();

        OpenGLZoomer = null;
        OpenGLCamera = null;
        OpenGLSimulationPanel = null;

        Network = null;

        IncidentTexture = null;
        RoadMapTexture = null;
        SatelliteMapTexture = null;
        HybridMapTexture = null;
        CameraTexture = null;
        DMSTexture = null;
        StopSignTexture = null;
        SignalTexture = null;
        TransitStopTexture = null;
        ParkAndRideTexture = null;

        for (OpenGLLink OpenGLLink : OpenGLLinks.values()) {
            OpenGLLink.Dispose();
        }
        OpenGLLinks.clear();
        OpenGLLinks = null;

        DrawablePanel = null;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   Draw Methods   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~">

    @Override
    public void display(GLAutoDrawable GLAutoDrawable) {
        try {
            GL GL = GLAutoDrawable.getGL();
            Background.Red = 0.94f;
            Background.Green = 0.9f;
            Background.Blue = 0.85f;
            // Background.Red  =1f; Background.Green  =0f; Background.Blue  =0f; 
            Background = new GLColorD(Background.Red, Background.Green, Background.Blue, Background.Alpha);
            InitializeOpenGL(GL);
            SetMouseListenerViewPort(GL);
            if (MapName.equalsIgnoreCase("Road Map")) {
                DrawMap(GL, RoadMapTexture);
            } else if (MapName.equalsIgnoreCase("Satellite Background")) {
                DrawMap(GL, SatelliteMapTexture);
            } else if (MapName.equalsIgnoreCase("Hybrid Map")) {
                DrawMap(GL, HybridMapTexture);
            }
        } catch (Exception Exception) {
        }
    }

    protected void DrawMap(GL GL, Texture MapTexture) {
        if (MapTexture != null) {
            DrawTexture(GL, MapTexture, MapP1.X - OpenGLCenter.X - 0.5, MapP2.Y - OpenGLCenter.Y + 0.7, MapP2.X - OpenGLCenter.X, MapP1.Y - OpenGLCenter.Y, 0, true);
            //  DrawTexture(GL, MapTexture, MapP1.x - OpenGLCenter.x - 2.0, MapP2.y - OpenGLCenter.y + 0.7, MapP2.x - OpenGLCenter.x + 2.5, MapP1.y - OpenGLCenter.y - 0.3, 0, true);
            //    DrawTexture(GL, MapTexture, MapP1.x, MapP2.y, MapP2.x, MapP1.y, 0, true);
        }
    }

    protected void DrawTexture(GL gl, Texture Texture, double X1, double Y1, double X2, double Y2, double Z, Boolean Transparent) {
        if (Transparent) {
            gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
            gl.glEnable(GL.GL_BLEND);
            gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        }
        TextureCoords coords = Texture.getImageTexCoords();
        Texture.enable();
        Texture.bind();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2d(coords.left(), coords.top());
        gl.glVertex3d(X2, Y2, Z);

        gl.glTexCoord2d(coords.right(), coords.top());
        gl.glVertex3d(X1, Y2, Z);

        gl.glTexCoord2d(coords.right(), coords.bottom());
        gl.glVertex3d(X1, Y1, Z);

        gl.glTexCoord2d(coords.left(), coords.bottom());
        gl.glVertex3d(X2, Y1, Z);
        gl.glEnd();
        Texture.disable();
        if (Transparent) {
            gl.glDisable(GL.GL_BLEND);
        }

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~ Translate GUI Mouse Location to OpenGL Loction ~~~~~~~~~~~~~~~">
    public Point3D TranslatePoint(Point MouseGUIPosition) {
        if (DrawablePanel == null) {
            return null;
        }
        Dimension Size = DrawablePanel.getSize();
        return new Point3D(MouseGUIPosition.getX(), (Size.getHeight() - MouseGUIPosition.getY()), 0);
    }
    // </editor-fold>

    public void SetVerticalRange(int value) {
        VerticalRange = value;
        OpenGLCamera.GetEye().Z = -1.5 * VerticalRange;
    }

    public OpenGLMouseListener getOpenGLMouseListener() {
        return OpenGLMouseListener;
    }

    private void PrepareData(Network Network) {
        long Index;
        DistanceConversion DV = new DistanceConversion();

        if (Network != null) {
//
//            MapP1.X = DV.distance(0, -85.33070241620, 0, 0, "M"); // East  Side
//            MapP2.X = DV.distance(0, -83.49565086570, 0, 0, "M"); // West  Side
//            MapP2.Y = DV.distance(33.16293715770, 0, 0, 0, "M");// South Side
//            MapP1.Y = DV.distance(34.52469816350, 0, 0, 0, "M"); // North Side 
//
//            Point3D FarXPoint = MapP1;// Network.NodesSortedByName.get(Network.Furthest_Node_On_X_Axis).Location;
//            Point3D NearXPoint = MapP2;// Network.NodesSortedByName.get(Network.Nearest_Node_On_X_Axis).Location;
//            Point3D FarYPoint = MapP2;// Network.NodesSortedByName.get(Network.Furthest_Node_On_Y_Axis).Location;
//            Point3D NearYPoint = MapP1;// Network.NodesSortedByName.get(Network.Nearest_Node_On_Y_Axis).Location;

            Point3D FarXPoint = Network.Nodes.get(Network.Furthest_Node_On_X_Axis).Location;
            Point3D NearXPoint = Network.Nodes.get(Network.Nearest_Node_On_X_Axis).Location;
            Point3D FarYPoint = Network.Nodes.get(Network.Furthest_Node_On_Y_Axis).Location;
            Point3D NearYPoint = Network.Nodes.get(Network.Nearest_Node_On_Y_Axis).Location;

            double NetworkWidth = NearXPoint.X - FarXPoint.X;
            double NetworkHeight = NearYPoint.Y - FarYPoint.Y;
            double MinX = FarXPoint.X;
            double MinY = FarYPoint.Y;
            double CenterX = NetworkWidth / 2.0 + MinX;
            double CenterY = NetworkHeight / 2.0 + MinY;
            OpenGLCenter = new Point3D(CenterX, CenterY, 0);
            System.err.println("==============================");
            System.err.println("OpenGLRenderer : Prepare Data");
            System.err.println("==============================");
            System.err.println(OpenGLCenter.toString());
            System.err.println("==============================");

//            MapP1.X = MapP1.X - OpenGLCenter.X; // East  Side
//            MapP2.X = MapP2.X - OpenGLCenter.X; // West  Side
//            MapP2.Y = MapP2.Y - OpenGLCenter.Y;// South Side
//            MapP1.Y = MapP1.Y - -OpenGLCenter.Y; // North Side 
            VerticalRange = NetworkHeight;
            Point3D Eye = OpenGLCamera.GetEye();

            Eye.Z = -1.5 * VerticalRange;
            OpenGLCamera.SetResetPosition(Eye);
            TreeMap<String, Link> Links = Network.Links;
            if (Links != null) {
                //for (Link Link : Links.values()) {
                for (Link Link : Links.values()) {
                    OpenGLLinks.put("" + Link.ID, new OpenGLLink(Network, Link, GLUT, OpenGLCenter, 0, ShiftLocation.UpStream, true));
                }
            }
        }
    }

    public void SetNetwork(Network newNetwork) {
        PrepareData(Network = newNetwork);
    }
}
