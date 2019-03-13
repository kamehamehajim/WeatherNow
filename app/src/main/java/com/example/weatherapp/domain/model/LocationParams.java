package com.example.weatherapp.domain.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class LocationParams {

    public abstract LocationSource source();

    public abstract double latitude();

    public abstract double longitude();

    public static LocationParams create(LocationSource source, double latitude, double longitude) {
        return new AutoValue_LocationParams(source, latitude, longitude);
    }

}
