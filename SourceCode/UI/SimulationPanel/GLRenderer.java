package UI.SimulationPanel;

import UI.Utils.OpenGLSimulationPanel;
import UI.Utils.OpenGLUtils.GLColorD;
import UI.Utils.OpenGLUtils.OpenGLLink;
import UI.Utils.OpenGLUtils.Point3D;
import UI.Utils.OpenGLUtils.Polygon2D;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.TreeMap;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import java.util.Timer;
import java.util.TimerTask;

import Engine.RoutingProblem_CPLEX;
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

    private Timer Timer = new Timer();
    private float RefreshRate = 1;
    private int SelectedPassenger = -1;
    private int SelectedTaxi = -1;

    public boolean ShowPassengers = false;
    public boolean ShowTaxis = false;

    public GLRenderer(OpenGLSimulationPanel newOpenGLSimulationPanel, Network newNetwork) {
        super(newOpenGLSimulationPanel);

        Timer.schedule(new RemindTask(), 0, 500);

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
                    GL.glRasterPos3d(Location.X + 0.03, Location.Y + 0.03, 0.03f);
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
        Link Link;
        Point3D UpStream;
        Point3D DownStream;
        for (Data.Taxi Taxi : Network.Taxis.values()) {
            if (SelectedTaxi != Taxi.Index) {
                //      continue;
            }
            GL.glLineWidth(Taxi.LineWidth);
            GL.glColor3f(Taxi.Color.Red, Taxi.Color.Green, Taxi.Color.Blue);
            GL.glBegin(GL.GL_LINES);
            int Order = 0;
            for (String LinkID : Taxi.PathLinks) {
                Link = Network.Links.get(LinkID);
                UpStream = Network.Nodes.get(Link.UpStream).OpenGLLocation;
                DownStream = Network.Nodes.get(Link.DownStream).OpenGLLocation;

                GL.glVertex3d(UpStream.X, UpStream.Y, UpStream.Z);
                GL.glVertex3d(DownStream.X, DownStream.Y, DownStream.Z);

            }
            GL.glEnd();

            for (Long NodeID : Taxi.NodeTimes.keySet()) {
                UpStream = Network.Nodes.get(NodeID).OpenGLLocation;
                Order++;
                try {
                    GL.glRasterPos3d(UpStream.X + 0.05, UpStream.Y - 0.15, 0.03f);
                    GLUT.glutBitmapString(GLUT.BITMAP_HELVETICA_12, String.format("T%d_%s @t=%d", Taxi.ID, NodeID, Taxi.NodeTimes.get(NodeID).intValue()));
                } catch (Exception E) {
                    int x = 0;
                }
            }
        }
        GL.glLineWidth(1);
    }

    private void DrawPassengers(GL GL) {

        Node Node;

        for (Data.Passenger Passenger : Network.Passengers.values()) {
            if (SelectedPassenger != Passenger.Index) {
                //      continue;
            }
            GL.glLineWidth(Passenger.LineWidth);
            GL.glColor3f(Passenger.Color.Red, Passenger.Color.Green, Passenger.Color.Blue);
            GL.glBegin(GL.GL_LINE_STRIP);
            int Order = 0;
            for (Long NodeID : Passenger.PathNodes) {
                Node = Network.Nodes.get(NodeID);
                Point3D Location = new Point3D(Node.OpenGLLocation.X, Node.OpenGLLocation.Y, 0);
                GL.glVertex3d(Location.X, Location.Y, Location.Z);

            }
            GL.glEnd();

            for (Long NodeID : Passenger.PathNodes) {
                Order++;
                Node = Network.Nodes.get(NodeID);
                Point3D Location = new Point3D(Node.OpenGLLocation.X, Node.OpenGLLocation.Y, 0);
                try {
                    GL.glRasterPos3d(Location.X + 0.05, Location.Y - 0.15, 0.03f);
                    GLUT.glutBitmapString(GLUT.BITMAP_HELVETICA_18, String.format("P%d_%d", Passenger.ID, Order));
                } catch (Exception E) {
                    int x = 0;
                }
            }
        }
        GL.glLineWidth(1);
    }

    @Override
    public void display(GLAutoDrawable GLAutoDrawable) {
        try {

            super.display(GLAutoDrawable);
            GL GL = GLAutoDrawable.getGL();

            DrawAllNodes(GL, true);
            DrawAllLinks(GL, false);
            if (ShowTaxis) {
                DrawTaxis(GL);
            }
            if (ShowPassengers) {
                DrawPassengers(GL);
            }

        } catch (Exception Exception) {

            int x = 0;
        }

    }

    class RemindTask extends TimerTask {

        public void run() {
            try {
                if (!Network.Passengers.isEmpty()) {
                    SelectedPassenger++;
                    SelectedPassenger = SelectedPassenger % Network.Passengers.size();
                }
            } catch (Exception E) {
            }
        }
    }

}
