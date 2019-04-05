package edu.androidlabs.soft1714080902403;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;

public class Soft1714080902403 extends AppCompatActivity {

    private TextView textView;
    private Button btn_read;
    private EditText et_info1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et_info1 = (EditText) findViewById(R.id.et_info1);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_read.setOnClickListener(new ButtonListene());
    }
    public class ButtonListene implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_read:
                    String content="";
                    try {
                        FileInputStream fis=openFileInput("data.txt");
                        byte[] buffer=new byte[fis.available()];
                        fis.read(buffer);
                        content=new String(buffer);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft1714080902403.this, "你输入的答案是："+content, Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }

}
