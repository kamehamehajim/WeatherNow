package com.example.weatherapp.data.api.mapper;

import com.example.weatherapp.data.dto.GetCurrentWeatherResponse;
import com.example.weatherapp.domain.model.WeatherModel;

import javax.inject.Inject;

public class ResponseMapper {

    @Inject
    public ResponseMapper() {
    }

    public WeatherModel mapWeather(GetCurrentWeatherResponse response) {
        return WeatherModel.builder()
                .generalInfo(response.weather.get(0).description)
                .generalIconCode(response.weather.get(0).icon)
                .temperatureKelvin(response.main.temp)
                .cloudsPercent(response.clouds.all)
                .humidityPercent(response.main.humidity)
                .pressureHectopascal(response.main.pressure)
                .windDegrees(response.wind.deg)
                .windSpeedMeterSecond(response.wind.speed)
                .sunriseTimestamp(response.sys.sunrise * 1000)
                .sunsetTimestamp(response.sys.sunset * 1000)
                .build();
    }
}
