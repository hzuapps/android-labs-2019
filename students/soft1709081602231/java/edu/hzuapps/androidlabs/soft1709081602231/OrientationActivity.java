package edu.hzuapps.androidlabs.soft1709081602231;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrientationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        // 通过Configuration对象 确认当前显示方向
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
            Toast toast = Toast.makeText(OrientationActivity.this,"为了更好的体验，请使用竖屏浏览",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            LinearLayout layout = (LinearLayout) toast.getView();
            layout.setBackgroundColor(Color.parseColor("#FFFFFF"));  //设置toast的背景颜色
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message); //toast显示的文本内容
            textView.setTextColor(Color.RED);   //设置toast的字体颜色
            textView.setTextSize(20);           //设置toast的字体大小
            toast.show();
        }
    }
}
