package edu.hzuapps.androidlabs.soft1714080902225;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class Soft1714080902225_1<view> extends AppCompatActivity {
    Button love;
    Button detail;
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902225_1_activity);

        try {
//            InputStream is = this.getAssets().open("test.json");//eclipse
            InputStream is = Soft1714080902225_1.this.getClass().getClassLoader().getResourceAsStream("assets/" + "text.json");//android studio
            BufferedReader bufr = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bufr.readLine()) != null) {
                builder.append(line);
            }
            is.close();
            bufr.close();
            try {
                JSONObject root = new JSONObject(builder.toString());
                Log.d("info","cat=" + root.getString("cat"));
                JSONArray array = root.getJSONArray("languages");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject lan = array.getJSONObject(i);
                    Log.d("info","-----------------------");
                    Log.d("info","id=" + lan.getInt("id"));
                    Log.d("info","ide=" + lan.getString("ide"));
                    Log.d("info","name=" + lan.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject root = new JSONObject();
            root.put("cat", "it");

            JSONObject lan1 = new JSONObject();
            lan1.put("id", 1);
            lan1.put("ide", "Eclipse");
            lan1.put("name", "Java");

            JSONObject lan2 = new JSONObject();
            lan2.put("id", 2);
            lan2.put("ide", "XCode");
            lan2.put("name", "Swift");

            JSONObject lan3 = new JSONObject();
            lan3.put("id", 3);
            lan3.put("ide", "Visual Studio");
            lan3.put("name", "C#");

            JSONArray array = new JSONArray();
            array.put(lan1);
            array.put(lan2);
            array.put(lan3);

            root.put("languages", array);

            Log.d("info",root.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }







        et_info = (EditText) findViewById(R.id.et_info);
        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener((OnClickListener) new ButtonListener());
        btn_read.setOnClickListener((OnClickListener) new ButtonListener());

        detail = (Button) findViewById(R.id.detail);
        detail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902225_1.this, Soft1714080902225_2.class);
                startActivity(intent);
            }
        });
        View button = findViewById(R.id.love);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902225_1.this, Soft1714080902225_3.class);
                startActivity(intent);
            }
        });
    }

    public Button getbtn_save() {
        return btn_save;
    }

    public void setBtn_save(Button btn_save) {
        this.btn_save = btn_save;
    }

    private class ButtonListener implements OnClickListener {
        private view v;

        @SuppressLint("WrongConstant")
        public void onclick(View view) {
            switch (view.getId()) {
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(Soft1714080902225_1.this, "数据保存成功", 0).show();

                    break;
                case R.id.btn_read:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902225_1.this, "保存的数据是：" + content, 0).show();
                    break;
                default:
                    break;
            }
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {

        }
    }
}
