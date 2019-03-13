package com.example.weatherapp.presentation;

import android.app.Activity;
import android.app.Application;
import com.example.weatherapp.R;
import com.example.weatherapp.di.AppModule;
import com.example.weatherapp.di.DaggerAppComponent;
import com.mapbox.mapboxsdk.Mapbox;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

import javax.inject.Inject;

public class App extends Application implements HasActivityInjector {

    @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        String mapBoxAccessTocken = getString(R.string.mapbox_access_token);
        Mapbox.getInstance(this, mapBoxAccessTocken);

        Timber.plant(new Timber.DebugTree());

        DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .create(this)
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
