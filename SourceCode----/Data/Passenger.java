package Data;

public class Passenger {

    public enum PassengerType {

        OnBoard, Seeker
    }

    public long ID;
    public int Index;
    public long Origin;
    public long Destination = 0;
    public long PickupTime;
    public long DropoffTime;
    public float Revenue;
    public long RideDuration;

    public PassengerType Type = PassengerType.Seeker;
        public Passenger(){
        
    }
    
    public Passenger( int ID){
        
    }
}
