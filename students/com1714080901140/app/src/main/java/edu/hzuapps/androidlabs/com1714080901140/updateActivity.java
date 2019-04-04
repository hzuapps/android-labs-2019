package edu.hzuapps.androidlabs.com1714080901140;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class updateActivity extends AppCompatActivity {

    private Button update;
    private EditText update_name;
    private EditText update_number;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        update=(Button)findViewById(R.id.update);
        update.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            String name=update_name.getText().toString().trim();
            String number=update_number.getText().toString().trim();
            FileOutputStream fos1,fos2;
            try{
                fos1=openFileOutput("data.txt", Context.MODE_APPEND);
                fos2=openFileOutput("data.txt", Context.MODE_APPEND);
                fos1.write(name.getBytes());
                fos2.write(name.getBytes());
                fos1.close();
                fos2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(updateActivity.this,"数据修改成功",Toast.LENGTH_SHORT).show();
        }
}
}
