package edu.hzuapps.androidlabs.soft1714080902420;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PlayActivity extends AppCompatActivity {

    private EditText Text1;
    private Button btu_save;
    private Button btu_read;
    private Button btu_download;
    private Button picture_download;
    private VideoView videoView;
    private Button playview;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Text1 = (EditText) findViewById(R.id.text1);
        btu_save = (Button) findViewById(R.id.button13);
        btu_read = (Button) findViewById(R.id.button14);
        playview = (Button) findViewById(R.id.button16);
        btu_download = (Button) findViewById(R.id.button12);
        picture_download = (Button) findViewById(R.id.button19);
        btu_save.setOnClickListener(new ButtonListener());
        btu_read.setOnClickListener(new ButtonListener());
        playview.setOnClickListener(new ButtonListener());
        btu_download.setOnClickListener(new ButtonListener());
        picture_download.setOnClickListener(new ButtonListener());
        videoView = (VideoView) this.findViewById(R.id.video);

    }
    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button13:
                    String saveinfo = Text1.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("entry.txt", Context.MODE_PRIVATE);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(PlayActivity.this, "影评已保存", Toast.LENGTH_LONG).show();
                    break;
                case R.id.button14:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("entry.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(PlayActivity.this, "您的影评是:" + content, Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
                case R.id.button16:
                    Uri uri = Uri.parse("https://www.imeiju.cc/3cd0d803-a95c-490f-964d-da970eba9008");
                    videoView.setVideoURI(uri);
                    videoView.start();
                    videoView.requestFocus();
                case R.id.button12:
                    Intent intent1 = new Intent();
                    intent1.setClass(PlayActivity.this, DownloadActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.button19:
                    Intent intent2 = new Intent();
                    intent2.setClass(PlayActivity.this, PictureActivity.class);
                    startActivity(intent2);
                    break;
            }
        }

    }
    public class NetWorkStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            NetworkUtils .checkNetwork(context);
        }
    }
    private NetWorkStateReceiver mNetWorkStateReceiver;

    @Override
    protected void onResume() {
        if (mNetWorkStateReceiver== null) {
            mNetWorkStateReceiver= new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetWorkStateReceiver, filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mNetWorkStateReceiver);
        super.onPause();
    }
}

