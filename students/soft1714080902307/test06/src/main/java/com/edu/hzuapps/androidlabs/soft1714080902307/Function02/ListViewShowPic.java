package com.edu.hzuapps.androidlabs.soft1714080902307.Function02;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.edu.hzuapps.androidlabs.soft1714080902307.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ListViewShowPic extends Activity {

    ListView listView;
    //读写权限
    // 要申请的权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_listview);

        listView = (ListView) findViewById(R.id.lv_show_img);

        MyAdapter adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
        Log.d("logd", "--UI--");
    }

    class MyAdapter extends BaseAdapter {

        /**
         * 网上的图片url
         */
        String[] picUrls = new String[] {
                "https://gratisography.com/thumbnails/gratisography-small-waterfall-summer-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-plam-trees-summer-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-snow-trees-winter-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-348-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-381-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-369-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-13-thumbnail.jpg"
        };
        /**
         * 名称对应url上的图片名称
         */
        String[] picNames = new String[] {
                "1.jpg",
                "2.jpg",
                "3.jpg",
                "4.jpg",
                "5.jpg",
                "6.jpg",
                "7.jpg" };
        String path = Environment.getExternalStorageDirectory()
                + "/MyToolbox/wallpaper/";// 文件目录

        LayoutInflater layoutInflater;
        Context context;
        File fileDir;

        public MyAdapter(Context context) {
            this.context = context;
            layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            /**
             * 文件目录如果不存在，则创建
             */
            fileDir = new File(path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

        }

        @Override
        public int getCount() {
            return picUrls.length;
        }

        @Override
        public Object getItem(int position) {
            return picUrls[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = layoutInflater.inflate(R.layout.listview_img, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.img);

            /**
             * 创建图片文件
             */
            File file = new File(fileDir, picNames[position]);
            if (!file.exists()) {// 如果本地图片不存在则从网上下载
                downloadPic(picNames[position], picUrls[position]);
            } else {// 图片存在则填充到listview上
                Bitmap bitmap = BitmapFactory
                        .decodeFile(file.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
            }
            return view;
        }

        /**
         * 使用子线程下载图片    
         */
        private void downloadPic(final String name, final String picurl) {

            new Thread(new Runnable() {

                @Override
                public void run() {

                    FileOutputStream fos = null;
                    InputStream in = null;

                    // 创建文件
                    File file = new File(fileDir, name);

                    try {

                        fos = new FileOutputStream(file);

                        URL url = new URL(picurl);

                        in = url.openStream();

                        int len = -1;
                        byte[] b = new byte[1024];
                        while ((len = in.read(b)) != -1) {
                            fos.write(b, 0, len);
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fos != null) {
                                fos.close();
                            }
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }

    }
}
