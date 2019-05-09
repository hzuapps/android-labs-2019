package edu.hzuapps.androidlabs.soft1709081602513;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;



public class StartActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private Button bgMusic;


    final String LOG = "------Screen------" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
         bgMusic =  findViewById(R.id.bg_music);
        bgMediaPlayer();//播放BGM


        bgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause(); // 暂停播放
                Toast.makeText(StartActivity.this,"BGM已暂停！",Toast.LENGTH_SHORT).show();
                }
                else
                     mediaPlayer.start();
                }
        });
        //如果是横屏则改为竖屏
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        // 应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

//        Activity的方向由方向传感器来决定，显示会根据用户设备的移动情况来旋转（已废弃）
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);



//        if (savedInstanceState == null) {
//            setContentView(R.layout.activity_start);
//        }
//        //检测是横屏还是竖屏
//        if(savedInstanceState != null){
//            //若为横屏
//            if( ScreenOrient(this)==ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE  )
//                setContentView(R.layout.activity_start_landscape);
//            //竖屏
//            if( ScreenOrient(this)==ActivityInfo.SCREEN_ORIENTATION_PORTRAIT  )
//                setContentView(R.layout.activity_start);
//            String temp = savedInstanceState.getString("data_key") ;
//            Log.d(LOG , "重新创建了Activity，之前保存的内容是"+temp) ;
//        }
    }


//    public int ScreenOrient(Activity activity)
//    {
//        int orient = activity.getRequestedOrientation();
//        if(orient != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE && orient != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//            WindowManager windowManager = activity.getWindowManager();
//            Display display = windowManager.getDefaultDisplay();
//            int screenWidth  = display.getWidth();
//            int screenHeight = display.getHeight();
//            orient = screenWidth < screenHeight ?  ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
//        }
//        return orient;
//    }
//
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        String tempData = "Something you want to save";
//        outState.putString("data_key", tempData);
//        Log.v(LOG, "onSaveInstanceState..");
//    }
    // 背景音乐
    private void bgMediaPlayer() {
        try {
            mediaPlayer = new MediaPlayer();
            Uri setDataSourceuri = Uri.parse("android.resource://edu.hzuapps.androidlabs.soft1709081602513/"+R.raw.music);
            mediaPlayer.setDataSource(this, setDataSourceuri); // 指定音频文件的路径
            mediaPlayer.setLooping(true);//无限循环
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error:","BGM加载错误！" );
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        mediaPlayer.pause();
    }
    @Override
    protected void onStop(){
        super.onStop();
        mediaPlayer.pause();
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        mediaPlayer.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}