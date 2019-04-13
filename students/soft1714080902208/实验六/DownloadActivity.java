package edu.hzuapps.soft1714080902208;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class DownloadActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        imageView = (ImageView) findViewById(R.id.ImageView);
        button = (Button) findViewById(R.id.bt1);
        button1 = (Button) findViewById(R.id.bt2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();//创建异步任务对象
                System.out.println("图片正在下载");
                task.execute("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555089070578&di=6859db623ed144f0da6aefab9978bd71&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F48%2F31%2F01300001128119143159316221518.jpg");//创建下载任务
                System.out.println("图片下载完成,且已展示在界面");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {//读取图片

            @Override
            public void onClick(View v) {
                Bitmap bmp = null;
                try {
                    FileInputStream fis = openFileInput("a.png");
                    bmp = BitmapFactory.decodeStream(fis);
                    if (bmp != null) {
                        imageView.setImageBitmap(bmp);
                    }
                } catch (Exception ex) {
                }
            }
        });
    }

    public class MyTask extends AsyncTask<String, Void, Bitmap> {   //bmp位图,泛型
        @Override
        protected Bitmap doInBackground(String... params) { //真正的下载
            String urlStr = params[0];  //取第一个变参
            Bitmap bmp = null;
            try {
                URL url = new URL(urlStr);
                bmp = BitmapFactory.decodeStream(url.openStream()); //位图工厂,返回bitmap
                InputStream is = url.openStream();
                byte[] buffer = new byte[4096];//byte[]缓冲区
                FileOutputStream fos = DownloadActivity.this.openFileOutput("a.png", DownloadActivity.MODE_PRIVATE);
                int hasRead = is.read(buffer);
                while (hasRead > 0) {
                    fos.write(buffer, 0, hasRead);//文件大小，起始位置，读取的量
                    hasRead = is.read(buffer);
                }
                fos.close();//关闭
                is.close();//关闭
            } catch (Exception ex) {
            } finally {
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
