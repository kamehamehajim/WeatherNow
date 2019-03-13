package com.example.weatherapp.data.dto;

import com.google.gson.annotations.SerializedName;

public class MainDto {

    @SerializedName("temp")
    public double temp;

    @SerializedName("pressure")
    public double pressure;

    @SerializedName("humidity")
    public long humidity;

    @SerializedName("temp_min")
    public double temp_min;

    @SerializedName("temp_max")
    public double temp_max;

    @SerializedName("sea_level")
    public double sea_level;

    @SerializedName("grnd_level")
    public double grnd_level;
}
