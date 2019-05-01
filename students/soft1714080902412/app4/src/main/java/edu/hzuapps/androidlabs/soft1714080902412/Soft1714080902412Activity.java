package edu.hzuapps.androidlabs.soft1714080902412;

import android.app.Service;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class Soft1714080902412Activity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902412);
        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        startAlarm();

    }
    private void startAlarm() {

        Uri uri1=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mediaPlayer = MediaPlayer.create(this, uri1);
        mediaPlayer.setLooping(true);
        try {
            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play:
                if (!mediaPlayer.isPlaying())
                    mediaPlayer.start();
                vibrator=(Vibrator)getApplication().getSystemService(Service.VIBRATOR_SERVICE);
                long [] hh={1000,1000,1000,1000};
                vibrator.vibrate(hh,2);

                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                vibrator.cancel();
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    vibrator.cancel();
                }
                break;
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer!=null)
            mediaPlayer.stop();
        mediaPlayer.release();
    }

}
