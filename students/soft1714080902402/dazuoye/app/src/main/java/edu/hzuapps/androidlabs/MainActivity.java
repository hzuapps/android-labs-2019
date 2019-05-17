package edu.hzuapps.androidlabs;

/*import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    int[] imageIds=new int[]{R.drawable.shoe_ok,R.drawable.shoe_sorry,R.drawable.shoe_sorry};//定义一个保存全部图片的ID的数组
    private ImageView image1;           //ImageView组件1
    private ImageView image2;           //ImageView组件2
    private ImageView image3;           //ImageView组件3
    private TextView result;            //显示结果
    private void reset(){
        for(int i=0;i<3;i++)
        {
            int temp=imageIds[i];
            int index=(int)(Math.random()*2);
            imageIds[i]=imageIds[index];
            imageIds[index]=temp;
        }
    }
    image1=(ImageView)findViewById(R.id.imageView1);
    image2=(ImageView)findViewById(R.id.imageView2);
    image3=(ImageView)findViewById(R.id.imageView3);


    image1.setOnClickListener(new View.OnClickListener()){
        @Override
                public void onClick(View v){
            isRight(v,0);
        }
    }

}*/


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView text;
    private ImageView pic0;
    private ImageView pic1;
    private ImageView pic2;
    int[] imageIds = new int[]{R.drawable.shoe_ok, R.drawable.shoe_sorry,
            R.drawable.shoe_sorry};




    /*网络编程*/


    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    private EditText et_path;
    private ImageView iv;
    //主线程创建消息处理器
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == CHANGE_UI) {
                Bitmap bitmap = (Bitmap) msg.obj;
                iv.setImageBitmap(bitmap);
            } else if (msg.what == ERROR) {
                Toast.makeText(MainActivity.this, "显示图片错误", Toast.LENGTH_SHORT).show();
            }
        }
    };


    /**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pic0 = (ImageView) findViewById(R.id.pic0);
        pic1 = (ImageView) findViewById(R.id.pic1);
        pic2 = (ImageView) findViewById(R.id.pic2);
        Button butplay = (Button) findViewById(R.id.butplay);
        text = (TextView) findViewById(R.id.text);
        pic0.setOnClickListener(this);
        pic1.setOnClickListener(this);
        pic2.setOnClickListener(this);
        butplay.setOnClickListener(this);
        replay();//开局先将鞋子顺序打乱


            /*网络编程*/


        et_path = (EditText) findViewById(R.id.et_path);
        iv = (ImageView) findViewById(R.id.iv);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pic0:
                //设置已打乱顺序的鞋子图片
                pic0.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[0]));
                pic1.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[1]));
                pic2.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[2]));
                if (imageIds[0] == R.drawable.shoe_ok) {
                    //判断是否猜中有鸡蛋的鞋子
                    text.setText("恭喜你，猜对了，祝你幸福！");
                } else
                    text.setText("很抱歉，猜错了，要不要再试一次？");
                break;

            case R.id.pic1:
                pic0.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[0]));
                pic1.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[1]));
                pic2.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[2]));
                if (imageIds[1] == R.drawable.shoe_ok) {
                    text.setText("恭喜你，猜对了，祝你幸福！");
                } else
                    text.setText("很抱歉，猜错了，要不要再试一次？");
                break;

            case R.id.pic2:
                pic0.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[0]));
                pic1.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[1]));
                pic2.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, imageIds[2]));
                if (imageIds[2] == R.drawable.shoe_ok) {
                    text.setText("恭喜你，猜对了，祝你幸福！");
                } else
                    text.setText("很抱歉，猜错了，要不要再试一次？");
                break;

            case R.id.butplay:
                replay();

            default:
                break;
        }



        /**/


        final String path = et_path.getText().toString().trim();
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(this, "图片路径不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //子线程请求网络，Android 4.0以后访问网络不能放在主线程中
            new Thread() {
                private HttpURLConnection conn;
                private Bitmap bitmap;
                public void run() {
                    //连接服务器get请求，获取图片
                    try {
                        //创建URL对象
                        URL url = new URL(path);
                        //根据url发送http的请求
                        conn = (HttpURLConnection) url.openConnection();
                        //设置请求的方式
                        conn.setRequestMethod("GET");
                        //设置超时时间
                        conn.setConnectTimeout(5000);
                        //设置请求头User-Agent浏览器的版本
                        conn.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;" + "SV1;.NET4.0C;.NET4.0E;.NET CLR 2.0.50727;" + ".NET CLR 3.0.4506.2152;.NET CLR 3.5.30729;Shuame)");
                        //得到服务器返回的响应码
                        int code = conn.getResponseCode();
                        //请求网络成功后返回码是200
                        if (code == 200) {
                            //获取输入流
                            InputStream is = conn.getInputStream();
                            //将流转换成Bitmap对象
                            bitmap = BitmapFactory.decodeStream(is);
                            //TODO: 告诉主线程一个消息：帮我更改界面，内容：bitmap
                            Message msg = new Message();
                            msg.what = CHANGE_UI;
                            msg.obj = bitmap;
                            handler.sendMessage(msg);
                        } else {
                            //返回码不是200，请求服务器失败
                            Message msg = new Message();
                            msg.what = ERROR;
                            handler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Message msg = new Message();
                        msg.what = ERROR;
                        handler.sendMessage(msg);
                    }
                }
            }.start();
        }

    }

    public void replay() {
        //点击再玩一次，恢复原有标题和鞋子图片
        text.setText("猜猜鸡蛋在哪只鞋子里？");
        pic0.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.shoe_default));
        pic1.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.shoe_default));
        pic2.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.shoe_default));

        for (int i = 0; i < 3; i++) {
            int temp = imageIds[i]; // 将数组元素i保存到临时变量中
            int index = (int) (Math.random() * 2); // 生成一个随机数
            imageIds[i] = imageIds[index]; // 将随机数指定的数组元素的内容赋给数组元素i
            imageIds[index] = temp; // 将临时变量的值赋值给随机数组指定的那个数组元素
        }
    }




    /**/


}
