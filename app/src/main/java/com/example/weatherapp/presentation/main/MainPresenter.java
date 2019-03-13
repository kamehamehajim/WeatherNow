package com.example.weatherapp.presentation.main;

import com.example.weatherapp.domain.WeatherInteractor;
import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.presentation.main.mapper.MainUiMapper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class MainPresenter implements MainContract.EventListener {

    private final WeatherInteractor mWeatherInteractor;
    private final MainUiMapper mUiMapper;

    private MainContract.View mView;

    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private final BehaviorSubject<LocationParams> mLocationSubject = BehaviorSubject.create();

    @Inject
    public MainPresenter(WeatherInteractor weatherInteractor, MainUiMapper uiMapper) {
        mWeatherInteractor = weatherInteractor;
        mUiMapper = uiMapper;
    }

    void attachView(MainContract.View view) {
        mView = view;
    }

    void detachView() {
        mView = null;
        mDisposables.dispose();
    }

    @Override
    public void onLocationUpdate(LocationParams locationParams) {
        mLocationSubject.onNext(locationParams);
    }

    @Override
    public void onPermissionGranted() {
        mView.showProgress(true);
        mView.getCurrentLocation();

        Observable<LocationParams> locationParamsObservable = mLocationSubject
                .distinctUntilChanged()
                .debounce(1000, TimeUnit.MILLISECONDS)
                .replay(1)
                .refCount()
                .observeOn(Schedulers.io());

        Disposable d = Observable
                .zip(
                        locationParamsObservable,
                        locationParamsObservable.switchMap(locationParams -> mWeatherInteractor
                                .getCurrentWeather(locationParams)
                                .toObservable()
                        ),
                        mUiMapper::map
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mainUiModel -> {
                            mView.showProgress(false);
                            mView.renderData(mainUiModel);
                        },
                        Timber::e
                );
        mDisposables.add(d);
    }

    @Override
    public void onPermissionDenied() {

    }

    @Override
    public void onMapReady() {
        mView.checkLocationPermission();
    }
}
