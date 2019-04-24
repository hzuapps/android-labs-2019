package edu.hzuapps.androidlabs.soft1714080902217;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Soft1714080902217Locate extends AppCompatActivity {

    private TextView locateView;

    private LocationManager lm;
    private String locationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902217_locate);
        locateView = (TextView) findViewById(R.id.locateView);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        if(providers.contains(LocationManager.GPS_PROVIDER)){
            locationProvider = LocationManager.GPS_PROVIDER;
        }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else{
            Toast.makeText(this, "没有可用的位置", Toast.LENGTH_SHORT).show();
            return ;
        }

        try {
            Location location = lm.getLastKnownLocation(locationProvider);
            if (lm != null) {
                lm.removeUpdates(locationListener);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        try {
            lm.requestLocationUpdates(locationProvider, 2000, 1, locationListener);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }

    private void showLocation(Location location){
        String locationStr = "纬度：" + location.getLatitude() +"\n"
                           + "经度：" + location.getLongitude();
        locateView.setText(locationStr);
    }

    LocationListener locationListener =  new LocationListener() {

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

}
