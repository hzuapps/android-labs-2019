package edu.hzuapps.androidlabs.examples;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.BackActivity;

public class ServiceDemoActivity extends BackActivity {

    public static final String TAG = ServiceDemoActivity.class.getSimpleName();

    private ServiceDemo mBoundService;
    private ServiceConnectionDemo mServiceConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);

        // 绑定事件到按钮
        final Activity thisActivity = this;
        Button button = (Button) findViewById(R.id.button_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thisActivity, ServiceDemo.class);
                thisActivity.startService(intent);
            }
        });

        button = (Button) findViewById(R.id.button_stop);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thisActivity, ServiceDemo.class);
                thisActivity.stopService(intent);
            }
        });

        button = (Button) findViewById(R.id.button_start_intent_service);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentServiceDemo.startActionFoo(thisActivity, "参数1", "参数2");
            }
        });

        button = (Button) findViewById(R.id.button_stop_intent_service);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentServiceDemo.stopActionFoo(thisActivity);
            }
        });

        button = (Button) findViewById(R.id.button_bound_service);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBoundService.doSomething();
            }
        });

        mServiceConnection = new ServiceConnectionDemo();
        doBindService();
    }

    void doBindService() {
        Intent intent = new Intent(this, ServiceDemo.class);
        this.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    void doUnbindService() {
        unbindService(mServiceConnection);
    }

    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    class ServiceConnectionDemo implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "服务连接成功!");
            mBoundService = ((ServiceDemo.LocalBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "服务断开!");
            mBoundService = null;
        }
    }

}
