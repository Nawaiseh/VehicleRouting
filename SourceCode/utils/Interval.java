/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.DecimalFormat;

/**
 *
 * @author aalnawai
 */
public class Interval implements Comparable {

    public int From;
    public int To;
    public int IntervalIndex;
    private String Interval = "";
    private String IntervalStart = "";
    private String IntervalEnd = "";
    public String IntervalStart_24 = "";
    public String IntervalEnd_24 = "";
    public double Weight=1;
    DecimalFormat DF = new DecimalFormat("0.00");

    public Interval(int newFrom, int newTo, int newIntervalIndex) {
        From = newFrom;
        To = newTo;
        IntervalIndex = newIntervalIndex;
        IntervalStart = secondsToTime(From);
        IntervalEnd = secondsToTime(To);
        IntervalStart_24 = secondsToTime_24(From);
        IntervalEnd_24 = secondsToTime_24(To);
       
        
       IntervalStart = IntervalStart.equals("00:00 AM")? "12:00 AM": IntervalStart;
       IntervalEnd = IntervalEnd.equals("00:00 AM")? "12:00 AM": IntervalEnd;
        
        Interval = IntervalStart + " - " + IntervalEnd;
    }
    public Interval(int newFrom, int newTo, int newIntervalIndex, double Weight) {
        From = newFrom;
        To = newTo;
        this.Weight = Weight;
        IntervalIndex = newIntervalIndex;
        IntervalStart = secondsToTime(From);
        IntervalEnd = secondsToTime(To);
        IntervalStart_24 = secondsToTime_24(From);
        IntervalEnd_24 = secondsToTime_24(To);
         IntervalStart = IntervalStart.equals("00:00 AM")? "12:00 AM": IntervalStart;
       IntervalEnd = IntervalEnd.equals("00:00 AM")? "12:00 AM": IntervalEnd;
        
        Interval = IntervalStart + " - " + IntervalEnd;
    }

    private String secondsToTime(int ss) {
        ss = (ss % 86400);
        int hour = ss / 3600;

        int min = (ss - (hour * 3600)) / 60;
        int second = ss - ((hour * 3600) + (min * 60));

        String PMAM = (hour >= 12) ? " PM" : " AM";
        hour = (hour > 12) ? hour - 12 : hour;
        String H = "" + ((hour > 9) ? hour : "0" + hour);
        H = (H.length() > 1) ? H : ("0" + H);
        String M = "" + ((min > 9) ? min : "0" + min);
        M = (M.length() > 1) ? M : ("0" + M);
        return H + ":" + M + PMAM;
    }

    private String secondsToTime_24(int ss) {
        ss = (ss % 86400);
        int hour = ss / 3600;

        int min = (ss - (hour * 3600)) / 60;
        int second = ss - ((hour * 3600) + (min * 60));
        String H = "" + ((hour > 9) ? hour : "0" + hour);
        H = (H.length() > 1) ? H : ("0" + H);
        String M = "" + ((min > 9) ? min : "0" + min);
        M = (M.length() > 1) ? M : ("0" + M);
        return H + ":" + M;
    }

    @Override
    public String toString() {
        return Interval;
    }

    public String toStartString() {
        return IntervalStart;
    }

    public String toEndString() {
        return IntervalEnd;
    }

    @Override
    public int compareTo(Object Object) {
        Interval IntervalObject = (Interval) Object;
        if (IntervalObject == null) {
            return 1;
        }
        Integer I = IntervalIndex;
        Integer O = IntervalObject.IntervalIndex;
        return I.compareTo(O);
    }
}
