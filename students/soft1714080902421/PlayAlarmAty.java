package edu.hzuapps.androidlabs.soft1714080902421;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlayAlarmAty extends AppCompatActivity {
    private MediaPlayer mp;

    Button btnAlarmPause;
    Button btnAlarmReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_player_aty);

        mp = MediaPlayer.create(this, R.raw.ringtone);
        mp.start();

        btnAlarmPause = (Button) findViewById(R.id.btnAlarmPause);
        btnAlarmPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
            }
        });

        btnAlarmReset = (Button) findViewById(R.id.btnAlarmReset);
        btnAlarmReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        mp.pause();
        mp.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mp.stop();
        mp.release();
    }
}

