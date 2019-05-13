package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getJson();
        ImageButton imageButton=(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });
    }
    public void getJson(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url_s="https://raw.githubusercontent.com/517865058/android-labs-2019/master/students/soft1714080902301/json/test.json";

                    URL url=new URL(url_s);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setConnectTimeout(5000);//设置超时
                    conn.setUseCaches(false);//数据不多不用缓存了
                    conn.connect();
                    InputStream inputStream=conn.getInputStream();
                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);

                    if (conn.getResponseCode()==200){
                        String inputLine;
                        StringBuffer resultData=new StringBuffer();
                        while ((inputLine=bufferedReader.readLine())!=null){
                            resultData.append(inputLine);
                        }
                        String text=resultData.toString();
                        Log.v("out-----",text);
                        JSONObject jsonObject=new JSONObject(text);
                        TextView textView=(TextView)findViewById(R.id.name);
                        TextView textView1=(TextView)findViewById(R.id.username);
                        textView.setText(jsonObject.getString("name"));
                        textView1.setText(jsonObject.getString("username"));
                        //JSONArray jsonArray=jsonObject.getJSONArray("");

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            setResult(1);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton imageButton=(ImageButton)findViewById(R.id.imageButton);
            imageButton.setImageBitmap(imageBitmap);
        }
    }

}
