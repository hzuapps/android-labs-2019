package edu.hzuapps.androidlabs.soft1714080902336;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import static android.net.ConnectivityManager.TYPE_WIFI;
import static android.provider.ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE;


public class Soft1714080902336Activity extends AppCompatActivity {

    final String LOG = "---ScreenOrientation---" ;
    private Button button;

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                switch (networkInfo.getType()) {
                    case TYPE_MOBILE:
                        Toast.makeText(context, "正在使用2G/3G/4G网络", Toast.LENGTH_SHORT).show();
                        break;
                    case TYPE_WIFI:
                        Toast.makeText(context, "正在使用wifi上网", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            } else {
                Toast.makeText(context, "当前无网络连接", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        if (savedInstanceState == null) {
            setContentView(R.layout.activity_soft1714080902336);
            button = (Button) findViewById(R.id.cs01);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Soft1714080902336Activity.this, SecondActivity.class);
                    startActivity(intent);
                }
            });

            button = (Button) findViewById(R.id.cs02);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Soft1714080902336Activity.this, FriendActivity.class);
                    startActivity(intent);
                }
            });

            button = (Button) findViewById(R.id.cs03);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Soft1714080902336Activity.this, AddActivity.class);
                    startActivity(intent);
                }
            });
        }
        if(savedInstanceState != null){
            //横屏
            if( ScreenOrient(this)==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE  )
                setContentView(R.layout.activity_soft1714080902336);
            //竖屏
            if( ScreenOrient(this)==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  )
                setContentView(R.layout.activity_soft1714080902336_heping);
            String temp = savedInstanceState.getString("data_key") ;
            Log.d(LOG , "重新创建了Activity，之前保存的内容是"+temp) ;
        }
    }
    public int ScreenOrient(Activity activity)
    {
        int orient = activity.getRequestedOrientation();
        if(orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int screenWidth  = display.getWidth();
            int screenHeight = display.getHeight();
            orient = screenWidth < screenHeight ?  ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        }
        return orient;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you want to save";
        outState.putString("data_key", tempData);
        Log.d(LOG, "onSaveInstanceState..");
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

}

