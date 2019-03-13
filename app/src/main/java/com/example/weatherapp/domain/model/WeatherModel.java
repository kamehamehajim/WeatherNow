package com.example.weatherapp.domain.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class WeatherModel {

    public abstract double temperatureKelvin();

    public abstract String generalInfo();
    public abstract String generalIconCode();

    public abstract long cloudsPercent();

    public abstract double pressureHectopascal();

    public abstract long humidityPercent();

    public abstract double windSpeedMeterSecond();
    public abstract double windDegrees();

    public abstract long sunriseTimestamp();
    public abstract long sunsetTimestamp();

    public static Builder builder() {
        return new AutoValue_WeatherModel.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder temperatureKelvin(double temperatureKelvin);

        public abstract Builder generalInfo(String generalInfo);

        public abstract Builder generalIconCode(String generalIconCode);

        public abstract Builder cloudsPercent(long cloudsPercent);

        public abstract Builder pressureHectopascal(double pressureHectopascal);

        public abstract Builder humidityPercent(long humidityPercent);

        public abstract Builder windSpeedMeterSecond(double windSpeedMeterSecond);

        public abstract Builder windDegrees(double windDegrees);

        public abstract Builder sunriseTimestamp(long sunriseTimestamp);

        public abstract Builder sunsetTimestamp(long sunsetTimestamp);

        public abstract WeatherModel build();
    }
}
