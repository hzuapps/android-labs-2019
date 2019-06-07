package edu.hzuapps.androidlabs.soft1714080902333.activity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902333.R;


public class LocationActivity extends AppCompatActivity {
    private TextView tvLatitube;
    private TextView tvLongitube;

    private LocationManager locationManager;

    private String locationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location);

        tvLatitube =  (TextView) findViewById(R.id.tv_latitube);
        tvLongitube = (TextView) findViewById(R.id.tv_longitube);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        List<String> providers = locationManager.getProviders(true);

        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "请开启定位权限：位于设置 -> 定位", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (locationManager != null) {
                locationManager.removeUpdates(locationListener);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        try {
            locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    private void showLocation(Location location) {
        if (location != null) {
            tvLatitube.setText("经度为：" + location.getLatitude());
            tvLongitube.setText("纬度为：" + location.getLongitude());
        } else {

        }
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}
