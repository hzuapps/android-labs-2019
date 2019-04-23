package edu.hzuapps.androidlabs.soft1714080902319;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private EditText in;
    private Button button1;
    private Button button2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902319);

        in = (EditText) findViewById(R.id.in);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button1.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private class ButtonListener implements View.OnClickListener{
        @SuppressLint("WrongConstant")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button1:
                    String saveinfo=in.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"价格更改成功",0).show();
                    break;
                case R.id.button2:
                    String content="";
                    try {
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte [fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this,"更改的数据是："+content,0).show();
                    break;
                default:
                    break;
            }
        }
    }

}