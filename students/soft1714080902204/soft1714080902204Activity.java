package edu.hzuapps.androidlabs.soft1714080902204;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902204Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902204);

        Button btnOpen = findViewById(R.id.button);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902204Activity.this,Soft1714080902204Activity_Second.class));

            }
        });
        final MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.yyz);//创建的mediaplayer对象，且装载了播放音频

        Button btn_stop=findViewById(R.id.button8);
        Button btn_pause=findViewById(R.id.button2);
        Button btn_play = findViewById(R.id.button3);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();//播放音频

            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();//暂停

            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();//停止

            }
        });




    }
}
