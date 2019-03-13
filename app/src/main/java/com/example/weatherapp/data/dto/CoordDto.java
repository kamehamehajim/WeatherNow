package com.example.weatherapp.data.dto;

import com.google.gson.annotations.SerializedName;

public class CoordDto {

    @SerializedName("lon")
    public double lon;

    @SerializedName("lat")
    public double lat;

}
