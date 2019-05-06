package com.itcast.diarybill;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itcast.diarybill.databases.BillDao;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button record_one;
    private Button create_database;
    private TextView tv_income;
    private TextView tv_coast;
    private TextView tv_surplus;
    private TextView avg_income;
    private TextView avg_coast;
    private TextView tv_day;
    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    private ImageView iv;
    //数据库操作类
    private BillDao dao;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //记一笔
        record_one = findViewById(R.id.record_one);
        record_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManageActivity.class);
                startActivity(intent);
            }
        });

        //设置背景
        iv = (ImageView) findViewById(R.id.iv);
        //数据库操作类实例化
        dao=new BillDao(this);
        //记账天数
        tv_day=findViewById(R.id.tv_day);
        Integer totalDay=dao.totalDay();
        tv_day.setText(String.valueOf(totalDay)+"天");
        //查询总收入
        tv_income=findViewById(R.id.tv_income);
        Integer totalIncome=dao.selectTotalIncome();
        tv_income.setText(String.valueOf(totalIncome)+"元");
        //查询总支出
        tv_coast=findViewById(R.id.tv_coast);
        Integer totalCoast=dao.selectTotalCoast();
        tv_coast.setText(String.valueOf(totalCoast)+"元");
        //查询总剩余
        tv_surplus=findViewById(R.id.tv_surplus);
        Integer totalSurplus=dao.totalSurplus();
        tv_surplus.setText(String.valueOf(totalSurplus)+"元");
        //平均收入
        avg_income=findViewById(R.id.tv_avg_income);
        Float avgIncome=dao.avgIncome();
        avg_income.setText(String.valueOf(avgIncome+"元"));
        //平均支出
        avg_coast=findViewById(R.id.tv_avg_coast);
        Float avgCoast=dao.avgCoast();
        avg_coast.setText(String.valueOf(avgCoast)+"元");
    }

    //获取图片
    public void click(View view) {
        final String p_url[]={"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2824970053,2177995476&fm=26&gp=0.jpg","https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2653600404,2483690615&fm=26&gp=0.jpg","https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2663140694,3784357085&fm=26&gp=0.jpg"};
        if (TextUtils.isEmpty(p_url[0])){
            Toast.makeText(this, "图片路径不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //子线程请求网络，Android 4.0以后访问网络不能放在主线程中
            new Thread() {
                private HttpURLConnection conn;
                private Bitmap bitmap;

                public void run() {
                    //连接服务器get请求，获取图片
                    try {
                        //0-2的随机数
                        int rand=(int)(Math.random()*3);
                        //创建URL对象
                        URL url = new URL(p_url[rand]);
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
}
