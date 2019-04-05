package edu.androidlabs.soft1714080902403;

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

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btn_save;
    private EditText et_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_info = (EditText) findViewById(R.id.et_info);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new ButtonListener());
    }


    public class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "输入答案成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }



    public void click(View view){
        Intent intent=new Intent(this,Soft1714080902403.class);
        startActivity(intent);
    }

}


