package com.example.weatherapp.di;

import android.content.Context;
import com.example.weatherapp.data.api.ApiWrapper;
import com.example.weatherapp.data.repository.WeatherApi;
import com.example.weatherapp.data.repository.WeatherRepositoryImpl;
import com.example.weatherapp.domain.WeatherRepository;
import com.example.weatherapp.presentation.main.di.MainActivitySubcomponent;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module(subcomponents = MainActivitySubcomponent.class)
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherRepositoryImpl weatherRepository) {
        return weatherRepository;
    }

    @Provides
    @Singleton
    WeatherApi providWeatherApi(ApiWrapper apiWrapper) {
        return apiWrapper;
    }
}
