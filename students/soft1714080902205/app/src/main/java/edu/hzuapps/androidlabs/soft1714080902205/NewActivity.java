package edu.hzuapps.androidlabs.soft1714080902205;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class NewActivity extends AppCompatActivity {

    private EditText book_name_et;
    private Button btn_save;
    private Button btn_look;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        book_name_et=(EditText) findViewById(R.id.book_name_et);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_look=(Button) findViewById(R.id.btn_look);

        btn_save.setOnClickListener(new ButtonListener());
        btn_look.setOnClickListener(new ButtonListener());
    }
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_save:
                    String saveInfo=book_name_et.getText().toString().trim();
                    FileOutputStream mfos;
                    try {
                        mfos=openFileOutput("MyBook.txt",MODE_PRIVATE);
                        mfos.write(saveInfo.getBytes());
                        mfos.close();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    Toast.makeText(NewActivity.this,"保存成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_look:
                    String content="";
                    try {
                        FileInputStream mfis = openFileInput("MyBook.txt");
                        byte[] buffer = new byte[mfis.available()];
                        mfis.read(buffer);
                        content = new String(buffer);
                        mfis.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(NewActivity.this,"你保存的书名是："+content,Toast.LENGTH_SHORT).show();
                    break;
                default:
                }
            }
        }
}

