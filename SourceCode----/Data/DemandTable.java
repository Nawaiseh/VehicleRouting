package Data;

import java.text.DecimalFormat;
import java.util.TreeMap;
import utils.Interval;

/**
 *
 * @author Nawaiseh
 */
public class DemandTable {

    public TreeMap<Interval, TreeMap<Long, TreeMap<Long, Demand>>> DemandSortedByIntervals = new TreeMap<>();
    public TreeMap<Long, TreeMap<Long, TreeMap<Interval, Demand>>> DemandSortedByZones = new TreeMap<>();
    public TreeMap<Integer, Interval> Intervals = new TreeMap<>();
    private final DecimalFormat DecimalFormat = new DecimalFormat("#,##0.00");

    public DemandTable() {
    }

//    public DemandTable(TreeMap<String, ODPair> OdPairs, TreeMap<String, Zone> newZones) {
//        Zones = newZones;
//        int ZonesCount = Zones.size();
//        ArrayList<Demand> TmpDemands = OdPairs.get((String) OdPairs.keySet().toArray()[0]).demand;
//        int IntervalsCount = TmpDemands.size();
//
//        for (int IntervalIndex = 0; IntervalIndex < IntervalsCount; IntervalIndex++) {
//            Demand Demand = TmpDemands.get(IntervalIndex);
//            Interval Interval = new Interval(Demand.getDemandIntervalStart(), Demand.getDemandIntervalEnd(), IntervalIndex);
//            Intervals.put(IntervalIndex, Interval);
//        }
//        Object[] ZonesKeys = Zones.keySet().toArray();
//        for (int IntervalIndex = 0; IntervalIndex < IntervalsCount; IntervalIndex++) {
//
//            Interval Interval = Intervals.get(IntervalIndex);
//            TreeMap<String, TreeMap<String, DemandData>> OriginDemands = new TreeMap();
//            for (int OriginZoneIndex = 0; OriginZoneIndex < ZonesCount; OriginZoneIndex++) {
//                TreeMap<String, DemandData> DestinationsDemands = new TreeMap();
//                String OriginZone = (String) ZonesKeys[OriginZoneIndex];
//                for (int DestinationZoneIndex = 0; DestinationZoneIndex < ZonesCount; DestinationZoneIndex++) {
//                    String DestinationZone = (String) ZonesKeys[DestinationZoneIndex];
//                    String ODKey = String.format("z%sz%s", OriginZone, DestinationZone);
//                    ArrayList<Demand> PairDemands = OdPairs.get(ODKey).demand;
//                    DemandData Demand = new DemandData(Interval, OriginZone, DestinationZone, PairDemands.get(IntervalIndex).getNumberOfTravelers(), PairDemands.get(IntervalIndex).getMultiplier());
//                    DestinationsDemands.put(DestinationZone, Demand);
//
//                }
//                OriginDemands.put(OriginZone, DestinationsDemands);
//            }
//            IntervalsDemands.put(Interval, OriginDemands);
//        }
//    }
//
//    public Object[] getValues(Interval Interval, String OriginZone) {
//        TreeMap<String, DemandData> DestinationsDemands = IntervalsDemands.get(Interval).get(OriginZone);
//        Object[] Values = new Object[DestinationsDemands.size() + 1];
//        int Index = 0;
//        Values[Index++] = OriginZone;
//        for (DemandData Demand : DestinationsDemands.values()) {
//            Values[Index++] = Demand.Demand;
//        }
//        return Values;
//    }
//
//    public Object getColomnValue(Interval Interval, String OriginZone, int columnIndex) {
//        return getValues(Interval, OriginZone)[columnIndex];
//    }
//
//    public String[] getValuesAsString(Interval Interval, String OriginZone) {
//        TreeMap<String, DemandData> DestinationsDemands = IntervalsDemands.get(Interval).get(OriginZone);
//        String[] Values = new String[DestinationsDemands.size()];
//        int Index = 0;
//        for (DemandData Demand : DestinationsDemands.values()) {
//            Values[Index++] = DecimalFormat.format(Demand.Demand);
//        }
//        return Values;
//    }
//}

}
