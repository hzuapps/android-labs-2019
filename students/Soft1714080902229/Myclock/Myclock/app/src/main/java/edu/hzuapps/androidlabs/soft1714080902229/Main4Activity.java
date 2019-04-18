package edu.hzuapps.androidlabs.soft1714080902229;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main4Activity extends AppCompatActivity {

    private ImageView img, showImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        img = (ImageView) findViewById(R.id.img);
        showImg = (ImageView) findViewById(R.id.showImg);
    }

    public void readImg(View v) {
        String path = Environment.getExternalStorageDirectory() + "/1.png";

        Bitmap bitmap = BitmapFactory.decodeFile(path);
        showImg.setImageBitmap(bitmap);


    }

    public void saveImg(View v) {
        //获取BitmapDrawable对象
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        File root = Environment.getExternalStorageDirectory();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(root + "/1.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //从网络获取图片
    public void getUrlImg(View v) {
        new GetImg().execute("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555597789397&di=f7a18b909edefa75502de42dac4603ba&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic3%2Fcover%2F00%2F90%2F03%2F58dbda1092ab8_610.jpg");
    }

    public  void saveHttpImg(View v){
        new SaveHttpImg().execute("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555597789397&di=f7a18b909edefa75502de42dac4603ba&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic3%2Fcover%2F00%2F90%2F03%2F58dbda1092ab8_610.jpg");
    }

    public class GetImg extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            HttpURLConnection con = null;
            //拿数据
            InputStream is = null;
            try {
                URL url=new URL(params[0]);
                con= (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5000);
                con.setReadTimeout(5*1000);
                if (con.getResponseCode()==200){
                    is=con.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    return bitmap;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (con!=null){
                    con.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            showImg.setImageBitmap(bitmap);
        }
    }

    public class SaveHttpImg extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection con = null;
            InputStream is = null;
            try {
                URL url = new URL(strings[0]);
                con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(5*1000);
                con.setReadTimeout(5*1000);
                File root = Environment.getExternalStorageDirectory();
                FileOutputStream fos = new FileOutputStream(root+"/http.jpg");
                if(con.getResponseCode()==200){
                    is = con.getInputStream();
                    int next=0;
                    byte[] bytes = new byte[1024];
                    while ( (next = is.read(bytes))>0){
                        fos.write(bytes,0,next);
                    }
                    fos.flush();
                    fos.close();
                    return  root+"/http.jpg";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(con!=null){
                    con.disconnect();
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!s.equals("")){
                Toast.makeText(Main4Activity.this, "保存成功:" + s, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Main4Activity.this,"保存失败:",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
