package Engine;

import Data.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 *
 * @author TRL
 */
public class ShortestRoute_Thread extends Thread {

    private Network Network;
    private Node Destination;
    private int NodesCount = 0;

    private Label[] Labels;

    public ShortestRoute_Thread(Network Network, Node Destination, int ThreadCounter) {
        this.Network = Network;
        this.Destination = Destination;
        NodesCount = Network.Nodes.size();// + 1;
        Labels = new Label[NodesCount];
        for (Node Node : Network.Nodes.values()) {
            Labels[Node.Index] = new Label();
        }

        Labels[Destination.Index].Cost = 0;
        Labels[Destination.Index].TravelTime = 0;

        //  nodePointer = new int[nodes];
    }

    private void ShortestRoute_Step1() {
        // Calculate The Labels Values

        Queue<Integer> Queue = new LinkedList();
        Queue.add(Destination.Index);

        while (!Queue.isEmpty()) {
            int CurrentNodeIndex = Queue.remove();
            Node CurrentNode = Network.Nodes_SortedByIndex.get(CurrentNodeIndex);
            ArrayList<Link> InLinks = new ArrayList(CurrentNode.InLinks.values());
            for (Link Link : InLinks) {

                Node PreviousNode = Network.Nodes.get(Link.UpStream);
                int PreviousNodeIndex = PreviousNode.Index;

                float TravelTime = Labels[CurrentNodeIndex].TravelTime + Link.TravelTime;
                float Cost = Labels[CurrentNodeIndex].Cost + (Route.OperationCostPerMile * Link.Length);

                if (Cost < Labels[PreviousNodeIndex].Cost) {
                    Labels[PreviousNodeIndex].TravelTime = TravelTime;
                    Labels[PreviousNodeIndex].Cost = Cost;
                    Labels[PreviousNodeIndex].IsUpdated = true;
                    Labels[PreviousNodeIndex].NextNode = CurrentNodeIndex;
                }
                if (Labels[PreviousNodeIndex].IsUpdated == true) {
                    Labels[PreviousNodeIndex].IsUpdated = false;
                    if (!Queue.contains(PreviousNodeIndex)) {
                        Queue.add(PreviousNodeIndex);
                    }
                }
            }
        }
    }

    private void ShortestRoute_Step2() {
        // Build Routes from The Calculated Labels

        for (Node Origin : Network.Nodes.values()) {
            if (Origin.ID == Destination.ID) {
                continue;
            }
            int OriginIndex = Origin.Index;
            Route Route = new Route();
            Route.Origin = Origin.ID;
            Route.Destination = Destination.ID;

            Route.TotalOperationCost += Labels[OriginIndex].Cost;
            Route.TotalTravelTime = Labels[OriginIndex].TravelTime;

            Route.Nodes.add(Route.Origin);
            int TempIndex = OriginIndex;
            while (TempIndex != -1 && TempIndex != Destination.Index) {
                int NextNode = Labels[TempIndex].NextNode;
                Route.Nodes.add(Network.Nodes_SortedByIndex.get(NextNode).ID);
                TempIndex = NextNode;
                if (Route.Nodes.size() > 250) {
                    TempIndex = -1;
                    break;
                }
            }
            if (TempIndex == -1) {
                continue;
            }

            // Route.Nodes.add(Route.Destination);
            boolean RouteValidity = Route.PopulateLinks(Network);
            if (RouteValidity == false) {
                continue;
            }
            synchronized (Network.Routes) {
                if (!Network.Routes.containsKey(Route.Origin)) {
                    Network.Routes.put(Route.Origin, new TreeMap());
                }

                try {
                    Network.Routes.get(Route.Origin).put(Route.Destination, Route);
                } catch (Exception e) {
                    e.printStackTrace();
                    int x = 0;
                }
            }
        }
    }

    @Override
    public void run() {

        ShortestRoute_Step1();
        ShortestRoute_Step2();
    }
}
