package com.example.shiyan4.shiyan6;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shiyan4.Newmain1;
import com.example.shiyan4.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Newmain7 extends AppCompatActivity {
    private TextView liu;
    private Button liu_tao;

    TextView textView=null;
    String text=null;
    JSONObject object;
    String URL="https://raw.githubusercontent.com/liutao2019/android-labs-2019/master/students/soft1714080902410/6.photo/songer";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain3);
        textView=findViewById(R.id.liu);
        liu_tao=findViewById(R.id.liu_tao);
        Button liutao=(Button)findViewById(R.id.liu_tao);
        liutao.setOnClickListener(new View.OnClickListener() {

            public void getjson(){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            java.net.URL url =new URL(URL);
                            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(5000);
                            connection.setReadTimeout(5000);
                            connection.setUseCaches(false);
                            connection.connect();
                            InputStream in=connection.getInputStream();
                            InputStreamReader input=new InputStreamReader(in);
                            BufferedReader reader=new BufferedReader(input);
                            if (connection.getResponseCode()==200){
                                StringBuilder response=new StringBuilder();
                                String Line;
                                while ((Line=reader.readLine())!=null){
                                    response.append(Line);
                                }
                                text=response.toString();
                                System.out.println(text);
                                object=new JSONObject(text);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                        @SuppressLint("SetText")
                            @Override
                            public void run(){
                            try {
                                liu_tao.setText(object.getString("songer"));
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        });
                    }
                }).start();
            }
            @Override
            public void onClick(View v) {
                getjson();
            }
        });
    }
}
