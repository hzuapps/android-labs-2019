package edu.hzuapps.androidlabs.gz_application;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }

        //跳转Button_hy
        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity.this, Button_hy.class);
                                           startActivity(intent);
                                       }
                                   }
        );


        //跳转Button_wq
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent intent = new Intent(MainActivity.this, Button_hy.class);
                                           startActivity(intent);
                                       }
                                   }
        );

        //解析按钮
        Button sendRequest = (Button) findViewById(R.id.send_request);
        sendRequest.setOnClickListener(this);


    }

        //文件存储
        @Override
        protected void onDestroy() {
            super.onDestroy();
            String inputText = edit.getText().toString();
            save(inputText);
        }

        public void save(String inputText) {
            FileOutputStream out = null;
            BufferedWriter writer = null;
            try {
                out = openFileOutput("data", Context.MODE_PRIVATE);
                writer = new BufferedWriter(new OutputStreamWriter(out));
                writer.write(inputText);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public String load() {
            FileInputStream in = null;
            BufferedReader reader = null;
            StringBuilder content = new StringBuilder();
            try {
                in = openFileInput("data");
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return content.toString();
        }


        //实验6_解析json
       private void sendRequestWithOkHttp() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("https://raw.githubusercontent.com/woshigaoshou/android-labs-2019/master/students/soft1714080902138/app/src/main/java/edu/hzuapps/androidlabs/myapplication138/set_data.json")
                                .build();
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();
                        parseJSONWithGSON(responseData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


    private void parseJSONWithGSON (String jsonData){
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {
        }.getType());
        int i=0;
        for (App app : appList) {
            Log.d("MainActivity", "id is" + app.getId());
            Log.d("MainActivity", "name is" + app.getName());
            Log.d("MainActivity", "version is" + app.getVersion());
        }
    }



    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send_request){
            sendRequestWithOkHttp();
            Toast.makeText(MainActivity.this,"解析json成功",Toast.LENGTH_SHORT).show();
        }
    }

}
