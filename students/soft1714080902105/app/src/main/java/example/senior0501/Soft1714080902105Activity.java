package example.senior0501;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Soft1714080902105Activity extends AppCompatActivity
{

    final String TAG = "测试MainActivity";
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.w(TAG, "onCreate...");
        //setContentView(R.layout.activity_main);

        orientation=getResources().getConfiguration().orientation;
        if(orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        {
            setContentView(R.layout.activity_main_portrait);
            Log.d(TAG,"现在为竖屏模式portrait...");
        }
        else
        {
            setContentView(R.layout.activity_main_landscape);
            Log.d(TAG,"现在为横屏模式landscape...");
        }

    }

    @Override
    protected void onDestroy()
    {
        Log.w(TAG, "onDestroy...");
        super.onDestroy();
    }

}
