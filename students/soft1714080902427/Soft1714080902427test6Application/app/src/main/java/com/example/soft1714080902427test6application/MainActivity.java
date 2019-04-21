package com.example.soft1714080902427test6application;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText dow_path;
    Button bu_dow;
    ImageView image;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bu_dow=(Button)findViewById(R.id.bu_dow);
        image=(ImageView)findViewById(R.id.image);

        bu_dow.setOnClickListener(this);;
        image.setOnClickListener(this);;
    }
    /**
     * 获取网络图片
     * @param imageurl 图片网络地址
     * @return Bitmap 返回位图
     */
    public Bitmap GetImageInputStream(String imageurl){
        URL url;
        HttpURLConnection connection=null;
        Bitmap bitmap=null;
        try {
            url = new URL(imageurl);
            connection=(HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(6000); //超时设置
            connection.setDoInput(true);
            connection.setUseCaches(false); //设置不使用缓存
            InputStream inputStream=connection.getInputStream();
            bitmap= BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dow_path:
                //加入网络图片地址
                new Task().execute("http://www.agri35.com/UploadFiles/img_1_2825419015_1252740300_27.jpg");
                break;

            case R.id.bu_dow:
                //点击下载后将图片保存到SD卡跟目录下的Test文件夹内
                SavaImage(bitmap, Environment.getExternalStorageDirectory().getPath()+"/Test");
                Toast.makeText(getBaseContext(), "图片保存", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123){
                image.setImageBitmap(bitmap);
            }
        };
    };


    /**
     * 异步线程下载图片
     *
     */
    class Task extends AsyncTask<String, Integer, Void> {

        protected Void doInBackground(String... params) {
            bitmap=GetImageInputStream((String)params[0]);
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Message message=new Message();
            message.what=0x123;
            handler.sendMessage(message);
        }

    }

    /**
     * 保存位图到本地
     * @param bitmap
     * @param path 本地路径
     * @return void
     */
    public void SavaImage(Bitmap bitmap, String path){
        File file=new File(path);
        FileOutputStream fileOutputStream=null;
        //文件夹不存在，则创建它
        if(!file.exists()){
            file.mkdir();
        }
        try {
            fileOutputStream=new FileOutputStream(path+"/"+System.currentTimeMillis()+".png");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
