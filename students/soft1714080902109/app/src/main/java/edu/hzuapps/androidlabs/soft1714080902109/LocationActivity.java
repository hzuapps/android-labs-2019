package edu.hzuapps.androidlabs.soft1714080902109;

import java.io.IOException;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import edu.hzuapps.androidlabs.Soft1714080902109.R;


public class LocationActivity extends Activity {
    String latLongString;
    Double a, b;
    private TextView city;
    private TextView showJW = null;
    private LocationManager locationManager;
    private double latitude = 0;
    private double longitude = 0;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            double[] data = (double[]) msg.obj;
            showJW.setText("经度：" + data[0] + "\t纬度:" + data[1]);

            List<Address> addList = null;
            Geocoder ge = new Geocoder(getApplicationContext());
            try {
                addList = ge.getFromLocation(data[0], data[1], 1);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                }
                if (addList != null && addList.size() > 0) {
                for (int i = 0; i < addList.size(); i++) {
                Address ad = addList.get(i);
               latLongString = ad.getLocality();
                }			}
                city.setText(latLongString);
        }
    };
                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_location);
                showJW = (TextView) findViewById(R.id.showJW);
                city = (TextView) findViewById(R.id.city);
                locationManager = (LocationManager)
                 getSystemService(Context.LOCATION_SERVICE);
                new Thread() {
                    @Override
               public void run() {
                        Location location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();// 经度
                            longitude = location.getLongitude();// 纬度
                            double[] data = { latitude, longitude };
                            Message msg = handler.obtainMessage();
                            msg.obj = data;
                            handler.sendMessage(msg);
                        }
                    }
                }.start();
                }
                        /**
                         * 确定按钮监听
                         *
                         * @param view
                         */
                        public void getJW(View view) {
                            new Thread() {
                                @Override
                                public void run() {
                                    Location location = locationManager
                                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                    if (location != null) {
                                        latitude = location.getLatitude();// 经度
                                        longitude = location.getLongitude();// 纬度
                                        double[] data = { latitude, longitude };
                                        Message msg = handler.obtainMessage();
                                        msg.obj = data;
                                        handler.sendMessage(msg);
                                    }
                                }
                            }.start();
                        }
}
