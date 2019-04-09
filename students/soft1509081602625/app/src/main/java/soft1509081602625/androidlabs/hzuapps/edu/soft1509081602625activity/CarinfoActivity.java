package soft1509081602625.androidlabs.hzuapps.edu.soft1509081602625activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class CarinfoActivity extends AppCompatActivity {
    private TextView aodi_info;
    private Button  btn_collect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carinfo_activity);
        //获取布局文件中的控件
        aodi_info = (TextView) findViewById(R.id.aodi_info);
        btn_collect = (Button) findViewById(R.id.btn_collect);
        btn_collect.setOnClickListener(new ButtonListener());

    }

    public void onClick2(View view) {
        Intent intent = new Intent(CarinfoActivity.this, Soft1509081602625Activity.class);
        startActivity(intent);
    }
    private class ButtonListener implements View.OnClickListener{
        @SuppressLint("WrongConstant")
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_collect:
                    String collection = aodi_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos = openFileOutput("car.text", Context.MODE_APPEND);
                        fos.write(collection.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(CarinfoActivity.this,"收藏成功",0).show();
                    break;
            }
        }
    }


}
