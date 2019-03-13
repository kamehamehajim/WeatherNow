package com.example.weatherapp.presentation.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import com.example.weatherapp.R;
import com.example.weatherapp.di.ActivityScope;
import com.example.weatherapp.domain.model.LocationParams;
import com.example.weatherapp.domain.model.LocationSource;
import com.google.android.gms.location.FusedLocationProviderClient;

import javax.inject.Inject;

@ActivityScope
public class LocationDelegate {

    private static final String PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int REQUEST_CODE = 1;

    public interface Listener {

        void onLocationUpdate(LocationParams locationParams);

        void onPermissionGranted();

        void onPermissionDenied();
    }

    private AlertDialog mAlertDialog;
    private LocationManager mLocationManager;

    private final Activity mActivity;
    private final FusedLocationProviderClient mFusedLocationProviderClient;

    private Listener mListener;

    @Inject
    public LocationDelegate(Activity activity) {
        mActivity = activity;
        mFusedLocationProviderClient = new FusedLocationProviderClient(mActivity);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public void onCheckPermission() {
        int permissionState = ContextCompat.checkSelfPermission(mActivity, PERMISSION);
        if (permissionState == PackageManager.PERMISSION_GRANTED) {
            mListener.onPermissionGranted();
        } else {
            requestPermission();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mListener.onPermissionGranted();
                } else {
                    if ((ActivityCompat.shouldShowRequestPermissionRationale(mActivity, PERMISSION))) {
                        showRationaleDialog();
                    } else {
                        mListener.onPermissionDenied();
                    }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    public void onGetLocation() {
        mFusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(mActivity, location -> {
                    if (location != null) {
                        LocationParams locationParams = LocationParams.create(LocationSource.SERVICES, location.getLatitude(), location.getLongitude());
                        mListener.onLocationUpdate(locationParams);
                    }
                });
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity, PERMISSION)) {
            showRationaleDialog();
        } else {
            doRequestPermission();
        }
    }

    private void doRequestPermission() {
        ActivityCompat.requestPermissions(
                mActivity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_CODE
        );
    }

    private void showRationaleDialog() {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(mActivity)
                    .setTitle(R.string.location_rationale_dialog_title)
                    .setMessage(R.string.location_rationale_dialog_message)
                    .setPositiveButton(R.string.location_rationale_dialog_ok, (dialog, which) -> doRequestPermission())
                    .setNegativeButton(R.string.location_rationale_dialog_no, (dialog, which) -> mListener.onPermissionDenied())
                    .setCancelable(false)
                    .create();
        }
        mAlertDialog.show();
    }
}
