package edu.hzuapps.androidlabs.soft1714080902120;


import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902120ImageSaveActivity extends Activity {

    // 图片地址
    private final String PATH = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555162912401&di=2331106c8c649e104b04385d81e59277&imgtype=0&src=http%3A%2F%2Fdingyue.nosdn.127.net%2FRJCmIWOJ3caq3tKeirRROpFE9L%3DXTsbDnFUB35qKTPj9R1476271917829.jpg";

    private ImageView imageView;
    private Button bt_set_wallpaper;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_save);

        imageView = findViewById(R.id.iv_image);
        bt_set_wallpaper = findViewById(R.id.bt_set_wallpaper);

        Button bt_get_image = findViewById(R.id.bt_get_image);
        bt_get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 让异步任务执行耗时操作
                new DownloadImage().execute(PATH);
            }
        });

        bt_set_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != bitmap) {
                    try {
                        setWallpaper(bitmap);
                        Toast.makeText(Soft1714080902120ImageSaveActivity.this, "壁纸设置成功", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Soft1714080902120ImageSaveActivity.this, "壁纸设置失败", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private Bitmap bitmap;

    class DownloadImage extends AsyncTask<String, Void, Object> {

        /**
         * 执行耗时操作前执行
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 弹出进度条
            progressDialog = new ProgressDialog(Soft1714080902120ImageSaveActivity.this);
            progressDialog.setMessage("Download ...");
            progressDialog.show();
        }

        /**
         * 执行耗时操作
         * @param strings
         * @return
         */
        @Override
        protected Object doInBackground(String... strings) {
            try {
                // 封装成网络地址
                URL url = new URL(PATH);

                // 打开一个连接
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                // 设置连接时长
                httpURLConnection.setConnectTimeout(5000);

                // 设置请求方式
                httpURLConnection.setRequestMethod("GET");

                /**
                 * 注意：⚠️ 不要肤浅的任务 打开连接对象 设置连接时长 设置请求方式 就向服务器发送Http请求了
                 *          是要执行httpURLConnection.getResponseCode()才会向服务器发送Http请求
                 */
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 得到服务器返回过来的流对象
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 耗时执行过程中 更新进度条刻度操作
         * @param values
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 耗时操作执行完成，用于更新UI
         * @param o
         */
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (o != null) { // 成功
                bitmap = (Bitmap) o;

                // 故意放慢两秒，模仿网络差的效果
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 设置从网上下载的图片
                        imageView.setImageBitmap(bitmap);
                        // 设置为可以点击
                        bt_set_wallpaper.setEnabled(true);

                        // 关闭进度条
                        progressDialog.dismiss();
                    }
                }, 2000);
            } else { //失败
                bt_set_wallpaper.setEnabled(false);
                Toast.makeText(Soft1714080902120ImageSaveActivity.this, "下载失败,请检查原因", Toast.LENGTH_LONG).show();
                // 关闭进度条
                progressDialog.dismiss();
            }
        }
    }
}