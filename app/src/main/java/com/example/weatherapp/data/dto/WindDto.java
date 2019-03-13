package com.example.weatherapp.data.dto;

import com.google.gson.annotations.SerializedName;

public class WindDto {

    @SerializedName("speed")
    public double speed;

    @SerializedName("deg")
    public double deg;

}
