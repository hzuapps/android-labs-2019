package edu.hzuapps.androidlabs.Soft1714080902238;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;

import lun.com.myapplication.R;


public class carActivity extends Activity {
    private  TextView textView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
    }
    protected void Click1(View view){
        Intent intent = new Intent(this,DisplayActivity.class);

        startActivity(intent);
    }
    protected void onResume(){
        super.onResume();
        String content="";
        FileInputStream fis;
        try{
            fis=openFileInput("shopping.txt");
            byte[] buffer=new byte[fis.available()];
            fis.read(buffer);
            content=new String(buffer);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


       textView=(TextView)findViewById(R.id.car1);
        textView.setText(content);


    }
}
