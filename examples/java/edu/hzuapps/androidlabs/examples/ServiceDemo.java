package edu.hzuapps.androidlabs.examples;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ServiceDemo extends Service implements Runnable {

    public static final String TAG = ServiceDemo.class.getSimpleName();

    private boolean isRunning = false;

    /** 封装服务 */
    private IBinder mTBinder;

    // 启动新线程运行
    private Thread mThread;

    public ServiceDemo() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.i(TAG, "onBind()");
        return mTBinder;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "服务停止!!!!");
        isRunning = false;
    }

    public void doSomething() {
        Log.i(TAG, "Doing something ...");
    }

    /**
     * 服务启动时,执行此回调方法.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "服务启动!!!!");
        if (!isRunning) {
            mThread.start();
        }
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        isRunning = false;
        mThread = new Thread(this);
        mTBinder = new LocalBinder();
    }

    @Override
    public void run() {
        isRunning = true;

        int counting = 0;

        while (isRunning) {
            try {
                Log.i(TAG, "服务正在运行……" + ++counting);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class LocalBinder extends Binder {

        ServiceDemo getService() {
            return ServiceDemo.this;
        }

    }
}
