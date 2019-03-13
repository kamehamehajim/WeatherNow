package com.example.weatherapp.data.repository;

import com.example.weatherapp.domain.WeatherRepository;
import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.domain.model.WeatherModel;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WeatherRepositoryImpl implements WeatherRepository {

    private final WeatherApi mWeatherApi;

    @Inject
    public WeatherRepositoryImpl(WeatherApi weatherApi) {
        mWeatherApi = weatherApi;
    }

    @Override
    public Single<WeatherModel> getCurrentWeather(LocationParams locationParams) {
        return mWeatherApi.getCurrentWeather(locationParams);
    }
}
