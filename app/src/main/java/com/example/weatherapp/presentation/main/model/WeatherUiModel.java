package com.example.weatherapp.presentation.main.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class WeatherUiModel {

    public abstract double temperatureCelsius();

    public abstract String info();
    public abstract String infoImageUrl();

    public abstract long cloudsPercent();

    public abstract double pressurMMHg();

    public abstract long humidityPercent();

    public abstract double windSpeedMeterSecond();
    public abstract String windDirection();

    public abstract String sunrise();
    public abstract String sunset();

    public abstract double latitude();
    public abstract double longitude();

    public static Builder builder() {
        return new AutoValue_WeatherUiModel.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder temperatureCelsius(double temperatureCelsius);

        public abstract Builder info(String info);

        public abstract Builder infoImageUrl(String infoImageUrl);

        public abstract Builder cloudsPercent(long cloudsPercent);

        public abstract Builder pressurMMHg(double pressurMMHg);

        public abstract Builder humidityPercent(long humidityPercent);

        public abstract Builder windSpeedMeterSecond(double windSpeedMeterSecond);

        public abstract Builder windDirection(String windDirection);

        public abstract Builder sunrise(String sunrise);

        public abstract Builder sunset(String sunset);

        public abstract Builder latitude(double latitude);

        public abstract Builder longitude(double longitude);

        public abstract WeatherUiModel build();
    }
}
