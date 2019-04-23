package edu.hzuapps.androidlabs.soft1714080902210;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902210Activity_Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902210__second);
        final MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.christopherlarkincityoftears);
        Button btn_play=findViewById(R.id.button4);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        Button btn_stop=findViewById(R.id.button11);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
            }
        });
        Button btn_pause=findViewById(R.id.button12);
        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
        Button btnOpen = findViewById(R.id.button2);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902210Activity_Second.this,Soft1714080902210Activity_New.class));
            }
        });
        Button btnOpen1 = findViewById(R.id.button3);
        btnOpen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902210Activity_Second.this,Soft1714080902210Activity_Check.class));
            }
        });
        Button btnOpen2 = findViewById(R.id.button10);
        btnOpen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902210Activity_Second.this,Sort1714080902210Activity.class));
            }
        });
        Button btnOpen3 = findViewById(R.id.button13);
        btnOpen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902210Activity_Second.this, Soft1714080902210Activity_Change.class));
            }
        });
        Button btnOpen4 = findViewById(R.id.button17);
        btnOpen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902210Activity_Second.this,Soft1714080902210Activity_Share.class));
            }
        });
    }

}
