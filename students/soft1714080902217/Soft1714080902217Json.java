package edu.hzuapps.androidlabs.soft1714080902217;

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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Soft1714080902217Json extends AppCompatActivity {

    private TextView json;
    private  String url="https://raw.githubusercontent.com/RvingL/" +
            "android-labs-2019/master/students/soft1714080902217/Rving.json" ;
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
        setContentView(R.layout.activity_soft1714080902217_json);
        Button btnOpen = findViewById(R.id.button5);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("返回主页");
                startActivity(new Intent(Soft1714080902217Json.this, Soft1714080902217Activity.class));
            }
        });

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
                sb.append("id：");
                sb.append(object.getString("id"));
                sb.append("\n\n");
                sb.append("操作时间：");
                sb.append(object.getString("data"));
                sb.append("\n\n");
                sb.append("名字：");
                sb.append(object.getString("name"));
                sb.append("\n\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
