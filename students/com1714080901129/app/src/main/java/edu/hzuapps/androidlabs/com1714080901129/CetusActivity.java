package edu.hzuapps.androidlabs.com1714080901129;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.google.gson.Gson;
import com.yzk.ordis.util.*;
import com.yzk.ordis.bean.*;


import java.io.IOException;

public class CetusActivity extends AppCompatActivity {

    private TextView Day,Time,ShortString;
    String Url="https://api.warframestat.us/pc/cetusCycle";
    CetusTime cetusTime =new CetusTime();
    GsonUtil gsonUtil=new GsonUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cetus);
        Day=(TextView)findViewById(R.id.day);
        Time=(TextView)findViewById(R.id.time);
        ShortString=(TextView)findViewById(R.id.shortString);

        sendRequestWithOkHttp();
//

    }
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程中执行Http请求，并将最终的请求结果回调到okhttp3.Callback中
                try {
                    httpUtil.sendOkHttpRequest(Url, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                String responseData=response.body().string();
                            System.out.println(responseData);

                            //gsonUtil.GsonToBean(responseData,CetusTime.class);
                            Gson gson=new Gson();
                            cetusTime=gson.fromJson(responseData,CetusTime.class);
                            if(cetusTime.isIsDay())
                            {
                                Day.setText("白天");
                            }else Day.setText("晚上");
                            System.out.println(cetusTime.getId());
                            Time.setText(cetusTime.getTimeLeft());
                            ShortString.setText(cetusTime.getTimeLeft());
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

