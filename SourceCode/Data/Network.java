package Data;

import static Data.ReaderWriter.Reader.MyLogger;
import UI.UserInterface;
import UI.Utils.OpenGLUtils.Point3D;
import java.util.Date;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings({"UnusedAssignment"})
public class Network {

    public static final Logger MyLogger = Logger.getLogger(Network.class.getName());

    public String Name = "Orignal Network";
    public UserInterface DView = null;
    public Date Today;

    public TreeMap<Long, Node> Nodes = new TreeMap<>();
    public TreeMap<String, Link> Links = new TreeMap<>();
    public TreeMap<Long, Passenger> Passengers = new TreeMap<>();

    public TreeMap<Long, Long> PickupLocations = new TreeMap<>();
    public TreeMap<Long, Long> DropoffLocations = new TreeMap<>();

    public TreeMap<Long, Taxi> Taxis = new TreeMap<>();

    public TreeMap<Integer, Node> Nodes_SortedByIndex = new TreeMap<>();
    public TreeMap<Integer, Link> Links_SortedByIndex = new TreeMap<>();
    public TreeMap<Integer, Passenger> Passengers_SortedByIndex = new TreeMap<>();
    public TreeMap<Integer, Taxi> Taxis_SortedByIndex = new TreeMap<>();

    public TreeMap<Long, OnBoardPassenger> OnBoardPassengers_SortedByPassengerID = new TreeMap<>();
    public TreeMap<Integer, OnBoardPassenger> OnBoardPassengers_SortedByPassengerIndex = new TreeMap<>();

    public TreeMap<Long, TreeMap<Long, OnBoardPassenger>> OnBoardPassengers_SortedByTaxiID = new TreeMap<>();
    public TreeMap<Integer, TreeMap<Integer, OnBoardPassenger>> OnBoardPassengers_SortedByTaxiIndex = new TreeMap<>();

    public TreeMap<Long, Long> SeekerPassengers = new TreeMap<>();
    public TreeMap<Integer, Long> SeekerPassengers_SortedByIndex = new TreeMap<>();

    public TreeMap<Long, TreeMap<Long, Route>> Routes = new TreeMap<>();

    public long Furthest_Node_On_X_Axis = -1;
    public long Nearest_Node_On_X_Axis = -1;
    public long Furthest_Node_On_Y_Axis = -1;
    public long Nearest_Node_On_Y_Axis = -1;

    public String Directory = null;
    public String InputDirectory = null;
    public String OutputDirectory = null;

