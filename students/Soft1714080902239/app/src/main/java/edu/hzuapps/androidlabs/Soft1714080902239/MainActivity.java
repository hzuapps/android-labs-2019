package edu.hzuapps.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private  Handler handler;
    private  ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView =(ImageView)findViewById(R.id.imageview);
        handler = new Handler();
        new HttpThread("https://github.com/ridworld/android-labs-2019/blob/master/students/Soft1714080902239/app/src/main/res/drawable/xiaoxin.jpg", imageView, handler).start();

    }
}
