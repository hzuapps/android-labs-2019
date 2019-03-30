package edu.hzuapps.androidlabs.examples;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.BackActivity;

public class BroadcastReceiverActivity extends BackActivity {

    public static final String TAG = BroadcastReceiverActivity.class.getSimpleName();

    public static final String MESSAGE = "edu.androidlabs.DETECTION";

    private BroadcastReceiverDemo mBroadcastReceiver;
    private IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);

        final Activity thisActivity = this;

        Button button = (Button) findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                thisActivity.sendBroadcast(intent);
            }
        });

        // 注册自定义广播
        IntentFilter intentFilter = new IntentFilter(MESSAGE);
        BroadcastReceiverDemo broadcastReceiver = new BroadcastReceiverDemo();
        this.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 注册系统时间广播
        mIntentFilter = new IntentFilter(MESSAGE);
        mBroadcastReceiver = new BroadcastReceiverDemo();
        this.registerReceiver(mBroadcastReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        this.unregisterReceiver(mBroadcastReceiver);
    }
}
