package edu.hzuapps.androidlabs.soft1714080902312;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class thirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        final EditText edit1ET=(EditText)findViewById(R.id.edit1);
        final EditText edit2ET=(EditText)findViewById(R.id.edit2);
        Button btn3=(Button)findViewById(R.id.btn3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit1=edit1ET.getText().toString();
                String edit2=edit2ET.getText().toString();
                FileOutputStream fos=null;

                try {
                    fos=openFileOutput("btn3",MODE_PRIVATE);

                    fos.write((edit1+" "+edit2).getBytes());
                    fos.flush();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if(fos!=null){
                        try {
                            fos.close();
                        }catch (IOException e){
                            e.printStackTrace();
                            Toast.makeText(thirdActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();

                        }
                    }
                }

                Intent intent = new Intent(thirdActivity.this, fouractivity.class);
                startActivity(intent);

            }
        });
    }

}
