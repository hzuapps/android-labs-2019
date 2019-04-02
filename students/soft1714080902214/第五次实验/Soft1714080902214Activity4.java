package edu.hzuapps.androidlabs.soft1714080902214;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileOutputStream;



public class Soft1714080902214Activity4 extends Activity implements View.OnClickListener {


    private TimePicker timePicker;
    private Button button;
    private Button button_read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902214_activity4);
        timePicker = (TimePicker) findViewById(R.id.timePicker4);
        button = (Button) findViewById(R.id.button2);
        button_read = (Button) findViewById(R.id.button) ;
        button.setOnClickListener(new ButtonListener());
        button_read.setOnClickListener(new ButtonListener());

    }

    private class ButtonListener implements View.OnClickListener {

        public void onClick(View v){
            switch (v.getId()){

                case R.id.button2:

                    String saveinfo="";
                    int a = timePicker.getCurrentHour();
                    if(a<10){
                        saveinfo=saveinfo+"0"+timePicker.getCurrentHour().toString();
                    }else{
                        saveinfo=saveinfo+timePicker.getCurrentHour().toString();
                    }
                    saveinfo+=":";
                    int b = timePicker.getCurrentMinute();
                    if(b<10){
                        saveinfo=saveinfo+"0"+timePicker.getCurrentMinute().toString();
                    }else{
                        saveinfo=saveinfo+timePicker.getCurrentMinute().toString();
                    }

                    String saveinfo2="";
                    int aa=(a+5)%24;
                    int bb=b;
                    if(aa<10){
                        saveinfo2=saveinfo2+"0"+String.valueOf(aa);
                    }else{
                        saveinfo2=saveinfo2+String.valueOf(aa);
                    }
                    saveinfo2+=":";
                    if(bb<10){
                        saveinfo2=saveinfo2+"0"+String.valueOf(bb);
                    }else{
                        saveinfo2=saveinfo2+String.valueOf(bb);
                    }

                    String saveinfo3="";
                    int aaa=a+((b+30)/60);
                    int bbb=(b+30)%60;
                    if(aaa<10){
                        saveinfo3=saveinfo3+"0"+String.valueOf(aaa);
                    }else{
                        saveinfo3=saveinfo3+String.valueOf(aaa);
                    }
                    saveinfo3+=":";
                    if(bbb<10){
                        saveinfo3=saveinfo3+"0"+String.valueOf(bbb);
                    }else{
                        saveinfo3=saveinfo3+String.valueOf(bbb);
                    }

                    FileOutputStream fos;

                    try{
                        fos = openFileOutput("data1.txt", Context.MODE_PRIVATE);
                        fos.write(saveinfo.getBytes());
                        fos = openFileOutput("data2.txt",Context.MODE_PRIVATE);
                        fos.write(saveinfo2.getBytes());
                        fos = openFileOutput("data3.txt",Context.MODE_PRIVATE);
                        fos.write(saveinfo3.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902214Activity4.this,"选择成功",0).show();
                    Intent intent = new Intent();
                    intent.setClass(Soft1714080902214Activity4.this,Soft1714080902214Activity3.class);
                    startActivity(intent);
                    break;

                case R.id.button:
                    /*String content = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902214Activity4.this, content, 0).show();
                    break;*/
                    Toast.makeText(Soft1714080902214Activity4.this,"取消选择",0).show();
                    Intent intent1 = new Intent();
                    intent1.setClass(Soft1714080902214Activity4.this,Soft1714080902214Activity3.class);
                    startActivity(intent1);
                    break;
                default:
                    break;
            }
        }
    }
    @Override
    public void onClick(View v) {

    }

}
