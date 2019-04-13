package edu.hzuapps.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpThread extends Thread {
    private String url;
    private ImageView imageView;
    private Handler handler;

    public HttpThread(String url, ImageView imageView, Handler handler) {
        this.url = url;
        this.imageView = imageView;
        this.handler = handler;
    }
    @Override
    public void run() {
        try {
            URL httpURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpURL.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            InputStream in = connection.getInputStream();
            FileOutputStream outputStream = null;
            File downloadFile = null;
            String fileName  = String.valueOf(System.currentTimeMillis());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdPath = Environment.getExternalStorageDirectory();
                downloadFile = new File(sdPath, fileName);
                outputStream = new FileOutputStream(downloadFile);
            }
            byte[] b = new byte[5*1024];
            int len = 0;
            if (outputStream != null) {

                while ((len = in.read(b)) != -1) {
                    outputStream.write(b, 0, len);
                }
            }
            final Bitmap bitmap = BitmapFactory.decodeFile(downloadFile.getAbsolutePath());
            handler.post(new Runnable() {

                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);

                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

