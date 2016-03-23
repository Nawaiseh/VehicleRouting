/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import UI.Utils.OpenGLUtils.Point3D;
import java.util.ArrayList;

/**
 *
 * @author Nawaiseh
 */
public class PlaceMark {

    public String ID = "";
    public String Name = "";
    public int MaxLines;
    public String Description;
    public ArrayList<Point3D> Coordinates = new ArrayList<>();

}
