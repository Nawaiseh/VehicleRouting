package UI;

import Data.Network;
import Data.Node;
import Data.Passenger;
import Data.ReaderWriter.Reader;
import Data.Route;
import Engine.RouteCalculation;
import Engine.RoutingProblem_CPLEX;
import Execution.Master;
import utils.Sorter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NetworkLoader extends Thread {

    private final UserInterface DView;
    private Network Network = null;

    public NetworkLoader(UserInterface newDirectView, Network newTrafficNetwork) {
        this.DView = newDirectView;
        if (!Master.ReadInput) {
            this.Network = DView.myNetwork = Network.Create(DView.DefualtPath);
        } else {
            this.Network = DView.myNetwork = new Network(DView.DefualtPath);
        }

    }
    // =========================================================================

    @Override
    public void run() {
        WaitingWindow.status = "Loading Cameras Data";

        Network.Directory = DView.DefualtPath;
        Network.InputDirectory = String.format("%s%s", Network.Directory, "Input");
        Network.OutputDirectory = String.format("%s%s", Network.Directory, "Output");
        if (Master.ReadInput) {
            Reader Reader = new Reader(DView, DView.DViewListner.NetworkPath);
            Reader.ParseNetworkData(Network);
        }
        Calendar TimeCalendar = Calendar.getInstance();
        Network.Today = new Date(TimeCalendar.get(Calendar.YEAR) - 1900, TimeCalendar.get(Calendar.MONTH), TimeCalendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Network.DView = DView;
        DView.ShowView(DView.MainSimulationView);

        DView.DViewListner.WaitingWindow.setVisible(false);

        ArrayList<Node> Nodes = new ArrayList(Network.Nodes.values());
        int NodesCount = Nodes.size();
        Sorter Sorter = new Sorter();
        ArrayList<Node> SortedNodes = Sorter.sortObject(Nodes, "X");
        Network.Furthest_Node_On_X_Axis = SortedNodes.get(0).ID;
        Network.Nearest_Node_On_X_Axis = SortedNodes.get(NodesCount - 1).ID;
        SortedNodes = Sorter.sortObject(Nodes, "Y");
        Node farY = SortedNodes.get(0);
        Node nearY = SortedNodes.get(NodesCount - 1);
        Network.Furthest_Node_On_Y_Axis = SortedNodes.get(0).ID;
        Network.Nearest_Node_On_Y_Axis = SortedNodes.get(NodesCount - 1).ID;

        DView.MainSimulationPanel.SetNetwork(Network);
        DView.MainSimulationPanel.LoadNetwork();

        //DView.FinalNetwork = new Network(Network);
        DView.FinalNetwork = Network;

        DView.FinalNetwork.DView = DView;

        RouteCalculation RouteCalculation = new RouteCalculation(Network);
try {
    Thread.sleep(3000);
}catch (Exception e) {
                int x=0;
            }
        
        for (Passenger Passenger : Network.Passengers.values()) {
            try {
                Route Route = Network.Routes.get(Passenger.Origin).get(Passenger.Destination);
                Passenger.Revenue = Route.TotalRevenue;
            } catch (Exception e) {
                int x=0;
            }
        }
       RoutingProblem_CPLEX RoutingProblem_CPLEX = new RoutingProblem_CPLEX(Network);

        

    }
}
