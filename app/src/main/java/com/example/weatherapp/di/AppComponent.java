package com.example.weatherapp.di;

import com.example.weatherapp.presentation.App;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        ActivityBindingsModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    public abstract class Builder extends AndroidInjector.Builder<App> {

        public abstract Builder appModule(AppModule module);
    }
}
