package edu.hzuapps.androidlabs;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class Soft1714080902209_Main extends AppCompatActivity implements AMapLocationListener {

    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902209_main);

        Reg_button=(Button)findViewById(R.id.Main_Reg);
        Reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_reg = new Intent();
                intent_reg.setClass(Soft1714080902209_Main.this, Soft1714080902209_Reg.class);//跳转至注册
                startActivity(intent_reg);
            }
        });
        Login_button=(Button)findViewById(R.id.Main_Login);
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=((EditText)findViewById(R.id.main_um)).getText().toString();
                String password=((EditText)findViewById(R.id.main_pwd)).getText().toString();
                if(username.equals("Admin") && password.equals("test")){
                    Toast.makeText(Soft1714080902209_Main.this,"欢迎，" + username + "\n登陆地点：" + location,Toast.LENGTH_SHORT).show();
                        Intent intent_login = new Intent();
                        intent_login.setClass(Soft1714080902209_Main.this, Soft1714080902209_Login.class);//登陆成功后跳转
                        startActivity(intent_login);
                        Soft1714080902209_Main.this.finish();
                }else if(username.equals("") && password.equals("")){
                    Toast.makeText(Soft1714080902209_Main.this,"请输入账号密码",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Soft1714080902209_Main.this,"账号密码错误",Toast.LENGTH_SHORT).show();
                }

            }
        });

        boolean netconn = new Soft714080902209_NetConn().IsNetConn();
        if(netconn){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RequestLocation();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

            //Toast.makeText(Soft1714080902209_Main.this, "true",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Soft1714080902209_Main.this,"Network disconnected",Toast.LENGTH_SHORT).show();
        }


    }

    public void RequestLocation() {
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};

        if (Build.VERSION.SDK_INT >= 23) {
            //如果超过6.0才需要动态权限，否则不需要动态权限
            //如果同时申请多个权限，可以for循环遍历
            int check = ContextCompat.checkSelfPermission(this,permissions[0]);
            if (check == PackageManager.PERMISSION_GRANTED) {
                //run
                GetLocation();
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        } else {
            GetLocation();
        }
    }
    private Button Reg_button;
    private Button Login_button;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String[] strMsg;

    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }

    }


    private Handler mHandler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOCATION_FINISH:
                    String result = "";
                    try {
                        AMapLocation loc = (AMapLocation) msg.obj;
                        result = getLocationStr(loc, 1);
                        strMsg = result.split(",");
                        location = strMsg[0] + " " + strMsg[1];
                        //Toast.makeText(Soft1714080902209_Main.this, location,Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        location = "unknow";
                        //Toast.makeText(Soft1714080902209_Main.this, location,Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    public void GetLocation() {
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
            mHandler.sendEmptyMessage(MSG_LOCATION_START);

        } catch (Exception e) {
        }
    }




    public final static int MSG_LOCATION_START = 0;

    public final static int MSG_LOCATION_FINISH = 1;

    public synchronized static String getLocationStr(AMapLocation location, final int index) {
        if (null == location) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
        if (location.getErrorCode() == 0) {
            sb.append("定位成功" + "\n");
            sb.append("定位类型: " + location.getLocationType() + "\n");
            sb.append("经    度    : " + location.getLongitude() + "\n");
            sb.append("纬    度    : " + location.getLatitude() + "\n");
            sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
            sb.append("提供者    : " + location.getProvider() + "\n");

            if (location.getProvider().equalsIgnoreCase(
                    android.location.LocationManager.GPS_PROVIDER)) {
                // 以下信息只有提供者是GPS时才会有
                sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                sb.append("角    度    : " + location.getBearing() + "\n");
                // 获取当前提供定位服务的卫星个数
                sb.append("星    数    : "
                        + location.getSatellites() + "\n");
            } else {
                // 提供者是GPS时是没有以下信息的
                sb.append("国    家    : " + location.getCountry() + "\n");
                sb.append("省            : " + location.getProvince() + "\n");
                sb.append("市            : " + location.getCity() + "\n");
                sb.append("城市编码 : " + location.getCityCode() + "\n");
                sb.append("区            : " + location.getDistrict() + "\n");
                sb.append("区域 码   : " + location.getAdCode() + "\n");
                sb.append("地    址    : " + location.getAddress() + "\n");
                sb.append("兴趣点    : " + location.getPoiName() + "\n");
                //return (location.getAddress() + "," + location.getLongitude() + "," + location.getLatitude());
                return(location.getProvince() + "," + location.getCity());
            }
        } else {
            //定位失败
            sb.append("unknow" + "\n");
            //sb.append("错误码:" + location.getErrorCode() + "\n");
            //sb.append("错误信息:" + location.getErrorInfo() + "\n");
            //sb.append("错误描述:" + location.getLocationDetail() + "\n");
            return sb.toString();
        }
        return sb.toString();
    }
}