    /**
     * Network Constructor
     *
     * @param Directory
     */
    public Network(String Directory) {
        this.Directory = Directory;
        InputDirectory = String.format("%s\\%s", Directory, "Input");
        InputDirectory = String.format("%s\\%s", Directory, "Output");

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Network(Network Network) {
        this.Directory = Network.Directory;
        InputDirectory = Network.InputDirectory;
        InputDirectory = Network.OutputDirectory;
    }

    private static void CreateNodes(Network Network) {

        long ID = 1;
        int NodeIndex = 0;
        for (int row = 1; row <= Settings.RowCount; row++) {
            for (int column = 1; column <= Settings.ColumnCount; column++) {
                Node Node = new Node();

                Node.ID = ID;
                Node.Index = NodeIndex++;

                Node.Location.X = column * Settings.DistanceBetweenAdjacentNodes;
                Node.Location.Y = row * Settings.DistanceBetweenAdjacentNodes;

                if (NodeIndex == 1) {
                    Network.Furthest_Node_On_X_Axis = Node.ID;
                    Network.Nearest_Node_On_X_Axis = Node.ID;
                    Network.Furthest_Node_On_Y_Axis = Node.ID;
                    Network.Nearest_Node_On_Y_Axis = Node.ID;

                } else {
                    if (Node.Location.X > Network.Nodes.get(Network.Furthest_Node_On_X_Axis).Location.X) {
                        Network.Furthest_Node_On_X_Axis = Node.ID;
                    }
                    if (Node.Location.X < Network.Nodes.get(Network.Nearest_Node_On_X_Axis).Location.X) {
                        Network.Nearest_Node_On_X_Axis = Node.ID;
                    }
                    if (Node.Location.Y > Network.Nodes.get(Network.Furthest_Node_On_Y_Axis).Location.Y) {
                        Network.Furthest_Node_On_Y_Axis = Node.ID;
                    }
                    if (Node.Location.Y > Network.Nodes.get(Network.Nearest_Node_On_Y_Axis).Location.Y) {
                        Network.Nearest_Node_On_Y_Axis = Node.ID;
                    }
                }
                Network.Nodes.put(ID, Node);
                Network.Nodes_SortedByIndex.put(Node.Index, Node);

                ID++;
            }
        }
    }

    private static void CreateLinks(Network Network) {
        int LinkIndex = 0;
        for (int row = 1; row <= Settings.RowCount; row++) {
            for (int column = 1; column <= Settings.ColumnCount; column++) {
                long Node1 = Settings.ColumnCount * (row - 1) + column;
                for (int i = row - 1; i <= row + 1; i++) {
                    if (i <= 0 || i > Settings.RowCount) {
                        continue;
                    }
                    for (int j = column - 1; j <= column + 1; j++) {
                        if (j <= 0 || j > Settings.ColumnCount) {
                            continue;
                        }
                        long Node2 = Settings.ColumnCount * (i - 1) + j;
                        if (Node2 == Node1) {
                            continue;
                        }
                        if (i != row && j != column) {
                            continue;
                        }
                        Link Link1 = new Link();
                        Link Link2 = new Link();
                        Link1.Index = LinkIndex++;
                        Link2.Index = LinkIndex++;

                        Link1.UpStream = Node1;
                        Link1.DownStream = Node2;
                        Link2.DownStream = Node1;
                        Link2.UpStream = Node2;

                        if (!Network.Nodes.get(Link1.UpStream).AdjcantNodes.contains(Node2)) {
                            Network.Nodes.get(Link1.UpStream).AdjcantNodes.add(Node2);
                        }
                        if (!Network.Nodes.get(Link1.DownStream).AdjcantNodes.contains(Node1)) {
                            Network.Nodes.get(Link1.DownStream).AdjcantNodes.add(Node1);
                        }

                        Link1.ID = String.format("%d-%d", Link1.UpStream, Link1.DownStream);
                        Link2.ID = String.format("%d-%d", Link2.UpStream, Link2.DownStream);

                        Link1.Length = Link2.Length = Settings.DistanceBetweenAdjacentNodes;

                        Network.Nodes.get(Link1.UpStream).OutLinks.put(Link1.ID, Link1);
                        Network.Nodes.get(Link2.UpStream).OutLinks.put(Link2.ID, Link2);

                        Network.Nodes.get(Link1.DownStream).InLinks.put(Link1.ID, Link1);
                        Network.Nodes.get(Link2.DownStream).InLinks.put(Link2.ID, Link2);

                        Point3D P1 = Network.Nodes.get(Link1.UpStream).Location;
                        Point3D P2 = Network.Nodes.get(Link1.DownStream).Location;
                        double Delta_X = P1.X - P2.X;
                        double Delta_Y = P1.Y - P2.Y;

                        Link1.Distance = Link2.Distance = (float) Math.sqrt(Delta_X * Delta_X + Delta_Y * Delta_Y);
                       // Link1.TravelTime = Link2.TravelTime = Link1.Distance / Link1.CurrentSpeed;
                        
                     Link2.TravelTime =   Link1.TravelTime = (Link1.Distance / Link1.CurrentSpeed) * 3600f ;

                        Network.Links.put(Link1.ID, Link1);
                        Network.Links.put(Link2.ID, Link2);

                        Network.Links_SortedByIndex.put(Link1.Index, Link1);
                        Network.Links_SortedByIndex.put(Link2.Index, Link2);

                    }
                }

            }
        }
    }

    private static void CreatePassengers(Network Network) {

        Random Random = new Random(111111);
        long PassengerID = 1;
        for (int PassengerIndex = 0; PassengerIndex < Settings.PassengersCount; PassengerIndex++) {
            Passenger Passenger = new Passenger();

            Passenger.ID = PassengerID++;
            Passenger.Index = PassengerIndex;
            Passenger.Origin = Random.nextInt(Network.Nodes.size()) + 1;
            do {
                Passenger.Destination = Random.nextInt(Network.Nodes.size()) + 1;
            } while (Passenger.Destination == Passenger.Origin);

            Passenger.PickupTime = Random.nextInt(300);
            Passenger.RideDuration = Random.nextInt(Settings.MaxRideDuration) + Settings.MinRideDuration;
            Passenger.DropoffTime = Passenger.PickupTime + Passenger.RideDuration;
            Passenger.Revenue = 0;

            Network.Passengers.put(Passenger.ID, Passenger);
            Network.SeekerPassengers.put(Passenger.ID, Passenger.ID);

            Network.Passengers_SortedByIndex.put(Passenger.Index, Passenger);
            Network.SeekerPassengers_SortedByIndex.put(Passenger.Index, Passenger.ID);
        }
    }

    private static void CreateOnBoardPassengers(Network Network) {

        Random Random = new Random(33333);
        long PassengerID = 0;
        for (int PassengerIndex = 0; PassengerIndex < Settings.OnBoardCount; PassengerIndex++) {

            do {
                PassengerID = Random.nextInt(Network.Passengers.size()) + 1;
            } while (Network.OnBoardPassengers_SortedByPassengerID.containsKey(PassengerID));

            OnBoardPassenger OnBoardPassenger = new OnBoardPassenger(Network.Passengers.get(PassengerID));

            OnBoardPassenger.TaxiID = Random.nextInt(Network.Taxis.size()) + 1;;
            long TaxiLocation = Network.Taxis.get(OnBoardPassenger.TaxiID).Location;

            int N = Random.nextInt(Network.Nodes.get(TaxiLocation).AdjcantNodes.size());

            OnBoardPassenger.Origin = Network.Nodes.get(TaxiLocation).AdjcantNodes.get(N);

            do {
                OnBoardPassenger.Destination = Random.nextInt(Network.Nodes.size()) + 1;
            } while (OnBoardPassenger.Destination == OnBoardPassenger.Origin);

            Network.OnBoardPassengers_SortedByPassengerID.put(PassengerID, OnBoardPassenger);
            Network.OnBoardPassengers_SortedByPassengerIndex.put(OnBoardPassenger.Index, OnBoardPassenger);

            if (!Network.OnBoardPassengers_SortedByTaxiID.containsKey(OnBoardPassenger.TaxiID)) {
                Network.OnBoardPassengers_SortedByTaxiID.put(OnBoardPassenger.TaxiID, new TreeMap());
                Network.OnBoardPassengers_SortedByTaxiIndex.put(Network.Taxis.get(OnBoardPassenger.TaxiID).Index, new TreeMap());
            }

            if (!Network.OnBoardPassengers_SortedByTaxiID.get(OnBoardPassenger.TaxiID).containsKey(PassengerID)) {
                Network.OnBoardPassengers_SortedByTaxiID.get(OnBoardPassenger.TaxiID).put(PassengerID, OnBoardPassenger);
                Network.OnBoardPassengers_SortedByTaxiIndex.get(Network.Taxis.get(OnBoardPassenger.TaxiID).Index).put(OnBoardPassenger.Index, OnBoardPassenger);

            } else {
                MyLogger.log(Level.SEVERE, String.format("Duplicate OnBoard Passenger [%d]", OnBoardPassenger.ID));
            }

            Network.Taxis.get(OnBoardPassenger.TaxiID).OnBoard++;
            Network.SeekerPassengers.remove(PassengerID);
            Network.SeekerPassengers_SortedByIndex.remove(OnBoardPassenger.Index);
        }
    }

    private static void CreateTaxis(Network Network) {

        Random Random = new Random(22222);
        long TaxiID = 1;
        for (int TaxiIndex = 0; TaxiIndex < Settings.TaxisCount; TaxiIndex++) {

            Taxi Taxi = new Taxi();
            Taxi.ID = TaxiID++;
            Taxi.Index = TaxiIndex;
            Taxi.Location = Random.nextInt(Network.Nodes.size()) + 1;

            Network.Taxis.put(Taxi.ID, Taxi);
            Network.Taxis_SortedByIndex.put(Taxi.Index, Taxi);

        }
    }

    public static Network Create(String Directory) {
        Network Network = new Network(Directory);
        CreateNodes(Network);
        CreateLinks(Network);
        CreatePassengers(Network);
        CreateTaxis(Network);
        CreateOnBoardPassengers(Network);

        return Network;
    }

}
