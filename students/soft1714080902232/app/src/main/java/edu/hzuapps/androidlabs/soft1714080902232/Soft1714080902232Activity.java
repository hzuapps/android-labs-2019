package edu.hzuapps.androidlabs.soft1714080902232;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Soft1714080902232Activity extends AppCompatActivity {
    TextView score_show;
    Soft1714080902232Activity_GameView gv;
    TextView new_game;
    Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int num =msg.arg1;
        score_show.setText(num+"");
    }

    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902232_activity);
        score_show = (TextView) findViewById(R.id.tv_score_show);
        gv = (Soft1714080902232Activity_GameView) findViewById(R.id.gv_show);
        new_game = (TextView) findViewById(R.id.tv_newgame);
        new_game.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0){
                gv.GameStart();
                gv.score = 0;
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Message msg = new Message();
                msg.arg1 = gv.score;
                handler.sendMessage(msg);
            }
        }, 80, 150);
        score_show.setText(100 + "");

    }
}