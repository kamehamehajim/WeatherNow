package com.example.weatherapp.presentation.main.di;

import android.app.Activity;
import com.example.weatherapp.di.ActivityScope;
import com.example.weatherapp.presentation.main.MainActivity;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = MainActivitySubcomponent.MainActivityModule.class)
public interface MainActivitySubcomponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }

    @Module
    class MainActivityModule {

        @Provides
        Activity provideActivity(MainActivity activity) {
            return activity;
        }
    }

}
