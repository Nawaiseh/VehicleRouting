package UI.SimulationPanel;

import UI.Utils.OpenGLSimulationPanel;
import UI.Utils.OpenGLUtils.OpenGLMouseListener;
import UI.Utils.OpenGLUtils.OpenGLRenderer;
import UI.Utils.OpenGLUtils.Point3D;
import java.awt.event.MouseEvent;
import java.util.TreeMap;

import Data.Node;
import Data.Link;
import Data.Network;


public class GLMouseListener extends OpenGLMouseListener {

    protected OpenGLSimulationPanel OpenGLSimulationPanel = null;

    public GLMouseListener(OpenGLSimulationPanel newOpenGLSimulationPanel, OpenGLRenderer newOpenGLRenderer) {
        super(newOpenGLRenderer);
        OpenGLSimulationPanel = newOpenGLSimulationPanel;
    }

    @Override
    public void Dispose() {
        try {
            super.Dispose();
            OpenGLSimulationPanel = null;
            OpenGLCamera = null;
            OpenGLZoomer = null;
        } catch (Exception Exception) {
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~    Common Tools    ~~~~~~~~~~~~~~~~~~~ ">
    double dot(Point3D u, Point3D v) {
        return (u.X * v.X + u.Y * v.Y + u.Z * v.Z);
    }

    double norm(Point3D u) {
        return (Math.sqrt(u.X * u.X + u.Y * u.Y + u.Z * u.Z));
    }

    double d(Point3D p1, Point3D p2) {
        try {
            double x2 = (p1.X - p2.X) * (p1.X - p2.X);
            double y2 = (p1.Y - p2.Y) * (p1.Y - p2.Y);
            double z2 = (p1.Z - p2.Z) * (p1.Z - p2.Z);

            return (Math.sqrt(x2 + y2 + z2));
        } catch (Exception Exception) {
        }
        return -1;
    }

    double DistanceFrom3DLine(Point3D P, Link Link, boolean flag) {
        Network Network = OpenGLSimulationPanel.Network;
        Point3D P1 = Network.Nodes.get(Link.UpStream).Location;
        Point3D P0 = Network.Nodes.get(Link.DownStream).Location;
        Point3D v = new Point3D((P1.X - P0.X), (P1.Y - P0.Y), (P1.Z - P0.Z));
        Point3D w = new Point3D((P.X - P0.X), (P.Y - P0.Y), (P.Z - P0.Z));
        double c1 = dot(w, v);
        double c2 = dot(v, v);
        if (flag) {
            if (c1 <= 0) {
                return d(P, P0);
            }
            if (c2 <= c1) {
                return d(P, P1);
            }
        }
        double b = c1 / c2;
        Point3D Pb = new Point3D((P0.X + b * v.X), (P0.Y + b * v.Y), (P0.Z + b * v.Z));
        return d(P, Pb);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~ Find Network Items ~~~~~~~~~~~~~~~~~~~ ">

    private Node FindNode(Point3D Location, TreeMap<String, Node> Nodes) {
        Node Node = null;
        try {
            Point3D Movement = OpenGLCamera.GetMovement();
            Point3D OpenGLCenter = OpenGLRenderer.GetOpenGLCenter();
            double MinDistance = Double.MAX_VALUE;
            Point3D P = new Point3D((OpenGLCenter.X + Location.X + Movement.X), OpenGLCenter.Y + Movement.Y - Location.Y, 0);
            for (Node Intersection : Nodes.values()) {
                double Distance = d(P, Intersection.Location);
                if ((MinDistance > Distance) && (Distance < 0.5)) {
                    MinDistance = Distance;
                    Node = Intersection;
                }
            }
        } catch (Exception Exception) {
        }

        return Node;
    }

    private Link FindLink(Point3D Location, TreeMap<String, Link> Links) {
        Link Link = null;
        try {
            Point3D Movement = OpenGLCamera.GetMovement();
            Point3D OpenGLCenter = OpenGLRenderer.GetOpenGLCenter();
            Network Network = OpenGLSimulationPanel.Network;
            double MinDistance = Double.MAX_VALUE;
            Point3D P = new Point3D((OpenGLCenter.X + Location.X + Movement.X), OpenGLCenter.Y + Movement.Y - Location.Y, 0);
            for (Link aLink : Links.values()) {
                double Distance = DistanceFrom3DLine(P, aLink, true);
                if ((MinDistance > Distance) && (Distance < 0.5) && (Distance != -1)) {
                    MinDistance = Distance;
                    Link = aLink;
                }
            }
        } catch (Exception Exception) {
        }

        return Link;
    }

      // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~ Display On GUI Methods ~~~~~~~~~~~~~~~~~~~ ">

   

    // </editor-fold>

    private long PickNode(Point3D Location) {
        Node NearestNode = null;
        try {
            Point3D Movement = OpenGLCamera.GetMovement();
            Point3D OpenGLCenter = OpenGLRenderer.GetOpenGLCenter();
            Network Network = OpenGLSimulationPanel.Network;
            TreeMap<Long, Node> Nodes = Network.Nodes;
            double MinDistance = Double.MAX_VALUE;

            Point3D P = new Point3D((OpenGLCenter.X + Location.X + Movement.X), OpenGLCenter.Y + Movement.Y - Location.Y, 0);
            for (Node Node : Nodes.values()) {
                double Distance = d(P, Node.Location);
                if ((MinDistance > Distance) && (Distance < 0.5)) {
                    MinDistance = Distance;
                    NearestNode = Node;
                }
            }
        } catch (Exception Exception) {
        }
        if (NearestNode == null) {
            return -1;
        } else {
            return NearestNode.ID;
        }
    }

    private String PickLink(Point3D Location) {
        Link NearestLink = null;
        try {
            Point3D Movement = OpenGLCamera.GetMovement();
            Point3D OpenGLCenter = OpenGLRenderer.GetOpenGLCenter();
            Network Network = OpenGLSimulationPanel.Network;
            TreeMap<String, Link> Links = Network.Links;
            double MinDistance = Double.MAX_VALUE;

            Point3D P = new Point3D((OpenGLCenter.X + Location.X + Movement.X), OpenGLCenter.Y + Movement.Y - Location.Y, 0);
            for (Link Link : Links.values()) {
                double Distance = DistanceFrom3DLine(P, Link, true);
                if ((MinDistance > Distance) && (Distance < 0.5) && (Distance != -1)) {
                    MinDistance = Distance;
                    NearestLink = Link;
                }
            }
        } catch (Exception Exception) {
        }

        if (NearestLink == null) {
            return "NULL";
        } else {
            return NearestLink.ID;
        }
    }

    @Override
    public void mouseClicked(MouseEvent Event) {
        super.mouseClicked(Event);
        try {
            Point3D Center = OpenGLCamera.GetCenter();
            Point3D Eye = OpenGLCamera.GetEye();
            int ClickCount = Event.getClickCount();
            SimulationPanel SimulationPanel = (SimulationPanel) OpenGLSimulationPanel;
            Network Network = SimulationPanel.GetNetwork();
            GLRenderer GLRenderer = (GLRenderer) OpenGLRenderer;
            
            switch (ClickCount) {
                case 1: {
                    switch (PressedButton) {
                        case MouseEvent.BUTTON1:

//                            if (OverviewPanel.HeaderButton.getSelectButtonSelectionStatus()) {
//                                if (OverviewPanel.getIntersectionsButton().getMainButtonSelectedStatus()) {
//                                    if (OverviewPanel.getIntersectionsButton().getEditButtonSelectedStatus()) {
//                                        DisplayEditNode(FindNode(Location, Network.getIntersectionsTreeWithNameKey()), Event.getPoint());
//                                    } else {
//                                        DisplayNode(FindNode(Location, Network.getIntersectionsTreeWithNameKey()), Event.getPoint());
//                                    }
//                                } else if (OverviewPanel.getLinksButton().getMainButtonSelectedStatus()) {
//                                    DisplayLink(FindLink(Location, Network.getRoads()), Event.getPoint());
//                                } else if (OverviewPanel.getZonesButton().getMainButtonSelectedStatus()) {
//                                    DisplayZone(FindZone(Location, Network.getZones()), Event.getPoint());
//                                } else if (OverviewPanel.getTransitLinesButton().getMainButtonSelectedStatus()) {
//                                    DisplayTransitLine(FindTransitLine(Location, Network.getTransitLines()), Event.getPoint());
//                                } else if (OverviewPanel.getODPairsButton().getMainButtonSelectedStatus()) {
//                                    DisplayODPair(FindZone(Location, Network.getZones()), Event.getPoint());
//                                } else if (OverviewPanel.getParkingLotsButton().getMainButtonSelectedStatus()) {
//                                    DisplayParkingLot(FindParkingLot(Location, Network.getParkingLots()), Event.getPoint());
//                                } else if (OverviewPanel.getCamerasButton().getMainButtonSelectedStatus()) {
//                                    DisplayCamera(FindCamera(Location, OverviewPanel.getCamerasTable().Cameras), Event.getPoint());
//                                } else if (OverviewPanel.getDynamicMessageSignsButton().getMainButtonSelectedStatus()) {
//                                    //  DisplayDMS(FindDMS(Location, Network.getActiveDynamicMessageSigns()), Event.getPoint());
//                                    DisplayDMS(FindDMS(Location, ((GLRenderer) OpenGLRenderer).DMSs), Event.getPoint());
//                                } else if (OverviewPanel.getFacilitiesButton().getMainButtonSelectedStatus()) {
//                                    //    FindFacilityAndShowItsWindow(Location, Event.getPoint());
//                                    DisplayFacility(FindFacility(Location, Network.getFacilities()), Event.getPoint());
//                                }
//                            } else if (OverviewPanel.HeaderButton.getAddButtonSelectionStatus()) {
//                                //FindZoneForSplit(x, y, Event.getPoint());
//                            } else {
//                                //  myOpenGLListener.Polygon.AddPoint(new Point3D((x + this.myCamera.getMovement().x), -(y - this.myCamera.getMovement().y), 0));
//                            }
                            break;
                        case MouseEvent.BUTTON3:
                            break;
                    }
                    break;
                }
                case 2: {
                    switch (PressedButton) {
                        case MouseEvent.BUTTON1:
                            Center.X = Eye.X = Center.X + Location.X;
                            Center.Y = Eye.Y = Center.Y - Location.Y;
                            Eye.Z = -3;
                            break;
                        case MouseEvent.BUTTON2:
                            break;
                        case MouseEvent.BUTTON3:
                            OpenGLCamera.reset();
                            break;
                    }
                    break;
                }
            }
        } catch (Exception Exception) {
        }

    }
}
