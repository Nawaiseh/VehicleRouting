package Data;

import UI.UserInterface;
import java.util.Date;
import java.util.TreeMap;
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

}
