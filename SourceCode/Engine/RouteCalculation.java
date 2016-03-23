package Engine;

import Data.Network;
import Data.Node;
import java.util.ArrayList;

/**
 *
 * @author TRL
 */
public class RouteCalculation {

    private Network Network;
    private static final int MaximumNumberOfThreads = 32;

    public RouteCalculation(Network Network) {
        this.Network = Network;
        CalculateShortestRoutes();
    }

    private void CalculateShortestRoutes() {
        int ThreadCounter = 1;
        ArrayList<ShortestRoute_Thread> ActiveThreads = new ArrayList(MaximumNumberOfThreads);
        for (Node Destination : Network.Nodes.values()) {
            ShortestRoute_Thread ShortestRoute_Thread = new ShortestRoute_Thread(Network, Destination, ThreadCounter);
            ActiveThreads.add(ShortestRoute_Thread);

            ShortestRoute_Thread.start();

            if (ThreadCounter == (MaximumNumberOfThreads - 1)) {
                boolean Resume = true;
                while (Resume) {
                    Resume = false;
                    for (int i = 0; i < ActiveThreads.size(); i++) {
                        Resume = Resume || ((Thread) ActiveThreads.get(i)).isAlive();
                        if (!((Thread) ActiveThreads.get(i)).isAlive()) {
                            ((Thread) ActiveThreads.get(i)).interrupt();
                        }
                    }
                }
                if (!Resume && ActiveThreads.size() == MaximumNumberOfThreads) {
                    ThreadCounter = 0;
                    ActiveThreads.clear();
                }
            }
            if (!ActiveThreads.isEmpty()) {
                ThreadCounter++;  // open location for one more thread
            }
        }
    }
}
