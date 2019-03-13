package com.example.weatherapp.data.dto;

import com.google.gson.annotations.SerializedName;

public class SysDto {

    @SerializedName("message")
    public double message;

    @SerializedName("sunrise")
    public long sunrise;

    @SerializedName("sunset")
    public long sunset;

}
