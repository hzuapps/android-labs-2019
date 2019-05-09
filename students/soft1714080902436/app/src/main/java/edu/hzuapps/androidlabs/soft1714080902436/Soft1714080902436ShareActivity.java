package edu.hzuapps.androidlabs.soft1714080902436;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;


public class Soft1714080902436ShareActivity extends AppCompatActivity implements AMapLocationListener {


    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private TextView textView;
    private String[] strMsg;
    public final static int START = 0;//开始定位
    public final static int FINISH = 1;//定位完成

    //判断GPS模块是否存在或开启
    private void openGPSSettings() {
        LocationManager alm = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText( this , " GPS模块正常 " , Toast.LENGTH_SHORT)
                    .show();
            return ;
        }
        Toast.makeText( this , " 请开启GPS！ " , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivityForResult(intent, 0 ); // 此为设置完成后返回到获取界面
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902436_share);
        textView = (TextView) findViewById(R.id.LocalText);
        Location();
    }

    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = FINISH;
            mHandler.sendMessage(msg);
        }
    }

    Handler mHandler =
            new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
                //定位完成
                case FINISH:
                    String result = "";
                    try {
                        AMapLocation loc = (AMapLocation) msg.obj;
                        //国家+省+市+区+兴趣点
                        result=loc.getCountry() + loc.getProvince() + loc.getCity()  +  loc.getPoiName() + "\n";
                        Toast.makeText(Soft1714080902436ShareActivity.this, "定位成功", Toast.LENGTH_LONG).show();
                        textView.setText( result );
                    } catch (Exception e) {
                        Toast.makeText(Soft1714080902436ShareActivity.this, "定位失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        }

        ;

    };

    public void Location() {
        // TODO Auto-generated method stub
        try {
            locationClient = new AMapLocationClient(this);
            locationOption = new AMapLocationClientOption();
            // 设置定位模式为低功耗模式
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            // 设置定位监听
            locationClient.setLocationListener(this);
            locationOption.setOnceLocation(true);//设置为单次定位
            locationClient.setLocationOption(locationOption);// 设置定位参数
            // 启动定位
            locationClient.startLocation();
            mHandler.sendEmptyMessage(START);
        } catch (Exception e) {
            Toast.makeText(Soft1714080902436ShareActivity.this, "定位失败", Toast.LENGTH_LONG).show();
        }
    }

}

