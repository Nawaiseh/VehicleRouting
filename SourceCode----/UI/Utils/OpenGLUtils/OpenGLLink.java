package UI.Utils.OpenGLUtils;

import com.sun.opengl.util.GLUT;
import java.awt.Component;
import javax.media.opengl.GL;
import Data.Node;
import Data.Link;
import Data.Network;

@SuppressWarnings({"CallToThreadDumpStack", "UnusedAssignment"})
public class OpenGLLink {

    private float[][] LevelOfServiceLegends = {{1, 0, 0}, {1, 0.5f, 0f}, {1, 1, 0}, {0f, 1f, 0f}}; // red, orange, yellow, green
    private float LineWidth = 10;

    public enum ShiftLocation {

        UpStream, DownStream
    };
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~   Variables  ~~~~~~~~~~~~~~~~~~">
    public Link Link = null;
    public String LinkID = null;
    private Node UpStream = null;
    private Node DownStream = null;
    private Point3D UpStreamOpenGLLocation = null;
    private Point3D DownStreamOpenGLLocation = null;
    private double LaneWidth = 0;
    private double NegLaneWidth = 0;
    private RectangleD[] LanesBounds;
    public Line3D[] Separators;
    public Line3D[] AlternativeRouteSeparators;
    public Line3D LinkBorder = null;
    private Rectangle3D[] TrafficLights;
    private double LaneSeparatorWidth = 1;
    private double DirectionSeparatorWidth = 3;
    public int nLanes = 0;
    private Point3D openGLCenter = new Point3D(0, 0, 0);
    private Point3D LeftTurnSplitLocation = new Point3D(0, 0, 0);
    private Point3D ThroughSplitLocation = new Point3D(0, 0, 0);
    private Point3D RightTurnSplitLocation = new Point3D(0, 0, 0);
    private Point3D TotalVolumeLocation = new Point3D(0, 0, 0);
    private GLUT GLUT = null;
    private Point3D Delta = null;
    private double AngleInRadians;
    public double AngleInDegrees;
    private double LinkLength;
    private double LinkGUILength;
    private double LinkHeight = 0.002;
    public double LinkWidth;
    public GLColorD DefaultColor = new GLColorD(0.4f, 0.4f, 0.4f);
    private GLColorD LaneSeparatorColor = new GLColorD(0.6f, 0.6f, 0.6f);
    public GLColorD DirectionSeparatorColor = new GLColorD(0.6f, 0.6f, 0);
    private GLColorD GreenArrowColor = new GLColorD(0, 1, 0);
    private GLColorD RedArrowColor = new GLColorD(1, 0, 0);
    private GLColorD YellowArrowColor = new GLColorD(1, 1, 0);
    private GLColorD GreenLight = new GLColorD(0, 1, 0);
    private GLColorD RedLight = new GLColorD(1, 0, 0);
    private GLColorD YellowLight = new GLColorD(1, 1, 0);
    private boolean InLink = false;
    public double ThetaDegrees = 0;
    public String direction = null;
    public Component BoundDirection = null;
    public int LaneWidthEnlargement = 30;
    public int GLScale = 1;
    public double TrafficLightShiftValue = 0;

    // </editor-fold>
    public void Dispose() {
        Link = null;
        UpStream = null;
        DownStream = null;
        GLUT = null;
        BoundDirection = null;
    }
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~  Constructor ~~~~~~~~~~~~~~~~~~">

    public OpenGLLink(Network Network, Link newLink, GLUT newGLUT, Point3D newOpenGLCenter, double ShiftDistance, ShiftLocation ShiftLocation, boolean UseOpenGLLocation) {
        Link = newLink;
        openGLCenter = newOpenGLCenter;
        if (ShiftLocation == ShiftLocation.UpStream) { // Out Links
            InLink = false;
            // DefaultColor = new GLColorD(0.84f, 0.4f, 0.4f);
        } else if (ShiftLocation == ShiftLocation.DownStream) { // In Links
            InLink = true;
            //   DefaultColor = new GLColorD(0.4f, 0.8f, 0.4f);
        }
        GLUT = newGLUT;

        Initialize(Network);
    }// </editor-fold>

