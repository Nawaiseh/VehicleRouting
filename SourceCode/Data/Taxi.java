package Data;

import UI.Utils.OpenGLUtils.GLColorD;
import java.util.ArrayList;
import java.util.TreeMap;

public class Taxi {

    public long ID;
    public int Index;
    public int Capacity = 5;
    public int OnBoard;
    public long Location;
    public int LocationIndex;
    public long TimeToReachCurrentLocation = 0;

    public ArrayList<Long> PathNodes = new ArrayList<>();
    public ArrayList<String> PathLinks = new ArrayList<>();

    public TreeMap<Long, Integer> NodeTimes = new TreeMap<>();
    public GLColorD Color = new GLColorD(0f, 0f, 0f, 0.5f);
    public int LineWidth = 1;
}
