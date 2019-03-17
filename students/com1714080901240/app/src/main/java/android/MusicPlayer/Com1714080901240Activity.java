package android.MusicPlayer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Com1714080901240Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901240);
    }
    public void click01(View view){
        //创建一个Intent对象，开启下一个activity
        Intent intent1 = new Intent(this,Com1714080901240Activity_01.class);
        startActivity(intent1);
    }
    public void click02(View view){
        //创建一个Intent对象，开启下一个activity
        Intent intent2 = new Intent(this,Com1714080901240Activity_02.class);
        startActivity(intent2);
    }
}
