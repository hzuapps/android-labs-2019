package com.example.administrator.pic;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void init()
    {
        String[] images={"file://"+Environment.getExternalStorageDirectory().getPath()+"/a.jpg",
"file://"+Environment.getExternalStorageDirectory().getPath()+"/b.jpg",
"file://"+Environment.getExternalStorageDirectory().getPath()+"/c.jpg",
"file://"+Environment.getExternalStorageDirectory().getPath()+"/d.jpg",
"file://"+Environment.getExternalStorageDirectory().getPath()+"/e.jpg"

};

        final Banner banner = (Banner) findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());//图片加载器
        banner.setImages(images);//图片集合
        banner.start();
        Button Button = (Button) findViewById(R.id.button_id);
        Button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                banner.stopAutoPlay();
                Toast.makeText(MainActivity.this, "停止", Toast.LENGTH_SHORT).show();
            }
        });
        Button Button2 = (Button) findViewById(R.id.button_sb);
        Button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                banner.startAutoPlay();
                Toast.makeText(MainActivity.this, "动起来", Toast.LENGTH_SHORT).show();
            }
        });
        Button Button_skip =(Button) findViewById(R.id.Skip_activity);
                    Button_skip.setOnClickListener(
                            new View.OnClickListener()
                    {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(new Intent(MainActivity.this,new_activity.class));
                    }
                }
        );

    }
}