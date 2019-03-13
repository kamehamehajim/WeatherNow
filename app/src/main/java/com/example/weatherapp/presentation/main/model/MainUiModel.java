package com.example.weatherapp.presentation.main.model;

import com.example.weatherapp.domain.model.LocationParams;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MainUiModel {

    public abstract LocationParams locationParams();

    public abstract WeatherUiModel weather();

    public static MainUiModel create(LocationParams locationParams, WeatherUiModel weather) {
        return new AutoValue_MainUiModel(locationParams, weather);
    }

}
