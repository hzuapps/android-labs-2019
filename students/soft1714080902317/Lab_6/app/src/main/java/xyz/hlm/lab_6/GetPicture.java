package xyz.hlm.lab_6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GetPicture {
    private File file;
    private Handler handler;

    public GetPicture(Handler handler) {
        this.handler = handler;
        if (!(file  = new File("sdcard/lab/")).isDirectory()){
            file.mkdirs();
        }
        file = new File ("sdcard/lab/lab6.png");
    }

    public static Bitmap getImageBitmap() {
        return BitmapFactory.decodeFile("sdcard/lab/lab6.png");
    }

    public boolean download(String url){
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            if (!file.exists()) {
                /**访问图片*/
                URL imgUrl = new URL(url);
                conn = (HttpURLConnection) imgUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(10000);
                conn.connect();
                is = conn.getInputStream();

                /**保存图片到本地*/
                file.createNewFile();
                FileOutputStream out = new FileOutputStream(file);
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = is.read(b)) != -1) {
                    out.write(b,0,len);
                }
                is.close();
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);

        return true;
    }
}
