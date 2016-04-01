/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.ArrayList;

/**
 *
 * @author TRL
 */
public class Route {

    public static final float PricePerMile = 1f; // 0.1 Dollar Per Mile
    public static final float OperationCostPerMile = 0.1f; // 0.1 Dollar Per Mile
    public static final float OpeningPrice = 0;   // 2 Dollars

    public long Origin;
    public long Destination;

    public float TotalDistance = 0;
    public float TotalOperationCost;
    public float TotalRevenue = OpeningPrice;
    public float TotalTravelTime = 0;

    public ArrayList<Long> Nodes = new ArrayList();
    public ArrayList<String> Links = new ArrayList();

    public boolean PopulateLinks(Network Network) {

        for (int NodeIndex = 1; NodeIndex < Nodes.size(); NodeIndex++) {

            String ID = String.format("%d-%d", Nodes.get(NodeIndex - 1), Nodes.get(NodeIndex));

            if (!Network.Links.containsKey(ID)) {
                return false;
            }
            if (Links.contains(ID)) {
                return false;
            }
            Links.add(ID);

            TotalRevenue += Network.Links.get(ID).Length * PricePerMile;
            TotalDistance += Network.Links.get(ID).Length;
        }
        return true;
    }

}
