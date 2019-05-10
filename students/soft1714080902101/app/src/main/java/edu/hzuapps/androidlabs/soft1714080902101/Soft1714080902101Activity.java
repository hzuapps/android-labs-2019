package edu.hzuapps.androidlabs.soft1714080902101;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Soft1714080902101Activity extends AppCompatActivity {

    private Button myButton_ct1;
    private Button myButton_chat;
    private Button myButton_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902101_activity);

        myButton_ct1 = (Button)findViewById(R.id.bt_ct1);
        myButton_ct1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Soft1714080902101Activity.this, ChatActivity.class);
                startActivity(intent);

            }
        });

        myButton_download = (Button)findViewById(R.id.bt_download);
        myButton_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902101Activity.this, DownloadActivity.class);
                startActivity(intent);

            }
        });



    }

    NetWorkStateReceiver netWorkStateReceiver;

    //onResume()方法注册
    @Override
    protected void onResume(){
        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        System.out.println("注册");
        super.onResume();
    }

    //onPause()方法注销
    @Override
    protected void onPause() {
        unregisterReceiver(netWorkStateReceiver);
        System.out.println("注销");
        super.onPause();
    }


}
