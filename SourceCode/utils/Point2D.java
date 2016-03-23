package utils;

/**
 *
 * @author aalnawai
 */
public class Point2D {

    public double X = -100;
    public double Y = -100;
    public double Longitude = -100;
    public double Latitude = -100;
    public boolean isMiles = false;
    public static double Zero = 0.01; // In Miles

    public Point2D() {
    }

    public Point2D(double x, double y) {
        isMiles = true;
        X = x;
        Y = y;
    }

    public Point2D(double x, double y, boolean Miles) {
        isMiles = Miles;
        if (isMiles) {
            X = x;
            Y = y;
        } else {
            isMiles = true;
            Longitude = x;
            Latitude = y;
            X = LonLatToMile(0, Latitude, 0, 0);
            Y = LonLatToMile(0, Longitude, 0, 0);
        }
    }

    public Point2D(double x, double y, double lon, double lat) {
        X = x;
        Y = y;
        Longitude = lon;
        Latitude = lat;
        isMiles = true;
    }

    Point2D(Point2D Point) {
        X = Point.X;
        Y = Point.Y;
        Longitude = Point.Longitude;
        Latitude = Point.Latitude;
        isMiles = Point.isMiles;
    }

    private double LonLatToMile(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (lat1 < 0 && lon1 == 0 && lat2 == 0 && lon2 == 0) {
            dist = (dist < 0) ? dist : -dist;
        }
        if (lat2 < 0 && lon1 == 0 && lat1 == 0 && lon2 == 0) {
            dist = (dist < 0) ? dist : -dist;
        }
        if (lon1 < 0 && lat1 == 0 && lat2 == 0 && lon2 == 0) {
            dist = (dist < 0) ? dist : -dist;
        }
        if (lon2 < 0 && lon1 == 0 && lat1 == 0 && lon2 == 0) {
            dist = (dist < 0) ? dist : -dist;
        }
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Override
    public String toString() {
        return ((isMiles) ? String.format("( X= %.6f , Y= %.6f )", X, Y) : String.format("( Lon = %.6f , Lat = %.6f )", Longitude, Latitude));
    }

    public boolean equals(Point2D P) {
        if (P == null) {
            return false;
        }
        double R1 = Math.abs(new Double(X - P.X));
        double R2 = Math.abs(new Double(Y - P.Y));
        return (R1 <= Zero && R2 <= Zero);
    }
}
