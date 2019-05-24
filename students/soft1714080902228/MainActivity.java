package edu.hzuapps.androidlabs.soft1714080902228;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hehongkun.testmusicplayer.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static List<Music> musicList = new ArrayList<Music>();
    private TextView musicStatus, musicTime, musicTotal,musicName;
    private SeekBar seekBar;
    private int currMusic;
    private Button btnPlayOrPause,btnTurnToList,btnPre,btnNext;
    private SimpleDateFormat time = new SimpleDateFormat("mm:ss");

    private boolean tag1 = false;
    private boolean tag2 = false;
    private MusicService musicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GetPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initMusicList();
        currMusic=0;
        findViewById();
        musicName.setText(musicList.get(currMusic).getName());
        bindServiceConnection();
        myListener();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser == true) {
                    musicService.mediaPlayer.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //动态申请权限
    private void GetPermission(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                //没有权限则申请权限
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        }
    }


    void initMusicList() {
        Music music1=new Music("可ai女人",R.drawable.jielun0);
        Music music2=new Music("算什么男人",R.drawable.jielun1);
        Music music3=new Music("手写的从前",R.drawable.jielun2);
        Music music4=new Music("爱在西元前",R.drawable.jielun3);
        Music music5=new Music("稻香",R.drawable.jielun4);
        Music music6=new Music("给我一首歌的时间",R.drawable.jielun5);
        Music music7=new Music("红尘客栈",R.drawable.jielun6);
        Music music8=new Music("兰亭序",R.drawable.jielun7);
        Music music9=new Music("说好的幸福呢",R.drawable.jielun8);
        Music music0=new Music("算什么男人",R.drawable.jielun9);
        musicList.add(music0);
        musicList.add(music1);
        musicList.add(music2);
        musicList.add(music3);
        musicList.add(music4);
        musicList.add(music5);
        musicList.add(music6);
        musicList.add(music7);
        musicList.add(music8);
        musicList.add(music9);
    }

    //连接activity中的组件
    private void findViewById() {
        musicTime = (TextView) findViewById(R.id.MusicTime);
        musicTotal = (TextView) findViewById(R.id.MusicTotal);
        seekBar = (SeekBar) findViewById(R.id.MusicSeekBar);
        btnPlayOrPause = (Button) findViewById(R.id.BtnPlayorPause);
        musicStatus = (TextView) findViewById(R.id.MusicStatus);
        btnTurnToList=(Button) findViewById(R.id.turn_to_list);
        btnNext=(Button)findViewById(R.id.BtnNext);
        btnPre=(Button)findViewById(R.id.BtnPre);
        musicName=(TextView)findViewById(R.id.music_name);
    }


    //  在Activity中调用 bindService 保持与 Service 的通信
    private void bindServiceConnection() {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        startService(intent);
        bindService(intent, serviceConnection, this.BIND_AUTO_CREATE);
    }

    //  回调onServiceConnected 函数，通过IBinder 获取 Service对象，实现Activity与 Service的绑定
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((MusicService.MyBinder) (service)).getService();
            Log.i("musicService", musicService + "");
            musicTotal.setText(time.format(musicService.mediaPlayer.getDuration()));
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };

    //  通过 Handler 更新 UI 上的组件状态
    public Handler handler = new Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            musicTime.setText(time.format(musicService.mediaPlayer.getCurrentPosition()));
            seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
            seekBar.setMax(musicService.mediaPlayer.getDuration());
            musicTotal.setText(time.format(musicService.mediaPlayer.getDuration()));
            handler.postDelayed(runnable, 200);

        }
    };

    private void myListener() {
        ImageView imageView = (ImageView) findViewById(R.id.Image);
        final ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360.0f);
        animator.setDuration(10000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);
        btnPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    musicName.setText(musicList.get(currMusic).getName());
                    musicService.mediaPlayer.setDataSource("/mnt/sdcard/" + musicList.get(currMusic).getName() + ".mp3" );
                    musicService.mediaPlayer.prepare();
                    musicService.mediaPlayer.setLooping(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (musicService.mediaPlayer != null) {
                    //  由tag的变换来控制事件的调用
                    if (musicService.tag != true) {
                        btnPlayOrPause.setBackgroundResource(R.drawable.seach_btn_pause);
                        musicStatus.setText("Playing");
                        musicService.playOrPause();
                        musicService.tag = true;

                        if (tag1 == false) {
                            animator.start();
                            tag1 = true;
                        } else {
                            animator.resume();
                        }
                    } else {
                        btnPlayOrPause.setBackgroundResource(R.drawable.seach_btn_start);
                        musicStatus.setText("Paused");
                        musicService.playOrPause();
                        animator.pause();
                        musicService.tag = false;
                    }
                    if (tag2 == false) {
                        handler.post(runnable);
                        tag2 = true;
                    }
                    seekBar.setProgress(musicService.mediaPlayer.getCurrentPosition());
                    seekBar.setMax(musicService.mediaPlayer.getDuration());
                }
            }
        });
        btnTurnToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MusicListActivity.class);
                moveTaskToBack(true);
                startActivity(intent);
            }
        });

        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.mediaPlayer.reset();
                currMusic--;
                if(currMusic==-1)
                    currMusic=musicList.size()-1;
                try {
                    musicName.setText(musicList.get(currMusic).getName());
                    musicService.mediaPlayer.setDataSource("/mnt/sdcard/" + musicList.get(currMusic).getName() + ".mp3" );
                    musicService.mediaPlayer.prepare();
                    musicService.mediaPlayer.setLooping(true);
                    musicService.mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicService.mediaPlayer.reset();
                currMusic++;
                if(currMusic==musicList.size())
                    currMusic=0;
                try {
                    musicName.setText(musicList.get(currMusic).getName());
                    musicService.mediaPlayer.setDataSource("/mnt/sdcard/" + musicList.get(currMusic).getName() + ".mp3" );
                    musicService.mediaPlayer.prepare();
                    musicService.mediaPlayer.setLooping(true);
                    musicService.mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    /*
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicStatus.setText("Stopped");
                btnPlayOrPause.setText("PLAY");
                musicService.stop();
                animator.pause();
                musicService.tag = false;
            }
        });

        //  停止服务时，必须解除绑定，写入btnQuit按钮中
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                unbindService(serviceConnection);
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                stopService(intent);
                try {
                    MainActivity.this.finish();
                } catch (Exception e) {

                }
            }
        });
        */

    }

    //  获取并设置返回键的点击事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}



