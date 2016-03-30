package UI.SimulationPanel;

import UI.Utils.OpenGLSimulationPanel;
import UI.Utils.OpenGLUtils.GLColorD;
import UI.Utils.OpenGLUtils.OpenGLLink;
import UI.Utils.OpenGLUtils.Point3D;
import UI.Utils.OpenGLUtils.Polygon2D;
import java.text.DecimalFormat;
import java.util.TreeMap;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;

import Data.Link;

import Data.Node;
import Data.Network;
import Data.ReaderWriter.Writer;

import java.util.ArrayList;

public class GLRenderer extends UI.Utils.OpenGLUtils.OpenGLRenderer {

    protected GLColorD NodeColor = new GLColorD(0.095f, 0.4f, 0.772f, 0.5f);
    protected GLColorD HighLightedColor = new GLColorD(0.95f, 0.4f, 0.272f, 0.5f);
    protected double NodeRadius = 0.02;
    protected double ZoneRadius = 0.04;
    protected double HighlightedNodeRadius = 0.4;
    protected double NodeShapeSmoothness = 50;
    protected Polygon2D NodeShape = new Polygon2D();
    private Polygon2D ZoneShape = new Polygon2D();
    protected Polygon2D HighlightedNodeShape = new Polygon2D();
    boolean draw = false;
    int OldTime = 0;

    public TreeMap<String, String> SelectedFacilities = new TreeMap();
    public TreeMap<Long, Long> SelectedNodes = new TreeMap();
    public TreeMap<String, String> SelectedLinks = new TreeMap();
    public TreeMap<Long, Long> SelectedZones = new TreeMap();
    public Point3D SelectedEventLocation = null;

    public GLRenderer(OpenGLSimulationPanel newOpenGLSimulationPanel, Network newNetwork) {
        super(newOpenGLSimulationPanel);
        try {
            OpenGLSimulationPanel = newOpenGLSimulationPanel;
            NodeShape.CreateCircle(NodeRadius, NodeShapeSmoothness);
            ZoneShape.CreateCircle(ZoneRadius, NodeShapeSmoothness);
            HighlightedNodeShape.CreateCircle(HighlightedNodeRadius, NodeShapeSmoothness);
            Network = newNetwork;
            OpenGLMouseListener = new GLMouseListener(OpenGLSimulationPanel, this);
            Point3D Eye = OpenGLCamera.GetEye();
            Point3D Center = OpenGLCamera.GetCenter();

            Eye.X = Center.X = 0;
            Eye.Y = Center.Y = 0;
            Center.Z = 0;
            Eye.Z = -1 * VerticalRange;
            OpenGLCamera.SetResetPosition(Eye);
        } catch (Exception Exception) {
        }
    }

    @Override
    public GLMouseListener getOpenGLMouseListener() {
        try {
            return (GLMouseListener) OpenGLMouseListener;
        } catch (Exception Exception) {
        }
        return null;
    }

    @Override
    public void init(GLAutoDrawable GLAutoDrawable) {
        try {
            super.init(GLAutoDrawable);
        } catch (Exception Exception) {
        }

    }

    public void setNetwork(Network newNetwork) {
        try {
            //PrepareData(Network = newNetwork);
            draw = true;
        } catch (Exception Exception) {
        }

    }

    @Override
    protected void SetMouseListenerViewPort(GL GL) {
        try {
            super.SetMouseListenerViewPort(GL);
            OpenGLMouseListener.setViewPort(ViewPort);
            OpenGLMouseListener.setProjectionMatrix(ProjectionMatrix);
            OpenGLMouseListener.setModelMatrix(ModelMatrix);
        } catch (Exception Exception) {
        }

    }

