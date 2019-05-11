package edu.hzuapps.androidlabs.soft1714080902101;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    private String dirpath;
    private String filepath;
    private Context context;//上下文环境
    private Button btn;
    private ImageView img;

    public DownloadTask(Context context, Button btn, ImageView img) {
        this.context = context;
        this.btn = btn;
        this.img = img;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        String filename = strings[1];
        dirpath = Environment.getExternalStorageDirectory() + "/download_picture/";

        File dir = new File(dirpath);
        if (!dir.exists()) {

            dir.mkdir();
        }
        filepath = dirpath + filename;
        File file = new File(filepath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return -1;
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;


        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == 200) {

                inputStream = connection.getInputStream();
            } else {
                return -2;
            }
            outputStream = new FileOutputStream(file);
            int length = 0;
            byte[] buffer = new byte[4 * 1024];
            while ((length = inputStream.read(buffer)) != -1) {

                outputStream.write(buffer, 0, length);

            }
            inputStream.close();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        switch (integer) {
            case 1:

                Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
                Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                img.setImageBitmap(bitmap);

                break;
            case -1:

                Toast.makeText(context, "文件已存在", Toast.LENGTH_SHORT).show();
                Bitmap bm = BitmapFactory.decodeFile(filepath);
                img.setImageBitmap(bm);
                break;
            case -2:

                Toast.makeText(context, "网络连接失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
