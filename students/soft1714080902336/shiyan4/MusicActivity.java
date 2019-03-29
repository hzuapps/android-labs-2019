package edu.hzuapps.androidlabs.soft1714080902336;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {

    private Intent Serviceintent = new Intent("com.angel.Android.MUSIC");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(MusicActivity.this,MusicServer.class);
        super.onCreate(savedInstanceState);
        startService(intent);
        setContentView(R.layout.activity_music);
    }

    protected void onStop(){
        Intent intent = new Intent(MusicActivity.this,MusicServer.class);
        stopService(intent);
        super.onStop();
    }
}

