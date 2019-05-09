package edu.hzuapps.androidlabs.soft1714080902401;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;

import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Soft1714080902401_FileStoreActivity extends AppCompatActivity {


    public static final String FILENAME = "file_demo.txt";
    public static final String TAG = Soft1714080902401_FileStoreActivity.class.getSimpleName();
    private EditText savetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_soft_1714080902401_filestore);
        savetext = (EditText) findViewById(R.id.savetext);
        String inputText = load();
        if(!TextUtils.isEmpty(inputText)){
            savetext.setText(inputText);
            savetext.setSelection(inputText.length());
            Toast.makeText(this,"读取成功！",Toast.LENGTH_LONG).show();
        }


    //     ((Button) findViewById(R.id.button_save_internal)).setOnClickListener(new View.OnClickListener() {
    //    @Override
    //    public void onClick(View view) {
    //           }
    //    });
    }

    @Override
    protected  void onDestroy(){
        super.onDestroy();
        String inputText = savetext.getText().toString();
        save(inputText);
    }
    // 将数据保存
    public void save(String inputText){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    //读取数据
    public  String load(){
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null){
                content.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }


}