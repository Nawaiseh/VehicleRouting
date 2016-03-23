package utils;

public class DistanceConversion {
    /*::                                                                         :*/
    /*::  Passed to function:                                                    :*/
    /*::    lat1, lon1 = Latitude and Longitude of point 1 (in decimal degrees)  :*/
    /*::    lat2, lon2 = Latitude and Longitude of point 2 (in decimal degrees)  :*/
    /*::    unit = the unit you desire for results                               :*/
    /*::           where: 'M' is statute miles                                   :*/
    /*::                  'K' is kilometers (default)                            :*/
    /*::                  'N' is nautical miles                                  :*/
    //system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
//system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
//system.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");

    public double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        if (unit.equalsIgnoreCase("K")) {
            dist = dist * 1.609344;
        } else if (unit.equalsIgnoreCase("N")) {
            dist = dist * 0.8684;
        }
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

    public double distance2(double lat1, double lon1, double lat2, double lon2, String unit) {
        double earthRadious = 6371 / 1.609344;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadious * c;

        if (unit.equalsIgnoreCase("K")) {
            dist = dist * 1.609344;
        } else if (unit.equalsIgnoreCase("N")) {
            dist = dist * 0.8684;
        }
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
}
