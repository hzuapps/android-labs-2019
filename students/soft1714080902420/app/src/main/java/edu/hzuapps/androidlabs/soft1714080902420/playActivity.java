package edu.hzuapps.androidlabs.soft1714080902420;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class playActivity extends AppCompatActivity {

    private EditText Text1;
    private Button btu_save;
    private Button btu_read;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Text1=(EditText)findViewById(R.id.text1);
        btu_save=(Button)findViewById(R.id.button13);
        btu_read=(Button)findViewById(R.id.button14);
        btu_save.setOnClickListener(new ButtonListener());
        btu_read.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener{
        public void onClick(View v){
            switch (v.getId()){
                case R.id.button13:
                    String saveinfo=Text1.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("entry.txt", Context.MODE_PRIVATE);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    }catch (Exception e){e.printStackTrace();}
                    Toast.makeText(playActivity.this,"影评已保存",Toast.LENGTH_LONG).show();
                    break;
                case R.id.button14:
                    String content="";
                    try{
                        FileInputStream fis=openFileInput("entry.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(playActivity.this,"您的影评是:"+content,Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    }
}