    public OpenGLLink(Network Network, Link newLink, GLUT newGLUT, Point3D newOpenGLCenter, double ShiftDistance, ShiftLocation ShiftLocation, boolean UseOpenGLLocation, int newLaneWidthEnlargement) {
        LaneWidthEnlargement = newLaneWidthEnlargement;
        Link = newLink;
        openGLCenter = newOpenGLCenter;
        if (ShiftLocation == ShiftLocation.UpStream) { // Out Links
            InLink = false;
            // DefaultColor = new GLColorD(0.84f, 0.4f, 0.4f);
        } else if (ShiftLocation == ShiftLocation.DownStream) { // In Links
            InLink = true;
            //   DefaultColor = new GLColorD(0.4f, 0.8f, 0.4f);
        }
        GLUT = newGLUT;

        Initialize(Network);

    }

    private void Initialize(Network Network) {
        try {

            LinkID = "" + Link.ID;
            LinkGUILength = Link.Length;

            try {
                UpStream = Network.Nodes.get(Link.UpStream);
                DownStream = Network.Nodes.get(Link.DownStream);
            } catch (Exception e) {
                int x = 0;
            }
//        if (UseOpenGLLocation){
//        UpStreamOpenGLLocation =  new Point3D(UpStreamOpenGLLocation);
//        DownStreamOpenGLLocation = new Point3D(DownStream.getOpenGLLocation());
//        }else{

            UpStreamOpenGLLocation = new Point3D(UpStream.Location);
            DownStreamOpenGLLocation = new Point3D(DownStream.Location);

            UpStreamOpenGLLocation.X = UpStreamOpenGLLocation.X - openGLCenter.X;
            UpStreamOpenGLLocation.Y = UpStreamOpenGLLocation.Y - openGLCenter.Y;

            DownStreamOpenGLLocation.X = DownStreamOpenGLLocation.X - openGLCenter.X;
            DownStreamOpenGLLocation.Y = DownStreamOpenGLLocation.Y - openGLCenter.Y;

//        }
            Delta = new Point3D(DownStreamOpenGLLocation.X - UpStreamOpenGLLocation.X, DownStreamOpenGLLocation.Y - UpStreamOpenGLLocation.Y, 0);
            AngleInRadians = Math.atan2(Delta.Y, Delta.X);
            AngleInDegrees = AngleInRadians * 180.0 / Math.PI;
            ThetaDegrees = (AngleInDegrees < 0) ? (360.0 + AngleInDegrees) : ((AngleInDegrees > 360) ? (AngleInDegrees - 360.0) : AngleInDegrees);

            LinkLength = Math.sqrt((Delta.X * Delta.X) + (Delta.Y * Delta.Y) + (Delta.Z * Delta.Z));
            //  LinkLength = 0.31; // 50 Meters Length
            // LinkLength = (LinkLength > 0.2) ? 0.2 : LinkLength;

            LaneWidth = Link.LaneWidth;
            LinkWidth = LaneWidth * nLanes;
            NegLaneWidth = -LaneWidth;
            nLanes = Link.LanesCount;

            LanesBounds = new RectangleD[nLanes];
            Separators = new Line3D[nLanes + 1];
            AlternativeRouteSeparators = new Line3D[nLanes + 1];

            TrafficLights = new Rectangle3D[nLanes];
            for (int LaneIndex = 0; LaneIndex < (nLanes + 1); LaneIndex++) {
                Point3D P1 = new Point3D(0, (LaneIndex * NegLaneWidth * GLScale), LinkHeight);
                Point3D P2 = new Point3D(LinkLength, (LaneIndex * NegLaneWidth * GLScale), LinkHeight);
                Point3D PAR1 = new Point3D(0, (LaneIndex * NegLaneWidth * GLScale * LaneWidthEnlargement), LinkHeight);
                Point3D PAR2 = new Point3D(LinkLength, (LaneIndex * NegLaneWidth * GLScale * LaneWidthEnlargement), LinkHeight);
                Separators[LaneIndex] = new Line3D(P1, P2);
                AlternativeRouteSeparators[LaneIndex] = new Line3D(PAR1, PAR2);
                LinkBorder = new Line3D(P1, P2);
                if (InLink) {
                    if ((ThetaDegrees > 90.0) && (ThetaDegrees < 180.0)) {
                        LinkBorder.P1.Y *= -1;
                        LinkBorder.P2.Y *= -1;
                    } else if ((ThetaDegrees > 180.0) && (ThetaDegrees < 270.0)) {
                        LinkBorder.P1.Y *= -1;
                        LinkBorder.P2.Y *= -1;
                    }
                } else { // Out Link
                    if ((ThetaDegrees < 90.0) || (ThetaDegrees > 270.0)) {
                        LinkBorder.P1.Y *= -1;
                        LinkBorder.P2.Y *= -1;
                    } else if ((ThetaDegrees > 90.0) && (ThetaDegrees < 180.0)) {
                        LinkBorder.P1.Y *= -1;
                        LinkBorder.P2.Y *= -1;
                    }
                }
//             LinkBorder = LinkBorder.Rotate(ThetaDegrees * Math.PI/180.0);

                LinkBorder.Translate(UpStreamOpenGLLocation);
                LinkBorder = LinkBorder.Rotate(UpStreamOpenGLLocation, AngleInRadians);

                if (LaneIndex < nLanes) {
                    Point3D TP1 = new Point3D(LinkLength, (LaneIndex * NegLaneWidth * GLScale), LinkHeight);
                    Point3D TP2 = new Point3D(LinkLength, ((LaneIndex + 1) * NegLaneWidth * GLScale), LinkHeight);
                    Point3D TP3 = new Point3D((LinkLength - LaneWidth), (LaneIndex + 1) * NegLaneWidth * GLScale, LinkHeight);
                    Point3D TP4 = new Point3D((LinkLength - LaneWidth), (LaneIndex * NegLaneWidth * GLScale), LinkHeight);
                    TrafficLights[LaneIndex] = new Rectangle3D(TP1, TP2, TP3, TP4);
                }
            }

            LanesBounds = new RectangleD[nLanes];
            Separators = new Line3D[nLanes + 1];
            AlternativeRouteSeparators = new Line3D[nLanes + 1];

            // TrafficLightShiftValue
            TrafficLights = new Rectangle3D[nLanes];
            for (int LaneIndex = 0; LaneIndex < (nLanes + 1); LaneIndex++) {
                Point3D P1 = new Point3D(0, (LaneIndex * NegLaneWidth), LinkHeight);
                Point3D P2 = new Point3D(LinkLength, (LaneIndex * NegLaneWidth), LinkHeight);
                Point3D PAR1 = new Point3D(0, (LaneIndex * NegLaneWidth * LaneWidthEnlargement), LinkHeight);
                Point3D PAR2 = new Point3D(LinkLength, (LaneIndex * NegLaneWidth * LaneWidthEnlargement), LinkHeight);
                Separators[LaneIndex] = new Line3D(P1, P2);
                AlternativeRouteSeparators[LaneIndex] = new Line3D(PAR1, PAR2);
                LinkBorder = new Line3D(P1, P2);
                if (InLink) {
                    if ((ThetaDegrees > 90.0) && (ThetaDegrees < 180.0)) {
                        LinkBorder.P1.Y *= -1;
                        LinkBorder.P2.Y *= -1;
                    } else if ((ThetaDegrees > 180.0) && (ThetaDegrees < 270.0)) {
                        LinkBorder.P1.Y *= -1;
                        LinkBorder.P2.Y *= -1;
                    }
                } else { // Out Link
                    if ((ThetaDegrees < 90.0) || (ThetaDegrees > 270.0)) {
                        LinkBorder.P1.Y *= -1;
                        LinkBorder.P2.Y *= -1;
                    } else if ((ThetaDegrees > 90.0) && (ThetaDegrees < 180.0)) {
                        LinkBorder.P1.Y *= -1;
                        LinkBorder.P2.Y *= -1;
                    }
                }
//             LinkBorder = LinkBorder.Rotate(ThetaDegrees * Math.PI/180.0);

                LinkBorder.Translate(UpStreamOpenGLLocation);
                LinkBorder = LinkBorder.Rotate(UpStreamOpenGLLocation, AngleInRadians);

            }

            TotalVolumeLocation.X = (InLink) ? LinkLength - 0.03 : LinkLength;
            LeftTurnSplitLocation.X = (InLink) ? LinkLength - 0.01 : LinkLength;
            ThroughSplitLocation.X = (InLink) ? LinkLength - 0.01 : LinkLength;
            RightTurnSplitLocation.X = (InLink) ? LinkLength - 0.01 : LinkLength;

            TotalVolumeLocation.Y = Separators[nLanes].P1.Y - 0.02;
            LeftTurnSplitLocation.Y = Separators[nLanes].P1.Y - 0.02;
            ThroughSplitLocation.Y = Separators[nLanes].P1.Y - 0.02;
            RightTurnSplitLocation.Y = Separators[nLanes].P1.Y - 0.02;
        } catch (Exception Exception) {
            Exception.printStackTrace();
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="~~~~~~~~~~~~~~~~~~ Draw Methods ~~~~~~~~~~~~~~~~~~">
    private GLColorD LinkDrawingColor = new GLColorD();

    private void DrawAsRail(GL GL, boolean Highlight) {
        try {
            if (Highlight) {
                GL.glBegin(GL.GL_POLYGON);
                GL.glVertex3d(AlternativeRouteSeparators[0].P1.X, AlternativeRouteSeparators[0].P1.Y, AlternativeRouteSeparators[0].P1.Z);
                GL.glVertex3d(AlternativeRouteSeparators[0].P2.X, AlternativeRouteSeparators[0].P2.Y, AlternativeRouteSeparators[0].P2.Z);
                GL.glVertex3d(AlternativeRouteSeparators[nLanes].P2.X, AlternativeRouteSeparators[nLanes].P2.Y, AlternativeRouteSeparators[nLanes].P2.Z);
                GL.glVertex3d(AlternativeRouteSeparators[nLanes].P1.X, AlternativeRouteSeparators[nLanes].P1.Y, AlternativeRouteSeparators[nLanes].P1.Z);
                GL.glEnd();
                double Step = LaneWidth * LaneWidthEnlargement;
                double FalankaWidth = 0.2 * LaneWidth * GLScale * LaneWidthEnlargement; // width of the falanka
                int NumberOfFalankat = (int) (LinkLength / Step);
                for (int FalankahIndex = 0; FalankahIndex < NumberOfFalankat; FalankahIndex++) {
                    GL.glColor3d(0.20, 0.20, 0.80);
                    GL.glBegin(GL.GL_POLYGON);
                    GL.glVertex3d(Step * FalankahIndex, 0, 0.001);
                    GL.glVertex3d(Step * FalankahIndex, LaneWidth * GLScale * LaneWidthEnlargement * 1.4, 0.001);
                    GL.glVertex3d(Step * FalankahIndex + FalankaWidth, LaneWidth * GLScale * LaneWidthEnlargement * 1.4, 0.001);
                    GL.glVertex3d(Step * FalankahIndex + FalankaWidth, 0, 0.001);
                    GL.glEnd();
                }
            } else {

                GL.glColor3d(LinkDrawingColor.Red, LinkDrawingColor.Green, LinkDrawingColor.Blue);
                GL.glBegin(GL.GL_POLYGON);
                GL.glVertex3d(Separators[0].P1.X, Separators[0].P1.Y, Separators[0].P1.Z);
                GL.glVertex3d(Separators[0].P2.X, Separators[0].P2.Y, Separators[0].P2.Z);
                GL.glVertex3d(Separators[nLanes].P2.X, Separators[nLanes].P2.Y, Separators[nLanes].P2.Z);
                GL.glVertex3d(Separators[nLanes].P1.X, Separators[nLanes].P1.Y, Separators[nLanes].P1.Z);
                GL.glEnd();
                double Step = LaneWidth;
                double FalankaWidth = 0.2 * GLScale * LaneWidth; // width of the falanka
                int NumberOfFalankat = (int) (LinkLength / Step);
                for (int FalankahIndex = 0; FalankahIndex < NumberOfFalankat; FalankahIndex++) {
                    GL.glColor3d(0.20, 0.20, 0.80);
                    GL.glBegin(GL.GL_POLYGON);
                    GL.glVertex3d(Step * FalankahIndex, 0, 0.001);
                    GL.glVertex3d(Step * FalankahIndex, LaneWidth * 1.4 * GLScale, 0.001);
                    GL.glVertex3d(Step * FalankahIndex + FalankaWidth, LaneWidth * GLScale * 1.4, 0.001);
                    GL.glVertex3d(Step * FalankahIndex + FalankaWidth, 0, 0.001);
                    GL.glEnd();
                }

            }
        } catch (Exception Exception) {
            int x = 0;
        }
    }

    private void DrawAsRoad(GL GL, boolean Highlight) {
        try {
            if (Highlight) {
                GL.glBegin(GL.GL_POLYGON);
                GL.glVertex3d(AlternativeRouteSeparators[0].P1.X, AlternativeRouteSeparators[0].P1.Y, AlternativeRouteSeparators[0].P1.Z);
                GL.glVertex3d(AlternativeRouteSeparators[0].P2.X, AlternativeRouteSeparators[0].P2.Y, AlternativeRouteSeparators[0].P2.Z);
                GL.glVertex3d(AlternativeRouteSeparators[nLanes].P2.X, AlternativeRouteSeparators[nLanes].P2.Y, AlternativeRouteSeparators[nLanes].P2.Z);
                GL.glVertex3d(AlternativeRouteSeparators[nLanes].P1.X, AlternativeRouteSeparators[nLanes].P1.Y, AlternativeRouteSeparators[nLanes].P1.Z);
                GL.glEnd();
            } else {

                GL.glColor3d(LinkDrawingColor.Red, LinkDrawingColor.Green, LinkDrawingColor.Blue);
                GL.glBegin(GL.GL_POLYGON);
                GL.glVertex3d(Separators[0].P1.X, Separators[0].P1.Y, Separators[0].P1.Z);
                GL.glVertex3d(Separators[0].P2.X, Separators[0].P2.Y, Separators[0].P2.Z);
                GL.glVertex3d(Separators[nLanes].P2.X, Separators[nLanes].P2.Y, Separators[nLanes].P2.Z);
                GL.glVertex3d(Separators[nLanes].P1.X, Separators[nLanes].P1.Y, Separators[nLanes].P1.Z);
                GL.glEnd();
            }
        } catch (Exception Exception) {
            int x = 0;
        }
    }

    public void Draw(GL GL) {
        GL.glPushMatrix();
        try {

            if (UpStreamOpenGLLocation.X < 4000) {
                int h = 0;
            }
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);

            DrawAsRoad(GL, false);

        } catch (Exception Exception) {
            int x = 0;
        }
        GL.glPopMatrix();
    }

    public void ShowID(GL GL) {
        GL.glPushMatrix();
        try {
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);
            GL.glColor3d(DefaultColor.Red, DefaultColor.Green, DefaultColor.Blue);

            GL.glRasterPos3d(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, 0.03f);
            GLUT.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "" + UpStream.ID);
            GL.glRasterPos3d(DownStreamOpenGLLocation.X, DownStreamOpenGLLocation.Y, 0.03f);
            GLUT.glutBitmapString(GLUT.BITMAP_HELVETICA_18, "" + DownStream.ID);
        } catch (Exception Exception) {
        }
        GL.glPopMatrix();
    }

    public void Draw(GL GL, boolean ShowID) {
        GL.glPushMatrix();
        try {
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);
            GL.glColor3d(DefaultColor.Red, DefaultColor.Green, DefaultColor.Blue);

            DrawAsRoad(GL, false);

            if (ShowID) {
                ShowID(GL);
            }
        } catch (Exception Exception) {
        }
        GL.glPopMatrix();
    }

    public void Draw(GL GL, GLColorD Color) {
        GL.glPushMatrix();
        try {
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);
            GL.glColor3d(Color.Red, Color.Green, Color.Blue);

            DrawAsRoad(GL, false);

        } catch (Exception Exception) {
        }
        GL.glPopMatrix();
    }

    public void Draw(GL GL, GLColorD Color, boolean Highlight) {
        GL.glPushMatrix();
        try {
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);
            GL.glColor3d(Color.Red, Color.Green, Color.Blue);

            DrawAsRoad(GL, Highlight);

        } catch (Exception Exception) {
        }
        GL.glPopMatrix();
    }

    public void DrawLaneSeparators(GL GL) {
        GL.glPushMatrix();
        try {
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);

            for (int LaneIndex = 0; LaneIndex < (nLanes + 1); LaneIndex++) { // Igonring Link Borders
                GL.glLineWidth(0.2f);
                GL.glColor3d(LaneSeparatorColor.Red, LaneSeparatorColor.Green, LaneSeparatorColor.Blue);
                GL.glEnable(GL.GL_LINE_STIPPLE);
                GL.glLineStipple(3, (short) 0x1234);
                GL.glBegin(GL.GL_LINES);
                GL.glVertex3d(Separators[LaneIndex].P1.X, Separators[LaneIndex].P1.Y, Separators[LaneIndex].P1.Z);
                GL.glVertex3d(Separators[LaneIndex].P2.X, Separators[LaneIndex].P2.Y, Separators[LaneIndex].P2.Z);
                GL.glEnd();
                GL.glLineWidth(1f);
                GL.glDisable(GL.GL_LINE_STIPPLE);
            }
        } catch (Exception Exception) {
            int x = 0;
        }
        GL.glPopMatrix();
    }

    public void DrawLinkBorders(GL GL, boolean DisplayInfo) {
        GL.glPushMatrix();
        try {
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);
            GL.glColor3d(DirectionSeparatorColor.Red, DirectionSeparatorColor.Green, DirectionSeparatorColor.Blue);

            GL.glBegin(GL.GL_LINES);
            GL.glVertex3d(Separators[0].P1.X, Separators[0].P1.Y, Separators[0].P1.Z);
            GL.glVertex3d(Separators[0].P2.X, Separators[0].P2.Y, Separators[0].P2.Z);
            GL.glVertex3d(Separators[nLanes].P1.X, Separators[nLanes].P1.Y, Separators[nLanes].P1.Z);
            GL.glVertex3d(Separators[nLanes].P2.X, Separators[nLanes].P2.Y, Separators[nLanes].P2.Z);
            GL.glVertex3d(Separators[0].P1.X, Separators[0].P1.Y, Separators[0].P1.Z);
            GL.glVertex3d(Separators[nLanes].P1.X, Separators[nLanes].P1.Y, Separators[nLanes].P1.Z);
            GL.glVertex3d(Separators[0].P2.X, Separators[0].P2.Y, Separators[0].P2.Z);
            GL.glVertex3d(Separators[nLanes].P2.X, Separators[nLanes].P2.Y, Separators[nLanes].P2.Z);
            GL.glEnd();
            if (DisplayInfo) {
                GL.glColor3d(1, 0, 1);
                GL.glRasterPos3d(Separators[nLanes].P2.X, Separators[nLanes].P2.Y, Separators[nLanes].P2.Z);
                GLUT.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_10, String.format("Link[%s]", Link.ID));
            }
        } catch (Exception Exception) {
            int x = 0;
        }
        GL.glPopMatrix();
    }

    public void DrawLinkLine(GL GL, GLColorD Color) {
        GL.glPushMatrix();
        try {
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);

            GL.glColor3d(Color.Red, Color.Green, Color.Blue);
            GL.glBegin(GL.GL_LINES);
            GL.glVertex3d(Separators[0].P1.X, Separators[0].P1.Y, Separators[0].P1.Z);
            GL.glVertex3d(Separators[0].P2.X, Separators[0].P2.Y, Separators[0].P2.Z);
//        GL.glVertex3d(Separators[nLanes].P1.x, Separators[nLanes].P1.y, Separators[nLanes].P1.z);
//        GL.glVertex3d(Separators[nLanes].P2.x, Separators[nLanes].P2.y, Separators[nLanes].P2.z);
//        GL.glVertex3d(Separators[0].P1.x, Separators[0].P1.y, Separators[0].P1.z);
//        GL.glVertex3d(Separators[nLanes].P1.x, Separators[nLanes].P1.y, Separators[nLanes].P1.z);
//        GL.glVertex3d(Separators[0].P2.x, Separators[0].P2.y, Separators[0].P2.z);
//        GL.glVertex3d(Separators[nLanes].P2.x, Separators[nLanes].P2.y, Separators[nLanes].P2.z);
            GL.glEnd();
        } catch (Exception Exception) {
            int x = 0;
        }
        GL.glPopMatrix();
    }

    public void DrawLinkPolyLine(GL GL, GLColorD Color) {
        GL.glPushMatrix();
        try {
            GL.glTranslated(UpStreamOpenGLLocation.X, UpStreamOpenGLLocation.Y, UpStreamOpenGLLocation.Z);
            GL.glRotated(AngleInDegrees, 0, 0, 1);

            GL.glColor3d(Color.Red, Color.Green, Color.Blue);
            GL.glBegin(GL.GL_LINES);
            GL.glVertex3d(Separators[0].P1.X, Separators[0].P1.Y, Separators[0].P1.Z);
            GL.glVertex3d(Separators[0].P2.X, Separators[0].P2.Y, Separators[0].P2.Z);
//        GL.glVertex3d(Separators[nLanes].P1.x, Separators[nLanes].P1.y, Separators[nLanes].P1.z);
//        GL.glVertex3d(Separators[nLanes].P2.x, Separators[nLanes].P2.y, Separators[nLanes].P2.z);
//        GL.glVertex3d(Separators[0].P1.x, Separators[0].P1.y, Separators[0].P1.z);
//        GL.glVertex3d(Separators[nLanes].P1.x, Separators[nLanes].P1.y, Separators[nLanes].P1.z);
//        GL.glVertex3d(Separators[0].P2.x, Separators[0].P2.y, Separators[0].P2.z);
//        GL.glVertex3d(Separators[nLanes].P2.x, Separators[nLanes].P2.y, Separators[nLanes].P2.z);
            GL.glEnd();
        } catch (Exception Exception) {
            int x = 0;
        }
        GL.glPopMatrix();
    }
    // </editor-fold>
}
