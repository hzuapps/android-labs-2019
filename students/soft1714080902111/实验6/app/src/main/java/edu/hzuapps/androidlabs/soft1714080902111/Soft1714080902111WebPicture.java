package edu.hzuapps.androidlabs.soft1714080902111;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902111WebPicture extends Activity {
    private EditText et_path;
    private ImageView imageView;
    private Button bt_set_wallpaper;
    private ProgressDialog progressDialog;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902111webpicture);
        et_path=(EditText) findViewById(R.id.et_path);///////5.30
        imageView = findViewById(R.id.iv_image);
        bt_set_wallpaper = findViewById(R.id.bt_set_wallpaper);

        Button bt_get_image = findViewById(R.id.bt_get_image);
        bt_get_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String PATH=et_path.getText().toString().trim();/////////5.30
                new DownloadImage().execute(PATH);
            }
        });
        bt_set_wallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != bitmap) {
                    try {
                        setWallpaper(bitmap);
                        Toast.makeText(Soft1714080902111WebPicture.this, "壁纸设置成功", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Soft1714080902111WebPicture.this, "壁纸设置失败", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    class DownloadImage extends AsyncTask<String, Void, Object> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 弹出进度条
            progressDialog = new ProgressDialog(Soft1714080902111WebPicture.this);
            progressDialog.setMessage("Download ...");
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(String... strings) {
            try {
                URL url = new URL(et_path.getText().toString().trim());
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (o != null) {
                bitmap = (Bitmap) o;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap);
                        bt_set_wallpaper.setEnabled(true);
                        progressDialog.dismiss();
                    }
                }, 2000);
            } else { //失败
                bt_set_wallpaper.setEnabled(false);
                Toast.makeText(Soft1714080902111WebPicture.this, "下载失败,请检查原因", Toast.LENGTH_LONG).show();
                // 关闭进度条
                progressDialog.dismiss();
            }
        }
    }
}

