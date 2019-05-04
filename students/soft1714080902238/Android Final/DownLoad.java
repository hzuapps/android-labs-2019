package edu.hzuapps.androidlabs.Soft1714080902238;

/**
 * Created by 75661 on 2019/4/18.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ll on 2018/3/14.
 */

public class DownLoad extends AsyncTask<String, Integer, Integer> {
    private String dirPath;//下载图片的目录 例如/root/pic/
    private String filePath;//下载存储的位置 例如/root/pic/a.jpg

    private Context context;


    public DownLoad(Context context, View btn) {
        this.context = context;

    }

    @Override
    protected Integer doInBackground(String... strings) {
        //耗时操作
        String fileName = strings[1];//要存储图片的名字

        //判断目录是否存在
        dirPath = Environment.getExternalStorageDirectory() + "/download_pics/";

        File dir = new File(dirPath);//目录对象
        if (!dir.exists()) {
            dir.mkdir();
        }

        //判断文件是否存在，若不存在，创建文件
        filePath = dirPath + strings[1];
        File file = new File(filePath);//创建文件对象
        if (file.exists()) {
            return -1;//已存在下载
        } else {
            try {
                file.createNewFile();//不存在，创建出这个文件
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //输入流、输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            //创建URL对象
            URL url = new URL(strings[0]);
            //通过URL对象得到HttpURLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //判断返回码是否正常，如果正常创建输入流，否则直接返回
            if (httpURLConnection.getResponseCode() == 200) {
                //得到输入流
                inputStream = httpURLConnection.getInputStream();
            } else {
                return -2;
            }
            outputStream = new FileOutputStream(file);
            int length = 0;
            byte[] buffer = new byte[4 * 1024];//缓存区
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return 1;//下载完成
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer) {
            case 1:
                //下载完成
                Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
                Bitmap bm = BitmapFactory.decodeFile(filePath);

                break;
            case -1:
                //已存在下载
                Toast.makeText(context, "已存在下载", Toast.LENGTH_SHORT).show();
                break;
            case -2:
                //网络异常
                Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}