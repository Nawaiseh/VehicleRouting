package Data;

public class Link {

    public String ID;
    public int Index;
    public float LaneWidth = 0.005f;
    public float Length = 0;
    public long UpStream;
    public int UpStreamIndex;
    public long DownStream;
    public int DownStreamIndex;
    public int LanesCount = 3;

    public float TravelTime;
    public float CurrentSpeed = 60; // 60 Miles Per Hour

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Link() {

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Link(Link Link, int LinkIndex) {
        Index = LinkIndex;
        ID = Link.ID;
        Length = Link.Length;
        UpStream = Link.UpStream;
        DownStream = Link.DownStream;
        LanesCount = Link.LanesCount;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}
