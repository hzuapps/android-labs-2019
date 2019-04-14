package edu.hzuapps.androidlabs.soft1714080902231;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/*修改为点击加载GitHut里的图片*/
public class AboutActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        /*String http="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555076106750&di=3dbb8e85d735db93396dfc1305a83162&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171211%2Ff406209e2cea47228cf851684f75858d.jpeg";*/
       String http="https://github.com/llinzhe/android-labs-2019/blob/f4aac49502b5440ed21f11c5cfcb0a5a4d34e659/students/%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E.PNG?raw=true";
        ImageView imageView = (ImageView) findViewById(R.id.image_view);
        //启动异步任务，加载网络图片
        new LoadImagesTask(imageView).execute(http);
    }

}


