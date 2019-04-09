package edu.hzuapps.androidlabs.soft1714080902421;

import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class ForeDownActivity extends AppCompatActivity {
            private ImageView iv_pic;
            private Button btn_get;
            @Override

            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_fore_down);
                btn_get = (Button) findViewById(R.id.btn_get);
                iv_pic = (ImageView) findViewById(R.id.iv_pic);
                //设置监听事件
                btn_get.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //开启子线程
                        new Thread(){
                            public void run() {
                                try {
                                    String urlPath = "http://pic7.nipic.com/20100607/4791134_172835008083_2.jpg";
                                    URL url = new URL(urlPath);
                                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                    conn.setConnectTimeout(6*1000);  // 注意要设置超时，设置时间不要超过10秒，避免被android系统回收
                                    if (conn.getResponseCode() != 200) throw new RuntimeException("请求url失败");
                                    InputStream inSream = conn.getInputStream();
                                    //把图片保存到项目的根目录
                                    readAsFile(inSream, new File(Environment.getExternalStorageDirectory()+"/"+"test.jpg"));
                                    Message msg=new Message();
                                    msg.what=0;
                                    handler.sendMessage(msg);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            };
                        }.start();
                    }
                });
            }
            public static void readAsFile(InputStream inSream, File file) throws Exception{
                FileOutputStream outStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = -1;
                while( (len = inSream.read(buffer)) != -1 ){
                    outStream.write(buffer, 0, len);
                }
                outStream.close();
                inSream.close();
            }
            //创建Handler
            Handler handler=new Handler(){
                public void handleMessage(android.os.Message msg) {
                    if(msg.what==0){
                        //开始绑定数据
                        iv_pic.setImageBitmap(BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/test.jpg"));
                    }
                };
            };
        }
