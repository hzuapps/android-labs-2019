package edu.hzuapps.androidlabs.soft1714080902130;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Soft1714080902130DetailsActivity extends AppCompatActivity {
    private TextView jsonText;
    private Button return_button;
    private Button show_button;
    private  String url2="https://raw.githubusercontent.com/zrjdev/" +
            "android-labs-2019/master/students/soft1714080902130/实验六/details.json" ;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str= parseJson((String) msg.obj);
            jsonText.setText(str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902130_details);
        show_button=(Button)findViewById(R.id.showButton);
        return_button=(Button)findViewById(R.id.reT);
        jsonText=(TextView)findViewById(R.id.jsonText);
        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902130DetailsActivity.this,
                        Soft1714080902130LoginActivity.class);
                startActivity(intent);
            }
        });
        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run() {
                        try {
                            URL url = new URL(url2);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setConnectTimeout(5000);
                            conn.setRequestMethod("GET");
                            conn.connect();
                            InputStream inputStream = conn.getInputStream();
                            InputStreamReader is = new InputStreamReader(inputStream);
                            BufferedReader buffer = new BufferedReader(is);
                            if (conn.getResponseCode() == 200) {//200意味着返回的是"OK"
                                String inputLine;
                                StringBuffer resultData = new StringBuffer();//StringBuffer字符串拼接
                                while ((inputLine = buffer.readLine()) != null) {
                                    resultData.append(inputLine);
                                }
                                String data = resultData.toString();
                                Message msg = handler.obtainMessage();
                                msg.obj = data;
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            Toast.makeText(Soft1714080902130DetailsActivity.this, "解析出错", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }
    //JSONArray 直接解析数据
    public String parseJson(String data){
        StringBuilder info=new StringBuilder();
        try {
            JSONArray jsonArray=new JSONArray(data);
            int length=jsonArray.length();
            for (int i=0;i<length;i++){
                JSONObject object=jsonArray.getJSONObject(i);
                info.append("编号：");
                info.append(object.getString("id"));
                info.append("\n名字：");
                info.append(object.getString("name"));
                info.append("\n联系方式：");
                info.append(object.getString("phone"));
                info.append("\n所属部门：");
                info.append(object.getString("department"));
                info.append("\n\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return info.toString();
    }
}
