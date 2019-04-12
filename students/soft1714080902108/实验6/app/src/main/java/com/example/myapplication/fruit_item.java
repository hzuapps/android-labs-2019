package com.example.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class fruit_item extends AppCompatActivity implements View.OnClickListener{
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_item);
        Button sendRquest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRquest.setOnClickListener(this);
    }

    public class App{
        private String id;
        private String name;
        private String version;
        public String getId(){
            return id;
        }
        public void setId(String id)
        {
            this.id = id;
        }

        public String getName(){
            return name;
        }
        public void setName(String name)
        {
            this.name = name;
        }
        public String getVersion(){
            return version;
        }
        public void setVersion(String version)
        {
            this.version = version;
        }


    }


    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://api.github.com/repos/hzuapps/android-labs-2019/issues").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson=new Gson();
        List<App> appList=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        int i=0;
        for(App app:appList){
            Log.d("fruit_item","id: "+ app.getId());
            Log.d("fruit_item","name: "+app.getName());
            Log.d("fruit_item","version:"+app.getVersion());
        }
    }
//    private void  parseJSONWithJSONObject(String jsonData){
//        try {
//            JSONArray jsonArray = new JSONArray(jsonData);
//            for(int i = 0;i < jsonArray.length();i++)
//            {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String id = jsonObject.getString("id");
//                String name = jsonObject.getString("name");
//                String version = version = jsonObject.getString("version");
//                Log.d("fruit_item","id is" + id);
//                Log.d("fruit_item","name is" + name);
//                Log.d("fruit_item","version is" + version);
//            }
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.send_request)
        {
            sendRequestWithOkHttp();
        }
    }
}
