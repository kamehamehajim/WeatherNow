package com.example.weatherapp.data.api;

import com.example.weatherapp.data.dto.GetCurrentWeatherResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

    @GET("weather")
    Single<GetCurrentWeatherResponse> getCurrentWeather(@Query("lat") double lat, @Query("lon") double lon);
}
