package com.example.soft1714080902113myexpress;



import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity{

    private EditText toFindPackage;
    private TextView expressText;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();

        if(actionBar!=null){
            actionBar.hide();
        }

        toFindPackage=(EditText) findViewById(R.id.toFindPackage);
        String inputText=load();
        if(!TextUtils.isEmpty(inputText)){
            toFindPackage.setText(inputText);
            toFindPackage.setSelection(inputText.length());
        }
        expressText=findViewById(R.id.expressinfo);
        sendRequestWithOkHttp();
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://10.0.2.2/myData.json")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSONWithGSON(String jsonData){
        Gson gson=new Gson();
        List<expresses> myList=gson.fromJson(jsonData,new TypeToken<List<expresses>>(){}.getType());
        String info="";
        for (expresses s:myList){
            info=info+"快递信息：\n快递单号："+s.getId()+"\n快递公司："+s.getCompany()+"\n收件地址："+s.getAddress()+"\n联系电话："+s.getPhone()+"\n收件人姓名："+s.getName()+"\n\n";
        }
        expressText.setText(info);
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText=toFindPackage.getText().toString();
        save(inputText);
    }
    public void save(String inputText){
        FileOutputStream out;
        BufferedWriter writer=null;
        try{
            out=openFileOutput("data", Context.MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in;
        BufferedReader reader=null;
        StringBuilder content=new StringBuilder();
        try{
            in=openFileInput("data");
            reader=new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


}

