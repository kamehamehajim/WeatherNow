package com.example.weatherapp.presentation.main;

import com.example.weatherapp.presentation.main.model.MainUiModel;

public interface MainContract {

    interface View {

        void checkLocationPermission();

        void getCurrentLocation();

        void renderData(MainUiModel uiModel);

        void showProgress(boolean show);
    }

    interface EventListener extends LocationDelegate.Listener {

        void onMapReady();
    }

}
