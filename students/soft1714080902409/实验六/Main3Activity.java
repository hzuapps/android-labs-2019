package com.example.myapplication3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {
    private TextView et_path;
    private Button bu;
    TextView textView=null;
    String text=null;
    JSONObject object;
    String URL = "https://raw.githubusercontent.com/Sakuraxia/android-labs-2019/master/data1/新建文本文档%20.JSON";
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView=findViewById(R.id.et_path);
        bu=findViewById(R.id.bu);
        Button bubu=(Button)findViewById(R.id.bu);
        bubu.setOnClickListener(new View.OnClickListener(){
            public void getjson(){
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        try{
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
                            if (connection.getResponseCode()==200) {
                                StringBuilder response = new StringBuilder();
                                String Line;
                                while ((Line = reader.readLine()) != null) {
                                    response.append(Line);
                                }
                                text = response.toString();
                                System.out.println(text);
                                object = new JSONObject(text);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetText")
                            @Override
                            public void run() {
                                try{
                                    bu.setText(object.getString("songer"));
                                }catch(JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }).start();
            }
            @Override
            public void onClick(View v){
                getjson();
            }
        });
    }

}
