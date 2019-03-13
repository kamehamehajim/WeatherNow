package com.example.weatherapp.presentation.main.mapper;

import android.content.Context;
import com.example.weatherapp.R;
import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.domain.model.WeatherModel;
import com.example.weatherapp.presentation.main.model.MainUiModel;
import com.example.weatherapp.presentation.main.model.WeatherUiModel;
import com.example.weatherapp.utils.Units;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainUiMapper {

    private static final String[] sDirections = new String[]{
            "N", "NNE", "NE", "ENE",
            "E", "ESE", "SE", "SSE",
            "S", "SSW", "SW", "WSW",
            "W", "WNW", "NW", "NNW"
    };

    private final Context mContext;
    private final SimpleDateFormat mTimeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

    @Inject
    public MainUiMapper(Context context) {
        mContext = context;
    }

    public MainUiModel map(LocationParams locationParams, WeatherModel weatherModel) {
        return MainUiModel.create(locationParams, mapWeather(weatherModel, locationParams));
    }

    private WeatherUiModel mapWeather(WeatherModel weatherModel, LocationParams locationParams) {
        String imageUrl = mContext.getString(R.string.image_url, weatherModel.generalIconCode());
        double temperature = Units.kelvinToCelsius(weatherModel.temperatureKelvin());
        double pressure = Units.hPaToMMHg(weatherModel.pressureHectopascal());
        String windDirection = getWindDirection(weatherModel.windDegrees());
        String sunriseTimeString = mTimeFormat.format(weatherModel.sunriseTimestamp());
        String sunsetTimeString = mTimeFormat.format(weatherModel.sunsetTimestamp());
        return WeatherUiModel.builder()
                .info(weatherModel.generalInfo())
                .infoImageUrl(imageUrl)
                .cloudsPercent(weatherModel.cloudsPercent())
                .humidityPercent(weatherModel.humidityPercent())
                .windDirection(windDirection)
                .windSpeedMeterSecond(weatherModel.windSpeedMeterSecond())
                .temperatureCelsius(temperature)
                .pressurMMHg(pressure)
                .sunrise(sunriseTimeString)
                .sunset(sunsetTimeString)
                .latitude(locationParams.latitude())
                .longitude(locationParams.longitude())
                .build();
    }

    private String getWindDirection(double degrees) {
        return sDirections[(int) Math.floor((degrees / 22.5) + 0.5) % 16];
    }
}
