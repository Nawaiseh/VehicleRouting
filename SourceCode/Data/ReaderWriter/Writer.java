package Data.ReaderWriter;

import Data.Network;
import utils.XMLDocumentHandler;

/**
 *
 * @author Nawaiseh
 */
public class Writer extends XMLDocumentHandler {

    public String NodesFile = "Nodes.xml";
    public String LinksFile = "Links.xml";
    public String SignalsFile = "SignalPlan_1.xml";
    public String ZonesFile = "Zones.xml";
    public String ZonesInformationFile = "ZonesInformation.xml";
    public String FacilitiesFile = "Facilities.xml";
    public String TrafficCountersFile = "TrafficCounters.xml";
    public String DemandTableFile = "Demand.dat";
    public String TrucksDemandTableFile = "TruckDemand.dat";

    //<editor-fold defaultstate="collapsed" desc=" ■■■■■■■■■■■  Constructors                ■■■■■■■■■■■ ">
    public Writer() {
        super();
    }

    public Writer(String newNetworkRootPath) {
        super(newNetworkRootPath);

    }

    //</editor-fold>


    public void WriteNetworkData(Network Network) {
        Network.DView.DViewListner.WaitingWindow.setVisible(true);
     
        Network.DView.DViewListner.WaitingWindow.setVisible(false);
    }
}
