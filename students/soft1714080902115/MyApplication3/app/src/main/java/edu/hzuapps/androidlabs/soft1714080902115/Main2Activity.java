package edu.hzuapps.androidlabs.Soft1714080902115;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;



public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       Button button1 = (Button) findViewById(R.id.send_request);

/*
       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Main2Activity.this,Main2Activity.class);
               startActivity(intent);
           }
       });
*/

       // Button sendRequest = (Button) findViewById(R.id.send_request);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);
    }



    @Override
    public void onClick(View v){
        if(v.getId()==R.id.send_request){
            sendRequestWithOkHttp();
            Toast.makeText(Main2Activity.this,"解析json成功",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://raw.githubusercontent.com/Breezee15/android-labs-2019/master/students/soft1714080902115/get_data.json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
        for(App app : appList){
            Log.d("Main2Activity", "id is " + app.getId());
            Log.d("Main2Activity", "name is " + app.getName());
            Log.d("Main2Activity", "version is " + app.getVersion());
        }

    }
}
