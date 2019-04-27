package android.MusicPlayer;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class Com1714080901240Activity extends AppCompatActivity {
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Button play_btn,pre_btn,next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901240);

        play_btn=(Button) this.findViewById(R.id.play_btn);
        next_btn=(Button) this.findViewById(R.id.next_btn);
        pre_btn=(Button) this.findViewById(R.id.pre_btn);
    }

    public void play_stop(View view){
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource("https://raw.githubusercontent.com/skyzzk/android-labs-2019/master/students/com1714080901240/app/src/main/1.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.prepareAsync();
                mediaPlayer.start();
            }
        });

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
