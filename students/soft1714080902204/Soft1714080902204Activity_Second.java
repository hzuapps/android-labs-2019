package edu.hzuapps.androidlabs.soft1714080902204;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;


import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class Soft1714080902204Activity_Second extends AppCompatActivity {
    private TextView text;
    private MapView mMapView;//地图组件
    /*
    private BaiduMap mBaiduMap;//定义百度地图对象
    private boolean isFirstLoc=true;//记录是否是第一次定位
    private MyLocationConfiguration.LocationMode locationMode;//定义当前定位模式*/




   /*rivate void judgePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0以上动态权限
            String[] permissions =
                    new String[]{
                            Manifest.permission.WRITE_SETTINGS
                    };
            List<String> mPermissionList = new ArrayList<>();
            mPermissionList.clear();
            for (String permission : permissions) {
                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permission);
                }
            }
            if (!mPermissionList.isEmpty()) {//请求权限方法
                permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
                ActivityCompat.requestPermissions(Soft1714080902204Activity_Second.this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {// 标识符
            case 1:
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        //判断是否勾选禁止后不再询问
                        boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                        if (showRequestPermission) {//
                            judgePermission();//重新申请权限
                            return;
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
*/


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());//初始化地图SDK
        setContentView(R.layout.activity_soft1714080902204_second);
        mMapView=(MapView) findViewById(R.id.bmapView);
        text=findViewById(R.id.textView);


        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);//获取LocationManager对象
        //添加权限检查
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                1,
                new LocationListener() {//监听GPS定位信息是否改变
                    @Override
                    public void onLocationChanged(Location location) {
                        locationUpdates(location);//GPS信息改变时，更新位置信息

                    }

                    @Override
                    public void onStatusChanged(String textView, int status, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String textView) {

                    }

                    @Override
                    public void onProviderDisabled(String textView) {

                    }
                }
        );
        Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        locationUpdates(location);//将最新的定位信息传递给创建的locationUpdates方法中



        Button btnOpen = findViewById(R.id.button4);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902204Activity_Second.this, Soft1714080902204Activity_Third.class));

            }
        });

        btnOpen = findViewById(R.id.button5);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902204Activity_Second.this, Soft1714080902204Activity_Four.class));

            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        mMapView.onResume();
    }


    @Override
    protected void onPause(){
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mMapView.onDestroy();
        mMapView=null;

    }
/*
    @Override
    protected void onStart(){
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);//开启定位图层
    }

    @Override
    protected void onStop(){
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);//停止定位图层
    }

*/

    public void locationUpdates(Location location){
        if(location!=null){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("你的位置是：\n");
            stringBuilder.append("经度：");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append("\n纬度：");
            stringBuilder.append(location.getLatitude());
            text.setText(stringBuilder.toString());//显示到页面上
/*
            if(isFirstLoc){
                MapStatusUpdate u= (MapStatusUpdate) MapStatusUpdateFactory.newLatLng(ll);//更新坐标位置
                mBaiduMap.animateMapStatus(u);//设置地图位置
                isFirstLoc=false;//取消第一次定位
            }
            //构造定位数据

            MyLocationData locData=new MyLocationData.Builder().accuracy(location.getAccuracy()).direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromResource(R.drawable.chacha);//自定义图标
            locationMode=MyLocationConfiguration.LocationMode.NORMAL;//设置定位模式
            MyLocationConfiguration config=new MyLocationConfiguration(locationMode,true,bitmapDescriptor);//设置构造方式
            mBaiduMap.setMyLocationConfiguration(config);//显示定位图标
*/
        }else{
            text.setText("没有获取到GPS信息");
        }
    }
}
