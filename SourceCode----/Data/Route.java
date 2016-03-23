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

    public static final double PricePerMile = 0.1; // 0.1 Dollar Per Mile
    public static final double OpeningPrice = 2;   // 2 Dollars

    public long Origin;
    public long Destination;

    public double TotalDistance = 0;
    public double TotalCost = OpeningPrice;
    public double TotalTravelTime = 0;

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
        }
        return true;
    }

}
