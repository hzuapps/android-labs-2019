package edu.hzuapps.androidlabs.soft1714080902214.myfinalproject;

import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by windowsPC on 2019/5/2.
 */

public class StarballActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starball);
    }

    protected void onResume() {
        super.onResume();

        UserService userService = new UserService();
        try {
            String s = getstream();
            List<User> users = userService.getuser(StarballActivity.class.getClassLoader().getResourceAsStream(s));

            TextView textView;
            String name,score;

            name = users.get(0).getName();
            score = users.get(0).getScore();
            textView = (TextView) findViewById(R.id.textView14);
            textView.setText(name);
            textView = (TextView) findViewById(R.id.textView15);
            textView.setText(score);

            name = users.get(1).getName();
            score = users.get(1).getScore();
            textView = (TextView) findViewById(R.id.textView17);
            textView.setText(name);
            textView = (TextView) findViewById(R.id.textView18);
            textView.setText(score);

            name = users.get(2).getName();
            score = users.get(2).getScore();
            textView = (TextView) findViewById(R.id.textView19);
            textView.setText(name);
            textView = (TextView) findViewById(R.id.textView20);
            textView.setText(score);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getstream() throws IOException {
        try{
            String url = "https://raw.githubusercontent.com/chicken-noodle/android-labs-2019/master/students/soft1714080902214/userinfo.xml";
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            byte[] b = new byte[1024];
            StringBuilder sb = new StringBuilder();
            String a;
            int len;
            while((len=is.read(b))!=-1){
                a = new String(b,0,len);
                sb.append(a);
            }
            return sb.toString();
        } catch(IOException e){
            Toast.makeText(this, "解析出错", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}