    public void SetTime(int ss) {
        try {
            if (OldTime == ss) {
                return;
            }
            OldTime = ss;
            ss = (ss % 86400);
            int hour = ss / 3600;
            int min = (ss - (hour * 3600)) / 60;
            int second = ss - ((hour * 3600) + (min * 60));
            OpenGLSimulationPanel.Clock.SetTime(hour, min, second);
        } catch (Exception Exception) {
        }

//        String PMAM = (hour >= 12) ? " PM" : " AM";
//        hour = (hour > 12) ? hour - 12 : hour;
        //  return "" + ((hour > 9) ? hour : "0" + hour) + ":" + ((min > 9) ? min : "0" + min) + ":" + ((second > 9) ? second : "0" + second) + PMAM;
    }

    @Override
    protected void InitializeOpenGL(GL GL) {
        try {
            super.InitializeOpenGL(GL);
            if (Network != null) {
                // SetTime(Network.timeNow);
                if (draw) {
                    init(DrawablePanel);
                    draw = false;
                }
            }
        } catch (Exception Exception) {
        }

    }

    @Override
    public void Dispose() {
        try {
            super.Dispose();
            if (OpenGLMouseListener != null) {
                ((GLMouseListener) OpenGLMouseListener).Dispose();
            }
            OpenGLMouseListener = null;
            ClearSelection();
            SelectedFacilities = null;
            SelectedNodes = null;
            SelectedLinks = null;
            SelectedZones = null;
        } catch (Exception Exception) {
        }

    }

    private void DrawAllNodes(GL GL, boolean ShowID) {
        try {
            TreeMap<Long, Node> Nodes = Network.Nodes;
            if (Nodes == null || Nodes.isEmpty()) {
                return;
            }
            TreeMap<Long, Node> MainNodes = OpenGLSimulationPanel.Network.Nodes;
            for (Node Node : Nodes.values()) {
                long NodeKey = Node.ID;
                Point3D Location = new Point3D(Node.OpenGLLocation.X, Node.OpenGLLocation.Y, 0);
                GL.glColor3f(NodeColor.Red, NodeColor.Green, NodeColor.Blue);
                if (HightedItemKeys[1].equals(NodeKey) || HightedItemKeys[2].equals(NodeKey)) {
                    HighlightedNodeShape.draw(GL, HighLightedColor, Location, 1);
                } else {
                    NodeShape.draw(GL, NodeColor, Location, 1);
                }

                if (ShowID) {
                    GL.glColor3f(0.1F, 0.1f, 1f);
                    GL.glRasterPos3d(Location.X+0.03, Location.Y+0.03, 0.03f);
                    GLUT.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, "" + Node.ID);
                }
            }
        } catch (Exception Exception) {
            int h = 3;
        }
    }
    DecimalFormat DF = new DecimalFormat("0.00");
    double CutDistance = 0.04;
    GLColorD OtherLinkLineColor = new GLColorD(0.4f, 0.4f, 0.4f, 0.4f);

    private void DrawAllLinks(GL GL, boolean ShowID) {
        double W = 0.2;
        double H = 0.25;
        int Index = 1;
        int Count = OpenGLLinks.size();
        int CC = 0;
        for (OpenGLLink OpenGLLink : OpenGLLinks.values()) {
            Index++;
            try {
                OpenGLLink.Draw(GL);
                OpenGLLink.DrawLaneSeparators(GL);
                OpenGLLink.DrawLinkLine(GL, OpenGLLink.DirectionSeparatorColor);
                OpenGLLink.DrawLinkBorders(GL, false);

            } catch (Exception Exception) {
                int x = 0;
            }
        }
    }

    public void ClearSelection() {
        try {
            //DMSs.clear();
            SelectedFacilities.clear();
            SelectedNodes.clear();
            SelectedLinks.clear();
            SelectedZones.clear();
        } catch (Exception Exception) {
        }

    }

    private void DrawTaxis(GL GL) {

    }

    private void DrawPassengers(GL GL) {

    }

    @Override
    public void display(GLAutoDrawable GLAutoDrawable) {
        try {

            super.display(GLAutoDrawable);
            GL GL = GLAutoDrawable.getGL();

            DrawAllNodes(GL, true);
            DrawAllLinks(GL, false);

            DrawTaxis(GL);
            DrawPassengers(GL);

        } catch (Exception Exception) {

            int x = 0;
        }

    }
}
