package com.example.weatherapp.data.dto;

import com.google.gson.annotations.SerializedName;

public class WeatherDto {

    @SerializedName("id")
    public long id;

    @SerializedName("main")
    public String main;

    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;

}
