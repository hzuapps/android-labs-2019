package com.example.myandroid_2_3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GetInternetActivity extends AppCompatActivity {

    TextView textView = null;
    String text = null;
    JSONObject object;
    String URL="https://raw.githubusercontent.com/416zxj/android-labs-2019/master/students/soft1714080902416/record.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_internet);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textView = findViewById(R.id.textView1);
        Button btn1 = (Button) findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {


            public void getjson() {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            URL url = new URL(URL);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(8000);
                            connection.setReadTimeout(8000);
                            connection.setUseCaches(false);
                            connection.connect();
                            InputStream in = connection.getInputStream();
                            InputStreamReader input = new InputStreamReader(in);
                            BufferedReader reader = new BufferedReader(input);
                            if (connection.getResponseCode() == 200) {
                                StringBuilder response = new StringBuilder();
                                String Line;
                                while ((Line = reader.readLine()) != null) {
                                    response.append(Line);
                                }
                                text = response.toString();
                                System.out.println(text);
                                object = new JSONObject(text);
                                Log.i("消费时间time:", object.getString("time"));
                                Log.i("事件event：", object.getString("event"));
                                Log.i("消费金额cost：", object.getString("cost"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                try {
                                    textView.setText(object.getString("time") + "\t" + "事件：" + object.getString("event") + "\t" + "花费：" + object.getString("cost"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        });

                    }
                }).start();
            }
            @Override
            public void onClick(View v) {
                // 给bnt1添加点击响应事件
                getjson();
            }
        });

    }
}