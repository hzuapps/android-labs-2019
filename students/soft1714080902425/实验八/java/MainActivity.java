package edu.androidlabs.thirdtest;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {

    private Button mCheckButton;
    private Button listbtn = null;
    private TextView etinfo;
    private Button but2;
    private boolean mConnected;
    private TextView mNetworkText;
    private Button start=null;
    private Button pause=null;
    private Button stop=null;
    private TextView state=null;
    private MediaPlayer mp3;
    private Boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listbtn = (Button) findViewById(R.id.musicList);
        listbtn.setOnClickListener(new MainActivity.MyButtonListener1());
        start=(Button) super.findViewById(R.id.start);
        start.setOnClickListener(new ButtonListener());
        etinfo = (TextView) findViewById(R.id.edit);
        but2 = (Button) findViewById(R.id.download);
        but2.setOnClickListener(new MainActivity.MyButtonListener2());
        mNetworkText = (TextView) findViewById(R.id.text_network);
        mCheckButton = (Button) findViewById(R.id.button_check);
        mCheckButton.setOnClickListener(new MainActivity.MyButtonListener3());
        pause=(Button) findViewById(R.id.pause);
        stop=(Button) findViewById(R.id.stop);
        state=(TextView) findViewById(R.id.state);
        start.setOnClickListener(new MainActivity.OnClickListenerStart());
        pause.setOnClickListener(new MainActivity.OnClickListenerPause());
        stop.setOnClickListener(new MainActivity.OnClickListenerStop());
        mp3= new MediaPlayer();
        mp3 = MediaPlayer.create(MainActivity.this,R.raw.a);
    }

    private class OnClickListenerStart implements View.OnClickListener {
        @Override
        public void onClick(View v)
        {
            try
            {
                if (mp3!=null)
                {
                    mp3.stop();
                }
                mp3.prepare();
                mp3.start();
                state.setText("正在播放");
            } catch (Exception e)
            {
                state.setText(e.toString());
                e.printStackTrace();
            }
        }
    }
    private class OnClickListenerPause implements View.OnClickListener{
        @Override
        public void onClick(View v)
        {
            try
            {
                if (flag==false)
                {
                    mp3.pause();
                    flag=true;
                    state.setText("已暂停，再次点击继续");
                }
                else if(flag==true){
                    mp3.start();
                    flag=false;
                    state.setText("正在播放");
                }
            } catch (Exception e)
            {
                state.setText(e.toString());
                e.printStackTrace();
            }
        }
    }
    //停止播放
    private class OnClickListenerStop implements View.OnClickListener{
        @Override
        public void onClick(View v)
        {
            try
            {
                if (mp3!=null)
                {
                    mp3.stop();
                    state.setText("已停止");
                }
            } catch (Exception e)
            {
                state.setText(e.toString());
                e.printStackTrace();
            }
        }
    }
    //重写暂停状态事件
    protected void onPause(){
        try
        {
            mp3.release();
        } catch (Exception e)
        {
            state.setText(e.toString());
            e.printStackTrace();
        }
        super.onPause();
    }

    //跳转到音乐列表
    private class MyButtonListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MusicListActivity.class);
            startActivity(intent);
        }
    }


    //保存数据
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View V) {
            String saveinfo = etinfo.getText().toString().trim();
            FileOutputStream fos;
            try {
                fos = openFileOutput("data.txt", Context.MODE_APPEND);
                fos.write(saveinfo.getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, "数据保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    //提示下载成功
    private class MyButtonListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            new downloadMP3Thread().start();
            Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_LONG).show();
        }
    }

    //下载MP3
    class downloadMP3Thread extends Thread {
        public void run() {
            HttpDownloader httpDownloader = new HttpDownloader();
            int downloadResult = httpDownloader.downloadFiles(
                    "https://raw.githubusercontent.com/AngleBeatQAQ/android-labs-2019/master/students/soft1714080902425/实验六/STEREO DIVE FOUNDATION - Daisy.mp3", "BoBoMusic", "STEREO DIVE FOUNDATION - Daisy.mp3");
            System.out.println("下载结果：" + downloadResult);
        }
    }

    //检查网络
    private class MyButtonListener3 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button_check) {
                checkNetworkState();
            }
        }
    }
    private void checkNetworkState() {
        // 取得连接管理器
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // 检查当前激的网络
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mConnected = true;
        } else {
            mConnected = false;
        }

        String types = "";

        // 检查Wi-Fi
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo != null && networkInfo.isConnected();
        types += isWifiConn ? "Wi-Fi" : "";

        // 检查数据网络
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo != null && networkInfo.isConnected();
        types += isMobileConn ? "流量" : "";

        // 检查蓝牙
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_BLUETOOTH);
        boolean isBluetoothConn = networkInfo != null && networkInfo.isConnected();
        types += isBluetoothConn ? ", 蓝牙" : "";

        mNetworkText.setTextColor(mConnected ? Color.RED : Color.BLUE);
        mNetworkText.setText(mConnected ? "网络正常 (" +types + ")" : "网络未连接!");
    }
}
