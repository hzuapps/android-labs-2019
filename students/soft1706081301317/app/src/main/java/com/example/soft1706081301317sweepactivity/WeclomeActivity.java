package com.example.soft1706081301317sweepacticity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeclomeActivity extends AppCompatActivity {

    private String text;
    private TextView textView;
    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weclome);
        TextView textView  = findViewById(R.id.username);
        getjson();
    }

    public void getjson() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://github.com/cqr22/android-labs-2019/tree/master/students/soft1706081301317/app/src/main/assets/content.json");
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
                        try {
                            JSONArray jsonArray = new JSONArray(text);
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(a);
                                String name = jsonObject.getString("username");
                                list.add(name);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      textView.setText(list.get(0).toString());
                    }
                });
            }
        }).start();
    }

}
