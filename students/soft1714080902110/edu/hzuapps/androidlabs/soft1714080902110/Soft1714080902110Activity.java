package edu.hzuapps.androidlabs.soft1714080902110;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Soft1714080902110Activity extends AppCompatActivity {

    public boolean isExit = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902110_activity);

        Button start_game = (Button) findViewById(R.id.start_game);
        Button rank_list = (Button) findViewById(R.id.rank_list);

        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_game = new Intent (Soft1714080902110Activity.this, Game.class);
                startActivity(start_game);
            }
        });
        rank_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rank_list = new Intent(Soft1714080902110Activity.this, RankList.class);
                startActivity(rank_list);
            }
        });
    }

    //双击退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
        }
        return false;
    }

    private void exit() {
        Timer timer;
        if (!isExit) {
            isExit = true;
            Toast exit_game = Toast.makeText(Soft1714080902110Activity.this, "", Toast.LENGTH_SHORT);
            exit_game.setText("再按一次退出游戏");
            exit_game.show();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }
}
