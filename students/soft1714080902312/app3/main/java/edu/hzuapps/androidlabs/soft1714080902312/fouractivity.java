package edu.hzuapps.androidlabs.soft1714080902312;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class fouractivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

            FileInputStream fis=null;
            byte[] buffer=null;
            try {
            fis=openFileInput("btn3");
             buffer=new byte[fis.available()];
             fis.read(buffer);
             }catch (FileNotFoundException e){
            e.printStackTrace();
             }catch (IOException e){
            e.printStackTrace();
            }finally {
             if(fis!=null){
            try {
            fis.close();
             }catch (IOException e){
             e.printStackTrace();
                }
               }
             }
        TextView edit1TV=(TextView)findViewById(R.id.edit1);
        TextView edit2TV=(TextView)findViewById(R.id.edit2);

        String data=new String(buffer);
        String edit1=data.split(" ")[0];
        String edit2=data.split(" ")[1];

        edit1TV.setText("名字："+edit1);
        edit2TV.setText("生日："+edit2);
    }

}
