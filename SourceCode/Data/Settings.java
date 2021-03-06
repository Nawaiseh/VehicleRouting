package Data;

public class Settings {

    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~ Version ~~~~~~~~~~~~~~~~~~~">
    public final static String Application = "Network Builder";
    public final static String Version = "1.0";
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc=" ~~~~~~~~~~~~~~~~~~~ Paths   ~~~~~~~~~~~~~~~~~~~">
    public final static String BasePath = String.format("%s\\Samples\\Sample Network", System.getProperty("user.dir"));
//</editor-fold>
    public final static int RowCount = 7;
    public final static int ColumnCount = 6;
    public final static int PassengersCount = 9;
    public final static int MaxRideDuration = 300;
    public final static int MinRideDuration = 480;


    public final static int TaxisCount = 5;
    public final static int OnBoardCount = 0;

    public final static float DistanceBetweenAdjacentNodes = 1;

    public static enum LinkType {

        HOV, Frontage, freeway, arterial, arterialMJ, collector, dummy, rail, ramp, imaginary
    }
}
