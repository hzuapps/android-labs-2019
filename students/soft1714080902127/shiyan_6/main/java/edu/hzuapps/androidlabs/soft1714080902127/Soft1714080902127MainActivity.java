package edu.hzuapps.androidlabs.soft1714080902127;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Soft1714080902127MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edit;
    TextView responseText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902127_mainactivity);

        //根据Id获取EditText实例
        edit=(EditText) findViewById(R.id.input_message);

        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this,"你上次输入的数据是："+inputText,Toast.LENGTH_SHORT).show();
        }

        ImageButton button1 = (ImageButton) findViewById(R.id.image_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Soft1714080902127MainActivity.this, Soft1714080902127Show1Activity.class);
                startActivity(intent);
            }
        });

        //实验6内容
        //获取ImgeButton1的实例
        ImageButton sendRequest=(ImageButton) findViewById(R.id.image_button1);
        sendRequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.image_button1){
            sendRequestWithOkHttp();
            Toast.makeText(Soft1714080902127MainActivity.this,"解析json成功",Toast.LENGTH_SHORT).show();
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
                            .url("https://raw.githubusercontent.com/410898194/android-labs-2019/master/students/soft1714080902127/shiyan_6/json/get_data.json")
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

    private void parseJSONWithGSON(String jsonData) {
        Gson gson=new Gson();
        List<App> appList=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        int i=0;
        for(App app:appList){
            Log.d("MainActivity","id: "+app.getId());
            Log.d("MainActivity","text: "+app.getText());
        }
    }

    //读取数据
    private String load() {
        FileInputStream in = null;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in =openFileInput("data");
            reader =new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    //重写onDestrory方法
    @Override
    public void onDestroy(){
        super.onDestroy();
        String inputText=edit.getText().toString();
        //将输入的内容存储到文件中
        save(inputText);
    }

   //保存数据
    private void save(String inputText) {
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if (writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



}
