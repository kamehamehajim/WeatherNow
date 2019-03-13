package com.example.weatherapp.utils;

public class Units {

    public static double kelvinToCelsius(double value) {
        return value - 273.15;
    }

    public static double hPaToMMHg(double value) {
        return value / 1.3332239;
    }

}
