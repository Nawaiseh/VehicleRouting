package Data;

import UI.Utils.OpenGLUtils.Point3D;
import java.util.*;

public class Node implements Cloneable {

    public TreeMap<String, Link> OutLinks = new TreeMap<>();
    public TreeMap<String, Link> InLinks = new TreeMap<>();
    public long ID;
    public int Index;

    public Point3D Location = new Point3D(0, 0, 0);
    public Point3D OpenGLLocation = new Point3D(0, 0, 0);

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * Node's Default Constructor
     */
    public Node() {

    }

    public double getX() {
        return Location.X;
    }

    public double getY() {
        return Location.Y;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Node(Node Node, int NodeIndex) {
        Index = NodeIndex;
        ID = Node.ID;
        Location.X = Node.Location.X;
        Location.Y = Node.Location.Y;
        OpenGLLocation.X = Node.OpenGLLocation.X;
        OpenGLLocation.Y = Node.OpenGLLocation.Y;
    }

    Node(Node Node) {
        ID = Node.ID;
        Index = Node.Index;
        Location.X = Node.Location.X;
        Location.Y = Node.Location.Y;
        OpenGLLocation.X = Node.OpenGLLocation.X;
        OpenGLLocation.Y = Node.OpenGLLocation.Y;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}
