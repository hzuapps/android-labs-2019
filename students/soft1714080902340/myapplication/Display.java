package com.cap.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Display extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_layout);
    }
*/
private ListView listView;   //界面组件
    private Bitmap[] bitmaps;    //定义一个位图数组，用来存放从网络下载下来的位图
    private ImageView[] images;  //定义一个ImageView数组，用来存放位图数组中的位图图片

    //网络图片资源
    private String[] urls = {
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2720761512,1992761174&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=401967138,750679164&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1059486618,1562064036&fm=26&gp=0.jpg" };

    //使用handler更新UI
    Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==111){
                listView.setAdapter(new MyAdapter());
            }
        };
    };

    //继承BaseAdapter
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return urls.length;
        }

        @Override
        public Object getItem(int arg0) {

            return arg0;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            images[arg0].setLayoutParams(layoutParams);
            return images[arg0];
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_layout);

        bitmaps = new Bitmap[urls.length];
        images = new ImageView[urls.length];
        listView = (ListView) findViewById(R.id.listView);

        // 为了下载图片资源，开辟一个新的子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < urls.length; i++) {
                    // 下载图片的路径
                    String iPath = urls[i];
                    try {
                        // 对资源链接
                        URL url = new URL(iPath);
                        // 打开输入流
                        InputStream inputStream = url.openStream();
                        // 对网上资源进行下载转换位图图片
                        bitmaps[i] = BitmapFactory.decodeStream(inputStream);
                        images[i] = new ImageView(Display.this);
                        images[i].setImageBitmap(bitmaps[i]);
                        inputStream.close();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(111);
            }

        }).start();


    }

}
