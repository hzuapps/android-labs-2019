package edu.hzuapps.androidlabs.examples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverDemo extends BroadcastReceiver {

    public static final String TAG = BroadcastReceiverDemo.class.getSimpleName();

    public BroadcastReceiverDemo() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "广播已接收!");
    }
}
