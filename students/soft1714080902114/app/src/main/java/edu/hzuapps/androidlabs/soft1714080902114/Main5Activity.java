package edu.hzuapps.androidlabs.soft1714080902114;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener {

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("https://raw.githubusercontent.com/lveking/android-labs-2019/master/students/soft1714080902114/app/src/main/java/edu/hzuapps/androidlabs/soft1714080902114/get_data.json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }catch(Exception e)

                {
                    e.printStackTrace();
                }
            }
        }).start();

    }



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main5);

    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.jx){
            sendRequestWithOkHttp();
           Toast.makeText(Main5Activity.this,"解析json成功",Toast.LENGTH_SHORT).show();
        }
    }

    private void parseJSONWithGSON(String jsonData){
        Gson gson=new Gson();
        List<App> applist=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for(App app :applist){
            Log.d("Main5Activity","id is" + app.getId());
            Log.d("Main5Activity","name is" + app.getName());
            Log.d("Main5Activity","version is " + app.getVersion());
        }
    }
}
