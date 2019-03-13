package com.example.weatherapp.domain;

import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.domain.model.WeatherModel;
import io.reactivex.Single;

public interface WeatherRepository {

    Single<WeatherModel> getCurrentWeather(LocationParams locationParams);
}
