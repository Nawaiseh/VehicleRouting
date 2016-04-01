package Data;

import UI.Utils.OpenGLUtils.GLColorD;

import java.util.ArrayList;

public class Passenger {

    public enum PassengerType {

        OnBoard, Seeker
    }

    public long ID;
    public int Index;
    public long Origin;
    public int OriginIndex;

    public long Destination;
    public int DestinationIndex;

    public long PickupTime;
    public long DropoffTime;
    public float Revenue;
    public long RideDuration;

    public PassengerType Type = PassengerType.Seeker;

    public Passenger() {

    }

    public Passenger(int ID) {

    }

    public ArrayList<Long> PathNodes = new ArrayList<>();
    public ArrayList<String> PathLinks = new ArrayList<>();
    public GLColorD Color = new GLColorD(0f, 0f, 0f, 0.5f);
    public int LineWidth = 1;
}
