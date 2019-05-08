package edu.androidlabs.thirdtest;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity{

    private Button listbtn = null, stopbtn = null;
    private TextView etinfo;
    private Button but2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listbtn = (Button) findViewById(R.id.musicList);
        listbtn.setOnClickListener(new MainActivity.MyButtonListener1());
        stopbtn=(Button) findViewById(R.id.stop);
        stopbtn.setOnClickListener(new ButtonListener());
        etinfo=(TextView) findViewById(R.id.edit);
        but2=(Button)findViewById(R.id.download);
        but2.setOnClickListener(new MainActivity.MyButtonListener2());
    }

    private class MyButtonListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, MusicListActivity.class);
            startActivity(intent);
        }
    }
    private class ButtonListener implements View.OnClickListener{
        public void onClick(View V){
            String saveinfo=etinfo.getText().toString().trim();
            FileOutputStream fos;
            try{
                fos=openFileOutput("data.txt", Context.MODE_APPEND);
                fos.write(saveinfo.getBytes());
                fos.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();
        }
    }

    private class MyButtonListener2 implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            new downloadMP3Thread().start();
            Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_LONG).show();
        }
    }

    class downloadMP3Thread extends Thread{
        public void run(){
            HttpDownloader httpDownloader=new HttpDownloader();
            int downloadResult=httpDownloader.downloadFiles(
                    "https://raw.githubusercontent.com/AngleBeatQAQ/android-labs-2019/master/students/soft1714080902425/实验六/STEREO DIVE FOUNDATION - Daisy.mp3","BoBoMusic","STEREO DIVE FOUNDATION - Daisy.mp3");
            System.out.println("下载结果："+downloadResult);
        }
    }
}
