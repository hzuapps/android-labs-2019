package edu.hzuapps.soft1714080902437test5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class No4Activity extends Activity {
    private Button button6;
    private TextView json;
    private  String url="https://raw.githubusercontent.com/leexwen/android-labs-2019/master/students/soft1714080902437/app/src/main/homework.json";

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str= parseJson((String) msg.obj);
            json.setText(str);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no4);
        json= (TextView) findViewById(R.id.json);



        new Thread(){
            @Override
            public void run() {
                super.run();
                String data=doUrl(url);
                Message msg=handler.obtainMessage();
                msg.obj=data;
                handler.sendMessage(msg);
            }
        }.start();
    }
    public String doUrl(String u) {
        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            byte[] b = new byte[1024];
            StringBuilder sb = new StringBuilder();
            String a;
            int len;
            while ((len=is.read(b)) != -1) {
                a=new String(b,0,len);
                sb.append(a);
            }
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "解析出错", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    public String parseJson(String data){
                            StringBuilder sb=new StringBuilder();
                            try {
                                JSONArray jsonArray=new JSONArray(data);
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject object=jsonArray.getJSONObject(i);
                                    sb.append("教师名字：");
                                    sb.append(object.getString("tname"));
                                    sb.append("\n\n");
                                    sb.append("课程名称：");
                                    sb.append(object.getString("cname"));
                                    sb.append("\n\n");
                                    sb.append("课后作业：");
                                    sb.append(object.getString("homework"));
                                    sb.append("\n\n");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return sb.toString();
                        }


}















