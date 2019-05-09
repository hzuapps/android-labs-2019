package edu.hzuapps.androidlabs.com1714080901108;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Json2Activity extends AppCompatActivity {

    private TextView textview=null;
    String text = null;
    JSONObject object;
    String URL = "https://github.com/cxh245/android-labs-2019/raw/c6082bd6951e6a9ce4d371cc661bd4d22a166aff/students/com1714080901108/app/src/main/assets/Information.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json2);

        textview = (TextView) findViewById(R.id.textView5);
        Button btn1=(Button)findViewById(R.id.button7);

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
                                Log.i("name：",object.getString("name"));
                                Log.i("age：",object.getString("age"));
                                Log.i("isBoy：",object.getString("isBoy"));
                                Log.i("numbers：",object.getString("numbers"));
                                Log.i("courses：",object.getString("courses"));

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                                          @SuppressLint("SetTextI18n")
                                          @Override
                                          public void run() {
                                              try {
                                                  textview.setText("姓名："+ object.getString("name")+"\n"+"年龄："+object.getString("age")
                                                                    +"\n"+"是否男："+object.getString("isBoy")+"\n"+"号码："+object.getString("numbers")+
                                                                    "\n"+"课程："+object.getString("courses"));

                                              } catch (JSONException e) {
                                                  e.printStackTrace();
                                              }
                                          }
                                      }
                        );
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