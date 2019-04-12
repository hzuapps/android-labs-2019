package edu.hzuapps.androidlabs.soft1714080902431;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;
import android.os.Handler;
import android.os.Message;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ImageActivity extends AppCompatActivity {
    protected static final int CHANGE_UI=1;
    protected static final int ERROR=2;
    private EditText et_path;
    private ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        et_path = (EditText) findViewById(R.id.et_path);
        iv = (ImageView) findViewById(R.id.iv);

    }

    @SuppressLint("WrongConstant")
    public void myclick(View view){
        final String path=et_path.getText().toString().trim();
        if(TextUtils.isEmpty(path)){
            Toast.makeText(this,"图片路径不能为空",0).show();
        }
        else {
            new Thread(){
                public void run(){
                    getImageByClient(path);
                }
            }.start();
        }
    }

    //使用HttpClient访问网络
    protected void getImageByClient(String path){
        HttpClient client=new DefaultHttpClient();
        HttpGet httpGet=new HttpGet(path);
        try{
            HttpResponse httpResponse=client.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                HttpEntity entity=httpResponse.getEntity();
                InputStream content=entity.getContent();
                Bitmap bitmap= BitmapFactory.decodeStream(content);
                Message message=new Message();
                message.what=CHANGE_UI;
                message.obj=bitmap;
                handler.sendMessage(message);

            }else {
                Message message=new Message();
                message.what=ERROR;
                handler.sendMessage(message);
            }
        }catch (Exception e){
            e.printStackTrace();
            Message message=new Message();
            message.what=ERROR;
            handler.sendMessage(message);
        }
    }

    //主线程创建消息处理器
    private Handler handler = new Handler() {
        @SuppressLint("WrongConstant")
        public void handleMessage(android.os.Message msg){
            if(msg.what==CHANGE_UI){
                Bitmap bitmap=(Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            }
            else if(msg.what==ERROR){
                Toast.makeText(ImageActivity.this,"显示图片错误",0).show();
            }
        };
    };
}