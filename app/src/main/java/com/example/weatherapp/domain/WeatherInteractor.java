package com.example.weatherapp.domain;

import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.domain.model.WeatherModel;
import io.reactivex.Single;

import javax.inject.Inject;

public class WeatherInteractor {

    private final WeatherRepository mWeatherRepository;

    @Inject
    public WeatherInteractor(WeatherRepository weatherRepository) {
        mWeatherRepository = weatherRepository;
    }

    public Single<WeatherModel> getCurrentWeather(LocationParams locationParams) {
        return mWeatherRepository.getCurrentWeather(locationParams);
    }
}
