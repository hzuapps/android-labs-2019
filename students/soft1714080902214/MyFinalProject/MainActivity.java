package edu.hzuapps.androidlabs.soft1714080902214.myfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import java.io.FileInputStream;


public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    protected void Click(View view){
        Intent intent = new Intent(this,ChooseActivity.class);
        startActivity(intent);
    }

    protected void CClick(View v){
        Intent intent = new Intent(this,StarballActivity.class);
        startActivity(intent);
    }

    protected void CCClick(View v){
        Intent intent = new Intent(this,ShakeActivity.class);
        startActivity(intent);
    }

    protected void onResume(){
        super.onResume();
        String content1 = "";
        String content2 = "";
        String content3 = "";
        try {
            FileInputStream fis = openFileInput("data1.txt");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            content1 = new String(buffer);

            fis = openFileInput("data2.txt");
            buffer = new byte[fis.available()];
            fis.read(buffer);
            content2 = new String(buffer);

            fis = openFileInput("data3.txt");
            buffer = new byte[fis.available()];
            fis.read(buffer);
            content3 = new String(buffer);

        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView textView;
        textView = (TextView)findViewById(R.id.textView4);
        textView.setText(content1);
        textView = (TextView)findViewById(R.id.textView9);
        textView.setText(content2);
        textView = (TextView)findViewById(R.id.textView7);
        textView.setText(content3);
        textView = (TextView)findViewById(R.id.textView10);
        textView.setText(content2);
    }

}
