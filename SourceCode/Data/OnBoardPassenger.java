package Data;

/**
 *
 * @author TRL
 */
public class OnBoardPassenger extends Passenger {

    public long TaxiID;
    public int TaxiIndex;

    public OnBoardPassenger() {
        super();
        super.Type = PassengerType.OnBoard;
    }


    public OnBoardPassenger(Passenger Passenger) {
        super();
        this.ID = Passenger.ID;
        this.Index = Passenger.Index;
        this.Origin = Passenger.Origin;
        this.Destination = Passenger.Destination;
        this.PickupTime = Passenger.PickupTime;
        this.DropoffTime = Passenger.DropoffTime;
        this.Revenue = Passenger.Revenue;
        this.RideDuration = Passenger.RideDuration;

        super.Type = PassengerType.OnBoard;
    }
}
