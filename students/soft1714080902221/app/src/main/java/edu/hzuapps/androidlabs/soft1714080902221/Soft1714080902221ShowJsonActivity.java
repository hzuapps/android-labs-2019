package edu.hzuapps.androids.soft1714080902221;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import edu.hzuapps.androidlabs.shiyan6_2.R;

public class Soft1714080902221ShowJsonActivity extends AppCompatActivity {
    private TextView tv_json;
    private  String url="https://raw.githubusercontent.com/zhengrunkai/" +
            "android-labs-2019/master/students/soft1714080902221/" +
            "app/src/main/Soft1714080902221_Json.json";
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str= parseJson((String) msg.obj);
            tv_json.setText(str);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902221_show_json);
        tv_json= (TextView) findViewById(R.id.tv_json);
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
                sb.append("姓名：");
                sb.append(object.getString("name"));
                sb.append("\n\n");
                sb.append("班级：");
                sb.append(object.getString("class"));
                sb.append("\n\n");
                sb.append("学号：");
                sb.append(object.getString("number"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}