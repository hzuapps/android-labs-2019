package edu.hzuapps.androidlabs.soft1714080902226activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import edu.hzuapps.androidlabs.soft1714080902226activity.view.SlideMenu;

public class Soft1714080902226Activity2 extends AppCompatActivity {

    private ImageView mImageView;

    private ImageView btn_back;
    private SlideMenu slideMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_soft17140809022262);
        mImageView = findViewById(R.id.diyiye);
        mImageView.setImageResource(R.drawable.tu2);
        btn_back = (ImageView)findViewById(R.id.btn_back);
        slideMenu = (SlideMenu)findViewById(R.id.slideMenu);
        //点击返回键打开或关闭Menu
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideMenu.switchMenu();
            }
        });
    }
}
