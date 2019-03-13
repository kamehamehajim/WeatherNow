package com.example.weatherapp.di;

import android.app.Activity;
import com.example.weatherapp.presentation.main.MainActivity;
import com.example.weatherapp.presentation.main.di.MainActivitySubcomponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MainActivitySubcomponent.class
})
public abstract class ActivityBindingsModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract AndroidInjector.Factory<? extends Activity> MainActivityComponentBuilder(MainActivitySubcomponent.Builder builder);
}
