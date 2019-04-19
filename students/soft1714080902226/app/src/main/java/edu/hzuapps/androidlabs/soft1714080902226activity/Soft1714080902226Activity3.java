package edu.hzuapps.androidlabs.soft1714080902226activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Soft1714080902226Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_soft17140809022263);

        final MyImageView myImageView = (MyImageView) findViewById(R.id.image_view);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//直接把网络的图片路径写上就可以显示网络的图片了
                myImageView.setImageURL("http://img5.imgtn.bdimg.com/it/u=3434277057,648399602&fm=26&gp=0.jpg");
            }
        });

    }


}

