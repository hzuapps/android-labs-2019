package edu.huzapp.fuyouapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileStorageActivity extends AppCompatActivity {
    private Button  btn_read;
    private Button  btn_save;
    private Button  btn_clean;
    private  RadioButton radio01;
    private  RadioButton radio02;
    private  RadioButton radio03;
    private  RadioButton radio04;
    private  RadioButton radio05;
    private  RadioButton radio06;
    private  RadioButton radio07;
    private  RadioButton radio11;
    private  RadioButton radio12;
    private  RadioButton radio13;
    private  RadioButton radio14;
    private  RadioButton radio15;
    private  RadioButton radio16;
    private  RadioButton radio17;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_storage);
        btn_read=(Button)findViewById(R.id.btn_read);
        btn_save=(Button)findViewById(R.id.btn_save);
        btn_clean=(Button)findViewById(R.id.btn_clean);
        radio01=(RadioButton)findViewById(R.id.mon);
        radio02=(RadioButton)findViewById(R.id.thur);
        radio03=(RadioButton)findViewById(R.id.tue);
        radio04=(RadioButton)findViewById(R.id.wen);
        radio05=(RadioButton)findViewById(R.id.fri);
        radio06=(RadioButton)findViewById(R.id.sat);
        radio07=(RadioButton)findViewById(R.id.sun);
        radio11=(RadioButton)findViewById(R.id.one);
        radio12=(RadioButton)findViewById(R.id.two);
        radio13=(RadioButton)findViewById(R.id.three);
        radio14=(RadioButton)findViewById(R.id.four);
        radio15=(RadioButton)findViewById(R.id.five);
        radio16=(RadioButton)findViewById(R.id.six);
        radio17=(RadioButton)findViewById(R.id.seven);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());

    }
    private class ButtonListener implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_save:
                    String saveInfo="";
                    if(radio01.isChecked()){saveInfo=saveInfo.concat("周一 ");}
                    else if(radio02.isChecked()){saveInfo=saveInfo.concat("周二 ");}
                    else if(radio03.isChecked()){saveInfo=saveInfo.concat("周三 ");}
                    else if(radio04.isChecked()){saveInfo=saveInfo.concat("周四 ");}
                    else if(radio05.isChecked()){saveInfo=saveInfo.concat("周五 ");}
                    else if(radio06.isChecked()){saveInfo=saveInfo.concat("周六 ");}
                    else if(radio07.isChecked()){saveInfo=saveInfo.concat("周日 ");}
                    if(radio11.isChecked()){saveInfo=saveInfo.concat(" 第一节 ");}
                    else if(radio12.isChecked()){saveInfo=saveInfo.concat(" 第二节 ");}
                    else if(radio13.isChecked()){saveInfo=saveInfo.concat(" 第三节 ");}
                    else if(radio14.isChecked()){saveInfo=saveInfo.concat(" 第四节 ");}
                    else if(radio15.isChecked()){saveInfo=saveInfo.concat(" 第五节 ");}
                    else if(radio16.isChecked()){saveInfo=saveInfo.concat(" 第六节 ");}
                    else if(radio17.isChecked()){saveInfo=saveInfo.concat(" 第七节 ");}
                    FileOutputStream fos;
                    try { fos=openFileOutput("data.txt",MODE_APPEND);//把文件输出到data中
                        fos.write(saveInfo.getBytes());//将我们写入的字符串变成字符数组）
                        fos.write(("\n").getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(FileStorageActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_read:
                    String content="";
                    try {
                        FileInputStream fis=openFileInput("data.txt");
                        byte [] buffer=new  byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(FileStorageActivity.this,"保存的数据是"+content,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_clean:
                    deleteFile("data.txt");
                    break;
                default:
            }
        }
    }
}