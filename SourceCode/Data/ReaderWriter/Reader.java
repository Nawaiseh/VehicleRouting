package Data.ReaderWriter;

import Data.*;
import UI.UserInterface;
import UI.Utils.OpenGLUtils.Point3D;
import UI.WaitingWindow;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader {

    public static final Logger MyLogger = Logger.getLogger(Reader.class.getName());
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~ DocumentBuilder Error Handler ~~~~~~~~~~~~~~~ ">
    public static ErrorHandler DocumentBuilderErrorHandler = new ErrorHandler() {
        @Override
        public void warning(SAXParseException exception) throws SAXException {
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
        }
    };
    public String BasePath;
    public String InputFolder = "Input";
    public String NodesFileName = "Nodes.xml";
    public String LinksFileName = "Links.xml";
    public String PassengersFileName = "Passenger.xml";
    public String TaxisFileName = "Taxis.xml";
    public String OnBoardPassengersFileName = "OnBoardPassengers.xml";
    public UserInterface UserInterface = null;

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~       Constuctors       ~~~~~~~~~~~~~~ ">
    public Reader() {

    }

    public Reader(String BasePath) {
        this.BasePath = BasePath;
        InputFolder = String.format("%s\\%s", BasePath, InputFolder);
    }

    public Reader(UserInterface DIRECTView) {

        UserInterface = DIRECTView;
    }
    //</editor-fold>

    public Reader(UserInterface DIRECTView, String BasePath) {
        UserInterface = DIRECTView;
        this.BasePath = BasePath;
        InputFolder = String.format("%s\\%s", BasePath, InputFolder);

    }
    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~        Open XML Document        ~~~~~~~~~~~~~~ ">

    protected Document OpenXMLDocument(String FileName) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            docBuilder.setErrorHandler(DocumentBuilderErrorHandler);
            Document Document = docBuilder.parse(new File(FileName));
            // normalize text representation
            Document.getDocumentElement().normalize();

            return Document;
        } catch (Exception Exception) {
            MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s'", FileName), Exception);
            return null;
        }
    }

    //</editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~  Private XML Common Methods   ~~~~~~~~~~~~~~~ ">
    public String GetNodeValue(Element element, String TagName) {
        NodeList NodeListTag = element.getElementsByTagName(TagName);
        Element TagElement = (Element) NodeListTag.item(0);
        NodeList nodeList = TagElement.getChildNodes();
        if (nodeList.getLength() == 0) {
            return "";
        }
        String result = ((Node) nodeList.item(0)).getNodeValue().trim();
        return result;
    }

    public String GetNodeValue(Element Element) {
        NodeList nodeList = Element.getChildNodes();
        if (nodeList.getLength() == 0) {
            return "";
        }
        String result = ((Node) nodeList.item(0)).getNodeValue().trim();
        return result;
    }

    public Element GetNode(Element element, String TagName) {
        NodeList NodeListTag = element.getElementsByTagName(TagName);
        Element TagElement = (Element) NodeListTag.item(0);
        return TagElement;
    }
    // </editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~       Parse Nodes Data       ~~~~~~~~~~~~~~ ">
    public void ParseNodesData(Network Network) {
        WaitingWindow.status = "Reading Nodes Data";
        String FileName = String.format("%s\\%s", InputFolder, NodesFileName);

        try {
            Document Document = OpenXMLDocument(FileName);
            NodeList NodesNodeList = Document.getElementsByTagName("Node");
            int NodesCount = NodesNodeList.getLength();
            if (NodesCount == 0) {
                MyLogger.log(Level.SEVERE, String.format("'%s' Is Empty", FileName));
                return;
            }
            for (int NodeIndex = 0; NodeIndex < NodesCount; NodeIndex++) {
                Node NodeNode = NodesNodeList.item(NodeIndex);
                if (NodeNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element NodeElement = (Element) NodeNode;

                    long NodeID = Long.parseLong(NodeElement.getAttribute("ID"));
                    double X = Double.parseDouble(NodeElement.getAttribute("X"));
                    double Y = Double.parseDouble(NodeElement.getAttribute("Y"));
                    if (Network.Nodes.containsKey(NodeID)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : ID %d is already Exist", FileName, NodeID));
                        continue;
                    }
                    Data.Node Node = new Data.Node();
                    Node.Index = NodeIndex;
                    Node.ID = NodeID;
                    Node.Location.X = X;
                    Node.Location.Y = Y;

                    if (!Network.Nodes.containsKey(Network.Furthest_Node_On_X_Axis)) {
                        Network.Furthest_Node_On_X_Axis = NodeID;
                        Network.Furthest_Node_On_Y_Axis = NodeID;
                        Network.Nearest_Node_On_X_Axis = NodeID;
                        Network.Nearest_Node_On_Y_Axis = NodeID;
                    } else {

                        if (Network.Nodes.get(Network.Furthest_Node_On_X_Axis).Location.X < X) {
                            Network.Furthest_Node_On_X_Axis = NodeID;
                        }

                        if (Network.Nodes.get(Network.Furthest_Node_On_Y_Axis).Location.Y < Y) {
                            Network.Furthest_Node_On_Y_Axis = NodeID;
                        }

                        if (Network.Nodes.get(Network.Nearest_Node_On_X_Axis).Location.X > X) {
                            Network.Nearest_Node_On_X_Axis = NodeID;
                        }
                        if (Network.Nodes.get(Network.Nearest_Node_On_Y_Axis).Location.Y > Y) {
                            Network.Nearest_Node_On_Y_Axis = NodeID;
                        }
                    }
                    Network.Nodes.put(Node.ID, Node);
                    Network.Nodes_SortedByIndex.put(Node.Index, Node);
                }
            }

        } catch (Exception Exception) {
            MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s'", FileName), Exception);
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~       Parse Links Data       ~~~~~~~~~~~~~~ ">

    public void ParseLinksData(Network Network) {
        WaitingWindow.status = "Reading Links Data";
        String FileName = String.format("%s\\%s", InputFolder, LinksFileName);

        try {
            Document Document = OpenXMLDocument(FileName);
            NodeList LinksNodeList = Document.getElementsByTagName("Link");
            int LinksCount = LinksNodeList.getLength();
            if (LinksCount == 0) {
                MyLogger.log(Level.SEVERE, String.format("'%s' Is Empty", FileName));
                return;
            }

            for (int LinkIndex = 0; LinkIndex < LinksCount; LinkIndex++) {
                Node LinkNode = LinksNodeList.item(LinkIndex);
                if (LinkNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element LinkElement = (Element) LinkNode;
                    long UpStream = Long.parseLong(LinkElement.getAttribute("UpStream"));
                    long DownStream = Long.parseLong(LinkElement.getAttribute("DownStream"));

                    String LinkID = String.format("%s-%s", UpStream, DownStream);
                    if (Network.Links.containsKey(LinkID)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Link %s alredy Exist", FileName, LinkID));
                        continue;
                    }
                    if (!Network.Nodes.containsKey(UpStream)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Upstream %d Does not Exist", FileName, UpStream));
                        continue;
                    }
                    if (!Network.Nodes.containsKey(DownStream)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : DownStream %d Does not Exist", FileName, DownStream));
                        continue;
                    }
                    Link Link = new Link();

                    Link.UpStream = UpStream;
                    Link.DownStream = DownStream;

                    Point3D P1 = Network.Nodes.get(UpStream).Location;
                    Point3D P2 = Network.Nodes.get(DownStream).Location;
                    double Delta_X = P1.X - P2.X;
                    double Delta_Y = P1.Y - P2.Y;

                    Link.Length = (float) Math.sqrt(Delta_X * Delta_X + Delta_Y * Delta_Y);
                    Link.TravelTime = (Link.Length / Link.CurrentSpeed) * 3600f;
                    Link.ID = LinkID;
                    Link.Index = LinkIndex;

                    try {

                        Network.Nodes.get(UpStream).OutLinks.put(Link.ID, Link);
                        Network.Nodes.get(DownStream).InLinks.put(Link.ID, Link);

                    } catch (Exception e) {

                        int x = 0;
                    }

                    if (!Network.Nodes.get(Link.UpStream).AdjcantNodes.contains(Link.DownStream)) {
                        Network.Nodes.get(Link.UpStream).AdjcantNodes.add(Link.DownStream);
                    }
                    if (!Network.Nodes.get(Link.DownStream).AdjcantNodes.contains(Link.UpStream)) {
                        Network.Nodes.get(Link.DownStream).AdjcantNodes.add(Link.UpStream);
                    }


                    Network.Links.put(LinkID, Link);
                    Network.Links_SortedByIndex.put(Link.Index, Link);
                }
            }
        } catch (Exception Exception) {
            MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s'", FileName), Exception);
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~       Parse Taxis Data       ~~~~~~~~~~~~~~ ">

    public void ParseTaxisData(Network Network) {
        WaitingWindow.status = "Reading Links Data";
        String FileName = String.format("%s\\%s", InputFolder, TaxisFileName);

        try {
            Document Document = OpenXMLDocument(FileName);
            NodeList TaxisNodeList = Document.getElementsByTagName("Taxi");
            int TaxisCount = TaxisNodeList.getLength();
            if (TaxisCount == 0) {
                MyLogger.log(Level.SEVERE, String.format("'%s' Is Empty", FileName));
                return;
            }

            for (int TaxiIndex = 0; TaxiIndex < TaxisCount; TaxiIndex++) {
                Node TaxiNode = TaxisNodeList.item(TaxiIndex);
                if (TaxiNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element TaxiElement = (Element) TaxiNode;
                    long ID = Long.parseLong(TaxiElement.getAttribute("ID"));
                    int Capacity = Integer.parseInt(TaxiElement.getAttribute("Capacity"));
                    int OnBoard = Integer.parseInt(TaxiElement.getAttribute("OnBoard"));
                    long Location = Long.parseLong(TaxiElement.getAttribute("Location"));

                    if (!Network.Nodes.containsKey(Location)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Location %d Does not Exist", FileName, Location));
                        continue;
                    }

                    if (Network.Taxis.containsKey(ID)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Taxi %d Is Duplicated", FileName, ID));
                        continue;
                    }

                    Taxi Taxi = new Taxi();
                    Taxi.ID = ID;
                    Taxi.Index = TaxiIndex;
                    Taxi.Capacity = Capacity;
                    Taxi.OnBoard = OnBoard;
                    Taxi.Location = Location;
                    Taxi.LocationIndex = Network.Nodes.get(Location).Index;

Network.V1.put(Location, Location);
                    Network.Taxis.put(ID, Taxi);
                    Network.Taxis_SortedByIndex.put(Taxi.Index, Taxi);
                }
            }
        } catch (Exception Exception) {
            MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s'", FileName), Exception);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~       Parse Passengers Data       ~~~~~~~~~~~~~~ ">
    public void ParsePassengersData(Network Network) {
        WaitingWindow.status = "Reading Links Data";
        String FileName = String.format("%s\\%s", InputFolder, PassengersFileName);
        try {
            Document Document = OpenXMLDocument(FileName);
            NodeList PassengersNodeList = Document.getElementsByTagName("Passenger");
            int PassengersCount = PassengersNodeList.getLength();
            if (PassengersCount == 0) {
                MyLogger.log(Level.SEVERE, String.format("'%s' Is Empty", FileName));
                return;
            }

            for (int PassengerIndex = 0; PassengerIndex < PassengersCount; PassengerIndex++) {
                Node PassengerNode = PassengersNodeList.item(PassengerIndex);
                if (PassengerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element PassengerElement = (Element) PassengerNode;
                    long ID = Long.parseLong(PassengerElement.getAttribute("ID"));
                    long Origin = Long.parseLong(PassengerElement.getAttribute("Origin"));
                    long Destination = Long.parseLong(PassengerElement.getAttribute("Destination"));

                    long PickupTime = Long.parseLong(PassengerElement.getAttribute("PickupTime"));
                    long DropoffTime = Long.parseLong(PassengerElement.getAttribute("DropoffTime"));
                    float Revenue = Float.parseFloat(PassengerElement.getAttribute("Revenue"));
                    long RideDuration = Long.parseLong(PassengerElement.getAttribute("RideDuration"));

                    if (Network.Passengers.containsKey(ID)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Duplicate Passenger %d", FileName, ID));
                        continue;
                    }

                    if (!Network.Nodes.containsKey(Origin)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Origin %d Does not Exist", FileName, Origin));
                        continue;
                    }
                    if (!Network.Nodes.containsKey(Destination)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Destination %d Does not Exist", FileName, Destination));
                        continue;
                    }

                    Passenger Passenger = new Passenger();

                    Passenger.ID = ID;
                    Passenger.Index = PassengerIndex;
                    Passenger.Origin = Origin;
                    Passenger.Destination = Destination;

                    Passenger.OriginIndex = Network.Nodes.get(Origin).Index;
                    Passenger.Destination = Network.Nodes.get(Destination).Index;

                    Passenger.PickupTime = PickupTime;
                    Passenger.DropoffTime = DropoffTime;
                    Passenger.Revenue = Revenue;
                    Passenger.RideDuration = RideDuration;

                    Network.Passengers.put(ID, Passenger);
                    Network.SeekerPassengers.put(ID, ID);

                    Network.Passengers_SortedByIndex.put(Passenger.Index, Passenger);
                    Network.SeekerPassengers_SortedByIndex.put(Passenger.Index, Passenger.ID);
                }
            }
        } catch (Exception Exception) {
            MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s'", FileName), Exception);
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~       Parse Onboard Passengers Data       ~~~~~~~~~~~~~~ ">

    public void ParseOnboardPassengersData(Network Network) {
        WaitingWindow.status = "Reading Links Data";
        String FileName = String.format("%s\\%s", InputFolder, OnBoardPassengersFileName);

        try {
            Document Document = OpenXMLDocument(FileName);
            NodeList PassengersNodeList = Document.getElementsByTagName("Passenger");
            int PassengersCount = PassengersNodeList.getLength();
            if (PassengersCount == 0) {
                MyLogger.log(Level.SEVERE, String.format("'%s' Is Empty", FileName));
                return;
            }

            for (int PassengerIndex = 0; PassengerIndex < PassengersCount; PassengerIndex++) {
                Node PassengerNode = PassengersNodeList.item(PassengerIndex);
                if (PassengerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element PassengerElement = (Element) PassengerNode;
                    long PassengerID = Long.parseLong(PassengerElement.getAttribute("ID"));
                    long TaxiID = Long.parseLong(PassengerElement.getAttribute("Taxi"));

                    if (!Network.Passengers.containsKey(PassengerID)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Passenger %d Does not Exist", FileName, PassengerID));
                        continue;
                    }

                    if (!Network.Taxis.containsKey(TaxiID)) {
                        MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s' : Taxi %d Does not Exist", FileName, TaxiID));
                        continue;
                    }

                    OnBoardPassenger OnBoardPassenger = null;
                    try {
                        OnBoardPassenger = new OnBoardPassenger(Network.Passengers.get(PassengerID));
                    } catch (Exception e) {
                        int x = 0;
                    }
                    OnBoardPassenger.TaxiID = TaxiID;

                    OnBoardPassenger.TaxiIndex = Network.Taxis.get(TaxiID).Index;

                    Network.OnBoardPassengers_SortedByPassengerID.put(PassengerID, OnBoardPassenger);
                    Network.OnBoardPassengers_SortedByPassengerIndex.put(OnBoardPassenger.Index, OnBoardPassenger);

                    if (!Network.OnBoardPassengers_SortedByTaxiID.containsKey(TaxiID)) {
                        Network.OnBoardPassengers_SortedByTaxiID.put(TaxiID, new TreeMap());
                        Network.OnBoardPassengers_SortedByTaxiIndex.put(Network.Taxis.get(TaxiID).Index, new TreeMap());
                    }

                    if (!Network.OnBoardPassengers_SortedByTaxiID.get(TaxiID).containsKey(PassengerID)) {
                        Network.OnBoardPassengers_SortedByTaxiID.get(TaxiID).put(PassengerID, OnBoardPassenger);
                        Network.OnBoardPassengers_SortedByTaxiIndex.get(Network.Taxis.get(TaxiID).Index).put(OnBoardPassenger.Index, OnBoardPassenger);

                    } else {
                        MyLogger.log(Level.SEVERE, String.format("Duplicate OnBoard Passenger [%d]", OnBoardPassenger.ID));
                    }
                    Network.Taxis.get(OnBoardPassenger.TaxiID).OnBoard++;

                    Network.SeekerPassengers.remove(PassengerID);
                    Network.SeekerPassengers_SortedByIndex.remove(OnBoardPassenger.Index);
                }
            }
        } catch (Exception Exception) {
            MyLogger.log(Level.SEVERE, String.format("Error In Parsing '%s'", FileName), Exception);
        }
    }

    //</editor-fold>
    public void ParseNetworkData(Network Network) {

  //     ParseNodesData(Network);
    //     ParseLinksData(Network);
        ParseTaxisData(Network);
        ParsePassengersData(Network);
        ParseOnboardPassengersData(Network);


        for (long PassengerID : Network.SeekerPassengers.keySet()) {


            Network.V3.put(Network.Passengers.get(PassengerID).Origin, Network.Passengers.get(PassengerID).Origin);
            Network.V4.put(Network.Passengers.get(PassengerID).Destination, Network.Passengers.get(PassengerID).Destination);

        }

    }
}
