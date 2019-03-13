package com.example.weatherapp.utils;

public class Map {

    public static double adjustLongitude(double lon) {
        return lon > 180 ? (lon - 360) : lon;
    }

    public static double midLongitude(double lonWest, double lonEast) {
        double adjustedLonWest = adjustLongitude(lonWest);
        double adjustedLonEast = adjustLongitude(lonEast);
        if (adjustedLonEast > adjustedLonWest) {
            return (adjustedLonWest + adjustedLonEast) / 2.0;
        } else {
            return adjustLongitude((adjustedLonWest + adjustedLonEast + 360) / 2.0);
        }
    }

}
