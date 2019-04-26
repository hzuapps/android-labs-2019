package com.hzuandroid.netconncheck;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BaseActivity extends AppCompatActivity {
    private NetworkChangeReceiver receiver;
    private TextView tv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);
        tv_info = findViewById(R.id.tv_info);
        IntentFilter intentFilter = new IntentFilter();

        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver,intentFilter);
        receiver.setOnCheckNetworkCallBack(new NetworkChangeReceiver.CheckNetworkCallBack() {
            @Override
            public void onChecked() {
                String msg = Netinfo.NetMessage.MESSAGE.getMsg();
                String net_name = Netinfo.NetType.TYPE_NAME.getType_name();
                boolean isUse = Netinfo.Net_state.ISCONNECT.isCanUse();
                String state_name = Netinfo.Net_state.ISCONNECT.getState_bame();
                tv_info.setText(msg+"\r\n"+net_name+"\r\n"+state_name+"\r\n"+isUse);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}

