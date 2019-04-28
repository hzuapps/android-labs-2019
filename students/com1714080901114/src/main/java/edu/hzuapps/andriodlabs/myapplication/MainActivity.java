package edu.hzuapps.andriodlabs.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {


    private Button button1;
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_info=(EditText)findViewById(R.id.second);
        btn_read=(Button)findViewById(R.id.reads);
        btn_save=(Button)findViewById(R.id.saves);
        btn_read.setOnClickListener(new ButtonListener());
        btn_save.setOnClickListener(new ButtonListener());



        button1 = (Button) findViewById(R.id.kaka);//id后面为上方button的id

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });



    }

    public class ButtonListener implements View.OnClickListener {
        public void onClick(View v){
            switch (v.getId()){
                case R.id.saves:
                    String saveinfo=et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_PRIVATE);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "日期记录完成", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.reads:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "上次登录日期为："+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }




    public void ShowToast(View view){
        Toast.makeText(this,"biu",Toast.LENGTH_SHORT).show();
    }
}
