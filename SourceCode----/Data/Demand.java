package Data;

import utils.Interval;

/**
 *
 * @author Nawaiseh
 */
public class Demand {

    public long Origin = 1;
    public long Destination = 1;
    public String ID = "1-1";

    public double TravelersCount = 0;
    public double TrucksCount = 0;

    public double DriveAloneCount = 0;
    public double Share2Count = 0;
    public double Share3Count = 0;

    public double CarMultiplier = 1;

    public double TruckMultiplier = 1;

    public Interval Interval = null;
    public double SharePercentage = 0;

    public Demand() {
    }

    public Demand(long Origin, long Destination, Interval Interval) {
        this.Origin = Origin;
        this.Destination = Destination;
        this.Interval = Interval;
        this.ID = String.format("z%dz%d", Origin, Destination);
    }

    public Demand(Demand Demand, double Percentage) {
        this.Origin = Demand.Origin;
        this.Destination = Demand.Destination;
        this.ID = Demand.ID;
        this.TravelersCount = Demand.TravelersCount;
        this.TrucksCount = Demand.TrucksCount;
        this.DriveAloneCount = Demand.DriveAloneCount;
        this.Share2Count = Demand.Share2Count;
        this.Share3Count = Demand.Share3Count;

        this.CarMultiplier = Demand.CarMultiplier * Percentage;
        this.TruckMultiplier = Demand.TruckMultiplier;
        this.Interval = Demand.Interval;
        this.SharePercentage = Demand.SharePercentage;
    }

}
