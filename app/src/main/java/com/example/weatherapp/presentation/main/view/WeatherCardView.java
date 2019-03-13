package com.example.weatherapp.presentation.main.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.presentation.main.model.WeatherUiModel;

public class WeatherCardView extends FrameLayout {

    private ImageView mWeatherIv;
    private TextView mInfoTv;
    private TextView mCloudsTv;
    private TextView mTemperatureTv;
    private TextView mPressureTv;
    private TextView mHumidityTv;
    private TextView mWindTv;
    private TextView mSunriseTv;
    private TextView mSunsetTv;
    private TextView mLocationTv;

    public WeatherCardView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public WeatherCardView(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WeatherCardView(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public WeatherCardView(@NonNull Context context, @NonNull AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        View.inflate(context, R.layout.weather_card, this);

        mWeatherIv = findViewById(R.id.weather_iv);
        mInfoTv = findViewById(R.id.weather_general_info_tv);
        mCloudsTv = findViewById(R.id.weather_clouds_tv);
        mTemperatureTv = findViewById(R.id.weather_temperature_tv);
        mPressureTv = findViewById(R.id.weather_pressure_tv);
        mHumidityTv = findViewById(R.id.weather_humidity_tv);
        mWindTv = findViewById(R.id.weather_wind_tv);
        mSunriseTv = findViewById(R.id.weather_sunrise_tv);
        mSunsetTv = findViewById(R.id.weather_sunset_tv);
        mLocationTv = findViewById(R.id.weather_location_tv);
    }

    public void renderModel(WeatherUiModel uiModel) {
        Glide.with(getContext())
                .load(uiModel.infoImageUrl())
                .into(mWeatherIv);
        String info = uiModel.info().substring(0,1).toUpperCase() + uiModel.info().substring(1);
        mInfoTv.setText(info);
        Resources res = getResources();
        mCloudsTv.setText(res.getString(R.string.weather_cloudiness, uiModel.cloudsPercent()));
        mTemperatureTv.setText(res.getString(R.string.weather_temperature, uiModel.temperatureCelsius()));
        mPressureTv.setText(res.getString(R.string.weather_pressure, uiModel.pressurMMHg()));
        mHumidityTv.setText(res.getString(R.string.weather_humidity, uiModel.humidityPercent()));
        mWindTv.setText(res.getString(R.string.weather_wind, uiModel.windDirection(), uiModel.windSpeedMeterSecond()));
        mSunriseTv.setText(uiModel.sunrise());
        mSunsetTv.setText(uiModel.sunset());
        mLocationTv.setText(res.getString(R.string.weather_location, uiModel.latitude(), uiModel.longitude()));
    }
}
