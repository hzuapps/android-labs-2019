package edu.hzuapps.androidlabs.Soft1714080902238;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import lun.com.myapplication.R;

public class DisplayActivity extends Activity implements View.OnClickListener {
    private IntentFilter intentFilter;
    private displayActivity.NetworkChangeReceiver networkChangeReceiver;

    @Override
    public void onClick(View v) {

    }

    class  NetworkChangeReceiver extends BroadcastReceiver {//网络变化接收者
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(displayActivity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null && networkInfo.isAvailable())
                Toast.makeText(displayActivity.this, "当前网络可用", Toast.LENGTH_SHORT).show();
            else {
                Toast.makeText(displayActivity.this, "当前网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Log.i("Activity01","onCreate()");
        //注册广播接收者，监测网络变化
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new displayActivity.NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver , intentFilter);

    }
    protected void Click(View view){
        Intent intent = new Intent(this,itemActivity.class);
        startActivity(intent);
    }
}
