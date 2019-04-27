package edu.hzuapps.androidlabs.soft1714080902413.wuziqi;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class Soft1714080902413startgame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809024134);

        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            Toast toastCenter=Toast.makeText(getApplicationContext(),"请将手机竖屏显示，谢谢",Toast.LENGTH_LONG);
            toastCenter.setGravity(Gravity.CENTER,0,0);
            toastCenter.show();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制为竖屏
        }

    }
}
