package edu.androidlabs.thirdtest;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {


    private Button listbtn = null, stopbtn = null;
    private EditText etinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listbtn = (Button) findViewById(R.id.musicList);
        listbtn.setOnClickListener(new MainActivity.MyButtonListener1());
        stopbtn=(Button) findViewById(R.id.stop);
        stopbtn.setOnClickListener(new ButtonListener());
        etinfo=(EditText) findViewById(R.id.edit);
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
}
