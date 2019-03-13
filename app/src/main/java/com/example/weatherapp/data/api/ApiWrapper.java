package com.example.weatherapp.data.api;

import com.example.weatherapp.data.api.mapper.ResponseMapper;
import com.example.weatherapp.data.repository.WeatherApi;
import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.domain.model.WeatherModel;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiWrapper implements WeatherApi {

    private final OpenWeatherApi mOpenWeatherApi;
    private final ResponseMapper mResponseMapper;

    @Inject
    public ApiWrapper(OpenWeatherApi openWeatherApi,
                      ResponseMapper responseMapper) {
        mOpenWeatherApi = openWeatherApi;
        mResponseMapper = responseMapper;
    }

    @Override
    public Single<WeatherModel> getCurrentWeather(LocationParams locationParams) {
        return mOpenWeatherApi.getCurrentWeather(locationParams.latitude(), locationParams.longitude())
                .map(mResponseMapper::mapWeather);
    }
}
