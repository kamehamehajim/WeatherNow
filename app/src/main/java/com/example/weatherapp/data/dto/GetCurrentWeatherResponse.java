package com.example.weatherapp.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCurrentWeatherResponse {

    @SerializedName("coord")
    public CoordDto coord;

    @SerializedName("weather")
    public List<WeatherDto> weather;

    @SerializedName("base")
    public String base;

    @SerializedName("main")
    public MainDto main;

    @SerializedName("wind")
    public WindDto wind;

    @SerializedName("clouds")
    public CloudsDto clouds;

    @SerializedName("dt")
    public long date;

    @SerializedName("sys")
    public SysDto sys;

    @SerializedName("id")
    public long id;

    @SerializedName("name")
    public String name;

    @SerializedName("cod")
    public long cod;

}
