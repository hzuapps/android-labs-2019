package com.edu.hzuapps.androidlabs.soft1714080902307.Module.Function.Wallpaper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.hzuapps.androidlabs.soft1714080902307.Module.Function.CatGame.SleepCatActivity;
import com.edu.hzuapps.androidlabs.soft1714080902307.R;

public class ListViewShowPicActivity extends Activity {

    ListView listView;
    //读写权限
    // 要申请的权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private AlertDialog dialog;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        judgeorientation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallpaper_listview);
        listView = (ListView) findViewById(R.id.lv_show_img);

        MyAdapter adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
        Log.d("logd", "--UI--");
    }

    public void judgeorientation() {


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {//判断是否为竖屏
            showNormalDialog();

        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

    }
    public static class ConnectionUtil {
        //判断网络连接是否已开
        public static boolean isConn(Context context) {
            boolean bisConnFlag = false;
            ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo network = conManager.getActiveNetworkInfo();
            if (network != null) {
                bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
            }
            return bisConnFlag;
        }
        //打开网络设置界面
        public static void setNetworkMethod(final Context context){
            //提示对话框
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    Intent intent=null;
                    //判断手机系统的版本  即API大于10 就是3.0或以上版本
                    if(android.os.Build.VERSION.SDK_INT>10){
                        intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    }else{
                        intent = new Intent();
                        ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                        intent.setComponent(component);
                        intent.setAction("android.intent.action.VIEW");
                    }
                    context.startActivity(intent);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            }).show();
        }
    }
    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final android.support.v7.app.AlertDialog.Builder normalDialog =
                new android.support.v7.app.AlertDialog.Builder(ListViewShowPicActivity.this);

        normalDialog.setTitle("提示");
        normalDialog.setMessage("现在是竖屏哦，建议横屏哦");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        normalDialog.show();
    }
    class MyAdapter extends BaseAdapter {
        /**
         * 网上的图片url
         */
        String[] picUrls = new String[] {
                "https://gratisography.com/thumbnails/gratisography-tall-tree-barren-thumbnail.jpg",

                "https://gratisography.com/thumbnails/gratisography-225-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-158-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-225-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-41-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-black-white-trees-winter-thumbnail.jpg",
                "https://gratisography.com/thumbnails/gratisography-409-thumbnail.jpg"
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

            View view = layoutInflater.inflate(R.layout.wallpaper_listview_img, null);

            ImageView imageView = (ImageView) view.findViewById(R.id.img);

            /**
             * 创建图片文件
             */
            File file = new File(fileDir, picNames[position]);

            if (!file.exists()) {
                // 如果本地图片不存在则从网上下载
                //简单检测是否有开启网络开关
                if(!ConnectionUtil.isConn(getApplicationContext())){
                    ConnectionUtil.setNetworkMethod(ListViewShowPicActivity.this);
                    Toast.makeText(ListViewShowPicActivity.this, "您没有联网，请联网之后再查看壁纸~", Toast.LENGTH_SHORT).show();
                }else {

                    downloadPic(picNames[position], picUrls[position]);
                }

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
