package com.edu.hzuapps.abdroidlads.soft1714080902330;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

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
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555357403069&di=3166008fb5fbebcd2ff5f953c50b84fa&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%3Bcp%3Dtieba%2C10%2C307%3Bap%3D%25C4%25E3%25BB%25AD%25CE%25D2%25B2%25C2%25B0%25C9%2C90%2C315%2Fsign%3Dba5fa605c8177f3e1034fc0540f458b7%2F8e5475e736d12f2ed2af9ad34ec2d56284356817.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555357418127&di=8353bb7d7837702ac72d7265a18332c9&imgtype=0&src=http%3A%2F%2Ffmn.rrfmn.com%2Ffmn039%2F20100412%2F1825%2Fp_large_YorI_5cc60004c0a52d0f.jpg",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=999784675,2892116443&fm=26&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555357764155&di=c685b60fee95e6683386d0a4917a04b5&imgtype=0&src=http%3A%2F%2Fimg.weitiyuba.com%2Fmmbiz_jpg%2FnomvS9ahOd5FALDbSweaOl5ZDdMrE5zse4C1QaUJbBYpJ7ttjApm81Tp9IxZS6G7mwiapWHPPp4kiamlTCs1N4dg%2F0%3Fwx_fmt.jpeg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555357616652&di=a5259a4f071cbc4637f05e0c6918edd2&imgtype=0&src=http%3A%2F%2Fimg1.mtime.com%2Fmg%2F2010%2F12%2Fa83ea1ec-d013-4aed-8b22-029981c9191b.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555357636663&di=fbc4e2c542809aa7f49f267a9fe02c6d&imgtype=0&src=http%3A%2F%2Fpic.962.net%2Fup%2F2012-5%2F2012050311112457109.png",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555357677349&di=fcc36448329cc23b6c3b4df160662593&imgtype=0&src=http%3A%2F%2Fi1.hdslb.com%2Fbfs%2Farchive%2F41136b350f20918b3b51d9d37127a1c6f32fceba.jpg"
        };
        /**
         * 名称对应url上的图片名称
         */
        String[] picNames = new String[] {
                "6.jpg",
                "66.jpg",
                "666.jpg",
                "6666.jpg",
                "66666.jpg",
                "666666.jpg",
                "6666666.jpg" };
        String path = Environment.getExternalStorageDirectory()
                + "/listviewImg/";// 文件目录

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
         *
         * @param name
         * @param
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
