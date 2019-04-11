package edu.huzapps.androidlabs.soft1714080902116;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{

    TextView responsetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
       Button sendRequest = (Button) findViewById(R.id.send_request);
       responsetText = (TextView) findViewById(R.id.response_text);
       sendRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.send_request){
           sendRequestWithOkHttp();
            Toast.makeText(Main3Activity.this,"解析json成功",Toast.LENGTH_SHORT).show();

        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            //指定访问的服务器地址是电脑本机
                            .url("https://raw.githubusercontent.com/1714080902116/android-labs-2019/master/students/soft1714080902116/get_data.json")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData =response.body().string();
                    parseJSONWithGSON(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void sendRequesWithHttpURLConnection(){
        //开启线程来发起网咯请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();

                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    showResponse(response.toString());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    {
                        if(reader != null){
                            try{
                                reader.close();
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        if(connection != null){
                            connection.disconnect();
                        }
                    }
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson=new Gson();
        List<App> appList=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for(App app:appList){
            Log.d("MainActivity","id: "+app.getId());
            Log.d("MainActivity","id: "+app.getName());
            Log.d("MainActivity","text: "+app.getVersion());
        }
    }


    private void showResponse(final String response){
        runOnUiThread(new Runnable(){
            @Override
            public void run(){
                responsetText.setText(response);
            }
        });
    }

}
