package edu.androidlabs.soft1714080902403;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902403 extends AppCompatActivity {



    private Button btn_read;



    TextView textView = null;
    String text = null;
    JSONObject object;
    String URL = "https://raw.githubusercontent.com/Coke1e/android-labs-2019/master/students/Soft1714080902403/app/src/main/zjl.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btn_read = (Button) findViewById(R.id.btn_read);
        btn_read.setOnClickListener(new ButtonListene());

        textView = findViewById(R.id.textview2);
        Button btn1=(Button)findViewById(R.id.button1);


        btn1.setOnClickListener(new View.OnClickListener() {

            public void getjson() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            java.net.URL url = new URL(URL);
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


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                                          @SuppressLint("SetTextI18n")
                                          @Override
                                          public void run() {
                                              try {
                                                  textView.setText(object.getString("answer"));

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


    public class ButtonListene implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_read:
                    String content="";
                    try {
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902403.this, "你输入的答案是："+content, Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

}
