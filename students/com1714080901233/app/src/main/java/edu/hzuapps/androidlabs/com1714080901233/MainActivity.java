package edu.hzuapps.androidlabs.com1714080901233;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest=(Button) findViewById(R.id.button);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button)
        {
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread (new Runnable(){
            @Override
            public void run(){
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://raw.githubusercontent.com/wechat210/android-labs-2019/master/students/com1714080901233/test.json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseJSONWithGSON(String jsonData) {
        Gson gson=new Gson();
        List<Singer> singerList=gson.fromJson(jsonData,new TypeToken<List<Singer>>(){}.getType());
        int i=0;
        for(Singer singer:singerList){
            Log.d("MainActivity","id: "+singer.getId());

            Log.d("MainActivity","name: "+singer.getName());

            Log.d("MainActivity","gender: "+singer.getGender());

        }
    }


}