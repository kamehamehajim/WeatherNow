package com.example.weatherapp.data.repository;

import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.domain.model.WeatherModel;
import io.reactivex.Single;

public interface WeatherApi {

    Single<WeatherModel> getCurrentWeather(LocationParams locationParams);
}
