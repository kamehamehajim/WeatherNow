package com.example.weatherapp.presentation.main;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.weatherapp.R;
import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.domain.model.LocationSource;
import com.example.weatherapp.presentation.common.BaseMapActivity;
import com.example.weatherapp.presentation.main.model.MainUiModel;
import com.example.weatherapp.presentation.main.view.WeatherCardView;
import com.example.weatherapp.utils.Map;
import com.mapbox.android.gestures.MoveGestureDetector;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import dagger.android.AndroidInjection;
import dmax.dialog.SpotsDialog;

import javax.inject.Inject;

public class MainActivity extends BaseMapActivity implements MainContract.View {

    private final static double BOUNDS_DELTA = 0.01;

    private MapboxMap mMapboxMap;
    private AlertDialog mProgressDialog;
    private WeatherCardView mWeatherCardView;
    private FloatingActionButton mLocationFab;
    private View mTargetView;

    private Animation mInAnim;
    private Animation mOutAnim;

    @Inject MainPresenter mPresenter;
    @Inject LocationDelegate mLocationDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        mInAnim = AnimationUtils.loadAnimation(this, R.anim.anim_view_go_in);
        mInAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTargetView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mWeatherCardView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mOutAnim = AnimationUtils.loadAnimation(this, R.anim.anim_view_go_out);
        mOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mWeatherCardView.setVisibility(View.INVISIBLE);
                mTargetView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mWeatherCardView = findViewById(R.id.weather_card_view);
        mLocationFab = findViewById(R.id.location_fab);
        mLocationFab.setOnClickListener(v -> {
            mLocationDelegate.onGetLocation();
        });
        mTargetView = findViewById(R.id.target_view);

        mLocationDelegate.setListener(mPresenter);

        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void onMapReady(MapboxMap mapboxMap) {
        mMapboxMap = mapboxMap;
        mMapboxMap.addOnMoveListener(new MapboxMap.OnMoveListener() {
            @Override
            public void onMoveBegin(@NonNull MoveGestureDetector detector) {
                if (mWeatherCardView.getVisibility() == View.VISIBLE) {
                    mWeatherCardView.startAnimation(mOutAnim);
                }
            }

            @Override
            public void onMove(@NonNull MoveGestureDetector detector) {
            }

            @Override
            public void onMoveEnd(@NonNull MoveGestureDetector detector) {
                mWeatherCardView.setVisibility(View.INVISIBLE);
                LatLngBounds bounds = mMapboxMap.getProjection().getVisibleRegion().latLngBounds;
                LocationParams locationParams = LocationParams.create(
                        LocationSource.USER,
                        (bounds.getLatSouth() + bounds.getLatNorth()) / 2.0,
                        Map.midLongitude(bounds.getLonWest(), bounds.getLonEast())
                );
                mPresenter.onLocationUpdate(locationParams);
            }
        });
        mPresenter.onMapReady();
    }

    @Override
    public void checkLocationPermission() {
        mLocationDelegate.onCheckPermission();
    }

    @Override
    public void getCurrentLocation() {
        mLocationDelegate.onGetLocation();
    }

    @Override
    public void renderData(MainUiModel uiModel) {
        double lat = uiModel.locationParams().latitude();
        double lon = uiModel.locationParams().longitude();
        LatLngBounds latLngBounds = LatLngBounds.from(
                lat + BOUNDS_DELTA,
                lon + BOUNDS_DELTA,
                lat - BOUNDS_DELTA,
                lon - BOUNDS_DELTA
        );
        if (uiModel.locationParams().source() == LocationSource.SERVICES) {
            mMapboxMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
        }
        mWeatherCardView.renderModel(uiModel.weather());
        if (mWeatherCardView.getVisibility() != View.VISIBLE) {
            mWeatherCardView.startAnimation(mInAnim);
        }
    }

    @Override
    public void showProgress(boolean show) {
        if (mProgressDialog == null) {
            mProgressDialog = new SpotsDialog.Builder()
                    .setContext(this)
                    .setCancelable(false)
                    .setMessage(R.string.progress_message)
                    .build();
        }
        if (show) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        mLocationDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
