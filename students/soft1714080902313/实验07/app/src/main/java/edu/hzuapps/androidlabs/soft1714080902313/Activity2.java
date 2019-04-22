package edu.hzuapps.androidlabs.soft1714080902313;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import java.io.File;
import java.io.IOException;

public class Activity2 extends AppCompatActivity {
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    /*用来记录录像存储路径*/
    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/video.mp4");//设置录像存储路径
    Uri uri = Uri.fromFile(file);//文件转成Uri格式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Button takePhoto = (Button) findViewById(R.id.take_photo);
        Button chooseFromAlbum = (Button) findViewById(R.id.choose_from_album);
        Button takeVideo = (Button) findViewById(R.id.take_video);
        Button showVideo = (Button) findViewById(R.id.show_videos);

        //对照相功能的响应
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在新的Intent里面打开，并且传递TAKE_PHOTO选项
                Intent intent = new Intent();
                intent.setClass(Activity2.this, image_album_show.class);//也可以这样写intent.setClass(MainActivity.this, OtherActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("id", TAKE_PHOTO);//使用显式Intent传递参数，用以区分功能
                intent.putExtras(bundle);

                Activity2.this.startActivity(intent);//启动新的Intent
            }
        });


        //设置相册选择的响应
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在新的Intent里面打开，并且传递CHOOSE_PHOTO选项
                Intent intent = new Intent();
                intent.setClass(Activity2.this, image_album_show.class);//也可以这样写intent.setClass(MainActivity.this, OtherActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("id", CHOOSE_PHOTO);
                intent.putExtras(bundle);

                Activity2.this.startActivity(intent);
            }
        });
        //设置录像选择的响应
        takeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 保存录像到指定的路径
                // File file = new File("storage/sdcard1/video.mp4" );//设置录像存储路径
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 激活系统的照相机进行录像，通过Intent激活相机并实现录像功能
                Intent intent = new Intent();
                intent.setAction("android.media.action.VIDEO_CAPTURE");
                intent.addCategory("android.intent.category.DEFAULT");

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, 0);
            }
        });
        showVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //initVideoPath();
                Intent intent = new Intent();
                intent.setClass(Activity2.this, Second_Activity.class);//也可以这样写intent.setClass(MainActivity.this, OtherActivity.class);
                Activity2.this.startActivity(intent);
                setContentView(R.layout.activity_second_);
            }
        });
    }
}