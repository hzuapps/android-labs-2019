package edu.hzuapps.androidlabs.soft1714080902214;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.FileInputStream;
import java.sql.Time;

/**
 * Created by windowsPC on 2019/3/25.
 */

public class Soft1714080902214Activity3 extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902214_activity3);
        Log.i("Activity03", "onCreate()");
    }
    protected void Click(View view){
        Intent intent = new Intent(this,Soft1714080902214Activity4.class);
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
